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

     public class File extends Downloadable implements Runnable {
    
    private final String dataModifiedDate;
    private double size;
    private final String name;
    private final String directory;
    private final String url;
    MainInterfaceController test;
    
    private static int getFileSize(URL url) {
    URLConnection conn = null;
    try {
        conn = url.openConnection();
        if(conn instanceof HttpURLConnection) {
            ((HttpURLConnection)conn).setRequestMethod("HEAD");
        }
        conn.getInputStream();
        return conn.getContentLength();
    } catch (IOException e) {
        throw new RuntimeException(e);
    } finally {
        if(conn instanceof HttpURLConnection) {
            ((HttpURLConnection)conn).disconnect();
        }
    }
}
    
     @Override
    public void run() {
        try {
            Start();
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public File(String url, String directory)
    {  
        super(url, directory);
        this.url = url;
        try {
            this.size = getFileSize(new URL(url));
        } catch (MalformedURLException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        setSize(size);
        this.directory = directory;
        this.name = url.substring(url.lastIndexOf('/')+1, url.length());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();  
        dataModifiedDate = dtf.format(now);   
    }

    public String getDataModifiedDate() {
        return dataModifiedDate;
    }

    public double getSize() {
        double ret = size;
        ret /= 1024;
        ret /= 1024;
        return ret;
    }

    public String getName() {
        return name;
    }

    public String getDirectory() {
        return directory;
    }

    public String getUrl() {
        return url;
    }

    
}
