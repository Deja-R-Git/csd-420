package com.mycompany.setup;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Setup extends JFrame implements ActionListener {

  Connection con;
  Statement stmt;
  
  JTextField id_field, first_name_field, last_name_field, team_field;
  JButton display_button, update_button;
  JLabel status_label;

  public Setup() {
  
    try {
  //connection to the database  
      Class.forName("com.mysql.cj.jdbc.Driver");
      String url = "jdbc:mysql://127.0.0.1:3306/databasedb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
 //user and password     
      con = DriverManager.getConnection(url, "student1", "pass");
      stmt = con.createStatement();
      System.out.println("Connected to databasedb");
      
    } catch (Exception e) {
    
      System.out.println("Error connection to database.");
   //helped determine user permission error that stopped connection to database
      e.printStackTrace();
      System.exit(0);
    
    }
    
    // This is the GUI setup for our program
    
    setTitle("Fans Database Program Manager");
    setSize(400,250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(6,2));
    
  //labels for fields
    add(new JLabel("Fan id"));
    id_field = new JTextField();
    add(id_field);
    
    add(new JLabel("First name"));
    first_name_field = new JTextField();
    add(first_name_field);
    
    add(new JLabel("Last name"));
    last_name_field = new JTextField();
    add(last_name_field);
    
    add(new JLabel("Favorite team"));
    team_field = new JTextField();
    add(team_field);
 //Created buttons for displaying and updating data   
    display_button = new JButton("Display");
    update_button = new JButton("Update");
 //sets up what action was done   
    display_button.addActionListener(this);
    update_button.addActionListener(this);
    
    add(display_button);
    add(update_button);
    
    status_label = new JLabel("Ready");
    add(status_label);
    
    setVisible(true);
    
    
    }
//determines which button was pressed and which method to use
	@Override
	public void actionPerformed(ActionEvent e) {
    
      if(e.getSource() == display_button) {
      
        display_record();
     
      } else if(e.getSource() == update_button) {
      
        update_record();
      }
    
    }

	private void display_record() {
    
      try {
   //query for retrieving data from database  
        int id = Integer.parseInt(id_field.getText());
        String query = "SELECT * FROM fans WHERE ID=" + id;
   //resultset object and query execution
        ResultSet rs = stmt.executeQuery(query);
   //iterating through resultset     
        if(rs.next()) {
        
          first_name_field.setText(rs.getString("firstname"));
          last_name_field.setText(rs.getString("lastname"));
          team_field.setText(rs.getString("favoriteteam"));
          status_label.setText("Record displayed");
          System.out.println("Record is displayed for ID " + id);
        
        } else {
        
          clear_fields();
          status_label.setText("No record found for this id");
          System.out.println("No record found for ID " + id);
        
        
        }
        
        rs.close();
      
      } catch (Exception e) {
      
        System.out.println("Error when displaying record");
        e.printStackTrace();
      }
    
    
    }

	private void update_record() {
    
      try {
      
        int id = Integer.parseInt(id_field.getText());
        String first_name = first_name_field.getText();
        String last_name = last_name_field.getText();
        String team = team_field.getText();
        String update_query = "UPDATE fans SET firstname='" + first_name + "', lastname='" + last_name + "', favoriteteam='" + team + "' WHERE ID=" + id;
        int rows = stmt.executeUpdate(update_query);
        
        if(rows > 0) {
        
          status_label.setText("Record updated");
          System.out.println("Record updated for ID " + id);
    //
        } else {
          status_label.setText("Unable to update for the user");
          System.out.println("No record found to update for ID " + id);
        
        
        }
        
      
      } catch (Exception e) {
    //exception if update doesnt work  
        System.out.println("Error when updating record");
        e.printStackTrace();
      }
    
    
    }

	private void clear_fields() {
    //clears fields if ID does not exists
      first_name_field.setText("");
      last_name_field.setText("");
      team_field.setText("");
    
    }

	public static void main(String[] args) {
    
      Setup app = new Setup();
    
    }
  
  
  }

