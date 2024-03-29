package com.lms.library.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lms.library.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * @author Ba'Hith BENON
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="users", 
	uniqueConstraints = {
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "username")
	}
)
public class ModelUser extends Object implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private boolean penalty;
    
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private UserRole role;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ModelToken> tokens;
    
    // Ajout de la relation inverse
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private ModelProfile profile;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ModelLoan> loans;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ModelReservation> reservations;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ModelNotification> notifications;

	public ModelUser(Long id, String username, String password, String email, boolean penalty, UserRole role,
			List<ModelToken> tokens, ModelProfile profile, List<ModelLoan> loans, List<ModelReservation> reservations,
			List<ModelNotification> notifications) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.penalty = penalty;
		this.role = role;
		this.tokens = tokens;
		this.profile = profile;
		this.loans = loans;
		this.reservations = reservations;
		this.notifications = notifications;
	}

	public ModelUser(Long id, String username, String password, String email, UserRole role, ModelProfile profile,
			List<ModelLoan> loans, List<ModelReservation> reservations, List<ModelNotification> notifications) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.profile = profile;
		this.loans = loans;
		this.reservations = reservations;
		this.notifications = notifications;
	}

	public ModelUser(Long id, String username, String password, String email, UserRole role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
	}
	
	public ModelUser(Long id, String username, String password, UserRole role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public ModelUser() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the role
	 */
	public UserRole getRole() {
		return role;
	}
	
	/**
	 * @return the role
	 */
	public String getRoleToString() {
		return role.toString();
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}

	/**
	 * @return the penalty
	 */
	public boolean isPenalty() {
		return penalty;
	}

	/**
	 * @param penalty the penalty to set
	 */
	public void setPenalty(boolean penalty) {
		this.penalty = penalty;
	}

	/**
	 * @return the tokens
	 */
	public List<ModelToken> getTokens() {
		return tokens;
	}

	/**
	 * @param tokens the tokens to set
	 */
	public void setTokens(List<ModelToken> tokens) {
		this.tokens = tokens;
	}

	/**
	 * @return the profile
	 */
	public ModelProfile getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(ModelProfile profile) {
		this.profile = profile;
	}

	/**
	 * @return the loans
	 */
	public List<ModelLoan> getLoans() {
		return loans;
	}

	/**
	 * @param loans the loans to set
	 */
	public void setLoans(List<ModelLoan> loans) {
		this.loans = loans;
	}
	
	/**
	 * @param loan the loans to add
	 */
	public void addLoan(ModelLoan loan) {
		if(loans == null) {
			loans = new ArrayList<ModelLoan>();
		}
		
		loans.add(loan);
	}

	/**
	 * @return the reservations
	 */
	public List<ModelReservation> getReservations() {
		return reservations;
	}

	/**
	 * @param reservations the reservations to set
	 */
	public void setReservations(List<ModelReservation> reservations) {
		this.reservations = reservations;
	}
	
	/**
	 * @param reservation the reservations to add
	 */
	public void addReservation(ModelReservation reservation) {
		if(reservations == null) {
			reservations = new ArrayList<ModelReservation>();
		}
		
		reservations.add(reservation);
	}

	/**
	 * @return the notifications
	 */
	public List<ModelNotification> getNotifications() {
		return notifications;
	}

	/**
	 * @param notifications the notifications to set
	 */
	public void setNotifications(List<ModelNotification> notifications) {
		this.notifications = notifications;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ModelUser)) {
			return false;
		}
		ModelUser other = (ModelUser) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password) && role == other.role
				&& Objects.equals(username, other.username);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
    
}
