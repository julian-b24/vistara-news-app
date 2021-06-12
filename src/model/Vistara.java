package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import exceptions.EmptyFieldsException;
import exceptions.InvalidUserException;
import exceptions.RepeatedUsernameException;
import javafx.scene.image.Image;
import thread.ThreadAddPostFollowers;

public class Vistara implements ModeratorManagement{

	public final static String IMAGE_PATH = "file:imgs/";
	public final static int MIN_RATING = 5;
	public final static String SAVE_PATH_USERS = "data/system/users.txr";
	public final static String SAVE_PATH_MODS = "data/system/mods.txr";
	public final static String SAVE_PATH_CATEGORIES = "data/system/categories.txr";
	public final static String SAVE_PATH_POSTS = "data/system/posts.txr";
	public final static String SAVE_PATH_TRENDING = "data/system/trending.txr";
	public final static String SAVE_PATH_COMMENTS = "data/system/comments.txr";
	
	public final static String IMPORT_CSV_USERS = "data/csv/users.csv";
	public final static String IMPORT_CSV_CATEGORIES = "data/csv/categories.csv";
	public final static String IMPORT_CSV_POSTS = "data/csv/posts.csv";
	
	public final static String EXPORT_REPORT_POST_PATH = "data/reports/reportPost";
	public final static String REPORTS_EXTENSION = ".csv";
	public final static String PROFILE_PIC_EXTENSION = ".png";
	
	public final static String VERIFIED = "VERIFIED";
	public final static String FAKE = "FAKE_NEW";	
	
	private ArrayList<Moderator> mods;
	private User rootUser;
	private Category rootCategory;
	private ArrayList<Post> posts;
	private ArrayList<Post> trending;
	private ArrayList<Comment> comments;
	private Comment firstComment;
	private Comment lastComment;
	private int numCommVistara;
	
	public Vistara() {
		mods = new ArrayList<>();
		posts = new ArrayList<Post>();
		trending = new ArrayList<>();
		comments = new ArrayList<>();
		
		Moderator m = new Moderator("a", "a", "a");
		mods.add(m);
		rootUser = m;
		numCommVistara = 0;
	}

	/**
	 * addUser: Creates a new user and adds it to the binary tree of users<br>
	 * <b> pre </b> <br>
	 * <b> pos </b> <br>
	 * @param username String with the username of the new user
	 * @param email String with the email of the new user
	 * @param password String with the password of the new user
	 * @return added Boolean that determines if the user can be added or not
	 * @throws RepeatedUsernameException Exception 
	 * @throws EmptyFieldsException Exception
	 * @throws IOException Exception
	*/
	public boolean addUser(String username, String email, String password) throws RepeatedUsernameException, EmptyFieldsException, IOException {
		
		User user = new User(username, email, password);

		boolean added = true;
		
		if(username.isEmpty() || email.isEmpty() || password.isEmpty()) {
			throw new EmptyFieldsException(new String[] {username, password});
		}
		
		if(rootUser != null) {
			//Search for any coincidence with the username	
			boolean repeatedName = searchUserByName(user.getUsername());
			if(!repeatedName) {
				addUser(rootUser, user);
			}else {
				throw new RepeatedUsernameException(username);
			}
			
		}else {
			rootUser = user;
		}
		//saveUsersData();
		
		return added;
	}
	
	/**
	 * Adds an user to the BST and returns true in case it was added
	 * @param newUser User
	 * @return true in case the user was added, any otherwise it throws an exception
	 * @throws RepeatedUsernameException in case the name of the user to be added is already in use by other user in the BST
	 * @throws IOException Exception
	 * @throws RepeatedUsernameException Exception
	 */
	public boolean addUser(User newUser) throws RepeatedUsernameException, IOException {
		boolean added = true;
		if(rootUser != null) {
			//Search for any coincidence with the username	
			boolean repeatedName = searchUserByName(newUser.getUsername());
			if(!repeatedName) {
				addUser(rootUser, newUser);
			}else {
				throw new RepeatedUsernameException(newUser.getUsername());
			}
			
		}else {
			rootUser = newUser;
		}
		//saveUsersData();
		
		return added;
	}

	/**
	* addUser: Adds recursively, a new User to the binary tree of users<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current The current node of the binary tree
	* @param user The new User object that will be added
	*/
	private void addUser(User current, User user) {
		
		if(user.getUsername().compareTo(current.getUsername())<0) {
			if(current.getLeftUser() == null) {
				current.setLeftUser(user);
				user.setParentUser(current);
			}else {
				addUser(current.getLeftUser(), user);
			}
		}else {
			if(current.getRightUser() == null) {
				current.setRightUser(user);
				user.setParentUser(current);
			}else {
				addUser(current.getRightUser(), user);
			}
		}
	}

