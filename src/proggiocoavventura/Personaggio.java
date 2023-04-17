package proggiocoavventura;

import javax.swing.JLabel;

public class Personaggio extends JLabel {

    int livello;
    int posX, posY;
    int size;
    boolean mov;
    //ImageIcon player;

    Personaggio(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        size = 40;
        livello = 1;
        mov = true;
        //player = new ImageIcon("player.png");
    }
}
