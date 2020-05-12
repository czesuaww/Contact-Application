package code;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MyContactsForm extends JFrame {

    private JPanel contentPane;
    private JPanel panelMain;
    private JPanel panelUp;

    private JTextField textFieldFirstName;
    private JTextField textFieldPhone;
    private JTextField textFieldEmail;
    private JTextField textFieldLastName;
    private JTextField textFieldId;

    public JLabel labelUserPicture;
    public JLabel labelUsername;
    private JLabel labelPictureAdded;

    private TextArea textAreaAdress;

    private JComboBox comboBoxGroup;

    private JTable jTable;

    String imagePth = null;
    public static int currentUserId;
    int pos = 0;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public MyContactsForm() {

        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1286, 804);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        initComponetns();
        populateJTable();
    }

    public void initComponetns() {
        setPanelUp();
        setPanelMain();
        setButtonX();
        setButtonMin();
        setLabelMyContactsHeader();
        setLabelId();
        setTextFieldId();
        setLabelUserPicture();
        setLabelUsernameHeader();
        setLabelFirstName();
        setTextFieldFirstName();
        setLabelLastName();
        setTextFieldLastName();
        setLabelPhone();
        setTextFieldPhone();
        setLabelEmail();
        setTextFieldEmail();
        setLabelGroup();
        setComboBoxGroup();
        setSeparator();
        setLabelProfilePicture();
        setLabelPictureAdded();
        setButtonBrowse();
        setLabelAddress();
        setTextAreaAddress();
        setTable();
        setButtonAdd();
        setButtonEditAndCheckConectionButtonEdit();
        setButtonDelete();
        setButtonRightRight();
        setButtonRight();
        setButtonLeftLeft();
        setButtonLeft();
    }

    //Metoda do wyświetlania zawartości tabeli z bazy w aplikacji.
    public void populateJTable() {

        ContactQuery cq = new ContactQuery();
        ArrayList<Contact> list = cq.contactList(currentUserId);

        String[] colNames = {"Id", "Frist Name", "Last Name", "Group", "Phone", "Email", "Address", "Image"};
        Object[][] rows = new Object[list.size()][8]; //dwuwymiarowa tablica obiektów. rows - nazwa tablicy. new Object[list.siez()][8] - są to rzędy. ma ich tyle ile nasza lista. oraz 8 kolmn.

        for(int i = 0; i < list.size(); i++) {
            rows[i][0] = list.get(i).getCid();
            rows[i][1] = list.get(i).getFirstName();
            rows[i][2] = list.get(i).getLastName();
            rows[i][3] = list.get(i).getGroupC();
            rows[i][4] = list.get(i).getPhone();
            rows[i][5] = list.get(i).getEmail();
            rows[i][6] = list.get(i).getAddress();

            ImageIcon picture = new ImageIcon(new ImageIcon(list.get(i).getPic()).getImage().getScaledInstance(120, 80, Image.SCALE_SMOOTH));

            rows[i][7] = picture;
        }

        generateTable(colNames, rows);

    }

    public void generateTable(String[] colNames, Object[][] rows) {

        PictureModel myMod = new PictureModel(rows, colNames);

        jTable.setModel(myMod);
        jTable.setRowHeight(80);
        jTable.getColumnModel().getColumn(7).setPreferredWidth(120);
        jTable.setGridColor(Color.YELLOW);
        jTable.setSelectionBackground(Color.LIGHT_GRAY);
    }

    //Metoda odświeżająca tabele
    public void refreshJTable() {

        jTable.setModel(new DefaultTableModel());
        populateJTable();

    }

    //Pokazaje zawartośc po wcisnieciu
    private void showData(int index) {

        textFieldFirstName.setText(jTable.getValueAt(index, 1).toString());
        textFieldLastName.setText(jTable.getValueAt(index, 2).toString());

        //group phone email adress pic

        comboBoxGroup.setSelectedItem(jTable.getValueAt(index, 3));
        textFieldPhone.setText(jTable.getValueAt(index, 4).toString());
        textFieldEmail.setText(jTable.getValueAt(index, 5).toString());
        textFieldId.setText(jTable.getValueAt(index, 0).toString());
        textAreaAdress.setText(jTable.getValueAt(index, 6).toString());

        Image pic = ((ImageIcon)jTable.getValueAt(index, 7)).getImage().getScaledInstance(labelPictureAdded.getWidth(), labelPictureAdded.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(pic);

        labelPictureAdded.setIcon(img);
    }

    //Metoda, która kasuje wszystkie pola, które są wypełnione
    public void clearFields() {

        textFieldId.setText("");
        textFieldFirstName.setText("");
        textFieldLastName.setText("");
        textFieldEmail.setText("");
        textFieldPhone.setText("");
        textAreaAdress.setText("");
        comboBoxGroup.setSelectedIndex(0);
        labelPictureAdded.setIcon(null);
        //set imagePath to null to prevent the user to add unwanted duplicate image
        imagePth = null;
    }

    public void setPanelUp() {
        panelUp = new JPanel();
        panelUp.setBounds(0, 0, 1286, 68);
        panelUp.setLayout(null);
        panelUp.setPreferredSize(new Dimension(100, 60));
        panelUp.setBorder(null);
        panelUp.setBackground(new Color(46, 139, 87));
        contentPane.add(panelUp);
    }

    public void setPanelMain() {
        panelMain = new JPanel();
        panelMain.setPreferredSize(new Dimension(1000, 10));
        panelMain.setBorder(null);
        panelMain.setBackground(new Color(102, 153, 153));
        panelMain.setBounds(0, 63, 1286, 741);
        contentPane.add(panelMain);
        panelMain.setLayout(null);
    }

    public void setButtonX() {
        JLabel labelExit = new JLabel("X");

        labelExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        labelExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelExit.setForeground(Color.WHITE);
        labelExit.setFont(new Font("Tahoma", Font.BOLD, 30));
        labelExit.setBounds(1254, 22, 22, 14);
        panelUp.add(labelExit);
    }

    public void setButtonMin() {
        JLabel labelMin = new JLabel("-");

        labelMin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setState(JFrame.ICONIFIED);
            }
        });

        labelMin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelMin.setForeground(Color.WHITE);
        labelMin.setFont(new Font("Tahoma", Font.BOLD, 30));
        labelMin.setBounds(1231, 22, 13, 14);
        panelUp.add(labelMin);
    }

    public void setLabelMyContactsHeader() {
        JLabel labelContacts = new JLabel("My Contacts");
        labelContacts.setForeground(Color.WHITE);
        labelContacts.setFont(new Font("Tahoma", Font.BOLD, 32));
        labelContacts.setBounds(535, 3, 198, 50);
        panelUp.add(labelContacts);
    }

    public void setLabelId() {
        JLabel labelId = new JLabel("Id:");
        labelId.setOpaque(true);
        labelId.setForeground(Color.BLACK);
        labelId.setFont(new Font("Tahoma", Font.PLAIN, 29));
        labelId.setBackground(new Color(102, 153, 153));
        labelId.setBounds(31, 28, 162, 35);
        panelMain.add(labelId);
    }

    public void setTextFieldId() {
        textFieldId = new JTextField();
        textFieldId.setEditable(false);
        textFieldId.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldId.setColumns(10);
        textFieldId.setBorder(null);
        textFieldId.setBackground(new Color(102, 153, 102));
        textFieldId.setBounds(203, 28, 233, 35);
        panelMain.add(textFieldId);
    }

    public void setLabelUserPicture() {
        labelUserPicture = new JLabel("");
        labelUserPicture.setOpaque(true);
        labelUserPicture.setBackground(Color.YELLOW);
        labelUserPicture.setMinimumSize(new Dimension(50, 50));
        labelUserPicture.setBounds(0, 0, 71, 68);
        panelUp.add(labelUserPicture);
    }

    public void setLabelUsernameHeader() {
        labelUsername = new JLabel("Username");
        labelUsername.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labelUsername.setBounds(81, 19, 86, 31);
        panelUp.add(labelUsername);
    }

    public void setLabelFirstName() {
        JLabel labelFirstNane = new JLabel("First Name:");
        labelFirstNane.setBounds(31, 71, 162, 35);
        labelFirstNane.setOpaque(true);
        labelFirstNane.setForeground(Color.BLACK);
        labelFirstNane.setFont(new Font("Tahoma", Font.PLAIN, 29));
        labelFirstNane.setBackground(new Color(102, 153, 153));
        panelMain.add(labelFirstNane);
    }

    public void setTextFieldFirstName() {
        textFieldFirstName = new JTextField();
        textFieldFirstName.setBounds(203, 71, 233, 35);
        textFieldFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldFirstName.setColumns(10);
        textFieldFirstName.setBorder(null);
        textFieldFirstName.setBackground(new Color(102, 153, 102));
        panelMain.add(textFieldFirstName);
    }

    public void setLabelLastName() {
        JLabel labelLastName = new JLabel("Last Name:");
        labelLastName.setBounds(31, 117, 162, 35);
        labelLastName.setOpaque(true);
        labelLastName.setForeground(Color.BLACK);
        labelLastName.setFont(new Font("Tahoma", Font.PLAIN, 29));
        labelLastName.setBackground(new Color(102, 153, 153));
        panelMain.add(labelLastName);
    }

    public void setTextFieldLastName() {
        textFieldLastName = new JTextField();
        textFieldLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldLastName.setColumns(10);
        textFieldLastName.setBorder(null);
        textFieldLastName.setBackground(new Color(102, 153, 102));
        textFieldLastName.setBounds(203, 117, 233, 35);
        panelMain.add(textFieldLastName);
    }

    public void setLabelPhone() {
        JLabel labelPhone = new JLabel("Phone:");
        labelPhone.setOpaque(true);
        labelPhone.setForeground(Color.BLACK);
        labelPhone.setFont(new Font("Tahoma", Font.PLAIN, 29));
        labelPhone.setBackground(new Color(102, 153, 153));
        labelPhone.setBounds(31, 163, 162, 35);
        panelMain.add(labelPhone);
    }

    public void setTextFieldPhone() {
        textFieldPhone = new JTextField();
        textFieldPhone.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldPhone.setColumns(10);
        textFieldPhone.setBorder(null);
        textFieldPhone.setBackground(new Color(102, 153, 102));
        textFieldPhone.setBounds(203, 163, 233, 35);
        panelMain.add(textFieldPhone);
    }

    public void setLabelEmail() {
        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setOpaque(true);
        labelEmail.setForeground(Color.BLACK);
        labelEmail.setFont(new Font("Tahoma", Font.PLAIN, 29));
        labelEmail.setBackground(new Color(102, 153, 153));
        labelEmail.setBounds(31, 209, 162, 35);
        panelMain.add(labelEmail);
    }

    public void setTextFieldEmail() {
        textFieldEmail = new JTextField();
        textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldEmail.setColumns(10);
        textFieldEmail.setBorder(null);
        textFieldEmail.setBackground(new Color(102, 153, 102));
        textFieldEmail.setBounds(203, 209, 233, 35);
        panelMain.add(textFieldEmail);
    }

    public void setLabelGroup() {
        JLabel labelGroup = new JLabel("Group:");
        labelGroup.setOpaque(true);
        labelGroup.setForeground(Color.BLACK);
        labelGroup.setFont(new Font("Tahoma", Font.PLAIN, 29));
        labelGroup.setBackground(new Color(102, 153, 153));
        labelGroup.setBounds(31, 255, 162, 35);
        panelMain.add(labelGroup);
    }

    public void setComboBoxGroup() {
        comboBoxGroup = new JComboBox();
        comboBoxGroup.setModel(new DefaultComboBoxModel(new String[] {"", "Family", "Friends", "Work"}));
        comboBoxGroup.setBounds(203, 255, 233, 35);
        panelMain.add(comboBoxGroup);
    }

    public void setSeparator() {
        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setBackground(new Color(102, 153, 51));
        separator.setBounds(492, 28, 12, 282);
        separator.setPreferredSize(new Dimension(5,1));
        panelMain.add(separator);
    }

    public void setLabelProfilePicture() {
        JLabel labelProfilePicture = new JLabel("Profile Picture:");
        labelProfilePicture.setOpaque(true);
        labelProfilePicture.setForeground(Color.BLACK);
        labelProfilePicture.setFont(new Font("Tahoma", Font.PLAIN, 29));
        labelProfilePicture.setBackground(new Color(102, 153, 153));
        labelProfilePicture.setBounds(527, 23, 196, 35);
        panelMain.add(labelProfilePicture);
    }

    public void setLabelPictureAdded() {
        labelPictureAdded = new JLabel("");
        labelPictureAdded.setBackground(new Color(102, 153, 102));
        labelPictureAdded.setOpaque(true);
        labelPictureAdded.setBounds(733, 23, 162, 110);
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
        buttonBrowse.setBounds(626, 70, 89, 35);
        panelMain.add(buttonBrowse);
    }

    public void setLabelAddress() {
        JLabel labelAddress = new JLabel("Address:");
        labelAddress.setOpaque(true);
        labelAddress.setForeground(Color.BLACK);
        labelAddress.setFont(new Font("Tahoma", Font.PLAIN, 29));
        labelAddress.setBackground(new Color(102, 153, 153));
        labelAddress.setBounds(527, 145, 196, 35);
        panelMain.add(labelAddress);
    }

    public void setTextAreaAddress() {
        textAreaAdress = new TextArea();
        textAreaAdress.setBackground(new Color(102, 153, 102));
        textAreaAdress.setBounds(733, 145, 404, 88);
        panelMain.add(textAreaAdress);
    }

    public void setTable() {
        jTable = new JTable();
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                //display selected JTable row data

                //get selected row index

                int rowIndex = jTable.getSelectedRow();
                textFieldFirstName.setText(jTable.getValueAt(rowIndex, 1).toString());
                textFieldLastName.setText(jTable.getValueAt(rowIndex, 2).toString());

                //group phone email adress pic

                comboBoxGroup.setSelectedItem(jTable.getValueAt(rowIndex, 3));
                textFieldPhone.setText(jTable.getValueAt(rowIndex, 4).toString());
                textFieldEmail.setText(jTable.getValueAt(rowIndex, 5).toString());
                textFieldId.setText(jTable.getValueAt(rowIndex, 0).toString());
                textAreaAdress.setText(jTable.getValueAt(rowIndex, 6).toString());

                Image pic = ((ImageIcon)jTable.getValueAt(rowIndex, 7)).getImage().getScaledInstance(labelPictureAdded.getWidth(), labelPictureAdded.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon img = new ImageIcon(pic);

                labelPictureAdded.setIcon(img);
            }
        });
        jTable.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        jTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                }
        ));

        jTable.setBounds(0, 335, 1286, 406);
        panelMain.add(jTable);
    }

    public void checkConectionButtonAdd(byte[] img, String firstName, String lastName, String groupC, String phone, String email, String address) {

        //you can check if all fields are empty, but in this case a I will only check if the user selected an image
        //and to do this we are gonna use imagePath
        if(imagePth != null) {
            try {
                //InputStream img = new FileInputStream(new File(imagePth));
                Path pth = Paths.get(imagePth);
                img = Files.readAllBytes(pth);
                Contact c = new Contact(null, firstName, lastName, groupC, phone, email, address,img, currentUserId);
                ContactQuery cq = new ContactQuery();
                cq.insertContact(c);
                refreshJTable();
                clearFields();
            }catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }else {
            JOptionPane.showMessageDialog(null, "You must select a profile picture");
        }
    }
    //firstName, lastName, groupC, phone, email, address,

    public void setButtonAdd() {
        JButton buttonAdd = new JButton("Add");

        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = textFieldFirstName.getText();
                String lastName = textFieldLastName.getText();
                String groupC = comboBoxGroup.getSelectedItem().toString();
                String phone = textFieldPhone.getText();
                String email = textFieldEmail.getText();
                String address = textAreaAdress.getText();
                //getFormTextField();

                byte[] img = null;
                checkConectionButtonAdd(img,firstName, lastName, groupC, phone, email, address);

            }
        });

        buttonAdd.setForeground(Color.WHITE);
        buttonAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        buttonAdd.setBorder(null);
        buttonAdd.setBackground(new Color(0, 191, 255));
        buttonAdd.setBounds(733, 252, 104, 35);
        panelMain.add(buttonAdd);
    }

    public void setButtonEditAndCheckConectionButtonEdit() {
        JButton buttonEdit = new JButton("Edit");
        buttonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //the user must select a contact to edit
                if(!textFieldId.getText().equals("")) {
                    //firstName, lastName, groupC, phone, email, address,
                    int id = Integer.valueOf(textFieldId.getText());
                    String firstName = textFieldFirstName.getText();
                    String lastName = textFieldLastName.getText();
                    String groupC = comboBoxGroup.getSelectedItem().toString();
                    String phone = textFieldPhone.getText();
                    String email = textFieldEmail.getText();
                    String address = textAreaAdress.getText();
                    //getFormTextField();

                    // if the user wants to update the data and the picture
                    if(imagePth != null) {
                        byte[] img = null;
                        try {
                            //InputStream img = new FileInputStream(new File(imagePth));
                            Path pth = Paths.get(imagePth);
                            img = Files.readAllBytes(pth);

                            Contact c = new Contact(id, firstName, lastName, groupC, phone, email, address,img, currentUserId);
                            ContactQuery cq = new ContactQuery();
                            cq.updateContact(c, true);
                            refreshJTable();
                            clearFields();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }else {
                        Contact c = new Contact(id, firstName, lastName, groupC, phone, email, address,null, currentUserId);
                        ContactQuery cq = new ContactQuery();
                        cq.updateContact(c, false);
                        refreshJTable();
                        clearFields();
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Select a contact to edit");
                }
            }
        });

        buttonEdit.setForeground(Color.WHITE);
        buttonEdit.setFont(new Font("Tahoma", Font.PLAIN, 18));
        buttonEdit.setBorder(null);
        buttonEdit.setBackground(Color.MAGENTA);
        buttonEdit.setBounds(886, 252, 104, 35);
        panelMain.add(buttonEdit);
    }

    public void setButtonDelete() {
        JButton buttonDelete = new JButton("Delete");

        buttonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //the user must select a contact to delete
                if(!textFieldId.getText().equals("")) {
                    int id = Integer.valueOf(textFieldId.getText());
                    ContactQuery cq = new ContactQuery();
                    cq.deleteContact(id);
                    refreshJTable();
                    clearFields();
                }else
                    JOptionPane.showMessageDialog(null, "Select a contact to delete");

            }
        });

        buttonDelete.setForeground(Color.WHITE);
        buttonDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
        buttonDelete.setBorder(null);
        buttonDelete.setBackground(Color.RED);
        buttonDelete.setBounds(1033, 252, 104, 35);
        panelMain.add(buttonDelete);
    }

    public void setButtonRightRight() {
        JButton buttonRightRight = new JButton(">>");
        buttonRightRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pos = jTable.getRowCount() - 1;
                showData(pos);
            }
        });
        buttonRightRight.setFont(new Font("Tahoma", Font.BOLD, 11));
        buttonRightRight.setBounds(395, 301, 75, 23);
        panelMain.add(buttonRightRight);
    }

    public void setButtonRight() {
        JButton buttonRight = new JButton(">");
        buttonRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pos++;
                showData(pos);
            }
        });
        buttonRight.setFont(new Font("Tahoma", Font.BOLD, 11));
        buttonRight.setBounds(310, 301, 75, 23);
        panelMain.add(buttonRight);
    }

    public void setButtonLeftLeft() {
        JButton buttonLeftLeft = new JButton("<<");
        buttonLeftLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pos = 0;
                showData(pos);
            }
        });
        buttonLeftLeft.setFont(new Font("Tahoma", Font.BOLD, 11));
        buttonLeftLeft.setBounds(140, 301, 75, 23);
        panelMain.add(buttonLeftLeft);
    }

    public void setButtonLeft() {
        JButton buttonLeft = new JButton("<");
        buttonLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pos--;
                showData(pos);
            }
        });
        buttonLeft.setFont(new Font("Tahoma", Font.BOLD, 11));
        buttonLeft.setBounds(225, 301, 75, 23);
        panelMain.add(buttonLeft);
    }
}
