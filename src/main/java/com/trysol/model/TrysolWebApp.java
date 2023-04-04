package com.trysol.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Trysol_services")
public class TrysolWebApp {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String email;
	private String subject;
	private String message;
	
	

}
