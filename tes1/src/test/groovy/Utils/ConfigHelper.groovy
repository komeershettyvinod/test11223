package Utils

import org.testng.Assert;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/**
 * This class provides methods to read values of keys from either app.properties or sys.properties from "ConfigFiles" folder
 */
public class ConfigHelper
{
    String rootDir = new File(".").getCanonicalPath()
    private String fileSeperator = System.getProperty("file.separator");

    String fileName;
    public static Properties properties;

    ConfigHelper(String fileName){
        properties = new Properties();
        try {
            this.fileName=fileName;
            FileInputStream fis = new FileInputStream(gettestResourcesFileFolderPath()+fileName);
            properties.load(fis);
            fis.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

    }
    /**
     *
     * This method is used to get the location of 'resources' folder
     *
     * @return, Returns the path of 'test resources' folder
     */
    private String gettestResourcesFileFolderPath()
    {
        return System.getProperty("user.dir")+"/src/test/resources/".replace('/',fileSeperator)
    }

    /**
     * Returns the value of given property from either sys.properties or app.properties file
     * @param key - ConfigParamvalue that requires to be returned from Config.properties file
     * @return - return ConfigValue
     */
    public String getProperty(String key)
    {
        String value ="";
        if(key!="")
        {
            try
            {
                if(!properties.getProperty(key).trim().isEmpty())
                    value = properties.getProperty(key).trim();
            }
            catch(NullPointerException e)
            {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            System.out.println("Please  provide proper key/value pair");
        }
        return value;
    }

}
