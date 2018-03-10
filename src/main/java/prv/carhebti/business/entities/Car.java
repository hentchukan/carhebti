package prv.carhebti.business.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Car
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Car.findAll", query="SELECT c FROM Car c"),
	@NamedQuery(name="Car.findByUser", query="SELECT c FROM Car c where c.owner = :owner")
})
public class Car implements Serializable, ICarhebtiEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	@Column(name="manifacturer", nullable=false)
	private String manifacturer;
	@Column(name="trade", nullable=false)
	private String trade;
	@Column(name="number", nullable=false)
	private String number;
	@Column(name="grey_card", nullable=false)
	private String greyCard;
	
	@ManyToOne
	@JoinColumn(name="owner")
	private User owner;
	
	public Car() {
		super();
	}

	public Car(Integer id, String manifacturer, String trade, String number, String greyCard, User owner) {
		this.id = id;
		this.manifacturer = manifacturer;
		this.trade = trade;
		this.number = number;
		this.greyCard = greyCard;
		this.owner = owner;
	}
	
	public Car(String manifacturer, String trade, String number, String greyCard, User owner) {
		this.manifacturer = manifacturer;
		this.trade = trade;
		this.number = number;
		this.greyCard = greyCard;
		this.owner = owner;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManifacturer() {
		return manifacturer;
	}

	public void setManifacturer(String manifacturer) {
		this.manifacturer = manifacturer;
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getGreyCard() {
		return greyCard;
	}

	public void setGreyCard(String greyCard) {
		this.greyCard = greyCard;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
   
}
