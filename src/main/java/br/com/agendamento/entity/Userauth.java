package br.com.agendamento.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="userauth")
public class Userauth implements Serializable {

  
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_Username", referencedColumnName = "username", nullable = true)
	private User user;
	
	@Column(name = "AUTH_authority", nullable = true)
	private String nameAuthority;
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNameAuthority() {
		return nameAuthority;
	}

	public void setNameAuthority(String nameAuthority) {
		this.nameAuthority = nameAuthority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((nameAuthority == null) ? 0 : nameAuthority.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Userauth other = (Userauth) obj;
		if (id != other.id)
			return false;
		if (nameAuthority == null) {
			if (other.nameAuthority != null)
				return false;
		} else if (!nameAuthority.equals(other.nameAuthority))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}


	
	
	
	
	


	
	
}
