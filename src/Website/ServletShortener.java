package Website;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Website.Session;

import java.util.regex.*;

@WebServlet("/shortener/*")
public class ServletShortener extends HttpServlet {
private static final long serialVersionUID = 1L;
private String regexURL = "^(https?|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

	public ServletShortener() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Path info "+request.getPathInfo());
		String shortenedLink = request.getPathInfo();
		if(shortenedLink == null)
			response.sendRedirect(request.getContextPath() + "/home");
		
		try {	
			PreparedStatement shortLinkToLink = null;
			
			StringBuilder sb = new StringBuilder(shortenedLink);
			sb.deleteCharAt(0);
			shortenedLink = sb.toString();
			
			System.out.println("After shortening " + shortenedLink);
			
			    Class.forName( "com.mysql.jdbc.Driver" );
			   
	      String url = "jdbc:mysql://localhost:3306/breizhlink";
	      String user = "root";
	      String passwd = "";
	      
	      Connection conn = DriverManager.getConnection(url, user, passwd);
	         
	      Statement statement =  conn.createStatement();
	      
	      String userStatement = "SELECT * FROM breizhlink.link WHERE link_shortened = ?";
	      
	      shortLinkToLink = (PreparedStatement)conn.prepareStatement(userStatement);
	      
	      shortLinkToLink.setString(1, shortenedLink);
	      
	      ResultSet rs = shortLinkToLink.executeQuery();
	      
	      String link;
	      
	      if (rs.next()) {
	    	  link = rs.getString("link");
            System.out.println("result " + link);
            response.sendRedirect(link);
	      }else {
	    	request.setAttribute("erreur", "The link was not found");
	    	System.out.println("fail rs");
	    	response.sendRedirect(request.getContextPath() + "/home");
	      }
	      
	      statement.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String returnJSP = "/WEB-INF/result.jsp";
		try {
			
			System.out.println("test works like a charm");
			String link = request.getParameter("URL");
			String linkShortened = new RandomString(6).nextString();
			
			Pattern pattern = Pattern.compile(regexURL);
	        Matcher matcher = pattern.matcher(link);
	        
	        
	        if(!matcher.find()) {
	        	System.out.println("regex failed");
	        	request.setAttribute("erreur", "Provided text is not an URL");
	        	returnJSP = "/WEB-INF/home.jsp";
	        }else {
	        	PreparedStatement addShortLink = null;
				
				System.out.println(link + linkShortened);
				
				Class.forName( "com.mysql.jdbc.Driver" );

				String url = "jdbc:mysql://localhost:3306/breizhlink";
				String user = "root";
				String passwd = "";
				     
				Connection conn = DriverManager.getConnection(url, user, passwd);
				   
				Statement statement =  conn.createStatement();
				  
				String userStatement = "INSERT INTO `breizhlink`.`link` (`idlink`,`link`,`link_shortened`)VALUES(null,?,?);";
				  
				addShortLink = (PreparedStatement)conn.prepareStatement(userStatement);
				  
				addShortLink.setString(1, link);
				addShortLink.setString(2, linkShortened);
	        	
	        	
	        	
			  	System.out.println(linkShortened);
				request.setAttribute("URLshortened", request.getScheme() + "://" +  
			             request.getServerName() +  ":" + request.getServerPort() +  "/server_10" +"/shortener/" + linkShortened);
				int rs = addShortLink.executeUpdate();
				System.out.println(rs);
				statement.close();
	        }
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Fail " + ex.getMessage());
			
		}
		this.getServletContext().getRequestDispatcher(returnJSP).forward(request, response);
    }
}
