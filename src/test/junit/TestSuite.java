package test.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestJUnitWithAnnotation.class, TestJUnitWithTestCase.class })
public class TestSuite {
	
}
