package me.pepe.games.chess;

public class TileMovement {

	// Creamos una matriz de booleanos que la usaremos como temporal.
	// Para recoger todos los datos en la matriz final.
	private boolean[][] bool = new boolean[8][8];

	public TileMovement() {
	}

	public boolean[][] getMovingTiles(Tile tile, int posx, int posy) {
		// Falseamos la rejilla completa
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				bool[x][y] = false;
			}
		}

		switch (tile.getPiece()) {
		case "wKing":
		case "bKing":

			break;

		case "wQueen":
		case "bQueen":
			// BISHOP FUNC
			// ABAJO DERECHA
			for (int i = 1; i < 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx + i, posy + i)) {
					if (!Board.TILES[posx + i][posy + i].hasPiece()) {
						bool[posx + i][posy + i] = true;
					} else {
						// Si hay una pieza entonces rompemos
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}

			// ARRIBA IZQUIERDA
			for (int i = 1; i < 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx - i, posy - i)) {
					if (!Board.TILES[posx - i][posy - i].hasPiece()) {
						bool[posx - i][posy - i] = true;
					} else {
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}

			// ABAJO IZQUIERDA
			for (int i = 1; i < 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx + i, posy - i)) {
					if (!Board.TILES[posx + i][posy - i].hasPiece()) {
						bool[posx + i][posy - i] = true;
					} else {
						// Si hay una pieza entonces rompemos
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}

			// ARRIBA DERECHA
			for (int i = 1; i < 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx - i, posy + i)) {
					if (!Board.TILES[posx - i][posy + i].hasPiece()) {
						bool[posx - i][posy + i] = true;
					} else {
						// Si hay una pieza entonces rompemos
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}

			// ROOK FUNC
			// ABAJO
			for (int i = 1; i <= 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx + i, posy)) {
					if (!Board.TILES[posx + i][posy].hasPiece()) {
						bool[posx + i][posy] = true;
					} else {
						// Si hay una pieza entonces rompemos
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}

			// ARRIBA
			for (int i = 1; i <= 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx - i, posy)) {
					if (!Board.TILES[posx - i][posy].hasPiece()) {
						bool[posx - i][posy] = true;
					} else {
						// Si hay una pieza entonces rompemos
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}

			// DERECHA
			for (int i = 1; i <= 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx, posy + i)) {
					if (!Board.TILES[posx][posy + i].hasPiece()) {
						bool[posx][posy + i] = true;
					} else {
						// Si hay una pieza entonces rompemos
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}

			// IZQUIERDA
			for (int i = 1; i <= 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx, posy - i)) {
					if (!Board.TILES[posx][posy - i].hasPiece()) {
						bool[posx][posy - i] = true;
					} else {
						// Si hay una pieza entonces rompemos
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}
			break;

		case "wBishop":
		case "bBishop":
			// ABAJO DERECHA
			for (int i = 1; i < 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx + i, posy + i)) {
					if (!Board.TILES[posx + i][posy + i].hasPiece()) {
						bool[posx + i][posy + i] = true;
					} else {
						// Si hay una pieza entonces rompemos
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}

			// ARRIBA IZQUIERDA
			for (int i = 1; i < 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx - i, posy - i)) {
					if (!Board.TILES[posx - i][posy - i].hasPiece()) {
						bool[posx - i][posy - i] = true;
					} else {
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}

			// ABAJO IZQUIERDA
			for (int i = 1; i < 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx + i, posy - i)) {
					if (!Board.TILES[posx + i][posy - i].hasPiece()) {
						bool[posx + i][posy - i] = true;
					} else {
						// Si hay una pieza entonces rompemos
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}

			// ARRIBA DERECHA
			for (int i = 1; i < 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx - i, posy + i)) {
					if (!Board.TILES[posx - i][posy + i].hasPiece()) {
						bool[posx - i][posy + i] = true;
					} else {
						// Si hay una pieza entonces rompemos
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}
			break;

		case "wKnight":
		case "bKnight":
			// ARRIBA DERECHA 2 nivel
			if (Board.tilesHandler.isPosInsideBounds(posx - 2, posy + 1)) {
				if (!Board.TILES[posx - 2][posy + 1].hasPiece()) {
					bool[posx - 2][posy + 1] = true;
				}
			}

			// ARRIBA IZQUIERDA 2 nivel
			if (Board.tilesHandler.isPosInsideBounds(posx - 2, posy - 1)) {
				if (!Board.TILES[posx - 2][posy - 1].hasPiece()) {
					bool[posx - 2][posy - 1] = true;
				}
			}

			// ABAJO DERECHA 2 nivel
			if (Board.tilesHandler.isPosInsideBounds(posx + 2, posy + 1)) {
				if (!Board.TILES[posx + 2][posy + 1].hasPiece()) {
					bool[posx + 2][posy + 1] = true;
				}
			}

			// ABAJO IZQUIERDA 2 nivel
			if (Board.tilesHandler.isPosInsideBounds(posx + 2, posy - 1)) {
				if (!Board.TILES[posx + 2][posy - 1].hasPiece()) {
					bool[posx + 2][posy - 1] = true;
				}
			}

			// ABAJO DERECHA 1 nivel
			if (Board.tilesHandler.isPosInsideBounds(posx + 1, posy + 2)) {
				if (!Board.TILES[posx + 1][posy + 2].hasPiece()) {
					bool[posx + 1][posy + 2] = true;
				}
			}

			// ABAJO IZQUIERDA 1 nivel
			if (Board.tilesHandler.isPosInsideBounds(posx + 1, posy - 2)) {
				if (!Board.TILES[posx + 1][posy - 2].hasPiece()) {
					bool[posx + 1][posy - 2] = true;
				}
			}

			// ARRIBA DERECHA 1 nivel
			if (Board.tilesHandler.isPosInsideBounds(posx - 1, posy + 2)) {
				if (!Board.TILES[posx - 1][posy + 2].hasPiece()) {
					bool[posx - 1][posy + 2] = true;
				}
			}

			// ARRIBA IZQUIERDA 1 nivel
			if (Board.tilesHandler.isPosInsideBounds(posx - 1, posy - 2)) {
				if (!Board.TILES[posx - 1][posy - 2].hasPiece()) {
					bool[posx - 1][posy - 2] = true;
				}
			}
			break;

		case "wRook":
		case "bRook":
			// ABAJO
			for (int i = 1; i <= 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx + i, posy)) {
					if (!Board.TILES[posx + i][posy].hasPiece()) {
						bool[posx + i][posy] = true;
					} else {
						// Si hay una pieza entonces rompemos
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}

			// ARRIBA
			for (int i = 1; i <= 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx - i, posy)) {
					if (!Board.TILES[posx - i][posy].hasPiece()) {
						bool[posx - i][posy] = true;
					} else {
						// Si hay una pieza entonces rompemos
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}

			// DERECHA
			for (int i = 1; i <= 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx, posy + i)) {
					if (!Board.TILES[posx][posy + i].hasPiece()) {
						bool[posx][posy + i] = true;
					} else {
						// Si hay una pieza entonces rompemos
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}

			// IZQUIERDA
			for (int i = 1; i <= 8; i++) {
				if (Board.tilesHandler.isPosInsideBounds(posx, posy - i)) {
					if (!Board.TILES[posx][posy - i].hasPiece()) {
						bool[posx][posy - i] = true;
					} else {
						// Si hay una pieza entonces rompemos
						break;
					}
				} else {
					// Si sale de los limites entonces rompemos
					break;
				}
			}
			break;

		case "wPawn":
			if (posx == 6) { // Comprobamos si es la PRIMERA JUGADA
				// ARRIBA 1
				if (!Board.TILES[posx - 1][posy].hasPiece()) {
					bool[posx - 1][posy] = true;
					// ARRIBA 2
					if (!Board.TILES[posx - 2][posy].hasPiece()) {
						bool[posx - 2][posy] = true;
					}
				}
			}

			// Cuando ya no es la primera jugada
			if (posx < 6 && Board.tilesHandler.isPosInsideBounds(posx - 1, posy)) {
				// ARRIBA
				if (!Board.TILES[posx - 1][posy].hasPiece()) {
					bool[posx - 1][posy] = true;
				}
			}
			break;

		case "bPawn":
			if (posx == 1) { // Comprobamos si es la PRIMERA JUGADA
				// ARRIBA 1
				if (!Board.TILES[posx + 1][posy].hasPiece()) {
					bool[posx + 1][posy] = true;
					// ARRIBA 2
					if (!Board.TILES[posx + 2][posy].hasPiece()) {
						bool[posx + 2][posy] = true;
					}
				}
			}

			// Cuando ya no es la primera jugada
			if (posx > 1 && Board.tilesHandler.isPosInsideBounds(posx + 1, posy)) {
				if (!Board.TILES[posx + 1][posy].hasPiece()) {
					bool[posx + 1][posy] = true;
				}
			}
			break;
		}
		Board.tilesHandler.setBlankTiles(false);
		return bool;
	}
	
	public boolean[][] getKillingTiles(Tile tile, int posx, int posy) {
		// Falseamos la rejilla completa
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				bool[x][y] = false;
			}
		}
		Board.tilesHandler.setBlankTiles(false);
		return bool;
	}
}
