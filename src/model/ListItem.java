package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * The List Item class contains the model/entity/POJO
 * This file contains the annotations that tell JPA how this entity needs to interact/communicate between Java and SQL
 * It also contains methods that allow for us to interact with the instance objects that are created on our end.
 */


@Entity // Any class that must be persisted in a DB must be annotated as an entity
@Table(name="tatesmusicstore") 
public class ListItem {
	// These annotations 
	@Id // Id annotation marks a field as primary key in the DB
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="instrumentClass")
	private String instrumentClass;
	@Column(name="INSTRUMENT")
	private String instrument;
	
	public ListItem() {
		super();
	}
	
	public ListItem(String instrumentClass, String instrument){
		super();
		this.instrumentClass = instrumentClass;
		this.instrument = instrument;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInstrumentClass() {
		return instrumentClass;
	}

	public void setInstrumentClass(String instrumentClass) {
		this.instrumentClass = instrumentClass;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	
	// Print method
	public String returnItemDetails() {
		return this.instrumentClass + ": " + this.instrument;
	}

}
