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

class ReverseAlphabeticalComparatorTest {

	static LocalDateTime localTime = LocalDateTime.of(2020, 05, 12, 0, 0);
	private String[] arraystringfordirectory(LinkedList<ApfsElement> fsElements) {
		String[] Listfilename = new String[fsElements.size()];
		int i = 0;
		for(ApfsElement Apfselement : fsElements) {
			Listfilename[i] = Apfselement.getName();
			i++;
		}
		return Listfilename;
	}
	@SuppressWarnings("unused")
	@BeforeAll
	public static void setupupoffilestructure() {
		
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.initFileSystem("drive", 3500);
		ApfsDirectory applications = new ApfsDirectory(root, "applications", 0, localTime, "owais", localTime);
		ApfsDirectory home = new ApfsDirectory(root, "home", 0, localTime, "owais", localTime);
		ApfsDirectory code = new ApfsDirectory(home, "code", 0, localTime, "owais", localTime);
		ApfsFile one = new ApfsFile(applications, "One", 350, localTime, "owais", LocalDateTime.of(2020, 05, 22, 10, 0));
		ApfsFile two = new ApfsFile(applications, "Two", 700, localTime, "owais", LocalDateTime.of(2020, 05, 23, 10, 0));
		ApfsFile three = new ApfsFile(home, "Three", 800, localTime, "owais", LocalDateTime.of(2020, 05, 22, 15, 0));
		ApfsFile four = new ApfsFile(home, "Four", 80, localTime, "owais", LocalDateTime.of(2020, 05, 23, 20, 0));
		ApfsLink five = new ApfsLink(home, "Five", 0, localTime, "owais", LocalDateTime.of(2020, 05, 24, 0, 15), applications);
		ApfsFile apfselement = new ApfsFile(code, "apfselement", 700, localTime, "owais", LocalDateTime.of(2020, 05, 22, 23, 0));
		ApfsFile six = new ApfsFile(code, "Six", 870, localTime, "owais", LocalDateTime.of(2020, 05, 23, 0, 20));		
		ApfsLink seven = new ApfsLink(code, "Seven", 0, localTime, "owais", LocalDateTime.of(2020, 05, 24, 0, 20), two);
	}
	
	
	@Test
	void Test_comparatorRev_files() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = {"Two", "One"};
		LinkedList<ApfsElement> actual = new LinkedList<ApfsElement>();
		for(ApfsElement Apfselement : root.findDirByName("applications").getFiles((ApfsElement object1, ApfsElement object2) -> object2.getName().compareTo(object1.getName()))){
			actual.add(Apfselement);
		}
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	
	@Test
	void Test_comparatorRev_child() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = {"home", "applications"};
		assertArrayEquals(expected,arraystringfordirectory(root.getChildren((ApfsElement object1, ApfsElement object2) -> object2.getName().compareTo(object1.getName()))));
	}

	@Test
	void Test_comparatorRev_directory() {
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		String[] expected = {"home", "applications"};
		LinkedList<ApfsElement> actual = new LinkedList<ApfsElement>();
		for(ApfsElement Apfselement : root.getSubDirectories((ApfsElement object1, ApfsElement object2) -> object2.getName().compareTo(object1.getName()))){
			actual.add(Apfselement);
		}
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	
	
}
