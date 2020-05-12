package code;

import java.awt.*;

public class LabelSetting {

    private String labelName;
    private Color foregroundColor;
    private Font font;
    private int[] boundTab = new int[4];

    public LabelSetting(String labelName, Color foregroundColor, Font font, int[] boundTab) {
        this.labelName = labelName;
        this.foregroundColor = foregroundColor;
        this.font = font;
        this.boundTab = boundTab;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
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
}
