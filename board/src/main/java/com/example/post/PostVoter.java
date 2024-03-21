package com.example.post; // 20240321 Day22

import com.example.user.User;
import com.exaple.common.BaseDateTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "board_post_voters")
@SequenceGenerator(
	name = "voter_pk_generator",
	sequenceName = "board_voters_seq",
	initialValue = 1000,
	allocationSize = 1
)
@Getter
@Setter
public class PostVoter extends BaseDateTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voter_pk_generator")
	private Long id;
	
	@ManyToOne // Many가 PostVoter, One이 Post
	@JoinColumn(name = "post_id")
	private Post post;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
