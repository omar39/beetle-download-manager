/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author AhmedSalama
 */
public class CustomizeWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public Scene scene;    
    public CustomizeAnchorPaneColor customizeAnchorPaneColor;
    public CustomizePaneColor customizeButtonPaneColor;
    public CustomizePaneColor customizeStatusPaneColor;
    public CustomizeTableColor customizeTableColor;
    public CustomizeMenuBarColor customizeMenuBarColor;
    public boolean light; 
    
    public CustomizePaneOpacity customizeButtonPaneOpacity;
    public CustomizePaneOpacity customizeStatusPaneOpacity;
    public CustomizeMenuBarOpacity customizeMenuBarOpacity;
    public CustomizeTableOpacity customizeTableOpacity; 
    
    @FXML
    private JFXSlider menuBarOpacitySlider;

    @FXML
    private JFXSlider statusPaneOpacitySlider;

    @FXML
    private JFXSlider tableOpacitySlider;

    @FXML
    private JFXSlider buttonsPaneOpacitySlider;
    
    @FXML
    private Label toggleThemeLabel;

    @FXML
    private JFXColorPicker anchorPaneColorPicker;    
    
    @FXML
    private JFXColorPicker statusPaneColorPicker;
    
    @FXML
    private JFXColorPicker buttonPaneColorPicker;

    @FXML
    private JFXColorPicker menuBarColorPicker;

    @FXML
    private JFXColorPicker tableColorPicker;

    @FXML
    private JFXToggleButton themeToggleButton;

    @FXML
    private JFXComboBox<String> themeComboBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO               
        //themeComboBox = new  JFXComboBox<String>();        
        themeComboBox.getItems().clear();
        themeComboBox.getItems().addAll("none" ,"MODENA", "CASPIAN", "FlatBee", "dark_theme", "modena_dark");
        light = true;
        toggleThemeLabel.setText("Light");
    }       

    @FXML    
    private void changeAnchorPaneColor(ActionEvent event) {       
        try {
            customizeAnchorPaneColor.setColorPicker(anchorPaneColorPicker);
            customizeAnchorPaneColor.changeStyle();
        } catch (Exception e) {
            System.out.println(e);
        }       
    }
    
    @FXML
    private void changeButtonPaneColor(ActionEvent event) {       
        customizeButtonPaneColor.setColorPicker(buttonPaneColorPicker);
        try {
             customizeButtonPaneColor.ChangeColor();
             customizeButtonPaneColor.changeStyle();
        } catch (Exception e) {
            System.out.println(e);
        }       
    }
    
    @FXML    
    private void changeStatusPaneColor(ActionEvent event) {       
        customizeStatusPaneColor.setColorPicker(statusPaneColorPicker);
        try {
             customizeStatusPaneColor.ChangeColor();
             customizeStatusPaneColor.changeStyle();
        } catch (Exception e) {
            System.out.println(e);
        }       
    }
    
    @FXML    
    private void changeMenuBarColor(ActionEvent event) {       
        customizeMenuBarColor.setColorPicker(menuBarColorPicker);
        try {
             customizeMenuBarColor.ChangeColor();
             customizeMenuBarColor.changeStyle();
        } catch (Exception e) {
            System.out.println(e);
        }       
    }
    
    @FXML    
    private void changeTableColor(ActionEvent event) {       
        customizeTableColor.setColorPicker(tableColorPicker);
        try {
             customizeTableColor.ChangeColor();
             customizeTableColor.changeStyle();
        } catch (Exception e) {
            System.out.println(e);
        }       
    }
    
    @FXML
    void changeTheme(ActionEvent event) {
        try {
            if(themeComboBox.getSelectionModel().getSelectedIndex() == 0){
                scene.getStylesheets().clear();
                Application.setUserAgentStylesheet(null); 
                toggleThemeLabel.setText("Light");
            }else
            if(themeComboBox.getSelectionModel().getSelectedIndex() == 1){
                scene.getStylesheets().clear();
                Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
            }else
            if(themeComboBox.getSelectionModel().getSelectedIndex() == 2){
                scene.getStylesheets().clear();
                Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
            }else
            if(themeComboBox.getSelectionModel().getSelectedIndex() == 3){
                scene.getStylesheets().clear();
                Application.setUserAgentStylesheet(null); 
                scene.getStylesheets().add(getClass().getResource("FlatBee.css").toExternalForm());
            }else
            if(themeComboBox.getSelectionModel().getSelectedIndex() == 4){
                scene.getStylesheets().clear();
                Application.setUserAgentStylesheet(null);                 
                scene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm());
                toggleThemeLabel.setText("Dark");
            }else
            if(themeComboBox.getSelectionModel().getSelectedIndex() == 5){
                scene.getStylesheets().clear();
                Application.setUserAgentStylesheet(null);                 
                scene.getStylesheets().add(getClass().getResource("modena_dark.css").toExternalForm());
                toggleThemeLabel.setText("Dark");
            }
        } catch (Exception e) {
            System.out.println(e);
        }        
    }
    
    @FXML
    void toggleTheme(ActionEvent event) {
        if(light)
        {
            //System.out.println("here1");
            try {
                scene.getStylesheets().clear();
                //Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
                Application.setUserAgentStylesheet(null);
                scene.getStylesheets().add(getClass().getResource("modena_dark.css").toExternalForm());
                toggleThemeLabel.setText("Dark");
            } catch (Exception e) {
                System.out.println(e);
            }
            light = false;
        } 
        else
        if(!light)
        {
            //System.out.println("here2");
            try {
                scene.getStylesheets().clear();
                Application.setUserAgentStylesheet(null);
                toggleThemeLabel.setText("Light");
                //scene.getStylesheets().add(getClass().getResource("CASPIAN").toExternalForm());
            } catch (Exception e) {
                System.out.println(e);
            }
            light = true;
        }
    }
    
    @FXML
    void changeButtonsPaneOpacity(MouseEvent event) {
        try {
            customizeButtonPaneOpacity.setOpacitySlider(buttonsPaneOpacitySlider);
            customizeButtonPaneOpacity.changeStyle();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void changeMenuBarOpacity(MouseEvent event) {
        try {
            customizeMenuBarOpacity.setOpacitySlider(menuBarOpacitySlider);
            customizeMenuBarOpacity.changeStyle();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void changeStatusPaneOpacity(MouseEvent event) {
        try {
            customizeStatusPaneOpacity.setOpacitySlider(statusPaneOpacitySlider);
            customizeStatusPaneOpacity.changeStyle();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void changeTableOpacity(MouseEvent event) {
        try {
            customizeTableOpacity.setOpacitySlider(tableOpacitySlider);
            customizeTableOpacity.changeStyle();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static class Builder 
    {
        public Scene sceneBuilder;    
        public CustomizeAnchorPaneColor customizeAnchorPaneColorBuilder;
        public CustomizePaneColor customizeButtonPaneColorBuilder;
        public CustomizePaneColor customizeStatusPaneColorBuilder;
        public CustomizeTableColor customizeTableColorBuilder;
        public CustomizeMenuBarColor customizeMenuBarColorBuilder;
        
        
        public Builder(Scene scene){
            this.sceneBuilder = scene;
        }
        
        public Builder initCustomizeAnchorPaneColorBuilder(CustomizeAnchorPaneColor customizeAnchorPaneColor){
            this.customizeAnchorPaneColorBuilder = customizeAnchorPaneColor;
            return this;
        }
        
        public Builder initCustomizeButtonPaneColorBuilder(CustomizePaneColor customizeButtonPaneColor){
            this.customizeButtonPaneColorBuilder = customizeButtonPaneColor;
            return this;
        }
          
        public Builder initCustomizeStatusPaneColorBuilder(CustomizePaneColor customizeStatusPaneColor){
            this.customizeStatusPaneColorBuilder = customizeStatusPaneColor;
            return this;
        }
            
        public Builder initCustomizeTableColorBuilder(CustomizeTableColor customizeTableColor){
            this.customizeTableColorBuilder = customizeTableColor;
            return this;
        }
              
        public Builder initCustomizeMenuBarColorBuilder(CustomizeMenuBarColor customizeMenuBarColor){
            this.customizeMenuBarColorBuilder = customizeMenuBarColor;
            return this;
        }
        
        public CustomizeWindowController Build(){
            CustomizeWindowController customizeWindowController = new CustomizeWindowController();
            customizeWindowController.customizeAnchorPaneColor = customizeAnchorPaneColorBuilder;
            customizeWindowController.customizeButtonPaneColor = customizeButtonPaneColorBuilder;
            customizeWindowController.customizeStatusPaneColor = customizeStatusPaneColorBuilder;
            customizeWindowController.customizeTableColor = customizeTableColorBuilder;
            customizeWindowController.customizeMenuBarColor = customizeMenuBarColorBuilder;            
            return customizeWindowController;
        }        
    }    
}