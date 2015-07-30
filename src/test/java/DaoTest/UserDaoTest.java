package DaoTest;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.util.Assert;

import spring.dao.UserDao;
import spring.entry.User;
import example.core.bean.ApplicationContextUtil;
import Base.JunitLoad;

public class UserDaoTest extends JunitLoad {
 
	@Test
	public void queryBySqlTest(){
		UserDao userDao=ApplicationContextUtil.getBean(UserDao.class);
		try {
			List users=userDao.queryByIocSql();
			Assert.notEmpty(users);
			Iterator it = users.iterator();
			while(it.hasNext()){
				Assert.isInstanceOf(User.class, it.next());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
