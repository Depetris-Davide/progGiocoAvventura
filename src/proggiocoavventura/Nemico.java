package proggiocoavventura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Nemico extends JLabel {

    int posX, posY;
    int size;
    String domanda;
    ArrayList<JButton> pulsanti;
    ArrayList<Boolean> risposteCo;
    boolean dead;

    //ImageIcon player;
    Nemico(String domanda, ArrayList<String> risposte, ArrayList<Boolean> risposteC, int posX, int posY, JPanel pannello, Personaggio pers) {
        pulsanti = new ArrayList<JButton>();
        this.posX = posX;
        this.posY = posY;
        this.domanda = domanda;
        risposteCo = new ArrayList<Boolean>();
        for (Boolean rispostaC : risposteC) {
            risposteCo.add(rispostaC);
        }
        int i = 0;
        for (String risposta : risposte) {
            pulsanti.add(new JButton(risposta));
            pulsanti.get(i).setFocusable(false);
            int k = i;
            pulsanti.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (risposteCo.get(k)) {
                        pers.indovinate += 1;
                        pannello.removeAll();
                        dead = true;
                        pannello.setVisible(false);
                    } else {
                        pers.errori += 1;
                    }
                    pers.mov = true;
                }
            });
            i++;
        }
        size = 40;
    }
}
