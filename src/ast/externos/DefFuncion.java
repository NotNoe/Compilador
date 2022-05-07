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
import errors.TypeMissmatchException;

public class DefFuncion implements Externo {

	private Tipo opnd1;
	private Identificador opnd2;
	private Parametros opnd3;
	private BloqueInstrucciones opnd4;
	private ArrayList<Tipo> listaTipos;
	public int fila, columna;
	public int size;
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		pila.peek().put(this.opnd2.getIden(), this);
		pila.push(new HashMap<String, ASTNode>());
		this.opnd3.bind(pila);
		this.opnd4.bind(pila);
		pila.pop();
	}
	
	public DefFuncion(Tipo opnd1, Identificador opnd2, Parametros opnd3, BloqueInstrucciones opnd4, int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.opnd3 = opnd3;
		this.opnd4 = opnd4;
	}

	

	public Tipo getOpnd1() {
		return opnd1;
	} 



	public Identificador getOpnd2() {
		return opnd2;
	}



	public Parametros getOpnd3() {
		return opnd3;
	}



	public BloqueInstrucciones getOpnd4() {
		return opnd4;
	}



	public KindExt kindExt() {
		return KindExt.DEF_FUNCION;
	}
	
	public String toString() {
		return "defFuncion(" + this.opnd1.toString() + "," + this.opnd2.toString() + "," +
				this.opnd3.toString() + "," + this.opnd4.toString() + ")";
	}

	@Override
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		try {
			this.opnd1 = opnd1.getBasicType(globalTypes);
		} catch (TypeMissmatchException e) {
			e.print();
		}
		opnd3.subsUserTypes(globalTypes);
		opnd3.type(null, null, null, false, false);
		this.listaTipos = new ArrayList<Tipo>();
		opnd3.getListaTipos(this.listaTipos);
		opnd4.subsUserTypes(globalTypes);
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		
		opnd4.type(this.opnd1, val_switch, current_class, continuable, breakeable);
	}

	public ArrayList<Tipo> getListaTipos() {
		return listaTipos;
	}

	public int precalcular(int i) {
		this.size = this.opnd4.precalcular(this.opnd3.precalcular(i));
		return this.size;
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		String aux = "(func $"+this.opnd2.getIden() + " (type $_sig_ri32)\n"
				+ "(result i32)\n"
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
				+ this.opnd4.generateCode(code, delta, 0)
				+ "i32.const 0"
				+ ")\n";
				
		return aux;
	}

	public ArrayList<Parametro> getParams() {
		return this.opnd3.getParams(new ArrayList<Parametro>());
	}
	

}
