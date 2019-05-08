/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;

import Classes.FileTypes.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import static java.lang.System.exit;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
        int idx = fileTybeComboBox.getSelectionModel().getSelectedIndex();
        String Link = linkTextField.getText();
        String Path = pathTextField.getText();
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