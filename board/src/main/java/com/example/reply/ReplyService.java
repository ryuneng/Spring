package com.example.reply; // 20240322 Day23

import org.springframework.stereotype.Service;

import com.example.post.Post;
import com.example.post.PostRepository;
import com.example.user.User;
import com.example.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {

	private final PostRepository postRepository;
	private final ReplyRepository replyRepository;
	private final ReplyVoterRepository replyVoterRepository;
	private final UserRepository userRepository;
	
	/**
	 * 댓글내용, 게시글번호, 사용자 아이디를 전달받아서 새 댓글을 추가한다.
	 * @param content 댓글내용
	 * @param postId 게시글번호
	 * @param username 사용자아이디
	 */
	public Reply createReply(String content, Long postId, String username) {
		Post post = postRepository.findById(postId).orElseThrow(); // orElseThrow() : 조회되는 데이터가 있으면 꺼내주고, 없으면 예외발생
		User user = userRepository.findByUsername(username).orElseThrow();
		
		Reply reply = new Reply();
		reply.setContent(content);
		reply.setPost(post);
		reply.setUser(user);
		
		return replyRepository.save(reply);
	}
}
