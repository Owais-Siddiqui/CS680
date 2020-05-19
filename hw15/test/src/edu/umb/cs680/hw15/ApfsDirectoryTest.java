package edu.umb.cs680.hw15;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw15.APFS;
import edu.umb.cs680.hw15.ApfsDirectory;
import edu.umb.cs680.hw15.ApfsFile;
import edu.umb.cs680.hw15.ApfsLink;

class ApfsDirectoryTest {

static LocalDateTime localTime = LocalDateTime.of(2020, 05, 22, 0, 0);
private String[] arraystringfordirectory(ApfsDirectory directory) {
	Optional<ApfsDirectory> optionalDirectory = Optional.ofNullable(directory.getParent());
	String[] inforofdirectory = { Boolean.toString(directory.isDirectory()), directory.getName(), 
			Integer.toString(directory.getSize()), directory.getCreationTime().toString(), 
			optionalDirectory.isPresent()?directory.getParent().getName():null, 
					Integer.toString(directory.getTotalSize()),
					Integer.toString(directory.countChildren()), directory.getOwnerName(),directory.getLastModified().toString()};
	return inforofdirectory;
}
	@SuppressWarnings("unused")
	@BeforeAll
	public static void setupupoffilestructure() {
		
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.initFileSystem("drive", 3500);
		ApfsDirectory applications = new ApfsDirectory(root, "applications", 0, localTime, "owais", localTime);
		ApfsDirectory home = new ApfsDirectory(root, "home", 0, localTime, "owais", localTime);
		ApfsDirectory code = new ApfsDirectory(home, "code", 0, localTime, "owais", localTime);
		ApfsFile one = new ApfsFile(applications, "One", 350, localTime, "owais", LocalDateTime.of(2020, 05, 12, 0, 0));
		ApfsFile two = new ApfsFile(applications, "Two", 700, localTime, "owais", LocalDateTime.of(2020, 05, 22, 10, 0));
		ApfsFile three = new ApfsFile(home, "Three", 800, localTime, "owais", LocalDateTime.of(2020, 05, 22, 10, 0));
		ApfsFile four = new ApfsFile(home, "Four", 80, localTime, "owais", LocalDateTime.of(2020, 05, 22, 10, 0));
		ApfsLink five = new ApfsLink(home, "Five", 0, localTime, "owais", LocalDateTime.of(2020, 05, 22, 10, 0), applications);
		ApfsFile apfselement = new ApfsFile(code, "apfselement", 700, localTime, "owais", LocalDateTime.of(2020, 05, 22, 10, 0));
		ApfsFile six = new ApfsFile(code, "Six", 870, localTime, "owais", LocalDateTime.of(2020, 05, 22, 10, 0));		
		ApfsLink seven = new ApfsLink(code, "Seven", 0, localTime, "owais", LocalDateTime.of(2020, 05, 22, 10, 0), two);
	
	}
	
	@Test
	void test_sizeOfdir() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		assertEquals(3500, root.findDirByName("root").getTotalSize());
		assertEquals(2450, root.findDirByName("home").getTotalSize());
		assertEquals(1050, root.findDirByName("applications").getTotalSize());
		assertEquals(1570, root.findDirByName("code").getTotalSize());
	}


	@Test
	void test_file_Dir() {
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
		assertSame("Two", root.findFileByName("Two").getName());
	}
	@Test
	public void test_DirectoryequalsHome() { 
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "true", "home", "0", localTime.toString(), "root", "2450", "3", "owais", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("home");
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	@Test
	void test_fetchedDirAndFile() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		assertSame("home", root.findDirByName("home").getName());
		assertSame("root", root.findDirByName("root").getName());
		assertSame("applications", root.findDirByName("applications").getName());
		assertSame("code", root.findDirByName("code").getName());
		assertSame("One", root.findFileByName("One").getName());
		assertSame("Six", root.findFileByName("Six").getName());
	}
	
	
	@Test
	public void test_DirectoryequalsCode() { 
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "true", "code", "0", localTime.toString(), "home", "1570", "2", "owais", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("code");
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	@Test
	public void test_DirectoryequalsApplications() { 
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = { "true", "applications", "0", localTime.toString(), "root", "1050", "2", "owais", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("applications");
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	@Test
	void test_info_files() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		assertSame("Three", root.findDirByName("home").getFiles().get(1).getName());
		assertSame("One", root.findDirByName("applications").getFiles().get(0).getName());
		assertSame("Two", root.findDirByName("applications").getFiles().get(1).getName());
		assertSame("Four", root.findDirByName("home").getFiles().get(0).getName());
	}
	
	@Test
	void testsubdir() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		assertSame("applications", root.findDirByName("root").getSubDirectories().get(0).getName());
		assertSame("home", root.findDirByName("root").getSubDirectories().get(1).getName());
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
	public void test_DirectoryequalsRoot() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "true", "root", "0", localTime.toString(), null, "3500", "2", "owais", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("root");
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	
	
	
	
	

}
