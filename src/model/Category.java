package model;

import java.io.Serializable;

public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Category leftCategory;
	private Category rightCategory;
	private Category parent;
	private String name;

	
	/**
	* Category: Category class constructor <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param cat String
	*/
	public Category(String cat) {
		name = cat;
	}
	
	/**
	* getName: Gets category name <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return name String
	*/
	public String getName() {
		return this.name;
	}

	/**
	* setName: Sets the name <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param name String
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* getLeftCategory: Gets the left category <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return leftCategory Category
	*/
	public Category getLeftCategory() {
		return leftCategory;
	}

	/**
	* setLeftCategory: Sets the left category <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param leftCategory Category
	*/
	public void setLeftCategory(Category leftCategory) {
		this.leftCategory = leftCategory;
	}

	/**
	* getRightCategory: Gets the right category <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return rightCategory Category
	*/
	public Category getRightCategory() {
		return rightCategory;
	}

	/**
	* setRightCategory: Sets the right category <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param rightCategory Category
	*/
	public void setRightCategory(Category rightCategory) {
		this.rightCategory = rightCategory;
	}

	/**
	* getParent: Gets the right category <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return parent Category
	*/
	public Category getParent() {
		return parent;
	}

	/**
	* setParent: Sets the right category <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param parent Category
	*/
	public void setParent(Category parent) {
		this.parent = parent;
	}

}