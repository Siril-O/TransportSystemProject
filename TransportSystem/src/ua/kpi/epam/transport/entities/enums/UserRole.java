package ua.kpi.epam.transport.entities.enums;

/**
 *
 * @author KIRIL
 */
public enum UserRole {

    /**
     *
     */
    USER("user", 1),

    /**
     *
     */
    ADMIN("admin", 2);

	private String role;
	private int index;

	UserRole(String role, int index) {
		this.role = role;
		this.index = index;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

}
