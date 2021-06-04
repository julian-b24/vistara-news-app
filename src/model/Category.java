package model;

import javafx.scene.image.Image;

public class Category {

	private Category leftCategory;
	private Category rightCategory;
	private String name;
	private Image background;

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Image getBackground() {
		return this.background;
	}

	/**
	 * 
	 * @param background
	 */
	public void setBackground(Image background) {
		this.background = background;
	}

	public Category getLeftCategory() {
		return leftCategory;
	}

	public void setLeftCategory(Category leftCategory) {
		this.leftCategory = leftCategory;
	}

	public Category getRightCategory() {
		return rightCategory;
	}

	public void setRightCategory(Category rightCategory) {
		this.rightCategory = rightCategory;
	}

}