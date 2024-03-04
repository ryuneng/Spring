package com.sample.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.service.HrService;
import com.sample.vo.Dept;
import com.sample.vo.Employee;
import com.sample.web.form.EmpCreateForm;

@Controller
@RequestMapping("emp")
public class EmpController {

	@Autowired
	private HrService hrService;
	
	// 전체 목록 조회 화면 (path 값 앞의 /는 적지 않아도 무관하나 적는 것이 관례)
	@GetMapping(path = "/list")
	public String list(Model model) {
		model.addAttribute("empList", hrService.getAllEmps());
		
		return "emp/list";
	}
	
	// 등록 폼 페이지 화면
	@GetMapping(path = "/create")
	public String form(Model model) {
		// 등록폼에서 부서번호 선택할 수 있도록 전체 부서정보 조회 후 Model에 저장
		List<Dept> deptList = hrService.getAllDepts();
		model.addAttribute("deptList", deptList);
		
		return "emp/form";
	}
	
	// 직원 등록 후 전체목록 재요청
	@PostMapping(path = "/create")
	public String create(EmpCreateForm empCreateForm) {
		hrService.createEmp(empCreateForm);
		
		return "redirect:list";
	}
	
	// 상세조회 화면
	@GetMapping("/detail")
	public String detail(int no, Model model) {
		Employee emp = hrService.getEmpDetail(no);
		
		List<Dept> deptList = hrService.getAllDepts();
		model.addAttribute("deptList", deptList);
		
		model.addAttribute("emp", emp);
		
		return "emp/detail";
	}
	
	// 수정 폼 페이지 화면
	@GetMapping(path = "/modify") // detail.jsp > a태그의 href="modify" 속성과 동일
	public String modifyform(int no, Model model) {
		Employee emp = hrService.getEmpDetail(no);
		model.addAttribute("emp", emp);
		
		List<Dept> deptList = hrService.getAllDepts();
		model.addAttribute("deptList", deptList);
		
		return "emp/modifyform";
	}
	
	// 직원정보 수정 후 전체목록 재요청
	@PostMapping(path = "/modify")
	public String modify(int no, EmpCreateForm empUpdateForm) {
		hrService.updateEmp(no, empUpdateForm);
		
		return "redirect:list";
	}
	
	// 직원 삭제
	@GetMapping(path = "/delete")
	public String delete(int no, EmpCreateForm empDeleteForm) {
		hrService.deleteEmp(no, empDeleteForm);
		
		return "redirect:list";
	}
	
}
