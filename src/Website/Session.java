package Website;

public class Session {
	static boolean LOGGED = false; 
	static String USERNAME = "";
	static int ID_USER;
	
	static void logOut() {
		LOGGED = false;
	}
}
