package com.sample.web.controller; // 20240226 Day5

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.service.CompanyService;
import com.sample.service.ProductService;
import com.sample.vo.Company;
import com.sample.vo.Product;
import com.sample.web.dto.Criteria;
import com.sample.web.dto.ListDto;
import com.sample.web.form.ProductCreateForm;

/* 20240227 Day6
    @Controller
    	- 이 클래스가 MVC 패턴에서 컨트롤러의 역할을 수행하는 클래스임을 나타낸다.
    	- 이 클래스가 컴포넌트스캔 대상 클래스임을 나타낸다. 컴포넌트스캔 대상 클래스는 애플리케이션 시작 시에 스프링 컨테이너가 객체를 생성하고, 필요한 의존성을 주입한다.
    	
    @RequestMapping("/product")
    	- URL 매핑정보를 정의한다.
    	- 클래스에 정의된 URL 매핑정보는 요청핸들러 메서드에 정의된 URL 매핑정보에 접두사처럼 추가된다.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
	
	/*
	    @Autowired
	    	- 자동의존성 주입을 지원하는 어노테이션
	    	- ProductController가 의존하는 객체를 스프링 컨테이너에서 찾아서 자동으로 조립한다.
	    	  만약 ProductService 타입의 객체가 스프링 컨테이너에 없으면, 애플리케이션 시작 시 오류가 발생한다.
	 */
	@Autowired // 객체 찾아서 넣어줌 (객체 생성 X)
	private ProductService productService;
	@Autowired
	private CompanyService companyService;
	
	/*
	    요청 URL
	    	/product/detail?no=10
	    요청파라미터
	    	no=10 (매개변수로 전달받도록 정의해두면 HandlerAdapter가 알아서 넣어줌)
	 */
	@GetMapping("/detail")
	public String detail(int no, Model model) { // 1.mapper -> 2.service -> 3.controller로 전달받은 product를 *model에 담아서 4.jsp로 전달
		Product product = productService.getProductDetail(no);
		model.addAttribute("product", product);
		
		return "product/detail";		// "/WEB-INF/views/product/detail.jsp"로 내부이동
	}

	/*
	    요청 URL
	    	/product/list
	    	/product/list?page=1&row=10&sort=date&opt=keyword
	    	/product/list?page=1&row=10&sort=lowprice&opt=name&keyword=노트북
	 */
	@GetMapping(path = "/list")
	public String list(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
			@RequestParam(name = "sort", required = false, defaultValue = "date") String sort,
			@RequestParam(name = "opt", required = false) String opt,
			@RequestParam(name = "keyword", required = false) String keyword,
			Model model) {
		
		Criteria criteria = new Criteria();
		criteria.setPage(page);
		criteria.setRows(rows);
		criteria.setSort(sort);
		
		// 검색옵션(opt)과 검색어(keyword) 모두 null이나 빈 문자열이 아닐 때만 Map에 저장한다.
		if (StringUtils.hasText(opt) && StringUtils.hasText(keyword)) {
			criteria.setOpt(opt);
			criteria.setKeyword(keyword);
		}
		
		ListDto<Product> dto = productService.getProducts(criteria);
		model.addAttribute("productList", dto.getItems()); // Model에 담은 값은 최종적으로 ModelAndView에 담겨서 전달됨
		model.addAttribute("paging", dto.getPaging());
		model.addAttribute("criteria", criteria);
		
		return "product/list";    // "/WEB-INF/views/product/list.jsp"로 내부이동
	}
	
	@GetMapping("/create")
	public String form(Model model) {
		// 전체 회사정보를 조회하고, Model에 저장한다. (등록폼에서 제조사 선택할 수 있어야 하니까)
		List<Company> companyList = companyService.getAllCompanies();
		model.addAttribute("companyList", companyList);
		
		return "product/form";    // "/WEB-INF/views/product/form.jsp"로 내부이동
	}
	
	@PostMapping("/create") // 상품등록 폼에서 등록 버튼 눌렀을 때
	public String create(ProductCreateForm productCreateForm) { // Form 객체(Command 객체)
		productService.createProduct(productCreateForm);
		
		return "redirect:list";
	}
	
	@GetMapping("/delete")
						// 요청핸들러메서드의 변수명을 다르게 가져가고 싶으면 @RequestParam 을 부착해줘야 함 (변수명을 no로 설정할거면 부착안해도 됨)
	public String delete(@RequestParam("no") List<Integer> noList) {
		productService.deleteProducts(noList);
		
		return "redirect:list";
	}
}
