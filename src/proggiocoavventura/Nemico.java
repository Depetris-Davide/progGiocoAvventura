package proggiocoavventura;

import java.util.ArrayList;
import javax.swing.JLabel;

public class Nemico extends JLabel {
    
    int livello;
    int posX, posY;
    int size;
    String domanda;
    ArrayList<String> risposte;
    
    //ImageIcon player;

    Nemico(String domanda,ArrayList<String> risposte,int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.domanda = domanda;
        this.risposte = risposte;
        size = 40;
    }
}
