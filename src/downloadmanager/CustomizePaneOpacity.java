/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;

import com.jfoenix.controls.JFXSlider;
import javafx.scene.layout.Pane;

/**
 *
 * @author AhmedSalama
 */
public class CustomizePaneOpacity extends CustomizeOpacity{

    public void setOpacitySlider(JFXSlider opacitySlider) {
        this.opacitySlider = opacitySlider;
    }

    private Pane pane;

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
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
            pane.setStyle(pane.getStyle() + Style);
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
