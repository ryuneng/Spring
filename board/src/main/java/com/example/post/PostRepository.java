package com.example.post; // 20240320 Day21

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	// Page<Post> findAll(Pageable pageable); // PagingAndSortingRepository를 통해 페이징/정렬 기능이 구현되어 자동으로 되기 때문에 안적어도 됨
}
