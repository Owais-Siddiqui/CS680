package edu.umb.cs680.hw07;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class FileSystemTest {
	private String[] stringarrayforthedirectory(Directory dir){
		String[] stringelement = {null, dir.getName(), Integer.toString(dir.getSize()), dir.getCreationTime().toString()};
		return stringelement; 
	}

	@Test
	void test_Createdinstance() {
		FileSystem Instanceone = FileSystem.getFileSystem();
		FileSystem Instancetwo = FileSystem.getFileSystem();
		assertSame(Instanceone, Instancetwo);
	}

	@Test
	void test_Rootdir() {
		LocalDateTime localDateTime = LocalDateTime.now();
		Directory root = new Directory(null, "root", 0, localDateTime);
		String[] expected = {null, "root", "0", localDateTime.toString()}; 
		FileSystem.getFileSystem().addRootDir(root);
		Directory actual = FileSystem.getFileSystem().getRootDirs().get(0);
		assertArrayEquals(expected, stringarrayforthedirectory(actual));
	}

}
