package com.pepe.games.chess;

import com.pepe.games.assets.Assets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author pepe
 */
public class Chess extends JFrame {
    
    public static final JButton[][] TILES = new JButton[8][8];
    
    public static final ImageIcon ICONbKing 
        = new ImageIcon(Assets.chssICON_bKING);
    public static final ImageIcon ICONbQueen 
        = new ImageIcon(Assets.chssICON_bQUEEN);
    public static final ImageIcon ICONbBishop 
        = new ImageIcon(Assets.chssICON_bBISHOP);
    public static final ImageIcon ICONbKnight 
        = new ImageIcon(Assets.chssICON_bKNIGHT);
    public static final ImageIcon ICONbRook 
        = new ImageIcon(Assets.chssICON_bROOK);
    public static final ImageIcon ICONbPawn 
        = new ImageIcon(Assets.chssICON_bPAWN);
    
    public static final ImageIcon ICONwKing 
        = new ImageIcon(Assets.chssICON_wKING);
    public static final ImageIcon ICONwQueen 
        = new ImageIcon(Assets.chssICON_wQUEEN);
    public static final ImageIcon ICONwBishop 
        = new ImageIcon(Assets.chssICON_wBISHOP);
    public static final ImageIcon ICONwKnight 
        = new ImageIcon(Assets.chssICON_wKNIGHT);
    public static final ImageIcon ICONwRook 
        = new ImageIcon(Assets.chssICON_wROOK);
    public static final ImageIcon ICONwPawn 
        = new ImageIcon(Assets.chssICON_wPAWN);
    
    private int locationX, locationY;
    private int newlocationX, newlocationY;
    private boolean pressed;
    private boolean playerW, playerB;
    private boolean[][] movingTiles = new boolean[8][8];
    private boolean[][] killingTiles = new boolean[8][8];
    
    public Chess() {
        initValues();
        initUI();
        initBoard();       
    }
    
    private void initValues() {
        playerW = true;
        playerB = false;
    }
    
