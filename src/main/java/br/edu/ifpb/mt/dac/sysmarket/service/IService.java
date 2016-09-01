package br.edu.ifpb.mt.dac.sysmarket.service;

import java.io.Serializable;
import java.util.List;

public interface IService<T> extends Serializable {

	void save(T t);
	
	T update(T t);
	
	void remove(T t);
	
	T getById(Long id);
	
	List<T> getAll();
}
