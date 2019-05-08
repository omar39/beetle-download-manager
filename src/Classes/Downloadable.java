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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import java.time.format.DateTimeFormatter;  
import java.time.format.DateTimeFormatterBuilder;

/**
 *
 * @author zulam
 */
public class Downloadable implements Runnable {
    private final String Name;
    private final String dataModifiedDate;
    private final String URL;
    private final String Path;
    private String status = "Paused";
    private boolean paused = false;
    private double speed;
    private double fileSize;
    private long downloadedBytes;
    private long timeleft;
    BufferedInputStream BIS;
    FileOutputStream FIS;
    URL urlCore;
    
    
     @Override
    public void run() {
        try {
            Start();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Downloadable(String _url, String _path) {
        _path += _url.substring(_url.lastIndexOf('/')+1, _url.length());
        URL = _url;
        Path = _path;
        downloadedBytes = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();  
        dataModifiedDate = dtf.format(now);   
        Name = URL.substring(URL.lastIndexOf('/')+1, URL.length());
        try {
            fileSize = getFileSize(new URL(URL));
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void Start() throws IOException { 
        urlCore = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection)urlCore.openConnection();
        java.io.File file = new java.io.File(Path);
        if (file.exists()) {
            downloadedBytes = file.length();
            connection.setRequestProperty("Range", "bytes="+(file.length())+"-");
            System.out.println(downloadedBytes);
        }
        else {
             connection.setRequestProperty("Range", "bytes=" + downloadedBytes + "-");
        }
        connection.connect();
        byte[] buffer = new byte[1024*8];
        int bytesCount;
        BIS = new BufferedInputStream(connection.getInputStream());
        if (downloadedBytes == 0)
            FIS = new FileOutputStream(Path);
        else
            FIS = new FileOutputStream(Path, true);
        int count = 0;
        status = "Downloading";
        while ((count = BIS.read(buffer, 0, 1024)) != -1) {
            if (paused)
                break;
            downloadedBytes += count;
            long before = System.nanoTime();
            FIS.write(buffer, 0, count);
            long after = System.nanoTime();
            double diff =  (double)after - (double)before;
            double mul = (count * 1e9) / diff;
            speed = mul;
            double l = fileSize - downloadedBytes;
            l /= 1024;
            timeleft = (long)(l / (speed / (1024 * 1024)));
        }
        if (downloadedBytes == fileSize)
            setStatus("Finished");
        BIS.close();
        FIS.close();
    }
    
    public void Resume() { 
        if (!status.equals("Paused"))
            return;
        status = "Downloading";
        paused = false;
        try {
            Start();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void Pause() {
        paused = true;
        if (status.equals("Downloading"))
            status = "Paused";
    }
    
    
    private static int getFileSize(URL url) {
    URLConnection conn = null;
    try {
        conn = url.openConnection();
        if(conn instanceof HttpURLConnection) {
            ((HttpURLConnection)conn).setRequestMethod("HEAD");
        }
        conn.getInputStream();
        return conn.getContentLength();
    } 
    catch (IOException e) {
        throw new RuntimeException(e);
    } 
    finally {
        if(conn instanceof HttpURLConnection) {
            ((HttpURLConnection)conn).disconnect();
            }
        }
    }
    
    
    public String getSizeString() {
        String res = "";
        String[] sizes = { "B", "KB", "MB", "GB", "TB" };
        int idx = 0;
        long sz = (long)downloadedBytes;
        while (sz >= 1024 && idx < sizes.length - 1) { 
            idx++;
            sz /= 1204;
        }
        res += String.valueOf(sz);
        res += sizes[idx];
        res += " / ";
        idx = 0;
        sz = (long)fileSize;
        while (sz >= 1024 && idx < sizes.length - 1) { 
            idx++;
            sz /= 1204;
        }
        res += String.valueOf(sz);
        res += sizes[idx];
        return res;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String _s) {
        status = _s;
    }
    
    public double getspeed() {
        return speed;
    }
    
    
    public String getDataModifiedDate() {
        return dataModifiedDate;
    }
    
    
    public String getName() {
        return Name;
    }

    public String getDirectory() {
        return Path;
    }

    public String getUrl() {
        return URL;
    }
    
    public double getSize() {
        double ret = fileSize;
        ret /= 1024;
        ret /= 1024;
        return ret;
    }
    
    
    public String getSpeedString() { 
        if (!status.equals("Downloading"))
            return "";
        String res = "";
        long tmp = (long)speed;
        tmp /= 1024;
        tmp /= 1024;
        if (tmp <= 1024)
            res += String.valueOf(tmp) + "KB/s";
        else {
            res += String.valueOf(tmp / 1024) + ".";
            res += String.valueOf(tmp % 1024) + "MB/s";
        }
        return res;
    }
    
    public String getStringLeftTime() {
        String res = "";
        long hours = timeleft / 3600;
        long minutes = (timeleft % 3600) / 60;
        long seconds = timeleft % 60;
        if (hours > 0)
            res += String.valueOf(hours) + " Hours ";
        if (minutes > 0)
            res += String.valueOf(minutes) + " Minutes ";
        if (seconds > 0)
            res += String.valueOf(seconds) + " Seconds ";
        return res;
    }
        
}
