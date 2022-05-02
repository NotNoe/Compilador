package ast.designadores;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.expresiones.KindE;
import ast.expresiones.Llamada;
import ast.externos.DefClase;
import ast.externos.DefFuncion;
import ast.externos.DefProcedimiento;
import ast.externos.DefStruct;
import ast.externos.util.Parametro;
import ast.instrucciones.Declaracion;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Punto extends Designador {

	private Designador opnd1;
	private E opnd2;

	public Punto(Designador opnd1, Identificador opnd2, int fila, int columna) {
		super(fila, columna);
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}

	public Punto(Designador opnd1, Llamada opnd2, int fila, int columna) {
		super(fila, columna);
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}

	public void bind(Stack<Map<String, ASTNode>> pila) {
		try {
			this.opnd1.bind(pila);
		} catch (UndefinedVariableException e) {
			e.print();
		}
		switch (opnd2.kind()) {
		case LLAMADA:
			((Llamada) opnd2).bindLlamadaClase(pila);
			break;
		case DESIGNADOR:
			break;
		default:
			throw new RuntimeException("LOL");
		}
	}

	public String toString() {
		return "punto(" + this.opnd1.toString() + "," + this.opnd2.toString() + ")";
	}

	@Override
	public KindDes kindDes() {
		return KindDes.PUNTO;
	}

	public Designador getOpnd1() {
		return opnd1;
	}

	public E getOpnd2() {
		return opnd2;
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable)
			throws TypeMissmatchException {
		opnd1.type(funcion, val_switch, current_class, continuable, breakeable);
		switch (opnd1.tipo.kindType()) {
		case STRUCT:
			Map<String, ASTNode> ambito = ((DefStruct) opnd1.tipo).getAmbito();
			String iden = ((Identificador) opnd2).getIden();
			if (!ambito.containsKey(iden))
				throw new TypeMissmatchException(
						"Struct " + ((DefStruct) opnd1.tipo).printT() + " doesn't " + "contain field " + iden + ".",
						this.fila, this.columna);
			this.tipo = ((Declaracion) ambito.get(iden)).getOpnd2();
			break;
		case CLASE:
			switch (opnd2.kind()) {
			case LLAMADA:

				ASTNode aux = ((DefClase) opnd1.tipo).getAmbito().get(((Llamada) opnd2).getIden().getIden());
				if (aux == null) {
					throw new TypeMissmatchException("Class " + ((Llamada) opnd1.tipo).getIden() + " doesn't "
							+ "contain function " + ((Llamada) opnd2).getIden().getIden() + ".", this.fila,
							this.columna);
				}
				if (aux instanceof DefFuncion) {
					// Comprobar que coinciden los argumentos de la llamada
					ArrayList<Tipo> listaLlamada = ((Llamada) opnd2).tiparArgumentos();
					ArrayList<Tipo> listaOriginal = ((DefFuncion) aux).getListaTipos();
					if (!this.comprobarTipos(listaLlamada, listaOriginal)) {
						String msg = "Type missmatch in function " + ((Llamada) this.opnd2).getIden() + ", expected (";
						for (Tipo t : listaLlamada) {
							msg += t.printT() + ", ";
						}
						if (listaLlamada.size() > 1) {
							msg = msg.substring(0, msg.length() - 3);
						}
						msg += ").";
						throw new TypeMissmatchException(msg, this.fila, this.columna);
					}
					this.tipo = ((DefFuncion) aux).getOpnd1();
				} else if (aux instanceof DefProcedimiento) {
					ArrayList<Tipo> listaLlamada = ((Llamada) opnd2).tiparArgumentos();
					ArrayList<Tipo> listaOriginal = ((DefProcedimiento) aux).getListaTipos();
					if (!this.comprobarTipos(listaLlamada, listaOriginal)) {
						String msg = "Type missmatch in procedure " + ((Llamada) this.opnd2).getIden() + ", expected (";
						for (Tipo t : listaLlamada) {
							msg += t.printT() + ", ";
						}
						if (listaLlamada.size() > 1) {
							msg = msg.substring(0, msg.length() - 3);
						}
						msg += ").";
						throw new TypeMissmatchException(msg, this.fila, this.columna);
					}
					this.tipo = null;
				} else {
					throw new RuntimeException("LOL");
				}
				break;
			case DESIGNADOR:
				ASTNode aux2 = ((DefClase) opnd1.tipo).getAmbito().get(((Identificador) opnd2).getIden());
				if (aux2 == null)
					throw new TypeMissmatchException("Class " + ((Llamada) opnd1.tipo).getIden() + " doesn't "
							+ "contain field " + ((Llamada) opnd2).getIden().getIden() + ".", this.fila, this.columna);
				this.tipo = ((Declaracion) ((DefClase) opnd1.tipo).getAmbito().get(((Identificador) opnd2).getIden()))
						.getOpnd2();

				break;
			default:
				throw new RuntimeException("LOL");
			}
		break;
			
		default:
			throw new RuntimeException("LOL");
		}
	}

	private boolean comprobarTipos(ArrayList<Tipo> listaLlamada, ArrayList<Tipo> listaOriginal) {
		if (listaLlamada.size() == listaOriginal.size()) {
			for (int i = 0; i < listaLlamada.size(); i++) {
				if (!Tipo.equals(listaLlamada.get(i), listaOriginal.get(i))) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		switch (opnd1.tipo.kindType()) {
		case STRUCT: {
			String aux = this.getDir(delta);
			return aux + "i32.load\n";
		}
		case CLASE:
			switch(this.opnd2.kind()) {
			case DESIGNADOR:
				return this.getDir(delta) + "i32.load\n";
			case LLAMADA:{
				Llamada llam = (Llamada) this.opnd2;
				DefClase cl = (DefClase) opnd1.tipo;
				ASTNode def = cl.getAmbito().get(llam.getIden().getIden());
				ArrayList<Parametro> params;
				ArrayList<Declaracion> decs = cl.getDecList();
				if(def instanceof DefFuncion) {
					params = ((DefFuncion) def).getParams();
				}else {
					params = ((DefProcedimiento) def).getParams();
				}				 
				ArrayList<E> exps = llam.getSeq().getListaExp(new ArrayList<E>());
				
				String aux = "";
				int offset = 0; 
				for(int i = 0; i < decs.size(); i++) {
					aux += "get_global $SP\n" + 
							"i32.const " + (8 + offset) + "\n" +
							"i32.add\n" +
							"get_local $localsStart\n" +
							"i32.const " + (this.opnd1.getDelta() + decs.get(i).getDelta()) + "\n"+
							"i32.add\n"+
							"i32.store\n";
					offset += 4;
				}
				
				for(int i = 0; i < params.size(); i++) {
					Parametro p = params.get(i);
					E exp = exps.get(i);
					if(p.isRef()) {
						aux += "get_global $SP\n"+
								((Designador) exp).getDir(0) +
								"i32.store offset=" + (8+offset) + "\n";
						offset += 4;
					}else {
						if(exp.kind() == KindE.DESIGNADOR) {
							aux +=  ((Designador) exp).getDir(0) +
									"get_global $SP\n" +
									"i32.const " + (8+offset) + "\n" +
									"i32.add\n" +
									"i32.const " + (exp.tipo.getSize() / 4) + "\n" +
									"call $copyn\n";
							offset += exp.tipo.getSize();
						}else {
							aux += "get_global $SP\n" +
									exp.generateCode(code, delta, depth) + 
									"i32.store offset=" + (8+offset) + "\n";
							offset += 4;
						}
					}
				}
				aux += "call $" + llam.getIden().getIden() + "\n";
				return aux;
						
			}
			default:
				throw new RuntimeException("No se debería llegar aqui");
			}
			
		default:
			//TODO
			return null;
		}
	}

	@Override
	public String getDir(int delta) {
		if(this.opnd1 instanceof This) {
			Map<String, ASTNode> ambito = ((DefClase) opnd1.tipo).getAmbito();
			String iden = ((Identificador) opnd2).getIden();
			int dec_delta = ((Declaracion) ambito.get(iden)).getDelta();
			return "get_local $localsStart\n" +
			"i32.const " + dec_delta + "\n" +
			"i32.add\n" +
			"i32.load\n";
		}else {
			switch (opnd1.tipo.kindType()) {
			case STRUCT: {
				Map<String, ASTNode> ambito = ((DefStruct) opnd1.tipo).getAmbito();
				String iden = ((Identificador) opnd2).getIden();
				int delta_ini = opnd1.getDelta();
				int dec_delta = ((Declaracion) ambito.get(iden)).getDelta();
				return  "get_local $localsStart\n" +
						"i32.const " + (delta_ini + dec_delta) + "\n" +
						"i32.add\n";
			}
			case CLASE: {
				Map<String, ASTNode> ambito = ((DefClase) opnd1.tipo).getAmbito();
				String iden = ((Identificador) opnd2).getIden();
				int delta_ini = opnd1.getDelta();
				int dec_delta = ((Declaracion) ambito.get(iden)).getDelta();
				return "get_local $localsStart\n" +
						"i32.const " + (delta_ini + dec_delta) + "\n" +
						"i32.add\n";
			}
			default:
				//TODO:Clases
				return null;
			}
		}
	}

	@Override
	protected int precalcular(int i) {
		return i;
	}

	@Override
	public int getDelta() {
		return this.opnd1.getDelta();
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		this.opnd1.subsUserTypes(globalTypes);
	}
	
	

}
