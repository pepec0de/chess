package chess;

/**
 *
 * @author pepe
 */
public class KillingTiles {
    
    public KillingTiles() {}
    
    public boolean[][] get(String tile, int posx, int posy) {
        boolean[][] bool = new boolean[8][8];
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                bool[x][y] = false;
            }
        }
        
        switch(tile) {
        // WHITE
            case "wKing":
                // ABAJO
                if(posx != 7) {
                    if (Chess.tilesHandler.isBlack(posx + 1, posy)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy);
                    }
                }
                
                // ARRIBA
                if(posx != 0) {               
                    if (Chess.tilesHandler.isBlack(posx - 1, posy)) {
                        Chess.tilesHandler.update(bool, posx - 1, posy);
                    }
                }
                
                // DERECHA
                if(posy != 7) {                
                    if (Chess.tilesHandler.isBlack(posx, posy + 1)) {
                        Chess.tilesHandler.update(bool, posx, posy + 1);
                    }
                }
                
                // IZQUIERDA
                if(posy != 0) {                
                    if (Chess.tilesHandler.isBlack(posx, posy - 1)) {
                        Chess.tilesHandler.update(bool, posx, posy - 1);
                    }
                }
                
                // ABAJO DERECHA
                if(posx != 7 && posy != 7) {                
                    if (Chess.tilesHandler.isBlack(posx + 1, posy + 1)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy + 1);
                    }
                }
                
                // ABAJO IZQUIERDA
                if(posx != 7 && posy != 0) {                
                    if (Chess.tilesHandler.isBlack(posx + 1, posy - 1)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy - 1);
                    }
                }
                
                // ARRIBA DERECHA
                if(posx != 0 && posy != 7) {                
                    if (Chess.tilesHandler.isBlack(posx - 1, posy + 1)) {
                        Chess.tilesHandler.update(bool, posx - 1, posy + 1);
                    }
                }
                
                // ARRIBA IZQUIERDA
                if(posx != 0 && posy != 0) {                
                    if (Chess.tilesHandler.isBlack(posx - 1, posy - 1)) {
                        Chess.tilesHandler.update(bool, posx - 1, posy - 1);
                    }
                }
            break;

            case "wQueen":
                
            // BISHOP FUNC
                // ARRIBA DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx + i, posy + i)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx + i, posy + i)) {
                            Chess.tilesHandler.update(bool, posx + i, posy + i);
                            break;
                        }
                    }                    
                }
                
                // ABAJO IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx - i, posy - i)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx - i, posy - i)) {
                            Chess.tilesHandler.update(bool, posx - i, posy - i);
                            break;
                        }
                    }                    
                }
                
                // ARRIBA IZQUIERDA                
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx + i, posy - i)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx + i, posy - i)) {
                            Chess.tilesHandler.update(bool, posx + i, posy - i);
                            break;
                        }
                    }                    
                }
                
                // ABAJO DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx - i, posy + i)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx - i, posy + i)) {
                            Chess.tilesHandler.update(bool, posx - i, posy + i);
                            break;
                        }
                    }                    
                }
                
            // ROOK FUNC
                // ABAJO
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx + i, posy)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx + i, posy)) {
                            Chess.tilesHandler.update(bool, posx + i, posy);
                            break;
                        }
                    }
                }
                
                // ARRIBA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx - i, posy)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx - i, posy)) {
                            Chess.tilesHandler.update(bool, posx - i, posy);
                            break;
                        }
                    }
                }
                
                // DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx, posy + i)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx, posy + i)) {
                            Chess.tilesHandler.update(bool, posx, posy + i);
                            break;
                        }
                    }
                }
                
                // IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx, posy - i)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx, posy - i)) {
                            Chess.tilesHandler.update(bool, posx, posy - i);
                            break;
                        }
                    }
                }
            break;

            case "wBishop":
                // ARRIBA DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx + i, posy + i)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx + i, posy + i)) {
                            Chess.tilesHandler.update(bool, posx + i, posy + i);
                            break;
                        }
                    }                    
                }
                
                // ABAJO IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx - i, posy - i)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx - i, posy - i)) {
                            Chess.tilesHandler.update(bool, posx - i, posy - i);
                            break;
                        }
                    }                    
                }
                
                // ARRIBA IZQUIERDA                
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx + i, posy - i)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx + i, posy - i)) {
                            Chess.tilesHandler.update(bool, posx + i, posy - i);
                            break;
                        }
                    }                    
                }
                
                // ABAJO DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx - i, posy + i)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx - i, posy + i)) {
                            Chess.tilesHandler.update(bool, posx - i, posy + i);
                            break;
                        }
                    }                    
                }
            break;

            case "wKnight":
                if( ((posx - 2) >= 0 && (posx - 2) <= 7)
                        && ( (posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(Chess.tilesHandler.isBlack(posx - 2, posy + 1)) {
                        Chess.tilesHandler.update(bool, posx - 2, posy + 1);
                    }
                }
                
                if( ((posx - 2) >= 0 && (posx - 2) <= 7)
                        && ( (posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(Chess.tilesHandler.isBlack(posx - 2, posy - 1)) {
                        Chess.tilesHandler.update(bool, posx - 2, posy - 1);
                    }
                }
                
                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ((posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(Chess.tilesHandler.isBlack(posx + 2, posy + 1)) {
                        Chess.tilesHandler.update(bool, posx + 2, posy + 1);
                    }
                }

                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ((posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(Chess.tilesHandler.isBlack(posx + 2, posy - 1)) {
                        Chess.tilesHandler.update(bool, posx + 2, posy - 1);
                    }
                }

                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ((posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(Chess.tilesHandler.isBlack(posx + 1, posy + 2)) {
                        Chess.tilesHandler.update(bool, posx + 2, posy + 2);
                    }
                }

                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ((posy - 2) >= 0 && (posy - 2) <= 7) ) {
                    if(Chess.tilesHandler.isBlack(posx + 1, posy - 2)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy - 2);
                    }
                }
                
                if( ((posx - 1) >= 0 && (posx - 1) <= 7)
                        && ((posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(Chess.tilesHandler.isBlack(posx - 1, posy + 2)) {
                        Chess.tilesHandler.update(bool, posx - 1, posy + 2);
                    }
                }
                
                if( ((posx - 1) >= 0 && (posx - 1) <= 7)
                        && ((posy - 2) >= 0 && (posy - 2) <= 7) ) {
                    if(Chess.tilesHandler.isBlack(posx - 1, posy - 2)) {                        
                        Chess.tilesHandler.update(bool, posx - 1, posy - 2);
                    }
                }
            break;

            case "wRook":
                
                // ABAJO
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx + i, posy)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx + i, posy)) {
                            Chess.tilesHandler.update(bool, posx + i, posy);
                            break;
                        }
                    }
                }
                
                // ARRIBA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx - i, posy)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx - i, posy)) {
                            Chess.tilesHandler.update(bool, posx - i, posy);
                            break;
                        }
                    }
                }
                
                // DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx, posy + i)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx, posy + i)) {
                            Chess.tilesHandler.update(bool, posx, posy + i);
                            break;
                        }
                    }
                }
                
                // IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.tilesHandler.isWhite(posx, posy - i)) {
                            break;
                        } else if(Chess.tilesHandler.isBlack(posx, posy - i)) {
                            Chess.tilesHandler.update(bool, posx, posy - i);
                            break;
                        }
                    }
                }
            break;

            case "wPawn":
                // ATAQUE DIAGONAL SUPERIOR DERECHA
                if(posy < 7) {
                    if (Chess.tilesHandler.isBlack(posx - 1, posy + 1)) {
                        Chess.tilesHandler.update(bool, posx - 1, posy + 1);
                    }
                }
                
                // ATAQUE DIAGONAL SUPERIOR IZQUIERDA
                if(posy >= 1) {
                    if (Chess.tilesHandler.isBlack(posx - 1, posy - 1)) {
                        Chess.tilesHandler.update(bool, posx - 1, posy - 1);
                    }
                }
            break;
                
                
        // BLACK
            case "bKing":
                // ABAJO
                if(posx != 7) {                        
                    if (Chess.tilesHandler.isWhite(posx + 1, posy)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy);
                    }
                }
                
                // ARRIBA
                if(posx != 0) {                        
                    if (Chess.tilesHandler.isWhite(posx - 1, posy)) {
                        Chess.tilesHandler.update(bool, posx - 1, posy);
                    }
                }
                
                // DERECHA
                if(posy != 7) {                        
                    if (Chess.tilesHandler.isWhite(posx, posy + 1)) {
                        Chess.tilesHandler.update(bool, posx, posy + 1);
                    }
                }

                // IZQUIERDA
                if(posy != 0) {                        
                    if (Chess.tilesHandler.isWhite(posx, posy - 1)) {
                        Chess.tilesHandler.update(bool, posx, posy - 1);
                    }
                }

                // ABAJO DERECHA
                if(posx != 7 && posy != 7) {                        
                    if (Chess.tilesHandler.isWhite(posx + 1, posy + 1)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy + 1);
                    }
                }

                // ABAJO IZQUIERDA
                if(posx != 7 && posy != 0) {                        
                    if (Chess.tilesHandler.isWhite(posx + 1, posy - 1)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy - 1);
                    }
                }
                
                // ARRIBA DERECHA
                if(posx != 0 && posy != 7) {                        
                    if (Chess.tilesHandler.isWhite(posx - 1, posy + 1)) {
                        Chess.tilesHandler.update(bool, posx - 1, posy + 1);
                    }
                }

                // ARRIBA IZQUIERDA
                if(posx != 0 && posy != 0) {                        
                    if (Chess.tilesHandler.isWhite(posx - 1, posy - 1)) {
                        Chess.tilesHandler.update(bool, posx - 1, posy - 1);
                    }
                }
            break;

            case "bQueen":
            // BISHOP FUNC
                // ABAJO DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx + i, posy + i)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx + i, posy + i)) {
                            Chess.tilesHandler.update(bool, posx + i, posy + i);
                            break;
                        }
                    }                    
                }
                
                // ARRIBA IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx - i, posy - i)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx - i, posy - i)) {
                            Chess.tilesHandler.update(bool, posx - i, posy - i);
                            break;
                        }
                    }                    
                }
                
                // ABAJO IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx + i, posy - i)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx + i, posy - i)) {
                            Chess.tilesHandler.update(bool, posx + i, posy - i);
                            break;
                        }
                    }                    
                }
                
                // ARRIBA DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx - i, posy + i)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx - i, posy + i)) {
                            Chess.tilesHandler.update(bool, posx - i, posy + i);
                            break;
                        }
                    }                    
                }
                
            // ROOK FUNC
                // ABAJO
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx + i, posy)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx + i, posy)) {
                            Chess.tilesHandler.update(bool, posx + i, posy);
                            break;
                        }
                    }
                }
                
                // ARRIBA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx - i, posy)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx - i, posy)) {
                            Chess.tilesHandler.update(bool, posx - i, posy);
                            break;
                        }
                    }
                }
                
                // DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx, posy + i)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx, posy + i)) {
                            Chess.tilesHandler.update(bool, posx, posy + i);
                            break;
                        }
                    }
                }
                
                // IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx, posy - i)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx, posy - i)) {
                            Chess.tilesHandler.update(bool, posx, posy - i);
                            break;
                        }
                    }
                }
            break;

            case "bBishop":
                // ABAJO DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx + i, posy + i)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx + i, posy + i)) {
                            Chess.tilesHandler.update(bool, posx + i, posy + i);
                            break;
                        }
                    }                    
                }
                
                // ARRIBA IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx - i, posy - i)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx - i, posy - i)) {
                            Chess.tilesHandler.update(bool, posx - i, posy - i);
                            break;
                        }
                    }                    
                }
                
                // ABAJO IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx + i, posy - i)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx + i, posy - i)) {
                            Chess.tilesHandler.update(bool, posx + i, posy - i);
                            break;
                        }
                    }                    
                }
                
                // ARRIBA DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx - i, posy + i)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx - i, posy + i)) {
                            Chess.tilesHandler.update(bool, posx - i, posy + i);
                            break;
                        }
                    }                    
                }
            break;

            case "bKnight":
                // ARRIBA ABAJO DERECHA
                if( ((posx - 2) >= 0 && (posx - 2) <= 7)
                        && ( (posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(Chess.tilesHandler.isWhite(posx - 2, posy + 1)) {
                        Chess.tilesHandler.update(bool, posx - 2, posy + 1);
                    }
                }
                
                if( ((posx - 2) >= 0 && (posx - 2) <= 7)
                        && ( (posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(Chess.tilesHandler.isWhite(posx - 2, posy - 1)) {
                        Chess.tilesHandler.update(bool, posx - 2, posy - 1);
                    }
                }
                
                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ( (posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(Chess.tilesHandler.isWhite(posx + 2, posy + 1)) {
                        Chess.tilesHandler.update(bool, posx + 2, posy + 1);
                    }
                }

                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ( (posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(Chess.tilesHandler.isWhite(posx + 2, posy - 1)) {
                        Chess.tilesHandler.update(bool, posx + 2, posy - 1);
                    }
                }

                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ( (posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(Chess.tilesHandler.isWhite(posx + 1, posy + 2)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy + 2);
                    }
                }

                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ((posy - 2) >= 0 && (posy - 2) <= 7) ) {
                    if(Chess.tilesHandler.isWhite(posx + 1, posy - 2)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy - 2);
                    }
                }
                
                if( ((posx - 1) >= 0 && (posx - 1) <= 7)
                        && ((posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(Chess.tilesHandler.isWhite(posx - 1, posy + 2)) {
                        Chess.tilesHandler.update(bool, posx - 1, posy + 2);
                    }
                }
                
                if( ((posx - 1) >= 0 && (posx - 1) <= 7)
                        && ((posy - 2) >= 0 && (posy - 2) <= 7) ) {
                    if(Chess.tilesHandler.isWhite(posx - 1, posy - 2)) {                        
                        Chess.tilesHandler.update(bool, posx - 1, posy - 2);
                    }
                }
            break;

            case "bRook":
                // ABAJO
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx + i, posy)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx + i, posy)) {
                            Chess.tilesHandler.update(bool, posx + i, posy);
                            break;
                        }
                    }
                }
                
                // ARRIBA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx - i, posy)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx - i, posy)) {
                            Chess.tilesHandler.update(bool, posx - i, posy);
                            break;
                        }
                    }
                }
                
                // DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx, posy + i)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx, posy + i)) {
                            Chess.tilesHandler.update(bool, posx, posy + i);
                            break;
                        }
                    }
                }
                
                // IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.tilesHandler.isBlack(posx, posy - i)) {
                            break;
                        } else if(Chess.tilesHandler.isWhite(posx, posy - i)) {
                            Chess.tilesHandler.update(bool, posx, posy - i);
                            break;
                        }
                    }
                }
            break;

            case "bPawn":
                // ATAQUE DIAGONAL SUPERIOR DERECHA
                if(posy <= 6) {
                    if(Chess.tilesHandler.isWhite(posx + 1, posy + 1)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy + 1);
                    }
                }

                // ATAQUE DIAGONAL SUPERIOR IZQUIERDA
                if(posy >= 1) {
                    if(Chess.tilesHandler.isWhite(posx + 1, posy - 1)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy - 1);
                    }
                }                    
            break;
        }
        Chess.tilesHandler.setBlankTiles(false);
        return bool;
    }    
}