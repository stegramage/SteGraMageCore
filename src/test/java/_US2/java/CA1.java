package _US2.java;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import _SteGraMageCore.Discover;

class CA1 {

	@Test
	void fileNotExistTest() {
		Discover dis = new Discover();
		assertThrows(FileNotFoundException.class, () -> dis.findClasses("/path/to/nothig"));
	}

}
