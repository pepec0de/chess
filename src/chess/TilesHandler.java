package me.pepe.games.chess;

import java.awt.*;

public class TilesHandler {

	public void repaint() {
		boolean color = true;
		int cont = 0;

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (color) {
					if (cont >= 8) {
						Board.TILES[x][y].setBackground(Color.LIGHT_GRAY);
						cont = 0;
					} else {
						Board.TILES[x][y].setBackground(Color.WHITE);
						color = false;
					}
				} else {
					if (cont >= 8) {
						Board.TILES[x][y].setBackground(Color.WHITE);
						cont = 0;
					} else {
						Board.TILES[x][y].setBackground(Color.LIGHT_GRAY);
						color = true;
					}
				}
				cont++;
			}
		}
	}

	// Funcion para deshabilitar la matriz
	public void setAllTiles(boolean bool) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Board.TILES[x][y].setEnabled(bool);
			}
		}
	}

	public void setNullTiles(boolean bool) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (!Board.TILES[x][y].hasPiece()) {
					Board.TILES[x][y].setEnabled(bool);
				}
			}
		}
	}

	// Funcion para (des)habilitar las casillas que no tienen figuras cuando hay un
	// movimiento
	public void setBlankTiles(boolean bool) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (Board.TILES[x][y].getBackground() == Color.LIGHT_GRAY
						|| Board.TILES[x][y].getBackground() == Color.WHITE) {
					if (!Board.TILES[x][y].hasPiece()) {
						Board.TILES[x][y].setEnabled(bool);
					}
				}
			}
		}
	}

	public boolean isBlack(Tile tile) {
		return tile.getTeam().equals("b");
	}
	
	public boolean isWhite(Tile tile) {
		return tile.getTeam().equals("w");
	}
	
	// Funcion para (des)habilitar todas las figuras que sean Black
	public void setBlackTeam(boolean bool) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (Board.TILES[x][y].getTeam().equals("b"))
					Board.TILES[x][y].setEnabled(bool);
			}
		}
	}

	// Funcion para (des)habilitar todas las figuras que sean White
	public void setWhiteTeam(boolean bool) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (Board.TILES[x][y].getTeam().equals("w")) Board.TILES[x][y].setEnabled(bool);
			}
		}
	}
}
