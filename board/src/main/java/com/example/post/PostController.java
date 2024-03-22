package com.example.post; // 20240320 Day21

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	
	@PreAuthorize("isAuthenticated()") // 로그인하지 않은 상태에서 url로 진입 시, 로그인폼으로 이동
	@GetMapping("/create")
	public String form(Model model) {
		model.addAttribute("postForm", new PostForm()); // 등록 오류났을 때 입력해놨던 값 다시 보여주기 위해 입력된 값을 담아놓을 폼 생성
		
		return "post/form";		// "src/main/resources/templates/post/form.html"
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String create(@Valid PostForm postForm, BindingResult errors, Principal principal) {
		if (errors.hasErrors()) {
			return "post/form";
		}
		
		postService.createPost(postForm, principal.getName());
		
		return "redirect:/post/list";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam(name = "page", required = false, defaultValue = "0") int page, Model model) {
		Page<Post> paging = postService.getPosts(page);
		model.addAttribute("paging", paging);
		
		return "post/list";		// "src/main/resources/templates/post/list.html"로 내부이동 (화면으로 이동할 때는 기본적으로 Model이 있음)
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam("id") Long id, Model model) {
		Post post = postService.getPostDetail(id);
		model.addAttribute("post", post);
		
		return "post/detail";	// "src/main/resources/templates/post/detail.html"
	}
	
	// 상세페이지에서 수정 버튼 클릭
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String form(@PathVariable("id") Long id, Principal principal, Model model) {
		Post post = postService.getPostDetail(id);
		// post.getUser().getUsername() : 게시글 작성자 아이디
		// principal.getName()			: 로그인한 사용자 아이디
		if (!post.getUser().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "다른 작성자의 게시글은 수정할 수 없습니다.");
		}
		
		PostForm postForm = new PostForm();
		postForm.setTitle(post.getTitle());
		postForm.setContent(post.getContent());
		model.addAttribute("postForm", postForm);
		
		return "post/form";		// "src/main/resources/templates/post/form.html"
	}
	
	// 수정폼에서 수정버튼 클릭
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")					// @Valid와 BindingResult는 항상 바로 옆에 붙어있어야 함. 순서 유지
	public String modify(@PathVariable("id") Long id, @Valid PostForm postForm, BindingResult errors, Principal principal) {
		if (errors.hasErrors()) {
			return "post/form";
		}
		
		Post post = postService.getPostDetail(id);
		if (!post.getUser().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "다른 작성자의 게시글은 수정할 수 없습니다.");
		}
		
		postService.updatePost(postForm, post);
		
		return String.format("redirect:/post/detail?id=%d", id); // %d : 매개변수 id가 치환될 위치
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{id}")
	public String vote(@PathVariable("id") Long id, Principal principal) {
		postService.vote(id, principal.getName());
		
		return String.format("redirect:/post/detail?id=%d", id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Principal principal) {
		Post post = postService.getPostDetail(id);
		if (!post.getUser().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "다른 작성자의 게시글은 삭제할 수 없습니다.");
		}
		
		postService.deletePost(post);
		
		return "redirect:/post/list";
	}
}
