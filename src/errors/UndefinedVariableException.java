package errors;

import constructorast.Main;

@SuppressWarnings("serial")
public class UndefinedVariableException extends Exception {
	private int fila;
	private int columna;
	
	public UndefinedVariableException(String msg, int fila, int columna) {
		super(msg);
		this.fila = fila;
		this.columna = columna;
		Main.error = true;
	}
	
	public void print() {
		System.err.println("Error in line " + fila + ", at column " + columna+": "+ this.getMessage());
	}
	
	public UndefinedVariableException(String msg, int fila, int columna, Throwable cause) {
		super(msg, cause);
		this.fila = fila;
		this.columna = columna;
	}
	
	public int getFila() {
		return this.fila;
	}
	public int getColumna() {
		
		return this.columna;
	}
	
}
