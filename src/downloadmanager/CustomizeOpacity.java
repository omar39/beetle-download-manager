/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;

import com.jfoenix.controls.JFXSlider;

/**
 *
 * @author AhmedSalama
 */
public abstract class CustomizeOpacity implements Customizable
{
    protected double opacity = 1;
    protected double opacityDiv = 100;
    protected String opacityProberty = "opacity";
    protected String opacityValue =  String.valueOf(opacity);
    protected JFXSlider opacitySlider;
    
    public abstract void CustomizeElementOpacity();
    
    public abstract void ChangeOpacity();            
}