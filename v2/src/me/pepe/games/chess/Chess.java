package me.pepe.games.chess;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Chess extends JFrame {

	// Resources
	public static final ImageIcon ICONbKing = new ImageIcon(Assets.chssICON_bKING);
	public static final ImageIcon ICONbQueen = new ImageIcon(Assets.chssICON_bQUEEN);
	public static final ImageIcon ICONbBishop = new ImageIcon(Assets.chssICON_bBISHOP);
	public static final ImageIcon ICONbKnight = new ImageIcon(Assets.chssICON_bKNIGHT);
	public static final ImageIcon ICONbRook = new ImageIcon(Assets.chssICON_bROOK);
	public static final ImageIcon ICONbPawn = new ImageIcon(Assets.chssICON_bPAWN);
	public static final ImageIcon ICONwKing = new ImageIcon(Assets.chssICON_wKING);
	public static final ImageIcon ICONwQueen = new ImageIcon(Assets.chssICON_wQUEEN);
	public static final ImageIcon ICONwBishop = new ImageIcon(Assets.chssICON_wBISHOP);
	public static final ImageIcon ICONwKnight = new ImageIcon(Assets.chssICON_wKNIGHT);
	public static final ImageIcon ICONwRook = new ImageIcon(Assets.chssICON_wROOK);
	public static final ImageIcon ICONwPawn = new ImageIcon(Assets.chssICON_wPAWN);
	
	public static HashMap<String, ImageIcon> ICONS = new HashMap<String, ImageIcon>();

	// JFrame comps
	private Board board;
	private JMenuBar bar;
	private JMenu menuGame;
	private JMenuItem itemStart;

	public Chess() {
		init();
		initUI();
	}

	private void init() {
		ICONS.put("bKing", ICONbKing);
		ICONS.put("wKing", ICONwKing);
		ICONS.put("bQueen", ICONbQueen);
		ICONS.put("wQueen", ICONwQueen);
		ICONS.put("bBishop", ICONbBishop);
		ICONS.put("wBishop", ICONwBishop);
		ICONS.put("bKnight", ICONbKnight);
		ICONS.put("wKnight", ICONwKnight);
		ICONS.put("bRook", ICONbRook);
		ICONS.put("wRook", ICONwRook);
		ICONS.put("bPawn", ICONbPawn);
		ICONS.put("wPawn", ICONwPawn);
		board = new Board();
	}

	private void initUI() {
		initBarmenu();
		setTitle("Chess");
		setSize(800, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(board);
	}

	private void initBarmenu() {
		bar = new JMenuBar();
		menuGame = new JMenu("Game");
		itemStart = new JMenuItem("Start");

		menuGame.add(itemStart);

		bar.add(menuGame);
		setJMenuBar(bar);

		itemStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				board.startGame();
			}
		});
	}

	public static void main(String[] args) {
		new Chess().setVisible(true);
	}

}
