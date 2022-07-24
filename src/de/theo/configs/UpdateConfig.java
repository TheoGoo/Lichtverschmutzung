package de.theo.configs;

import java.io.*;
import java.util.Properties;

public class UpdateConfig {

    public void deleteEntry(String entry){
        File configFile = new File("config.properties");
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            props.remove(entry);
            FileWriter writer = new FileWriter(configFile);
            props.store(writer, entry + " removed");
            writer.close();
        } catch (FileNotFoundException ex){
            // File does not exist
        } catch (IOException ex){
            // I/O error
        }
    }

    public void createEntry(String entry, String value) {
        updateEntry(entry, value);
    }
    public void updateEntry(String entry, String value) {
        File configFile = new File("config.properties");
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            props.setProperty(entry, value);
            FileWriter writer = new FileWriter(configFile);
            props.store(writer, entry + " settings");
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }
    }
}
