package edu.umb.cs680.hw10;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ApfsLinkTest {
	
	static LocalDateTime localTime = LocalDateTime.of(2020, 04, 22, 0, 0);
	private String[] stringelementforfs(FSElement Elementforfs) {
		Optional<ApfsDirectory> optionalDirectory = Optional.ofNullable(Elementforfs.getParent());
		String[] informationoffs = { Boolean.toString(Elementforfs.isDirectory()), Elementforfs.getName(), 
				Integer.toString(Elementforfs.getSize()), Elementforfs.getCreationTime().toString(), 
				optionalDirectory.isPresent()?Elementforfs.getParent().getName():null};
		return informationoffs;
	}
	@SuppressWarnings("unused")
	@BeforeAll
	public static void setupupoffilestructure() {
		
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
	public void test_home() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "true", "home", "0", localTime.toString(), "root" };
		ApfsDirectory actual = root.findDirByName("home");
		assertArrayEquals(expected,stringelementforfs(actual));
	}
	
	@Test
	public void Test_TargetEqualsOne() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "false", "One", "350", localTime.toString(), "applications" };
		// True for applications as one is part of applications but still returns false.
		ApfsFile actual = root.findFileByName("One");
		assertArrayEquals(expected,stringelementforfs(actual));
	}

	@Test
	public void test_TargetEqualsTwo() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "false", "Two", "700", localTime.toString(), "applications" };
		// True for applications as twp is part of applications but still returns false.
		ApfsFile actual = root.findFileByName("Two");
		assertArrayEquals(expected,stringelementforfs(actual));
	}

	@Test
	public void test_TargetEqualsSix() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "false", "Six", "700", localTime.toString(), "code" };
		// True for applications as six is part of code but still returns false.
		ApfsFile actual = root.findFileByName("Six");
		assertArrayEquals(expected,stringelementforfs(actual));
	}

	@Test
	public void test_TargetEqualsFour() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "false", "Four", "80", localTime.toString(), "home" };
		// True for applications as four is part of home but still returns false.
		ApfsFile actual = root.findFileByName("Four");
		assertArrayEquals(expected,stringelementforfs(actual));
	}
}

