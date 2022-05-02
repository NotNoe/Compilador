package ast.expresiones;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.SeqExp;
import ast.designadores.Designador;
import ast.designadores.Identificador;
import ast.externos.util.Parametro;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Llamada extends E {

	private Identificador link;
	private SeqExp seq;
	public Llamada(Identificador iden, SeqExp seq, int fila, int columna) {
		super(fila, columna);
		this.link = iden;
		this.seq = seq;
	}
	private ArrayList<Tipo> listaTipos;
	
	public String toString() {
		return "llamada(" + this.link.toString() + "," + this.seq.toString() + ")";
	}
	
	public KindE kind() {
		return KindE.LLAMADA;
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		try {
			this.link.bind(pila);
		} catch (UndefinedVariableException e) {
			e.print();
		}
		this.seq.bind(pila);
	}
	public void bindLlamadaClase(Stack<Map<String, ASTNode>> pila) {
		this.seq.bind(pila);
	}

	public Identificador getIden() {
		return link;
	}

	public SeqExp getSeq() {
		return seq;
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) throws TypeMissmatchException {
		this.seq.type(funcion, val_switch, current_class, continuable, breakeable, false);
		this.listaTipos = new ArrayList<Tipo>();
		this.seq.getListaTipos(listaTipos);
		this.link.type(funcion, val_switch, current_class, continuable, breakeable);
		this.tipo = link.tipo;
		if(!this.link.checkTipos(listaTipos)) {
			String msg = "Type missmatch in function " + this.link.getIden() + ", expected (";
			for(Tipo t : listaTipos) {
				msg += t.printT() + ", ";
			}
			if(listaTipos.size() > 1) {
				msg = msg.substring(0, msg.length() - 3);
			}
			msg += ").";
			throw new TypeMissmatchException(msg, this.fila, this.columna);
		}
	}
	
	public ArrayList<Tipo> tiparArgumentos() throws TypeMissmatchException{
		this.seq.type(null, null, null, false, false, false);
		this.listaTipos = new ArrayList<Tipo>();
		this.seq.getListaTipos(listaTipos);
		return this.listaTipos;
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		//copy params
		int offset = 0;
		String aux = "";
		ArrayList<Parametro> params = this.link.getParams();
		ArrayList<E> exps = this.seq.getListaExp(new ArrayList<E>());
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
		aux += "call $" + this.link.getIden() + "\n";
		return aux;
	}

	@Override
	protected int precalcular(int i) {
		return i;
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		this.seq.subsUserTypes(globalTypes);
	}
	
	

}