    private void initUI() {
        initBar();
        panelGame = new JPanel();

        setTitle("Chess");
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelGame.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Chess.TILES[i][j] = new JButton();
                //Chess.TILES[i][j].setToolTipText("Dim: " + i + " Elemnt: " + j);
                setTileTip(i, j);
                Chess.TILES[i][j].addActionListener(tileListener);
                panelGame.add(Chess.TILES[i][j]);
            }
        }
        paint();
        add(panelGame);
    }
    
    private void initBar() {
        bar = new JMenuBar();
        menuGame = new JMenu("Game");
        itemStart = new JMenuItem("Start");

        menuGame.add(itemStart);

        bar.add(menuGame);
        setJMenuBar(bar);

        itemStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                setWhiteTrue();
            }
        });
    }
    
    ActionListener tileListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if(!pressed) {
                getPos(evt);
                setAllNull();
                Chess.TILES[locationX][locationY].setEnabled(true);
                setMovingTilesFalse();
                setKillingTilesFalse();
                getMovingTiles(getTile(locationX, locationY), locationX, locationY);
                setMovingTiles();
                getKillingTiles(getTile(locationX, locationY), locationX, locationY);
                setKillingTiles();
                pressed = true;
            } else {
                getPos(evt);
                if (locationX == newlocationX && locationY == newlocationY) {
                    // CANCEL MOVING TILE
                    clean();
                    setNullTileFalse();
                    if(playerW) {
                        setWhiteTrue();
                    } else if(playerB) {
                        setBlackTrue();
                    }
                    pressed = false;
                } else {
                    moveTile(Chess.TILES[locationX][locationY], Chess.TILES[newlocationX][newlocationY]);
                    clean();
                    setNullTileFalse();
                    setAllNull();
                    turns();
                    pressed = false;
                }
            }
        }
    };
    
    private void initBoard() {
        setStartPos();
        setAllNull();
    }
    
    private void setTileTip(int dim, int elemnt) {
        switch(dim) {
            case 0:
                switch(elemnt) {
                    case 0:
                        Chess.TILES[dim][elemnt].setToolTipText("1, A");
                        break;
                    case 1:
                        Chess.TILES[dim][elemnt].setToolTipText("1, B");
                        break;
                    case 2:
                        Chess.TILES[dim][elemnt].setToolTipText("1, C");
                        break;
                    case 3:
                        Chess.TILES[dim][elemnt].setToolTipText("1, D");
                        break;
                    case 4:
                        Chess.TILES[dim][elemnt].setToolTipText("1, E");
                        break;
                    case 5:
                        Chess.TILES[dim][elemnt].setToolTipText("1, F");
                        break;
                    case 6:
                        Chess.TILES[dim][elemnt].setToolTipText("1, G");
                        break;
                    case 7:
                        Chess.TILES[dim][elemnt].setToolTipText("1, H");
                        break;                            
                }
                break;
            case 1:
                switch(elemnt) {
                    case 0:
                        Chess.TILES[dim][elemnt].setToolTipText("2, A");
                        break;
                    case 1:
                        Chess.TILES[dim][elemnt].setToolTipText("2, B");
                        break;
                    case 2:
                        Chess.TILES[dim][elemnt].setToolTipText("2, C");
                        break;
                    case 3:
                        Chess.TILES[dim][elemnt].setToolTipText("2, D");
                        break;
                    case 4:
                        Chess.TILES[dim][elemnt].setToolTipText("2, E");
                        break;
                    case 5:
                        Chess.TILES[dim][elemnt].setToolTipText("2, F");
                        break;
                    case 6:
                        Chess.TILES[dim][elemnt].setToolTipText("2, G");
                        break;
                    case 7:
                        Chess.TILES[dim][elemnt].setToolTipText("2, H");
                        break;                            
                }
                break;
            case 2:
                switch(elemnt) {
                    case 0:
                        Chess.TILES[dim][elemnt].setToolTipText("3, A");
                        break;
                    case 1:
                        Chess.TILES[dim][elemnt].setToolTipText("3, B");
                        break;
                    case 2:
                        Chess.TILES[dim][elemnt].setToolTipText("3, C");
                        break;
                    case 3:
                        Chess.TILES[dim][elemnt].setToolTipText("3, D");
                        break;
                    case 4:
                        Chess.TILES[dim][elemnt].setToolTipText("3, E");
                        break;
                    case 5:
                        Chess.TILES[dim][elemnt].setToolTipText("3, F");
                        break;
                    case 6:
                        Chess.TILES[dim][elemnt].setToolTipText("3, G");
                        break;
                    case 7:
                        Chess.TILES[dim][elemnt].setToolTipText("3, H");
                        break;                            
                }
                break;
            case 3:
                switch(elemnt) {
                    case 0:
                        Chess.TILES[dim][elemnt].setToolTipText("4, A");
                        break;
                    case 1:
                        Chess.TILES[dim][elemnt].setToolTipText("4, B");
                        break;
                    case 2:
                        Chess.TILES[dim][elemnt].setToolTipText("4, C");
                        break;
                    case 3:
                        Chess.TILES[dim][elemnt].setToolTipText("4, D");
                        break;
                    case 4:
                        Chess.TILES[dim][elemnt].setToolTipText("4, E");
                        break;
                    case 5:
                        Chess.TILES[dim][elemnt].setToolTipText("4, G");
                        break;
                    case 6:
                        Chess.TILES[dim][elemnt].setToolTipText("4, F");
                        break;
                    case 7:
                        Chess.TILES[dim][elemnt].setToolTipText("4, H");
                        break;                            
                }
                break;
            case 4:
                switch(elemnt) {
                    case 0:
                        Chess.TILES[dim][elemnt].setToolTipText("5, A");
                        break;
                    case 1:
                        Chess.TILES[dim][elemnt].setToolTipText("5, B");
                        break;
                    case 2:
                        Chess.TILES[dim][elemnt].setToolTipText("5, C");
                        break;
                    case 3:
                        Chess.TILES[dim][elemnt].setToolTipText("5, D");
                        break;
                    case 4:
                        Chess.TILES[dim][elemnt].setToolTipText("5, E");
                        break;
                    case 5:
                        Chess.TILES[dim][elemnt].setToolTipText("5, F");
                        break;
                    case 6:
                        Chess.TILES[dim][elemnt].setToolTipText("5, G");
                        break;
                    case 7:
                        Chess.TILES[dim][elemnt].setToolTipText("5, H");
                        break;                            
                }
                break;
            case 5:
                switch(elemnt) {
                    case 0:
                        Chess.TILES[dim][elemnt].setToolTipText("6, A");
                        break;
                    case 1:
                        Chess.TILES[dim][elemnt].setToolTipText("6, B");
                        break;
                    case 2:
                        Chess.TILES[dim][elemnt].setToolTipText("6, C");
                        break;
                    case 3:
                        Chess.TILES[dim][elemnt].setToolTipText("6, D");
                        break;
                    case 4:
                        Chess.TILES[dim][elemnt].setToolTipText("6, E");
                        break;
                    case 5:
                        Chess.TILES[dim][elemnt].setToolTipText("6, F");
                        break;
                    case 6:
                        Chess.TILES[dim][elemnt].setToolTipText("6, G");
                        break;
                    case 7:
                        Chess.TILES[dim][elemnt].setToolTipText("6, H");
                        break;                            
                }
                break;
            case 6:
                switch(elemnt) {
                    case 0:
                        Chess.TILES[dim][elemnt].setToolTipText("7, A");
                        break;
                    case 1:
                        Chess.TILES[dim][elemnt].setToolTipText("7, B");
                        break;
                    case 2:
                        Chess.TILES[dim][elemnt].setToolTipText("7, C");
                        break;
                    case 3:
                        Chess.TILES[dim][elemnt].setToolTipText("7, D");
                        break;
                    case 4:
                        Chess.TILES[dim][elemnt].setToolTipText("7, E");
                        break;
                    case 5:
                        Chess.TILES[dim][elemnt].setToolTipText("7, F");
                        break;
                    case 6:
                        Chess.TILES[dim][elemnt].setToolTipText("7, G");
                        break;
                    case 7:
                        Chess.TILES[dim][elemnt].setToolTipText("7, H");
                        break;                            
                }
                break;
            case 7:
                switch(elemnt) {
                    case 0:
                        Chess.TILES[dim][elemnt].setToolTipText("8, A");
                        break;
                    case 1:
                        Chess.TILES[dim][elemnt].setToolTipText("8, B");
                        break;
                    case 2:
                        Chess.TILES[dim][elemnt].setToolTipText("8, C");
                        break;
                    case 3:
                        Chess.TILES[dim][elemnt].setToolTipText("8, D");
                        break;
                    case 4:
                        Chess.TILES[dim][elemnt].setToolTipText("8, E");
                        break;
                    case 5:
                        Chess.TILES[dim][elemnt].setToolTipText("8, F");
                        break;
                    case 6:
                        Chess.TILES[dim][elemnt].setToolTipText("8, G");
                        break;
                    case 7:
                        Chess.TILES[dim][elemnt].setToolTipText("8, H");
                        break;                            
                }
                break;                    
        }
    }
    
    private void getPos(ActionEvent evt) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(Chess.TILES[i][j] == evt.getSource()) {
                    if (!pressed) {
                        this.locationX = i;
                        this.locationY = j;
                    } else {
                        this.newlocationX = i;
                        this.newlocationY = j;
                    }
                    break;
                }
            }
        }
    }
    
    private boolean isWhite(int posx, int posy) {
        if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwBishop
         || Chess.TILES[posx][posy].getIcon() == Chess.ICONwKing
         || Chess.TILES[posx][posy].getIcon() == Chess.ICONwKnight
         || Chess.TILES[posx][posy].getIcon() == Chess.ICONwPawn
         || Chess.TILES[posx][posy].getIcon() == Chess.ICONwQueen
         || Chess.TILES[posx][posy].getIcon() == Chess.ICONwRook) {
            return true;
         } else {
            return false;
        }
    }
    
    private boolean isBlack(int posx, int posy) {        
        if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbBishop
         || Chess.TILES[posx][posy].getIcon() == Chess.ICONbKing
         || Chess.TILES[posx][posy].getIcon() == Chess.ICONbKnight
         || Chess.TILES[posx][posy].getIcon() == Chess.ICONbPawn
         || Chess.TILES[posx][posy].getIcon() == Chess.ICONbQueen
         || Chess.TILES[posx][posy].getIcon() == Chess.ICONbRook) {
            return true;
        } else {
            return false;
        }        
    }
    
    private void setStartPos() {
        // BLACK
        Chess.TILES[0][0].setIcon(Chess.ICONbRook);
        Chess.TILES[0][1].setIcon(Chess.ICONbKnight);
        Chess.TILES[0][2].setIcon(Chess.ICONbBishop);
        Chess.TILES[0][3].setIcon(Chess.ICONbQueen);
        Chess.TILES[0][4].setIcon(Chess.ICONbKing);
        Chess.TILES[0][5].setIcon(Chess.ICONbBishop);
        Chess.TILES[0][6].setIcon(Chess.ICONbKnight);
        Chess.TILES[0][7].setIcon(Chess.ICONbRook);
        for(int i = 0; i < 8; i++)Chess.TILES[1][i].setIcon(Chess.ICONbPawn);
        
        // WHITE
        Chess.TILES[7][0].setIcon(Chess.ICONwRook);
        Chess.TILES[7][1].setIcon(Chess.ICONwKnight);
        Chess.TILES[7][2].setIcon(Chess.ICONwBishop);
        Chess.TILES[7][3].setIcon(Chess.ICONwQueen);
        Chess.TILES[7][4].setIcon(Chess.ICONwKing);
        Chess.TILES[7][5].setIcon(Chess.ICONwBishop);
        Chess.TILES[7][6].setIcon(Chess.ICONwKnight);
        Chess.TILES[7][7].setIcon(Chess.ICONwRook);
        for(int i = 0; i < 8; i++)Chess.TILES[6][i].setIcon(Chess.ICONwPawn);
    }
    
    // PLAYER TURNS
    private void turns() {
        if(playerW) {
            playerW = false;
            playerB = true;
            setWhiteFalse();
            setBlackTrue();
        } else if(playerB) {
            playerW = true;
            playerB = false;
            setWhiteTrue();
            setBlackFalse();
        }
    }
    
    // UTILS
    private void clean() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {                                        
                Chess.TILES[i][j].setBackground(null);                
            }
        }
        paint();                 
    }
    
    private void paint() {
        boolean color = true;
        int cont = 0;
        
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(color) {
                    if(cont >= 8) {
                        Chess.TILES[i][j].setBackground(Color.LIGHT_GRAY);
                        cont = 0;
                    } else {
                        Chess.TILES[i][j].setBackground(Color.WHITE);
                        color = false;                       
                    }
                } else {
                    if(cont >= 8) {
                        Chess.TILES[i][j].setBackground(Color.WHITE);
                        cont = 0;
                    } else {
                        Chess.TILES[i][j].setBackground(Color.LIGHT_GRAY);
                        color = true;
                    }
                }
                cont++;
            }
        }
    }
    
    private void setMovingTilesFalse() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                movingTiles[i][j] = false;
            }
        }
    }
    
    private void setKillingTilesFalse() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                killingTiles[i][j] = false;
            }
        }
    }
    
    private void setAllNull() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Chess.TILES[i][j].setEnabled(false);
            }
        }
    }
    
    private void setNullTileFalse() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(Chess.TILES[i][j].getIcon() == null) {
                    Chess.TILES[i][j].setEnabled(false);
                }
            }
        }
    }
    
    private void setWhiteTrue() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (isWhite(i, j)) {
                    Chess.TILES[i][j].setEnabled(true);
                }
            }
        }
    }
    
    private void setWhiteFalse() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (isWhite(i, j)) {
                    Chess.TILES[i][j].setEnabled(false);
                }                
            }
        }
    }
    
    private void setBlackTrue() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (isBlack(i, j)) Chess.TILES[i][j].setEnabled(true);                
            }
        }
    }
    
    private void setBlackFalse() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (isBlack(i, j)) {
                Chess.TILES[i][j].setEnabled(false);
                }
            }
        }
    }
    
    private void setMovingTiles() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(movingTiles[i][j]) {
                    Chess.TILES[i][j].setBackground(Color.YELLOW);
                }
            }
        }
    }
    
    private void setKillingTiles() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(killingTiles[i][j]) {
                    Chess.TILES[i][j].setBackground(Color.RED);
                }
            }
        }
    }
    
    private void getMovingTiles(String tile, int posx, int posy) {
        switch(tile) {
        // WHITE
            case "wKing":
                // ABAJO
                if(posx != 7) {
                    if(Chess.TILES[posx + 1][posy].getIcon() == null) {
                        setup(posx + 1, posy, "move");
                    }                   
                }
                
                // ARRIBA
                if(posx != 0) {                    
                    if(Chess.TILES[posx - 1][posy].getIcon() == null) {
                        setup(posx - 1, posy, "move");
                    }                                        
                }
                
                // DERECHA
                if(posy != 7) {
                    if(Chess.TILES[posx][posy + 1].getIcon() == null) {                    
                        setup(posx, posy + 1, "move");
                    }                    
                }
                
                // IZQUIERDA
                if(posy != 0) {
                    if(Chess.TILES[posx][posy - 1].getIcon() == null) {
                        setup(posx, posy - 1, "move");          
                    }                   
                }
                
                // ABAJO DERECHA
                if(posx != 7 && posy != 7) {
                    if(Chess.TILES[posx + 1][posy + 1].getIcon() == null) {
                       setup(posx + 1, posy + 1, "move");
                    }                  
                }
                
                // ABAJO IZQUIERDA
                if(posx != 7 && posy != 0) {
                    if(Chess.TILES[posx + 1][posy - 1].getIcon() == null) {
                       setup(posx + 1, posy - 1, "move");
                    }                  
                }
                
                // ARRIBA DERECHA
                if(posx != 0 && posy != 7) {
                    if(Chess.TILES[posx - 1][posy + 1].getIcon() == null) {
                       setup(posx - 1, posy + 1, "move");
                    }                  
                }
                
                // ARRIBA IZQUIERDA
                if(posx != 0 && posy != 0) {
                    if(Chess.TILES[posx - 1][posy - 1].getIcon() == null) {
                       setup(posx - 1, posy - 1, "move");
                    }                
                }
            break;

            case "wQueen":
                // BISHOP FUNC
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.TILES[posx + i][posy + i].getIcon() == null) {
                            setup(posx + i, posy + i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.TILES[posx - i][posy - i].getIcon() == null) {
                            setup(posx - i, posy - i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.TILES[posx + i][posy - i].getIcon() == null) {
                            setup(posx + i, posy - i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.TILES[posx - i][posy + i].getIcon() == null) {
                            setup(posx - i, posy + i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
                
                // ROOK FUNC
                for(int i = 1; i < 8; i++) {
                    if( ( (posx + i) >= 0 && (posx + i) <= 7 ) ) {
                        if(Chess.TILES[posx + i][posy].getIcon() == null) {
                            setup(posx + i, posy, "move");
                        } else {
                            break;
                        }
                    }
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7) ) {
                        if(Chess.TILES[posx - i][posy].getIcon() == null) {
                            setup(posx - i, posy, "move");
                        } else {
                            break;
                        }
                    }
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ( (posy + i) >= 0 && (posy + i) <= 7 ) ) {
                        if(Chess.TILES[posx][posy + i].getIcon() == null) {
                            setup(posx, posy + i, "move");
                        } else {
                            break;
                        }
                    }
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.TILES[posx][posy - i].getIcon() == null) {
                            setup(posx, posy - i, "move");
                        } else {
                            break;
                        }
                    }
                }
            break;

            case "wBishop":
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.TILES[posx + i][posy + i].getIcon() == null) {
                            setup(posx + i, posy + i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.TILES[posx - i][posy - i].getIcon() == null) {
                            setup(posx - i, posy - i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.TILES[posx + i][posy - i].getIcon() == null) {
                            setup(posx + i, posy - i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.TILES[posx - i][posy + i].getIcon() == null) {
                            setup(posx - i, posy + i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
            break;

            case "wKnight":                
                if( ((posx - 2) >= 0 && (posx - 2) <= 7)
                        && ((posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(Chess.TILES[posx - 2][posy + 1].getIcon() == null) {
                        setup(posx - 2, posy + 1, "move");
                    }
                }
                
                if( ((posx - 2) >= 0 && (posx - 2) <= 7)
                        && ((posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(Chess.TILES[posx - 2][posy - 1].getIcon() == null) {
                        setup(posx - 2, posy - 1, "move");
                    }
                }
                
                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ((posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(Chess.TILES[posx + 2][posy + 1].getIcon() == null) {
                        setup(posx + 2, posy + 1, "move");
                    }
                }

                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ((posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(Chess.TILES[posx + 2][posy - 1].getIcon() == null) {
                        setup(posx + 2, posy - 1, "move");
                    }
                }

                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ((posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(Chess.TILES[posx + 1][posy + 2].getIcon() == null) {
                        setup(posx + 1, posy + 2, "move");
                    }
                }

                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ((posy - 2) >= 0 && (posy - 2) <= 7) ) {
                    if(Chess.TILES[posx + 1][posy - 2].getIcon() == null) {
                        setup(posx + 1, posy - 2, "move");
                    }
                }
                
                if( ((posx - 1) >= 0 && (posx - 1) <= 7)
                        && ((posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(Chess.TILES[posx - 1][posy + 2].getIcon() == null) {
                        setup(posx - 1, posy + 2, "move");
                    }
                }
                
                if( ((posx - 1) >= 0 && (posx - 1) <= 7)
                        && ((posy - 2) >= 0 && (posy - 2) <= 7) ) {
                    if(Chess.TILES[posx - 1][posy - 2].getIcon() == null) {                        
                        setup(posx - 1, posy - 2, "move");
                    }
                }
            break;

            case "wRook":                
                // ABAJO
                for(int i = 1; i <= 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7) ) {
                        if(Chess.TILES[posx + i][posy].getIcon() == null) {
                            setup(posx + i, posy, "move");
                        } else {
                            break;
                        }
                    }
                }
                
                // ARRIBA
                for(int i = 1; i <= 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7) ) {
                        if(Chess.TILES[posx - i][posy].getIcon() == null) {
                            setup(posx - i, posy, "move");
                        } else {
                            break;
                        }
                    }
                }
                
                // DERECHA
                for(int i = 1; i <= 8; i++) {
                    if( ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.TILES[posx][posy + i].getIcon() == null) {
                            setup(posx, posy + i, "move");
                        } else {
                            break;
                        }
                    }
                }
                
                // IZQUIERDA
                for(int i = 1; i <= 8; i++) {
                    if( ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.TILES[posx][posy - i].getIcon() == null) {
                            setup(posx, posy - i, "move");
                        } else {
                            break;
                        }
                    }
                }
            break;

            case "wPawn":
                if(posx == 6) { // CONDICIONAL PRIMERA JUGADA
                    // ARRIBA 1
                    if(Chess.TILES[posx - 1][posy].getIcon() == null) {
                        setup(posx - 1, posy, "move");
                    }
                    
                    // ARRIBA 2
                    if (Chess.TILES[posx - 2][posy].getIcon() == null) {
                        setup(posx - 2, posy, "move");
                    }
                }

                if(posx < 6) { // CONDICIONAL SIGUIENTES JUGADAS
                    // ARRIBA
                    if(Chess.TILES[posx - 1][posy].getIcon() == null) {
                        setup(posx - 1, posy, "move");
                    }
                }
            break;                
            
        // BLACK
            case "bKing":
                if(posx != 7) {
                    if(Chess.TILES[posx + 1][posy].getIcon() == null) {
                        setup(posx + 1, posy, "move");
                    }                
                }

                if(posx != 0) {
                    if(Chess.TILES[posx - 1][posy].getIcon() == null) {
                        setup(posx - 1, posy, "move");
                    }                
                }

                if(posy != 7) {
                    if(Chess.TILES[posx][posy + 1].getIcon() == null) {                    
                        setup(posx, posy + 1, "move");
                    }                
                }

                if(posy != 0) {
                    if(Chess.TILES[posx][posy - 1].getIcon() == null) {
                        setup(posx, posy - 1, "move");
                    }                
                }

                if(posx != 7 && posy != 7) {
                    if(Chess.TILES[posx + 1][posy + 1].getIcon() == null) {
                        setup(posx + 1, posy + 1, "move");
                    }                
                }

                if(posx != 7 && posy != 0) {
                    if(Chess.TILES[posx + 1][posy - 1].getIcon() == null) {
                       setup(posx + 1, posy - 1, "move");
                    }               
                }

                if(posx != 0 && posy != 7) {
                    if(Chess.TILES[posx - 1][posy + 1].getIcon() == null) {
                        setup(posx - 1, posy + 1, "move");
                    }                
                }

                if(posx != 0 && posy != 0) {
                    if(Chess.TILES[posx - 1][posy - 1].getIcon() == null) {
                        setup(posx - 1, posy - 1, "move");
                    }                
                }
            break;

            case "bQueen":                
                // BISHOP FUNC
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.TILES[posx + i][posy + i].getIcon() == null) {
                            setup(posx + i, posy + i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.TILES[posx - i][posy - i].getIcon() == null) {
                            setup(posx - i, posy - i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.TILES[posx + i][posy - i].getIcon() == null) {
                            setup(posx + i, posy - i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.TILES[posx - i][posy + i].getIcon() == null) {
                            setup(posx - i, posy + i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
                
                // ROOK FUNC
                for(int i = 1; i < 8; i++) {
                    if( ( (posx + i) >= 0 && (posx + i) <= 7 ) ) {
                        if(Chess.TILES[posx + i][posy].getIcon() == null) {
                            setup(posx + i, posy, "move");
                        } else {
                            break;
                        }
                    }
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7) ) {
                        if(Chess.TILES[posx - i][posy].getIcon() == null) {
                            setup(posx - i, posy, "move");
                        } else {
                            break;
                        }
                    }
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ( (posy + i) >= 0 && (posy + i) <= 7 ) ) {
                        if(Chess.TILES[posx][posy + i].getIcon() == null) {
                            setup(posx, posy + i, "move");
                        } else {
                            break;
                        }
                    }
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.TILES[posx][posy - i].getIcon() == null) {
                            setup(posx, posy - i, "move");
                        } else {
                            break;
                        }
                    }
                }
            break;

            case "bBishop":
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.TILES[posx + i][posy + i].getIcon() == null) {
                            setup(posx + i, posy + i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.TILES[posx - i][posy - i].getIcon() == null) {
                            setup(posx - i, posy - i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.TILES[posx + i][posy - i].getIcon() == null) {
                            setup(posx + i, posy - i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(Chess.TILES[posx - i][posy + i].getIcon() == null) {
                            setup(posx - i, posy + i, "move");
                        } else {
                            break;
                        }
                    }                    
                }
            break;

            case "bKnight":
                if( ((posx - 2) >= 0 && (posx - 2) <= 7 )
                        && ( (posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(Chess.TILES[posx - 2][posy + 1].getIcon() == null) {
                        setup(posx - 2, posy + 1, "move");
                    }
                }
                
                if( ((posx - 2) >= 0 && (posx - 2) <= 7 )
                        && ((posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(Chess.TILES[posx - 2][posy - 1].getIcon() == null) {
                        setup(posx - 2, posy - 1, "move");
                    }
                }
                
                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ( (posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(Chess.TILES[posx + 2][posy + 1].getIcon() == null) {
                        setup(posx + 2, posy + 1, "move");
                    }
                }

                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ( (posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(Chess.TILES[posx + 2][posy - 1].getIcon() == null) {
                        setup(posx + 2, posy - 1, "move");
                    }
                }

                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ((posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(Chess.TILES[posx + 1][posy + 2].getIcon() == null) {
                        setup(posx + 1, posy + 2, "move");
                    }
                }

                if( ( (posx + 1) >= 0 && (posx + 1) <= 7 )
                        && ( (posy - 2) >= 0 && (posy - 2) <= 7 ) ) {
                    if(Chess.TILES[posx + 1][posy - 2].getIcon() == null) {
                        setup(posx + 1, posy - 2, "move");
                    }
                }
                
                if( ( (posx - 1) >= 0 && (posx - 1) <= 7 )
                        && ( (posy + 2) >= 0 && (posy + 2) <= 7 ) ) {
                    if(Chess.TILES[posx - 1][posy + 2].getIcon() == null) {
                        setup(posx - 1, posy + 2, "move");
                    }
                }
                
                if( ((posx - 1) >= 0 && (posx - 1) <= 7)
                        && ((posy - 2) >= 0 && (posy - 2) <= 7) ) {
                    if(Chess.TILES[posx - 1][posy - 2].getIcon() == null) {                        
                        setup(posx - 1, posy + 2, "move");
                    }
                }
            break;

            case "bRook":
                for(int i = 1; i < 8; i++) {
                    if( ( (posx + i) >= 0 && (posx + i) <= 7 ) ) {
                        if(Chess.TILES[posx + i][posy].getIcon() == null) {
                            setup(posx + i, posy, "move");
                        } else {
                            break;
                        }
                    }
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7) ) {
                        if(Chess.TILES[posx - i][posy].getIcon() == null) {
                            setup(posx - i, posy, "move");
                        } else {
                            break;
                        }
                    }
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ( (posy + i) >= 0 && (posy + i) <= 7 ) ) {
                        if(Chess.TILES[posx][posy + i].getIcon() == null) {
                            setup(posx, posy + i, "move");
                        } else {
                            break;
                        }
                    }
                }
                
                for(int i = 1; i < 8; i++) {
                    if( ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(Chess.TILES[posx][posy - i].getIcon() == null) {
                            setup(posx, posy - i, "move");
                        } else {
                            break;
                        }
                    }
                }
            break;

            case "bPawn":
                if(posx == 1) {                
                    if(Chess.TILES[posx + 1][posy].getIcon() == null) {
                        setup(posx + 1, posy, "move");
                    }

                    if (Chess.TILES[posx + 2][posy].getIcon() == null) {
                        setup(posx + 2, posy, "move");
                    }
                }

                if(posx > 1) {
                    if(Chess.TILES[posx + 1][posy].getIcon() == null) {
                        setup(posx + 1, posy, "move");
                    }
                }
            break;
        }
    }
    
    private void getKillingTiles(String tile, int posx, int posy) {        
        switch(tile) {
        // WHITE
            case "wKing":
                if(posx != 7) {
                    if (isBlack(posx + 1, posy)) {
                        setup(posx + 1, posy, "kill");
                    }
                }

                if(posx != 0) {               
                    if (isBlack(posx - 1, posy)) {
                        setup(posx - 1, posy, "kill");
                    }
                }

                if(posy != 7) {                
                    if (isBlack(posx, posy + 1)) {
                        setup(posx, posy + 1, "kill");
                    }
                }

                if(posy != 0) {                
                    if (isBlack(posx, posy - 1)) {
                        setup(posx, posy - 1, "kill");
                    }
                }

                if(posx != 7 && posy != 7) {                
                    if (isBlack(posx + 1, posy + 1)) {
                        setup(posx + 1, posy + 1, "kill");
                    }
                }

                if(posx != 7 && posy != 0) {                
                    if (isBlack(posx + 1, posy - 1)) {
                        setup(posx + 1, posy - 1, "kill");
                    }
                }

                if(posx != 0 && posy != 7) {                
                    if (isBlack(posx - 1, posy + 1)) {
                        setup(posx - 1, posy + 1, "kill");
                    }
                }

                if(posx != 0 && posy != 0) {                
                    if (isBlack(posx - 1, posy - 1)) {
                        setup(posx - 1, posy - 1, "kill");
                    }
                }
            break;

            case "wQueen":
                
            // BISHOP FUNC
                // ARRIBA DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(isWhite(posx + i, posy + i)) {
                            break;
                        } else if(isBlack(posx + i, posy + i)) {
                            setup(posx + i, posy + i, "kill");
                            break;
                        }
                    }                    
                }
                
                // ABAJO IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(isWhite(posx - i, posy - i)) {
                            break;
                        } else if(isBlack(posx - i, posy - i)) {
                            setup(posx - i, posy - i, "kill");
                            break;
                        }
                    }                    
                }
                
                // ARRIBA IZQUIERDA                
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(isWhite(posx + i, posy - i)) {
                            break;
                        } else if(isBlack(posx + i, posy - i)) {
                            setup(posx + i, posy - i, "kill");
                            break;
                        }
                    }                    
                }
                
                // ABAJO DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(isWhite(posx - i, posy + i)) {
                            break;
                        } else if(isBlack(posx - i, posy + i)) {
                            setup(posx - i, posy + i, "kill");
                            break;
                        }
                    }                    
                }
                
            // ROOK FUNC
                // ABAJO
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7) ) {
                        if(isWhite(posx + i, posy)) {
                            break;
                        } else if(isBlack(posx + i, posy)) {
                            setup(posx + i, posy, "kill");
                            break;
                        }
                    }
                }
                
                // ARRIBA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7) ) {
                        if(isWhite(posx - i, posy)) {
                            break;
                        } else if(isBlack(posx - i, posy)) {
                            setup(posx - i, posy, "kill");
                            break;
                        }
                    }
                }
                
                // DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(isWhite(posx, posy + i)) {
                            break;
                        } else if(isBlack(posx, posy + i)) {
                            setup(posx, posy + i, "kill");
                            break;
                        }
                    }
                }
                
                // IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(isWhite(posx, posy - i)) {
                            break;
                        } else if(isBlack(posx, posy - i)) {
                            setup(posx, posy - i, "kill");
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
                        if(isWhite(posx + i, posy + i)) {
                            break;
                        } else if(isBlack(posx + i, posy + i)) {
                            setup(posx + i, posy + i, "kill");
                            break;
                        }
                    }                    
                }
                
                // ABAJO IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(isWhite(posx - i, posy - i)) {
                            break;
                        } else if(isBlack(posx - i, posy - i)) {
                            setup(posx - i, posy - i, "kill");
                            break;
                        }
                    }                    
                }
                
                // ARRIBA IZQUIERDA                
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(isWhite(posx + i, posy - i)) {
                            break;
                        } else if(isBlack(posx + i, posy - i)) {
                            setup(posx + i, posy - i, "kill");
                            break;
                        }
                    }                    
                }
                
                // ABAJO DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(isWhite(posx - i, posy + i)) {
                            break;
                        } else if(isBlack(posx - i, posy + i)) {
                            setup(posx - i, posy + i, "kill");
                            break;
                        }
                    }                    
                }
            break;

            case "wKnight":
                if( ((posx - 2) >= 0 && (posx - 2) <= 7)
                        && ( (posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(isBlack(posx - 2, posy + 1)) {
                        setup(posx - 2, posy + 1, "kill");
                    }
                }
                
                if( ((posx - 2) >= 0 && (posx - 2) <= 7)
                        && ( (posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(isBlack(posx - 2, posy - 1)) {
                        setup(posx - 2, posy - 1, "kill");
                    }
                }
                
                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ((posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(isBlack(posx + 2, posy + 1)) {
                        setup(posx + 2, posy + 1, "kill");
                    }
                }

                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ((posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(isBlack(posx + 2, posy - 1)) {
                        setup(posx + 2, posy - 1, "kill");
                    }
                }

                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ((posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(isBlack(posx + 1, posy + 2)) {
                        setup(posx + 2, posy + 2, "kill");
                    }
                }

                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ((posy - 2) >= 0 && (posy - 2) <= 7) ) {
                    if(isBlack(posx + 1, posy - 2)) {
                        setup(posx + 1, posy - 2, "kill");
                    }
                }
                
                if( ((posx - 1) >= 0 && (posx - 1) <= 7)
                        && ((posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(isBlack(posx - 1, posy + 2)) {
                        setup(posx - 1, posy + 2, "kill");
                    }
                }
                
                if( ((posx - 1) >= 0 && (posx - 1) <= 7)
                        && ((posy - 2) >= 0 && (posy - 2) <= 7) ) {
                    if(isBlack(posx - 1, posy - 2)) {                        
                        setup(posx - 1, posy - 2, "kill");
                    }
                }
            break;

            case "wRook":
                // ABAJO
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7) ) {
                        if(isWhite(posx + i, posy)) {
                            break;
                        } else if(isBlack(posx + i, posy)) {
                            setup(posx + i, posy, "kill");
                            break;
                        }
                    }
                }
                
                // ARRIBA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7) ) {
                        if(isWhite(posx - i, posy)) {
                            break;
                        } else if(isBlack(posx - i, posy)) {
                            setup(posx - i, posy, "kill");
                            break;
                        }
                    }
                }
                
                // DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(isWhite(posx, posy + i)) {
                            break;
                        } else if(isBlack(posx, posy + i)) {
                            setup(posx, posy + i, "kill");
                            break;
                        }
                    }
                }
                
                // IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(isWhite(posx, posy - i)) {
                            break;
                        } else if(isBlack(posx, posy - i)) {
                            setup(posx, posy - i, "kill");
                            break;
                        }
                    }
                }
            break;

            case "wPawn":
                // ATAQUE EN DIAGONAL SUPERIOR DERECHA
                if(posy < 7) {
                    if (isBlack(posx - 1, posy + 1)) {
                        setup(posx - 1, posy + 1, "kill");
                    }
                }
                
                // ATAQUE EN DIAGONAL SUPERIOR IZQUIERDA
                if(posy > 1) {
                    if (isBlack(posx - 1, posy - 1)) {
                        setup(posx - 1, posy - 1, "kill");
                    }
                }
            break;
                
                
        // BLACK
            case "bKing":
                // ABAJO
                if(posx != 7) {                        
                    if (isWhite(posx + 1, posy)) {
                        setup(posx + 1, posy, "kill");
                    }
                }
                
                // ARRIBA
                if(posx != 0) {                        
                    if (isWhite(posx - 1, posy)) {
                        setup(posx - 1, posy, "kill");
                    }
                }
                
                // DERECHA
                if(posy != 7) {                        
                    if (isWhite(posx, posy + 1)) {
                        setup(posx, posy + 1, "kill");
                    }
                }

                // IZQUIERDA
                if(posy != 0) {                        
                    if (isWhite(posx, posy - 1)) {
                        setup(posx, posy - 1, "kill");
                    }
                }

                // ABAJO DERECHA
                if(posx != 7 && posy != 7) {                        
                    if (isWhite(posx + 1, posy + 1)) {
                        setup(posx + 1, posy + 1, "kill");
                    }
                }

                // ABAJO IZQUIERDA
                if(posx != 7 && posy != 0) {                        
                    if (isWhite(posx + 1, posy - 1)) {
                        setup(posx + 1, posy - 1, "kill");
                    }
                }
                
                // ARRIBA DERECHA
                if(posx != 0 && posy != 7) {                        
                    if (isWhite(posx - 1, posy + 1)) {
                        setup(posx - 1, posy + 1, "kill");
                    }
                }

                // ARRIBA IZQUIERDA
                if(posx != 0 && posy != 0) {                        
                    if (isWhite(posx - 1, posy - 1)) {
                        setup(posx - 1, posy - 1, "kill");
                    }
                }
            break;

            case "bQueen":
            // BISHOP FUNC
                // ABAJO DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(isBlack(posx + i, posy + i)) {
                            break;
                        } else if(isWhite(posx + i, posy + i)) {
                            setup(posx + i, posy + i, "kill");
                            break;
                        }
                    }                    
                }
                
                // ARRIBA IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(isBlack(posx - i, posy - i)) {
                            break;
                        } else if(isWhite(posx - i, posy - i)) {
                            setup(posx - i, posy - i, "kill");
                            break;
                        }
                    }                    
                }
                
                // ABAJO IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(isBlack(posx + i, posy - i)) {
                            break;
                        } else if(isWhite(posx + i, posy - i)) {
                            setup(posx + i, posy - i, "kill");
                            break;
                        }
                    }                    
                }
                
                // ARRIBA DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(isBlack(posx - i, posy + i)) {
                            break;
                        } else if(isWhite(posx - i, posy + i)) {
                            setup(posx - i, posy + i, "kill");
                            break;
                        }
                    }                    
                }
                
            // ROOK FUNC
                // ABAJO
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7) ) {
                        if(isBlack(posx + i, posy)) {
                            break;
                        } else if(isWhite(posx + i, posy)) {
                            setup(posx + i, posy, "kill");
                            break;
                        }
                    }
                }
                
                // ARRIBA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7) ) {
                        if(isBlack(posx - i, posy)) {
                            break;
                        } else if(isWhite(posx - i, posy)) {
                            setup(posx - i, posy, "kill");
                            break;
                        }
                    }
                }
                
                // DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(isBlack(posx, posy + i)) {
                            break;
                        } else if(isWhite(posx, posy + i)) {
                            setup(posx, posy + i, "kill");
                            break;
                        }
                    }
                }
                
                // IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(isBlack(posx, posy - i)) {
                            break;
                        } else if(isWhite(posx, posy - i)) {
                            setup(posx, posy - i, "kill");
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
                        if(isBlack(posx + i, posy + i)) {
                            break;
                        } else if(isWhite(posx + i, posy + i)) {
                            setup(posx + i, posy + i, "kill");
                            break;
                        }
                    }                    
                }
                
                // ARRIBA IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(isBlack(posx - i, posy - i)) {
                            break;
                        } else if(isWhite(posx - i, posy - i)) {
                            setup(posx - i, posy - i, "kill");
                            break;
                        }
                    }                    
                }
                
                // ABAJO IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7)
                            && ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(isBlack(posx + i, posy - i)) {
                            break;
                        } else if(isWhite(posx + i, posy - i)) {
                            setup(posx + i, posy - i, "kill");
                            break;
                        }
                    }                    
                }
                
                // ARRIBA DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7)
                            && ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(isBlack(posx - i, posy + i)) {
                            break;
                        } else if(isWhite(posx - i, posy + i)) {
                            setup(posx - i, posy + i, "kill");
                            break;
                        }
                    }                    
                }
            break;

            case "bKnight":
                // ARRIBA ABAJO DERECHA
                if( ((posx - 2) >= 0 && (posx - 2) <= 7)
                        && ( (posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(isWhite(posx - 2, posy + 1)) {
                        setup(posx - 2, posy + 1, "kill");
                    }
                }
                
                if( ((posx - 2) >= 0 && (posx - 2) <= 7)
                        && ( (posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(isWhite(posx - 2, posy - 1)) {
                        setup(posx - 2, posy - 1, "kill");
                    }
                }
                
                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ( (posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(isWhite(posx + 2, posy + 1)) {
                        setup(posx + 2, posy + 1, "kill");
                    }
                }

                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ( (posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(isWhite(posx + 2, posy - 1)) {
                        setup(posx + 2, posy - 1, "kill");
                    }
                }

                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ( (posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(isWhite(posx + 1, posy + 2)) {
                        setup(posx + 1, posy + 2, "kill");
                    }
                }

                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ((posy - 2) >= 0 && (posy - 2) <= 7) ) {
                    if(isWhite(posx + 1, posy - 2)) {
                        setup(posx + 1, posy - 2, "kill");
                    }
                }
                
                if( ((posx - 1) >= 0 && (posx - 1) <= 7)
                        && ((posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(isWhite(posx - 1, posy + 2)) {
                        setup(posx - 1, posy + 2, "kill");
                    }
                }
                
                if( ((posx - 1) >= 0 && (posx - 1) <= 7)
                        && ((posy - 2) >= 0 && (posy - 2) <= 7) ) {
                    if(isWhite(posx - 1, posy - 2)) {                        
                        setup(posx - 1, posy - 2, "kill");
                    }
                }
            break;

            case "bRook":
                // ABAJO
                for(int i = 1; i < 8; i++) {
                    if( ((posx + i) >= 0 && (posx + i) <= 7) ) {
                        if(isBlack(posx + i, posy)) {
                            break;
                        } else if(isWhite(posx + i, posy)) {
                            setup(posx + i, posy, "kill");
                            break;
                        }
                    }
                }
                
                // ARRIBA
                for(int i = 1; i < 8; i++) {
                    if( ((posx - i) >= 0 && (posx - i) <= 7) ) {
                        if(isBlack(posx - i, posy)) {
                            break;
                        } else if(isWhite(posx - i, posy)) {
                            setup(posx - i, posy, "kill");
                            break;
                        }
                    }
                }
                
                // DERECHA
                for(int i = 1; i < 8; i++) {
                    if( ((posy + i) >= 0 && (posy + i) <= 7) ) {
                        if(isBlack(posx, posy + i)) {
                            break;
                        } else if(isWhite(posx, posy + i)) {
                            setup(posx, posy + i, "kill");
                            break;
                        }
                    }
                }
                
                // IZQUIERDA
                for(int i = 1; i < 8; i++) {
                    if( ((posy - i) >= 0 && (posy - i) <= 7) ) {
                        if(isBlack(posx, posy - i)) {
                            break;
                        } else if(isWhite(posx, posy - i)) {
                            setup(posx, posy - i, "kill");
                            break;
                        }
                    }
                }
            break;

            case "bPawn":
                // ATAQUE DIAGONAL SUPERIOR DERECHA
                if(posy <= 6) {
                    if(isWhite(posx + 1, posy + 1)) {
                        setup(posx + 1, posy + 1, "kill");
                    }
                }

                // ATAQUE DIAGONAL SUPERIOR IZQUIERDA
                if(posy >= 1) {
                    if(isWhite(posx + 1, posy - 1)) {
                        setup(posx + 1, posy - 1, "kill");
                    }
                }                    
            break;
        }
    }
    
    private String getTile(int posx, int posy) {
        String tile = null;
        if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwKing) {
            tile = "wKing";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwQueen) {
            tile = "wQueen";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwBishop) {
            tile = "wBishop";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwKnight) {
            tile = "wKnight";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwRook) {
            tile = "wRook";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONwPawn) {
            tile = "wPawn";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbKing) {
            tile = "bKing";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbQueen) {
            tile = "bQueen";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbBishop) {
            tile = "bBishop";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbKnight) {
            tile = "bKnight";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbRook) {
            tile = "bRook";
        } else if (Chess.TILES[posx][posy].getIcon() == Chess.ICONbPawn) {
            tile = "bPawn";
        }
        return tile;
    }
    
    private void moveTile(JButton tile, JButton newtile) {
        Icon icon = tile.getIcon();
        tile.setIcon(null);
        newtile.setIcon(icon);
    }        
    
    private void setup(int posx, int posy, String action) {
        Chess.TILES[posx][posy].setEnabled(true);
        switch(action) {
            case "move":
                movingTiles[posx][posy] = true;
                break;
            case "kill":
                killingTiles[posx][posy] = true;
                break;
        }
    }
    
    public static void main(String[] args) {
        new Chess().setVisible(true);
    }
    
    // FRAME COMPONENTS
    private JMenuBar bar;
    private JMenu menuGame;
    private JMenuItem itemStart;
    private JPanel panelGame;
}