/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;
import com.jfoenix.controls.*;
import javafx.scene.layout.Pane;

/**
 *
 * @author AhmedSalama
 */
public class CustomizePaneColor extends CustomizeBackgroundColor {
    private Pane pane; 

    public void setPane(Pane pane) {
        this.pane = pane;
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
        ChangeColor();
        int Color = super.color.hashCode();
        backgroundColorValue += Integer.toHexString(Color);
    
        String Style = super.styleTemplate;
        Style = Style.replace(proberty, backgroundColorProberty);
        Style = Style.replace(value, backgroundColorValue);
        //Style += "-fx-opacity: .5;";
        try {
            System.out.println(Style);            
            pane.setStyle(pane.getStyle() + Style);
            backgroundColorValue = "#";
        } catch (Exception e) {
                System.out.println(e.getCause());
        }
    }
}