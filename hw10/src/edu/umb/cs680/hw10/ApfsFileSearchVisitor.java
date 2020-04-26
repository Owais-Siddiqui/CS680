package edu.umb.cs680.hw10;

public class ApfsFileSearchVisitor implements ApfsFSVisitor{

	private String fileName;
	private ApfsFile file;
	
	
	@Override
	public void visit(ApfsDirectory dir) {
		return;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void visit(ApfsLink link) {
		return;
	}
	public ApfsFile getFile() {
		return file;
	}

	@Override
	public void visit(ApfsFile file) {
		if(fileName.equals(file.getName()))
			this.file = file;
	}

	public static void main(String[] args) {
		System.out.println("Class Apfsfilesearchvisitor Executed Successfully!");
	}
}
