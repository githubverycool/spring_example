package springTest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import example.core.bean.ApplicationContextUtil;
import Base.JunitLoad;

public class ApplicationTest extends JunitLoad {
	
	@Test
	public void testApplicationUtil(){
		ApplicationContext ac=ApplicationContextUtil.getApplicationContext();
		Assert.notNull(ac);
	}
	
}
