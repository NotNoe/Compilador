package ast.externos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.designadores.Identificador;
import ast.externos.util.Parametro;
import ast.externos.util.KindExt;
import ast.externos.util.Parametros;
import ast.instrucciones.BloqueInstrucciones;
import ast.tipo.Tipo;

public class DefProcedimiento implements Externo {
	

	private Identificador opnd1;
	private Parametros opnd2;
	private BloqueInstrucciones opnd3;
	private ArrayList<Tipo> listaTipos;
	public int fila, columna;
	public int size;
	
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
		this.opnd1.setLink(this);
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
		opnd2.type(null, null, null, false, false);
		this.listaTipos = new ArrayList<Tipo>();
		opnd2.getListaTipos(listaTipos);
		opnd3.subsUserTypes(globalTypes);
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		opnd3.type(null, null, current_class, continuable, breakeable);
	}

	public ArrayList<Tipo> getListaTipos() {
		return listaTipos;
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		String aux = "(func $"+this.opnd1.getIden() + " (type $_sig_void)\n"
				+ "(local $localsStart i32)\r\n"
				+ "(local $temp i32)\n" 
				+ "i32.const " + (this.size + 8) + "\n"
				+ "call $reserveStack  ;; returns old MP (dynamic link)\r\n"
				+ "   set_local $temp\r\n"
				+ "   get_global $MP\r\n"
				+ "   get_local $temp\r\n"
				+ "   i32.store\r\n"
				+ "   get_global $MP\r\n"
				+ "   get_global $SP\r\n"
				+ "   i32.store offset=4\r\n"
				+ "   get_global $MP\r\n"
				+ "   i32.const 8\r\n"
				+ "   i32.add\r\n"
				+ "   set_local $localsStart\n"
				+ this.opnd3.generateCode(code, delta, 0)
				+ "call $freeStack\n"
				+ ")\n";
				
		return aux;
	}

	public int precalcular(int i) {
		this.size = this.opnd3.precalcular(this.opnd2.precalcular(i));
		return this.size;
	}

	public ArrayList<Parametro> getParams() {
		return this.opnd2.getParams(new ArrayList<Parametro>());
	}

	
	
	 
}
