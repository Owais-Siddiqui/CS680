package edu.umb.cs680.hw09;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw09.APFS;
import edu.umb.cs680.hw09.ApfsDirectory;

class ApfsDirectoryTest {

static LocalDateTime localTime = LocalDateTime.of(2020, 04, 25, 0, 0);
private String[] arraystringfordirectory(ApfsDirectory Elementforfs) {
	Optional<ApfsDirectory> optionalDirectory = Optional.ofNullable(Elementforfs.getParent());
	String[] informationoffile = { Boolean.toString(Elementforfs.isDirectory()), Elementforfs.getName(), 
			Integer.toString(Elementforfs.getSize()), Elementforfs.getCreationTime().toString(), 
			optionalDirectory.isPresent()?Elementforfs.getParent().getName():null, 
					Integer.toString(Elementforfs.getTotalSize()),
					Integer.toString(Elementforfs.countChildren()), Elementforfs.getOwnerName(),Elementforfs.getLastModified().toString()};
	return informationoffile;
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
	void testforfileanddirectory() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		assertSame("home", root.findDirByName("home").getName());
		assertSame("root", root.findDirByName("root").getName());
		assertSame("applications", root.findDirByName("applications").getName());
		assertSame("code", root.findDirByName("code").getName());
		assertSame("One", root.findFileByName("One").getName());	
		assertSame("Three", root.findFileByName("Three").getName());
		assertSame("Four", root.findFileByName("Four").getName());
		assertSame("Six", root.findFileByName("Six").getName());
		assertSame("Seven", root.findFileByName("Seven").getName());
		assertSame("Two", root.findFileByName("Two").getName());
	}
	@Test
	void testdir() {
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
	void testforsizedir() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		assertEquals(3500, root.findDirByName("root").getTotalSize());
		assertEquals(2450, root.findDirByName("home").getTotalSize());
		assertEquals(1050, root.findDirByName("applications").getTotalSize());
		assertEquals(1570, root.findDirByName("code").getTotalSize());
	}
	@Test
	public void verifyDirectoryEqualityApplications() { 
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "true", "applications", "0", localTime.toString(), "root", "1050", "2", "Owais", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("applications");
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	@Test
	void testforsizecode() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		assertEquals(1570, root.findDirByName("code").getTotalSize());
	}
	
	@Test
	public void verifyDirectoryEqualityCode() { 
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "true", "code", "0", localTime.toString(), "home", "1570", "2", "Owais", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("code");
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	
	@Test
	void testsubdir() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		assertSame("applications", root.findDirByName("root").getSubDirectories().get(0).getName());
		assertSame("home", root.findDirByName("root").getSubDirectories().get(1).getName());
	}
	
	@Test
	void testforinfoonfiles() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		assertSame("Four", root.findDirByName("home").getFiles().get(1).getName());
		assertSame("One", root.findDirByName("applications").getFiles().get(0).getName());
		assertSame("Two", root.findDirByName("applications").getFiles().get(1).getName());
		assertSame("Three", root.findDirByName("home").getFiles().get(0).getName());
	}

	@Test
	void testsubfilesindir() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		assertEquals(2, root.findDirByName("root").countChildren());
		assertEquals(2, root.findDirByName("applications").countChildren());
		assertEquals(3, root.findDirByName("home").countChildren());
		assertEquals(2, root.findDirByName("code").countChildren());
	}
	
	@Test
	public void verifyDirectoryEqualityRoot() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "true", "root", "0", localTime.toString(), null, "3500", "2", "Owais", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("root");
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	
	@Test
	public void verifyDirectoryEqualityCodefile() { 
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "true", "code", "0", localTime.toString(), "home", "1570", "2", "Owais", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("code");
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	@Test
	public void verifyDirectoryEqualityHome() { 
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "true", "home", "0", localTime.toString(), "root", "2450", "3", "Owais", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("home");
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	
	
	
	

}
