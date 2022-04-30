package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.designadores.Designador;
import ast.tipo.KindType;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Delete extends Instruccion {

	private Designador opnd1;
	
	public String toString() {
		return "delete("+opnd1.toString()+")";
	}
	
	public Designador getOpnd1() {
		return opnd1;
	}

	public Delete(Designador opnd1, int fila, int columna) {
		super(fila, columna);
		this.opnd1 = opnd1;
	}



	public KindI kindI() {
		return KindI.DELETE;
	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
		try {
			opnd1.bind(pila);
		} catch (UndefinedVariableException e) {
			e.print();
		}
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		try {
			opnd1.type(funcion, val_switch, current_class, false, false);
			if(opnd1.tipo.kindType() != KindType.POINTER)
				(new TypeMissmatchException("Delete exception.", this.fila, this.columna)).print();
		} catch (TypeMissmatchException e) {
			e.print();
		}	
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
