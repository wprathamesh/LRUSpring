package com.prathamesh.assignment.espressif.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prathamesh.assignment.espressif.dao.LRUCacheDAO;

@RestController
public class LRUCacheController {

	@Autowired
	private LRUCacheDAO lRUCacheDAO;

	@RequestMapping("getitem")
	public String getCacheItem(@PathParam(value = "item") String item) {
		return lRUCacheDAO.getItem(item);
	}

	@RequestMapping(value = "additem")
	public boolean addItem(@PathParam(value = "item") String item) {
		lRUCacheDAO.addItem(item);
		return true;
	}
	
	@RequestMapping(value = "getallitems")
	public List<String> getAllItems() {
		return lRUCacheDAO.getAllItem();
	}

}
