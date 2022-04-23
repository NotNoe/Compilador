package errors;

import constructorast.Main;

@SuppressWarnings("serial")
public class MainNotFoundException extends Exception {
	public MainNotFoundException() {
		Main.error = true;
	}
	
	public void print() {
		System.err.println("Error: A main function is not provided.");
	}
}
