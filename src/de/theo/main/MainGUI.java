package de.theo.main;

import com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme;
import de.theo.OpenCV.HoughCirclesRun;
import de.theo.configs.ConfigCreate;
import de.theo.configs.ReadConfig;
import de.theo.configs.UpdateConfig;
import org.opencv.core.Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;


public class MainGUI {

    public MainGUI() {
        ReadConfig config = new ReadConfig();
        DurchmesserPixel.setText(config.readConfig("GroesseObjekt"));
        FlaechenHelligkeit.setText(config.readConfig("MagnitudeStern"));

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
                config.createEntry("Quadratbogensekunden", QuadratbogensekundenBerechnen());
                config.createEntry("MagnitudeStern",FlaechenHelligkeit.getText());
                config.createEntry("FlaechenhelligkeitStern",FlaechenHelligkeitBerechnen());
                config.createEntry("GroesseObjekt",DurchmesserPixel.getText());
                FlaechenHelligkeitOutput.setText("Die Flächenhelligkeit des Sterns beträgt: " + FlaechenHelligkeitBerechnen() + " mag/arcsec^2");
                //Bilderkennung
                ReadConfig configRead = new ReadConfig();
                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                new HoughCirclesRun().run();
                pixelWertAusgabe.setText("Pixelwert(Durchschnitt) des Hintergrundes: "+configRead.readConfig("pixelValueHintergrund")+" | Pixelwert(Durchschnitt) Stern/Vordergrund: "+configRead.readConfig("pixelValueVordergrund"));


            }
        });
        imageChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

    private String QuadratbogensekundenBerechnen(){
        ReadConfig config = new ReadConfig();
        BigDecimal quadratbogensekunden = new BigDecimal(DurchmesserPixel.getText()).multiply(new BigDecimal(config.readConfig("Winkelsekunde"))).divide(new BigDecimal("2"),4,BigDecimal.ROUND_HALF_UP).pow(2).multiply(new BigDecimal(Math.PI));
        //https://archiv.astronomicum.de/modules.php?name=News&file=article&sid=108
        return quadratbogensekunden.toString();
    }

    private String FlaechenHelligkeitBerechnen(){
        ReadConfig config = new ReadConfig();
        double FHm = Double.parseDouble(config.readConfig("MagnitudeStern")) + (2.5*Math.log10(Double.parseDouble(config.readConfig("Quadratbogensekunden"))));
        //https://archiv.astronomicum.de/modules.php?name=News&file=article&sid=108
        return FHm+"";
    }

    private JPanel MainWindow;
    private JButton einstellungenButton;
    private JButton PixelFlaecheOk;
    private JTextField DurchmesserPixel;
    private JTextField FlaechenHelligkeit;
    private JTextField FlaechenHelligkeitOutput;
    private JButton imageChooser;
    private JTextField pixelWertAusgabe;
}
