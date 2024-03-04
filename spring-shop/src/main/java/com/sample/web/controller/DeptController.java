package com.sample.web.controller; // 20240304 Day9 실습

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.service.HrService;
import com.sample.web.form.DeptCreateForm;

@Controller
@RequestMapping("/dept")
public class DeptController {

	@Autowired
	private HrService hrService;

	// 부서 리스트 조회
	@GetMapping(path = "/list")
	public String list(Model model) {
		model.addAttribute("deptList", hrService.getAllDepts());
		
		return "dept/list";
	}
	
	// 신규 부서 등록 폼
	@GetMapping("/create")
	public String form(Model model) {
		return "dept/form";
	}
	
	@PostMapping("/create")
	public String create(DeptCreateForm deptCreateForm) {
		hrService.createDept(deptCreateForm);
		
		return "redirect:list";
	}
}
