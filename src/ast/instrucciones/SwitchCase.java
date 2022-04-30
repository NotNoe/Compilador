package ast.instrucciones;

import ast.ASTNode;
import ast.tipo.Tipo;

public abstract class SwitchCase implements ASTNode {
	public abstract void type(Tipo funcion, Tipo val_switch, Tipo current_class, boolean continuable, boolean breakeable);

	protected abstract int precalcular(int i);
}
