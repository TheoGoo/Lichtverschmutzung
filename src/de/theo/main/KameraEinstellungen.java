package de.theo.main;

import de.theo.configs.UpdateConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KameraEinstellungen {
    JPanel Einstellungen;
    private JButton okBrennweite;
    private JButton okPixel;
    private JTextField brennweiteEingabe;
    private JTextField pixelEingabe;

    public KameraEinstellungen() {
        okBrennweite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateConfig config = new UpdateConfig();
                config.createEntry("Brennweite",brennweiteEingabe.getText());
            }
        });
        okPixel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateConfig config = new UpdateConfig();
                config.createEntry("Pixel",pixelEingabe.getText());
            }
        });
    }
}
