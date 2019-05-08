 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;

import Classes.FileTypes.MusicFile;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.*;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import javafx.collections.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.stage.Stage;
import Classes.FileTypes.*;

/**
 *
 * @author Beshoy Victor
 */
public class MainInterfaceController implements Initializable {
    
    @FXML
     private JFXTreeTableView<Download> downView;
    
    @FXML
    private Button AddButton;
    
    @FXML 
    private Button PauseButton;
    
    @FXML 
    private Button ResumeButton;
    
     public static ArrayList<Classes.File> Files = new ArrayList<Classes.File>();
     public static ExecutorService executor = Executors.newFixedThreadPool(10);
     
     public void Load() {
        try { 
            BufferedReader r = new BufferedReader(new FileReader("Downloads.txt"));
            String line = r.readLine();
            while (line != null) {
                String[] SplittedLine = line.split("@");
                String url = SplittedLine[0];
                String path = SplittedLine[1];
                String type = SplittedLine[3];
                Classes.File currFile;
                if (type.equals("1")) {
                    currFile = new  MusicFile(url, path);
                }
                else if (type.equals("2")) {
                    currFile = new VideoFile(url, path);
                }
                else if (type.equals("3")) {
                    currFile = new ExecFile(url, path);
                }
                else {
                    currFile = new DLLFile(url, path);
                }
                if (SplittedLine[2].equals("1"))
                    currFile.setStatus("Finished");
                Files.add(currFile);
                line = r.readLine();
            }
            r.close();
        }
        catch (IOException ex) { 
            System.out.println(ex.getMessage());
        }
     } 
     
