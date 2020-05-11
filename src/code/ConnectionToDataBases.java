package code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ConnectionToDataBases extends JFrame {

    private static ConnectionToDataBases  single_instance = null;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    private ConnectionToDataBases () {
        con = MyConnectionBuilder.getConnection();
        ps = null;
        rs = null;
    }

    public void checkConnectionToLoginMyContactForm(JTextField textFieldUsername, JPasswordField passwordField) {

        //JPasswordField incorectPassword = passwordField;
        //JTextField incorectUsername = textFieldUsername;

        if(verifyDataLoginMyContactForm(textFieldUsername, passwordField)) {
            try {
                ps = con.prepareStatement("SELECT `username`, `pass`, `picture`, id FROM `user` WHERE `username` = ? AND `pass` = ?");
                ps.setString(1, textFieldUsername.getText());
                ps.setString(2, String.valueOf(passwordField.getPassword()));
                rs = ps.executeQuery();
                //JOptionPane.showMessageDialog(null, "Logged");
                //get the current user id
                if(rs.next()) {
                    MyContactsForm.currentUserId = rs.getInt("id");
                    System.out.println(rs.getInt("id") + "From Login");
                    MyContactsForm myConForm = new MyContactsForm();
                    myConForm.setVisible(true);
                    myConForm.setLocationRelativeTo(null);
                    myConForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //show the user profile picture in top form
                    myConForm.labelUserPicture.setIcon(new PictureResize().resizePic(null, rs.getBytes(3), myConForm.labelUserPicture.getWidth(), myConForm.labelUserPicture.getHeight()));
                    myConForm.labelUsername.setText(rs.getString(1));
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "User does not exist");
                }
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }
    }

    public boolean verifyDataLoginMyContactForm(JTextField textFieldUsername, JPasswordField passwordField) {

        if(textFieldUsername.getText().equals("") && String.valueOf(passwordField.getPassword()).equals("")) {
            JOptionPane.showMessageDialog(null, "Field Username and field Password are empty");
            return false;
        }//else if(textFieldUsername.getText().equals("")) {
        //	JOptionPane.showMessageDialog(null, "Field Username is empty");
        else if(String.valueOf(passwordField.getPassword()).equals("")){
            JOptionPane.showMessageDialog(null, "Field Password is empty");
            return false;
        }
        return true;
    }

    //tworzenie instancji klasy
    public static ConnectionToDataBases getInstance()
    {
        if (single_instance == null)
            single_instance = new ConnectionToDataBases ();

        return single_instance;
    }
}
