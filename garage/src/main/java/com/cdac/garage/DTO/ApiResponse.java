package com.cdac.garage.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

	private LocalDateTime timeStamp;
	private String message;

	public ApiResponse(String message) {
		this.timeStamp = LocalDateTime.now();
		this.message = message;
	}
}
