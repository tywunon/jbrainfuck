package jBrainfuck.interpreter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Stack;

public class Interpreter {
	private int pointer;
	private Stack<Integer> loops;
	private final int memorySize; 
	private byte[] memory;
	private final InputStream inputStream;
	private final OutputStream outputStream;
	
	public Interpreter(InputStream inputStream, OutputStream outputStream, int memorySize) {
		this.memorySize = memorySize;
		this.inputStream = inputStream;
		this.outputStream = outputStream;
	}
	
	public void runCode(String code) throws InterpreterException, IOException{
		reset();
		processCode(code.toCharArray());
	}
	
	private void reset() {
		pointer = 0;
		loops = new Stack<>();
		memory = new byte[memorySize];
	}
	
	public void dumpMemory() throws IOException {
		dumpMemory(this.outputStream);
	}
	
	public void dumpMemory(OutputStream outStream) throws IOException{
		outStream.write(memory);
	}
	
	private byte loadMemory() {
		return memory[normalizePointer(pointer)];
	}
	
	private void writeMemory(byte value) {
		memory[normalizePointer(pointer)] = value;
	}
	
	private void incremetMemory() {
		memory[normalizePointer(pointer)]++;
	}
	
	private void decrementMemory() {
		memory[normalizePointer(pointer)]--;
	}
	
	private int normalizePointer(int pointer) {
		return pointer % memorySize;
	}
	
	private void processCode(char[] code) throws InterpreterException, IOException{
		for(int codePosition=0; codePosition<code.length; codePosition++) {
			switch (code[codePosition]) {
			case '>':
				pointer++;
				break;
			case '<':
				pointer--;
				break;
			case '+':
				incremetMemory();
				break;
			case '-':
				decrementMemory();
				break;
			case '.':
				outputStream.write((0x000000FF & loadMemory()));
				break;
			case ',':
				writeMemory((byte)(inputStream.read() & 0x000000FF));
				break;
			case '[' :
				loops.push(codePosition);
				break;
			case ']' :
				if (loops.empty()) {
					throw new InterpreterException(String.format("Syntax Error : ] without [ found @ %d", codePosition));
				}
				if(loadMemory() == 0){
					loops.pop();
				}
				else {
					codePosition = loops.peek();
				}
				break;

			default:
				throw new InterpreterException(String.format("Syntax Error : Symbol unknown @ %d", codePosition));
			}
		}
	}
}
