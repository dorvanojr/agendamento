package br.com.agendamento.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author Felipe
 * 
 */
@Entity
@Table(name = "USER")
@NamedQueries({  
	@NamedQuery(name="User.findList", query="SELECT u FROM User u where u.username = :login")
 })
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "username", length = 40)
	private String username;
	@Column(name = "password", length = 40)
	private String password;
	@Column(name = "enable")
	private int enable;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Userauth> userauth;
	
	
	@ManyToMany
	@JoinTable(name = "USERAUTH", joinColumns = @JoinColumn(name = "USER_Username"), inverseJoinColumns = @JoinColumn(name = "AUTH_authority"))
	private List<Authority> authorities;


	@JoinColumn(name = "idCliente",  nullable = true)
    @ManyToOne
	private Cliente cliente;

	
	@JoinColumn(name = "idClinica", nullable = true)
    @ManyToOne
	private Clinica clinica;
	
	
	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Userauth> getUserauth() {
		return userauth;
	}

	public void setUserAuth(List<Userauth> userAuth) {
		this.userauth = userAuth;
	}
	
	
	

	public void setUserauth(List<Userauth> userauth) {
		this.userauth = userauth;
	}

	public void addUserAlth(Userauth a){
		if(this.userauth==null){
		     this.userauth = new ArrayList<Userauth>();
		}
		this.userauth.add(a);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((clinica == null) ? 0 : clinica.hashCode());
		result = prime * result + enable;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((userauth == null) ? 0 : userauth.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (clinica == null) {
			if (other.clinica != null)
				return false;
		} else if (!clinica.equals(other.clinica))
			return false;
		if (enable != other.enable)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userauth == null) {
			if (other.userauth != null)
				return false;
		} else if (!userauth.equals(other.userauth))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
}