package de.theo.main;

import de.theo.configs.ReadConfig;
import de.theo.configs.UpdateConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class KameraEinstellungen {
    JPanel Einstellungen;
    private JTextField brennweiteEingabe;
    private JTextField pixelEingabe;
    private JButton okButton;

    public KameraEinstellungen() {

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateConfig config = new UpdateConfig();
                config.createEntry("Brennweite",brennweiteEingabe.getText());
                config.createEntry("Pixel",pixelEingabe.getText());
                config.createEntry("Winkelsekunde",Abbildungsmassstab());
            }
        });
    }

    private String Abbildungsmassstab(){
        ReadConfig readConfig = new ReadConfig();
        BigDecimal bigDecimal = new BigDecimal("206").multiply(new BigDecimal(readConfig.readConfig("Pixel"))).divide(new BigDecimal(readConfig.readConfig("Brennweite")),4,BigDecimal.ROUND_HALF_UP);
        String abbildungsmassstab = bigDecimal.toString();
        /*
        ---------------------------------------------------------
        |https://www.sterngucker.de/astrofotografie/sampling/   |
        ---------------------------------------------------------
         */
        return abbildungsmassstab;
    }
}
