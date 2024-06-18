package com.cdac.garage.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private Long uId;
	
	@NotBlank
	@Column(name = "u_name",  nullable = false)
	private String uName;
	
	@NotBlank
	@Column(name = "email" , nullable = false)
	private String email;
	
	@Size(min = 6)
	@NotBlank
	@Column(name = "password" , nullable = false)
	private String password;
	
	@NotBlank
	@Column(name = "city" , nullable = false)
	private String city;
	
	@Size(min = 10)
	@NotBlank
	@Column(name = "contact_no" , nullable = false)
	private Long contactNo;
	
}
