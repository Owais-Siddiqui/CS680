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


class AlphabeticalComparatorTest {

	static LocalDateTime localTime = LocalDateTime.of(2020, 05, 22, 0, 0);
	private String[] Stringarrayforelement(LinkedList<ApfsElement> fsElements) {
		String[] listoffilename = new String[fsElements.size()];
		int i = 0;
		for(ApfsElement elementforapfs : fsElements) {
			listoffilename[i] = elementforapfs.getName();
			i++;
		}
		return listoffilename;
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
	void Test_order_child() {
		APFS Apfssystemfile = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)Apfssystemfile.getRootDir();
		String[] expected = {"applications", "home"};
		assertArrayEquals(expected,Stringarrayforelement(root.getChildren()));
	}
	@Test
	void Test_Order_Files() {
		APFS Apfssystemfile = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)Apfssystemfile.getRootDir();
		String[] expected = {"Two", "one"};
		LinkedList<ApfsElement> actual = new LinkedList<ApfsElement>();
		for(ApfsElement apfselement : root.findDirByName("applications").getFiles()){
			actual.add(apfselement);
		}
		assertArrayEquals(expected,Stringarrayforelement(actual));
	}
	
	
	@Test
	void Test_dire_order() {
		APFS Apfssystemfile = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)Apfssystemfile.getRootDir();
		String[] expected = {"applications", "home"};
		LinkedList<ApfsElement> actual = new LinkedList<ApfsElement>();
		for(ApfsElement apfselement : root.getSubDirectories()){
			actual.add(apfselement);
		}
		assertArrayEquals(expected,Stringarrayforelement(actual));
	}
	
	
}
