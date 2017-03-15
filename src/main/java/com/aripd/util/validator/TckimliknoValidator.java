package com.aripd.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * TCK Validator
 * 
 */
public class TckimliknoValidator implements ConstraintValidator<Tckimlikno, String> {

    @Override
    public void initialize(Tckimlikno a) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cvc) {
        return value == null || (value.length() == 11 && isDigits(value) && isCorrect(value));
    }

    public boolean isDigits(String s) {
        return java.util.regex.Pattern.matches("\\d+", s);
    }

    public boolean isCorrect(String value) {
        char[] digit = value.toCharArray();
        
        int c1 = digit[0];
        int c2 = digit[1];
        int c3 = digit[2];
        int c4 = digit[3];
        int c5 = digit[4];
        int c6 = digit[5];
        int c7 = digit[6];
        int c8 = digit[7];
        int c9 = digit[8];
        int c10 = digit[9];
        int c11 = digit[10];

        // İlk hane sıfır olamaz
        if (c1 == 0) {
            return false;
        }

        // Son hanenin çift olup olmadığını kontrol ediyoruz
        if (c11 % 2 == 1) {
            return false;
        }

        // 1. 3. 5. 7. ve 9. hanelerin toplamının 7 katından, 2. 4. 6. ve 8. hanelerin toplamı çıkartıldığında, elde edilen sonucun 10′a bölümünden kalan, yani Mod10′u bize 10. haneyi verir.
        int check1 = (((c1 + c3 + c5 + c7 + c9) * 7) - (c2 + c4 + c6 + c8)) % 10;

        //1. 2. 3. 4. 5. 6. 7. 8. 9. ve 10. hanelerin toplamından elde edilen sonucun 10′a bölümünden kalan, yani Mod10′u bize 11. haneyi verir.
        int check2 = (c1 + c2 + c3 + c4 + c5 + c6 + c7 + c8 + c9 + c10) % 10;

        if (check1 != c10) {
            return false;
        }
        
        return check2 == c11;
    }
}
