package example.core.bean;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtil implements ApplicationContextAware {
	
	private static final Logger log = Logger.getLogger(ApplicationContextUtil.class);
	private static ApplicationContext applicationContext;

	
	/**
	 * spring容器管理bean时，如果继承ApplicationContextAware接口,
	 * spring将调用setApplicationContext方法，将应用上下文的引用传入.
	 */
	@Override
	public void setApplicationContext(ApplicationContext _applicationContext)
			throws BeansException {
		log.info("automatic init applicationContext bean ...");
		applicationContext=_applicationContext;
	}
	
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
    /**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型
	 * @param name
	 * @return
	 */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型
	 * 如果有多个Bean符合Class, 取出第一个
	 * @param clazz
	 * @return
	 */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        Map<String, T> beanMaps = applicationContext.getBeansOfType(clazz);
        if (beanMaps == null || beanMaps.isEmpty()) {
        	return null;
        } else{
        	return beanMaps.values().iterator().next();
        }
    }

    /**
     * 如果spring容器注入上下文引用失败，采用手动注入.
     */
    private static void checkApplicationContext() {
        if (applicationContext == null) {
        	log.info("manual init applicationContext bean...");
			applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        }
    }
}
