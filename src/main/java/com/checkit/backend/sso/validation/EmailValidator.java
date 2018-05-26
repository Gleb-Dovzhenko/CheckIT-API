package com.checkit.backend.sso.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Gleb Dovzhenko on 10.05.2018.
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final Pattern VALID_EMAIL_ADDRESS = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
    private Matcher matcher;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return validateEmail(email);
    }

    private boolean validateEmail(String email) {
        matcher = VALID_EMAIL_ADDRESS.matcher(email);
        return matcher.matches();
    }
}
