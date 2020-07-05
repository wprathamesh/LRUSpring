package com.prathamesh.assignment.espressif.dao.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.prathamesh.assignment.espressif.dao.LRUCacheDAO;

public class InMemoryCacheDAOImpl implements LRUCacheDAO {

	private static Map<String, String> inMemoryCacheOrder;
	private static Logger logger = LogManager.getLogger(InMemoryCacheDAOImpl.class);
	private static List<String> inMemoryData;

	int MAX_MEMORY_SIZE = 2;

	public InMemoryCacheDAOImpl() {
		super();
		initInMemCache();
	}

//	@Bean
//	public Map<String, Integer> getInMemoryCacheOrder() {
//		return new HashMap<String, Integer>();
//	}
//	
//	@Bean
//	public List<String> getInMemoryData() {
//		return new LinkedList<String>();
//	}

	public void initInMemCache() {
		inMemoryCacheOrder = new HashMap<String, String>();
		inMemoryData = new LinkedList<String>();
	}

	@Override
	public String getItem(String item) {
		if (inMemoryCacheOrder.containsKey(item)) {
			// Remove the occurance of the element from list and add the element to head of
			// list as its accessed recently.
			inMemoryData.remove(item);
			inMemoryData.add(0, item);
			System.out.println("Last element accessed " + item);
			return inMemoryCacheOrder.get(item);
		} else {
			return "Item not present in list";
		}

	}

	@Override
	public boolean addItem(String item) {
		logger.info("Entering synchronized block");
		synchronized (this) {

			if (!inMemoryData.contains(item)) {
				if (inMemoryData.size() == MAX_MEMORY_SIZE) {
					if (removeLeastUsedElement()) {
						inMemoryData.add(0, item);
						inMemoryCacheOrder.put(item, item);
						logger.info("Element added " + item);
						return true;
					}

				} else {
					// If the item is inserted the first time set the used counter to 1
					inMemoryData.add(0, item);
					inMemoryCacheOrder.put(item, item);
					logger.info("Element added " + item);
					return true;
				}
			}

		}
		logger.info("Exited synchronized block");
		return false;

	}

	private synchronized boolean removeLeastUsedElement() {
		int indexToRemove = inMemoryData.size();
		inMemoryCacheOrder.remove((inMemoryData.get(indexToRemove - 1)));
		String eleRemoved = inMemoryData.remove(indexToRemove - 1);

		logger.info("Element removed from list " + eleRemoved);
		if (!eleRemoved.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public List<String> getAllItem() {
		return inMemoryData;
	}

}
