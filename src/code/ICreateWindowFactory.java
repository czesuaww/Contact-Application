package code;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public interface ICreateWindowFactory {

    JPanel createPanels(String panelName, JPanel contentPane);
    ArrayList<JPanel> getPanels();

    void createLabels();
    ArrayList<JLabel> getLabels();

    JLabel createLabelAction(String labelName);
    ArrayList<JLabel> getLabelAction();

    JButton createButtons(String buttonName);

    JTextField createTextFields();
    ArrayList<JTextField> getTextFields();

    default JCheckBox createCheckBoxGroups() {
        JCheckBox checkBoxShowPass = new JCheckBox("Show Pass");
        checkBoxShowPass.setBackground(new Color(102, 153, 153));
        checkBoxShowPass.setBorder(null);


        checkBoxShowPass.setBounds(228, 189, 97, 23);
        return checkBoxShowPass;
    }

    default JPasswordField createPasswords(){
        JPasswordField passwordField;
        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(102, 153, 102));
        passwordField.setBorder(null);
        passwordField.setBounds(228, 147, 233, 35);
        return passwordField;
    }
}
