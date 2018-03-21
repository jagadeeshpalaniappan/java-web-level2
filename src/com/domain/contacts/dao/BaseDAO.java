package com.domain.contacts.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDAO {
	
	private static Logger logger = Logger.getLogger(BaseDAO.class);
	
	//y protected? //Ans: Child directly needs access
	protected JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public static void closeDBResources(ResultSet rset,Statement stmt, Connection conn) {
		try {
			if(rset!=null){
				rset.close();
			}
			if(stmt!=null){
				stmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	
	public static void commit(Connection conn) {

		try {
			if(conn!=null)
				conn.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
	}

	public static void rollBack(Connection conn) {
		try {
			if(conn!=null)
				conn.rollback();
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	

}
