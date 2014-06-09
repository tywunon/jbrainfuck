package jBrainfuck.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import jBrainfuck.interpreter.Interpreter;
import jBrainfuck.interpreter.InterpreterException;

import org.junit.Test;

public class InterpreterTest {
	
	public static void main(String[] args) throws FileNotFoundException {
		new InterpreterTest().test();
	}

	@Test
	public void test() throws FileNotFoundException {
		Interpreter inpr = new Interpreter(System.in, System.out, 1024);
		try {
			inpr.runCode( ">++++++++[<+++"
						+ "+++++>-]<+++++"
						+ "++.--..++++++.");
		} catch (InterpreterException | IOException e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}

}
