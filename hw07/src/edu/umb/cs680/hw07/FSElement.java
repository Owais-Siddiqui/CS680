package edu.umb.cs680.hw07;

import java.time.LocalDateTime;

public abstract class FSElement {
	private String name;
	private int size;
	private LocalDateTime creationTime;
	private Directory parent; 
	
	public FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
		this.parent = parent;
		this.name = name;
		this.size = size;
		this.creationTime = creationTime;
	}
	//Getter Method for Parent
	public Directory getParent() {
		return parent;
	}
	//Getter Method for Name
	public String getName() {
		return name;
	}
	//Getter Method for Size 
	public int getSize() {
		return size;
	}
	//Getter Method for Creatin time
	public LocalDateTime getCreationTime() {
		return creationTime;
	}
	//Abstract method to check if Disrectory exists.
	public abstract boolean isDirectory();
	
	public static void main(String[] args) {
		System.out.println("Class FSElement Executed!");
	}
}
