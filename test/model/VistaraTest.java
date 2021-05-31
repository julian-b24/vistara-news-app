package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.EmptyFieldsException;
import exceptions.InvalidUserException;
import exceptions.RepeatedUsernameException;
import javafx.scene.image.Image;

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
	
	public void setupScenary3() {
		vistara = new Vistara(); 
		try {
			vistara.addUser("Andres", "and@yt.com", "hola123");
			vistara.addUser("Camilo", "per@yt.com", "now1");
		} catch (RepeatedUsernameException | EmptyFieldsException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddUser() {
		
		setupScenary1();
		try {
			assertTrue(vistara.addUser("Andres", "and@yt.com", "Hola123") && vistara.getRootUser() != null);
		} catch (RepeatedUsernameException e2) {
			fail();
		} catch (EmptyFieldsException e) {
			fail();
		}
		
		setupScenary2();
		try {
			assertTrue(vistara.addUser("Cami", "cam@yt.com", "cam456") && vistara.getRootUser().getRightUser() != null);
		} catch (RepeatedUsernameException e1) {
			fail();
		} catch (EmptyFieldsException e) {
			fail();
		}
		
		try {
			setupScenary1();
			vistara.addUser("", "", "Hola123");
		} catch (EmptyFieldsException e) {
			assertTrue(true);
		} catch (RepeatedUsernameException e) {
			fail();
		}
		
		setupScenary2();
		try {
			vistara.addUser("Andres", "mx@yt.com", "cam456");
		} catch (EmptyFieldsException e) {
			fail();
		}catch (RepeatedUsernameException e) {
			assertTrue(true);
		}
	}
	
	
	@Test
	public void testLogin() {
		
		setupScenary1();
		String username1 = "Andres";
		String password1 = "Hola123";
		try {
			vistara.verifyLogin(username1, password1);
		} catch (InvalidUserException e){
			assertTrue(true);
		} catch (EmptyFieldsException e) {
			fail();
		}
		
		setupScenary2();
		String username2 = "Andres";
		String password2 = "mal";
		try {
			vistara.verifyLogin(username2, password2);
		} catch (InvalidUserException e){
			assertTrue(true);
		} catch (EmptyFieldsException e) {
			fail();
		}
		
		setupScenary2();
		String username3 = "Andres";
		String password3 = "hola123";
		try {
			assertTrue(vistara.verifyLogin(username3, password3)!=null);
		} catch (InvalidUserException | EmptyFieldsException e) {
			fail();
		}
	}
	
	/*
	@Test
	public void testEditUser(){
		
		String newUsername = "Camilo";
		String newPassword = "123";
		String newEmail = "me@po.co";
		String newDesc = "Artist";
		Image newPic = null;

		setupScenary2();
		assertTrue(vistara.editUser(newUsername, newPassword, newEmail,
									newDesc, newPic));
		
		setupScenary3();
		try {
			vistara.editUser(newUsername, newPassword, newEmail,
							 newDesc, newPic);
		}catch (RepeatedUsernameException e) {
			assertTrue(true);
		}catch (EmptyFieldsException e) {
			fail();
		}
		
		setupScenary2();
		try {
			vistara.editUser("", newPassword, newEmail,
							 newDesc, newPic);
		}catch (RepeatedUsernameException e) {
			fail();
		}catch (EmptyFieldsException e) {
			assertTrue(true);
		}
	}*/

}
