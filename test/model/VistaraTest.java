package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.EmptyFieldsException;
import exceptions.InvalidUserException;
import exceptions.RepeatedUsernameException;

class VistaraTest {

	private Vistara vistara;
	
	public void setupScenary1() {
		vistara = new Vistara();
	}
	
	public void setupScenary2() {
		vistara = new Vistara(); 
		User user = new User("Andres", "and@yt.com", "hola123");
		user.setDescription("Artista");
		vistara.setRootUser(user);
	}
	
	@Test
	public void testAddUser() {
		
		setupScenary1();
		User user1 = new User("Andres", "and@yt.com", "Hola123");
		assertTrue(vistara.addUser(user1) && vistara.getRootUser() != null);
		
		setupScenary2();
		User user2 = new User("Cami", "cam@yt.com", "cam456");
		assertTrue(vistara.addUser(user2) && vistara.getRootUser().getRightUser() != null);
		
		try {
			setupScenary1();
			User user3 = new User("", "", "Hola123");
			vistara.addUser(user3);
		} catch (EmptyFieldsException e) {
			assertTrue(true);
		}
		
		try {
			User user4 = new User("Andres", "mx@yt.com", "cam456");
			vistara.addUser(user4);
		} catch (RepeatedUsernameException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testLogin() {
		
		setupScenary1();
		String username1 = "Andres";
		String password1 = "Hola123";
		try {
			vistara.login(username1, password1);
		} catch (InvalidUserException e){
			assertTrue(true);
		}
		
		setupScenary2();
		String username2 = "Andres";
		String password2 = "mal";
		try {
			vistara.login(username2, password2);
		} catch (InvalidUserException e){
			assertTrue(true);
		}
		
		setupScenary2();
		String username3 = "Andres";
		String password3 = "Hola123";
		assertTrue(vistara.login(username3, password3));
	}

}
