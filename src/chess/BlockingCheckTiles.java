package chess;

/**
 *
 * @author pepe
 */
public class BlockingCheckTiles {
        
    private Chess chessInst = new Chess();
    
    public BlockingCheckTiles() {}
    
    // Funcion para determinar las coordenadas a mover para bloquear un jaque
    public boolean[][] get(String tile, int posx, int posy) {
        int offX = 0, offY = 0;
        boolean[][] bool = new boolean[8][8];
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                bool[x][y] = false;
            }
        }
        
        // Get king in check pos
        //System.out.println("GET KING POS " + tile);
        int killX = Chess.tilesHandler.getTilePos("x", Chess.tilesHandler.getPlayer(tile) + "King");
        int killY = Chess.tilesHandler.getTilePos("y", Chess.tilesHandler.getPlayer(tile) + "King");
        
        // Get which tile is making the check
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                switch(Chess.tilesHandler.getPlayer(tile)) {
                    case "w":
                        if (Chess.tilesHandler.isBlack(x, y)) {
                            if(Chess.killingTiles.get(Chess.tilesHandler.getTile(x, y), x, y)[killX][killY]) {
                                offX = x;
                                offY = y;
                            }
                        }
                        break;
                    
                    case "b":
                        if (Chess.tilesHandler.isWhite(x, y)) {
                            if (Chess.killingTiles.get(Chess.tilesHandler.getTile(x, y), x, y)[killX][killY]) {
                                offX = x;
                                offY = y;
                            }
                        }                    
                        break;                     
                }              
            }
        }
        
        //System.out.println("offX: " + offX + " offY: " + offY);
        //System.out.println("killX: " + killX + " killY: " + killY);
        switch(tile) {
            // TODO: Resolver primero la QUEEN
            case "wKing":                
            case "bKing":
                // In case currentTile can kill it
                if(Chess.killingTiles.get(Chess.tilesHandler.getTile(posx, posy), posx, posy)[offX][offY]) {
                    Chess.tilesHandler.update(bool, offX, offY);
                } else {
                    // Obviamente el rey nunca va a poder bloquear un jaque
                }
                break;
            
            case "wQueen":                
            case "bQueen":            
            case "wBishop":                
            case "bBishop":
            case "wKnight":
            case "bKnight":
            case "wRook":                
            case "bRook":
            case "wPawn":                
            case "bPawn":
                // In case currentTile can kill it
                if(Chess.killingTiles.get(Chess.tilesHandler.getTile(posx, posy), posx, posy)[offX][offY]) {
                    Chess.tilesHandler.update(bool, offX, offY);
                } else {
                    for(int x = 0; x < 8; x++) {
                        for(int y = 0; y < 8; y++) {
                            // Get a position that offTile can move to
                            // Then if true, check if currentTile can move to it to avoid the check
                            //System.out.println(java.util.Arrays.deepToString(getRoute(Chess.tilesHandler.getTile(offX, offY), offX, offY, killX, killY)));
                            if(getRoute(Chess.tilesHandler.getTile(offX, offY), offX, offY, killX, killY)[x][y]) {
                                if(Chess.movingTiles.get(Chess.tilesHandler.getTile(posx, posy), posx, posy)[x][y]) {
                                    Chess.tilesHandler.update(bool, x, y);
                                    break;
                                }
                            }
                        }
                    }
                }
                break;                                                
        }
        switch(Chess.tilesHandler.getPlayer(tile)) {
            case "w":
                Chess.tilesHandler.setWhite(false);                
                break;
                
            case "b":
                Chess.tilesHandler.setBlack(false);
                break;
        }
        Chess.TILES[posx][posy].setEnabled(true);
        Chess.tilesHandler.setBlankTiles(false);
        return bool;
    }


    public boolean[][] getRoute(String tile, int posx, int posy, int killPosx, int killPosy) {
        // TODO: Primero determinar en que direccion estan las coordenadas
        // A partir de ahi, seleccionar que direccion de movimiento es la correcta.
        // Esto solo hacerlo con las TILES que tengan varias opciones de movimiento
        // Lista: Queen, Bishop, Rook, Knight
        // HECHO
        // TODO: Hacer la ruta desde la posicion inicial posx, posy hasta killPosx, killPosy
        // Pero con las posibilidades que tenga cada ficha para hacer los movimientos
        boolean[][] bool = new boolean[8][8];
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                bool[x][y] = false;
            }
        }
        
        // Determinar [dir = kill - off]        
        int dirX = killPosx - posx;
        int dirY = killPosy - posy;
        
        int fDirX = 0;
        int fDirY = 0;        
        
        if(dirX > 0) {
            // ABAJO
            fDirX = 1;
        } else if(dirX < 0) {
            // ARRIBA
            fDirX = -1;
        }
        
        if(dirY > 0) {
            // DERECHA
            fDirY = 1;
        } else if(dirY < 0) {            
            // IZQUIERDA
            fDirY = -1;
        }
        
        //System.out.println("getRoute() " + tile);
        try {
        switch(tile) {
                        
            case "wKing":                
            case "bKing":
                // Paq
                break;
                
            
            case "wQueen":
            case "bQueen":
            switch(fDirX) {
                case -1:
                    switch(fDirY) {
                        case -1:                            
                            // ARRIBA IZQUIERDA (x - I, y - I)
                            // BLOCK TYPE: BISHOP
                            for(int i = 1; i < 8; i++) {
                                // temp coords for refreshing and making the route
                                int xi = posx - i;
                                int yi = posy - i;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }                    
                            }                            
                            break;

                        case 0:                            
                            // ARRIBA (x - I, y)
                            // BLOCK TYPE: ROOK
                            for(int i = 1; i < 8; i++) {
                                // temp coords for refreshing and making the route
                                int xi = posx - i;
                                int yi = posy;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }
                            }                            
                            break;

                        case 1:
                            // ARRIBA DERECHA (x - I, y + I)
                            // BLOCK TYPE: BISHOP
                            for(int i = 1; i < 8; i++) {
                                int xi = posx - i;
                                int yi = posy + i;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }                    
                            }
                            break;
                    }
                    break;
                
                case 0:
                    switch(fDirY) {
                        case -1:
                            // IZQUIERDA (x, y - I)
                            // BLOCK TYPE: ROOK
                            for(int i = 1; i < 8; i++) {
                                int xi = posx;
                                int yi = posy - i;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            break;

                        case 0:
                            throw new Exception("Esto es imposible que suceda");

                        case 1:
                            // DERECHA (x, y + I)
                            // BLOCK TYPE: ROOK
                            for(int i = 1; i < 8; i++) {
                                int xi = posx;
                                int yi = posy + i;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                    }
                    break;

                case 1:
                    switch(fDirY) {
                        case -1:
                            // ABAJO IZQUIERDA (x + I, y - I)
                            // BLOCK TYPE: BISHOP
                            for(int i = 1; i < 8; i++) {
                                int xi = posx + i;
                                int yi = posy - i;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }                    
                            }                            
                            break;

                        case 0:
                            // ABAJO (x + I, y)
                            // BLOCK TYPE: ROOK
                            for(int i = 1; i < 8; i++) {
                                int xi = posx + i;
                                int yi = posy;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }
                            }                          
                            break;

                        case 1:
                            // ABAJO DERECHA (x + I, y + I)
                            // BLOCK TYPE: BISHOP
                            for(int i = 1; i < 8; i++) {
                                int xi = posx + i;
                                int yi = posy + i;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }                    
                            }
                            break;
                    }
                    break;
            }
            break;                                                                                                           
            
            case "wBishop":
            case "bBishop":
            switch(fDirX) {
                case -1:
                    switch(fDirY) {
                        case -1:                            
                            // ARRIBA IZQUIERDA (x - I, y - I)
                            // BLOCK TYPE: BISHOP
                            for(int i = 1; i < 8; i++) {
                                int xi = posx - i;
                                int yi = posy - i;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }                    
                            }                            
                            break;

                        case 0:                            
                            break;

                        case 1:
                            // ARRIBA DERECHA (x - I, y + I)
                            // BLOCK TYPE: BISHOP
                            for(int i = 1; i < 8; i++) {
                                int xi = posx - i;
                                int yi = posy + i;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }                    
                            }
                            break;
                    }
                    break;
                
                case 0:
                    break;

                case 1:
                    switch(fDirY) {
                        case -1:
                            // ABAJO IZQUIERDA (x + I, y - I)
                            // BLOCK TYPE: BISHOP
                            for(int i = 1; i < 8; i++) {
                                int xi = posx + i;
                                int yi = posy - i;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }                    
                            }                            
                            break;

                        case 0:                          
                            break;

                        case 1:
                            // ABAJO DERECHA (x + I, y + I)
                            // BLOCK TYPE: BISHOP
                            for(int i = 1; i < 8; i++) {
                                int xi = posx + i;
                                int yi = posy + i;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }                    
                            }
                            break;
                    }
                    break;
            }
            break;
            
            case "wKnight":
            case "bKnight":
            // TODO: Comprobar y estudiar a parte si se puede usar la misma forma
            switch(fDirX) {
                case -1:
                    switch(fDirY) {
                        case -1:                            
                            // ARRIBA IZQUIERDA (x - I, y - I)
                            // 1 nivel
                            updateGetRoute(Chess.tilesHandler.getPlayer(tile), bool, posx - 1, posy - 2);
                            
                            // 2 nivel
                            updateGetRoute(Chess.tilesHandler.getPlayer(tile), bool, posx - 2, posy - 1);
                            break;

                        case 1:
                            // ARRIBA DERECHA (x - I, y + I)
                            // 1 nivel
                            updateGetRoute(Chess.tilesHandler.getPlayer(tile), bool, posx - 1, posy + 2);
                            
                            // 2 nivel 
                            updateGetRoute(Chess.tilesHandler.getPlayer(tile), bool, posx - 2, posy + 1);
                            break;
                    }
                    break;

                case 1:
                    switch(fDirY) {
                        case -1:
                            // ABAJO IZQUIERDA (x + I, y - I)        
                            // 1 nivel
                            updateGetRoute(Chess.tilesHandler.getPlayer(tile), bool, posx + 1, posy - 2);
                            
                            // 2 nivel
                            updateGetRoute(Chess.tilesHandler.getPlayer(tile), bool, posx + 2, posy - 1);
                            break;

                        case 1:
                            // ABAJO DERECHA (x + I, y + I)
                            // 1 nivel
                            updateGetRoute(Chess.tilesHandler.getPlayer(tile), bool, posx + 1, posy + 2);
                            
                            // 2 nivel
                            updateGetRoute(Chess.tilesHandler.getPlayer(tile), bool, posx + 2, posy + 1);                            
                            break;
                    }
            }                            
            break;
                
                
            case "wRook":
            case "bRook":
            switch(fDirX) {
                case -1:
                    switch(fDirY) {
                        case 0:                            
                            // ARRIBA (x - I, y)
                            // BLOCK TYPE: ROOK
                            for(int i = 1; i < 8; i++) {
                                // temp coords for refreshing and making the route
                                int xi = posx - i;
                                int yi = posy;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }
                            }                           
                            break;

                        case 1:
                            break;
                    }
                    break;
                
                case 0:
                    switch(fDirY) {
                        case -1:
                            // IZQUIERDA (x, y - I)
                            // BLOCK TYPE: ROOK
                            for(int i = 1; i < 8; i++) {
                                // temp coords for refreshing and making the route
                                int xi = posx;
                                int yi = posy - i;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            break;

                        case 0:
                            throw new Exception("Esto es imposible que suceda");

                        case 1:
                            // DERECHA (x, y + I)
                            // BLOCK TYPE: ROOK
                            for(int i = 1; i < 8; i++) {
                                // temp coords for refreshing and making the route
                                int xi = posx;
                                int yi = posy + i;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                    }
                    break;

                case 1:
                    switch(fDirY) {
                        case 0:
                            // ABAJO (x + I, y)
                            // BLOCK TYPE: ROOK
                            for(int i = 1; i < 8; i++) {
                                // temp coords for refreshing and making the route
                                int xi = posx + i;
                                int yi = posy;
                                // Check if IndexOutofBounds doesnt happen
                                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                                        Chess.tilesHandler.update(bool, xi, yi);
                                    } else {
                                        // Check the player
                                        // If the player is white then mark if the tile to move is black
                                        if(Chess.tilesHandler.isWhite(posx, posy)) {
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            }
                                        // If the player is black then mark if the tile to move is white
                                        } else if(Chess.tilesHandler.isBlack(posx, posy)) {                                            
                                            if(Chess.tilesHandler.isWhite(xi, yi)) {
                                                Chess.tilesHandler.update(bool, xi, yi);
                                                break;
                                            } else if(Chess.tilesHandler.isBlack(xi, yi)) {                                                
                                                break;
                                            }
                                        }
                                    }
                                }
                            }                          
                            break;
                    }
                    break;
            }
            break;
                                
            case "wPawn":                                
            case "bPawn":
                return chessInst.movingTilesget(tile, posx, posy);                
        }
        } catch(Exception e) {e.printStackTrace();}
        return bool;
    }
    
    public void updateGetRoute(String player, boolean bool[][], int xi, int yi) {        
        switch(player) {
            case "w":
                // Check if IndexOutofBounds doesnt happen
                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                        Chess.tilesHandler.update(bool, xi, yi);
                    } else if(Chess.tilesHandler.isBlack(xi, yi)) {
                        Chess.tilesHandler.update(bool, xi, yi);
                    }                        
                }
                break;
                
            case "b":
                // Check if IndexOutofBounds doesnt happen
                if(Chess.tilesHandler.isPosInsideBounds(xi, yi)) {
                    if(!Chess.tilesHandler.isTile(xi, yi)) {
                        Chess.tilesHandler.update(bool, xi, yi);
                    } else if(Chess.tilesHandler.isWhite(xi, yi)) {
                        Chess.tilesHandler.update(bool, xi, yi);
                    }                        
                }
                break;
        }
    }
}