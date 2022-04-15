package ast.externos;

import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.ArrayDimensiones;
import ast.designadores.Identificador;
import ast.externos.util.KindExt;
import ast.tipo.Tipo;

public class DefTipo implements Externo {

	private Tipo opnd1;
	private Identificador opnd2;
	private ArrayDimensiones opnd3;
	
	
	public Tipo getOpnd1() {
		return opnd1;
	}

	public void bind (Stack<Map<String, ASTNode>> pila) {
	}

	public Identificador getOpnd2() {
		return opnd2;
	}

	public DefTipo(Tipo opnd1, Identificador opnd2, ArrayDimensiones opnd3) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
	}

	public String toString() {
		if(opnd3 != null) {
			return "defTipo(" + this.opnd1.toString() + "," + this.opnd2.toString() + ")";
		}else {
			return "defTipo(" + this.opnd1.toString() + "," + this.opnd2.toString() + 
					"," + this.opnd3.toString() + ")";
		}
		
	}

	public KindExt kindExt() {
		return KindExt.DEF_TIPO;
	}

	public Tipo getBasicType(Map<String, Tipo> globalTypes) {
		return opnd3.tipar(opnd1.getBasicType(globalTypes));
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		
	}

}
