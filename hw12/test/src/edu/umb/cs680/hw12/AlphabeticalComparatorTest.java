package edu.umb.cs680.hw12;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw12.AlphabeticalComparator;
import edu.umb.cs680.hw12.APFS;
import edu.umb.cs680.hw12.ApfsDirectory;
import edu.umb.cs680.hw12.ApfsElement;
import edu.umb.cs680.hw12.ApfsFile;
import edu.umb.cs680.hw12.ApfsLink;

public class AlphabeticalComparatorTest {
	
	APFS fs;

	private void befor() {
		fs = APFS.getFileSystem();
		ApfsElement root = new ApfsDirectory(null, "Root", 0, LocalDateTime.of(2020, 2, 2, 5, 0), "Owais", null);

		ApfsElement system = new ApfsDirectory(root, "System", 0, LocalDateTime.of(2020, 2, 2, 5, 0), "Owais", null);
		root.appendChild(system);
		
		ApfsElement home = new ApfsDirectory(root, "Home", 0, LocalDateTime.of(2020, 2, 2, 5, 0), "Owais", null);
		root.appendChild(home);
		
		ApfsElement picture = new ApfsDirectory(home, "Picture", 0, LocalDateTime.of(2020, 2, 2, 5, 0), "Owais", null);
		home.appendChild(picture);

		
		ApfsFile one = new ApfsFile(system, "one.txt", 100, LocalDateTime.of(2020, 2, 2, 5, 0), "Owais", null);

		ApfsFile two = new ApfsFile(system, "two.txt", 200, LocalDateTime.of(2020, 2, 2, 5, 0), "Owais", null);

		ApfsFile three = new ApfsFile(system, "three.txt", 100, LocalDateTime.of(2020, 2, 2, 5, 0), "Owais", null);
		system.appendChild(one);
		system.appendChild(two);
		system.appendChild(three);
		
		ApfsFile four = new ApfsFile(home, "four.txt", 400, LocalDateTime.of(2020, 2, 2, 5, 0), "Owais", null);
		home.appendChild(four);

		ApfsElement five = new ApfsLink(home, "five", 0, LocalDateTime.of(2020, 2, 2, 5, 0), system, "Owais", null);
		home.appendChild(five);
		
		ApfsFile six = new ApfsFile(picture, "six.txt", 500, LocalDateTime.of(2020, 2, 2, 5, 0), "Owais", null);

		ApfsFile seven = new ApfsFile(picture, "seven.txt", 100, LocalDateTime.of(2020, 2, 2, 5, 0), "Owais", null);

		ApfsElement eight = new ApfsLink(picture, "eight", 0, LocalDateTime.of(2020, 2, 2, 5, 0), six, "Owais", null);
		picture.appendChild(eight);
		picture.appendChild(six);
		picture.appendChild(seven);
		fs.setRoot(root);

	}
	

	@Test
	public void test_LinkEqualsSystem() {
		befor();
		LinkedList<ApfsElement> l1 = ((ApfsDirectory)fs.getRoot()).getChildren();
		String str = l1.getFirst().getName();
		LinkedList<ApfsElement> l2 = ((ApfsDirectory)fs.getRoot()).getChildren(new AlphabeticalComparator());
		String str1 = l2.getFirst().getName();
		assertNotEquals(str, str1);
	}
}

