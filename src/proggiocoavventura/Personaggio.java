package proggiocoavventura;

import javax.swing.JLabel;

public class Personaggio extends JLabel {

    int livello;
    int posX, posY;
    int size;
    //ImageIcon player;

    Personaggio(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        size = 40;
        livello = 1;
        //player = new ImageIcon("player.png");
    }
}
