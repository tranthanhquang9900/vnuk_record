package vn.edu.vnuk.record.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import vn.edu.vnuk.record.jdbc.ConnectionFactory;
import vn.edu.vnuk.record.model.Contact;

public class ContactDao {
	private Connection connection;
	
	Scanner sc = new Scanner(System.in);
	
	public ContactDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	public void create(Contact contact) throws SQLException {
		
		String sqlQuery = "insert into contacts (name, email, address, date_of_register) "
                +	"values (?, ?, ?, ?)";
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getEmail());
            statement.setString(3, contact.getAddress());
            statement.setDate(4, new java.sql.Date(
                            contact.getDateOfRegister().getTimeInMillis()
                    )
            );

            // 	Executing statement
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Insert ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			connection.close();
	}
	
	@SuppressWarnings("finally")
	public List<Contact>read()throws SQLException{
		PreparedStatement statement;
		String sqlQuery = "select * from contacts";
		List<Contact> contacts = new ArrayList<Contact>();

		try {

			statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				contacts.add(buildContact(result));
				
			}
			result.close();
			// statement.executeQuery();
			statement.close();
	        System.out.println("   DATA successfully readed");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			
			connection.close();
			return contacts;
			
		}
		
	}
	
	@SuppressWarnings("finally")
	public Contact read (long id) throws SQLException {
		PreparedStatement statement;

		String sqlQuery = "select * from contacts where id =" + id
				+ ";";
		Contact contact = new Contact();

		try {

			statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				contact = buildContact(result);
				
			}
			result.close();
			// statement.executeQuery();
			statement.close();
	        System.out.println("   DATA successfully readed ");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			
			return contact;
			
		}
	}
	
	public void update(Long id, Contact newContact) throws SQLException {

		Contact contact = new Contact();

		String sqlQuery = "update contacts set name = ?, email = ?, address = ? where id = " + id;

		PreparedStatement statement;

		try {
			contact = read(id);
			statement = connection.prepareStatement(sqlQuery);

			statement.setString(1, newContact.getName());
			statement.setString(2, newContact.getEmail());
			statement.setString(3, newContact.getAddress());

			if(contact != null) {
				int rowsUpdated = statement.executeUpdate();

				if(rowsUpdated > 0) {
					System.out.println("Updated for ID number: " + id);
				} else {
					System.out.println("Error: ID NOT FOUND!");
				}
			} else {
				System.out.println("Error: ID NOT FOUND!");
			}

			statement.close();
		}

		catch(Exception e) {
			e.printStackTrace();
			connection.close();
		}

		finally {
			connection.close();
		}
	}
	
	public void delete(Long id) throws SQLException {
		
		Contact contact = new Contact();
		
		String sqlQuery = " delete from contacts where id = " + id
				+ ";";
		
		PreparedStatement statement;
		
		try {
			contact = read(id);
			statement = connection.prepareStatement(sqlQuery);

			int affectedRow = statement.executeUpdate();

			if(affectedRow == 0) {
				System.out.println("Error: ID NOT FOUND!");
			} else {
				System.out.println("Complete delete row number: " + affectedRow);
			}



			statement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			connection.close();
		}
		
		finally {
			connection.close();
		}
		
	}
	
	private Contact buildContact(ResultSet result) throws SQLException {
		Contact contact = new Contact();
		contact.setId(result.getLong("id"));
		contact.setName(result.getString("name"));
		contact.setAddress(result.getString("address"));
		contact.setEmail(result.getString("email"));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(result.getDate("date_of_register"));
		contact.setDateOfRegister(calendar);
		
		return contact;
		
	}
}