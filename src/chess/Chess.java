package chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Pepe
 */
public class Chess extends JFrame {

    public Chess chessInst;
    // Res
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

    // Grid
    public static final JButton[][] TILES = new JButton[8][8];
    // Posicion actual que se ha pulsado
    private int locationX, locationY;
    // Posicion a la que se va a mover
    private int newlocationX, newlocationY;
    // Si ya se ha pulsado una figura para mover
    private boolean pressed;
    // turnos
    private boolean playerW, playerB;
    // 0 -> Black; 1 -> White | Condicional si el rey se ha movido
    private boolean[] canKingCastle = new boolean[2];
    // 0 -> Black; 1 -> White | Condicional si la torre (derecha) se mueve
    private boolean[] canDoShortCastling = new boolean[2];
    // 0 -> Black; 1 -> White | Condicional si la torre (izquierda) se mueve
    private boolean[] canDoLongCastling = new boolean[2];
    //private boolean[][] hasRookMove = new boolean[2][2]; // 0 -> Black [ y = 0, y = 7]; 1 -> White [ y = 0, y = 7];

    public static TilesHandler tilesHandler = new TilesHandler();
    public static MovingTiles movingTiles = new MovingTiles();
    public static KillingTiles killingTiles = new KillingTiles();
    public static BlockingCheckTiles blockingCheckTiles = new BlockingCheckTiles();

    public Chess() {
        initValues(); // Game vars init/reset
        initUI();
        initBoard();
    }

    private void initValues() {
        playerW = true;
        playerB = false;
        canKingCastle[0] = true;
        canKingCastle[1] = true;
        canDoLongCastling[0] = true;
        canDoLongCastling[1] = true;
        canDoShortCastling[0] = true;
        canDoShortCastling[1] = true;
    }

