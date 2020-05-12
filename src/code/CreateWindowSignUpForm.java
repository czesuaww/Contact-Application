package code;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateWindowSignUpForm extends JFrame implements ICreateWindowFactory{

    ArrayList<JPanel> listOfPanelss = new ArrayList<>();
    ArrayList<JLabel> listOfLabelsWithoutAction = new ArrayList<>();

    @Override
    public JPanel createPanels(String panelName, JPanel contentPane) {
        JPanel panel = new JPanel();

        if(panelName == "PanelUp") {
            panel.setName("PanelUp");
            panel.setBorder(null);
            panel.setPreferredSize(new Dimension(100, 60));
            panel.setBackground(new Color(46, 139, 87));
            panel.setLayout(null);
            contentPane.add(panel, BorderLayout.NORTH);

        }else if(panelName == "PanelMain") {
            panel.setName("PanelMain");
            panel.setBorder(null);
            panel.setPreferredSize(new Dimension(1000, 10));
            panel.setBackground(new Color(102, 153, 153));
            panel.setLayout(null);
            contentPane.add(panel, BorderLayout.CENTER);
        }
        listOfPanelss.add(panel);
        return panel;
    }

    @Override
    public ArrayList<JPanel> getPanels() {
        return listOfPanelss;
    }

    public void createLabels(LabelSetting ls){
        JLabel label = new JLabel(ls.getLabelName());
        label.setForeground(ls.getForegroundColor());
        label.setFont(ls.getFont());
        label.setBounds(ls.getBoundTab()[0],ls.getBoundTab()[1],ls.getBoundTab()[2],ls.getBoundTab()[3]);
        listOfLabelsWithoutAction.add(label);
    }

    @Override
    public ArrayList<JLabel> getLabels() {
        return listOfLabelsWithoutAction;
    }

    @Override
    public JLabel createLabelAction(String labelName) {
        return null;
    }

    @Override
    public ArrayList<JLabel> getLabelAction() {
        return null;
    }

    @Override
    public JButton createButtons(String buttonName) {
        return null;
    }

    @Override
    public JTextField createTextFields() {
        return null;
    }

    @Override
    public ArrayList<JTextField> getTextFields() {
        return null;
    }
}
