package ua.kpi.epam.transport.entities;

import ua.kpi.epam.transport.entities.enums.UserRole;

/**
 *
 * @author KIRIL
 */
public class User {

	private Integer id;
	private String login;
	private String name;
	private String surname;
	private Integer passwordHash;
	private UserRole role;

    /**
     *
     * @param login
     * @param name
     * @param surname
     * @param password
     * @param role
     */
    public User(String login, String name, String surname, String password,
			UserRole role) {
		super();
		this.login = login;
		this.name = name;
		this.surname = surname;
		this.passwordHash = calcPasswordHash(password);
		this.role = role;
	}

    /**
     *
     * @param id
     * @param login
     * @param name
     * @param surname
     * @param passwordHash
     * @param role
     */
    public User(Integer id, String login, String name, String surname,
			Integer passwordHash, UserRole role) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.surname = surname;
		this.passwordHash = passwordHash;
		this.role = role;
	}

    /**
     *
     * @param id
     * @param login
     * @param name
     * @param surname
     * @param password
     * @param role
     */
    public User(Integer id, String login, String name, String surname,
			String password, UserRole role) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.surname = surname;
		this.passwordHash = calcPasswordHash(password);
		this.role = role;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the passwordHash
	 */
	public Integer getPasswordHash() {
		return passwordHash;
	}

	/**
	 * @param passwordHash
	 *            the passwordHash to set
	 */
	public void setPasswordHash(Integer passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * @return the role
	 */
	public UserRole getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}

    /**
     *
     * @param password
     * @return
     */
    public static Integer calcPasswordHash(String password) {
		Integer seed = 131;
		Integer hash = 12;
		hash = seed * hash + password.hashCode();
		return hash;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((passwordHash == null) ? 0 : passwordHash.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passwordHash == null) {
			if (other.passwordHash != null)
				return false;
		} else if (!passwordHash.equals(other.passwordHash))
			return false;
		if (role != other.role)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", name=" + name
				+ ", surname=" + surname + ", role=" + role + "]";
	}

}
