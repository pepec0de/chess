package chess;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author pepe
 */
public class TilesHandler {    

    public int getTilePos(String axis, String tile) {
        for(int x = 0; x < 8; x++) { // Grid loop
            for(int y = 0; y < 8; y++) {
                // If tile-requested equals tile-loop_coords
                if(tile.equals(getTile(x, y))) {
                    // Return coords
                    switch(axis) {
                        case "x":
                            return x;
                            
                        case "y":
                            return y;                                                  
                    }                    
                }
            }
        }
        return 0;
    }
    
    public String getPlayer(String tile) {
        return String.valueOf(tile.charAt(0));
    }
    
    public boolean isWhite(int posx, int posy) {
        return Chess.TILES[posx][posy].getIcon() == Chess.ICONwBishop
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONwKing
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONwKnight
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONwPawn
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONwQueen
            || Chess.TILES[posx][posy].getIcon() == Chess.ICONwRook;
    }
    
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
    
    public boolean canKingMoveTo(String player, int posx, int posy) {        
        boolean bool = true;
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {                
                switch(player) {
                    case "w":                        
                        if(isBlack(x, y)) {
                            if(getTile(x, y).equals("bPawn")) {
                                // en caso de que sea peon negro
                                /**  
                                 *  Este condicional sirve porque el peon es la unica pieza
                                 *  que no se puede mover a las mismas posiciones a las
                                 *  que ataque en un caso de movimiento normal y por eso se
                                 *  necesita hacer estos 2 condicionales para cada "diagonal" en
                                 *  la que puede haber una posibilidad de ataque
                                **/
                                if(y <= 6) {
                                    if (Chess.movingTiles.get(getTile(x, y), x + 1, y + 1)[posx][posy] == true) {
                                        bool = false;                                    
                                        break;
                                    }
                                }
                                if(y >= 1) {
                                    if (Chess.movingTiles.get(getTile(x, y), x + 1, y - 1)[posx][posy] == true) {
                                        bool = false;                                    
                                        break;
                                    }
                                }
                            } else {                                  
                                if(Chess.movingTiles.get(getTile(x, y), x, y)[posx][posy] == true) {
                                    bool = false;                                    
                                    break;
                                }
                            }
                        }                         
                    break;
                    
                    case "b":
                        if(isWhite(x, y)) {
                            if(getTile(x, y).equals("wPawn")) {
                                // en caso de que sea peon blanco
                                if(y < 7) {
                                    if (Chess.movingTiles.get(getTile(x, y), x - 1, y + 1)[posx][posy] == true) {
                                        bool = false;                                    
                                        break;
                                    }
                                }
                                if(y > 1) {
                                    if (Chess.movingTiles.get(getTile(x, y), x - 1, y - 1)[posx][posy] == true) {
                                        bool = false;                                    
                                        break;
                                    }
                                }
                            } else {                                  
                                if(Chess.movingTiles.get(getTile(x, y), x, y)[posx][posy] == true) {
                                    bool = false;                                    
                                    break;
                                }
                            }
                        }
                    break;
                }
            }
        }
        return bool;
    }
    
    public boolean isPosInsideBounds(int posx, int posy) {
        return(((posx >= 0) && (posx <= 7)) && ((posy >= 0) && (posy <= 7)));
    }
    
    public boolean isCheck(String player) {
        switch(player) {
            case "w":
                for(int x = 0; x < 8; x++) {
                    for(int y = 0; y < 8; y++) {
                        if(isBlack(x, y)) {
                            if(Chess.killingTiles.get(getTile(x, y), x, y)
                                    [getTilePos("x", "wKing")][getTilePos("y","wKing")]) {
                                return true;                                
                            }
                        }
                    }
                }
            break;
            
            case "b":
                for(int x = 0; x < 8; x++) {
                    for(int y = 0; y < 8; y++) {
                        if (isWhite(x, y)) {
                            if (Chess.killingTiles.get(getTile(x, y), x, y)
                                    [getTilePos("x", "bKing")][getTilePos("y", "bKing")]) {
                                return true;                                
                            }
                        }
                    }
                }
            break;
        }
        
        return false;
    }
    
    public void repaint() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {                                        
                Chess.TILES[i][j].setBackground(null);                
            }
        }
        paint();                 
    }
    
    // Pintar la matriz de botones
    public void paint() {
        boolean color = true;
        int cont = 0;
        
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if(color) {
                    if(cont >= 8) {
                        Chess.TILES[x][y].setBackground(Color.LIGHT_GRAY);
                        cont = 0;
                    } else {
                        Chess.TILES[x][y].setBackground(Color.WHITE);
                        color = false;                       
                    }
                } else {
                    if(cont >= 8) {
                        Chess.TILES[x][y].setBackground(Color.WHITE);
                        cont = 0;
                    } else {
                        Chess.TILES[x][y].setBackground(Color.LIGHT_GRAY);
                        color = true;
                    }
                }
                cont++;
            }
        }
    }
    
    // Funcion para deshabilitar la matriz
    public void setAllFalse() {
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                Chess.TILES[x][y].setEnabled(false);
            }
        }
    }
    
    public void setNullTileFalse() {
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if(!isTile(x, y)) {
                    Chess.TILES[x][y].setEnabled(false);
                }
            }
        }
    }
    
    public void setBlankTiles(boolean bool) {
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if(Chess.TILES[x][y].getBackground() == Color.LIGHT_GRAY 
                    || Chess.TILES[x][y].getBackground() == Color.WHITE) {
                    if(!isTile(x, y)) {
                        Chess.TILES[x][y].setEnabled(bool);
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
                    Chess.TILES[x][y].setEnabled(bool);
                }
            }
        }
    }
    
    // Funcion para (des)habilitar todas las figuras que sean Black
    public void setBlack(boolean bool) {
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if (isBlack(x, y)) Chess.TILES[x][y].setEnabled(bool);                
            }
        }
    }
    
    // Funcion para obtener el codigo de la figura dependiendo de la imagen
    public String getTile(int posx, int posy) {
        if(Chess.TILES[posx][posy].getIcon() == null) {
            return null;
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwKing) {
            return "wKing";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwQueen) {
            return "wQueen";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwBishop) {
            return "wBishop";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwKnight) {
            return "wKnight";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwRook) {
            return "wRook";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwPawn) {
            return "wPawn";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbKing) {
            return "bKing";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbQueen) {
            return "bQueen";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbBishop) {
            return "bBishop";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbKnight) {
            return "bKnight";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbRook) {
            return "bRook";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbPawn) {
            return "bPawn";
        }
        return "error";
    }
    
    // Funcion para obtener el codigo de la figura dependiendo de la imagen
    public String getTile(boolean wPlayerDat, int posx, int posy) {
        if(Chess.TILES[posx][posy].getIcon() == null) {
            return null;
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwKing) {
            if(wPlayerDat) {
                return "wKing";
            } else {
                return "king";
            }
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwQueen) {
            if(wPlayerDat) {
                return "wQueen";
            } else {
                
            }
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwBishop) {
            if(wPlayerDat) {
                return "wBishop";
            } else {
                return "bishop";
            }
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwKnight) {
            if(wPlayerDat) {
                return "wKnight";
            } else {
                return "knight";
            }
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwRook) {
            if(wPlayerDat) {
                return "wRook";
            } else {
                return "rook";
            }
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwPawn) {
            if(wPlayerDat) {
                return "wPawn";
            } else {               
                return "pawn";
            }
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbKing) {
            if(wPlayerDat) {
                return "bKing";
            } else {
                return "king";
            }
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbQueen) {
            if(wPlayerDat) {
                return "bQueen";
            } else {
                return "queen";
            }
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbBishop) {
            if(wPlayerDat) {
                return "bBishop";
            } else {
                return "bishop";
            }
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbKnight) {
            if(wPlayerDat) {
                return "bKnight";
            } else {
                return "knight";               
            }
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbRook) {
            if(wPlayerDat) {
                return "bRook";
            } else {
                return "rook";
            }
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbPawn) {
            if(wPlayerDat) {
                return "bPawn";
            } else {
                return "pawn";
            }
        }
        return "error";
    }
    
    public void moveTile(JButton tile, JButton newtile) {
        Icon icon = tile.getIcon();
        tile.setIcon(null);
        newtile.setIcon(icon);
    }
    
    public void update(boolean bool[][], int posx, int posy) {
        Chess.TILES[posx][posy].setEnabled(true);
        bool[posx][posy] = true;
    }
}
