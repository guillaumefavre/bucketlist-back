package com.gfavre.bucketlistapp.item;

public enum ItemStatus {

	TODO("To Do"),
	DOING("Doing"),
	DONE("Done");
	
	private String label ="";
	
	ItemStatus(String label) {
		this.label = label;
	}
	
	public String toString() {
		return this.label;
	}
}
