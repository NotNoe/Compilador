package ast.expresiones;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.SeqExp;
import ast.designadores.Identificador;
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
	public String generateCode(String code, int delta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int precalcular(int i) {
		return i;
	}
	

}
