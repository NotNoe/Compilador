package ast.externos;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.externos.util.KindExt;
import ast.externos.util.KindP;
import ast.instrucciones.Declaracion;
import ast.tipo.Tipo;
import constructorast.Main;
import errors.MainNotFoundException;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Programa implements ASTNode {

	private Externo opnd1;
	private Programa opnd2;
	private KindP kind;
	
	
	public Programa() {
		this.kind = KindP.VACIO;
	}
	
	public Programa(Externo opnd1, Programa opnd2) {
		this.kind = KindP.NO_VACIO;
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public void vincular(HashMap<String, ASTNode> globalAmb) {
		
		this.preBinding(globalAmb);
		if(!globalAmb.containsKey("main"))
			(new MainNotFoundException()).print();
		else if(((Externo) globalAmb.get("main")).kindExt() != KindExt.DEF_PROCEDIMIENTO)
			(new MainNotFoundException()).print();
		Stack<Map<String, ASTNode>> global = new Stack<Map<String, ASTNode>>();
		global.push(globalAmb);
		this.bind(global);
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		if(opnd1 != null) {
			try {
				this.opnd1.bind(pila);
			} catch (UndefinedVariableException e) {
				e.print();
			}
			this.opnd2.bind(pila);
		}
	}
	
	public void compilar() {
		HashMap<String, ASTNode> globalAmb = new HashMap<String, ASTNode>();
		this.vincular(globalAmb);
		Map<String, Tipo> globalTypes = new HashMap<String, Tipo>();
		try {
			getUserTypes(globalTypes);
		} catch (TypeMissmatchException e) {
			e.print();
		}
		this.subsUserTypes(globalTypes);
		this.type(null, null, null, false, false);
		
		if(Main.error)
			System.exit(1);
		
		int delta = this.precalcular(0);
		String code = "(module\r\n"
				+ "(type $_sig_i32i32i32 (func (param i32 i32 i32) ))\r\n"
				+ "(type $_sig_i32ri32 (func (param i32) (result i32)))\r\n"
				+ "(type $_sig_i32 (func (param i32)))\r\n"
				+ "(type $_sig_ri32 (func (result i32)))\r\n"
				+ "(type $_sig_void (func ))\n"
				+ "(import \"runtime\" \"print\" (func $print (param i32)))\r\n"
				+ "(import \"runtime\" \"read\" (func $read (result i32)))\r\n"
				+ "(import \"runtime\" \"exceptionHandler\" (func $exception (type $_sig_i32)))\n"
				+ "(memory 2000)\r\n"
				+ "(global $SP (mut i32) (i32.const 0)) ;; start of stack\r\n"
				+ "(global $MP (mut i32) (i32.const 0)) ;; mark pointer\r\n"
				+ "(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4\n"
				+ "(start $main)\r\n";
		
		code += this.generateCode(code, delta, 0);
		code += "(func $reserveStack (param $size i32)\r\n"
				+ "   (result i32)\r\n"
				+ "   get_global $MP\r\n"
				+ "   get_global $SP\r\n"
				+ "   set_global $MP\r\n"
				+ "   get_global $SP\r\n"
				+ "   get_local $size\r\n"
				+ "   i32.add\r\n"
				+ "   set_global $SP\r\n"
				+ "   get_global $SP\r\n"
				+ "   get_global $NP\r\n"
				+ "   i32.gt_u\r\n"
				+ "   if\r\n"
				+ "   i32.const 3\r\n"
				+ "   call $exception\r\n"
				+ "   end\r\n"
				+ ")\r\n"
				+ "(func $freeStack (type $_sig_void)\r\n"
				+ "   get_global $MP\r\n"
				+ "   i32.load\r\n"
				+ "   i32.load offset=4\r\n"
				+ "   set_global $SP\r\n"
				+ "   get_global $MP\r\n"
				+ "   i32.load\r\n"
				+ "   set_global $MP   \r\n"
				+ ")\r\n"
				+ "(func $reserveHeap (type $_sig_i32)\r\n"
				+ "   (param $size i32)\r\n"
				+ ";; ....\r\n"
				+ ")\r\n"
				+ "(func $copyn (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest\r\n"
				+ "   (param $src i32)\r\n"
				+ "   (param $dest i32)\r\n"
				+ "   (param $n i32)\r\n"
				+ "   block\r\n"
				+ "     loop\r\n"
				+ "       get_local $n\r\n"
				+ "       i32.eqz\r\n"
				+ "       br_if 1\r\n"
				+ "       get_local $n\r\n"
				+ "       i32.const 1\r\n"
				+ "       i32.sub\r\n"
				+ "       set_local $n\r\n"
				+ "       get_local $dest\r\n"
				+ "       get_local $src\r\n"
				+ "       i32.load\r\n"
				+ "       i32.store\r\n"
				+ "       get_local $dest\r\n"
				+ "       i32.const 4\r\n"
				+ "       i32.add\r\n"
				+ "       set_local $dest\r\n"
				+ "       get_local $src\r\n"
				+ "       i32.const 4\r\n"
				+ "       i32.add\r\n"
				+ "       set_local $src\r\n"
				+ "       br 0\r\n"
				+ "     end\r\n"
				+ "   end\r\n"
				+ ")";
		code += "(export \"main\" (func $main))\r\n"
				+ ")";
		
		System.out.println(code);
		
	}
	
	
	public void subsUserTypes(Map<String, Tipo> globalTypes) {
		if(this.kind == KindP.NO_VACIO) {
			this.opnd1.subsUserTypes(globalTypes);
			this.opnd2.subsUserTypes(globalTypes);
		}
	}

	private Map<String, Tipo> getUserTypes(Map<String, Tipo> globalTypes) throws TypeMissmatchException{
		if(this.kind == KindP.NO_VACIO) {
			switch(this.opnd1.kindExt()) {
			case DEF_CLASE:
				globalTypes.put(((DefClase) opnd1).getOpnd2().getIden(), (DefClase) opnd1);
				break;
			case DEF_STRUCT:
				globalTypes.put(((DefStruct) opnd1).getOpnd2().getIden(), (DefStruct) opnd1);
				break;
			case DEF_TIPO:
				globalTypes.put(((DefTipo) opnd1).getOpnd2().getIden(),((DefTipo) opnd1).getBasicType(globalTypes));
				break;
			default:
				break;
			}
			return opnd2.getUserTypes(globalTypes);
		}else {
			return globalTypes;
		}
	}
	

	public KindP kindP() {return this.kind;}
	
	private void preBinding(HashMap<String, ASTNode> globalBind) {
		if(this.kind != KindP.VACIO)
			switch(this.opnd1.kindExt()) {
			case DECLARACION:
				globalBind.put(((Declaracion) opnd1).getOpnd3().getIden(), opnd1);
				this.opnd2.preBinding(globalBind);
				break;
			case DEF_CLASE:
				globalBind.put(((DefClase) opnd1).getOpnd2().getIden(), opnd1);
				this.opnd2.preBinding(globalBind);
				break;
			case DEF_FUNCION:
				globalBind.put(((DefFuncion) opnd1).getOpnd2().getIden(), opnd1);
				this.opnd2.preBinding(globalBind);
				break;
			case DEF_PROCEDIMIENTO:
				globalBind.put(((DefProcedimiento) opnd1).getOpnd1().getIden(), opnd1);
				this.opnd2.preBinding(globalBind);
				break;
			case DEF_STRUCT:
				globalBind.put(((DefStruct) opnd1).getOpnd2().getIden(), opnd1);
				this.opnd2.preBinding(globalBind);
				break;
			case DEF_TIPO:
				globalBind.put(((DefTipo) opnd1).getOpnd2().getIden(), opnd1);
				this.opnd2.preBinding(globalBind);
				break;
			default:
				throw new RuntimeException("Error en prebinding");
			
			}
	}
	
	public Externo getOpnd1() {
		return opnd1;
	}

	public Programa getOpnd2() {return this.opnd2;}



	public NodeKind nodeKind() {
		return NodeKind.PROGRAMA;
	}
	
	public String toString() {
		if(this.kind == KindP.VACIO)
			return "fin_programa()";
		else
			return "programa(" + opnd1.toString() + "," + opnd2.toString() + ")";
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable) {
		if(this.kind == KindP.NO_VACIO) {
			try {
				opnd1.type(null, null, null, continuable, breakeable);
			} catch (TypeMissmatchException e) {
				e.print();
			}
			opnd2.type(null, null, null, continuable, breakeable);
		}
	}
	
	private int precalcular(int delta) {
		if(this.kind == KindP.NO_VACIO) {
			switch(this.opnd1.kindExt()) {
			case DECLARACION:
				return this.opnd2.precalcular(((Declaracion) opnd1).precalcular(delta));
			case DEF_CLASE:
				return this.opnd2.precalcular(((DefClase) opnd1).precalcular(0));
			case DEF_STRUCT:
				return this.opnd2.precalcular(((DefStruct) opnd1).precalcular(0));
			case DEF_FUNCION:
				return this.opnd2.precalcular(((DefFuncion) opnd1).precalcular(0));
			case DEF_PROCEDIMIENTO:
				return this.opnd2.precalcular(((DefProcedimiento) opnd1).precalcular(0));
			default:
				return this.opnd2.precalcular(delta);
			}
		}else {
			return delta;
		}
		
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		if(this.opnd1 != null) {
			switch(this.opnd1.kindExt()) {
			case DEF_CLASE:
				return ((DefClase) opnd1).generateCode(code, delta, depth) +
						this.opnd2.generateCode(code, 0, depth);
			case DEF_FUNCION:
				return ((DefFuncion) opnd1).generateCode(code, delta, depth) + 
						this.opnd2.generateCode(code, 0, depth);
			case DEF_PROCEDIMIENTO:
				return ((DefProcedimiento) opnd1).generateCode(code, delta, depth) +
						this.opnd2.generateCode(code, 0, depth);
			default:
				return "";
			}
		}else {
			return "";
		}
	}

}
