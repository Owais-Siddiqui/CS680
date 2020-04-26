package edu.umb.cs680.hw10;

public class ApfsCountingVisitor implements ApfsFSVisitor{

	private int dirNum = 0;
	private int fileNum = 0;
	private int linkNum = 0;

	@Override
	public void visit(ApfsDirectory dir) {
		dirNum++;
	}
	public int getDirNum() {
		return dirNum;
	}

	public int getFileNum() {
		return fileNum;
	}

	@Override
	public void visit(ApfsLink link) {
		linkNum++;
	}

	public int getLinkNum() {
		return linkNum;
	}

	@Override
	public void visit(ApfsFile file) {
		fileNum++;
	}

	public static void main(String[] args) {
		System.out.println("Class ApfscountingVisitor Executed Successfully!");
	}
}
