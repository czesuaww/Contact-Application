package code;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class ContactQuery {

    //Metoda umożliwiająca dodanie kontaktu
    public void insertContact(Contact cont) {

        //boolean contactIsCreated = true;
        Connection con = MyConnectionBuilder.getConnection();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO `mycontact`(`firstname`, `lastname`, `groupc`, `phone`, `email`, `address`, `picture`, `userid`) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, cont.getFirstName());
            ps.setString(2, cont.getLastName());
            ps.setString(3, cont.getGroupC());
            ps.setString(4, cont.getPhone());
            ps.setString(5, cont.getEmail());
            ps.setString(6, cont.getAddress());
            ps.setBytes(7, cont.getPic());
            ps.setInt(8, cont.getUserId());

            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "New Contact Added");
                //contactIsCreated = true;
            }else{
                JOptionPane.showMessageDialog(null, "Something went wrong");
                //contactIsCreated = false;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //return contactIsCreated;
    }

    //Metoda umożliwiająca edytowanie kontaktu
    public void updateContact(Contact cont, boolean withImage) {

        //boolean contactIsCreated = true;
        Connection con = MyConnectionBuilder.getConnection();
        PreparedStatement ps;
        String updateQuery = "";

        //if the user wants to update the contact picture
        if(withImage == true) {
            updateQuery = "UPDATE `mycontact` SET `firstname`= ?,`lastname`= ?,`groupc`= ?,`phone`= ?,`email`= ?,`address`= ?,`picture`= ?  WHERE `id` = ?";

            try {
                ps = con.prepareStatement(updateQuery);
                ps.setString(1, cont.getFirstName());
                ps.setString(2, cont.getLastName());
                ps.setString(3, cont.getGroupC());
                ps.setString(4, cont.getPhone());
                ps.setString(5, cont.getEmail());
                ps.setString(6, cont.getAddress());
                ps.setBytes(7, cont.getPic());
                ps.setInt(8, cont.getCid());

                if(ps.executeUpdate() != 0){
                    JOptionPane.showMessageDialog(null, "Contact Updated");
                    //contactIsCreated = true;
                }else{
                    JOptionPane.showMessageDialog(null, "Something went wrong");
                    //contactIsCreated = false;
                }

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //return contactIsCreated;

        }else { //if the user wants to keep the same image or remove the image
            updateQuery = "UPDATE `mycontact` SET `firstname`= ?,`lastname`= ?,`groupc`= ?,`phone`= ?,`email`= ?,`address`= ? WHERE `id` = ?";

            try {
                ps = con.prepareStatement(updateQuery);
                ps.setString(1, cont.getFirstName());
                ps.setString(2, cont.getLastName());
                ps.setString(3, cont.getGroupC());
                ps.setString(4, cont.getPhone());
                ps.setString(5, cont.getEmail());
                ps.setString(6, cont.getAddress());
                ps.setInt(7, cont.getCid());

                if(ps.executeUpdate() != 0){
                    JOptionPane.showMessageDialog(null, "Contact Updated");
                    //contactIsCreated = true;
                }else{
                    JOptionPane.showMessageDialog(null, "Something went wrong");
                    //contactIsCreated = false;
                }

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //return contactIsCreated;
        }


    }


    ////Metoda umożliwiająca usuwanie kontaktu
    public void deleteContact(int cid) {

        Connection con = MyConnectionBuilder.getConnection();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("DELETE FROM `mycontact` WHERE `id` = ?" );
            ps.setInt(1, cid);

            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Contact Deleted");
                //contactIsCreated = true;
            }else{
                JOptionPane.showMessageDialog(null, "Something went wrong");
                //contactIsCreated = false;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //create a list of contact

    public ArrayList<Contact> contactList(int userId){

        ArrayList<Contact> list = new ArrayList<Contact>();
        Connection con = MyConnectionBuilder.getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT `id`, `firstname`, `lastname`, `groupc`, `phone`, `email`, `address`, `picture` FROM `mycontact` WHERE userid =" + userId);
            Contact cont;

            while(rs.next()) {
                cont = new Contact(rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("groupc"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getBytes("picture"),
                        userId);

                list.add(cont);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }
}
