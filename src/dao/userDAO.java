package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Website.Session;

public class userDAO {
	public ArrayList<User> users;
	
	Statement statement;
	Connection connexion;
	
	public userDAO()throws SQLException {
		try {
		    Class.forName( "com.mysql.jdbc.Driver" );
		   
			
		} catch ( ClassNotFoundException e ) {
		}
		String url = "jdbc:mysql://localhost:3306/jee_tutorial";
		String utilisateur = "root";
		String motDePasse = "";
		connexion = null;
		connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		statement = connexion.createStatement();
	}
	
	public ArrayList<User> getAll() {
		try {

			PreparedStatement userConnected = null;

			String userStatement = "SELECT * FROM breizhlink.user";

			userConnected = (PreparedStatement)connexion.prepareStatement(userStatement);

			ResultSet rs = userConnected.executeQuery();

			ArrayList<Link> liens = new ArrayList<Link>();

			while (rs.next()) {
				User userTemp = new User();
				userTemp.idUser = rs.getInt("idUser");
				userTemp.Login = rs.getString("Login");

				this.users.add(userTemp);
			}

			statement.close();

			return this.users;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUser(String aUser){
		try {
			
			PreparedStatement userConnected = null;
	      
	      String userStatement = "SELECT * FROM `breizhlink`.`user` WHERE Login = ?";
	      
	      userConnected = (PreparedStatement)connexion.prepareStatement(userStatement);
	      
	      userConnected.setString(1, aUser);
	      
	      ResultSet rs = userConnected.executeQuery();
	      if (rs.next()) {
	    	User resultUser = new User();
	    	resultUser.Login = rs.getString("Login");
	    	resultUser.password = rs.getString("password");
	    	resultUser.idUser = rs.getInt("idUser");
	    	statement.close();
	    	return resultUser;
	    	
	      }else {
	    	statement.close();
	    	return null;
	      }
	      
	      
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void insert(User aUser) {
		try {
			PreparedStatement userConnected = null;

			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/jee_tutorial";
			String user = "root";
			String passwd = "";

			Connection conn = DriverManager.getConnection(url, user, passwd);

			Statement statement =  conn.createStatement();

			String userStatement = "INSERT INTO `breizhlink`.`user`" + 
					"(`idUser`,`Login`,`password`)" + 
					"VALUES (null,?,?);";

			userConnected = (PreparedStatement)conn.prepareStatement(userStatement);

			userConnected.setString(1, aUser.Login);
			userConnected.setString(2, aUser.password);			

			int rs = userConnected.executeUpdate();

			statement.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
