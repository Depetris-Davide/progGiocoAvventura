/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proggiocoavventura;

import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author gigga
 */
public class Nemico extends JLabel {
    
    int livello;
    int posX, posY;
    int size;
    String domanda;
    ArrayList<String> risposte;
    String terreno;
    
    //ImageIcon player;

    Nemico(String terreno,String domanda,ArrayList<String> risposte,int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.terreno = terreno;
        this.domanda = domanda;
        this.risposte = risposte;
        size = 40;
    }
}
