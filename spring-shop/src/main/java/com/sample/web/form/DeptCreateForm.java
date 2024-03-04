package com.sample.web.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeptCreateForm {
	// form클래스에는 입력화면에 있는 변수만 있어야 함. -> no는 없어야 함
	private String name;
	private String tel;
	private String fax;
}
