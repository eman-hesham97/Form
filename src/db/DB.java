/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package db;

import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class DB {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/" + "javalab?zeroDateTimeBehavior=convertToNull";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String USER = "root";
    public static final String PASS = "";
    Connection con;

    /**
     * @param args the command line arguments
     */
    public DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception ex) {
            ex.getMessage();
        }

    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<ContactPerson> getContacts() {
        Vector<ContactPerson> contactVector = new Vector<ContactPerson>();
        try {
            Statement stat = con.createStatement();
            String query = "select * from contacts";
            ResultSet st = stat.executeQuery(query);
            while (st.next()) {
                int id = st.getInt(1);
                String first_name = st.getString(2);
                String middle_name = st.getString(3);
                String last_name = st.getString(4);
                String phone = st.getString(5);
                String email = st.getString(6);
                ContactPerson contact = new ContactPerson(id, first_name, middle_name, last_name, phone, email);
                contactVector.add(contact);

            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return contactVector;

    }
}