package com.hms.common.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String keycloakId;

	@Column(nullable = false)
	private String firstName;

	@Column
	private String middleName;

	@Column
	private String lastName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column
	private String phone;

	@Column
	private String address;

	@ManyToMany
	@JoinTable(
		name = "user_hospital",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "hospital_id")
	)
	private Set<HospitalModel> hospitals = new HashSet<>();
}
