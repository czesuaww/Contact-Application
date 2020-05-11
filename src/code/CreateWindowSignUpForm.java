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

    @Override
    public void createLabels() {
        JLabel labelCreateNewAccount = new JLabel("Create New Account");
        labelCreateNewAccount.setForeground(Color.WHITE);
        labelCreateNewAccount.setFont(new Font("Tahoma", Font.BOLD, 26));
        labelCreateNewAccount.setBounds(10, 18, 271, 27);
        listOfLabelsWithoutAction.add(labelCreateNewAccount);

        JLabel labelFirstName = new JLabel("First Name:");
        labelFirstName.setOpaque(true);
        labelFirstName.setForeground(Color.BLACK);
        labelFirstName.setBackground(new Color(102, 153, 153));
        labelFirstName.setFont(new Font("Tahoma", Font.PLAIN, 29));
        labelFirstName.setBounds(127, 89, 147, 35);
        listOfLabelsWithoutAction.add(labelFirstName);

        JLabel labelLastName = new JLabel("Last Name:");
        labelLastName.setOpaque(true);
        labelLastName.setForeground(Color.BLACK);
        labelLastName.setFont(new Font("Tahoma", Font.PLAIN, 29));
        labelLastName.setBackground(new Color(102, 153, 153));
        labelLastName.setBounds(127, 135, 147, 35);
        listOfLabelsWithoutAction.add(labelLastName);

        JLabel labelUsername = new JLabel("Username:");
        labelUsername.setOpaque(true);
        labelUsername.setForeground(Color.BLACK);
        labelUsername.setFont(new Font("Tahoma", Font.PLAIN, 29));
        labelUsername.setBackground(new Color(102, 153, 153));
        labelUsername.setBounds(137, 181, 137, 35);
        listOfLabelsWithoutAction.add(labelUsername);

        JLabel labelPasswd = new JLabel("Password:");
        labelPasswd.setOpaque(true);
        labelPasswd.setForeground(Color.BLACK);
        labelPasswd.setFont(new Font("Tahoma", Font.PLAIN, 29));
        labelPasswd.setBackground(new Color(102, 153, 153));
        labelPasswd.setBounds(137, 227, 137, 35);
        listOfLabelsWithoutAction.add(labelPasswd);

        JLabel labelRetypePassword = new JLabel("Retype Password:");
        labelRetypePassword.setOpaque(true);
        labelRetypePassword.setForeground(Color.BLACK);
        labelRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 29));
        labelRetypePassword.setBackground(new Color(102, 153, 153));
        labelRetypePassword.setBounds(41, 273, 233, 35);
        listOfLabelsWithoutAction.add(labelRetypePassword);

        JLabel labelPicture = new JLabel("Picture:");
        labelPicture.setOpaque(true);
        labelPicture.setForeground(Color.BLACK);
        labelPicture.setFont(new Font("Tahoma", Font.PLAIN, 29));
        labelPicture.setBackground(new Color(102, 153, 153));
        labelPicture.setBounds(170, 361, 104, 35);
        listOfLabelsWithoutAction.add(labelPicture);
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
