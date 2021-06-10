package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

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
	
	public void setupScenary5() {
		vistara = new Vistara();
		String username = "Camilo";
		String email = "per@yt.com";
		String password = "now1";
		try {
			vistara.addUser(username, email, password);
		} catch (RepeatedUsernameException | EmptyFieldsException e) {
			e.printStackTrace();
		}
		
		String title = "Avalancha";
		String content = "Armero";
		LocalDateTime date = LocalDateTime.of(2020, 3, 12, 12, 30);
		State state = State.VERIFIED;
		try {
			vistara.createPost(title, content, "hola", vistara.searchUser(username), "link");
			vistara.getPosts().get(0).setState(state);
			vistara.getPosts().get(0).setDate(date);
		} catch (InvalidUserException | EmptyFieldsException e) {
			e.printStackTrace();
		}
	}
	
	public void setupScenary6() {
		setupScenary2();
		vistara.addCategory("Politica");
	}
	
	public void setupScenary7() {
		try {
			setupScenary6();
			Moderator mod = new Moderator("Camilo", "per@ytmcom", "now1");
			mod.setDescription("Pinto");
			vistara.addUser(mod);
			vistara.getMods().add(mod);
		} catch (RepeatedUsernameException e) {
			e.printStackTrace();
		}
	}
	
	public void setupScenary8() {
		setupScenary7();
		String title = "Avalancha";
		String content = "Armero";
		LocalDateTime date = LocalDateTime.of(2020, 3, 12, 12, 30);
		State state = State.VERIFIED;
		try {
			vistara.createPost(title, content, "Politica", vistara.searchUser("Andres"), "link");
			vistara.getPosts().get(0).setState(state);
			vistara.getPosts().get(0).setDate(date);
		} catch (InvalidUserException | EmptyFieldsException e) {
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
			followedUser.getFollowers().size();
		} catch (InvalidUserException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testReactPost() {
		
		setupScenary5();
		String username = "Camilo";
		String title = "Avalancha";
		
		Post post = vistara.searchPost(title);
		double reactions = post.getReactions();
		User user;
		try {
			user = vistara.searchUser(username);
			int reactedPosts = user.getReactedPosts().size();
			
			vistara.reactToPost(post, user);
			
			assertEquals(reactions + 1, post.getReactions());
			assertEquals(reactedPosts + 1, user.getReactedPosts().size());
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreatePost() {
		setupScenary6();
		String title = "Title";
		String content = "content";
		String category = "Politica";
		String fullNewLink = "go.com";
		try {
			vistara.createPost(title, content, category, vistara.getRootUser(), fullNewLink);
		} catch (EmptyFieldsException e) {
			fail();
		}
		
		Post post = vistara.searchPost(title);
		assertEquals(title, post.getTitle());
		assertEquals(content, post.getContent());
		assertEquals(fullNewLink, post.getFullNewLink());
		assertEquals(category, post.getCategory().getName());
		
		setupScenary6();
		title = "";
		try {
			vistara.createPost(title, content, category, vistara.getRootUser(), fullNewLink);
			fail();
		} catch (EmptyFieldsException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testConvertUser() {
		fail();
	}
	
	@Test
	public void testAddCategory() {
	
		String categoryName = "Religion";
		setupScenary1();
		vistara.addCategory(categoryName);
		boolean oneElement = vistara.getRootCategory() != null && vistara.getRootCategory().getLeftCategory() == null &&
							 vistara.getRootCategory().getRightCategory() == null;
		assertTrue(oneElement);
		
		setupScenary4();
		vistara.addCategory(categoryName);
		boolean twoElement = vistara.getRootCategory() != null && vistara.getRootCategory().getLeftCategory() == null &&
				 			 vistara.getRootCategory().getRightCategory() != null;
		assertTrue(twoElement);
	}
	
	@Test
	public  void testDeletePost() {
		setupScenary8();
		String title = "Avalancha";
		try {
			vistara.deletePost("Andres", "Camilo", vistara.searchPost(title));
			assertEquals(0, vistara.getPosts().size());
			assertEquals(0, vistara.searchUser("Andres").getOwnPosts().size());
			assertEquals(0, vistara.getMods().get(0).getPendingPosts().size());
		} catch (InvalidUserException | EmptyFieldsException e) {
			fail();
		}
		
		setupScenary8();
		title = "";
		try {
			vistara.deletePost("Andres", "Camilo", vistara.searchPost(title));
			assertEquals(0, vistara.getPosts().size());
			assertEquals(0, vistara.searchUser("Andres").getOwnPosts().size());
			assertEquals(0, vistara.getMods().get(0).getPendingPosts().size());
		} catch (InvalidUserException e) {
			fail();
		} catch (EmptyFieldsException e) {
			assertTrue(true);
		}
		
		setupScenary7();
		title = "Avalancha";
		try {
			vistara.deletePost("Andres", "Camilo", vistara.searchPost(title));
			assertEquals(0, vistara.getPosts().size());
			assertEquals(0, vistara.searchUser("Andres").getOwnPosts().size());
			assertEquals(0, vistara.getMods().get(0).getPendingPosts().size());
		} catch (InvalidUserException | EmptyFieldsException e) {
			fail();
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testVerifyPost() {
		fail();
	}

}
