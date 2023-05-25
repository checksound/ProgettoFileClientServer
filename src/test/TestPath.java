package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class TestPath {

	@Test
	void testRoot() {
		
		String requestString = "/";
		Path path = Paths.get(requestString);
		Path pathFileName = path.getFileName();
		
		assertEquals(null, pathFileName);
		
		//fail("Not yet implemented");
	}
	
	@Test
	void testSubdirectory() {
		String requestString = "product/pippo";
		Path path = Paths.get(requestString);
		Path pathFileName = path.getFileName();
		
		assertEquals(Paths.get("pippo"), pathFileName);
		
		
	}

}
