package edu.umb.cs680.hw07;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileTest {
	private String[] stringarraytocompare(File f) {
		Optional<Directory> optionalDirectory = Optional.ofNullable(f.getParent());
		String[] fileInfo = { Boolean.toString(f.isDirectory()), f.getName(), 
				Integer.toString(f.getSize()), f.getCreationTime().toString(), 
				optionalDirectory.isPresent()?f.getParent().getName():null};
		return fileInfo;
	}
	static LocalDateTime localTime = LocalDateTime.now();
	
	@SuppressWarnings("unused")
	@BeforeAll
	public static void directoryandfilecreation() {
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
		//creating files and addding the root directory
		FileSystem.getFileSystem().addRootDir(root);
	}
	
	
	@Test
	public void test_FileEqualsOne() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "false", "One", "800", localTime.toString(), "applications" };
		File actual = fileSystem.getRootDirs().get(0).findFileByName("One");
		assertArrayEquals(expected,stringarraytocompare(actual));
	}
	
	@Test
	public void test_FileEqualsTwo() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "false", "Two", "550", localTime.toString(), "applications" };
		File actual = fileSystem.getRootDirs().get(0).findFileByName("Two");
		assertArrayEquals(expected,stringarraytocompare(actual));
	}
	
	@Test
	public void test_FileEqualsThree() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "false", "Three", "700", localTime.toString(), "home" };
		File actual = fileSystem.getRootDirs().get(0).findFileByName("Three");
		assertArrayEquals(expected,stringarraytocompare(actual));
	}

	
	@Test
	public void test_FileEqualsFour() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "false", "Four", "900", localTime.toString(), "home" };
		File actual = fileSystem.getRootDirs().get(0).findFileByName("Four");
		assertArrayEquals(expected,stringarraytocompare(actual));
	}


	@Test
	public void test_FileEqualsFive() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "false", "Five", "170", localTime.toString(), "code" };
		File actual = fileSystem.getRootDirs().get(0).findFileByName("Five");
		assertArrayEquals(expected,stringarraytocompare(actual));
	}
	

	@Test
	public void test_FileEqualsSix() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "false", "Six", "1300", localTime.toString(), "code" };
		File actual = fileSystem.getRootDirs().get(0).findFileByName("Six");
		assertArrayEquals(expected,stringarraytocompare(actual));
	}


	@Test
	void Test_Directory() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("root").isDirectory());
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("home").isDirectory());
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("applications").isDirectory());
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("code").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("One").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("Two").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("Three").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("Four").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("Five").isDirectory());
		assertFalse(fileSystem.getRootDirs().get(0).findFileByName("Six").isDirectory());
	}
	
	@Test
	void Test_RootDir() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		assertTrue(fileSystem.getRootDirs().get(0).findDirByName("root").isDirectory());
	}
}
