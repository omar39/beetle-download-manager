/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTreeTableView;
import static downloadmanager.Customizable.proberty;
import static downloadmanager.Customizable.value;

/**
 *
 * @author AhmedSalama
 */
public class CustomizeTableColor extends CustomizeBackgroundColor {
    
    private JFXTreeTableView<?> table; 

    public void setTable(JFXTreeTableView<?> table) {
        this.table = table;
    }

    public void setColorPicker(JFXColorPicker colorPicker) {
        this.colorPicker = colorPicker;
    }
        
    @Override
    public void changeStyle() {
        CustomizeElementColor();
    }    

    @Override
    public void ChangeColor() {
        super.color = super.colorPicker.getValue();
    }
    
    @Override
    public void CustomizeElementColor() {
        int Color = super.color.hashCode();
        backgroundColorValue += Integer.toHexString(Color);
    
        String Style = super.styleTemplate;
        Style = Style.replace(proberty, backgroundColorProberty);
        Style = Style.replace(value, backgroundColorValue);
        //Style += "-fx-opacity: .5;";
        try {
            System.out.println(Style);            
            table.setStyle(table.getStyle() + Style);
            backgroundColorValue = "#";
        } catch (Exception e) {
                System.out.println(e.getCause());
        }
    }
}