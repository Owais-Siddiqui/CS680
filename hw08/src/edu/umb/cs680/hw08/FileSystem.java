package edu.umb.cs680.hw08;

import java.util.LinkedList;

public class FileSystem {

	private static FileSystem instanceVar = null;
	private LinkedList<Directory> rootDirs;
	public static FileSystem getFileSystem() {
		if(instanceVar==null)
			instanceVar = new FileSystem ();
		return instanceVar;
	}
	private FileSystem() {};
	
	public LinkedList<Directory> getRootDirs() {
		return this.rootDirs;
	}
	
	
	public void addRootDir(Directory rootDir) {
		rootDirs = new LinkedList<Directory>();
		rootDirs.add(rootDir);
	}
	
	public static void main(String[] args) {
		System.out.println("Class Filesystem Executed successfully!");
	}
}
