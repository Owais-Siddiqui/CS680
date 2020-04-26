package edu.umb.cs680.hw10;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AfpsFileSearchVisitorTest {

static LocalDateTime localTime = LocalDateTime.of(2020, 04, 22, 0, 0);
private String[] arraystringfordirectory(ApfsFile f) {
	Optional<ApfsDirectory> optionalDirectory = Optional.ofNullable(f.getParent());
	String[] fileInfo = { Boolean.toString(f.isDirectory()), f.getName(), 
			Integer.toString(f.getSize()), f.getCreationTime().toString(), 
			optionalDirectory.isPresent()?f.getParent().getName():null, f.getOwnerName(),f.getLastModified().toString()};
	return fileInfo;
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
	public void test_FileEqualsOne() {
		ApfsFileSearchVisitor apfsvisior = new ApfsFileSearchVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "One", "350", localTime.toString(), "applications", "Owais", localTime.toString() };
		apfsvisior.setFileName("One");
		root.accept(apfsvisior);
		ApfsFile actual = apfsvisior.getFile();
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	@Test
	public void test_FileEqualsSix() {
		ApfsFileSearchVisitor apfsvisior = new ApfsFileSearchVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "Six", "700", localTime.toString(), "code", "Owais", localTime.toString() };
		apfsvisior.setFileName("Six");
		root.accept(apfsvisior);
		ApfsFile actual = apfsvisior.getFile();
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	@Test
	public void test_FileEqualsTwo() {
		ApfsFileSearchVisitor apfsvisior = new ApfsFileSearchVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "Two", "700", localTime.toString(), "applications", "Owais", localTime.toString() };
		apfsvisior.setFileName("Two");
		root.accept(apfsvisior);
		ApfsFile actual = apfsvisior.getFile();
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	
	@Test
	public void test_FileEqualsThree() {
		ApfsFileSearchVisitor apfsvisior = new ApfsFileSearchVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "Three", "800", localTime.toString(), "home", "Owais", localTime.toString() };
		apfsvisior.setFileName("Three");
		root.accept(apfsvisior);
		ApfsFile actual = apfsvisior.getFile();
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	
	@Test
	public void test_FileEqualsFour() {
		ApfsFileSearchVisitor apfsvisior = new ApfsFileSearchVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "false", "Four", "80", localTime.toString(), "home", "Owais", localTime.toString() };
		apfsvisior.setFileName("Four");
		root.accept(apfsvisior);
		ApfsFile actual = apfsvisior.getFile();
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}

}
