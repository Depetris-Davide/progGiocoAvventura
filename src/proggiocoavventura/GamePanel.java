package proggiocoavventura;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    Personaggio papa;
    Nemico prova;
    Thread gameThread;
    KeyHandler keyH;
    int mondoSizeX, mondoSizeY;
    Piattaforma[][] mondo;
    String nuovoMondo;
    int mondoX = 0, mondoY = 0;
    ArrayList<String> risposte = new ArrayList<String>();
    JButton stat;
    JPanel statPanel;
    JLabel livello = new JLabel();

    GamePanel() throws IOException {
        risposte.add("4");
        risposte.add("6");
        mondoSizeX = 26;
        mondoSizeY = 14;
        papa = new Personaggio(11, 6);
        prova = new Nemico("erba","Quanto fa 2+2?",risposte,11,7);
        keyH = new KeyHandler();
        mondo = new Piattaforma[mondoSizeY][mondoSizeX];
        stat = new JButton("Statistiche");

        creaStatPanel();

        stat.setBounds(100, 700, 400, 50);
        stat.setFocusable(false);
        stat.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
        stat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                setStatPanel();
                if (statPanel.isVisible()) {
                    statPanel.setVisible(false);
                } else {
                    statPanel.setVisible(true);
                }
            }
        });
        this.add(stat);
        this.add(statPanel);

        this.setLayout(null);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        creaMondo("mondo" + mondoY + "-" + mondoX + ".txt");

        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        while (gameThread != null) {
            try {
                update();
            } catch (IOException ex) {
            }

            repaint();
        }
    }

    public void update() throws IOException {

        if (keyH.up) {
            papa.posY -= 1;
            keyH.up = false;
        }
        if (keyH.right) {
            papa.posX += 1;
            keyH.right = false;
        }
        if (keyH.down) {
            papa.posY += 1;
            keyH.down = false;
        }
        if (keyH.left) {
            papa.posX -= 1;
            keyH.left = false;
        }
        if (papa.posX >= mondoSizeX) {
            mondoX++;
            nuovoMondo = "mondo" + mondoY + "-" + mondoX + ".txt";
            creaMondo(nuovoMondo);
            papa.posX = 0;
        } else if (papa.posX < 0) {
            mondoX--;
            nuovoMondo = "mondo" + mondoY + "-" + mondoX + ".txt";
            creaMondo(nuovoMondo);
            papa.posX = mondoSizeX - 1;
        }
        if (papa.posY >= mondoSizeY) {
            mondoY++;
            nuovoMondo = "mondo" + mondoY + "-" + mondoX + ".txt";
            creaMondo(nuovoMondo);
            papa.posY = 0;
        } else if (papa.posY < 0) {
            mondoY--;
            nuovoMondo = "mondo" + mondoY + "-" + mondoX + ".txt";
            creaMondo(nuovoMondo);
            papa.posY = mondoSizeY - 1;
        }

        if (mondo[papa.posY][papa.posX].terreno.equals("acqua")) {
            papa.posX = 11;
            papa.posY = 6;
            creaMondo("mondo0-0.txt");
            mondoX = 0;
            mondoY = 0;
        }
        
        if((prova.posX + 1 == papa.posX || prova.posX-1 == papa.posX) && prova.posY == papa.posY
            ||
            (prova.posY + 1 == papa.posY || prova.posY-1 == papa.posY) && prova.posX == papa.posX){
            System.out.println("Collisione");
        }
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < mondoSizeY; i++) {
            for (int j = 0; j < mondoSizeX; j++) {
                switch (mondo[i][j].terreno) {
                    case "acquaP":
                        g.setColor(new Color(47, 54, 153));
                        break;
                    case "acqua":
                        g.setColor(new Color(77, 109, 243));
                        break;
                    case "acquaS":
                        g.setColor(new Color(153, 217, 234));
                        break;
                    case "erbaC":
                        g.setColor(new Color(168, 230, 29));
                        break;
                    case "erba":
                        g.setColor(new Color(34, 177, 76));
                        break;
                    case "terra":
                        g.setColor(new Color(155, 90, 60));
                        break;

                }
                g.fillRect(mondo[i][j].posY * mondo[i][j].size, mondo[i][j].posX * mondo[i][j].size, mondo[i][j].size, mondo[i][j].size);
                g.setColor(Color.black);
                g.drawRect(mondo[i][j].posY * mondo[i][j].size, mondo[i][j].posX * mondo[i][j].size, mondo[i][j].size, mondo[i][j].size);
            }
        }
        g.fillRect((papa.posX * 50) + 5, (papa.posY * 50) + 5, papa.size, papa.size);
        g.setColor(Color.red);
        g.fillRect((prova.posX * 50) + 5, (prova.posY * 50) + 5, prova.size,prova.size );
    }

    public void creaStatPanel() {
        statPanel = new JPanel();
        statPanel.setBounds(0, 0, 400, 400);
        statPanel.setVisible(false);

        livello.setText("Livello: " + papa.livello);
        livello.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
        livello.setBounds(0, 0, 400, 50);
        livello.setBackground(Color.yellow);
        livello.setOpaque(true);
        statPanel.add(livello);
    }

    public void setStatPanel() {
        livello.setText("Livello: " + papa.livello);
    }

    public void creaMondo(String cambiaMondo) throws FileNotFoundException, IOException {
        BufferedReader filein = new BufferedReader(new FileReader(cambiaMondo));
        String s;
        System.out.println(cambiaMondo);

        for (int i = 0; i < mondoSizeY; i++) {
            for (int j = 0; j < mondoSizeX; j++) {
                s = filein.readLine();
                mondo[i][j] = new Piattaforma(s, i, j);
            }
        }
    }

}
