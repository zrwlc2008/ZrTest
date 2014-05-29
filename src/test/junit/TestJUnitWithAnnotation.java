package test.junit;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestJUnitWithAnnotation {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testTrue() {
		org.junit.Assert.assertTrue(1==2);
	}

}
