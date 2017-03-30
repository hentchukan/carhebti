package prv.carhebti.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the type database table.
 * 
 */
@Entity
@NamedQuery(name="Type.findAll", query="SELECT t FROM Type t")
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String name;

	private byte odometer;

	@Column(name="provider_column_name")
	private String providerColumnName;

	private byte qte;

	//bi-directional many-to-one association to Service
	@OneToMany(mappedBy="type", fetch=FetchType.LAZY)
	private List<Service> services;

	public Type() {}
	public Type(String name, String provider, Boolean odometer, Boolean quantity) {
		this.name = name;
		this.providerColumnName = provider;
		this.odometer = (odometer)?(byte)1:(byte)0;
		this.qte = (quantity)?(byte)1:(byte)0;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getOdometer() {
		return this.odometer;
	}

	public void setOdometer(byte odometer) {
		this.odometer = odometer;
	}

	public String getProviderColumnName() {
		return this.providerColumnName;
	}

	public void setProviderColumnName(String providerColumnName) {
		this.providerColumnName = providerColumnName;
	}

	public byte getQte() {
		return this.qte;
	}

	public void setQte(byte qte) {
		this.qte = qte;
	}

	public List<Service> getServices() {
		return this.services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Service addService(Service service) {
		getServices().add(service);
		service.setType(this);

		return service;
	}

	public Service removeService(Service service) {
		getServices().remove(service);
		service.setType(null);

		return service;
	}

}