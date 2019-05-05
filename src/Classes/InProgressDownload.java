
package Classes;
import java.io.*;
import java.io.IOException;
import java.net.URL;
public class InProgressDownload {
    private File file;
    private double currentSize;
    
    public InProgressDownload(File file)
    {
        this.file = file;
    }
    public double getCurrentSize()
    {
        
        return currentSize;
    }
   void llaa()
   {
       String FILE_URL = null;
       try(BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream()))
        {
            String fileName = FILE_URL.substring(FILE_URL.lastIndexOf('/')+1, FILE_URL.length());
            System.out.println(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                
            }  
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
}
}

