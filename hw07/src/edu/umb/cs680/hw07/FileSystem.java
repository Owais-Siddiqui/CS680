package edu.umb.cs680.hw07;

import java.util.LinkedList;

public class FileSystem {
	private LinkedList<Directory> directoryroot;
	private static FileSystem instanceVar = null;
	//Setter Root Directory
	void addRootDir(Directory Rootdirectory) {
		//Creating root directory
		directoryroot = new LinkedList<Directory>();
		directoryroot.add(Rootdirectory);
	}
	private FileSystem() {};
	//Getter Method for File System
	public static FileSystem getFileSystem() {
		if(instanceVar==null) 
			instanceVar = new FileSystem ();
		return instanceVar; 
	}
	//Getter Method for Root Directory
	public LinkedList<Directory> getRootDirs() {
		return this.directoryroot;
	}
	public static void main(String[] args) {
		System.out.println("Class FileSystem Executed!");
	}
}
