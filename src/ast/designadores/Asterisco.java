package ast.designadores;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.tipo.KindType;
import ast.tipo.Pointer;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Asterisco extends Designador {
	
	private Designador opnd1;
	public int fila, columna;
	
	public Asterisco(Designador op1, int fila, int columna) {
		super(fila, columna);
		this.opnd1 = op1;
		this.fila = fila;
		this.columna = columna;
	}
	
	public void bind(Stack<Map<String, ASTNode>> pila) throws UndefinedVariableException {
		this.opnd1.bind(pila);
	}
	
	public E opnd1() {return this.opnd1;} 

	@Override
	public KindDes kindDes() {
		return KindDes.ASTERISCO;
	}
	
	public String toString() {
		return "acceso("+this.opnd1.toString()+")";
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) throws TypeMissmatchException {
		opnd1.type(funcion, val_switch, current_class, continuable, breakeable);
		if(opnd1.tipo.kindType() == KindType.POINTER) {
			this.tipo = ((Pointer) opnd1.tipo).getP();
		}else {
			throw new TypeMissmatchException("Cannot access a not pointer type.", this.fila, this.columna);
		}
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDir(int delta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int precalcular(int i) {
		return i;
	}

	@Override
	public int getDelta() {
		return this.opnd1.getDelta();
	}

}
