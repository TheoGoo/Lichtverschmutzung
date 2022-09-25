package de.theo.main;

import com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme;
import de.theo.configs.ConfigCreate;
import de.theo.configs.ReadConfig;
import de.theo.configs.UpdateConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;


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
        PixelFlaecheOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateConfig config = new UpdateConfig();
                config.createEntry("FlaecheAbbildungsmasstab",FlaecheAbbildungsmasstab());
                config.createEntry("Öffnungsverhältnis",FlaechenHelligkeit.getText());

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

    private String FlaecheAbbildungsmasstab(){
        ReadConfig config = new ReadConfig();
        BigDecimal flaecheAbbildungsmasstab = new BigDecimal(DurchmesserPixel.getText()).multiply(new BigDecimal(config.readConfig("Abbildungsmassstab"))).divide(new BigDecimal("2"),4,BigDecimal.ROUND_HALF_UP).pow(2).multiply(new BigDecimal(Math.PI));
        //https://archiv.astronomicum.de/modules.php?name=News&file=article&sid=108
        return flaecheAbbildungsmasstab.toString();
    }

    private String FlaechenHelligkeit(){
        ReadConfig config = new ReadConfig();
        BigDecimal FHm = new BigDecimal("-2.5").multiply(new BigDecimal(FlaechenHelligkeit.getText()).divide(new BigDecimal(config.readConfig("FlaecheAbbildungsmasstab")),4,BigDecimal.ROUND_HALF_UP));

        //https://archiv.astronomicum.de/modules.php?name=News&file=article&sid=108
        return null;
    }



    private JPanel MainWindow;
    private JButton einstellungenButton;
    private JButton PixelFlaecheOk;
    private JTextField DurchmesserPixel;
    private JTextField FlaechenHelligkeit;
}
