package de.theo.configs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ConfigCreate {
    public void creation()  {
        File configFile = new File("config.properties");

        try {
            Properties props = new Properties();
            props.setProperty("host", "placeholder");
            FileWriter writer = new FileWriter(configFile);
            props.store(writer, "Server IP");
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }
    }
    public int CheckForConfig(){
        File configFile = new File("config.properties");
        if(!configFile.exists()){
            return 0;
        } else {
            return 1;
        }
    }
}
