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

class FileSystemTest {

static LocalDateTime localTime = LocalDateTime.of(2020, 05, 22, 0, 0);
private String[] arraystringfordirectory(ApfsDirectory directory) {
	APFS FilesystemofApfs = APFS.getAPFSFileSystem();
	Optional<ApfsDirectory> optionalDirectory = Optional.ofNullable(directory.getParent());
	String[] inforofdirectory = { Boolean.toString(directory.isDirectory()), directory.getName(), 
			Integer.toString(directory.getSize()), directory.getCreationTime().toString(), 
			optionalDirectory.isPresent()?directory.getParent().getName():null, 
					Integer.toString(directory.getTotalSize()),
					Integer.toString(directory.countChildren()), FilesystemofApfs.getFileSystemName(), 
					Integer.toString(FilesystemofApfs.getCapacity()), directory.getOwnerName(),directory.getLastModified().toString() };
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
		ApfsFile one = new ApfsFile(applications, "one", 350, localTime, "owais", LocalDateTime.of(2020, 05, 22,0, 0));
		ApfsFile two = new ApfsFile(applications, "Two", 700, localTime, "owais", LocalDateTime.of(2020, 05, 22, 0, 0));
		ApfsFile three = new ApfsFile(home, "Three", 800, localTime, "owais", LocalDateTime.of(2020, 05, 22, 0, 0));
		ApfsFile four = new ApfsFile(home, "Four", 80, localTime, "owais", LocalDateTime.of(2020, 05, 22, 0, 0));
		ApfsLink five = new ApfsLink(home, "Five", 0, localTime, "owais", LocalDateTime.of(2020, 05, 22, 0, 0), applications);
		ApfsFile apfselement = new ApfsFile(code, "apfselement", 700, localTime, "owais", LocalDateTime.of(2020, 05, 22, 0, 0));
		ApfsFile six = new ApfsFile(code, "Six", 870, localTime, "owais", LocalDateTime.of(2020, 05, 22, 0, 0));		
		ApfsLink seven = new ApfsLink(code, "Seven", 0, localTime, "owais", LocalDateTime.of(2020, 05, 22, 0, 0), two);
	}
	@Test
	public void test_fileOwner() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		assertEquals(root.getOwnerName(), "owais");
		assertEquals(root.getLastModified(), localTime);
	}
	
	
	@Test
	public void test_dir_setup() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "true", "root", "0", localTime.toString(), null, "3500", "2", "drive", "3500", "owais", localTime.toString()};
		ApfsDirectory actual = root.findDirByName("root");
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	
	

}
