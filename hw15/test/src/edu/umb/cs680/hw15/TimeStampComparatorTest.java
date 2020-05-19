package edu.umb.cs680.hw15;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw15.APFS;
import edu.umb.cs680.hw15.ApfsDirectory;
import edu.umb.cs680.hw15.ApfsElement;
import edu.umb.cs680.hw15.ApfsFile;
import edu.umb.cs680.hw15.ApfsLink;

class TimeStampComparatorTest {
	
static LocalDateTime localTime = LocalDateTime.of(2020, 05, 12, 0, 0);
private String[] arraystringfordirectory(LinkedList<ApfsElement> fsElements) {
	String[] listnamefile = new String[fsElements.size()];
	int i = 0;
	for(ApfsElement afpselement : fsElements) {
		listnamefile[i] = afpselement.getName();
		i++;
	}
	return listnamefile;
}
	@SuppressWarnings("unused")
	@BeforeAll
	public static void setupupoffilestructure() {
		
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.initFileSystem("drive", 3500);
		ApfsDirectory applications = new ApfsDirectory(root, "applications", 0, localTime, "owais", LocalDateTime.of(2020, 05, 12, 0, 0));
		ApfsDirectory home = new ApfsDirectory(root, "home", 0, localTime, "owais", LocalDateTime.of(2020, 05, 21, 10, 0));
		ApfsDirectory code = new ApfsDirectory(home, "code", 0, localTime, "owais", LocalDateTime.of(2020, 05, 21, 5, 0));
		ApfsFile one = new ApfsFile(applications, "one", 350, localTime, "owais", LocalDateTime.of(2020, 05, 22, 10, 0));
		ApfsFile two = new ApfsFile(applications, "Two", 700, localTime, "owais", LocalDateTime.of(2020, 05, 23, 10, 0));
		ApfsFile three = new ApfsFile(home, "Three", 800, localTime, "owais", LocalDateTime.of(2020, 05, 22, 15, 0));
		ApfsFile four = new ApfsFile(home, "Four", 80, localTime, "owais", LocalDateTime.of(2020, 05, 23, 20, 0));
		ApfsLink five = new ApfsLink(home, "Five", 0, localTime, "owais", LocalDateTime.of(2020, 05, 24, 0, 15), applications);
		ApfsFile apfselement = new ApfsFile(code, "apfselement", 700, localTime, "owais", LocalDateTime.of(2020, 05, 22, 23, 0));
		ApfsFile six = new ApfsFile(code, "Six", 870, localTime, "owais", LocalDateTime.of(2020, 05, 23, 0, 20));		
		ApfsLink seven = new ApfsLink(code, "Seven", 0, localTime, "owais", LocalDateTime.of(2020, 05, 24, 0, 20), two);
	}
	
	
	@Test
	void Test_RC_children() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = {"applications", "home"};
		assertArrayEquals(expected,arraystringfordirectory(root.getChildren((ApfsElement object1, ApfsElement object2) -> object1.getLastModified().compareTo(object2.getLastModified()))));
	}
	
	
	@Test
	void Test_RC_filesforcode() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = {"apfselement", "Six"};
		LinkedList<ApfsElement> actual = new LinkedList<ApfsElement>();
		for(ApfsElement apfselement : root.findDirByName("code").getFiles((ApfsElement object1, ApfsElement object2) -> object1.getLastModified().compareTo(object2.getLastModified()))){
			actual.add(apfselement);
		}
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	@Test
	void Test_RC_directory() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = {"applications", "home"};
		LinkedList<ApfsElement> actual = new LinkedList<ApfsElement>();
		for(ApfsElement apfselement : root.getSubDirectories((ApfsElement object1, ApfsElement object2) -> object1.getLastModified().compareTo(object2.getLastModified()))){
			actual.add(apfselement);
		}
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	@Test
	void Test_RC_filesforhome() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = {"Three", "Four"};
		LinkedList<ApfsElement> actual = new LinkedList<ApfsElement>();
		for(ApfsElement apfselement : root.findDirByName("home").getFiles((ApfsElement object1, ApfsElement object2) -> object1.getLastModified().compareTo(object2.getLastModified()))){
			actual.add(apfselement);
		}
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	
}
