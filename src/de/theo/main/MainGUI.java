package de.theo.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class MainGUI {

    static JFrame frame = new JFrame("GText");
    int pX, pY;

    public MainGUI() {
        TitleBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                // Get x,y and store them
                pX = me.getX();
                pY = me.getY();
            }
            public void mouseDragged(MouseEvent me) {

                frame.setLocation(frame.getLocation().x + me.getX() - pX,
                        frame.getLocation().y + me.getY() - pY);
            }
        });
        TitleBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {

                frame.setLocation(frame.getLocation().x + me.getX() - pX,
                        frame.getLocation().y + me.getY() - pY);
            }
        });
        CloseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
        MinimizeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setState(Frame.ICONIFIED);
            }
        });
    }

    public static void main(String[] args) {
        setIcon();
        frame.setSize(1280,720);
        frame.setUndecorated(true);
        frame.setContentPane(new MainGUI().MainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static void setIcon(){
        java.net.URL url = ClassLoader.getSystemResource("de/theo/icons/gtext_icon.jpg");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        frame.setIconImage(img);
    }


    private JPanel MainWindow;
    private JPanel TitleBar;
    private JLabel AppName;
    private JLabel CloseButton;
    private JLabel MinimizeButton;
}
