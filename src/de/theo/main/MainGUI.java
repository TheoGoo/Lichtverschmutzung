package de.theo.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class MainGUI {

    //create JFrame in class to make it accessible to outside the main method
    static JFrame frame = new JFrame("GText");
    //stores initial position of the mouse cursor (can be used for moving windows and maybe more in the future)
    int pX, pY;

    public MainGUI() {

        /*
        Move the entire window around without using the default "decoration"
         */
        TitleBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                // Get x,y and store them
                pX = me.getX();
                pY = me.getY();
            }
        });
        TitleBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {
                //calculate new window position
                frame.setLocation(frame.getLocation().x + me.getX() - pX,
                        frame.getLocation().y + me.getY() - pY);
            }
        });
        /*
        Basic functionality like minimize and close buttons
         */
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
        /*
        Temporary solution for resizing the main window
         */
        MainWindow.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                //get curser position and set as size
                frame.setSize(e.getX(),e.getY());
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

    /*
    Setting the icon for the task bar
     */
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
    private JPanel WindowContent;
}
