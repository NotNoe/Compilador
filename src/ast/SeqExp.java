package ast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import ast.expresiones.E;
import ast.externos.util.Delim;
import ast.tipo.Array;
import ast.tipo.EmptyArray;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class SeqExp implements ASTNode {

	private E izq;
	private SeqExp der;
	public Tipo tipo;
	public int size = 0;
	
	public SeqExp(E izq, SeqExp der) {
		this.izq = izq;
		this.der = der;
	}
	
	public SeqExp() {
		
	}
	
	public void bind(Stack<Map<String, ASTNode>> pila) {
		if(this.izq != null) {
			try {
				this.izq.bind(pila);
			} catch (UndefinedVariableException e) {
				e.print();
			}
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

	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable, boolean esLista) throws TypeMissmatchException {
		if(izq != null) {
			izq.type(funcion, val_switch, current_class, continuable, breakeable);
			der.type(funcion, val_switch, current_class, continuable, breakeable, esLista);
			this.size = 1 + der.size;
			if(Tipo.equals(der.tipo, new Delim()))
				this.tipo = izq.tipo;
			else if(Tipo.equals(der.tipo, izq.tipo))
				this.tipo = izq.tipo;
			else
				if(esLista) {
					throw new TypeMissmatchException("Polymorphic arrays are not allowed.", izq.fila, izq.columna);
				}else {
					this.tipo = izq.tipo;
				}
		}else {
			this.tipo = new Delim();
		}
	}

	public void getListaTipos(ArrayList<Tipo> listaTipos) {
		if(izq != null) {
			listaTipos.add(izq.tipo);
			this.der.getListaTipos(listaTipos);
		}
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable)
			throws TypeMissmatchException {
		throw new RuntimeException("No se debería llamar");
		
	}
}