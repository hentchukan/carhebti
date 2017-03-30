package prv.carhebti.business.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the service database table.
 * 
 */
@Entity
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_service")
	private int idService;

	@Column(name="comment_service")
	private String commentService;

	@Temporal(TemporalType.DATE)
	@Column(name="date_service")
	private Date dateService;

	@Column(name="odometer_service")
	private BigDecimal odometerService;

	@Column(name="provider_service")
	private String providerService;

	@Column(name="qte_service")
	private BigDecimal qteService;

	//bi-directional many-to-one association to Type
	@ManyToOne
	@JoinColumn(name="type_service")
	private Type type;

	public Service() {
	}

	public int getIdService() {
		return this.idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public String getCommentService() {
		return this.commentService;
	}

	public void setCommentService(String commentService) {
		this.commentService = commentService;
	}

	public Date getDateService() {
		return this.dateService;
	}

	public void setDateService(Date dateService) {
		this.dateService = dateService;
	}

	public BigDecimal getOdometerService() {
		return this.odometerService;
	}

	public void setOdometerService(BigDecimal odometerService) {
		this.odometerService = odometerService;
	}

	public String getProviderService() {
		return this.providerService;
	}

	public void setProviderService(String providerService) {
		this.providerService = providerService;
	}

	public BigDecimal getQteService() {
		return this.qteService;
	}

	public void setQteService(BigDecimal qteService) {
		this.qteService = qteService;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}