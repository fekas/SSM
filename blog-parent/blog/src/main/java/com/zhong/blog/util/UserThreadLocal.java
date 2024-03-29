package com.zhong.blog.util;

import com.zhong.blog.dao.pojo.SysUser;

public class UserThreadLocal {
	
	private UserThreadLocal() {};
	
	private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();
	
	public static void put(SysUser sysUser) {
		LOCAL.set(sysUser);
	}
	
	public static SysUser get() {
		return LOCAL.get();
	}

	public static void remove() {
		LOCAL.remove();
	}

}
