package springTest;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import spring.entry.User;
import spring.log.ILog;
import spring.service.UserService;
import spring.service.impl.UserServiceImpl;
import example.core.bean.ApplicationContextUtil;
import Base.JunitLoad;

public class AopDeclareParentsTest extends JunitLoad {
	@Test
	public void testADP() {
		ApplicationContext ac = ApplicationContextUtil.getApplicationContext();
		// 验证是否是ILog子类
		ILog log = (ILog) ac.getBean("userService");
		Assert.assertNotNull(log.getLogger());
		// 通过反射验证是否有aop添加的方法
		Object o = ac.getBean("userService");
		Method[] ms = o.getClass().getDeclaredMethods();
		boolean b = false;
		String name = "getLogger";
		for (Method m : ms) {
			if (m.getName().equals(name)) {
				name = m.getName();
				b = true;
				break;
			}
		}
		//接口之间强制转换
		Assert.assertTrue(b);
		UserService us=(UserService) log;
		Assert.assertNotNull(us);
		// 装饰器模式也可以动态的为对象添加新功能，采用的实现方式大致是从新返回一个子类，该子类从新实现新的功能
		// aop采用的方式大致是 代理类拦截请求，把新增的功能委托给其他对象实现。
		// 这样获取的bean要提前转换成某个接口的子类：
		// 如ac.getBean("userService")要么是ILog的子类或者是UserService的子类

	}
}
