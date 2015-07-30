package spring.aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class UserServiceInterceptor implements MethodInterceptor{

	private static final Logger log = Logger.getLogger(UserServiceInterceptor.class);
	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		log.info("拦截的方法："+arg0.getMethod());
		return arg0.proceed();
	}

}
