package spring.service.impl;


import spring.dao.BaseDao;
import spring.dao.UserDao;
import spring.entry.User;
import spring.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<User> implements UserService  {
	
	private UserDao userDao;

	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public BaseDao<User> getEntryDao() {
		return userDao;
	}

	
}