    private void initUI() {
        initBar();
        panelGame = new JPanel();

        setTitle("Chess");
        setSize(800, 800);
        setResizable(false);
        centerWindow(); // = setLocationRelativeTo / better
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelGame.setLayout(new GridLayout(8, 8));
        // Init grid
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Chess.TILES[x][y] = new JButton();
                Chess.TILES[x][y].setToolTipText("X: " + x + " Y: " + y);
                //new TilesTips(x, y);
                Chess.TILES[x][y].addActionListener(tileListener);
                panelGame.add(Chess.TILES[x][y]);
            }
        }
        // Paint chess grid
        tilesHandler.paint();
        add(panelGame);
    }

    private void centerWindow() {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = defaultToolkit.getScreenSize();
        this.setLocation(
                (int) ((screenSize.getWidth() / 2) - (this.getWidth() / 2)),
                (int) ((screenSize.getHeight() / 2) - (this.getHeight() / 2)));
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
                tilesHandler.setWhite(true);
            }
        });
    }

    ActionListener tileListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String player = null;
            if (playerW) {
                player = "w";
            } else {
                player = "b";
            }
            if (!pressed) {
                getPos(evt);
                tilesHandler.setAllFalse();
                Chess.TILES[locationX][locationY].setEnabled(true);
                if (!tilesHandler.isCheck(player)) {
                    System.out.println("NO ES JAQUE");
                    // NO ES JAQUE
                    setMovingTiles();
                    setKillingTiles();
                } else {
                    System.out.println("ES JAQUE");
                    setBlockingCheckTiles();
                }

                pressed = true;
            } else {
                getPos(evt);
                if (locationX == newlocationX && locationY == newlocationY) {
                    // CANCEL MOVING TILE
                    tilesHandler.repaint(); // old clean function                    
                    tilesHandler.setNullTileFalse(); // set tiles with no figure false
                    // Player turning
                    if (playerW) {
                        // Set all white tiles enabled true
                        tilesHandler.setWhite(true);
                        // Set all black tiles enabled false
                        tilesHandler.setBlack(false);
                    } else if (playerB) {
                        // Set all white tiles enabled false
                        tilesHandler.setWhite(false);
                        // Set all black tiles enabled true
                        tilesHandler.setBlack(true);
                    }
                    pressed = false;
                } else {
                    // Cancelar enrocamiento en un lado si se mueve la torre
                    String tileR = player + "Rook"; // Codigo de la figura Torre
                    if(tilesHandler.getTile(locationX, locationY).equals(tileR)) {
                        System.out.println("w has clicked rook in: "+locationY);
                        if(locationX == 0 || locationY == 7) {
                            switch(locationY) {
                                case 0:
                                    setCanDoCastling(player, "long", false);
                                    break;
                                    
                                case 7:
                                    setCanDoCastling(player, "short", false);
                                    break;
                            }
                        }
                    }
                    // ENROCAMIENTO
                    int axisX = 0;
                    //Icon rookIc = null;   
                    switch (player) {
                        case "b":
                            axisX = 0;
                            //rookIc = Chess.ICONbRook;
                            break;

                        case "w":
                            axisX = 7;
                            //rookIc = Chess.ICONwRook;
                            break;
                    }
                    String tileK = player + "King"; // Codigo de la figura Rey
                    // Comprueba que el rey se quiere mover a la torre (enroque)
                    if (tilesHandler.getTile(locationX, locationY).equals(tileK)) {
                        /* Comprobar si es King*/
                        // Si no hay figura
                        if (!tilesHandler.isTile(newlocationX, newlocationY)) {
                            // Rutina normal

                            // El rey ya no se puede enrocar
                            setCanKingCastle(player, false);
                            // Mover figura
                            tilesHandler.moveTile(Chess.TILES[locationX][locationY], Chess.TILES[newlocationX][newlocationY]);
                        } else {
                            // Se comprueba si no se ha movido el rey
                            if (getCanKingCastle(player)) {
                                System.out.println("NO HA ENROCADO TODAVIA " + player);
                                if (tilesHandler.getTile(newlocationX, newlocationY).equals(tileR)) {
                                    /* Comprobar si es Rook*/
                                    System.out.println("SE QUIERE ENROCAR " + tileK);
                                    /**
                                     *   Se comprueba por 2a vez si se puede cumplir el enroque mirando si los ejes x
                                     *   de las dos figuras igualan a 0 para las figuras negras o igualan 7 para las
                                     *   figuras blancas, esto no es una forma correcta de comprobarlo ya que existen
                                     *   dos tipos de enroque y hay 2 torres, la funcion para encontrar el eje X dice la
                                     *   primera torre que se encuentra, se podria hacer un objeto para cada ficha y darle
                                     *   a cada figura un codigo unico, para no confundir las parejas Rook, Knight, Bishop,
                                     *   pero esta forma tambien gasta mas recursos.                                    
                                    **/
                                    
                                    // TODO: Crear otro condicional para comprobar si se puede hacer el enroque
                                    //if( (/* Black Player */false) || (/* White player*/false) ) {}
                                    if (/* bKing & bRook */(tilesHandler.getTilePos("x", tileK) == 0
                                            && tilesHandler.getTilePos("x", tileR) == 0) ||
                                        /* wKing & wRook */ (tilesHandler.getTilePos("x", tileK) == 7
                                            && tilesHandler.getTilePos("x", tileR) == 7) ) {
                                        // COMPROBADO
                                        System.out.println("COMPROBADO");
                                        // El rey ya no se puede enrocar mas
                                        setCanKingCastle(player, false);
                                        if (!(tilesHandler.isTile(axisX, 1) && tilesHandler.isTile(axisX, 2)
                                                && tilesHandler.isTile(axisX, 3))) {
                                            // Check if the rook of that side has moved
                                            if(getCanDoCastling(player, "long")) {
                                                // LONG CASTLING
                                                tilesHandler.moveTile(Chess.TILES[axisX][4], Chess.TILES[axisX][2]);
                                                tilesHandler.moveTile(Chess.TILES[axisX][0], Chess.TILES[axisX][3]);                                                
                                            }
                                        }
                                        if (!(tilesHandler.isTile(axisX, 5) && tilesHandler.isTile(axisX, 6))) {
                                            // Check if the rook of that side has moved
                                            if(getCanDoCastling(player, "short")) {
                                                // SHORT CASTLING                                            
                                                tilesHandler.moveTile(Chess.TILES[axisX][4], Chess.TILES[axisX][6]);
                                                tilesHandler.moveTile(Chess.TILES[axisX][7], Chess.TILES[axisX][5]);
                                            }
                                        }
                                    }
                                    System.out.println("!COMPROBADO");
                                }
                            }
                        }
                    } else {
                        tilesHandler.moveTile(Chess.TILES[locationX][locationY], Chess.TILES[newlocationX][newlocationY]);
                    }
                    tilesHandler.repaint();
                    tilesHandler.setNullTileFalse();
                    tilesHandler.setAllFalse();
                    turns();
                    pressed = false;
                }
            }
        }
    };

    private void initBoard() {
        setStartPos();
        tilesHandler.setAllFalse();
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
        for (int i = 0; i < 8; i++) {
            Chess.TILES[1][i].setIcon(Chess.ICONbPawn);
        }

        // WHITE
        Chess.TILES[7][0].setIcon(Chess.ICONwRook);
        Chess.TILES[7][1].setIcon(Chess.ICONwKnight);
        Chess.TILES[7][2].setIcon(Chess.ICONwBishop);
        Chess.TILES[7][3].setIcon(Chess.ICONwQueen);
        Chess.TILES[7][4].setIcon(Chess.ICONwKing);
        Chess.TILES[7][5].setIcon(Chess.ICONwBishop);
        Chess.TILES[7][6].setIcon(Chess.ICONwKnight);
        Chess.TILES[7][7].setIcon(Chess.ICONwRook);
        for (int i = 0; i < 8; i++) {
            Chess.TILES[6][i].setIcon(Chess.ICONwPawn);
        }
    }

    private void getPos(ActionEvent evt) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (Chess.TILES[x][y] == evt.getSource()) {
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

    // PLAYER TURNS
    private void turns() {
        if (playerW) {
            playerW = false;
            playerB = true;
            tilesHandler.setWhite(false);
            tilesHandler.setBlack(true);
        } else if (playerB) {
            playerW = true;
            playerB = false;
            tilesHandler.setWhite(true);
            tilesHandler.setBlack(false);
        }
    }

    public void setMovingTiles() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (!(x == locationX && y == locationY)) {
                    if (movingTilesget(tilesHandler.getTile(locationX, locationY), locationX, locationY)[x][y]) {
                        Chess.TILES[x][y].setBackground(Color.YELLOW);
                    } else if (!movingTilesget(tilesHandler.getTile(locationX, locationY), locationX, locationY)[x][y]
                            || killingTiles.get(tilesHandler.getTile(locationX, locationY), locationX, locationY)[x][y]) {
                        Chess.TILES[x][y].setEnabled(false);
                    }
                }
            }
        }
    }
    
    public boolean[][] movingTilesget(String tile, int posx, int posy) {
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
                
                if(getCanKingCastle("w") == true) {
                    System.out.println("Se puede marcar enroque getCanKingCastle(\"w\"): "+getCanKingCastle("w"));
                    // ENROQUE LARGO      
                    if(getCanDoCastling("w", "long") == true) {
                        if (posx == 7) {                    
                            if (!(Chess.tilesHandler.isTile(7, 1) && Chess.tilesHandler.isTile(7, 2) && Chess.tilesHandler.isTile(7, 3))) {
                                Chess.tilesHandler.update(bool, 7, 0);
                            }
                        }
                    }
                    // ENROQUE CORTO
                    if(getCanDoCastling("w", "short") == true) {
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
                    if(Chess.tilesHandler.canKingMoveTo("b", posx + 1, posy) == true) {
                        if(!Chess.tilesHandler.isTile(posx + 1,posy)) {
                            Chess.tilesHandler.update(bool, posx + 1, posy);
                        }                   
                    }
                }
                
                
                // ARRIBA
                if(posx != 0) {
                    if(Chess.tilesHandler.canKingMoveTo("b", posx - 1, posy) == true) {
                        if(!Chess.tilesHandler.isTile(posx - 1,posy)) {
                            Chess.tilesHandler.update(bool, posx - 1, posy);
                        }                                  
                    }
                }
                
                // DERECHA
                if(posy != 7) {
                    if(Chess.tilesHandler.canKingMoveTo("b", posx, posy + 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx,posy + 1)) {                    
                            Chess.tilesHandler.update(bool, posx, posy + 1);
                        }
                    }                    
                }
                
                // IZQUIERDA
                if(posy != 0) {
                    if(Chess.tilesHandler.canKingMoveTo("b", posx, posy - 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx,posy - 1)) {
                            Chess.tilesHandler.update(bool, posx, posy - 1);          
                        }                   
                    }
                }
                
                // ABAJO DERECHA
                if(posx != 7 && posy != 7) {
                    if(Chess.tilesHandler.canKingMoveTo("b", posx + 1, posy + 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx + 1,posy + 1)) {
                           Chess.tilesHandler.update(bool, posx + 1, posy + 1);
                        }                  
                    }
                }
                
                // ABAJO IZQUIERDA
                if(posx != 7 && posy != 0) {
                    if(Chess.tilesHandler.canKingMoveTo("b", posx + 1, posy - 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx + 1,posy - 1)) {
                           Chess.tilesHandler.update(bool, posx + 1, posy - 1);
                        }        
                    }
                }
                
                // ARRIBA DERECHA
                if(posx != 0 && posy != 7) {
                    if(Chess.tilesHandler.canKingMoveTo("b", posx - 1, posy + 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx - 1,posy + 1)) {
                           Chess.tilesHandler.update(bool, posx - 1, posy + 1);
                        }
                    }
                }
                
                // ARRIBA IZQUIERDA
                if(posx != 0 && posy != 0) {
                    if(Chess.tilesHandler.canKingMoveTo("b", posx - 1, posy - 1) == true) {
                        if(!Chess.tilesHandler.isTile(posx - 1,posy - 1)) {
                           Chess.tilesHandler.update(bool, posx - 1, posy - 1);
                        }                
                    }
                }
                
                // ENROQUES
                if(getCanKingCastle("b") == true) {
                    // ENROQUE LARGO
                    if(getCanDoCastling("b", "long") == true) {                        
                        if (posx == 0) {                    
                            if (!(Chess.tilesHandler.isTile(0, 1) && Chess.tilesHandler.isTile(0, 2) && Chess.tilesHandler.isTile(0, 3))) {
                                Chess.tilesHandler.update(bool, 0, 0);
                            }
                        }
                    }

                    // ENROQUE CORTO
                    if(getCanDoCastling("b", "short") == true) {
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
                if( ((posx - 2) >= 0 && (posx - 2) <= 7)
                        && ((posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx - 2,posy + 1)) {
                        Chess.tilesHandler.update(bool, posx - 2, posy + 1);
                    }
                }
                
                if( ((posx - 2) >= 0 && (posx - 2) <= 7)
                        && ((posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx - 2,posy - 1)) {
                        Chess.tilesHandler.update(bool, posx - 2, posy - 1);
                    }
                }
                
                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ((posy + 1) >= 0 && (posy + 1) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx + 2,posy + 1)) {
                        Chess.tilesHandler.update(bool, posx + 2, posy + 1);
                    }
                }

                if( ((posx + 2) >= 0 && (posx + 2) <= 7)
                        && ((posy - 1) >= 0 && (posy - 1) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx + 2,posy - 1)) {
                        Chess.tilesHandler.update(bool, posx + 2, posy - 1);
                    }
                }

                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ((posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx + 1,posy + 2)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy + 2);
                    }
                }

                if( ((posx + 1) >= 0 && (posx + 1) <= 7)
                        && ((posy - 2) >= 0 && (posy - 2) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx + 1,posy - 2)) {
                        Chess.tilesHandler.update(bool, posx + 1, posy - 2);
                    }
                }
                
                if( ((posx - 1) >= 0 && (posx - 1) <= 7)
                        && ((posy + 2) >= 0 && (posy + 2) <= 7) ) {
                    if(!Chess.tilesHandler.isTile(posx - 1,posy + 2)) {
                        Chess.tilesHandler.update(bool, posx - 1, posy + 2);
                    }
                }
                
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

    public void setKillingTiles() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (!(x == locationX && y == locationY)) {
                    if (killingTiles.get(tilesHandler.getTile(locationX, locationY), locationX, locationY)[x][y]) {
                        Chess.TILES[x][y].setBackground(Color.RED);
                    } else if (!movingTilesget(tilesHandler.getTile(locationX, locationY), locationX, locationY)[x][y]
                            || killingTiles.get(tilesHandler.getTile(locationX, locationY), locationX, locationY)[x][y]) {
                        Chess.TILES[x][y].setEnabled(false);
                    }
                }
            }
        }
    }
    
    public boolean[][] killingTilesget(String tile, int posx, int posy) {
        boolean[][] bool = new boolean[8][8];
        return bool;
    }

    public void setBlockingCheckTiles() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (blockingCheckTiles.get(tilesHandler.getTile(locationX, locationY), locationX, locationY)[x][y]) {
                    Chess.TILES[x][y].setBackground(Color.GREEN);
                }
            }
        }
    }
    
    public boolean[][] blockingCheckTilesget(String tile, int posx, int posy) {
        boolean[][] bool = new boolean[8][8];
        return bool;
    }

    /* Funcion para controlar que el rey se ha movido
     * 0 -> Black; 1 -> White 
     */
    public void setCanKingCastle(String player, boolean bool) {
        switch (player) {
            case "b":
                this.canKingCastle[0] = bool;
                break;

            case "w":
                this.canKingCastle[1] = bool;
                break;
        }
    }

    public boolean getCanKingCastle(String player) {
        switch (player) {
            case "b":
                return this.canKingCastle[0];

            case "w":
                return this.canKingCastle[1];
        }
        return false;
    }

    /* Funcion para controlar que las torres se han movido
     * 0 -> Black; 1 -> White 
     */
    public void setCanDoCastling(String player, String which, boolean bool) {
        switch (player) {
            case "b":
                switch(which) {
                    case "short":
                        this.canDoShortCastling[0] = bool;
                        break;
                        
                    case "long":
                        this.canDoLongCastling[0] = bool;
                        break;
                }
                break;

            case "w":
                switch(which) {
                    case "short":                        
                        this.canDoShortCastling[1] = bool;
                        break;
                        
                    case "long":                        
                        this.canDoLongCastling[1] = bool;
                        break;
                }
                break;
        }
    }
    
    public boolean getCanDoCastling(String player, String which) {
        switch (player) {
            case "b":
                switch(which) {
                    case "short":
                        return this.canDoShortCastling[0];
                        
                    case "long":
                        return this.canDoLongCastling[0];
                }
                break;

            case "w":
                switch(which) {
                    case "short":
                        return this.canDoShortCastling[1];                        
                        
                    case "long":
                        return this.canDoLongCastling[1];
                }
                break;
        }
        return true;
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
