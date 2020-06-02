package com.luv2code.payphoneApp.validator;

import com.luv2code.payphoneApp.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator  implements Validator {

   // private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w\\d._-]+@[\\w\\d.-]+\\.[\\w\\d]{2,6}$");

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        User user = (User) object;

        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","Username is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","Password is Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"fullName","Full Name is Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"confirmPassword","Confirm Password is required");
*/
        if(user.getPassword().length()<6){
            errors.rejectValue("password","Length","Password Must be 6 Characters");
        }

        if(user.getConfirmPassword()!=null &&!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("confirmPassword","Match","Password Must Match !");
        }

       /* if(user.getUsername()!=null && !EMAIL_REGEX.matcher(user.getUsername()).matches()){
            errors.rejectValue("username","Email is not valid");
        }*/

    }
}
