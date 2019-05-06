/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import downloadmanager.MainInterfaceController;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URL;
import java.io.*;
import java.net.URLConnection;
import javafx.application.Platform;
import javafx.concurrent.Task;

/**
 *
 * @author zulam
 */
public class Downloadable {
    
    private final String URL;
    private final String Path;
    private String status = "Paused";
    private double speed;
    private double fileSize;
    private long downloadedBytes;
    private long timeleft;
    BufferedInputStream inputStream;
    BufferedOutputStream outputStream;
    BufferedInputStream BIS;
    FileOutputStream FIS;
    URL urlCore;
    
    public Downloadable(String _url, String _path) {
        _path += _url.substring(_url.lastIndexOf('/')+1, _url.length());
        URL = _url;
        Path = _path;
        downloadedBytes = 0;
    }
    
    public void setSize(double s) {
        fileSize = s;
    }
    
    public String getStatus() {
        return status;
    }
    
    public double getspeed() {
        return speed;
    }
    
    public String Left(){ 
        String ret = "";
        ret += timeleft;
        ret += " secs";
        return ret;
    }
    
    public void Start() throws IOException { 
        urlCore = new URL(URL);
        URLConnection connection = urlCore.openConnection();
        java.io.File file = new java.io.File(Path);
        if (file.exists()) {
            downloadedBytes = file.length();
            connection.setRequestProperty("Range", "bytes=" + downloadedBytes + "-");
            outputStream = new BufferedOutputStream(new FileOutputStream(file, true));
        }
        else {
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
        }
        connection.connect();
        inputStream = new BufferedInputStream(connection.getInputStream());
        byte[] buffer = new byte[1024*8];
        int bytesCount;
        BIS = new BufferedInputStream(urlCore.openStream());
        FIS = new FileOutputStream(file);
        int count = 0;
        status = "Downloading";
        while ((count = BIS.read(buffer, 0, 1024)) != -1) {
            downloadedBytes += count;
            long before = System.nanoTime();
            FIS.write(buffer, 0, count);
            long after = System.nanoTime();
            double diff =  (double)after - (double)before;
            double mul = (count * 1e9) / diff;
            mul /= 1024;
            mul /= 1024;
            speed = mul;
            double l = fileSize - downloadedBytes;
            l /= 1024;
            timeleft = (long)(l / speed);
        }
        BIS.close();
        FIS.close();
        inputStream.close();
        outputStream.close();
    }

            
}
