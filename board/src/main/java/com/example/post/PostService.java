package com.example.post; // 20240320 Day21

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	
	/**
	 * 지정된 페이지에 해당하는 게시글 목록을 조회해서 반환한다.
	 * @param page 조회할 페이지 번호
	 * @return 전체 게시글 목록
	 */
	public Page<Post> getPosts(int page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("id")));
		return postRepository.findAll(pageable);
	}

	/**
	 * 지정된 게시글 아이디에 해당하는 게시글 정보를 반환한다.
	 * @param id 게시글 아이디
	 * @return 게시글 정보가 포함된 Post 객체, 게시글 정보가 존재하지 않으면 예외를 발생시킨다.
	 */
	public Post getPostDetail(Long id) {
		Optional<Post> optional = postRepository.findById(id);
		
		// 1번째 방법 - 내가 원하는 사용자정의 예외 Exception 발생시키고 싶을 때
		if (optional.isEmpty()) {
			throw new RuntimeException("게시글 정보가 존재하지 않습니다.");
		}
		
		return optional.get();
		
		// 2번째 방법 - 자동으로 NoSuchException 발생
//		return optional.orElseThrow(); // optional이 감싸고 있는 Post 객체를 반환하고, value(Post)가 존재하지 않으면 예외를 발생시킴
	}
}
