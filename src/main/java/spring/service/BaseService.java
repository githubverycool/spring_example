package spring.service;

import java.io.Serializable;


public interface BaseService<T extends Serializable> {

	
	public void save(T entry);
	
}
