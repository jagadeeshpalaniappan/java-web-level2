package com.domain.contacts.vo;

public class BaseVO {

	/*Pagination Attributes*/
	private int pageNumber = 1;
	private int totalPgnRecords = 0;
	private int pageSize = 10;
	
	/* Required attribute in all VOs */
	private String lastUpdatedBy = null;


	/**
	 * @return totalPgnRecords
	 */
	public int getTotalPgnRecords() {
		return totalPgnRecords;
	}
	/**
	 * @param totalPgnRecords
	 */
	public void setTotalPgnRecords(int totalPgnRecords) {
		this.totalPgnRecords = totalPgnRecords;
	}


	/**
	 * @return pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}
	/**
	 * @param pageNumber
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	/**
	 * @return pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return lastUpdatedBy
	 */ 
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	/**
	 * @param lastUpdatedBy
	 */
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * @return minrecords
	 */
	public int getPgnMinRecords(){
		int minrecords = 0;
		minrecords = (pageNumber*pageSize-pageSize);

		return minrecords;
	}

	/**
	 * @return maxRecords
	 */ 
	public int getPgnMaxRecords(){
		int maxRecords = 0;
		maxRecords = pageNumber * pageSize;

		return maxRecords;
	}
}

