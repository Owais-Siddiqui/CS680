package edu.umb.cs680.hw08;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw08.Directory;
import edu.umb.cs680.hw08.File;
import edu.umb.cs680.hw08.FileSystem;

class DirectoryTest {
	
	static LocalDateTime localTime = LocalDateTime.now();
	
	@SuppressWarnings("unused")
	@BeforeAll
	public static void setupupoffilestructure() {
		Directory root = new Directory(null, "root", 0, localTime);
		Directory applications = new Directory(root, "applications", 0, localTime);
		Directory home = new Directory(root, "home", 0, localTime);
		Directory code = new Directory(home, "code", 0, localTime);
		File One = new File(applications, "One", 1300, localTime);
		File Two = new File(applications, "Two", 350, localTime);
		File Three = new File(home, "Three", 500, localTime);
		File Four = new File(home, "Four", 700, localTime);
		Link Five = new Link(home, "Five", 0, localTime, applications);
		File Six = new File(code, "Six", 70, localTime);
		File Seven = new File(code, "Seven", 130, localTime);	
		Link Eight = new Link(code, "Eight", 0, localTime, Two);
		//creation of files and adding root dir
		FileSystem.getFileSystem().addRootDir(root);
		
	}
	
	private String[] fsElementToStringArray(Directory d) {
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
	void test_Directoryandfiles() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("root").isDirectory());
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("home").isDirectory());
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("home").isDirectory());
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("code").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("One").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("Two").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("Three").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("Four").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("Six").isDirectory());
	}
	
	@Test
	public void test_DirectoryEqualsCode() { 
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "true", "code", "0", localTime.toString(), "home", "200", "2" };
		Directory actual = fileSystem.getRootDirs().get(0).findDirByName("code");
		assertArrayEquals(expected,fsElementToStringArray(actual));
	}
	
	@Test
	void test_directoryfilenumbers() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertSame("One", fileSystem.getRootDirs().get(0).findDirByName("applications").getFiles().get(0).getName());
		assertSame("Two", fileSystem.getRootDirs().get(0).findDirByName("applications").getFiles().get(1).getName());
		assertSame("Three", fileSystem.getRootDirs().get(0).findDirByName("home").getFiles().get(0).getName());
		assertSame("Four", fileSystem.getRootDirs().get(0).findDirByName("home").getFiles().get(1).getName());
		// assertSame("Five", fileSystem.getRootDirs().get(0).findDirByName("code").getFiles().get(0).getName());
		// assertSame("Five", fileSystem.getRootDirs().get(0).findDirByName("code").getFiles().get(1).getName());
	}
	@Test
	void test_CodeSize() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertEquals(200, fileSystem.getRootDirs().get(0).findDirByName("code").getTotalSize());

		
	}
	@Test
	void test_Subdirectories() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertSame("applications", fileSystem.getRootDirs().get(0).findDirByName("root").getSubDirectories().get(0).getName());
		assertSame("home", fileSystem.getRootDirs().get(0).findDirByName("root").getSubDirectories().get(1).getName());
		assertSame("code", fileSystem.getRootDirs().get(0).findDirByName("home").getSubDirectories().get(0).getName());
	}
	
	@Test
	void test_subfilesandfoldersNumber() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertEquals(2, fileSystem.getRootDirs().get(0).findDirByName("root").getCountChildren());
		assertEquals(2, fileSystem.getRootDirs().get(0).findDirByName("applications").getCountChildren());
		assertEquals(3, fileSystem.getRootDirs().get(0).findDirByName("home").getCountChildren());
		assertEquals(2, fileSystem.getRootDirs().get(0).findDirByName("code").getCountChildren());
	}
	
	@Test
	public void test_DirectoryEqualsRoot() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "true", "root", "0", localTime.toString(), null, "3050", "2" };
		Directory actual = fileSystem.getRootDirs().get(0).findDirByName("root");
		assertArrayEquals(expected,fsElementToStringArray(actual));
	}
	@Test
	void test_rootsize() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertEquals(3050, fileSystem.getRootDirs().get(0).findDirByName("root").getTotalSize());	
	}
	
	@Test
	public void Test_DirectoryEqualsHome() { 
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "true", "home", "0", localTime.toString(), "root", "1400", "3" };
		Directory actual = fileSystem.getRootDirs().get(0).findDirByName("home");
		assertArrayEquals(expected,fsElementToStringArray(actual));
	}
	
	@Test
	void test_folderSize() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertEquals(3050, fileSystem.getRootDirs().get(0).findDirByName("root").getTotalSize());
		assertEquals(200, fileSystem.getRootDirs().get(0).findDirByName("code").getTotalSize());
		assertEquals(1650, fileSystem.getRootDirs().get(0).findDirByName("applications").getTotalSize());
		assertEquals(1400, fileSystem.getRootDirs().get(0).findDirByName("home").getTotalSize());
		
	}
	@Test
	public void test_DirectoryEqualsApplications() { 
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "true", "applications", "0", localTime.toString(), "root", "1650", "2" };
		Directory actual = fileSystem.getRootDirs().get(0).findDirByName("applications");
		assertArrayEquals(expected,fsElementToStringArray(actual));
	}
	
	
}
