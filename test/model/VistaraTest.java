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
	
	public void setupScenary3() {
		vistara = new Vistara(); 
		try {
			vistara.addUser("Andres", "and@yt.com", "hola123");
			vistara.addUser("Camilo", "per@yt.com", "now1");
		} catch (RepeatedUsernameException | EmptyFieldsException e) {
			e.printStackTrace();
		}
	}
	
	public void setupScenary4() {
		vistara = new Vistara(); 
		vistara.addCategory("Politica");
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
	
	
	@Test
	public void testEditUser(){
		
		String newUsername = "Camilo";
		String newPassword = "123";
		String newEmail = "me@po.co";
		String newDesc = "Artist";
		String newPic = null;

		setupScenary2();
		try {
			vistara.editUser(vistara.getRootUser(), newUsername, newPassword, newEmail,
							 newDesc, newPic);
		} catch (RepeatedUsernameException | EmptyFieldsException e1) {
			fail();
		} 
		User editedUser;
		try {
			editedUser = vistara.searchUser(newUsername);
			assertEquals(newUsername, editedUser.getUsername());
			assertEquals(newPassword, editedUser.getPassword());
			assertEquals(newEmail, editedUser.getEmail());
			assertEquals(newDesc, editedUser.getDescription());
			assertEquals(newPic, editedUser.getProfilePic());
		} catch (InvalidUserException e1) {
			fail();
		}
		
		
		setupScenary3();
		String toEditUsername = "Andres";
		User toEditUser;
		try {
			toEditUser = vistara.searchUser(toEditUsername);
			vistara.editUser(toEditUser, newUsername, newPassword, newEmail,
					 	newDesc, newPic);		
		}catch (RepeatedUsernameException e) {
			assertTrue(true);
		}catch (EmptyFieldsException | InvalidUserException e) {
			fail();
		}
		
		setupScenary2();
		try {
			toEditUser = vistara.searchUser(toEditUsername);
			vistara.editUser(toEditUser, "", newPassword, newEmail,
							 newDesc, newPic);
		}catch (RepeatedUsernameException | InvalidUserException e) {
			fail();
		}catch (EmptyFieldsException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testSearchUser() {
		
		setupScenary2();
		String username = "Andres";
		User user;
		try {
			user = vistara.searchUser(username);
			assertEquals(username, user.getUsername());
		} catch (InvalidUserException e) {
			fail();
		}
		
		
		setupScenary3();
		username = "Camilo";
		try {
			user = vistara.searchUser(username);
			assertEquals(username, user.getUsername());
		} catch (InvalidUserException e1) {
			e1.printStackTrace();
		}
		
		
		setupScenary2();
		username = "Camilo";
		try {
			user = vistara.searchUser(username);
			fail();
		} catch(NullPointerException e) {
			fail();
		} catch (InvalidUserException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testSearchCategory() {
		
		String category = "Politica";
		
		setupScenary1();
		Category categoryTest = vistara.searchCategory(category);
		try {
			categoryTest.getName();
		}catch(NullPointerException e) {
			assertTrue(true);
		}
		
		setupScenary4();
		categoryTest = vistara.searchCategory(category);
		try {
			assertEquals(category, categoryTest.getName());
		}catch(NullPointerException e) {
			fail();
		}
	}
	
	@Test
	public void testFollowUser() {
		
		String followed = "Andres";
		String follower = "Camilo";
		
		setupScenary3();
		try {
			User followerUser = vistara.searchUser(follower);
			int previousFollowing = followerUser.getFollowing().size();
			
			User followedUser = vistara.searchUser(followed);
			int previousFollowers = followedUser.getFollowers().size();
			
			vistara.followUser(followerUser, followed);
			
			assertEquals(previousFollowers + 1, followedUser.getFollowers().size());
			assertEquals(previousFollowing + 1, followerUser.getFollowing().size());
			
		} catch (InvalidUserException e) {
			fail();
		}
		
		setupScenary2();
		try {
			User followedUser = vistara.searchUser(followed);
			int previousFollowers = followedUser.getFollowers().size();
		} catch (InvalidUserException e) {
			assertTrue(true);
		}
		
		
		
	}

}
