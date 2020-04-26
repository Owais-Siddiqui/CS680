package edu.umb.cs680.hw09;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileSystemTest {

static LocalDateTime localTime = LocalDateTime.of(2020, 04, 25, 0, 0);
	
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

	private String[] stringarraycreation(ApfsDirectory dir) {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		Optional<ApfsDirectory> optionalDirectory = Optional.ofNullable(dir.getParent());
		String[] infofordirectory = { Boolean.toString(dir.isDirectory()), dir.getName(), 
				Integer.toString(dir.getSize()), dir.getCreationTime().toString(), 
				optionalDirectory.isPresent()?dir.getParent().getName():null, 
						Integer.toString(dir.getTotalSize()),
						Integer.toString(dir.countChildren()), FilesystemofApfs.getFileSystemName(), 
						Integer.toString(FilesystemofApfs.getCapacity()), dir.getOwnerName(),dir.getLastModified().toString() };
		return infofordirectory;
	}
	

	@Test
	public void test_RootInstance() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		assertEquals(root.getOwnerName(), "Owais");
		assertEquals(root.getLastModified(), localTime);
	}

	@Test
	public void test_RootStatus() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "true", "root", "0", localTime.toString(), null, "3500", "2", "drive", "3500", "Owais", localTime.toString()};
		ApfsDirectory actual = root.findDirByName("root");
		assertArrayEquals(expected,stringarraycreation(actual));
	}	

}