     public void Save() {
        try {
            FileWriter write = new FileWriter("Downloads.txt", false);
            PrintWriter print_line = new PrintWriter(write);
            for (int i = 0; i < Files.size(); i++) {
                Classes.File currFile = Files.get(i);
                String Line = "";
                Line += currFile.getUrl();
                Line += "@";
                Line += currFile.getName();
                Line += "@";
                Line += currFile.getStatus().equals("Finished") ? "1" : "0";
                Line += "@";
                if (currFile.getType().equals("Music File"))
                    Line += "1";
                else if (currFile.getType().equals("Video File"))
                    Line += "2";
                else if (currFile.getType().equals("Executable File"))
                    Line += "3";
                else
                    Line += "4";
                print_line.println(Line);
            }
            write.close();;
            print_line.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
     }
    
    public void Update() {
        TreeTableViewSelectionModel<Download> sm = downView.getSelectionModel();
        int rowIndex = sm.getSelectedIndex();
        JFXTreeTableColumn<Download, String> dSelect = new JFXTreeTableColumn<>("");
        dSelect.setPrefWidth(30);
        dSelect.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Download, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Download, String> param) {
                return param.getValue().getValue().selection;
            }
        });
        JFXTreeTableColumn<Download, String> dName = new JFXTreeTableColumn<>("Name");
        dName.setPrefWidth(150);
        dName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Download, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Download, String> param) {
                return param.getValue().getValue().downName;
            }
        });
         JFXTreeTableColumn<Download, String> dStatus = new JFXTreeTableColumn<>("Status");
        dStatus.setPrefWidth(100);
        dStatus.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Download, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Download, String> param) {
                return param.getValue().getValue().downStatus;
            }
        });
        
        JFXTreeTableColumn<Download, String> dType = new JFXTreeTableColumn<>("File Type");
        dType.setPrefWidth(100);
        dType.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Download, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Download, String> param) {
                return param.getValue().getValue().typeStatus;
            }
        });

         JFXTreeTableColumn<Download, String> dSpeed = new JFXTreeTableColumn<>("Speed");
        dSpeed.setPrefWidth(100);
        dSpeed.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Download, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Download, String> param) {
                return param.getValue().getValue().downSpeed;
            }
        });

        JFXTreeTableColumn<Download, String> dSize = new JFXTreeTableColumn<>("Size");
        dSize.setPrefWidth(100);
        dSize.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Download, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Download, String> param) {
                return param.getValue().getValue().downSize;
            }
        });

        JFXTreeTableColumn<Download, String> dTime = new JFXTreeTableColumn<>("Time Left");
        dTime.setPrefWidth(100);
        dTime.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Download, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Download, String> param) {
                return param.getValue().getValue().downTime;
            }
        });

        JFXTreeTableColumn<Download, String> dDate = new JFXTreeTableColumn<>("Date");
        dDate.setPrefWidth(100);
        dDate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Download, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Download, String> param) {
                return param.getValue().getValue().downDate;
            }
        });
        
        ObservableList<Download> downloads = FXCollections.observableArrayList();
        for (int i = 0; i < Files.size(); i++){ 
            String _Name = Files.get(i).getName();
            String _Status = Files.get(i).getStatus();
            String _Speed = Files.get(i).getSpeedString();
            String _Size = Files.get(i).getSizeString();
            String _TimeLeft = Files.get(i).getStringLeftTime();
            String _Type = Files.get(i).getType();
            downloads.add(new Download("", _Name, _Status, _Type, _Speed,  _Size, _TimeLeft, Files.get(i).getDataModifiedDate()));
        }
        final TreeItem<Download> root = new RecursiveTreeItem<Download>(downloads, RecursiveTreeObject::getChildren);
        downView.getColumns().setAll(dSelect,dName,dStatus, dType, dSpeed,dSize,dTime,dDate);
        downView.setRoot(root);
        downView.setShowRoot(false);
        
         sm = downView.getSelectionModel();
         sm.select(rowIndex);
        
    }
    
     private void PauseButton_OnClick(ActionEvent event)  {
        TreeTableViewSelectionModel<Download> sm = downView.getSelectionModel();
        int rowIndex = sm.getSelectedIndex();
        Files.get(rowIndex).Pause();
        
     }
     
     private void ResumeButton_OnClick(ActionEvent event)  {
         TreeTableViewSelectionModel<Download> sm = downView.getSelectionModel();
        int rowIndex = sm.getSelectedIndex();
        System.out.println("zh2t");
        Runnable runnableTask = () -> {
            Files.get(rowIndex).Resume();
            };
            
            executor.execute(runnableTask);
     }
    private void AddButton_OnClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DownloadInfoWindow.fxml"));
        Parent rootl;
        try {
            rootl = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Add new");
            Scene scene = new Scene(rootl);                 
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AddButton.setOnAction(this::AddButton_OnClick);
        PauseButton.setOnAction(this::PauseButton_OnClick);
        ResumeButton.setOnAction(this::ResumeButton_OnClick);
        Load();
        Timer t = new Timer( );
        t.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(() -> { Update(); });
            }
        }, 1000, 600);
        
        Timer t2 = new Timer( );
        t2.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(() -> { Save(); });
            }
        }, 1000, 3000);
    }    
    
     try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomizeWindow.fxml"));
            
            Parent rootl = (Parent)fxmlLoader.load();
            
            /*
            //anchorPane.setStyle("-fx-background-color: #ff0000;");
            if(anchorPane == null)
                System.out.println("hello");
            else
                anchorPane.setStyle("-fx-background-color: #ff0000; ");
            */

            //Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
            //System.out.println(Application.STYLESHEET_MODENA);
              
            CustomizeWindowController c = fxmlLoader.getController();
            
            /*
            c = new CustomizeWindowController.Builder(scene)
                    .initCustomizeAnchorPaneColorBuilder(new CustomizeAnchorPaneColor())
                    .initCustomizeButtonPaneColorBuilder(new CustomizePaneColor())
                    .initCustomizeStatusPaneColorBuilder(new CustomizePaneColor())
                    .initCustomizeTableColorBuilder(new CustomizeTableColor())
                    .initCustomizeMenuBarColorBuilder(new CustomizeMenuBarColor())
                    .Build();
            
            c.customizeAnchorPaneColor.setAnchorPane(anchorPane);
            c.customizeButtonPaneColor.setPane(buttonsPane);
            c.customizeStatusPaneColor.setPane(statusPane);
            c.customizeMenuBarColor.setMenuBar(menuBar);
            c.customizeTableColor.setTable(downView);
            */
            
            c.customizeAnchorPaneColor = new CustomizeAnchorPaneColor();
            c.customizeAnchorPaneColor.setAnchorPane(anchorPane);
            c.customizeButtonPaneColor = new CustomizePaneColor();
            c.customizeButtonPaneColor.setPane(buttonsPane);
            c.customizeStatusPaneColor = new CustomizePaneColor();
            c.customizeStatusPaneColor.setPane(statusPane);
            c.customizeMenuBarColor = new CustomizeMenuBarColor();
            c.customizeMenuBarColor.setMenuBar(menuBar);
            c.customizeTableColor = new CustomizeTableColor();
            c.customizeTableColor.setTable(downView);
                
            //anchorPane.setOpacity(.5);
            
            c.customizeButtonPaneOpacity = new CustomizePaneOpacity();
            c.customizeButtonPaneOpacity.setPane(buttonsPane);
            c.customizeMenuBarOpacity = new CustomizeMenuBarOpacity();
            c.customizeMenuBarOpacity.setPaneMenuBar(menuBar);
            c.customizeStatusPaneOpacity = new CustomizePaneOpacity();
            c.customizeStatusPaneOpacity.setPane(statusPane);
            c.customizeTableOpacity = new CustomizeTableOpacity();
            c.customizeTableOpacity.settable(downView);
                        
            Stage stage = new Stage();
            stage.setResizable(false);
            //stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("CusomizeWindow");
            Scene scene = new Scene(rootl);
            c.scene = this.scene;
            //this.scene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm()); 
            //scene.getStylesheets().add(getClass().getResource("modena_dark.css").toExternalForm());                       
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Can't Load New Window");
        }               
}






