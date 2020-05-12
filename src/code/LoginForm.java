package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class LoginForm extends JFrame{

    private final JPanel contentPane = new JPanel();
    private JPanel panelUp;
    private JPanel panelMain;

    private JTextField textField = new JTextField();
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

        ArrayList<LabelSetting> label= new ArrayList<>();
        label.add(new LabelSetting("Login", new Color(255, 255, 255), new Font("Tahoma", Font.BOLD, 26), new int[] { 10, 18, 91, 27}));
        label.add(new LabelSetting("Username", new Color(0, 0, 0), new Font("Tahoma", Font.PLAIN, 29), new int[] { 66, 89, 143, 35}));
        label.add(new LabelSetting("Password", new Color(0, 0, 0), new Font("Tahoma", Font.PLAIN, 29), new int[] { 66, 147, 143, 35}));

        for(LabelSetting y :  label) {
            iCreate.createLabels(y);
        }

        ArrayList<JLabel> labels;

        labels = iCreate.getLabels();

        for (JLabel x : labels) {
            setLabels(x);
        }

        //ArrayList<TextFieldSetting> textField= new ArrayList<>();
       // textField.add(new TextFieldSetting(new JTextField(),null, new Font("Tahoma", Font.PLAIN, 18), new int[] {228, 89, 233, 35}, new Color(102, 153, 102)));

        //for(TextFieldSetting y :  textField) {
       //     iCreate.createTextFields(y);
       // }
       // ArrayList<JTextField> textFields = new ArrayList<>();

        //textFields = iCreate.getTextFields();
        //for (JTextField x : textFields) {
       //     setTextFields(x);
       // }

        textField = iCreate.createTextFields(new TextFieldSetting(null,new Font("Tahoma", Font.PLAIN, 18), new int[] {228, 89, 233, 35}, new Color(102, 153, 102)));

        passwordField = iCreate.createPasswords();
        setTextFields();
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

    public void setTextFields() {
        panelMain.add(textField);
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
                    ConnectionToDataBases.getInstance().checkConnectionToLoginMyContactForm(textField, passwordField);
                }
            });
            panelMain.add(buttons);
        }
    }
}