	/**
	* searchUserByName: Searches an user in the app binary tree of users by its name and return a boolean that determines if it found it or not<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param username The current node of the binary tree
	* @return The boolean that determines if the user was found or not.
	*/
	public boolean searchUserByName(String username) {
		return searchUserByName(rootUser, username);		
	}
	
	/**
	* searchUserByName: Searches recursively for an user in the app binary tree of users by its name and return a boolean that determines if it found it or not<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current The current node of the binary tree
	* @param username The String with the username of the searched user
	* @return The boolean that determines if the user was found or not.
	*/
	private boolean searchUserByName(User current, String username) {
		if(current == null) {
			return false;
		}		
	
		if(username.compareTo(current.getUsername())<0) {
			return searchUserByName(current.getLeftUser(), username);
			
		}else if(username.compareTo(current.getUsername())>0) {
			return searchUserByName(current.getRightUser(), username);
			
		}else {
			return true;
		}
	}

	/**
	* verifyLogin: Verifies that the username and password received belong to the same account, and that non of them are empty<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param username The String with the username of the user
	* @param password The String with the password of the user
	* @return searchedUser The searched User object if the account username exists. It is null if the user is not found.
	* @throws InvalidUserException Exception for invalid user
	* @throws EmptyFieldsException Exception for empty parameters
	*/
	public User verifyLogin(String username, String password) throws InvalidUserException, EmptyFieldsException {
		
		if(username.isEmpty() || password.isEmpty()) {
			throw new EmptyFieldsException(new String[] {username, password});
		}
		
		//search the user in the binary tree
		User searchedUser = searchUser(username);
		if(searchedUser == null) {
			throw new InvalidUserException(username, password);
		}
		
		if(!searchedUser.getPassword().equals(password)) {
			throw new InvalidUserException(username, password);
		}
		
		return searchedUser;
	}
	
	/**
	* searchUser: A method that searches a user in the list of users from the app and returns it<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param username The String with the username of the searched user
	* @return current The searched User object. It is null if the user is not found.
	* @throws InvalidUserException Exception
	*/
	public User searchUser(String username) throws InvalidUserException {
		return searchUser(rootUser, username);		
	}
	
	/**
	* searchUser: A recursive method that searches a user in the list of users from the app and returns it<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current The current node of the binary tree
	* @param username The String with the username of the searched user
	* @return current The searched User object. It is null if the user is not found.
	 * @throws InvalidUserException 
	*/
	private User searchUser(User current, String username) throws InvalidUserException {
		if(current == null) {
			throw new InvalidUserException(username, "");
		}
	
		if(username.compareTo(current.getUsername())<0) {
			return searchUser(current.getLeftUser(), username);
			
		}else if(username.compareTo(current.getUsername())>0) {
			return searchUser(current.getRightUser(), username);
			
		}else {
			return current;
		}
	}
	
	/**
	 * Creates a post and add it to the list of posts of vistara, the author and the followers of the author
	 * @param title, String, the title of the post. It must be different from null and an empty string
	 * @param content, String, the content of the post. It must be different from null and an empty string
	 * @param categoryName, String, name of the category. It must be different from null and an empty string
	 * @param author, User, the user that is creating the post
	 * @param link String link to the whole new
	 * @throws EmptyFieldsException in case any of the parameters is an empty string
	 */
	public void createPost(String title, String content, String categoryName, User author, String link) throws EmptyFieldsException {
		
		if(title.isEmpty() || content.isEmpty() || categoryName.isEmpty() || link.isEmpty()) {
			throw new EmptyFieldsException(new String[] {title, content, categoryName, link});
		}else {

			Category category = searchCategory(categoryName);
			LocalDateTime date = LocalDateTime.now();
			Post post = new Post(author.getUsername(), title, content, category, date, link);
			posts.add(post);
			author.getOwnPosts().add(post);
			ThreadAddPostFollowers addThread = new ThreadAddPostFollowers(author, post);
			addPostToModeratorList(post);
			addThread.start();
		}
		sortPosts();
	}
	
	/**
	 * Creates a new comment in a specific post. The comment will be added to the post's comments linked list
	 * @param author, User, the author of the comment. It must be different from null.
	 * @param content, String, the content of the comment. It must be different from null and an empty string
	 * @param post, Post, the post to be commented. It must be different from null.
	 */
	public void createComment(User author, String content, Post post) {
		Comment newComment = new Comment(author.getUsername(), content);
		post.addComment(newComment);
		comments.add(newComment);
		addCommentToVistara(newComment);
	}
	
