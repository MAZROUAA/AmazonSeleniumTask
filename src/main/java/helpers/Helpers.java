package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class Helpers {


    public static Properties userData=LoadProperties("config.properties");
    public static Properties addressData=LoadProperties("addressDetails.properties");

    private static Properties LoadProperties(String path)
    {
        Properties prop=new Properties();
        //stream for reading file
        try {
            FileInputStream stream=new FileInputStream(path);
            prop.load(stream);
        } catch (FileNotFoundException e) {
            System.out.println("Error occured "+e.getMessage());
        } catch (IOException e) {
            System.out.println("Error occured "+e.getMessage());
        }
        return prop;
    }

}
