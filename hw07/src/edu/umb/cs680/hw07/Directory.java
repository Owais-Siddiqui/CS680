package edu.umb.cs680.hw07;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement{
	
	private LinkedList<FSElement> child;
	
	public LinkedList<FSElement> getChildren() {
		return child;
	}
	
	
	public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
		super(parent, name, size, creationTime);
		if(parent != null)
			parent.appendChild(this);
	}

	@Override
	public boolean isDirectory() {
		return true;
	}
	
	public File findFileByName(String nameoffile) {
		File namedfile = null;
		for(File f : getFiles()) {
			if(nameoffile.equals(f.getName()))
				namedfile = f;
		}
		if(namedfile == null) {
			for(Directory dir : getSubDirectories()) {
				namedfile = dir.findFileByName(nameoffile);
				if(namedfile != null)
					break;
			}
		}
		return namedfile;
	}
	
	public Directory findDirByName(String dirName) {
		Directory namedirectory = null;
		if(dirName.equals(getName()))
			namedirectory = this;
		else {
			for(Directory dir : getSubDirectories()) {
				if(namedirectory == null) {
					namedirectory = dir.findDirByName(dirName);
					if(dirName.equals(dir.getName())) {
						namedirectory = dir;
						break;
					}
				}
			}
		}
		return namedirectory;
	}

	public void appendChild(FSElement child) {
		if(this.child == null) {
			this.child = new LinkedList<FSElement>();
		}
		this.child.add(child);
	}
		
	public int getCountChildren() {
		return getChildren().size();
	}
		
	public LinkedList<Directory> getSubDirectories() {
		LinkedList<Directory> listdir = new LinkedList<Directory>();
		for(FSElement s : getChildren()) {
			if(s instanceof Directory)
				listdir.add((Directory) s);
		}
		return listdir;	
	}

	public LinkedList<File> getFiles() {
		LinkedList<File> listfile = new LinkedList<File>();
		for(FSElement s : getChildren()) {
			if(s instanceof File)
				listfile.add((File) s);
		}
		return listfile;
	}

	public int getTotalSize() {
		int size_total = 0;
		for(FSElement s : getChildren()) {
			if(s instanceof Directory)
				size_total += ((Directory) s).getTotalSize();
			else
				size_total += s.getSize();
		}
		return size_total;
	}
	
	public static void main(String[] args) {
		System.out.println("Class Directory Executed!");
	}
}
