package spring.service.impl;

import java.io.Serializable;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import spring.dao.BaseDao;
import spring.service.BaseService;

public abstract class BaseServiceImpl<T extends Serializable> implements BaseService<T> {

	private TransactionTemplate transactionTemplate;
	

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public abstract BaseDao<T> getEntryDao();

	/**
	 * 一个编码式事务执行案例
	 */
	@Override
	public void save(final T entry) {
		getTransactionTemplate().execute(new TransactionCallback<Void>() {
			@Override
			public Void doInTransaction(TransactionStatus status) {
				try {
					getEntryDao().save(entry);
				} catch (Exception e) {
					status.setRollbackOnly();
					e.printStackTrace();
//					throw e;必须是运行时异常(未检出异常)
				}
				return null;
			}
			
		});
	}
	
	
	
	
}
