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
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    ArrayList<String> risposteTemp = new ArrayList<String>();
    ArrayList<Boolean> risposteCTemp = new ArrayList<Boolean>();

    Personaggio papa;
    LinkedList<Nemico> nemici;
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
    JPanel domandaBG = new JPanel();
    JLabel domandaText = new JLabel();
    LinkedList<JButton> rispostePuls;
    LinkedList<Boolean> risposteC;

    GamePanel() throws IOException {
        rispostePuls = new LinkedList<JButton>();
        risposteC = new LinkedList<Boolean>();

        nemici = new LinkedList<Nemico>();
        domandaBG.setBounds(0, 0, 1300, 200);
        domandaText.setBounds(0, 0, 1300, 150);
        risposte.add("4");
        risposte.add("6");
        mondoSizeX = 26;
        mondoSizeY = 14;
        papa = new Personaggio(11, 6);
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
        creaMondo(0, 0);

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
        boolean trov = false;
        if (papa.mov) {
            if (keyH.up) {
                papa.posY -= 1;
                if ((papa.posY < mondoSizeY && papa.posY >= 0) && (papa.posX < mondoSizeX && papa.posX >= 0)) {
                    if (mondo[papa.posY][papa.posX].terreno.equals("muro")) {
                        papa.posY += 1;
                    }
                }
                for (int i = 0; i < nemici.size() && !trov; i++) {
                    if (papa.posX == nemici.get(i).posX && papa.posY == nemici.get(i).posY) {
                        papa.posY += 1;
                        trov = true;
                        domanda(nemici.get(i));
                        papa.mov = false;
                    }

                }

                trov = false;
                keyH.up = false;
            }
            if (keyH.right) {
                papa.posX += 1;
                if ((papa.posY < mondoSizeY && papa.posY >= 0) && (papa.posX < mondoSizeX && papa.posX >= 0)) {
                    if (mondo[papa.posY][papa.posX].terreno.equals("muro")) {
                        papa.posX -= 1;
                    }
                }
                for (int i = 0; i < nemici.size() && !trov; i++) {
                    if (papa.posX == nemici.get(i).posX && papa.posY == nemici.get(i).posY) {
                        papa.posX -= 1;
                        trov = true;
                        domanda(nemici.get(i));
                        papa.mov = false;
                    }
                }

                trov = false;
                keyH.right = false;
            }
            if (keyH.down) {
                papa.posY += 1;
                if ((papa.posY < mondoSizeY && papa.posY >= 0) && (papa.posX < mondoSizeX && papa.posX >= 0)) {
                    if (mondo[papa.posY][papa.posX].terreno.equals("muro")) {
                        papa.posY -= 1;
                    }

                }
                for (int i = 0; i < nemici.size() && !trov; i++) {
                    if (papa.posX == nemici.get(i).posX && papa.posY == nemici.get(i).posY) {
                        papa.posY -= 1;
                        trov = true;
                        domanda(nemici.get(i));
                        papa.mov = false;
                    }
                }

                trov = false;
                keyH.down = false;
            }
            if (keyH.left) {
                papa.posX -= 1;
                if ((papa.posY < mondoSizeY && papa.posY >= 0) && (papa.posX < mondoSizeX && papa.posX >= 0)) {
                    if (mondo[papa.posY][papa.posX].terreno.equals("muro")) {
                        papa.posX += 1;
                    }
                }
                for (int i = 0; i < nemici.size() && !trov; i++) {
                    if (papa.posX == nemici.get(i).posX && papa.posY == nemici.get(i).posY) {
                        papa.posX += 1;
                        trov = true;
                        domanda(nemici.get(i));
                        papa.mov = false;
                    }
                }

                trov = false;
                keyH.left = false;
            }
        }
        if (papa.posX >= mondoSizeX) {
            mondoX++;
            nuovoMondo = "mondo" + mondoY + "-" + mondoX + ".txt";
            creaMondo(mondoY, mondoX);
            papa.posX = 0;
        } else if (papa.posX < 0) {
            mondoX--;
            nuovoMondo = "mondo" + mondoY + "-" + mondoX + ".txt";
            creaMondo(mondoY, mondoX);
            papa.posX = mondoSizeX - 1;
        }
        if (papa.posY >= mondoSizeY) {
            mondoY++;
            nuovoMondo = "mondo" + mondoY + "-" + mondoX + ".txt";
            creaMondo(mondoY, mondoX);
            papa.posY = 0;
        } else if (papa.posY < 0) {
            mondoY--;
            nuovoMondo = "mondo" + mondoY + "-" + mondoX + ".txt";
            creaMondo(mondoY, mondoX);
            papa.posY = mondoSizeY - 1;
        }

        if (mondo[papa.posY][papa.posX].terreno.equals("acqua")) {
            papa.posX = 11;
            papa.posY = 6;
            creaMondo(0, 0);
            mondoX = 0;
            mondoY = 0;
        }

        for (int i = 0; i < nemici.size() && !trov; i++) {
            if (nemici.get(i).dead) {
                nemici.remove(i);
            }
        }
        trov = false;
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
                    case "muro":
                        g.setColor(new Color(90, 90, 90));
                        break;
                }
                g.fillRect(mondo[i][j].posY * mondo[i][j].size, mondo[i][j].posX * mondo[i][j].size, mondo[i][j].size, mondo[i][j].size);
                g.setColor(Color.black);
                g.drawRect(mondo[i][j].posY * mondo[i][j].size, mondo[i][j].posX * mondo[i][j].size, mondo[i][j].size, mondo[i][j].size);
            }
        }
        g.fillRect((papa.posX * 50) + 5, (papa.posY * 50) + 5, papa.size, papa.size);
        g.setColor(Color.red);
        for (Nemico nemico : nemici) {
            g.fillRect((nemico.posX * 50) + 5, (nemico.posY * 50) + 5, nemico.size, nemico.size);
        }
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

    public void creaMondo(int MondoY, int MondoX) throws IOException {
        boolean errNem = false, errMondo = false;
        String s = "";
        String[] tok;
        String[] tokRisp;
        boolean risp;
        String domanda = "";

        BufferedReader fileinMondo = null;
        try {
            fileinMondo = new BufferedReader(new FileReader("mondo" + MondoY + "-" + MondoX + ".txt"));
        } catch (FileNotFoundException ex) {
            errMondo = true;
        }
        BufferedReader fileinNemico = null;
        try {
            fileinNemico = new BufferedReader(new FileReader("nemico" + MondoY + "-" + MondoX + ".txt"));
        } catch (FileNotFoundException ex) {
            errNem = true;
        }

        int posX = 0, posY = 0;

        if (!nemici.isEmpty()) {
            nemici.clear();

        }

        if (!errMondo) {
            for (int i = 0; i < mondoSizeY; i++) {
                for (int j = 0; j < mondoSizeX; j++) {
                    s = fileinMondo.readLine();
                    mondo[i][j] = new Piattaforma(s, i, j);
                }
            }
            fileinMondo.close();
        } else {
            for (int i = 0; i < mondoSizeY; i++) {
                for (int j = 0; j < mondoSizeX; j++) {
                    mondo[i][j] = new Piattaforma("acqua", i, j);
                }
            }

        }
        if (!errNem) {
            do {
                s = fileinNemico.readLine();
                if (s != null) {
                    tok = s.split("\\®");
                    for (int i = 0; i < 5; i++) {

                        switch (i) {
                            case 0:
                                domanda = tok[0];
                                break;
                            case 1:
                                tokRisp = tok[1].split("\\|");
                                for (String singRisp : tokRisp) {
                                    risposteTemp.add(singRisp);
                                }
                                break;
                            case 2:
                                tokRisp = tok[2].split("\\|");

                                for (String singRisp : tokRisp) {
                                    risp = Boolean.parseBoolean(singRisp);
                                    risposteCTemp.add(risp);
                                }
                                break;
                            case 3:
                                posX = Integer.parseInt(tok[3]);
                                break;
                            case 4:
                                posY = Integer.parseInt(tok[4]);
                                break;
                        }
                    }
                    nemici.add(new Nemico(domanda, risposteTemp, risposteCTemp, posX, posY, domandaBG, papa));
                    risposteTemp.clear();
                }
            } while (s != null);
            fileinNemico.close();
        }

    }

    public void domanda(Nemico nemico) {
        if (!rispostePuls.isEmpty()) {
            rispostePuls.clear();
        }

        for (int i = 0; i < nemico.pulsanti.size(); i++) {
            rispostePuls.add(nemico.pulsanti.get(i));
            rispostePuls.get(i).setBounds(110 * i + 500, 50, 100, 50);
            domandaBG.add(rispostePuls.get(i));
        }

        if (papa.livello >= 1 && papa.livello <= 5) {
            domandaText.setText(nemico.domanda);
        }
        domandaBG.add(domandaText);
        domandaBG.setVisible(true);

        this.add(domandaBG);
    }

}
