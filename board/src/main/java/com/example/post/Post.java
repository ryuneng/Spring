package com.example.post; // 20240319 Day20

import java.util.List;
import java.util.Set;

import com.example.common.BaseDateTimeEntity;
import com.example.reply.Reply;
import com.example.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*
    @ManyToOne
    	- N:1(다대일) 연관관계를 표현하는 어노테이션
    	- @ManyToOne 어노테이션이 붙어있는 엔티티가 N이고, 반대 엔티티가 1이다.
    	  즉, Post 엔티티가 N이고, User 엔티티가 1이다.
    	- 가장 많이 사용되는 연관관계 표현 어노테이션
 */
@Entity
@Table(name = "board_posts")
@SequenceGenerator(
	name = "post_pk_generator",
	sequenceName = "board_posts_seq",
	initialValue = 1000,
	allocationSize = 1
)
@Getter
@Setter
public class Post extends BaseDateTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_pk_generator")
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(nullable = false)
	private String content;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE) 
	private List<Reply> replies;	   // List 순서 유지 (대부분의 경우 List 사용)
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private Set<PostVoter> postVoters; // Set 중복허용x - 똑같은 사람이 재추천 못하도록
	
}
