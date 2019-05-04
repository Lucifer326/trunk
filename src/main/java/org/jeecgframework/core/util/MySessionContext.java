package org.jeecgframework.core.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class MySessionContext {
	
	private static MySessionContext instance;
	private Map<String,HttpSession> myMap;
	
	private MySessionContext(){
		myMap = new HashMap<String,HttpSession>();
	}
	
	public static MySessionContext getInstance(){
		if(instance==null){
			instance = new MySessionContext();
		}
		return instance;
	}
	
	public synchronized void addSession(HttpSession session){
		if(session!=null){
			myMap.put(session.getId(), session);
		}
	}
	
	public synchronized void delSession(HttpSession session){
		if(session!=null){
			myMap.remove(session.getId());
		}
	}
	
	public synchronized HttpSession getSession(String sessionId){
		if(sessionId==null||sessionId.trim().equals("")){
			return null;
		}
		return (HttpSession)myMap.get(sessionId);
	}

}
