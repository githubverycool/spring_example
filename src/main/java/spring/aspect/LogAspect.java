package spring.aspect;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {

	private static final  org.apache.log4j.Logger log = Logger.getLogger(LogAspect.class);
//	private static final  org.apache.log4j.Logger log2 = (Logger) LogFactory.getLog(LogAspect.class);

	public void beforeLog(JoinPoint jp) {
		log.info("获取被通知的对象:"+jp.getTarget());
		log.info("获取被通知的方法:"+jp.getSignature().getName());
		log.info("获取被通知的参数数组:"+jp.getArgs());
		execute(new Throwable().getStackTrace()[0].getMethodName());
	}

	public void afterLog(JoinPoint jp) {
		execute(new Throwable().getStackTrace()[0].getMethodName());
	}

	public void afterReturnLog(JoinPoint jp) {
		execute(new Throwable().getStackTrace()[0].getMethodName());
	}

	public void afterThrowingLog(JoinPoint jp) {
		execute(new Throwable().getStackTrace()[0].getMethodName());
	}

	public void aroundLog(ProceedingJoinPoint pjp) {
		try {
			Long begin=System.currentTimeMillis();
			//环绕通知需要调用此方法
			pjp.proceed();//调用被通知的方法(即拦截的方法)
			Long end=System.currentTimeMillis();
			log.info("run time:"+(end-begin));

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void execute(String methodName) {
		log.info(methodName + " say hello!");
	}

	public static void main(String[] args) {
		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
	}
}
