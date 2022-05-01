package ast.instrucciones;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.ArrayDimensiones;
import ast.designadores.Identificador;
import ast.expresiones.E;
import ast.externos.DefClase;
import ast.externos.DefStruct;
import ast.externos.Externo;
import ast.externos.util.KindExt;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Declaracion extends Instruccion implements Externo {

	private boolean opnd1; // Indica si la variable es constante
	private Tipo opnd2;
	private Identificador opnd3;
	private ArrayDimensiones opnd4;
	private E opnd5;
	private int size;
	private int delta;

	public KindExt kindExt() {
		return KindExt.DECLARACION;
	}

	public Declaracion(boolean opnd1, Tipo opnd2, Identificador opnd3, ArrayDimensiones opnd4, E opnd5, int fila,
			int columna) {
		super(fila, columna);
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
		this.opnd4 = opnd4;
		this.opnd5 = opnd5;
	}

	public Declaracion(Tipo opnd2, Identificador opnd3, ArrayDimensiones opnd4, int fila, int columna) {
		super(fila, columna);
		this.opnd1 = false;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
		this.opnd4 = opnd4;
	}

	public String toString() {
		if (opnd5 == null) {
			return "declaracion(" + opnd2.toString() + "," + opnd3.toString() + "," + opnd4.toString() + ")";
		} else if (opnd1) {
			return "declaracion(const," + opnd2.toString() + "," + opnd3.toString() + "," + opnd4.toString() + ","
					+ opnd5.toString() + ")";
		} else {
			return "declaracion(" + opnd2.toString() + "," + opnd3.toString() + "," + opnd4.toString() + ","
					+ opnd5.toString() + ")";
		}
	}

	@Override
	public KindI kindI() {
		return KindI.DECLARACION;
	}

	public Boolean getOpnd1() {
		return opnd1;
	}

	public Tipo getOpnd2() {
		return opnd2;
	}

	public Identificador getOpnd3() {
		return opnd3;
	}

	public ArrayDimensiones getOpnd4() {
		return opnd4;
	}

	public E getOpnd5() {
		return opnd5;
	}

	public void bind(Stack<Map<String, ASTNode>> pila) {
		pila.peek().put(opnd3.getIden(), this);
		opnd4.bind(pila);
		if (opnd5 != null) {
			try {
				opnd5.bind(pila);
			} catch (UndefinedVariableException e) {
				e.print();
			}
		}
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		try {
			this.opnd2 = this.opnd2.getBasicType(globalTypes);
		} catch (TypeMissmatchException e) {
			e.print();
		}
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		this.opnd2 = opnd4.tipar(opnd2);
		if (opnd5 != null) {
			try {
				this.opnd5.type(funcion, val_switch, current_class, false, false);
				if (!Tipo.equals(opnd2, opnd5.tipo))
					(new TypeMissmatchException(
							"Type doesn't match between " + this.opnd2.printT() + " and " + this.opnd5.tipo.printT(),
							this.fila, this.columna)).print();
			} catch (TypeMissmatchException e) {
				e.print();
			}

		}
	}

	public int precalcular(int delta) {
		this.delta = delta;
		this.size = this.opnd2.getSize();
		return delta + this.size;
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		switch(this.opnd2.kindType()) {
		case STRUCT: 
			return ((DefStruct) opnd2).getCodeStruct(this.delta);
		
		case CLASE:
			return ((DefClase) opnd2).getCodeClass(this.delta);
		default:{
			String aux = "";
			if(this.opnd5 == null) {
				for(int i = 0; i < this.opnd2.getSize(); i = i + 4) {
					aux = aux + "i32.const " + this.delta + "\n" +"i32.const 0\n" + "i32.store offset=" + i + "\n";
				}
				return aux;
			}else {
				//TODO:asignacion array
				aux += "i32.const " + this.delta + "\n";
				aux += this.opnd5.generateCode(code, delta, depth);
				aux += "i32.store\n";
				return aux;
			}
		}
		}
		
	}

	public int getDelta() {
		return this.delta;
	}
	
	public int getSize() {
		return this.size;
	}

	public String getCodeExtern(int dir) {
		String aux = "";
		if(this.opnd5 == null) {
			for(int i = 0; i < this.opnd2.getSize(); i = i + 4) {
				aux = aux + "i32.const " + (dir + this.delta) + "\n" +"i32.const 0\n" + "i32.store offset=" + i + "\n";
			}
			return aux;
		}else {
			//TODO:asignacion array
			aux += "i32.const " + (dir + this.delta) + "\n";
			aux += this.opnd5.generateCode("", dir, 0);
			aux += "i32.store\n";
			return aux;
		}
	}
	
	

}
