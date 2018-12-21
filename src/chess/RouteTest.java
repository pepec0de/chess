package chess;

/**
 * A class for studying the algorythim to solve the direction task
 * @author Pepe
 */
public class RouteTest {

    public RouteTest(int posx, int posy, int killPosx, int killPosy) {
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
        
        System.out.println("fDirX: " + fDirX + " fDirY: " + fDirY);
        switch(fDirX) {
            case -1:
                switch(fDirY) {
                    case -1:
                        System.out.println("ARRIBA IZQUIERDA");
                        break;
                        
                    case 0:
                        System.out.println("ARRIBA");
                        break;
                        
                    case 1:
                        System.out.println("ARRIBA DERECHA");
                        
                        break;
                }
                break;
                
            case 0:
                switch(fDirY) {
                    case -1:
                        System.out.println("IZQUIERDA");
                        break;
                        
                    case 0:
                        System.out.println("IMPOSIBLE");
                        break;
                        
                    case 1:
                        System.out.println("DERECHA");
                        break;
                }
                break;
                
            case 1:
                switch(fDirY) {
                    case -1:
                        System.out.println("ABAJO IZQUIERDA");
                        break;
                        
                    case 0:
                        System.out.println("ABAJO");
                        break;
                        
                    case 1:
                        System.out.println("ABAJO DERECHA");
                        break;
                }
                break;
        }
    }
    public static void main(String[] args) {
        new RouteTest(4, 2, 0, 2);
    }
}
