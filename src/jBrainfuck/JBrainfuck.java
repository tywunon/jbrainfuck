package jBrainfuck;

import jBrainfuck.interpreter.Interpreter;
import jBrainfuck.ui.JBrainfuckUI;

public class JBrainfuck {
	
	
	
	public void start() {
		Interpreter interpreter = new Interpreter(System.in, System.out, 1024);
		new JBrainfuckUI(interpreter).setVisible(true);;
		
	}


	public static void main(String[] args) {
		new JBrainfuck().start();
	}
}
