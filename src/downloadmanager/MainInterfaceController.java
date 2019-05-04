 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;

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
import javafx.beans.value.*;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import javafx.collections.*;
import javafx.scene.control.TreeItem;

/**
 *
 * @author Beshoy Victor
 */
public class MainInterfaceController implements Initializable {
    
    @FXML
     private JFXTreeTableView<Download> downView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        downloads.add(new Download("test","test","test","test","test","test","test"));
        final TreeItem<Download> root = new RecursiveTreeItem<Download>(downloads, RecursiveTreeObject::getChildren);
        downView.getColumns().setAll(dSelect,dName,dStatus,dSpeed,dSize,dTime,dDate);
        downView.setRoot(root);
        downView.setShowRoot(false);
    }    
    
    
    
    class Download extends RecursiveTreeObject<Download>{
    StringProperty selection;
    StringProperty downName; 
    StringProperty downStatus;
    StringProperty downSpeed;
    StringProperty downSize;
    StringProperty downTime;
    StringProperty downDate;
    
    public Download(String selection,String downName,String downStatus,String downSpeed,String downSize,String downTime,String downDate){
        this.selection=new SimpleStringProperty(selection);
        this.downName=new SimpleStringProperty(downName);
        this.downStatus=new SimpleStringProperty(downStatus);
        this.downSpeed=new SimpleStringProperty(downSpeed);
        this.downSize=new SimpleStringProperty(downSize);
        this.downTime=new SimpleStringProperty(downTime);
        this.downDate=new SimpleStringProperty(downDate);
        }
    }

    
}






