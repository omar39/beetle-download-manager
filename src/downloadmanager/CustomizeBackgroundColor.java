/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager;

import javafx.scene.paint.Color;
import com.jfoenix.controls.*;

/**
 *
 * @author AhmedSalama
 */
public abstract class CustomizeBackgroundColor implements Customizable
{
    protected Color color = new Color(0, 0, 1, 0);
    protected String backgroundColorProberty = "background-color";
    protected String backgroundColorValue = "#";
    protected JFXColorPicker colorPicker;
    
    public abstract void CustomizeElementColor();
    
    protected abstract void ChangeColor();        
}