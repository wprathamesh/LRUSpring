package com.prathamesh.assignment.espressif.model;

public class LRUItemModel {

	String item;
	int accessedTimes;

	public LRUItemModel() {
		super();
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getAccessedTimes() {
		return accessedTimes;
	}

	public void setAccessedTimes(int accessedTimes) {
		this.accessedTimes = accessedTimes;
	}

}
