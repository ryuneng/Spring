package com.example.member; // 20240326 Day25

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rest_members")
@SequenceGenerator(
		name = "member_pk_genereator",
		sequenceName = "rest_members_seq",
		initialValue = 1,
		allocationSize = 1
		)
@Getter
@Setter
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_pk_genereator")
	private Long id;
	
	private String email;
	private String password;
	private String name;
	private String tel;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
}
