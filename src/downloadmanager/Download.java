/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author zulam
 */
public  class Download extends RecursiveTreeObject<Download>{
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
