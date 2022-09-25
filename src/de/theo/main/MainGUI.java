package de.theo.main;

import com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme;
import com.formdev.flatlaf.json.Json;
import de.theo.configs.ConfigCreate;
import de.theo.configs.ReadConfig;
import de.theo.configs.UpdateConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Console;

public class MainGUI {

    public MainGUI() {

        UsernameConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new UpdateConfig().createEntry("username",Username.getText());
            }
        });
        RemoveUsername.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new UpdateConfig().deleteEntry("username");
            }
        });
        SendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bababoi_text = ChatField.getText();
                String jsonRequest =
                        "{" + "username: " + new ReadConfig().readConfig("username") + "}";
                System.out.println("username");
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

    static void initConfig(){
        ConfigCreate configCreate = new ConfigCreate();
        if(configCreate.CheckForConfig() == 0){
            configCreate.creation();
        }
    }


    private JPanel MainWindow;
    private JButton UsernameConfirm;
    private JTextField Username;
    private JButton ReadTest;
    private JButton RemoveUsername;
    private JTextField ChatField;
    private JButton SendMessage;
    private JTextArea textArea1;
}