	private void addCommentToVistara(Comment newComment) {
		if(firstComment == null) {
			firstComment = newComment;
			lastComment = firstComment;
			firstComment.setNextCommentVistara(lastComment);
		}else {
			lastComment.setNextCommentVistara(newComment);
			newComment.setNextCommentVistara(firstComment);
			lastComment = newComment;
		}
		numCommVistara++;
		System.out.println(numCommVistara);
	}

	/**
	* addPostToModeratorList: Adds a post to the pending list of the first moderator in the list<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param post The new post
	*/
	private void addPostToModeratorList(Post post) {
		if(mods.size() > 0) {
			mods.get(0).getPendingPosts().add(post);
			reOrderModerators();
		}
	}

	/**
	* addCategory: Adds a new category into the binary three of categories<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param newCat The new category thats going to be added
	* @return added A boolean that determines whether or not the new category is already in the tree or not
	*/
	@Override
	public boolean createCategory(String newCat) {
		boolean added = true;
		Category newCategory = new Category(newCat);
		
		Category repeatedCategory = searchCategory(newCat);
		
		if(repeatedCategory == null) {
			if(rootCategory == null) {
				
				rootCategory = newCategory;
			}else {
				addCategory(rootCategory, newCategory);
			}
		}else {
			added = false;
		}
		
		return added;
	}
	
	/**
	* addCategory: Is a recursive method that adds a new category into the binary three of categories<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param currentCat is the current node of the tree where the program is located
	* @param newCat Is the new category thats going to be added
	*/
	private void addCategory(Category currentCat, Category newCat) {
		
		if(newCat.getName().compareTo(currentCat.getName())<0) {
			if(currentCat.getLeftCategory() == null) {
				currentCat.setLeftCategory(newCat);
				newCat.setParent(currentCat);
			}else {
				addCategory(currentCat.getLeftCategory(), newCat);
			}
		}else {
			if(currentCat.getRightCategory() == null) {
				currentCat.setRightCategory(newCat);
				newCat.setParent(currentCat);
			}else {
				addCategory(currentCat.getRightCategory(), newCat);
			}
		}
	}

	/**
	 * Search a category making a call to a recursive method, that goes through the BST of categories
	 * @param categoryName, String name of the category. It must be different from null and empty string
	 * @return The searched category if its on the BST any other case returns null 
	 */
	public Category searchCategory(String categoryName) {
		return searchCategory(categoryName, rootCategory);
	}

	/**
	 * Search a category according to its name in the BST of categories recursively
	 * @param categoryName, String name of the category. It must be different from null and empty string
	 * @param currentCategory, Current category in the recursive process
	 * @return The searched category if its on the BST returns null 
	 */
	private Category searchCategory(String categoryName, Category currentCategory) {
		if(currentCategory == null) {
			return null;
		}
		
		if(currentCategory.getName().equals(categoryName)) {
			return currentCategory;
		}else if(currentCategory.getName().compareTo(categoryName) > 0){
			return searchCategory(categoryName, currentCategory.getLeftCategory());
		}else {
			return searchCategory(categoryName, currentCategory.getRightCategory());
		}
	}
	
	/**
	 * Edits the information of an user in the BST
	 * @param user, User, the user that will be edited
	 * @param username, String, the new name of the user
	 * @param password, String, the new password of the user
	 * @param email, String, the new email of the user
	 * @param description, String, the new description of the user
	 * @param profilePicName, String, the new path of the user's profile picture
	 * @throws RepeatedUsernameException in case the new username already exists in the app
	 * @throws EmptyFieldsException in case any parameter is empty
	 * @throws IOException in case a serialization happened
	 */
	public void editUser(User user, String username, String password, String email, String description, String profilePicName) throws RepeatedUsernameException, EmptyFieldsException, IOException {
		boolean repeatedName = searchUserByName(username);
		if(username != null && password != null && email != null && description != null) {
			if(username.isEmpty() || password.isEmpty() || email.isEmpty() || description.isEmpty()) {
				throw new EmptyFieldsException(new String[] {username, password});
			}
			
			if(!repeatedName) {
				User copy = user.getClone();
				removeUser(user);
				copy.setUsername(username);
				copy.setPassword(password);
				copy.setEmail(email);
				copy.setDescription(description);
				if(profilePicName != null) {
					Image profilePic = new Image(IMAGE_PATH + profilePicName);
					copy.setProfilePic(profilePic);
				}
				addUser(copy);
				
			}else {
				throw new RepeatedUsernameException(username);
			}
		}
	}
	
