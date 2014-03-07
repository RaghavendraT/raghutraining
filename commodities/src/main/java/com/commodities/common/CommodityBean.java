package com.commodities.common;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.commodities.client.CommodityClient;

@ManagedBean
@SessionScoped
public class CommodityBean implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	 private  String types[] = {"General", "Electronics", "Food Items"};
	//private Map<String, String> types = new HashMap<String, String>();
	 private  List<SelectItem> items= new ArrayList<SelectItem>();
	private boolean state = false;

	private long id;

	private String name;

	private String price;

	private String type;
	
	private Date date;  

	private List<CommodityData> listCommodity;
	private List<CommodityData> listOfExpFood;
	public CommodityBean() {
		super();
		items.add( new SelectItem("Electronics"));
		items.add(new SelectItem("Food Items") );
		// TODO Auto-generated constructor stub
	}
	
	

	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	

	

	public  List<SelectItem> getItems() {
		return items;
	}

	public  void setItems(List<SelectItem> items) {
		this.items = items;
	}

	public List<CommodityData> getListCommodity() {
		return listCommodity;
	}

	public void setListCommodity(List<CommodityData> listCommodity) {
		this.listCommodity = listCommodity;
	}
	
	

	public List<CommodityData> getListOfExpFood() {
		return listOfExpFood;
	}



	public void setListOfExpFood(List<CommodityData> listOfExpFood) {
		this.listOfExpFood = listOfExpFood;
	}



	@Override
	public String toString() {
		return "CommodityBean [types=" + Arrays.toString(types) + ", items="
				+ items + ", state=" + state + ", id=" + id + ", name=" + name
				+ ", price=" + price + ", type=" + type + ", date=" + date
				+ ", listCommodity=" + listCommodity + ", listOfExpFood="
				+ listOfExpFood + "]";
	}



	public CommodityBean(String[] types, List<SelectItem> items, boolean state,
			long id, String name, String price, String type, Date date,
			List<CommodityData> listCommodity, List<CommodityData> listOfExpFood) {
		super();
		this.types = types;
		this.items = items;
		this.state = state;
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.date = date;
		this.listCommodity = listCommodity;
		this.listOfExpFood = listOfExpFood;
	}



	public CommodityBean(long iD, String name, String price, String type,
			List<CommodityData> listCommodity) {
		super();
		this.id = iD;
		this.name = name;
		this.price = price;
		this.type = type;
		this.listCommodity = listCommodity;
	}

	public CommodityBean(long iD, String name, String price, String type) {
		super();
		this.id = iD;
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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


	public void addCommodity() {
		CommodityData cmd = new CommodityData(getId(), getName(), getDate(),
				getPrice(), getType());

		 CommodityClient.addEntryUI(cmd);
	}

	public void editCommodity() throws IOException {
		CommodityData cmd = new CommodityData(getId(), getName(), getDate(),
				getPrice(), getType());

		 CommodityClient.updateEntryUI(getId(), cmd);
	}

	public void deleteCommodity() throws IOException {
		CommodityClient.deleteEntryUI(getId());
	}

	public void getbyIdCommodity() throws IOException {
		CommodityData cmd = CommodityClient.viewEntryUI(getId());

		setName(cmd.getName());
		setPrice(cmd.getPrice());
		setType(cmd.getType());
		setDate(cmd.getDate());
	}

	public void getbyNameCommodity() throws IOException {
		CommodityData cmd = CommodityClient.viewEntryUI(getName());
		setId(cmd.getId());
		setPrice(cmd.getPrice());
		setType(cmd.getType());
		setDate(cmd.getDate());
	}
	
	public void  fetchCommodity() throws IOException 
	{
	    
		   CommodityData commodityData =   CommodityClient.viewEntryUI(getId()) ;
		   setName(commodityData.getName());
		   setPrice(commodityData.getPrice());
		   setType(commodityData.getType());
		   setDate(commodityData.getDate());
	}
	
	public void listOfCommodity() {
		listCommodity = CommodityClient.listOfAllCommodityDataUI();

	}
	
	public void listOfExpCommodity() {
		listOfExpFood = CommodityClient.listOfExpCommodityDataUI();

	}
	
	

	public void listExpItmCommodity() {

	}

	public void status() {
		if (type.equals("Food Items")) {
			state = true;
		} else
			state = false;

	}
}
