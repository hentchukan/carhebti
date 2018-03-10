package prv.carhebti.business.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Settings database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Settings.findAll", query="SELECT s FROM Settings s"),
	@NamedQuery(name="Settings.findByUser", query="SELECT s FROM Settings s where s.user = :user")
})
public class Settings implements Serializable, ICarhebtiEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	@OneToOne
	@JoinColumn(name="car", nullable=true, unique=true)
	private Car car;

	@Column(name="language")
	private String language;

	@OneToOne
	@JoinColumn(name="user", nullable=false, unique=true)
	private User user;

	public Settings() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}