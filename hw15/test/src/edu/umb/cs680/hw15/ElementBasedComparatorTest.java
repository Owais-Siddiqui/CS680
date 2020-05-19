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

class ElementBasedComparatorTest {
	
	static LocalDateTime localTime = LocalDateTime.of(2020, 05, 12, 0, 0);
	
	@SuppressWarnings("unused")
	@BeforeAll
public static void setupupoffilestructure() {
		
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.initFileSystem("drive", 3500);
		ApfsDirectory applications = new ApfsDirectory(root, "applications", 0, localTime, "owais", localTime);
		ApfsDirectory home = new ApfsDirectory(root, "home", 0, localTime, "owais", localTime);
		ApfsDirectory code = new ApfsDirectory(home, "code", 0, localTime, "owais", localTime);
		ApfsFile one = new ApfsFile(applications, "one", 350, localTime, "owais", LocalDateTime.of(2020, 05, 22, 10, 0));
		ApfsFile two = new ApfsFile(applications, "Two", 700, localTime, "owais", LocalDateTime.of(2020, 05, 23, 10, 0));
		ApfsFile three = new ApfsFile(home, "Three", 800, localTime, "owais", LocalDateTime.of(2020, 05, 22, 15, 0));
		ApfsFile four = new ApfsFile(home, "Four", 80, localTime, "owais", LocalDateTime.of(2020, 05, 23, 20, 0));
		ApfsLink five = new ApfsLink(home, "Five", 0, localTime, "owais", LocalDateTime.of(2020, 05, 24, 0, 15), applications);
		ApfsFile ten = new ApfsFile(code, "ten", 700, localTime, "owais", LocalDateTime.of(2020, 05, 22, 23, 0));
		ApfsFile six = new ApfsFile(code, "Six", 870, localTime, "owais", LocalDateTime.of(2020, 05, 23, 0, 20));		
		ApfsLink seven = new ApfsLink(code, "Seven", 0, localTime, "owais", LocalDateTime.of(2020, 05, 24, 0, 20), two);
	}
	
	private String[] elementToStringArray(LinkedList<ApfsElement> fsElements) {
		String[] nameoflistfile = new String[fsElements.size()];
		int i = 0;
		for(ApfsElement apfselement : fsElements) {
			nameoflistfile[i] = apfselement.getName();
			i++;
		}
		return nameoflistfile;
	}

	@Test
	void Test_ElementComparator_System() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = {"code", "Three", "Four","Five"};
		assertArrayEquals(expected,elementToStringArray(root.findDirByName("home").getChildren((ApfsElement object1, ApfsElement object2) -> object1.getOwnerName().compareTo(object2.getOwnerName()))));
	}
	
	@Test
	void Test_ElementComparator_Pictures() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = {"ten", "Six", "Seven"};
		assertArrayEquals(expected,elementToStringArray(root.findDirByName("code").getChildren((ApfsElement object1, ApfsElement object2) -> object1.getOwnerName().compareTo(object2.getOwnerName()))));
	}
}
