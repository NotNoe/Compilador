package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class BloqueInstrucciones implements ASTNode {
	
	private Instruccion opnd1;
	private BloqueInstrucciones opnd2;
	
	

	public BloqueInstrucciones(Instruccion opnd1, BloqueInstrucciones opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		if(this.opnd1 != null) {
			try {
				this.opnd1.bind(pila);
			} catch (UndefinedVariableException e) {
				e.print();
			}
			this.opnd2.bind(pila);
		}
	}

	public BloqueInstrucciones() {
		super();
	}

	public String toString() {
		if(opnd1 != null) {
			return "bloque_inst("+opnd1.toString()+","+opnd2.toString()+")";
		}else {
			return "bloque_inst()";
		}
	}


	public Instruccion getOpnd1() {
		return opnd1;
	}

	public BloqueInstrucciones getOpnd2() {
		return opnd2;
	}

	@Override
	public NodeKind nodeKind() {
		return NodeKind.BLOQUE_INST;
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		if(opnd1 != null) {
			opnd1.subsUserTypes(globalTypes);
			opnd2.subsUserTypes(globalTypes);
		}
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		if(this.opnd1 != null) {
			try {
				opnd1.type(funcion, val_switch, current_class, continuable, breakeable);
			} catch (TypeMissmatchException e) {
				e.print();
			}
			opnd2.type(funcion, val_switch, current_class, continuable, breakeable);
		}
	}

}
