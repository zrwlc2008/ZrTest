package test.springaop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.springaop.service.AService;
import test.springaop.service.BService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml"})
public class TestAOPWithAnnotation extends AbstractJUnit4SpringContextTests {

	@Autowired
	private AService aService;

	@Autowired
	private BService bService;

	/**
	 * 测试正常调用
	 */
	@Test
	public void testCall() {
		aService.barA("111","2222");
		bService.fooB();
	}

	/**
	 * 测试After-Throwing
	 */
	public void testThrow() {
		try {
			bService.barB();
		} catch (IllegalArgumentException e) {

		}
	}

}
