package com.zy.myeyes.beans;

import javax.persistence.Id;

public class User {
	@Id
	private long uid;

	private String name;
	private int  age;


	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("uid:").append(getUid()).append(" age:").append(getAge()).append(" username:").append(getName());
		return sb.toString();
	}
}
