package model;

import exceptions.EmptyFieldsException;
import exceptions.InvalidUserException;

public interface ModeratorManagement {

	public boolean createCategory(String name);

	public void deletePost(String creator, String mod, Post postToRemove) throws InvalidUserException, EmptyFieldsException;

	public void verifyPost(String creator, String mod, Post postToVerify, String state) throws InvalidUserException ;

}