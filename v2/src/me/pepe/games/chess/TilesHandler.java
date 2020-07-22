package me.pepe.games.chess;

import java.awt.*;

public class TilesHandler {

	// Funcion para pintar el tablero
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
	
	// Funcion para (des)activar la rejilla entera
	public void setAllTiles(boolean bool) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Board.TILES[x][y].setEnabled(bool);
			}
		}
	}

	// Funcion para (des)activar los cuadros donde no haya piezas
	public void setEmptyTiles(boolean bool) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (!Board.TILES[x][y].hasPiece()) {
					Board.TILES[x][y].setEnabled(bool);
				}
			}
		}
	}

	// Funcion para (des)activar las casillas que no tienen figuras cuando hay un movimiento
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
	
	// Funcion para (des)activar todas las figuras de un equipo
	public void setTeam(String team, boolean bool) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if(Board.TILES[x][y].getTeam().equals(team)) Board.TILES[x][y].setEnabled(bool);
			}
		}
	}
	
// FUNCIONES DE COMPROBACION
	
	// Funciones para comprobar si es del equipo blanco o negro
	public boolean isBlack(Tile tile) {
		return tile.getTeam().equals("b");
	}
	
	public boolean isWhite(Tile tile) {
		return tile.getTeam().equals("w");
	}
	
	// Funcion para comprobar si el equipo dado esta en JAQUE
	public boolean isCheck(String team) {
		switch(team) {
		case "w":
			break;
		case "b":
			break;
		}
		return false;
	}
	
	// Funcion para determinar si las coordenadas dadas no salen de los limites
    public boolean isPosInsideBounds(int posx, int posy) {
        return(((posx >= 0) && (posx <= 7)) && ((posy >= 0) && (posy <= 7)));
    }
}
