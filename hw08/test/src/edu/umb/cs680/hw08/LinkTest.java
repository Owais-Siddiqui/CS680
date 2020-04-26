package edu.umb.cs680.hw08;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LinkTest {
	
	static LocalDateTime localTime = LocalDateTime.now();
	private String[] fsElementToStringArray(FSElement f) {
		Optional<Directory> optionalDirectory = Optional.ofNullable(f.getParent());
		String[] fsElementInfo = { Boolean.toString(f.isDirectory()), f.getName(), 
				Integer.toString(f.getSize()), f.getCreationTime().toString(), 
				optionalDirectory.isPresent()?f.getParent().getName():null};
		return fsElementInfo;
	}
	@SuppressWarnings("unused")
	@BeforeAll
	public static void setupupoffilestructure() {
		Directory root = new Directory(null, "root", 0, localTime);
		Directory applications = new Directory(root, "applications", 0, localTime);
		Directory home = new Directory(root, "home", 0, localTime);
		Directory code = new Directory(home, "code", 0, localTime);
		File One = new File(applications, "One", 1300, localTime);
		File Two = new File(applications, "Two", 350, localTime);
		File Three = new File(home, "Three", 500, localTime);
		File Four = new File(home, "Four", 700, localTime);
		Link Five = new Link(home, "Five", 0, localTime, applications);
		File Six = new File(code, "Six", 70, localTime);
		File Seven = new File(code, "Seven", 130, localTime);	
		Link Eight = new Link(code, "Eight", 0, localTime, Two);
		
		FileSystem.getFileSystem().addRootDir(root);
	}
	
	@Test
	public void test_TargetEqualsHome() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "true", "applications", "0", localTime.toString(), "root" };
		Directory actual = (Directory) fileSystem.getRootDirs().get(0).findLinkByName("Five").getTarget();
		assertArrayEquals(expected,fsElementToStringArray(actual));
	}
	@Test
	public void test_TargetsSix() {
		FileSystem fileSystem = FileSystem.getFileSystem();
		String[] expected = { "false", "Two", "350", localTime.toString(), "applications" };
		File actual = (File) fileSystem.getRootDirs().get(0).findLinkByName("Eight").getTarget();
		assertArrayEquals(expected,fsElementToStringArray(actual));
	}

}
