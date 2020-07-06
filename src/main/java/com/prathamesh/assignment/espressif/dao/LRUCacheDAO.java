package com.prathamesh.assignment.espressif.dao;

import java.util.List;

public interface LRUCacheDAO {

	public String getItem(String item);
	public String addItem(String item);
	public List<String> getAllItem();
	
}
