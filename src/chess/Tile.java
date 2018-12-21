package chess;

import javax.swing.*;

/**
 * USELESS CLASS
 * @author Pepe
 */
public class Tile extends JButton {
    
    private int posx;
    private int posy;
    private String code;
    private Icon tileIcon;
    
    public Tile(int posx, int posy, String code, Icon tileIcon) {
        this.posx = posx;
        this.posy = posy;
        this.code = code;
        this.tileIcon = tileIcon;
    }
    
    public void setPosX(int posx) {
        this.posx = posx;
    }
    
    public int getPosX() {
        return this.posx;
    }
    
    public void setPosY(int posy) {
        this.posy = posy;
    }
    
    public int getPosY() {
        return this.posy;        
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public void setTileIcon(Icon tileIcon) {
        this.tileIcon = tileIcon;
    }
    
    public Icon getTileIcon() {
        return this.tileIcon;
    }

}
