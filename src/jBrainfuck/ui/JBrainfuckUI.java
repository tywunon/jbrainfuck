package jBrainfuck.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import jBrainfuck.interpreter.Interpreter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JBrainfuckUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1124853751710443388L;
	
	private Interpreter interpreter;
	
	public JBrainfuckUI(Interpreter interpreter) {
		this.interpreter = interpreter;
		buildUI();
	}
	
	private void buildUI() {
		this.setTitle("jBrainfuck");
		this.setLayout(new GridLayout(1, 2, 5, 5));
		this.setPreferredSize(new Dimension(500, 500));
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		JTextArea codeArea = new JTextArea();
		leftPanel.add(codeArea);
		add(leftPanel);
		add(rightPanel);
		this.pack();
	}
}
