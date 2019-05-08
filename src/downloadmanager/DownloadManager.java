/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Beshoy Victor
 */
public class DownloadManager extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try {
                    
        FXMLLoader fxmlLoader = new  FXMLLoader(getClass().getResource("MainInterface.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        
        Scene scene = new Scene(root);
        //stage.setResizable(false);
        //scene.getStylesheets().add(getClass().getResource("modena_dark.css").toExternalForm());  
        MainInterfaceController m = fxmlLoader.getController();    
        m.scene = scene;
        stage.setScene(scene);
        stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   
}