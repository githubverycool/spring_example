package serviceTest;

import org.junit.Test;

import spring.dao.UserDao;
import spring.entry.User;
import spring.service.UserService;
import example.core.bean.ApplicationContextUtil;
import Base.JunitLoad;

public class UserServiceTest extends JunitLoad {

	@Test
	public void testSaveUser(){
		UserService userService=ApplicationContextUtil.getBean(UserService.class);
		User u = new User();
		u.setName("t");
		userService.save(u);
	}
}
