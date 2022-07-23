package de.theo.main;

import com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme;

import javax.swing.*;
import java.awt.*;

public class MainGUI {

    public MainGUI() {

    }

    public static void main(String[] args) {
        FlatXcodeDarkIJTheme.setup();
        try {
            UIManager.setLookAndFeel( new FlatXcodeDarkIJTheme() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        JFrame frame = new JFrame("GText");
        frame.setSize(1280,720);
        frame.setContentPane(new MainGUI().MainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        java.net.URL url = ClassLoader.getSystemResource("de/theo/icons/gtext_icon.jpg");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        frame.setIconImage(img);
        frame.setVisible(true);
    }


    private JPanel MainWindow;
}
