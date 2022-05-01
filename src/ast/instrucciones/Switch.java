package ast.instrucciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.expresiones.Num;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Switch extends Instruccion {
	
	private E opnd1;
	private Cuerpo_Switch opnd2;
	
	

	public Switch(E opnd1, Cuerpo_Switch opnd2, int fila, int columna) {
		super(fila, columna);
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public String toString() {
		return "switch("+opnd1.toString()+ "," + opnd2.toString();
	}

	public void bind (Stack<Map<String, ASTNode>> pila) {
		try {
			this.opnd1.bind(pila);
		} catch (UndefinedVariableException e) {
			e.print();
		}
		this.opnd2.bind(pila);
	}
	
	
	@Override
	public KindI kindI() {
		return KindI.SWITCH;
	}

	public E getOpnd1() {
		return opnd1;
	}

	public Cuerpo_Switch getOpnd2() {
		return opnd2;
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
		} catch (TypeMissmatchException e) {
			e.print();
		}
		opnd2.type(funcion, opnd1.tipo, current_class, continuable, true);
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		String aux = "";
		ArrayList<SwitchCase> casos = new ArrayList<SwitchCase>();
		this.opnd2.getCases(casos);
		int max = 0;
		HashMap<Integer, Integer> map= new HashMap<Integer, Integer>();
		for(int i = 0; i < casos.size(); i++) {
			aux += "block\n";
			if(casos.get(i) instanceof Case) {
				int val = ((Num) ((Case) casos.get(i)).getOpnd1()).getValue();
				map.put(val, i);
				if(max < val) {
					max = val;
				}
			}else {
				map.put(-1, i);
			}
		}
		aux += "block\n";
		aux += this.opnd1.generateCode(code, delta, depth);
		aux += "br_table ";
		for(int i = 0; i <= max; i++) {
			if(map.containsKey(i)) {
				aux += map.get(i).toString() + " ";
			}else {
				aux += map.get(-1).toString() + " ";
			}
		}
		aux += map.get(-1).toString() + "\n";
		aux += "end\n";
		int i = 0;
		for(SwitchCase c : casos) {
			if(c instanceof Case) {
				aux += ((Case) c).getOpnd2().generateCode(code, delta, (casos.size() - 1 - i));
			}else {
				aux += ((Default) c).getOpnd1().generateCode(code, delta, (casos.size() - 1 - i));
			}
			aux += "end\n";
			i++;
		}
		return aux;
	}

	@Override
	protected int precalcular(int i) {
		return this.opnd2.precalcular(i);
	}
	
	

}
