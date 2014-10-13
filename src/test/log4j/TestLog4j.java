package test.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLog4j {
	public void printLog() {
		Logger log = Logger.getLogger(TestLog4j.class.getClass());
		log.info("≤‚ ‘info");
		log.debug("≤‚ ‘debug");
		log.error("≤‚ ‘error");
	}

	public static void main(String[] args) {
		//DOMConfigurator.configure("D:/workspace/ZrTest/src/test/log4j/log4j.xml");
		PropertyConfigurator.configure("D:/workspace/ZrTest/src/test/log4j/log4j.properties");
		TestLog4j app = new TestLog4j();
		app.printLog();
	}

}
