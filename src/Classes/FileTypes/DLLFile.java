/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.FileTypes;

/**
 *
 * @author zulam
 */
public class DLLFile extends Classes.File {
    public DLLFile(String url, String directory) { 
        super(url, directory);
    }
    
    public String getType() { 
       return "DLL File"; 
    }
    
}
