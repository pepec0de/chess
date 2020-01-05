package me.pepe.games.chess;

import java.awt.*;

public class TilesHandler {

	public void repaint() {}
	
	// Determinar si una ficha es blanca
    public boolean isWhite(int posx, int posy) {
        return Chess.TILES[posx][posy].getIcon() == Chess.ICONwBishop
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONwKing
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONwKnight
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONwPawn
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONwQueen
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONwRook;
    }
    
    // Determinar si una ficha es blanca
    public boolean isBlack(int posx, int posy) {        
        return Chess.TILES[posx][posy].getIcon() == Chess.ICONbBishop
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONbKing
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONbKnight
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONbPawn
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONbQueen
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONbRook;        
    }
    
    public boolean isTile(int posx, int posy) {
        //return isWhite(posx, posy) || isBlack(posx, posy);
        return Chess.TILES[posx][posy].getIcon() != null;
    }
    
    // Funcion para deshabilitar la matriz
    public void setAllFalse() {
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                Board.TILES[x][y].setEnabled(false);
            }
        }
    }
    
    public void setNullTiles(boolean bool) {
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if(!isTile(x, y)) {
                    Board.TILES[x][y].setEnabled(bool);
                }
            }
        }
    }
    
    public void setBlankTiles(boolean bool) {
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if(Board.TILES[x][y].getBackground() == Color.LIGHT_GRAY 
                    || Board.TILES[x][y].getBackground() == Color.WHITE) {
                    if(!isTile(x, y)) {
                        Board.TILES[x][y].setEnabled(bool);
                    }
                }
            }
        }
    }
    
    // Funcion para (des)habilitar todas las figuras que sean White
    public void setWhite(boolean bool) {
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if (isWhite(x, y)) {
                    Board.TILES[x][y].setEnabled(bool);
                }
            }
        }
    }
    
    // Funcion para (des)habilitar todas las figuras que sean Black
    public void setBlack(boolean bool) {
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if (isBlack(x, y)) Board.TILES[x][y].setEnabled(bool);                
            }
        }
    }
}
