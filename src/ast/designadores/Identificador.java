package ast.designadores;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.NodeKind;
import ast.externos.DefFuncion;
import ast.externos.DefProcedimiento;
import ast.externos.Externo;
import ast.externos.util.KindExt;
import ast.externos.util.Parametro;
import ast.instrucciones.Declaracion;
import ast.tipo.KindType;
import ast.tipo.Tipo;
import errors.TypeMissmatchException;
import errors.UndefinedVariableException;

public class Identificador extends Designador implements Tipo {

	private String iden;
	private ASTNode link;

	public Identificador(String iden, int fila, int columna) {
		super(fila, columna);
		this.iden = iden;
	}

	public String getIden() {
		return this.iden;
	}

	public void bind(Stack<Map<String, ASTNode>> pila) throws UndefinedVariableException {
		Stack<Map<String, ASTNode>> aux = new Stack<Map<String, ASTNode>>();
		while (!pila.empty()) {
			Map<String, ASTNode> top = pila.peek();
			if (top.containsKey(this.iden)) {
				this.link = top.get(this.iden);
				break;
			} else {
				pila.pop();
				aux.push(top);
			}
		}
		while (!aux.empty()) {
			pila.push(aux.pop());
		}
		if (this.link == null) {
			throw new UndefinedVariableException("Undefined use of identifier " + this.iden + ".", this.fila,
					this.columna);
		}
	}

	public String toString() {
		return "identificador(" + this.iden + ")";
	}

	public KindDes kindDes() {
		return KindDes.IDENFITICADOR;
	}

	@Override
	public KindType kindType() {
		return KindType.IDENTIFICADOR;
	}

	@Override
	public Tipo getBasicType(Map<String, Tipo> globalTypes) throws TypeMissmatchException {
		if (globalTypes.containsKey(this.iden)) {
			return globalTypes.get(this.iden);
		} else {
			throw new TypeMissmatchException("Cannot resolve type.", this.fila, this.columna);
		}
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable)
			throws TypeMissmatchException {
		if (this.link == null)
			throw new TypeMissmatchException("Cannot resolve type.", this.fila, this.columna);
		if (this.link.nodeKind() == NodeKind.PARAMETRO) {
			this.tipo = ((Parametro) link).getOpnd1();
		} else {
			switch (((Externo) link).kindExt()) {
			case DECLARACION:
				this.tipo = ((Declaracion) link).getOpnd2();
				break;
			case DEF_FUNCION:
				this.tipo = ((DefFuncion) link).getOpnd1();
				break;
			case DEF_PROCEDIMIENTO:
				this.tipo = null;
				break;

			}
		}
	}

	public boolean checkTipos(ArrayList<Tipo> listaTipos) throws TypeMissmatchException {
		if (this.link == null)
			throw new TypeMissmatchException("Cannot resolve type.", this.fila, this.columna);
		ArrayList<Tipo> lista2;
		if (((Externo) link).kindExt() == KindExt.DEF_PROCEDIMIENTO) {
			lista2 = ((DefProcedimiento) link).getListaTipos();
		} else if (((Externo) link).kindExt() == KindExt.DEF_FUNCION) {
			lista2 = ((DefFuncion) link).getListaTipos();
		} else {
			lista2 = new ArrayList<Tipo>();
		}
		if (lista2.size() == listaTipos.size()) {
			for (int i = 0; i < lista2.size(); i++) {
				if (!Tipo.equals(lista2.get(i), listaTipos.get(i))) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String printT() {
		return "ERR";
	}

	@Override
	public int getSize() {
		return this.tipo.getSize();
	}

	@Override
	public String generateCode(String code, int delta, int depth) {
		String aux = "";
		if (this.link.nodeKind() == NodeKind.PARAMETRO) {
			Parametro par = (Parametro) link;
			for (int i = 0; i < par.getOpnd1().getSize() / 4; i++) {
				aux += this.getDir(delta);
				aux += "i32.load offset=" + i + "\n";
			}
			return aux;
		} else {
			switch (((Externo) link).kindExt()) {
			case DECLARACION:
				Declaracion dec = (Declaracion) link;
				for (int i = 0; i < dec.getOpnd2().getSize() / 4; i++) {
					aux += this.getDir(delta);
					aux += "i32.load offset=" + i + "\n";
				}
				return aux;
			case DEF_FUNCION:
				// TODO
				return null;
			case DEF_PROCEDIMIENTO:
				// TODO
				return null;
			default:
				// TODO
				return null;
			}
		}
	}

	@Override
	public String getDir(int delta) {
		if (this.link.nodeKind() == NodeKind.PARAMETRO) {
			Parametro par = (Parametro) this.link;
			if (par.isRef()) {
				return "get_local $localsStart\n" + "i32.const " + par.delta + "\n" + "i32.add\n" + "i32.load\n";
			} else {
				return "get_local $localsStart\n" + "i32.const " + par.delta + "\n" + "i32.add\n";
			}

		} else {
			switch (((Externo) link).kindExt()) {
			case DECLARACION:
				Declaracion dec = (Declaracion) link;
				return "get_local $localsStart\n" + "i32.const " + dec.getDelta() + "\n" + "i32.add\n";
			default:
				// TODO
				return null;
			}
		}
	}

	@Override
	protected int precalcular(int i) {
		return i;
	}

	@Override
	public int getDelta() {
		return ((Declaracion) this.link).getDelta();
	}

	public ArrayList<Parametro> getParams() {
		switch (((Externo) this.link).kindExt()) {
		case DECLARACION:
			// TODO:
			return null;
		case DEF_FUNCION:
			DefFuncion fun = ((DefFuncion) this.link);
			return fun.getParams();
		case DEF_PROCEDIMIENTO:
			DefProcedimiento prod = ((DefProcedimiento) this.link);
			return prod.getParams();

		}
		return null;
	}

	public void setLink(ASTNode link) {
		this.link = link;
	}
	
	

}
