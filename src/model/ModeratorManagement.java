package model;

public interface ModeratorManagement {

	/**
	 * 
	 * @param name
	 */
	public void createCategory(String name);

	public void deletePost();

	public void verifyPost();

}