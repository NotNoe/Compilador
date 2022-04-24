package ast.designadores;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.expresiones.Llamada;
import ast.externos.DefClase;
import ast.externos.DefFuncion;
import ast.externos.DefProcedimiento;
import ast.externos.DefStruct;
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

}
