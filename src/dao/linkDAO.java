package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class linkDAO {
	
	Statement statement;
	Connection connexion;
	
	public linkDAO()throws SQLException {
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
	
	public ArrayList<Link> links;
	
	
	public ArrayList<Link> getAll() {
		try {

			PreparedStatement userConnected = null;

			String userStatement = "SELECT * FROM breizhlink.link";

			userConnected = (PreparedStatement)connexion.prepareStatement(userStatement);

			ResultSet rs = userConnected.executeQuery();

			this.links = new ArrayList<Link>();

			while (rs.next()) {
				Link lien = new Link();
				lien.idLink = rs.getInt("idLink");
				lien.link = rs.getString("link");
				lien.link_shortened = rs.getString("link_shortened");
				lien.link_isLocked = rs.getBoolean("link_isLocked");
				lien.link_pwd = rs.getString("link_pwd");
				lien.link_visits = rs.getInt("link_visits");

				this.links.add(lien);
			}

			statement.close();

			return this.links;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Link> getAllOfUser(int idUser){
		try {
		PreparedStatement userConnected = null;

		String userStatement = "SELECT * FROM breizhlink.link WHERE user_fk";

		userConnected = (PreparedStatement)connexion.prepareStatement(userStatement);

		userConnected.setInt(1, idUser);

		ResultSet rs = userConnected.executeQuery();

		ArrayList<Link> liens = new ArrayList<Link>();

		while (rs.next()) {
			Link lien = new Link();
			lien.idLink = rs.getInt("idLink");
			lien.link = rs.getString("link");
			lien.link_shortened = rs.getString("link_shortened");
			lien.link_isLocked = rs.getBoolean("link_isLocked");
			lien.link_pwd = rs.getString("link_pwd");
			lien.link_visits = rs.getInt("link_visits");

			liens.add(lien);
		}

		statement.close();

		return liens;
		}catch(Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public void insert(Link aLien) {
		try {
			PreparedStatement userConnected = null;

			String userStatement = "INSERT INTO `breizhlink`.`link`\r\n" + 
					"(`idlink`,`link`,`link_shortened`,`user_fk`,`link_visits`,`link_pwd`,`link_isLocked`)VALUES"+
					"(null,?,?,?,?,?,?)";

			userConnected = (PreparedStatement)connexion.prepareStatement(userStatement);

			userConnected.setString(1, aLien.link);
			userConnected.setString(2, aLien.link_shortened);
			userConnected.setInt(3, aLien.user_fk);
			userConnected.setInt(4, aLien.link_visits);
			userConnected.setString(5, aLien.link_pwd);
			userConnected.setBoolean(6, aLien.link_isLocked);
			

			ResultSet rs = userConnected.executeQuery();

			this.links = new ArrayList<Link>();

			if (rs.next()) {
				Link lien = new Link();
				lien.idLink = rs.getInt("idLink");
				lien.link = rs.getString("link");
				lien.link_shortened = rs.getString("link_shortened");
				lien.link_isLocked = rs.getBoolean("link_isLocked");
				lien.link_pwd = rs.getString("link_pwd");
				lien.link_visits = rs.getInt("link_visits");

				this.links.add(lien);
			}

			statement.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
