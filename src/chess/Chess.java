package me.pepe.games.chess;

import javax.swing.*;

public class Chess extends JFrame {
	
	private Board board;
	public Chess() {
		init();
		initUI();
	}
	
	private void init() {
		board = new Board();
	}
	
	private void initUI() {
		setTitle("Chess");
		setSize(800, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(board);
	}
	public static void main(String[] args) {
		new Chess().setVisible(true);
	}

}
