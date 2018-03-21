package com.domain.contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.domain.contacts.constants.ContactsConstants;
import com.domain.contacts.exception.ContactsDAOException;
import com.domain.contacts.vo.ContactVO;
import com.domain.contacts.vo.GenericMessageVO;

public class ContactsDAOImpl extends BaseDAO implements IContactsDAO {
	/*
	private DataSource dataSource = null;
	public GenericMessageVO setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	*/
	
	
	private static Logger logger = Logger.getLogger(ContactsDAOImpl.class);
	

	@Override
	public List<ContactVO> getAllContacts() throws ContactsDAOException {
		List<ContactVO> contactVOs = null;
		Connection connection = null;
		try {
			
			connection= jdbcTemplate.getDataSource().getConnection();
			contactVOs = getAllContacts(connection);
			
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			throw new ContactsDAOException(e.getMessage(),e);
			
		}finally{
			closeDBResources(null, null, connection);
		}
		
		
		return contactVOs;
	}

	@Override
	public List<ContactVO> getAllContacts(Connection connection)  throws ContactsDAOException {
		List<ContactVO> contactVOs = new ArrayList<ContactVO>();
		ContactVO contactVO = null;
		
		String sqlQuery = "SELECT CONTACT_ID, CONTACT_NAME, CONTACT_MOBILE, CONTACT_LANDLINE FROM CONTACTS";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = connection.prepareStatement(sqlQuery);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				contactVO = new ContactVO();
				contactVO.setContactId(resultSet.getLong(1));
				contactVO.setContactName(resultSet.getString(2));
				contactVO.setContactMobile(resultSet.getString(3));
				contactVO.setContactLandline(resultSet.getString(4));
				
				contactVOs.add(contactVO);
			}
			
			
			
		}catch(SQLException e){
			logger.error(e.getMessage(),e);
			throw new ContactsDAOException(e.getMessage(),e);
		}finally{
			closeDBResources(resultSet, preparedStatement, null);
		}
		
		
		return contactVOs;
	}

	

	@Override
	public GenericMessageVO saveContact(ContactVO contactVO) throws ContactsDAOException {
		GenericMessageVO genericMessageVO = null;
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			
			
			genericMessageVO = saveContact(contactVO,connection);

			//Transactions Maintenance
			//If external system also saved success -do a commit in our app 
			boolean externalSystemStatus = true; //Assume true
			
			if(externalSystemStatus){
				commit(connection);
			}else{
				rollBack(connection);
			}
			
			
		} catch(SQLException e){
			rollBack(connection);
			logger.error(e.getMessage(),e);
			throw new ContactsDAOException(e.getMessage(),e);
		}catch(ContactsDAOException e){
			rollBack(connection);
			logger.error(e.getMessage(),e);
			throw e;
		}
		finally{
			commit(connection);
			closeDBResources(null, null, connection);
		}


		return genericMessageVO;		
	}

	public GenericMessageVO saveContact(ContactVO contactVO, Connection connection)  throws ContactsDAOException{
		GenericMessageVO genericMessageVO = new GenericMessageVO();
		PreparedStatement preparedStatement = null;
		//String sqlQuery = "INSERT INTO CONTACTS(CONTACT_NAME, CONTACT_MOBILE, CONTACT_LANDLINE) VALUES(?,?,?);";
		
		String sqlQuery = "INSERT INTO " +
							" CONTACTS (CONTACT_ID, CONTACT_NAME, CONTACT_MOBILE, CONTACT_LANDLINE) " +
							" VALUES  (?, ?, ?, ?) " +
							" ON DUPLICATE KEY " +
							" UPDATE CONTACT_NAME = VALUES(CONTACT_NAME), CONTACT_MOBILE = VALUES(CONTACT_MOBILE), CONTACT_LANDLINE = VALUES(CONTACT_LANDLINE)";
		
		try {
			preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, contactVO.getContactId()==null? 0 : contactVO.getContactId());
			preparedStatement.setString(2, contactVO.getContactName());
			preparedStatement.setString(3, contactVO.getContactMobile());
			preparedStatement.setString(4, contactVO.getContactLandline());
			
			int rowsaffected = preparedStatement.executeUpdate();
			if(rowsaffected>0){
				//Success
				genericMessageVO.setCode(ContactsConstants.SUCCESS);
				
				//Getting the last Sequence Id
				ResultSet rs = preparedStatement.getGeneratedKeys();
			    rs.next();
			    contactVO.setContactId(rs.getLong(1));
				
			}else{
				genericMessageVO.setCode(ContactsConstants.FAILURE);
			}
			
			
		} catch (SQLException e) {
			genericMessageVO.setCode(ContactsConstants.TECHNICAL_FAILURE);
			logger.error(e.getMessage(), e);
			throw new ContactsDAOException(e.getMessage(), e);
		} finally{
			closeDBResources(null, preparedStatement, null);
		}
		
		return genericMessageVO;
	}
	
	

	@Override
	public ContactVO getContact(ContactVO contactVO) throws ContactsDAOException {
		Connection connection = null;
		try {
			
			connection= jdbcTemplate.getDataSource().getConnection();
			contactVO = getContact(contactVO, connection);
			
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			throw new ContactsDAOException(e.getMessage(),e);
			
		}finally{
			closeDBResources(null, null, connection);
		}
		
		
		return contactVO;
	}

	@Override
	public ContactVO getContact(ContactVO contactVO, Connection connection) throws ContactsDAOException {
		
		String sqlQuery = "SELECT CONTACT_ID, CONTACT_NAME, CONTACT_MOBILE, CONTACT_LANDLINE FROM CONTACTS WHERE CONTACT_ID=?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, contactVO.getContactId());
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				contactVO.setContactId(resultSet.getLong(1));
				contactVO.setContactName(resultSet.getString(2));
				contactVO.setContactMobile(resultSet.getString(3));
				contactVO.setContactLandline(resultSet.getString(4));
			}
			
			
			
		}catch(SQLException e){
			logger.error(e.getMessage(),e);
			throw new ContactsDAOException(e.getMessage(),e);
		}finally{
			closeDBResources(resultSet, preparedStatement, null);
		}
		return contactVO;
	}

	@Override
	public GenericMessageVO deleteContact(ContactVO contactVO) throws ContactsDAOException {
		
		GenericMessageVO genericMessageVO = null;
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			
			
			genericMessageVO = deleteContact(contactVO,connection);

			//Transactions Maintenance
			//If external system also saved success -do a commit in our app 
			boolean externalSystemStatus = true; //Assume true
			
			if(externalSystemStatus){
				commit(connection);
			}else{
				rollBack(connection);
			}
			
			
		} catch(SQLException e){
			rollBack(connection);
			logger.error(e.getMessage(),e);
			throw new ContactsDAOException(e.getMessage(),e);
		}catch(ContactsDAOException e){
			rollBack(connection);
			logger.error(e.getMessage(),e);
			throw e;
		}
		finally{
			commit(connection);
			closeDBResources(null, null, connection);
		}


		return genericMessageVO;	
	}

	@Override
	public GenericMessageVO deleteContact(ContactVO contactVO, Connection connection) throws ContactsDAOException {
		GenericMessageVO genericMessageVO = new GenericMessageVO();
		PreparedStatement preparedStatement = null;
		String sqlQuery = "DELETE FROM CONTACTS WHERE CONTACT_ID=?";
		
		try {
			preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, contactVO.getContactId());
			
			int rowsaffected = preparedStatement.executeUpdate();
			if(rowsaffected>1){
				//Success
				genericMessageVO.setCode(ContactsConstants.SUCCESS);
			}else{
				genericMessageVO.setCode(ContactsConstants.FAILURE);
			}
			
			
		} catch (SQLException e) {
			genericMessageVO.setCode(ContactsConstants.TECHNICAL_FAILURE);
			logger.error(e.getMessage(), e);
			throw new ContactsDAOException(e.getMessage(), e);
		} finally{
			closeDBResources(null, preparedStatement, null);
		}
		
		return genericMessageVO;
	}
	
	
	
	

}
