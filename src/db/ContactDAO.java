/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import static db.DB.DB_URL;
import static db.DB.PASS;
import static db.DB.USER;
import java.sql.*;
import java.util.*;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class ContactDAO extends Application {

    Label id = new Label("id");
    Label Fname = new Label("First Name");
    Label Mname = new Label("Middle Name");
    Label Lname = new Label("Last Name");
    Label email = new Label("Email");
    Label phone = new Label("Phone");

    TextField id_field = new TextField();
    TextField Fname_field = new TextField();
    TextField Mname_field = new TextField();
    TextField Lname_field = new TextField();
    TextField email_field = new TextField();
    TextField phone_field = new TextField();

    Button AddBtn = new Button("Add");
    Button DeleteBtn = new Button("Delete");
    Button firstBtn = new Button("First");
    Button lastBtn = new Button("Last");
    Button PrevBtn = new Button("Previous");
    Button NextBtn = new Button("Next");
    //insert update delete
    GridPane pane = new GridPane();
    Group group = new Group();
    int currentIndex = -1;
    static Vector<ContactPerson> ContactVector;

    @Override
    public void init() {
//        id_field.setDisable(true);
        //Setting size for the pane  
        pane.setMinSize(400, 200);
        //Setting the padding  
        pane.setPadding(new Insets(10, 10, 10, 10));
        //Setting the vertical and horizontal gaps between the columns 
        pane.setVgap(5);
        pane.setHgap(5);
        //Setting the Grid alignment 
        pane.setAlignment(Pos.CENTER);
        //adding items
        pane.add(id, 0, 0);
        pane.add(id_field, 1, 0);
        pane.add(Fname, 0, 1);
        pane.add(Fname_field, 1, 1);
        pane.add(Mname, 0, 2);
        pane.add(Mname_field, 1, 2);
        pane.add(Lname, 0, 3);
        pane.add(Lname_field, 1, 3);
        pane.add(email, 0, 4);
        pane.add(email_field, 1, 4);
        pane.add(phone, 0, 5);
        pane.add(phone_field, 1, 5);
        pane.add(firstBtn, 0, 7);
        pane.add(lastBtn, 1, 7);
        pane.add(PrevBtn, 0, 8);
        pane.add(NextBtn, 1, 8);
        pane.add(AddBtn, 0, 9);
        pane.add(DeleteBtn, 1, 9);
        firstBtn.setPrefWidth(150);
        lastBtn.setPrefWidth(150);
        PrevBtn.setPrefWidth(150);
        NextBtn.setPrefWidth(150);
        AddBtn.setPrefWidth(150);
        DeleteBtn.setPrefWidth(150);

        group = new Group();
        group.getChildren().addAll(pane);

        //////////////////////Next
        NextBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentIndex < ContactVector.size() - 1) {
                    currentIndex++;
                    ContactPerson current = ContactVector.elementAt(currentIndex);
                    id_field.setText(Integer.toString(current.getId()));
                    Fname_field.setText(current.getFirst_name());
                    Mname_field.setText(current.getMiddle_name());
                    Lname_field.setText(current.getLast_name());
                    email_field.setText(current.getEmail());
                    phone_field.setText(current.getPhone());
                }
            }
        });

        //////////////////////Previous
        PrevBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentIndex > 0) {
                    currentIndex--;
                    ContactPerson current = ContactVector.elementAt(currentIndex);
                    id_field.setText(Integer.toString(current.getId()));
                    Fname_field.setText(current.getFirst_name());
                    Mname_field.setText(current.getMiddle_name());
                    Lname_field.setText(current.getLast_name());
                    email_field.setText(current.getEmail());
                    phone_field.setText(current.getPhone());
                }
            }
        });

        //////////////////////First
        firstBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentIndex = 0;
                ContactPerson current = ContactVector.elementAt(currentIndex);
                id_field.setText(Integer.toString(current.getId()));
                Fname_field.setText(current.getFirst_name());
                Mname_field.setText(current.getMiddle_name());
                Lname_field.setText(current.getLast_name());
                email_field.setText(current.getEmail());
                phone_field.setText(current.getPhone());
            }
        });
        //////////////////////Last
        lastBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ContactPerson current = ContactVector.elementAt(ContactVector.size() - 1);
                id_field.setText(Integer.toString(current.getId()));
                Fname_field.setText(current.getFirst_name());
                Mname_field.setText(current.getMiddle_name());
                Lname_field.setText(current.getLast_name());
                email_field.setText(current.getEmail());
                phone_field.setText(current.getPhone());

            }
        });

        //////////////////////Add
        AddBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String DB_URL = "jdbc:mysql://localhost:3306/" + "javalab?zeroDateTimeBehavior=convertToNull";
                try {
                    Connection conn = DriverManager.getConnection(DB_URL);
                    Statement stat = conn.createStatement();
                    String query = "insert into contacts(id,first_name, middle_name, last_name, phone, email) values (?,?,?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(query);
                    int id = Integer.valueOf(id_field.getText());
                    String first_name = Fname_field.getText();
                    String middle_name = Mname_field.getText();
                    String last_name = Lname_field.getText();
                    String phone = phone_field.getText();
                    String email = email_field.getText();
                    ps.setInt(1, id);
                    ps.setString(2, first_name);
                    ps.setString(3, middle_name);
                    ps.setString(4, last_name);
                    ps.setString(5, phone);
                    ps.setString(6, email);
                    ps.execute();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //////////////////////Delete
        DeleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String DB_URL = "jdbc:mysql://localhost:3306/" + "javalab?zeroDateTimeBehavior=convertToNull";
                try {
                    Connection conn = DriverManager.getConnection(DB_URL);
                    Statement stat = conn.createStatement();
                    String id = id_field.getText();
                    String query = "delete from contacts where id = "+id+" ";
//                    ResultSet st = stat.executeQuery(query);
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.execute();
                    id_field.clear();
                    Fname_field.clear();
                    Mname_field.clear();
                    Lname_field.clear();
                    email_field.clear();
                    phone_field.clear();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } 
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(group, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Contacts");
        primaryStage.show();
    }

    public static void main(String[] args) {

        DB myDb = new DB();
        myDb.connect();
        ContactVector = myDb.getContacts();
        myDb.closeConnection();
        Application.launch(args);
    }
}
