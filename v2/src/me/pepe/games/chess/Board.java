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
	// turno
	private String turn;

	// To check that game runs
	private boolean isGameOver;

	public static TilesHandler tilesHandler;
	public static TileMovement tileMovement;
	
	public Board() {
		init();
		initBoard();
	}

	private void init() {
		tilesHandler = new TilesHandler();
		tileMovement = new TileMovement();
	}

	private void initBoard() {
		setLayout(new GridLayout(8, 8));
		// Init grid
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
	
	public void startGame() {        
        init();
        
        this.isGameOver = false;
        this.pressed = false;
        this.turn = "w";
        
        // Clean grid from icons
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                TILES[x][y].setPiece("");
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
        tilesHandler.setTeam(turn, true);
	}
	
	private void turns() {
		switch (this.turn) {
		case "w":
			this.turn = "b";
			break;
			
		case "b":
			this.turn = "w";
			break;
		}
	}
	
	ActionListener tileListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent evt) {
			if (!isGameOver) {
				if (!pressed) {
					// PRIMERA vez que el jugador presiona un boton de la rejilla.					
					getPos(evt); // Tomamos las coordenadas (locationX, locationY).
					String team = TILES[locationX][locationY].getTeam(); // Determinamos su equipo
					tilesHandler.setAllTiles(false); // Desactivamos el tablero.
					TILES[locationX][locationY].setEnabled(true); // Activamos la pieza que se quiere mover por si se quiere cancelar.
					if(tilesHandler.isCheck(team)) { // Comprobamos si el equipo esta en JAQUE
						setAvoidingCheckTiles();
					} else {
						// Si no esta en JAQUE damos opciones de movimiento normales
						setMovingTiles();
		                setKillingTiles();
					}
					
					pressed = true; // Establecemos que ya se ha producido la PRIMERA PULSACION
				} else {
					// SEGUNDA vez que el jugador presiona un boton de la rejilla.
					getPos(evt); // Tomamos las nuevas coordenadas (newlocationX, newlocationY).
					// Comprobamos si el jugador quiere cancelar el movimiento (pulsando en la misma pieza).
					if (locationX == newlocationX && locationY == newlocationY) {
						// Cancelamos el movimiento
						tilesHandler.repaint(); // Pintamos el ajedrez
						tilesHandler.setAllTiles(false);
						tilesHandler.setTeam(turn, true);
					} else {
						// Hacemos un else ya que si el jugador quiere cancelar no podemos seguir estas sentencias
						
						// Se realiza el movimiento
						TILES[newlocationX][newlocationY].setPiece(TILES[locationX][locationY].getPiece());
						TILES[locationX][locationY].setPiece("");
						
						// Cancelamos y turnamos
						tilesHandler.repaint();
		                tilesHandler.setAllTiles(false);
		                turns();
		                tilesHandler.setTeam(turn, true);						
					}
					pressed = false;
				}
			}
		}
	};
	
	// Utils for tileListener
	private void getPos(ActionEvent evt) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (TILES[x][y] == evt.getSource()) {
                    if (!pressed) {
                        locationX = x;
                        locationY = y;
                    } else {
                        newlocationX = x;
                        newlocationY = y;
                    }
                    break;
                }
            }
        }
    }
	
	private void setMovingTiles() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if(!(x == locationX && y == locationY)) {
					if (tileMovement.getMovingTiles(TILES[locationX][locationY], locationX, locationY)[x][y]) {
						TILES[x][y].setBackground(Color.YELLOW);
						TILES[x][y].setEnabled(true);
					}
				}
			}
		}
	}
	
	private void setKillingTiles() {
		
	}
	
	private void setAvoidingCheckTiles() {
		
	}
	
}
