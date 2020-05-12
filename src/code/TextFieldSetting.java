package code;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextFieldSetting {

    //private String textFieldName;
    // private Color foregroundColor;

    private Border border;
    private Font font;
    private int[] boundTab = new int[4];
    private Color backgroundColor;

    public TextFieldSetting(Border border, Font font, int[] boundTab, Color backgroundColor) {
        this.border = border;
        this.font = font;
        this.boundTab = boundTab;
        this.backgroundColor = backgroundColor;
    }




    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public int[] getBoundTab() {
        return boundTab;
    }

    public void setBoundTab(int[] boundTab) {
        this.boundTab = boundTab;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setForegroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}