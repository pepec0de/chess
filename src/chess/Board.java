package me.pepe.games.chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel {

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

	// Grid
	public static Tile[][] TILES = new Tile[8][8];
	// Posicion actual que se ha pulsado
	private int locationX, locationY;
	// Posicion a la que se va a mover
	private int newlocationX, newlocationY;
	// Si ya se ha pulsado una figura para mover
	private boolean pressed;
	// turnos
	private boolean playerW, playerB;

	// To check that game runs
	private boolean isGameOver;

	public static TilesHandler tilesHandler;
	
	public Board() {
		init();
		initBoard();
	}

	private void init() {
		tilesHandler = new TilesHandler();
	}

	private void initBoard() {
		setLayout(new GridLayout(8, 8));
		// Init gird
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                TILES[x][y] = new Tile();
                TILES[x][y].setToolTipText("X: " + x + " Y: " + y);
                //new TilesTips(x, y);
                TILES[x][y].addActionListener(tileListener);
                add(TILES[x][y]);
            }
        }
        tilesHandler.repaint();
	}
	
	ActionListener tileListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private void startGame() {
        this.isGameOver = false;
        init();
        
        // Clean grid from icons
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                TILES[x][y].setIcon(null);
            }
        }
        // BLACK
        
        // Set start position
        TILES[0][0].setIcon(ICONbRook);
        TILES[0][1].setIcon(ICONbKnight);
        TILES[0][2].setIcon(ICONbBishop);
        TILES[0][3].setIcon(ICONbQueen);
        TILES[0][4].setIcon(ICONbKing);
        TILES[0][5].setIcon(ICONbBishop);
        TILES[0][6].setIcon(ICONbKnight);
        TILES[0][7].setIcon(ICONbRook);
        for (int i = 0; i < 8; i++) {
            TILES[1][i].setIcon(ICONbPawn);
        }

        // WHITE
        
        // Set start position
        TILES[7][0].setIcon(ICONwRook);
        TILES[7][1].setIcon(ICONwKnight);
        TILES[7][2].setIcon(ICONwBishop);
        TILES[7][3].setIcon(ICONwQueen);
        TILES[7][4].setIcon(ICONwKing);
        TILES[7][5].setIcon(ICONwBishop);
        TILES[7][6].setIcon(ICONwKnight);
        TILES[7][7].setIcon(ICONwRook);
        for (int i = 0; i < 8; i++) {
            TILES[6][i].setIcon(ICONwPawn);
        }
        
        tilesHandler.repaint();
        tilesHandler.setAllTiles(false);
        tilesHandler.setWhite(true);
	}
}
