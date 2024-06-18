package com.cdac.garage.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cdac.garage.entities.Company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VehicleDto {
	
	@NotBlank
	private String vName;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Company company;
	
	@NotBlank
	private String vNumber;
	
	@NotBlank
	private String vType;
	
	@NotBlank
	@NotNull
	private Long uId;
}
