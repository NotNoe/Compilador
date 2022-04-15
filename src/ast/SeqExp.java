package ast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import ast.expresiones.E;
import ast.tipo.Array;
import ast.tipo.EmptyArray;
import ast.tipo.Tipo;

public class SeqExp implements ASTNode {

	private E izq;
	private SeqExp der;
	public Tipo tipo;
	
	public SeqExp(E izq, SeqExp der) {
		this.izq = izq;
		this.der = der;
	}
	
	public SeqExp() {
		
	}
	
	public void bind(Stack<Map<String, ASTNode>> pila) {
		if(this.izq != null) {
			this.izq.bind(pila);
			this.der.bind(pila);
		}
	}
	
	public String toString() {
		if(izq == null)
			return "seqExp()";
		else
			return "seqExp("+izq.toString()+","+der.toString()+")";
	}


	public NodeKind nodeKind() {
		return NodeKind.SEQ_EXPRE;
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		if(izq != null) {
			izq.subsUserTypes(globalTypes);
			der.subsUserTypes(globalTypes);
		}
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		if(izq != null) {
			izq.type(funcion, val_switch, current_class);
			if(der.izq == null) {
				der.tipo = izq.tipo;
				this.tipo = new Array(izq.tipo);
			}else {
				der.type(funcion, val_switch, current_class);
				if(Tipo.equals(izq.tipo, ((Array) der.tipo).getTipo())) {
					this.tipo = new Array(izq.tipo);
				}else {
					//TODO: error
				}
			}
		}else {
			this.tipo = new EmptyArray();
		}
	}

	public void getListaTipos(ArrayList<Tipo> listaTipos) {
		if(izq != null) {
			listaTipos.add(izq.tipo);
		}
	}
}