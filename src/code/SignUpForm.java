package code;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUpForm extends JFrame {

    private final JPanel contentPane = new JPanel();;
    private JPanel panelUp;
    private JPanel panelMain;

    private JTextField textFieldFristName;
    private JTextField textFieldLastName;
    private JTextField textFieldUsername;

    private JPasswordField passwordField;
    private JPasswordField rePasswordField;

    private JLabel lblPictureAdd;
    private JLabel labelPictureAdded;

    private JButton buttonCreate;

    String imagePth = null;

    ICreateWindowFactory iCreate;

    public SignUpForm() {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 581, 785);
        setLocationRelativeTo(null);
        contentPane.setBorder(null);
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        initComponents();
    }

    public void initComponents() {
        CreateWindowFactory factory = new CreateWindowFactory();
        iCreate = factory.chooseForm("SignUpForm");
        setPanels(iCreate.createPanels("PanelUp", contentPane));
        setPanels(iCreate.createPanels("PanelMain", contentPane));

        ArrayList<LabelSetting> label= new ArrayList<>();
        label.add(new LabelSetting("Create New Account", new Color(255, 255, 255), new Font("Tahoma", Font.BOLD, 26), new int[] {10, 18, 271, 27}));
        label.add(new LabelSetting("First Name:", new Color(0, 0, 0), new Font("Tahoma", Font.PLAIN, 29), new int[] {127, 89, 147, 35}));
        label.add(new LabelSetting("Last Name: ", new Color(0, 0, 0), new Font("Tahoma", Font.PLAIN, 29), new int[] {127, 135, 180, 35}));
        label.add(new LabelSetting("Username: ", new Color(0, 0, 0), new Font("Tahoma", Font.PLAIN, 29), new int[] {137, 181, 180, 35}));
        label.add(new LabelSetting("Password: ", new Color(0, 0, 0), new Font("Tahoma", Font.PLAIN, 29), new int[] {137, 227, 140, 35}));
        label.add(new LabelSetting("Retype Password: ", new Color(0, 0, 0), new Font("Tahoma", Font.PLAIN, 29), new int[] {41, 273, 240, 35}));
        label.add(new LabelSetting("Picture: ", new Color(0, 0, 0), new Font("Tahoma", Font.PLAIN, 29), new int[] {170, 361, 110, 35}));


        for(LabelSetting y :  label) {
            iCreate.createLabels(y);
        }

        ArrayList<JLabel> labels;
        labels = iCreate.getLabels();

        for (JLabel x : labels) {
            setLabels(x);
        }

        setLabelX();
        setLabelMin();
        setTextFieldFirsName();
        setTextFieldLastName();
        setTextFieldUsername();
        setPasswordField();
        setTextFieldRetypePassword();
        setLabelPictureAdded();
        setButtonBrowse();
        setButtonCancel();
        setButtonCreate();
        redirectionToLogin();
    }

    public void setPanels(JPanel panel) {
        if(panel.getName().equals("PanelUp")) {
            this.panelUp = panel;
        }else if(panel.getName().equals("PanelMain"))
            this.panelMain = panel;
    }

    public void setLabels(JLabel label){
        if(label.getText().equals("Create New Account")) {
            panelUp.add(label);
        }else
            panelMain.add(label);
    }
    public void setLabelX() {
        JLabel lblExit = new JLabel("X");
        lblExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        lblExit.setForeground(Color.WHITE);
        lblExit.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblExit.setBounds(549, 22, 22, 14);
        panelUp.add(lblExit);
    }

    public void setLabelMin() {
        JLabel labelMin = new JLabel("-");
        labelMin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        labelMin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setState(JFrame.ICONIFIED);
            }
        });

        labelMin.setForeground(Color.WHITE);
        labelMin.setFont(new Font("Tahoma", Font.BOLD, 30));
        labelMin.setBounds(526, 22, 13, 14);
        panelUp.add(labelMin);
    }


    public void setTextFieldFirsName() {
        textFieldFristName = new JTextField();
        textFieldFristName.setBorder(null);
        textFieldFristName.setBackground(new Color(102, 153, 102));
        textFieldFristName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldFristName.setColumns(10);
        textFieldFristName.setBounds(292, 89, 233, 35);
        panelMain.add(textFieldFristName);
    }



    public void setTextFieldLastName() {
        textFieldLastName = new JTextField();
        textFieldLastName.setBackground(new Color(102, 153, 102));
        textFieldLastName.setBorder(null);
        textFieldLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldLastName.setColumns(10);
        textFieldLastName.setBounds(292, 135, 233, 35);
        panelMain.add(textFieldLastName);
    }


    public void setTextFieldUsername() {
        textFieldUsername = new JTextField();
        textFieldUsername.setBackground(new Color(102, 153, 102));
        textFieldUsername.setBorder(null);
        textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldUsername.setColumns(10);
        textFieldUsername.setBounds(292, 181, 233, 35);
        panelMain.add(textFieldUsername);
    }


    public void setPasswordField() {
        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(102, 153, 102));
        passwordField.setBorder(null);
        passwordField.setBounds(292, 227, 233, 35);
        panelMain.add(passwordField);
    }


    public void setTextFieldRetypePassword() {
        rePasswordField = new JPasswordField();
        rePasswordField.setBorder(null);
        rePasswordField.setBackground(new Color(102, 153, 102));
        rePasswordField.setBounds(292, 273, 233, 35);
        panelMain.add(rePasswordField);
    }

    public void setLabelPictureAdded() {
        labelPictureAdded = new JLabel("");
        labelPictureAdded.setOpaque(true);
        labelPictureAdded.setBackground(new Color(102, 153, 102));
        labelPictureAdded.setBounds(292, 370, 233, 142);
        panelMain.add(labelPictureAdded);
    }

    public void setButtonBrowse() {
        JButton buttonBrowse = new JButton("Browse");

        buttonBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PictureSearch mf = new PictureSearch();
                imagePth = mf.browseImage(labelPictureAdded);
            }
        });

        buttonBrowse.setFont(new Font("Tahoma", Font.BOLD, 11));
        buttonBrowse.setBounds(180, 407, 89, 35);
        panelMain.add(buttonBrowse);
    }

    public void setButtonCancel() {
        JButton buttonCancel = new JButton("Cancel");

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonCancel.setBorder(null);
        buttonCancel.setForeground(new Color(255, 255, 255));
        buttonCancel.setBackground(new Color(204, 0, 0));
        buttonCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        buttonCancel.setBounds(286, 644, 104, 35);
        panelMain.add(buttonCancel);
    }

    public void setButtonCreate() {
        buttonCreate = new JButton("Create");

        buttonCreate.addActionListener(new ActionListener() {

            //Sprawdzenie czy użytkonik podał wymagane dane.
            public boolean verifData() {

                //pierwszy if: wymagane pierwsze imie, nazwisko, nazwa użytkownika, hasło
                //drugi if: sprawdzanie zgodności haseł, czy są takie same.
                //trzeci if: czy zdjęcie zostało wybrane.
                if(textFieldFristName.getText().equals("") || textFieldLastName.getText().equals("") || textFieldUsername.getText().equals("") || String.valueOf(passwordField.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(null, "One or more field are empty");
                    return false;
                }else if(!String.valueOf(passwordField.getPassword()).equals(String.valueOf(rePasswordField.getPassword()))) {
                    JOptionPane.showMessageDialog(null, "Incorrect Password");
                    return false;
                }else if(imagePth == null) {
                    JOptionPane.showMessageDialog(null, "No picture selected");
                    return false;
                }else
                    return true;
            }

            //Sprawdzanie czy użytkownik istnieje
            public boolean isUserExists(String un) {

                boolean userExist = false;

                Connection con = MyConnectionBuilder.getConnection();
                PreparedStatement ps;
                ResultSet rs;

                try {
                    ps = con.prepareStatement("SELECT * FROM `user` WHERE `username` = ?");
                    ps.setString(1, textFieldUsername.getText());

                    rs = ps.executeQuery();

                    if(rs.next()) {
                        userExist = true;
                    }

                }catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                return userExist;
            }

            public void actionPerformed(ActionEvent e) {

                if(verifData()) {
                    Connection con = MyConnectionBuilder.getConnection();
                    PreparedStatement ps;

                    try {
                        ps = con.prepareStatement("INSERT INTO `user`(`firstname`, `lastname`, `username`, `pass`, `picture`) VALUES (?,?,?,?,?)");
                        ps.setString(1, textFieldFristName.getText());
                        ps.setString(2, textFieldLastName.getText());
                        ps.setString(3, textFieldUsername.getText());
                        ps.setString(4, String.valueOf(passwordField.getPassword()));

                        InputStream img = new FileInputStream(new File(imagePth));
                        ps.setBlob(5,img);

                        if(isUserExists(textFieldUsername.getText())) {
                            JOptionPane.showMessageDialog(null, "Username already exist. Try again.");
                        }else if(ps.executeUpdate() != 0){
                            JOptionPane.showMessageDialog(null, "Account Created");
                        }else{
                            JOptionPane.showMessageDialog(null, "Something went wrong");
                        }

                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, e1);
                    }
                }

            }
        });

        buttonCreate.setBorder(null);
        buttonCreate.setForeground(new Color(255, 255, 255));
        buttonCreate.setBackground(new Color(0, 191, 255));
        buttonCreate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        buttonCreate.setBounds(161, 644, 104, 35);
        panelMain.add(buttonCreate);
    }

    public void redirectionToLogin() {
        JLabel labelLogin = new JLabel("Already have an account? Click here to login");
        labelLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginForm form = new LoginForm();
                form.setVisible(true);
                form.setLocationRelativeTo(null);
                form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });

        labelLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelLogin.setForeground(new Color(255, 255, 255));
        labelLogin.setBackground(new Color(255, 255, 255));
        labelLogin.setFont(new Font("Tahoma", Font.ITALIC, 15));
        labelLogin.setBounds(137, 690, 304, 26);
        panelMain.add(labelLogin);
    }
}
