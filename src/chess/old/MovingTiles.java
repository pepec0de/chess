package chess;

/**
 *
 * @author pepe
 */
public class MovingTiles {    
    
    private Chess chessInst;
    
    public MovingTiles() {
        chessInst = new Chess();
    }            
    
    // FUNCION EN DESUSO SE HA REEMPLAZADO POR LA QUE ESTA EN Chess.java
    public boolean[][] get(String tile, int posx, int posy) {
        boolean[][] bool = new boolean[8][8];
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                bool[x][y] = false;
            }
        }
        
        switch(tile) {
            case "wKing":
            // WHITE
                // ABAJO
                if(posx != 7) {
                    if(Chess.tilesHandler.canKingMoveTo("w", posx + 1, posy) == true) {
                        if(!Chess.tilesHandler.isTile(posx + 1,posy)) {
                            Chess.tilesHandler.update(bool, posx + 1, posy);
                        }                   
                    }
                }
                
                
                // ARRIBA
                if(posx != 0) {
                    if(Chess.tilesHandler.canKingMoveTo("w", posx - 1, posy) == true) {
                        if(!Chess.tilesHandler.isTile(posx - 1,posy)) {
                            Chess.tilesHandler.update(bool, posx - 1, posy);
                        }                                  
                    }
                }
                
                // DERECHA
                if(posy != 7) {
                    if(Chess.tilesHandler.canKingMoveTo("w", posx, posy + 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx,posy + 1)) {                    
                            Chess.tilesHandler.update(bool, posx, posy + 1);
                        }
                    }                    
                }
                
                // IZQUIERDA
                if(posy != 0) {
                    if(Chess.tilesHandler.canKingMoveTo("w", posx, posy - 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx,posy - 1)) {
                            Chess.tilesHandler.update(bool, posx, posy - 1);          
                        }                   
                    }
                }
                
                // ABAJO DERECHA
                if(posx != 7 && posy != 7) {
                    if(Chess.tilesHandler.canKingMoveTo("w", posx + 1, posy + 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx + 1,posy + 1)) {
                           Chess.tilesHandler.update(bool, posx + 1, posy + 1);
                        }                  
                    }
                }
                
                // ABAJO IZQUIERDA
                if(posx != 7 && posy != 0) {
                    if(Chess.tilesHandler.canKingMoveTo("w", posx + 1, posy - 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx + 1,posy - 1)) {
                           Chess.tilesHandler.update(bool, posx + 1, posy - 1);
                        }        
                    }
                }
                
                // ARRIBA DERECHA
                if(posx != 0 && posy != 7) {
                    if(Chess.tilesHandler.canKingMoveTo("w", posx - 1, posy + 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx - 1,posy + 1)) {
                           Chess.tilesHandler.update(bool, posx - 1, posy + 1);
                        }
                    }
                }
                
                // ARRIBA IZQUIERDA
                if(posx != 0 && posy != 0) {
                    if(Chess.tilesHandler.canKingMoveTo("w", posx - 1, posy - 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx - 1,posy - 1)) {
                           Chess.tilesHandler.update(bool, posx - 1, posy - 1);
                        }                
                    }
                }
                
                if(chessInst.getCanKingCastle("w") == true) {
                    System.out.println("Se puede marcar enroque getCanKingCastle(\"w\"): "+chessInst.getCanKingCastle("w"));
                    // ENROQUE LARGO      
                    if(chessInst.getCanDoCastling("w", "long") == true) {
                        if (posx == 7) {                    
                            if (!(Chess.tilesHandler.isTile(7, 1) && Chess.tilesHandler.isTile(7, 2) && Chess.tilesHandler.isTile(7, 3))) {
                                Chess.tilesHandler.update(bool, 7, 0);
                            }
                        }
                    }
                    // ENROQUE CORTO
                    if(chessInst.getCanDoCastling("w", "short") == true) {
                        if (posx == 7) {
                            if (!(Chess.tilesHandler.isTile(7, 5) && Chess.tilesHandler.isTile(7, 6))) {
                                Chess.tilesHandler.update(bool, 7, 7);
                            }
                        }
                    }
                }
            break;

            case "bKing":
            // BLACK
                // ABAJO
                if(posx != 7) {
                    //if(Chess.tilesHandler.canKingMoveTo("b", posx + 1, posy) == true) {
                        if(!Chess.tilesHandler.isTile(posx + 1,posy)) {
                            Chess.tilesHandler.update(bool, posx + 1, posy);
                        }                   
                    //}
                }
                
                
                // ARRIBA
                if(posx != 0) {
                    //if(Chess.tilesHandler.canKingMoveTo("b", posx - 1, posy) == true) {
                        if(!Chess.tilesHandler.isTile(posx - 1,posy)) {
                            Chess.tilesHandler.update(bool, posx - 1, posy);
                        }                                  
                    //}
                }
                
                // DERECHA
                if(posy != 7) {
                    //if(Chess.tilesHandler.canKingMoveTo("b", posx, posy + 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx,posy + 1)) {                    
                            Chess.tilesHandler.update(bool, posx, posy + 1);
                        }
                    //}                    
                }
                
                // IZQUIERDA
                if(posy != 0) {
                    //if(Chess.tilesHandler.canKingMoveTo("b", posx, posy - 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx,posy - 1)) {
                            Chess.tilesHandler.update(bool, posx, posy - 1);          
                        }                   
                    //}
                }
                
                // ABAJO DERECHA
                if(posx != 7 && posy != 7) {
                    //if(Chess.tilesHandler.canKingMoveTo("b", posx + 1, posy + 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx + 1,posy + 1)) {
                           Chess.tilesHandler.update(bool, posx + 1, posy + 1);
                        }                  
                    //}
                }
                
                // ABAJO IZQUIERDA
                if(posx != 7 && posy != 0) {
                    //if(Chess.tilesHandler.canKingMoveTo("b", posx + 1, posy - 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx + 1,posy - 1)) {
                           Chess.tilesHandler.update(bool, posx + 1, posy - 1);
                        }        
                    //}
                }
                
                // ARRIBA DERECHA
                if(posx != 0 && posy != 7) {
                    //if(Chess.tilesHandler.canKingMoveTo("b", posx - 1, posy + 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx - 1,posy + 1)) {
                           Chess.tilesHandler.update(bool, posx - 1, posy + 1);
                        }
                    //}
                }
                
                // ARRIBA IZQUIERDA
                if(posx != 0 && posy != 0) {
                    //if(Chess.tilesHandler.canKingMoveTo("b", posx - 1, posy - 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx - 1,posy - 1)) {
                           Chess.tilesHandler.update(bool, posx - 1, posy - 1);
                        }                
                    //}
                }
                
                // ENROQUES
                if(chessInst.getCanKingCastle("b") == true) {
                    // ENROQUE LARGO
                    if(chessInst.getCanDoCastling("b", "long") == true) {                        
                        if (posx == 0) {                    
                            if (!(Chess.tilesHandler.isTile(0, 1) && Chess.tilesHandler.isTile(0, 2) && Chess.tilesHandler.isTile(0, 3))) {
                                Chess.tilesHandler.update(bool, 0, 0);
                            }
                        }
                    }

                    // ENROQUE CORTO
                    if(chessInst.getCanDoCastling("b", "short") == true) {
                        if (posx == 0) {
                            if (!(Chess.tilesHandler.isTile(0, 5) && Chess.tilesHandler.isTile(0, 6))) {
                                Chess.tilesHandler.update(bool, 0, 7);
                            }
                        }
                    }
                }
            break;
            
            case "wPawn":
                if(posx == 6) { // CONDICIONAL PRIMERA JUGADA
                    // ARRIBA 1
                    if(!Chess.tilesHandler.isTile(posx - 1,posy)) {
                        Chess.tilesHandler.update(bool, posx - 1, posy);
                        // ARRIBA 2
                        if (!Chess.tilesHandler.isTile(posx - 2,posy)) {
                            Chess.tilesHandler.update(bool, posx - 2, posy);
                        }
                    }                   
                }

                if(posx < 6) { // CONDICIONAL SIGUIENTES JUGADAS
                    // ARRIBA
                    if(!Chess.tilesHandler.isTile(posx - 1,posy)) {
                        Chess.tilesHandler.update(bool, posx - 1, posy);
                    }
                }
            break; 
            
            case "bPawn":
                if(posx == 1) { // CONDICIONAL PRIMERA JUGADA
                    // ARRIBA 1
                    if(!Chess.tilesHandler.isTile(posx + 1, posy)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy);
                        // ARRIBA 2
                        if(!Chess.tilesHandler.isTile(posx + 2, posy)) {
                            Chess.tilesHandler.update(bool, posx + 2, posy);
                        }
                    }                    
                }

                if(posx > 1) {
                    if(!Chess.tilesHandler.isTile(posx + 1, posy)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy);
                    }
                }
            break;
            
            case "wQueen":
            case "bQueen":
                // BISHOP FUNC
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(!Chess.tilesHandler.isTile(posx + i,posy + i)) {
                            Chess.tilesHandler.update(bool, posx + i, posy + i);
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(!Chess.tilesHandler.isTile(posx - i,posy - i)) {
                            Chess.tilesHandler.update(bool, posx - i, posy - i);
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(!Chess.tilesHandler.isTile(posx + i,posy - i)) {
                            Chess.tilesHandler.update(bool, posx + i, posy - i);
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(!Chess.tilesHandler.isTile(posx - i,posy + i)) {
                            Chess.tilesHandler.update(bool, posx - i, posy + i);
                        } else {
                            break;
                        }
                    }                    
                }
                
                // ROOK FUNC
                for(int i = 1; i < 8; i++) {
                    if( ( (posx + i) >= 0 && (posx + i) <= 7 ) ) {
                        if(!Chess.tilesHandler.isTile(posx + i,posy)) {
                            Chess.tilesHandler.update(bool, posx + i, posy);
                        } else {
                            break;
                        }
                    }
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7) ) {
                        if(!Chess.tilesHandler.isTile(posx - i,posy)) {
                            Chess.tilesHandler.update(bool, posx - i, posy);
                        } else {
                            break;
                        }
                    }
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ( (posy + i) >= 0 && (posy + i) <= 7 ) ) {
                        if(!Chess.tilesHandler.isTile(posx,posy + i)) {
                            Chess.tilesHandler.update(bool, posx, posy + i);
                        } else {
                            break;
                        }
                    }
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(!Chess.tilesHandler.isTile(posx,posy - i)) {
                            Chess.tilesHandler.update(bool, posx, posy - i);
                        } else {
                            break;
                        }
                    }
                }
            break;

            case "wBishop":
            case "bBishop":
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(!Chess.tilesHandler.isTile(posx + i,posy + i)) {
                            Chess.tilesHandler.update(bool, posx + i, posy + i);
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(!Chess.tilesHandler.isTile(posx - i,posy - i)) {
                            Chess.tilesHandler.update(bool, posx - i, posy - i);
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(!Chess.tilesHandler.isTile(posx + i,posy - i)) {
                            Chess.tilesHandler.update(bool, posx + i, posy - i);
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(!Chess.tilesHandler.isTile(posx - i,posy + i)) {
                            Chess.tilesHandler.update(bool, posx - i, posy + i);
                        } else {
                            break;
                        }
                    }                    
                }
            break;

            case "wKnight":
            case "bKnight":
                // ARRIBA DERECHA 2 nivel
                if( ((posx - 2) >= 0 && (posx - 2) <= 7)
                        && ((posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx - 2,posy + 1)) {
                        Chess.tilesHandler.update(bool, posx - 2, posy + 1);
                    }
                }
                
                // ARRIBA IZQUIERDA 2 nivel
                if( ((posx - 2) >= 0 && (posx - 2) <= 7)
                        && ((posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx - 2,posy - 1)) {
                        Chess.tilesHandler.update(bool, posx - 2, posy - 1);
                    }
                }
                
                // ABAJO DERECHA 2 nivel
                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ((posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx + 2,posy + 1)) {
                        Chess.tilesHandler.update(bool, posx + 2, posy + 1);
                    }
                }
                
                // ABAJO IZQUIERDA 2 nivel
                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ((posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx + 2,posy - 1)) {
                        Chess.tilesHandler.update(bool, posx + 2, posy - 1);
                    }
                }

                // ABAJO DERECHA 1 nivel
                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ((posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx + 1,posy + 2)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy + 2);
                    }
                }

                // ABAJO IZQUIERDA 1 nivel
                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ((posy - 2) >= 0 && (posy - 2) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx + 1,posy - 2)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy - 2);
                    }
                }
                
                // ARRIBA DERECHA 1 nivel
                if( ((posx - 1) >= 0 && (posx - 1) <= 7)
                        && ((posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx - 1,posy + 2)) {
                        Chess.tilesHandler.update(bool, posx - 1, posy + 2);
                    }
                }
                
                // ARRIBA IZQUIERDA 1 nivel
                if( ((posx - 1) >= 0 && (posx - 1) <= 7)
                        && ((posy - 2) >= 0 && (posy - 2) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx - 1,posy - 2)) {                        
                        Chess.tilesHandler.update(bool, posx - 1, posy - 2);
                    }
                }
            break;

            case "wRook":      
            case "bRook":
                // ABAJO
                for(int i = 1; i <= 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7) ) {
                        if(!Chess.tilesHandler.isTile(posx + i,posy)) {
                            Chess.tilesHandler.update(bool, posx + i, posy);
                        } else {
                            break;
                        }
                    }
                }
                
                // ARRIBA
                for(int i = 1; i <= 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7) ) {
                        if(!Chess.tilesHandler.isTile(posx - i,posy)) {
                            Chess.tilesHandler.update(bool, posx - i, posy);
                        } else {
                            break;
                        }
                    }
                }
                
                // DERECHA
                for(int i = 1; i <= 8; i++) {
                    if( ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(!Chess.tilesHandler.isTile(posx,posy + i)) {
                            Chess.tilesHandler.update(bool, posx, posy + i);
                        } else {
                            break;
                        }
                    }
                }
                
                // IZQUIERDA
                for(int i = 1; i <= 8; i++) {
                    if( ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(!Chess.tilesHandler.isTile(posx,posy - i)) {
                            Chess.tilesHandler.update(bool, posx, posy - i);
                        } else {
                            break;
                        }
                    }
                }
            break;                          
        }
        Chess.tilesHandler.setBlankTiles(false);
        return bool;
    }
}
