
package Classes;
import java.io.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class InProgressDownload {
    private File downloadFile;
    private double currentSize;
    
    public InProgressDownload(File downloadFile)
    {
        this.downloadFile = downloadFile;
    }
    public double getCurrentSize()
    {
        
        return currentSize;
    }
    // this function takes URL and the desired directory
    // and download it
   public void downloadFile(String fileURL, String saveDir) throws IOException {
        
       long downloadedLength = 0;
        saveDir += fileURL.substring(fileURL.lastIndexOf('/')+1, fileURL.length());
        java.io.File file = new java.io.File(saveDir);
        URL url = new URL(fileURL);
 
        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        saveDir += fileURL.substring(fileURL.lastIndexOf('/')+1, fileURL.length());

        URLConnection connection = url.openConnection();
        
        if(file.exists()){
            downloadedLength = file.length();
            connection.setRequestProperty("Range", "bytes=" + downloadedLength + "-");
            outputStream = new BufferedOutputStream(new FileOutputStream(file, true));

        }else{
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
        }

        connection.connect();

        inputStream = new BufferedInputStream(connection.getInputStream());


        byte[] buffer = new byte[1024*8];
        int byteCount;

       BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(file);
            int count = 0;
            while ((count = bis.read(buffer, 0, 1024)) != -1)
            {
                fis.write(buffer, 0, count);           
            }
            bis.close();
            fis.close(); 
    }
}

