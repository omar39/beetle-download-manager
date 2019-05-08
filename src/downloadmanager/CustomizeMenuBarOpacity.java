/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;

import com.jfoenix.controls.JFXSlider;
import javafx.scene.control.MenuBar;

/**
 *
 * @author AhmedSalama
 */
public class CustomizeMenuBarOpacity extends CustomizeOpacity{

    public void setOpacitySlider(JFXSlider opacitySlider) {
        this.opacitySlider = opacitySlider;
    }

    private MenuBar menuBar;

    public MenuBar getPane() {
        return menuBar;
    }

    public void setPaneMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }
    
    @Override
    public void CustomizeElementOpacity() {

        ChangeOpacity();        
        try {
            double Opacity = opacitySlider.getValue() / this.opacityDiv;
            opacityValue = String.valueOf(Opacity);
            String Style = super.styleTemplate;
            System.out.println(Opacity);
            Style = Style.replace(proberty, opacityProberty);
            Style = Style.replace(value, opacityValue);
            menuBar.setStyle(menuBar.getStyle() + Style);
            //pane.setOpacity(obacity);
        } catch (Exception e) {
            System.out.println(e);
        }                
    }

    @Override
    public void ChangeOpacity() {
        this.opacity = opacitySlider.getValue();
    }

    @Override
    public void changeStyle() {
        CustomizeElementOpacity();
    }    
}
