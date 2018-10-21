package com.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.demo.entity.UserInfo;

/**
 * 
 * 创建数据库userinfo表的接口
 * @author Administrator
 *
 */
public interface DaoInterface extends CrudRepository<UserInfo, Long>{

}
