package ast.expresiones;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.SeqExp;
import ast.designadores.Identificador;
import ast.tipo.Tipo;

public class Llamada extends E {

	private Identificador iden;
	private SeqExp seq;
	public Llamada(Identificador iden, SeqExp seq) {
		this.iden = iden;
		this.seq = seq;
	}
	private ArrayList<Tipo> listaTipos;
	
	public String toString() {
		return "llamada(" + this.iden.toString() + "," + this.seq.toString() + ")";
	}
	
	public KindE kind() {
		return KindE.LLAMADA;
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		this.iden.bind(pila);
		this.seq.bind(pila);
	}
	public void bindLlamadaClase(Stack<Map<String, ASTNode>> pila) {
		this.seq.bind(pila);
	}

	public Identificador getIden() {
		return iden;
	}

	public SeqExp getSeq() {
		return seq;
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		this.seq.type(funcion, val_switch, current_class);
		this.listaTipos = new ArrayList<Tipo>();
		this.seq.getListaTipos(listaTipos);
		if(this.iden.checkTipos(listaTipos)) {
			//TODO: error
		}
		this.tipo = iden.tipo;
	}
	
	public ArrayList<Tipo> tiparArgumentos(){
		this.seq.type(null, null, null);
		this.listaTipos = new ArrayList<Tipo>();
		this.seq.getListaTipos(listaTipos);
		return this.listaTipos;
	}
	

}
