package de.theo.main;

import com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme;
import de.theo.configs.ConfigCreate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainGUI {

    public MainGUI() {

        einstellungenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlatXcodeDarkIJTheme.setup();
                try {
                    UIManager.setLookAndFeel( new FlatXcodeDarkIJTheme() );
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
                JFrame frame = new JFrame("Einstellungen");
                frame.setContentPane(new KameraEinstellungen().Einstellungen);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                java.net.URL url = ClassLoader.getSystemResource("de/theo/icons/Lichtverschmutzung.jpg");
                Toolkit kit = Toolkit.getDefaultToolkit();
                Image img = kit.createImage(url);
                frame.setIconImage(img);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        initGUI();
        initConfig();

    }

    static void initGUI(){
        FlatXcodeDarkIJTheme.setup();
        try {
            UIManager.setLookAndFeel( new FlatXcodeDarkIJTheme() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        JFrame frame = new JFrame("Lichtverschmutzung | Theo Gosch");
        frame.setSize(1280,720);
        frame.setContentPane(new MainGUI().MainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        java.net.URL url = ClassLoader.getSystemResource("de/theo/icons/Lichtverschmutzung.jpg");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        frame.setIconImage(img);
        frame.setVisible(true);
    }

    static void initConfig(){
        ConfigCreate configCreate = new ConfigCreate();
        if(configCreate.CheckForConfig() == 0){
            configCreate.creation();
        }
    }


    private JPanel MainWindow;
    private JButton einstellungenButton;
}
