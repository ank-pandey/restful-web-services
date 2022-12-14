package com.abcdeveloper.rest.webservices.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
	
	private LocalDateTime timestamp;
	private String message;
	private String details;

}
