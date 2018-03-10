package prv.carhebti.business.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the type database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Type.findAll", query="SELECT t FROM Type t"),
	@NamedQuery(name="Type.findByUser", query="SELECT t FROM Type t where t.owner = :owner")
})
public class Type implements Serializable, ICarhebtiEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private byte odometer;

	@Column(name="provider_column_name")
	private String providerColumnName;

	private byte qte;
	
	@ManyToOne
	@JoinColumn(name="owner")
	private User owner;
	
	//bi-directional many-to-one association to Service
	@JsonIgnore
	@OneToMany(mappedBy="type", fetch=FetchType.LAZY)
	private List<Service> services;

	public Type() {}
	public Type(String name, String provider, Boolean odometer, Boolean quantity, User owner) {
		this.name = name;
		this.providerColumnName = provider;
		this.odometer = (odometer)?(byte)1:(byte)0;
		this.qte = (quantity)?(byte)1:(byte)0;
		this.owner = owner;
	}
	
	public Type(Integer id, String name, String provider, Boolean odometer, Boolean quantity, User owner) {
		this.id = id;
		this.name = name;
		this.providerColumnName = provider;
		this.odometer = (odometer)?(byte)1:(byte)0;
		this.qte = (quantity)?(byte)1:(byte)0;
		this.owner = owner;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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
	
	public boolean isQuantifiable() {
		return this.qte == 1;
	}
	
	public boolean isOdometerable() {
		return this.odometer == 1;
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

	public String toString() {
		return this.name;
	}
}