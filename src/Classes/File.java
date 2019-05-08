/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import downloadmanager.MainInterfaceController;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;    
import java.time.format.DateTimeFormatter;  
import java.time.format.DateTimeFormatterBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author omarkhaled
 * 
 */

     public abstract class File extends Downloadable {
    
    public abstract String getType();
    public File(String url, String directory)
    {  
        super(url, directory);
    }

    
    

    
}
