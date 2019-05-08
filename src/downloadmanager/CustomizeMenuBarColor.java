/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;

import com.jfoenix.controls.JFXColorPicker;
import static downloadmanager.Customizable.proberty;
import static downloadmanager.Customizable.value;
import javafx.scene.control.MenuBar;

/**
 *
 * @author AhmedSalama
 */
public class CustomizeMenuBarColor extends CustomizeBackgroundColor {
    
    private MenuBar menuBar;    

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
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
            menuBar.setStyle(menuBar.getStyle() + Style);
            backgroundColorValue = "#";
        } catch (Exception e) {
                System.out.println(e.getCause());
        }
    }
}