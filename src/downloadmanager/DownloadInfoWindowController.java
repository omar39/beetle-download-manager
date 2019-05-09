/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;

import Classes.File;
import Classes.FileTypes.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AhmedSalama
 */

public class DownloadInfoWindowController {

    @FXML
    private JFXTextField linkTextField;

    @FXML
    private JFXTextField pathTextField;

    @FXML
    private JFXComboBox<String> fileTybeComboBox;
    
    @FXML
    private JFXButton confirmButton;

    @FXML
    void confirm(ActionEvent event) {  
        String p = "";
                JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new  java.io.File(System.getProperty("user.home")));
        fileChooser.setDialogTitle("Choose a directory to save your file: ");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getCurrentDirectory();            
            if (fileChooser.getSelectedFile().isDirectory()) {
                System.out.println("You selected the directory: " + fileChooser.getSelectedFile());
                p = fileChooser.getSelectedFile().toString();

            }            
        }
        try {
            FileInputStream input = new FileInputStream(fileChooser.getSelectedFile().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select an image", "Error", JOptionPane.ERROR_MESSAGE);
        }    
        
        int idx = fileTybeComboBox.getSelectionModel().getSelectedIndex();
        String Link = linkTextField.getText();
        //String Path = pathTextField.getText();
        String Path = p;


        
           Runnable runnableTask = () -> {
           if (idx == 0) { 
                MusicFile f = new MusicFile(Link, Path);
                MainInterfaceController.Files.add(f);
                f.run();
            }
           if (idx == 1) { 
                VideoFile f = new VideoFile(Link, Path);
                MainInterfaceController.Files.add(f);
                f.run();
            }
           if (idx == 2) { 
                ExecFile f = new ExecFile(Link, Path);
                MainInterfaceController.Files.add(f);
                f.run();
            }
           if (idx == 3) { 
                DLLFile f = new DLLFile(Link, Path);
                MainInterfaceController.Files.add(f);
                f.run();
            }
         };
         MainInterfaceController.executor.execute(runnableTask);
         Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void setFileTybe(ActionEvent event) {

    }

    @FXML
    void setLink(ActionEvent event) {

    }

    @FXML
    void setPath(ActionEvent event) {

    }
    
    @FXML
    public void initialize() {
        fileTybeComboBox.getItems().clear();
        fileTybeComboBox.getItems().addAll("Music", "Video", "Executable", "DLL");
    }
    
}