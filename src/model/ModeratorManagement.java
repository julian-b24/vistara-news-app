package model;

import java.io.IOException;

import exceptions.EmptyFieldsException;
import exceptions.InvalidUserException;

public interface ModeratorManagement {

	public boolean createCategory(String name) throws IOException;

	public void deletePost(String creator, String mod, Post postToRemove) throws InvalidUserException, EmptyFieldsException, IOException;

	public void verifyPost(String creator, String mod, Post postToVerify, String state) throws InvalidUserException, IOException ;

}