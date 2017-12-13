package com.lzt.dao;

import java.io.Serializable;

public interface BaseDao<T, PK extends Serializable> {
	
	T save(T var1);

    T merge(T var1);

    T update(T var1);

    T get(PK var1);
    
    void remove(PK var1);

    void remove(T var1);

    void evict(T var1);

}
