package errors;

import constructorast.Main;

public class OutOfContextControlStatementException extends Exception {
	private int fila;
	private int columna;
	
	public OutOfContextControlStatementException(int fila, int columna) {
		super("Our of context control statement.");
		this.fila = fila;
		this.columna = columna;
		Main.error = true;
	}
	
	public void print() {
		System.err.println("Error in line " + fila + ", at column " + columna+": "+ this.getMessage());
	}
	
	public int getFila() {
		return this.fila;
	}
	public int getColumna() {
		
		return this.columna;
	}
}
