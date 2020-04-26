package edu.umb.cs680.hw10;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



class ApfsFileTest {

static LocalDateTime localTime = LocalDateTime.of(2020, 04, 22, 0, 0);
private String[] arraystringforfile(ApfsFile Elementforfs) {
	Optional<ApfsDirectory> optionalDirectory = Optional.ofNullable(Elementforfs.getParent());
	String[] informationoffile = { Boolean.toString(Elementforfs.isDirectory()), Elementforfs.getName(), 
			Integer.toString(Elementforfs.getSize()), Elementforfs.getCreationTime().toString(), 
			optionalDirectory.isPresent()?Elementforfs.getParent().getName():null, Elementforfs.getOwnerName(),Elementforfs.getLastModified().toString()};
	return informationoffile;
}
	@SuppressWarnings("unused")
	@BeforeAll
	public static void initialize() {
		
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.initFileSystem("drive", 3500);
		ApfsDirectory applications = new ApfsDirectory(root, "applications", 0, localTime, "Owais", localTime);
		ApfsDirectory home = new ApfsDirectory(root, "home", 0, localTime, "Owais", localTime);
		ApfsDirectory code = new ApfsDirectory(home, "code", 0, localTime, "Owais", localTime);
		ApfsFile One = new ApfsFile(applications, "One", 350, localTime, "Owais", localTime);
		ApfsFile Two = new ApfsFile(applications, "Two", 700, localTime, "Owais", localTime);
		ApfsFile Three = new ApfsFile(home, "Three", 800, localTime, "Owais", localTime);
		ApfsFile Four = new ApfsFile(home, "Four", 80, localTime, "Owais", localTime);
		ApfsLink Five = new ApfsLink(home, "Five", 0, localTime, "Owais", localTime, applications);
		ApfsFile Six = new ApfsFile(code, "Six", 700, localTime, "Owais", localTime);
		ApfsFile Seven = new ApfsFile(code, "Seven", 870, localTime, "Owais", localTime);
		ApfsLink Eight = new ApfsLink(code, "Eight", 0, localTime, "Owais", localTime, Two);
	}

	@Test
	void testfordir() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		assertTrue(root.findDirByName("root").isDirectory());
		assertTrue(root.findDirByName("home").isDirectory());
		assertTrue(root.findDirByName("applications").isDirectory());
		assertTrue(root.findDirByName("code").isDirectory());
		assertFalse(root.findFileByName("One").isDirectory());
		assertFalse(root.findFileByName("Two").isDirectory());
		assertFalse(root.findFileByName("Three").isDirectory());
		assertFalse(root.findFileByName("Four").isDirectory());
		assertFalse(root.findFileByName("Six").isDirectory());
		assertFalse(root.findFileByName("Seven").isDirectory());
	}

	@Test
	public void test_FileEqualsOne() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "false", "One", "350", localTime.toString(), "applications", "Owais", localTime.toString() };
		ApfsFile actual = root.findFileByName("One");
		assertArrayEquals(expected,arraystringforfile(actual));
	}
	
	@Test
	public void test_FileEqualsTwo() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "false", "Two", "700", localTime.toString(), "applications", "Owais", localTime.toString() };
		ApfsFile actual = root.findFileByName("Two");
		assertArrayEquals(expected,arraystringforfile(actual));
	}
	
	@Test
	public void test_FileEqualsThree() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "false", "Three", "800", localTime.toString(), "home", "Owais", localTime.toString() };
		ApfsFile actual = root.findFileByName("Three");
		assertArrayEquals(expected,arraystringforfile(actual));
	}

	@Test
	public void test_FileEqualsFour() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "false", "Four", "80", localTime.toString(), "home", "Owais", localTime.toString() };
		ApfsFile actual = root.findFileByName("Four");
		assertArrayEquals(expected,arraystringforfile(actual));
	}

	@Test
	public void test_FileEqualsSix() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "false", "Six", "700", localTime.toString(), "code", "Owais", localTime.toString() };
		ApfsFile actual = root.findFileByName("Six");
		assertArrayEquals(expected,arraystringforfile(actual));
	}

	@Test
	public void test_FileEqualsSeven() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "false", "Seven", "870", localTime.toString(), "code", "Owais", localTime.toString() };
		ApfsFile actual = root.findFileByName("Seven");
		assertArrayEquals(expected,arraystringforfile(actual));
	}

}
