package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CreateWindowLogin extends JFrame implements ICreateWindowFactory {

    ArrayList<JLabel> listOfLabelsWithAction = new ArrayList<>();
    ArrayList<JLabel> listOfLabelsWithoutAction = new ArrayList<>();
    ArrayList<JTextField> listOfTextFields = new ArrayList<>();
    ArrayList<JButton> listOfButtons = new ArrayList<>();
    ArrayList<JPanel> listOfPanels = new ArrayList<>();

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
        listOfPanels.add(panel);
        return panel;
    }

    @Override
    public ArrayList<JPanel> getPanels() {
        return listOfPanels;
    }
    @Override
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
        JLabel label = new JLabel(labelName);


        if(labelName == "X") {

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.exit(0);
                }
            });
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            label.setForeground(new Color(255, 255, 255));
            label.setFont(new Font("Tahoma", Font.BOLD, 30));
            label.setBounds(555, 22, 22, 14);
        }
        else if(labelName == "-") {
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setState(JFrame.ICONIFIED);
                }
            });
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            label.setForeground(new Color(255, 255, 255));
            label.setFont(new Font("Tahoma", Font.BOLD, 30));
            label.setBounds(532, 22, 13, 14);
            //panelMain.add(label);
        }else if(labelName == "You are new? Click here to register") {
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SignUpForm form = new SignUpForm();
                    form.setVisible(true);
                    form.setLocationRelativeTo(null);
                    form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    dispose();
                }
            });
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            label.setForeground(new Color(255, 255, 255));
            label.setBackground(new Color(255, 255, 255));
            label.setFont(new Font("Tahoma", Font.ITALIC, 15));
            label.setBounds(176, 309, 255, 26);
            //panelMain.add(label);
        }
        listOfLabelsWithAction.add(label);
        return label;
    }

    @Override
    public ArrayList<JLabel> getLabelAction() {
        return listOfLabelsWithAction;
    }

    @Override
    public JButton createButtons(String buttonName) {
        JButton button = null; //Cancle

        if(buttonName == "Cancel") {
            button = new JButton(buttonName);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            button.setBorder(null);
            button.setForeground(new Color(255, 255, 255));
            button.setBackground(new Color(204, 0, 0));
            button.setFont(new Font("Tahoma", Font.PLAIN, 18));
            button.setBounds(357, 242, 104, 35);
        }else if(buttonName == "Login") {
            button = new JButton(buttonName);
            button.setBorder(null);
            button.setForeground(new Color(255, 255, 255));
            button.setBackground(new Color(0, 191, 255));
            button.setFont(new Font("Tahoma", Font.PLAIN, 18));
            button.setBounds(221, 242, 104, 35);
        }

        listOfButtons.add(button);
        return button;
    }


    public JTextField createTextFields(TextFieldSetting ts) {
       /* JTextField textFieldUsername = new JTextField();
        textFieldUsername.setBorder(null);
        textFieldUsername.setBackground(new Color(102, 153, 102));
        textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldUsername.setBounds(228, 89, 233, 35);
        textFieldUsername.setColumns(10);*/
       // return null;
        JTextField textField = new JTextField();
        textField.setBorder(ts.getBorder());
        textField.setFont(ts.getFont());
        textField.setBackground(ts.getBackgroundColor());
        textField.setBounds(ts.getBoundTab()[0],ts.getBoundTab()[1],ts.getBoundTab()[2],ts.getBoundTab()[3]);
        return textField;
    }

    @Override
    public ArrayList<JTextField> getTextFields() {
        return listOfTextFields;
    }
}
