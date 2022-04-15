package ast.designadores;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import ast.ASTNode;
import ast.expresiones.E;
import ast.expresiones.KindE;
import ast.expresiones.Llamada;
import ast.externos.DefClase;
import ast.externos.DefFuncion;
import ast.externos.DefProcedimiento;
import ast.externos.DefStruct;
import ast.instrucciones.Declaracion;
import ast.tipo.Tipo;

public class Punto extends Designador {
	
	private Designador opnd1;
	private E opnd2;
	
	public Punto(Designador opnd1, Identificador opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public Punto(Designador opnd1, Llamada opnd2) {
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}
	
	public void bind (Stack<Map<String, ASTNode>> pila) {
		this.opnd1.bind(pila);
		switch(opnd2.kind()) {
		case LLAMADA :
			((Llamada) opnd2).bindLlamadaClase(pila);
			break;
		case DESIGNADOR :
			break;
		default:
			//TODO error
		}
	}
	
	public String toString() {
		return "punto("+this.opnd1.toString()+","+this.opnd2.toString()+")";
	}

	@Override
	public KindDes kindDes() {
		return KindDes.PUNTO;
	}

	public Designador getOpnd1() {
		return opnd1;
	}

	public E getOpnd2() {
		return opnd2;
	}

	@Override
	public void type(Tipo funcion, Tipo val_switch, Tipo current_class) {
		opnd1.type(funcion, val_switch, current_class);
		switch(opnd1.tipo.kindType()) {
		case STRUCT:
			this.tipo = ((Declaracion) (((DefStruct) opnd1.tipo).getAmbito().get(((Identificador) opnd2).getIden()))).getOpnd2();
			break;
		case CLASE:
			switch(opnd2.kind()) {
			case LLAMADA:
				ASTNode aux = ((DefClase) opnd1.tipo).getAmbito().get(((Llamada) opnd2).getIden().getIden());
				if(aux instanceof DefFuncion) {
					//Comprobar que coinciden los argumentos de la llamada
					ArrayList<Tipo> listaLlamada = ((Llamada) opnd2).tiparArgumentos();
					ArrayList<Tipo> listaOriginal = ((DefFuncion) aux).getListaTipos();
					if(!this.comprobarTipos(listaLlamada, listaOriginal)) {
						//TODO: error
					}
					this.tipo = ((DefFuncion) aux).getOpnd1();
				}else if(aux instanceof DefProcedimiento) {
					ArrayList<Tipo> listaLlamada = ((Llamada) opnd2).tiparArgumentos();
					ArrayList<Tipo> listaOriginal = ((DefProcedimiento) aux).getListaTipos();
					if(!this.comprobarTipos(listaLlamada, listaOriginal)) {
						//TODO: error
					}
					this.tipo = null;
				}else {
					//TODO error
				}
				break;
			case DESIGNADOR:
				this.tipo = ((Declaracion) ((DefClase) opnd1.tipo).getAmbito().get(((Identificador) opnd2).getIden())).getOpnd2();
				break;
			default:
				//TODO: error
				break;
			}
		default:
			//TODO: error
			break;
		}
	}

	private boolean comprobarTipos(ArrayList<Tipo> listaLlamada, ArrayList<Tipo> listaOriginal) {
		if(listaLlamada.size() == listaOriginal.size()) {
			for(int i = 0; i < listaLlamada.size(); i++) {
				if(!Tipo.equals(listaLlamada.get(i), listaOriginal.get(i))) {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}

	
	
}
