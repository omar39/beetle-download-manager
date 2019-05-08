/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTreeTableView;

/**
 *
 * @author AhmedSalama
 */
public class CustomizeTableOpacity extends CustomizeOpacity{

    public void setOpacitySlider(JFXSlider opacitySlider) {
        this.opacitySlider = opacitySlider;
    }

    private  JFXTreeTableView<?> table;

    public JFXTreeTableView<?> gettable() {
        return table;
    }

    public void settable(JFXTreeTableView<?> table) {
        this.table = table;
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
            table.setStyle(table.getStyle() + Style);
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