	/**
	 * Removes an user from the BST of users
	 * @param user, the user that will be removed
	 */
	private void removeUser(User user) {
		
		if(user != null) {
			
			if(user.getLeftUser() == null && user.getRightUser() == null) {
				if(user == rootUser) {
					rootUser = null;
				}else if (user.compareTo(user.getParentUser()) <= 0) {
					user.getParentUser().setLeftUser(null);
				} else {
					user.getParentUser().setRightUser(null);
				}
				
				user.setParentUser(null);
			}else if(user.getLeftUser() == null || user.getRightUser() == null) {
				
				User child;
				if(user.getLeftUser() == null) {
					child = user.getRightUser();
				} else {
					child = user.getLeftUser();
				}
				
				child.setParentUser(user.getParentUser());
				
				if(user == rootUser) {
					rootUser = child;
				}else if (user.compareTo(user.getParentUser()) <= 0) {
					user.getParentUser().setLeftUser(child);
				} else {
					user.getParentUser().setRightUser(child);
				}
			}else {
				User successor = getMin(user.getRightUser());
				user.setFollowers(successor.getFollowers());
				user.setFollowing(successor.getFollowing());
				user.setUsername(successor.getUsername());
				user.setProfilePic(successor.getProfilePic());
				user.setDescription(successor.getDescription());
				user.setEmail(successor.getEmail());
				user.setPassword(successor.getPassword());
				user.setCreationDate(successor.getCreationDate());
				user.setFeed(successor.getFeed());
				user.setOwnPosts(successor.getOwnPosts());
				user.setReactedPosts(successor.getReactedPosts());
				
				removeUser(successor);
			}
		}
	}

	/**
	 * Get the user with the user with the lowest (A to Z) username in a subtree of the BST of users.<br>
	 * It iterates recursively through the BST.
	 * @param currentUser, the current parent of the subtree
	 * @return the user with the user with the lowest (A to Z) username
	 */
	private User getMin(User currentUser) {
		if(currentUser.getLeftUser() == null) {
			return currentUser;
		}else {
			return getMin(currentUser.getLeftUser());
		}
	}

	/**
	* reOrderModerators: It takes the list of moderators in the program and sort it by the amount of pending posts they have yet to confirm<br>
	* It sorts using with selection sort.
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	*/
	public void reOrderModerators() {
		for (int i = 0; i < mods.size(); i++) {
			
			Moderator min = mods.get(i);
			
			for (int j = i + 1; j < mods.size(); j++) {
				
				if (mods.get(j).compareTo(min) < 0) {
					Moderator temp = mods.get(i);
					mods.set(j, min);
					min = temp;
				}
			}
			mods.set(i, min);
		}
		sortPendingPosts();
	}
	
	 /**
	 * Sorts the pending posts of all moderators list according to their amount of reactions <br>
	 */
	private void sortPendingPosts() {
		for (Moderator mod : mods) {
			mod.sortPendingPosts();
		}
	}

	/**
	* loadPossibleCategories: Obtains the names of all categories within the binary tree of categories<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return cats The ArrayList of type String with the names of the categories in the tree
	*/
	public ArrayList<String> loadPossibleCategories() {

		ArrayList<String> cats = new ArrayList<>();
		if(rootCategory != null ) {
			cats = loadPossibleCategories(rootCategory, cats);
		}
		return cats;
	}
	
	/**
	* loadPossibleCategories: Is a recursive method which travels through the categories tree in order to obtain all its values and returns an ArrayList of type String with those names<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param current Is the current node of the tree where the program is located
	* @param cats Is the ArrayList of type String with the names of all categories in the tree
	* @return cats The ArrayList of type String with the names of the categories in the tree
	*/
	public ArrayList<String> loadPossibleCategories(Category current, ArrayList<String> cats) {
		if(current != null) {
			cats = loadPossibleCategories(current.getLeftCategory(), cats);
			cats.add(current.getName());
			cats = loadPossibleCategories(current.getRightCategory(), cats);
		}
		return cats;
	}
	
	/**
	 * Gets the list of trending posts by a specific category
	 * @param categoryName, String, the name of the category to search, it must be different from null and an empty string
	 * @return the list of trending posts by a category
	 */
	public ArrayList<Post> getTrendingByCategory(String categoryName) {
		
		ArrayList<Post> categoryTrend = new ArrayList<Post>();
		Category category = searchCategory(categoryName);
		for (Post post : posts) {
			if(post.getCategory().equals(category)) {
				if(post.getRating() >= MIN_RATING) {
					categoryTrend.add(post);
				}
			}
		}
		
		return categoryTrend;
	}
	
	/**
	 * Load the serialized app's information from the data.system folder
	 * @throws IOException Exception
	 * @throws FileNotFoundException in case the file with the serialized information does not exist
	 * @throws ClassNotFoundException in case the class to read does not exist
	 */
	@SuppressWarnings("unchecked")
	public void loadData() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		File fileMods = new File(SAVE_PATH_MODS);
		File fileCategories = new File(SAVE_PATH_MODS);
		File fileComments = new File(SAVE_PATH_MODS);
		File fileUsers = new File(SAVE_PATH_MODS);
		File filePosts = new File(SAVE_PATH_MODS);
		File fileTrending = new File(SAVE_PATH_MODS);
		
