package com.onegirloneguy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="image_type")
public class ImageType {

	private Integer imageTypeId;
	private String imagedesc;
	private Integer sortOrder;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name="increment", strategy = "increment")
	public Integer getImageTypeId() {
		return imageTypeId;
	}
	public void setImageTypeId(final Integer imageTypeId) {
		this.imageTypeId = imageTypeId;
	}
	public String getImagedesc() {
		return imagedesc;
	}
	public void setImagedesc(final String imagedesc) {
		this.imagedesc = imagedesc;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(final Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	
	
}
