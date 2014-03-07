package com.commodities.common;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.codehaus.jackson.annotate.JsonTypeInfo;

@PersistenceCapable(detachable = "true", table = "commodityData")
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public class CommodityData implements Serializable {

	/**
	 * 
	 */
	@NotPersistent
	private static final long serialVersionUID = 1L;



	@Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.INCREMENT)
	private long id;
	
	@Column(name="name", jdbcType="VARCHAR", length=100)
	@Persistent
	private String name;
	
	@Column(name="date", jdbcType="VARCHAR", length=20)
	@Persistent
	private Date date;
	
	@Column(name="price", jdbcType="VARCHAR", length=20)
	@Persistent
	private String price;
	
	@Column(name="type", jdbcType="VARCHAR", length=20)
	@Persistent
	private String type;





	public CommodityData() {
		super();
		// TODO Auto-generated constructor stub
	}





	public CommodityData(long id, String name, Date date, String price,
			String type) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.price = price;
		this.type = type;
	}





	@Override
	public String toString() {
		return "CommodityData [id=" + id + ", name=" + name + ", date=" + date
				+ ", price=" + price + ", type=" + type + "]";
	}





	public Date getDate() {
		return date;
	}





	public void setDate(Date date) {
		this.date = date;
	}





	public long getId() {
		return id;
	}

	public void setId(long iD) {
		this.id = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public static Date getCurrentDate()  {
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YY");
		//Date date = new Date();
		String time = sdf.format(new Date());
		Date date;
		try {
			date = sdf.parse(time);
			return date;
		} catch (ParseException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ; 
		}
		
	
	}

}
