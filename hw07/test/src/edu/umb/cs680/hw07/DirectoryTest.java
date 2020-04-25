package edu.umb.cs680.hw07;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DirectoryTest {
	
	static LocalDateTime localTime = LocalDateTime.now();
	
	@SuppressWarnings("unused")
	@BeforeAll
	public static void setupupoffilestructure() {
		Directory root = new Directory(null, "root", 0, localTime);
		Directory applications = new Directory(root, "applications", 0, localTime);
		Directory home = new Directory(root, "home", 0, localTime);
		Directory code = new Directory(home, "code", 0, localTime);
		File One = new File(applications, "One", 800, localTime);
		File Two = new File(applications, "Two", 550, localTime);
		File Three = new File(home, "Three", 700, localTime);
		File Four = new File(home, "Four", 900, localTime);
		File Five = new File(code, "Five", 170, localTime);
		File Six = new File(code, "Six", 1300, localTime);
		//creating files and adding root dir
		FileSystem.getFileSystem().addRootDir(root);
		
	}
	
	private String[] dirToStringArray(Directory d) {
		Optional<Directory> optionalDirectory = Optional.ofNullable(d.getParent());
		String[] directorystring = { Boolean.toString(d.isDirectory()), d.getName(), 
				Integer.toString(d.getSize()), d.getCreationTime().toString(), 
				optionalDirectory.isPresent()?d.getParent().getName():null, 
						Integer.toString(d.getTotalSize()),
						Integer.toString(d.getCountChildren())};
		return directorystring;
	}
	
	@Test
	void test_RootDirectoryandFiles() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertSame("home", fileSystem.getRootDirs().get(0).findDirByName("home").getName());
		assertSame("root", fileSystem.getRootDirs().get(0).findDirByName("root").getName());
		assertSame("applications", fileSystem.getRootDirs().get(0).findDirByName("applications").getName());
		assertSame("code", fileSystem.getRootDirs().get(0).findDirByName("code").getName());
		assertSame("One", fileSystem.getRootDirs().get(0).findFileByName("One").getName());
		assertSame("Six", fileSystem.getRootDirs().get(0).findFileByName("Six").getName());
	} 
	
	@Test
	void test_DirectoryandFiles() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("root").isDirectory());
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("home").isDirectory());
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("home").isDirectory());
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("code").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("One").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("Two").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("Three").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("Four").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("Five").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("Six").isDirectory());
	}
	
	@Test
	public void Test_DirectoryEqualsCode() { 
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "true", "code", "0", localTime.toString(), "home", "1470", "2" };
		Directory actual = fileSystem.getRootDirs().get(0).findDirByName("code");
		assertArrayEquals(expected,dirToStringArray(actual));
	}
	
	@Test
	public void Test_DirectoryEqualsRoot() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "true", "root", "0", localTime.toString(), null, "4420", "2" };
		Directory actual = fileSystem.getRootDirs().get(0).findDirByName("root");
		assertArrayEquals(expected,dirToStringArray(actual));
	}
	
	@Test
	public void Test_DirectoryEqualsHome() { 
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "true", "home", "0", localTime.toString(), "root", "3070", "3" };
		Directory actual = fileSystem.getRootDirs().get(0).findDirByName("home");
		assertArrayEquals(expected,dirToStringArray(actual));
	}
	@Test
	void test_FubfilesFoldersNumber() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertEquals(2, fileSystem.getRootDirs().get(0).findDirByName("root").getCountChildren());
		assertEquals(2, fileSystem.getRootDirs().get(0).findDirByName("applications").getCountChildren());
		assertEquals(3, fileSystem.getRootDirs().get(0).findDirByName("home").getCountChildren());
		assertEquals(2, fileSystem.getRootDirs().get(0).findDirByName("code").getCountChildren());
	}
	@Test
	void test_DirectoryFileNumber() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertSame("One", fileSystem.getRootDirs().get(0).findDirByName("applications").getFiles().get(0).getName());
		assertSame("Two", fileSystem.getRootDirs().get(0).findDirByName("applications").getFiles().get(1).getName());
		assertSame("Three", fileSystem.getRootDirs().get(0).findDirByName("home").getFiles().get(0).getName());
		assertSame("Four", fileSystem.getRootDirs().get(0).findDirByName("home").getFiles().get(1).getName());
		assertSame("Five", fileSystem.getRootDirs().get(0).findDirByName("code").getFiles().get(0).getName());
		assertSame("Six", fileSystem.getRootDirs().get(0).findDirByName("code").getFiles().get(1).getName());
	}
	
	@Test
	void test_Subdirectories() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertSame("applications", fileSystem.getRootDirs().get(0).findDirByName("root").getSubDirectories().get(0).getName());
		assertSame("home", fileSystem.getRootDirs().get(0).findDirByName("root").getSubDirectories().get(1).getName());
		assertSame("code", fileSystem.getRootDirs().get(0).findDirByName("home").getSubDirectories().get(0).getName());
	}
	@Test
	void test_codesize() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertEquals(1470, fileSystem.getRootDirs().get(0).findDirByName("code").getTotalSize());
	}


	@Test
	void test_FolderSizes() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertEquals(4420, fileSystem.getRootDirs().get(0).findDirByName("root").getTotalSize());
		assertEquals(1470, fileSystem.getRootDirs().get(0).findDirByName("code").getTotalSize());
		assertEquals(1350, fileSystem.getRootDirs().get(0).findDirByName("applications").getTotalSize());
		assertEquals(3070, fileSystem.getRootDirs().get(0).findDirByName("home").getTotalSize());
		
	}
	@Test
	void test_RootSize() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertEquals(4420, fileSystem.getRootDirs().get(0).findDirByName("root").getTotalSize());	
	}
	@Test
	public void Test_DirectoryEqualsApplications() { 
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "true", "applications", "0", localTime.toString(), "root", "1350", "2" };
		Directory actual = fileSystem.getRootDirs().get(0).findDirByName("applications");
		assertArrayEquals(expected,dirToStringArray(actual));
	}
	
	
}

