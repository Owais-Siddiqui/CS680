package edu.umb.cs680.hw09;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class APFSTest {

	static LocalDateTime localTime = LocalDateTime.of(2020, 04, 25, 0, 0);
	private String[] stringarraycreation(ApfsDirectory directory) {
		Optional<ApfsDirectory> optionalDirectory = Optional.ofNullable(directory.getParent());
		String[] inforofdirectory = { Boolean.toString(directory.isDirectory()), directory.getName(), 
				Integer.toString(directory.getSize()), directory.getCreationTime().toString(), 
				optionalDirectory.isPresent()?directory.getParent().getName():null, 
						Integer.toString(directory.getTotalSize()),
						Integer.toString(directory.countChildren()), directory.getOwnerName(),directory.getLastModified().toString()};
		return inforofdirectory;
	}
	@BeforeAll
	public static void setupupoffilestructure() {
		
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		FilesystemofApfs.initFileSystem("drive", 3500);
	}
	
	
	@Test
	public void testrootmaindirectory() {
		APFS FilesystemofApfs = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory)FilesystemofApfs.getRootDir();
		String[] expected = { "true", "root", "0", localTime.toString(), null, "0", "0", "Owais", localTime.toString() };
		ApfsDirectory actual = root.findDirByName("root");
		assertArrayEquals(expected,stringarraycreation(actual));
	}

}
