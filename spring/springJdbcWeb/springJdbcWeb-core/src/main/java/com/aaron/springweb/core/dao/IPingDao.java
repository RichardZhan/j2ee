package com.aaron.springweb.core.dao;

import com.aaron.springweb.bean.Page;
import com.aaron.springweb.bean.Ping;

import java.util.List;

public interface IPingDao {

	public void insert(String tag);

	public List<Ping> findAllPings();

	public Page<Ping> getAllPingByPage(Page<Ping> page);
	
}
