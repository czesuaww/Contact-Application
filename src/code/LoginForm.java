package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
// coś innego
public class LoginForm extends JFrame{

    private final JPanel contentPane = new JPanel();
    private JPanel panelUp;
    private JPanel panelMain;

    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    ICreateWindowFactory iCreate;

    public LoginForm() {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 587, 406);
        setLocationRelativeTo(null);
        contentPane.setBorder(null);
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        initComponents();
    }

    public void initComponents() {
        CreateWindowFactory factory = new CreateWindowFactory();
        iCreate = factory.chooseForm("LoginForm");
        setPanels(iCreate.createPanels("PanelUp", contentPane));
        setPanels(iCreate.createPanels("PanelMain", contentPane));
        setLabelsWithActions(iCreate.createLabelAction("X"));
        setLabelsWithActions(iCreate.createLabelAction("-"));
        setLabelsWithActions(iCreate.createLabelAction("You are new? Click here to register"));

        iCreate.createLabels();
        ArrayList<JLabel> labels;
        labels = iCreate.getLabels();

        for (JLabel x : labels) {
            setLabels(x);
        }

        textFieldUsername = iCreate.createTextFields();
        passwordField = iCreate.createPasswords();
        setTextFieldUsername();
        setTextPassword();
        showHiddenPassword();
        setButtons(iCreate.createButtons("Cancel"));
        setButtons(iCreate.createButtons("Login"));
    }

    public void setPanels(JPanel panel) {
        if(panel.getName().equals("PanelUp")) {
            this.panelUp = panel;
        }else if(panel.getName().equals("PanelMain"))
            this.panelMain = panel;
    }

    public void setLabels(JLabel label) {
        if(label.getText().equals("Login")) {
            panelUp.add(label);
        }else
            panelMain.add(label);
    }


    public void setLabelsWithActions(JLabel labels) {
        if(labels.getText().equals("-")) {
            labels.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setState(JFrame.ICONIFIED);
                }
            });
            panelUp.add(labels);
        }else if(labels.getText().equals("X")) {
            panelUp.add(labels);
        }else
            panelMain.add(labels);
    }

    public void setTextFieldUsername() {
        panelMain.add(textFieldUsername);
    }

    public void setTextPassword() {
        panelMain.add(passwordField);
    }

    public void showHiddenPassword() {
        JCheckBox checkBoxShowPass = iCreate.createCheckBoxGroups();
        actionCheckBox(checkBoxShowPass);
        panelMain.add(checkBoxShowPass);
    }

    public void actionCheckBox(JCheckBox checkBoxShowPass) {
        checkBoxShowPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(checkBoxShowPass.isSelected()) {
                    passwordField.setEchoChar((char)0);
                }else
                    passwordField.setEchoChar('*');
            }
        });
    }

    public void setButtons(JButton buttons) {
        if(buttons.getText() == "Cancel") {
            panelMain.add(buttons);
        }else if(buttons.getText() == "Login") {
            buttons.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ConnectionToDataBases.getInstance().checkConnectionToLoginMyContactForm(textFieldUsername, passwordField);
                }
            });
            panelMain.add(buttons);
        }
    }
}
