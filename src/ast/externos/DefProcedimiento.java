package ast.externos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.designadores.Identificador;
import ast.externos.util.KindExt;
import ast.externos.util.Parametros;
import ast.instrucciones.BloqueInstrucciones;
import ast.tipo.Tipo;

public class DefProcedimiento implements Externo {
	
	private Tipo tipo = null;
	private Identificador opnd1;
	private Parametros opnd2;
	private BloqueInstrucciones opnd3;
	private ArrayList<Tipo> listaTipos;
	public int fila, columna;
	
	public DefProcedimiento(Identificador opnd1, Parametros opnd2, BloqueInstrucciones opnd3, int fila, int columna) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
		this.fila = fila;
		this.columna = columna;
	}

	public void bind (Stack<Map<String, ASTNode>> pila) {
		pila.peek().put(this.opnd1.getIden(), this);
		pila.push(new HashMap<String, ASTNode>());
		this.opnd2.bind(pila);
		this.opnd3.bind(pila);
		pila.pop();
	}
	
	public Identificador getOpnd1() {
		return opnd1;
	}

	public Parametros getOpnd2() {
		return opnd2;
	}

	public BloqueInstrucciones getOpnd3() {
		return opnd3;
	}

	@Override
	public KindExt kindExt() {
		return KindExt.DEF_PROCEDIMIENTO;
	}
	
	public String toString() {
		return "defProcedimiento(" + this.opnd1.toString() + "," + this.opnd2.toString() + ","
				+this.opnd3.toString() + ")";
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		opnd2.subsUserTypes(globalTypes);
		opnd3.subsUserTypes(globalTypes);
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		opnd2.type(null, null, current_class, continuable, breakeable);
		this.listaTipos = new ArrayList<Tipo>();
		opnd2.getListaTipos(listaTipos);
		opnd3.type(null, null, current_class, continuable, breakeable);
	}

	public ArrayList<Tipo> getListaTipos() {
		return listaTipos;
	}

	@Override
	public String generateCode(String code, int delta) {
		if(this.opnd1.getIden().equals("main")){
			return code + "(func $main\n" + this.opnd3.generateCode("", delta) + ")\n";
		}
				
		return this.opnd3.generateCode(code, delta);
	}

	public int precalcular(int i) {
		if(this.opnd1.getIden().equals("main")){
			return this.opnd3.precalcular(0);
		}else {
			return this.opnd3.precalcular(this.opnd2.precalcular(0));
		}
	}

	
	
	 
}
