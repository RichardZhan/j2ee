package com.aaron.springbootApp.bean;

import java.io.Serializable;

/**
 * @author aaron.qiu
 * @date 2016-11-12 22:27:57
 */
public class User implements Serializable{
    
	private static final long serialVersionUID = -315483573915817347L;

	public User() {
		System.err.println("-----init---- user");
	}
	
	/**
     * 
     */
    private String id;
    
    
    private String username;

    private String password;

    /**
     * 
     */
    private Integer age;
    
    public String getId()
    {
        return id;
    }
        
    public void setId(String id)
    {
        this.id = id;
    }
        
    public String getUsername()
    {
        return username;
    }
        
    public void setUsername(String username)
    {
        this.username = username;
    }
        
    public Integer getAge()
    {
        return age;
    }
        
    public void setAge(Integer age)
    {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
