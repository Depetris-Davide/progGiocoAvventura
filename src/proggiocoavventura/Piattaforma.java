package proggiocoavventura;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Piattaforma extends JPanel {

    String terreno;
    int posX, posY;
    int size;

    Piattaforma(String terreno, int posX, int posY) throws IOException {
        this.terreno = terreno;
        this.posX = posX;
        this.posY = posY;
        size = 50;
        /*switch (terreno) {
            case "acquaP":
                JLabel pic = new JLabel(new ImageIcon(ImageIO.read(new File("acquaP.png"))));
                this.add(pic);
                break;
            case "acqua":
                //g.setColor(new Color(77, 109, 243));
                break;
            case "acquaS":
                //g.setColor(new Color(153, 217, 234));
                break;
            case "erbaC":
                //g.setColor(new Color(168, 230, 29));
                break;
            case "erba":
                //g.setColor(new Color(34, 177, 76));
                break;
            case "terra":
                //g.setColor(new Color(155, 90, 60));
                break;
        }*/
    }

}
