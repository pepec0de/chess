package me.pepe.games.chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel {

	// Rejilla
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
        tilesHandler.setAllTiles(false);
	}
	
	ActionListener tileListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(null, "que pasa bro");
		}
	};
	
	public void startGame() {
        this.isGameOver = false;
        init();
        
        // Clean grid from icons
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                TILES[x][y].setPiece("null");
            }
        }
        // BLACK
        
        // Set start position
        TILES[0][0].setPiece("bRook");
        TILES[0][1].setPiece("bKnight");
        TILES[0][2].setPiece("bBishop");
        TILES[0][3].setPiece("bQueen");
		TILES[0][4].setPiece("bKing");
		TILES[0][5].setPiece("bBishop");
		TILES[0][6].setPiece("bKnight");
		TILES[0][7].setPiece("bRook");
        for (int i = 0; i < 8; i++) {
            TILES[1][i].setPiece("bPawn");
        }

        // WHITE
        
        // Set start position
        TILES[7][0].setPiece("wRook");
        TILES[7][1].setPiece("wKnight");
        TILES[7][2].setPiece("wBishop");
        TILES[7][3].setPiece("wQueen");
		TILES[7][4].setPiece("wKing");
		TILES[7][5].setPiece("wBishop");
		TILES[7][6].setPiece("wKnight");
		TILES[7][7].setPiece("wRook");
        for (int i = 0; i < 8; i++) {
            TILES[6][i].setPiece("wPawn");
        }
        
        tilesHandler.repaint();
        tilesHandler.setAllTiles(false);
        tilesHandler.setWhiteTeam(true);
	}
}
