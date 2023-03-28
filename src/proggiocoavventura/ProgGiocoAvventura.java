package proggiocoavventura;

import java.awt.Color;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ProgGiocoAvventura extends JFrame {

    JFrame frame;
    JPanel menuPanel;
    JButton play, exit;
    GamePanel panel;

    ProgGiocoAvventura() throws IOException {

        frame = new JFrame("TITOLO");
        menuPanel = new JPanel();
        play = new JButton("Gioca");
        exit = new JButton("Esci");
        panel = new GamePanel();

        frame.setResizable(false);
        frame.setBounds(110, 20, 1315, 787);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBackground(Color.black);

        /*menuPanel.setBackground(Color.green);
        menuPanel.setLayout(null);

        play.setBounds(100, 100, 250, 100);
        play.setContentAreaFilled(false);
        play.setBorderPainted(false);
        play.setFocusPainted(false);
        //font utilizzabili
        //Engravers MT
        play.setFont(new Font("Engravers MT", Font.PLAIN, 50));
        play.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                play.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                play.setForeground(null);
            }
        });
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                menuPanel.setVisible(false);
            }
        });
        menuPanel.add(play);
        exit.setBounds(100, 300, 250, 100);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        //font utilizzabili
        //Engravers MT
        exit.setFont(new Font("Engravers MT", Font.PLAIN, 50));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                exit.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                exit.setForeground(null);
            }
        });
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                System.exit(0);
            }
        });
        menuPanel.add(exit);
        frame.add(menuPanel);
        menuPanel.setVisible(true);
        menuPanel.setFocusable(false);*/
        frame.add(panel);

        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new ProgGiocoAvventura();
    }
}
