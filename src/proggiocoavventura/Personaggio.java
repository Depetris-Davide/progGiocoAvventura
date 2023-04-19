package proggiocoavventura;

import javax.swing.JLabel;

public class Personaggio extends JLabel {

    int indovinate;
    int posX, posY;
    int size;
    boolean mov;
    int errori;
    //ImageIcon player;

    Personaggio(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        size = 40;
        indovinate = 0;
        mov = true;
        errori = 0;
        //player = new ImageIcon("player.png");
    }
}
