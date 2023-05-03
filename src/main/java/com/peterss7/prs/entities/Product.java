package com.peterss7.prs.entities;




import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Product")
@JsonPropertyOrder({"id", "partNumber", "name", "price", "unit", 
	"photoPath","vendorId"})
public class Product {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	@Column(name="PartNumber")	
	private String partNumber;
	@Column(name = "Name")
	private String name;
	@Column(name="Price")
	private double price;
	@Column(name="Unit")
	private String unit;
	@Column(name="PhotoPath")
	private String photoPath;
	
	
	@ManyToOne
	@JoinColumn(name = "vendorId")	
	private Vendor vendor;
	

	
	
	
		
	public int getId() {
		return id;
	}

	public Product() {
		super();
		
	}

	public Product(String partNumber, String name, double price, String unit, String photoPath, Vendor vendor, int id) {
		super();
		this.partNumber = partNumber;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.photoPath = photoPath;
		this.vendor = vendor;
		this.id = id;
		
		
	}

	@Override
	public String toString() {
		return "Product [partNumber=" + partNumber + ", name=" + name + ", price=" + price + ", unit=" + unit
				+ ", photoPath=" + photoPath + ", vendor=" + vendor + ", id=" + id + "]";
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getVendorId() {

		return vendor.getId();
	    
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;	
		
	}



}
