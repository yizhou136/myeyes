package com.zy.myeyes.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.Id;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener{
	private final Log log = LogFactory.getLog(User.class);

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

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		log.info("valueBound event:"+event.getName());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		log.info("valueUnbound event:"+event.getName());
	}
}
