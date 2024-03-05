package com.sample.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.service.HrService;
import com.sample.vo.Dept;
import com.sample.vo.Employee;
import com.sample.web.dto.Criteria;
import com.sample.web.dto.ListDto;
import com.sample.web.form.EmpCreateForm;

@Controller
@RequestMapping("emp")
public class EmpController {

	@Autowired
	private HrService hrService;
	
	// 1. path 값 앞의 /는 적지 않아도 무관하나 적는 것이 관례
	// 2. path는 기본값이기 때문에 생략 가능 - 보통 여러개를 적을 때는 path를 적고, 하나일 때는 생략
	// 전체 목록 조회 화면
	@GetMapping(path = "/list")
	public String list(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
			Model model) {
		
		Criteria criteria = new Criteria();
		criteria.setPage(page);
		criteria.setRows(rows);
		
		ListDto<Employee> dto = hrService.getAllEmps(criteria);
		model.addAttribute("empList", dto.getItems());  // Model에 담은 값은 최종적으로 ModelAndView에 담겨서 전달됨
		model.addAttribute("paging", dto.getPaging());
		model.addAttribute("criteria", criteria);
		
		return "emp/list";
	}
	
	// 등록 폼 페이지 화면
	@GetMapping("/create")
	public String form(Model model) {
		// 등록폼에서 부서번호 선택할 수 있도록 전체 부서정보 조회 후 Model에 저장
		List<Dept> deptList = hrService.getAllDepts();
		model.addAttribute("deptList", deptList);
		
		return "emp/form";
	}
	
	// 직원 등록 후 전체목록 재요청
	@PostMapping("/create")
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
	@GetMapping("/modify") // detail.jsp > a태그의 href="modify" 속성과 동일
	public String modifyform(int no, Model model) {
		Employee emp = hrService.getEmpDetail(no);
		model.addAttribute("emp", emp);
		
		List<Dept> deptList = hrService.getAllDepts();
		model.addAttribute("deptList", deptList);
		
		return "emp/modifyform";
	}
	
	// 직원정보 수정 후 해당 직원의 상세정보조회 화면 재요청
	@PostMapping("/modify")
	public String modify(int no, EmpCreateForm empUpdateForm) {
		hrService.updateEmp(no, empUpdateForm);
		
		return "redirect:detail?no=" + no;
	}
	
	// 직원 삭제
	@GetMapping("/delete")
	public String delete(int no) {
		hrService.deleteEmp(no);
		
		return "redirect:list";
	}
	
}
