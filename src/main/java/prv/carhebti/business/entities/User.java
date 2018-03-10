package prv.carhebti.business.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name="User")
@NamedQueries({
	@NamedQuery(name="User.findByUsername", query="Select u From User u where u.username = :username")
})
public class User implements Serializable, ICarhebtiEntity {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	@Column(name="username", nullable = false, unique=true, length=20)
	private String username;
	@Column(name="fullname", length=50)
	private String fullname;
	@Column(name="password")
	private String password;
	
	public User() {
		super();
	}
	
	public User(Integer id, String username, String fullname, String password) {
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
