package com.zy.myeyes.validatores;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.zy.myeyes.beans.User;

@Component
public class UserValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		User user = (User)target;
		if (user.getName().length() < 8){
			errors.rejectValue("userName", "valid.userNameLen", 
					new Object[]{"minLength", 8}, "�û����������� {0}λ");
		}
	}
	
}
