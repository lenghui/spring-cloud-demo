package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Model class of user_login.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class UserLogin implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** user_no. */
	private Integer userNo;

	/** login_id. */
	private String loginId;

	/** mobile. */
	private String mobile;

	/** password. */
	private String password;

	/** email. */
	private String email;

	/** reg_time. */
	private Date regTime;

	/**
	 * Constructor.
	 */
	public UserLogin() {
	}

	/**
	 * Set the user_no.
	 * 
	 * @param userNo
	 *            user_no
	 */
	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	/**
	 * Get the user_no.
	 * 
	 * @return user_no
	 */
	public Integer getUserNo() {
		return this.userNo;
	}

	/**
	 * Set the login_id.
	 * 
	 * @param loginId
	 *            login_id
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * Get the login_id.
	 * 
	 * @return login_id
	 */
	public String getLoginId() {
		return this.loginId;
	}

	/**
	 * Set the mobile.
	 * 
	 * @param mobile
	 *            mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Get the mobile.
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * Set the password.
	 * 
	 * @param password
	 *            password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the password.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Set the email.
	 * 
	 * @param email
	 *            email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the email.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Set the reg_time.
	 * 
	 * @param regTime
	 *            reg_time
	 */
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	/**
	 * Get the reg_time.
	 * 
	 * @return reg_time
	 */
	public Date getRegTime() {
		return this.regTime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userNo == null) ? 0 : userNo.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UserLogin other = (UserLogin) obj;
		if (userNo == null) {
			if (other.userNo != null) {
				return false;
			}
		} else if (!userNo.equals(other.userNo)) {
			return false;
		}
		return true;
	}

}
