package ast.instrucciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.tipo.Bool;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class While extends Instruccion {
	
	private E opnd1;
	private BloqueInstrucciones opnd2;
	
	

	public While(E opnd1, BloqueInstrucciones opnd2, int fila, int columna) {
		super(fila, columna);
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}

	public String toString() {
		return "while("+opnd1.toString() + "," + opnd2.toString() + ")";
	}

	@Override
	public KindI kindI() {
		return KindI.INSTRUCCION;
	}

	public E getOpnd1() {
		return opnd1;
	}

	public BloqueInstrucciones getOpnd2() {
		return opnd2;
	}

	@Override
	public void bind(Stack<Map<String, ASTNode>> pila) {
		try {
			opnd1.bind(pila);
		} catch (UndefinedVariableException e) {
			e.print();
		}
		pila.add(new HashMap<String, ASTNode>());
		opnd2.bind(pila);
		pila.pop();
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		opnd1.subsUserTypes(globalTypes);
		opnd2.subsUserTypes(globalTypes);
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		try {
			opnd1.type(funcion, val_switch, current_class, false, false);
			if(!Tipo.equals(opnd1.tipo, new Bool())) {
				(new TypeMissmatchException("Type doesn't match, it must be bool, not "+
						opnd1.tipo.printT(), this.fila, this.columna)).print();
			}
		} catch (TypeMissmatchException e) {
			e.print();
		}
		
		opnd2.type(funcion, val_switch, current_class, true, true);
	}

	@Override
	public String generateCode(String code, int delta) {
		return "block\n loop\n" + this.opnd1.generateCode(code, delta) + "i32.eqz\n" + "br_if 1\n" 
				+ this.opnd2.generateCode(code, delta) + "br 0\n" + "end\n" + "end\n";
	}

	@Override
	protected int precalcular(int i) {
		return this.opnd2.precalcular(i);
	}
	
	

}
