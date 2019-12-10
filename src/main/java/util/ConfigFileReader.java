package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    Properties prop;

    public ConfigFileReader(){
        String  ConfigFileName ="testconfig.properties";
        FileInputStream inputFileStream= null;

        this.prop = new Properties();
        String configFilePath = System.getProperty("user.dir")+"/" +
                "src/main/resources/config" + "/" +ConfigFileName;
            System.out.println("properties file name is "+configFilePath);
        try{
            inputFileStream = new FileInputStream(configFilePath);
            System.out.println(inputFileStream);
            prop.load(inputFileStream);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getProperty(String propertyName){
        System.out.println(propertyName);
        return this.prop.getProperty(propertyName);
    }

}

