/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;    
import java.time.format.DateTimeFormatter;  
import java.time.format.DateTimeFormatterBuilder;

/**
 *
 * @author omarkhaled
 * 
 */

     class File {
    
    private final String dataModifiedDate;
    private final double size;
    private final String name;
    private final String directory;
    private final String url;
    
    public File(String url, String directory, double size)
    {  
        this.url = url;
        this.size = size;
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
        return size;
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
