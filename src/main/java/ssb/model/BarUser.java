package ssb.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ssb.util.DateJsonSerializer;

public class BarUser implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String msisdn;
	private Date modifyDate;
	
	public BarUser() {
		super();
	}
	
	public BarUser(String id, String msisdn, Date modifyDate) {
		super();
		this.id = id;
		this.msisdn = msisdn;
		this.modifyDate = modifyDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
	@JsonSerialize(using = DateJsonSerializer.class)
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	@Override
	public String toString() {
		return "BarUser [id=" + id + ", msisdn=" + msisdn + ", modifyDate=" + modifyDate + "]";
	}


	
}
