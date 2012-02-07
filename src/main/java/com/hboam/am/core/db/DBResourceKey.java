package com.hboam.am.core.db;

/**
 * 用于存储数据库访问基本信息的对象<p>
 * 通过配置文件配置的
 * @author Eric
 *
 */
public class DBResourceKey {
	public DBResourceKey(String name, String url, String pwd, String user) {
		super();
		this.name = name;
		this.url = url;
		this.pwd = pwd;
		this.user = user;
	}
	private String name;
	private String url;
	private String pwd;
	private String user;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
