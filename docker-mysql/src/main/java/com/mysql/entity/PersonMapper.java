package com.mysql.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PersonMapper implements RowMapper<Person>{

	@Override
	public Person mapRow(ResultSet resultSet, int i) throws SQLException {
		int id = resultSet.getInt("id");
		String name = resultSet.getString("name");
		Person person = new Person();
		person.setId(id);
		person.setName(name);
		return person;
	}

}
