package edu.umb.cs680.hw10;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AfspFileCrawlingVisitorTest {

static LocalDateTime localTime = LocalDateTime.of(2020, 04, 25, 0, 0);
private String[] arraystringfordirectory(ArrayList<ApfsFile> files) {
	String[] filesInDir = new String[files.size()];
	int i = 0;
	for(ApfsFile f : files) {
		filesInDir[i] = f.getName();
		i++;
	}
	return filesInDir;
}
	@SuppressWarnings("unused")
	@BeforeAll
	public static void setupupoffilestructure() {
		
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.initFileSystem("drive", 3500);
		ApfsDirectory applications = new ApfsDirectory(root, "applications", 0, localTime, "vipul", localTime);
		ApfsDirectory home = new ApfsDirectory(root, "home", 0, localTime, "vipul", localTime);
		ApfsDirectory code = new ApfsDirectory(home, "code", 0, localTime, "vipul", localTime);
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
	void test_FilesInCode() {
		ApfsFileCrawlingVisitor visitor = new ApfsFileCrawlingVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		ApfsDirectory pictures = root.findDirByName("code");
		pictures.accept(visitor);
		String[] expected = {"Six","Seven"};
		ArrayList<ApfsFile> actual= visitor.getFiles();
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}

	@Test
	void test_FilesInRoot() {
		ApfsFileCrawlingVisitor visitor = new ApfsFileCrawlingVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		root.accept(visitor);
		String[] expected = {"One","Two","Six","Seven","Three","Four"};
		ArrayList<ApfsFile> actual= visitor.getFiles();
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
	
	@Test
	void test_FilesInApplcations() {
		ApfsFileCrawlingVisitor visitor = new ApfsFileCrawlingVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		ApfsDirectory system = root.findDirByName("applications");
		system.accept(visitor);
		String[] expected = {"One","Two"};
		ArrayList<ApfsFile> actual= visitor.getFiles();
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}

	@Test
	void test_FilesInHome() {
		ApfsFileCrawlingVisitor visitor = new ApfsFileCrawlingVisitor();
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)ApfsFileSystem.getRootDir();
		ApfsDirectory home = root.findDirByName("home");
		home.accept(visitor);
		String[] expected = {"Six","Seven","Three","Four"};
		ArrayList<ApfsFile> actual= visitor.getFiles();
		assertArrayEquals(expected,arraystringfordirectory(actual));
	}
}

