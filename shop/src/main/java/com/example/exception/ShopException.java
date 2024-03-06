package com.example.exception; // 20240306 Day11

public class ShopException extends RuntimeException {

	private static final long serialVersionUID = 6500075737162637512L;

	public ShopException() {}
	
	public ShopException(String message) {
		super(message);
	}
	
	public ShopException(String message, Throwable cause) {
		super(message, cause);
	}
}

/*
    1. ShopException 생성
    2. AlreadyUsedIdException, AlreadyUsedEmailException 생성
    3. UserService
			public void registerUser(UserRegisterForm form) {
				User foundUser = userMapper.getUserById(form.getId());
				if (foundUser != null) {
					throw new AlreadyUsedIdException("["+form.getId()+"]는 이미 사용중인 아이디입니다.");
				}
				
				foundUser = userMapper.getUserByEmail(form.getEmail());
				if (foundUser != null) {
					throw new AlreadyUsedEmailException("["+form.getEmail()+"]는 이미 사용중인 이메일입니다.");
				}
				
				User user = form.toUser();
				userMapper.insertUser(user);
			}
			
	4. HomeController
			try {
				// 폼 입력값 유효성 체크를 통과한 경우
				userService.registerUser(form);
			} catch (AlreadyUsedIdException ex) {
				errors.rejectValue("id", null, ex.getMessage());
				return "form";
				
			} catch (AlreadyUsedEmailException ex) {
				errors.rejectValue("email", null, ex.getMessage());
				return "form";
			}
				
			return "redirect:/";
 */