		if (fileMods.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileMods));
			mods = (ArrayList<Moderator>) ois.readObject();
			ois.close();
		}
		
		if (fileCategories.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileCategories));
			rootCategory = (Category) ois.readObject();
			ois.close();
		}
		
		if (fileComments.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileComments));
			comments = (ArrayList<Comment>) ois.readObject();
			ois.close();
		}
		
		if (fileUsers.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileUsers));
			rootUser = (User) ois.readObject();
			ois.close();
		}
		
		if (filePosts.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePosts));
			posts = (ArrayList<Post>) ois.readObject();
			ois.close();
		}
		
		if (fileTrending.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileTrending));
			trending = (ArrayList<Post>) ois.readObject();
			ois.close();
		}
	}
	
	public void saveData() throws IOException {
		saveCommentsData();
		saveModsData();
		//saveUsersData();
		saveTrendingData();
		saveCategoriesData();
		savePostsData();
	}

	/**
	* saveCommentsData: Is a recursive method which travels through the categories tree in order to obtain all its values and returns an ArrayList of type String with those names<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @throws IOException Exception
	*/
	public void saveCommentsData() throws IOException {
		ObjectOutputStream oosI =  new ObjectOutputStream(new FileOutputStream(SAVE_PATH_COMMENTS));
		oosI.writeObject(comments);
		oosI.close();
	}
	
	/**
	* saveModsData: Is a recursive method which travels through the categories tree in order to obtain all its values and returns an ArrayList of type String with those names<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @throws IOException Exception
	*/
	public void saveModsData() throws IOException {
		ObjectOutputStream oosI =  new ObjectOutputStream(new FileOutputStream(SAVE_PATH_MODS));
		oosI.writeObject(mods);
		oosI.close();
	}
	
	/**
	* saveUsersData: Is a recursive method which travels through the categories tree in order to obtain all its values and returns an ArrayList of type String with those names<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @throws IOException Exception
	*/
	public void saveUsersData() throws IOException {
		ObjectOutputStream oosI =  new ObjectOutputStream(new FileOutputStream(SAVE_PATH_USERS));
		oosI.writeObject(rootUser);
		oosI.close();
	}
	
	/**
	* saveTrendingData: Is a recursive method which travels through the categories tree in order to obtain all its values and returns an ArrayList of type String with those names<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @throws IOException Exception
	*/
	public void saveTrendingData() throws IOException {
		ObjectOutputStream oosI =  new ObjectOutputStream(new FileOutputStream(SAVE_PATH_TRENDING));
		oosI.writeObject(trending);
		oosI.close();
	}
	
	/**
	* saveCategoriesData: Is a recursive method which travels through the categories tree in order to obtain all its values and returns an ArrayList of type String with those names<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @throws IOException Exception
	*/
	public void saveCategoriesData() throws IOException {
		ObjectOutputStream oosI =  new ObjectOutputStream(new FileOutputStream(SAVE_PATH_CATEGORIES));
		oosI.writeObject(rootCategory);
		oosI.close();
	}
	
	/**
	* savePostsData: Is a recursive method which travels through the categories tree in order to obtain all its values and returns an ArrayList of type String with those names<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @throws IOException Exception
	*/
	public void savePostsData() throws IOException {
		ObjectOutputStream oosI =  new ObjectOutputStream(new FileOutputStream(SAVE_PATH_POSTS));
		oosI.writeObject(posts);
		oosI.close();
	}

	/**
	* followUser: Adds the current user who is using the app to the list of followers of the searched user. Also adds the searched user to the list of following users from the current user<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param currentUser Is an User object, reference to the user who is currently using the app
	* @param searchedUsername Is the string of the searched user,used for searching and obtaining the searched user object
	* @throws InvalidUserException Exception
	*/
	public void followUser(User currentUser, String searchedUsername) throws InvalidUserException {
		User followedUser = searchUser(searchedUsername);
    	followedUser.getFollowers().add(currentUser);
    	currentUser.getFollowing().add(followedUser);
	}

	/**
	* unfollowUser: Removes the current user who is using the app to the list of followers of the searched user. Also removes the searched user to the list of following users from the current user<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param currentUser Is an User object, reference to the user who is currently using the app
	* @param searchedUsername Is the string of the searched user,used for searching and obtaining the searched user object
	* @throws InvalidUserException Exception
	*/
	public void unfollowUser(User currentUser, String searchedUsername) throws InvalidUserException {
		User followedUser = searchUser(searchedUsername);
    	followedUser.getFollowers().remove(currentUser);
    	currentUser.getFollowing().remove(followedUser);
	}
	
	/**
	 * Exports the post report in a csv file with the title of the post in the folder data
	 * @param post, Post, the post to export the report
	 * @throws FileNotFoundException in case the file where the report will be created does not exist
	 */
	public void exportPostReport(Post post) throws FileNotFoundException {
		String report = post.reportToString();
		String path = EXPORT_REPORT_POST_PATH + post.getTitle() + REPORTS_EXTENSION;
		PrintWriter pw = new PrintWriter(path);
		pw.println(report);
		pw.close();
	}
	
	/**
	 * Imports some extra data for the app
	 * @throws IOException Exception
	 * @throws RepeatedUsernameException Exception
	 * @throws EmptyFieldsException Exception
	 * @throws InvalidUserException Exception
	 */
	public void importData() throws IOException, RepeatedUsernameException, EmptyFieldsException, InvalidUserException {
		importUsers();
		importCategories();
		importPosts();
	}
	
	/**
	 * Import the file categories.csv and loads all the categories in it
	 * @throws IOException
	 */
	private void importCategories() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(IMPORT_CSV_CATEGORIES));
		br.readLine(); //Read first line
		
		String line = br.readLine();
		while (line != null) {
			String categoryName = line;
			createCategory(categoryName);
			line = br.readLine();
		}
		br.close();
	}

	/**
	 * Import the file posts.csv and loads all the posts in it <br>
	 * <b> pre: </b> The categories of categories.csv and the users of users.csv must be imported <br>
	 * @throws IOException
	 * @throws EmptyFieldsException
	 * @throws InvalidUserException
	 */
	private void importPosts() throws IOException, EmptyFieldsException, InvalidUserException {
		BufferedReader br = new BufferedReader(new FileReader(IMPORT_CSV_POSTS));
		br.readLine(); //Read first line
		
		String line = br.readLine();
		while (line != null) {
			String[] values = line.split(";");
			String author = values[0];
			String title = values[1];
			String content = values[2];
			String categoryName = values[3];
			String link = values[4];
			String creationString = values[5];
			LocalDateTime creationDate = createDate(creationString);
			createPost(title, content, categoryName, searchUser(author), link);
			searchPost(title).setDate(creationDate);
			line = br.readLine();
		}
		br.close();
	}

	/**
	 * Import the file users.csv and loads all the users in it
	 * @throws IOException
	 * @throws RepeatedUsernameException
	 * @throws EmptyFieldsException
	 * @throws InvalidUserException
	 */
	private void importUsers() throws IOException, RepeatedUsernameException, EmptyFieldsException, InvalidUserException {
		BufferedReader br = new BufferedReader(new FileReader(IMPORT_CSV_USERS));
		br.readLine(); //Read first line
		
		String line = br.readLine();
		while (line != null) {
			System.out.println(line);
			String[] values = line.split(";");
			String username = values[0];
			String email = values[1];
			String password = values[2];
			String creationString = values[3];
			LocalDateTime creationDate = createDate(creationString);
			String bio = values[4];
			boolean isMod = Boolean.valueOf(values[5]);
			String profilePicPath = IMAGE_PATH + values[6] + PROFILE_PIC_EXTENSION;
			Image profilePic = new Image(profilePicPath);
			addUser(username, email, password);
			if(!isMod) {
				upgradeUser(username);
			}
			searchUser(username).setCreationDate(creationDate);
			searchUser(username).setDescription(bio);
			searchUser(username).setProfilePic(profilePic);
			line = br.readLine();
		}
		br.close();
	}

	/**
	* createDate: It creates a new LocalDateTime variable that contains the date of creation of an object<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param dateString String, Date in String format
	* @param date LocalDateTime, date of creation of the object
	*/
	private LocalDateTime createDate(String dateString) {
		LocalDateTime date;
		if(dateString.equals("now")) {
			date = LocalDateTime.now();
		}else {
			String[] dateValues = dateString.split("/");
			int year = Integer.valueOf(dateValues[2]);
			int month = Integer.valueOf(dateValues[1]);
			int day = Integer.valueOf(dateValues[0]);
			
			date = LocalDateTime.of(year, month, day, 0, 0);
		}		
		return date;
	}

	/**
	* getMods: Gets mods<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return mods ArrayList of moderators
	*/
	public ArrayList<Moderator> getMods() {
		return mods;
	}

	/**
	* setMods: Changes the moderators list<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param mods ArrayList of moderators
	*/
	public void setMods(ArrayList<Moderator> mods) {
		this.mods = mods;
	}

	/**
	* getRootUser: Obtains the root user in the binary tree of users<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return rootUser User, root user of the binary tree
	*/
	public User getRootUser() {
		return rootUser;
	}

	/**
	* setRootUser: Sets the root user in the binary tree of users<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param rootUser User, root user of the binary tree
	*/
	public void setRootUser(User rootUser) {
		this.rootUser = rootUser;
	}

	/**
	* getRootCategory: Obtains the root category in the binary tree of categories<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return rootCategory Category, root category of the binary tree
	*/
	public Category getRootCategory() {
		return rootCategory;
	}

	/**
	* g¿setRootCategory: Sets the root category in the binary tree of categories<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param rootCategory Category, root category of the binary tree
	*/

	public void setRootCategory(Category rootCategory) {
		this.rootCategory = rootCategory;
	}

	/**
	* getPosts: Obtains the list of posts<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return posts ArrayList of posts, posts from the app
	*/
	public ArrayList<Post> getPosts() {
		return posts;
	}

	/**
	* setPosts: Sets the list of posts<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param posts ArrayList of posts, posts from the app
	*/
	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}

	/**
	* getTrending: Obtains the list of trending posts<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return trending ArrayList of posts, trending posts from the app
	*/
	public ArrayList<Post> getTrending() {
		return trending;
	}

	/**
	* setTrending: Sets the list of trending posts<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param trending ArrayList of posts, trending posts from the app
	*/
	public void setTrending(ArrayList<Post> trending) {
		this.trending = trending;
	}
	
	/**
	* upgradeUser: Creates a new Moderator that replaces an old user. The old user is removed from the list of users and the moderator is added in order to take its place<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param username String, reference to the user that will be upgraded
	* @throws InvalidUserException Exception
	* @throws RepeatedUsernameException Exception
	* @throws IOException Exception
	*/
	public void upgradeUser(String username) throws InvalidUserException, RepeatedUsernameException, IOException {
		User user = searchUser(username);
		LocalDateTime nowTime = LocalDateTime.now();
		Duration timeInVistara = Duration.between(user.getCreationDate(), nowTime);
		if(user.getVerifiedPosts() >=20 && timeInVistara.toDays() >= 30) {
		
			ArrayList<User> userFollowers = user.getFollowers();
			ArrayList<User> userFollowing = user.getFollowing();
			String userUsername = user.getUsername();
			Image userProfilePic = user.getProfilePic();
			String userDescription = user.getDescription();
			String userEmail = user.getEmail();
			String userPassword = user.getPassword();
			LocalDateTime userCreationDate = user.getCreationDate();
			ArrayList<Post> userFeed = user.getFeed();
			ArrayList<Post> userOwnPosts = user.getOwnPosts();
			ArrayList<Post> userReactedPosts = user.getReactedPosts();
			int userVerifiedPosts = user.getVerifiedPosts();
			int userFakePosts = user.getFakePosts();
			removeUser(user);
			//create and assign values to newMod
			Moderator newMod = new Moderator(userUsername, userEmail, userPassword);
			newMod.setFollowers(userFollowers);
			newMod.setFollowing(userFollowing);
			newMod.setProfilePic(userProfilePic);
			newMod.setDescription(userDescription);
			newMod.setCreationDate(userCreationDate);
			newMod.setFeed(userFeed);
			newMod.setOwnPosts(userOwnPosts);
			newMod.setReactedPosts(userReactedPosts);
			newMod.setVerifiedPosts(userVerifiedPosts);
			newMod.setFakePosts(userFakePosts);
			//add newmod
			mods.add(newMod);
			addUser(newMod);
		}
	}

	/**
	* reactToPost: Allows the current user of the app to interact with a post, incrementing or decreasing the amount of reactions of th epost<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param post Post, post with which the user is interacting
	* @param currentUser String, reference to the user that will be upgraded
	*/
	public void reactToPost(Post post, User currentUser) {
		boolean reacted = currentUser.searchReactedPost(post);		
		if(reacted) {
			post.getReactedUsers().remove(currentUser);
			post.setReactions(post.getReactions()-1);
			currentUser.getReactedPosts().remove(post);
		}else {
			post.getReactedUsers().add(currentUser);
			post.setReactions(post.getReactions()+1);
			currentUser.getReactedPosts().add(post);
		}
	}
	
	/**
	 * Sort the post's list using insertion sort based on their title
	 */
	public void sortPosts() {
		for (int i = 1; i < posts.size(); i++) {
			for (int j = i; j > 0 && posts.get(j-1).compareTo(posts.get(j)) > 0; j--) {
				Post temp = posts.get(j-1);
				posts.set(j-1, posts.get(j));
				posts.set(j, temp);
			}
		}
	}

	/**
	 * Search a posts according to the title. This method implements binary search cause the list is sorted
	 * @param title, String, the title of the posts to search. It must be different from null and an empty string
	 * @return the searched post or null in case the post does not exist
	 * @throws EmptyFieldsException in case the title is an empty string
	 */
	public Post searchPost(String title) throws EmptyFieldsException {
		
		if(title.isEmpty()) {
			throw new EmptyFieldsException(new String[] {title});
		}
		
		Post post = null;
		if(posts.size() > 0) {
			
			int low = 0;
			int top = posts.size();
			boolean found = false;
			while(low <= top && !found) {
				int mid = (low + top)/2;
				if (posts.get(mid).getTitle().equals(title)) {
					post = posts.get(mid);
					found = true;
				
				} else if (posts.get(mid).getTitle().compareTo(title) < 0) {
					low = mid + 1;
				} else {
					top = mid - 1;
				}
			}
		}

		return post;
	}

	/**
	* deletePost: Deletes a post form all places in the app<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param creatorString String, post creator
	* @param moderator String, actual moderator deleting the post
	* @param postToRemove Post, post to delete
	* @throws InvalidUserException Exception
	* @throws EmptyFieldsException Exception
	*/
	@Override
	public void deletePost(String creatorString, String moderator, Post postToRemove) throws InvalidUserException, EmptyFieldsException {
		
		if(creatorString.isEmpty() || moderator.isEmpty()) {
			throw new EmptyFieldsException(new String[] {creatorString, moderator});
		}
		
		User creator = searchUser(creatorString);
		User mod = searchUser(moderator);

		mod.deletePost(postToRemove.getDate());
		if(mod instanceof Moderator) {
			creator.getOwnPosts().remove(postToRemove);
			((Moderator) mod).removePost(postToRemove);
			posts.remove(postToRemove);
		}
	}

	/**
	* verifyPost: Verifies a post<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param creatorUser String, post creator
	* @param mod String, actual moderator deleting the post
	* @param postToVerify Post, post to verify
	* @param state String, state of verification
	* @throws InvalidUserException Exception
	*/
	@Override
	public void verifyPost(String creatorUser, String mod, Post postToVerify, String state) throws InvalidUserException {
		User creator = searchUser(creatorUser);		
		User moderator = searchUser(mod);
		
		postToVerify.editState(state);
		
		if(state.equals(VERIFIED)) {
			creator.setVerifiedPosts(creator.getVerifiedPosts()+1);
		}else {
			creator.setFakePosts(creator.getFakePosts()+1);
		}
		
		if(moderator instanceof Moderator) {
			((Moderator) moderator).getPendingPosts().remove(postToVerify);
		}
	}

	/**
	* confirmPorfileEdition: Changes current user info<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param oldName String, old user username
	* @param newName String, new user username
	* @param email String, new email
	* @param bio String, users new description
	* @throws InvalidUserException Exception
	* @throws RepeatedUsernameException Exception
	* @throws IOException Exception
	*/
	public void confirmPorfileEdition(String oldName,String newName, String email, String bio) throws InvalidUserException, RepeatedUsernameException, IOException {
		User user = searchUser(oldName);
		if(oldName.equals(newName)) {
			user.setEmail(email);
			user.setDescription(bio);
		}else {
			boolean repeatedName = searchUserByName(newName);
			if(!repeatedName) {
				User newUser = user.getClone();
				removeUser(user);
				newUser.setUsername(newName);
				newUser.setEmail(email);
				newUser.setDescription(bio);			
				addUser(newUser);
			}
		}
	}

	/**
	* setUserProfilePic: Changes the profile picture of the user<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param username String, username of the user
	* @param selectedFile File, Image path file
	* @throws InvalidUserException Exception
	*/
	public void setUserProfilePic(String username, File selectedFile) throws InvalidUserException {
		User user = searchUser(username);
		Image profilePic = new Image("file:"+selectedFile.getAbsolutePath());
		user.setProfilePic(profilePic);
	}

	/**
	* createImagePost: Creates a post with an image<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param currentUser User, current user
	* @param title String, title of the post
	* @param content String, post content
	* @param category String, post category
	* @param link String, news link
	* @param path String, image path
	*/
	public void createImagePost(User currentUser, String title, String content, String category, String link, String path) {
		Category cat = new Category(category);
		LocalDateTime now = LocalDateTime.now();
		Image img = new Image("file:"+path);
		ImagePost post = new ImagePost(currentUser.getUsername(), title, content, cat, now, link, img);
		
		posts.add(post);
		currentUser.getOwnPosts().add(post);
		ThreadAddPostFollowers addThread = new ThreadAddPostFollowers(currentUser, post);
		addPostToModeratorList(post);
		addThread.start();
		sortPosts();
	}
	
	/**
	* addCategoryImage: Add an image to a category<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param cat String, new category
	* @param path String, image path
	*/
	public void addCategoryImage(String path, String cat) {
		Category category = searchCategory(cat);
		Image image = new Image(path);
		category.setBackground(image);
	}
}