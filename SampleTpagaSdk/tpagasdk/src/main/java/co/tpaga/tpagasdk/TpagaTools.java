package co.tpaga.tpagasdk;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import org.apache.commons.validator.routines.CreditCardValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TpagaTools {


    public static boolean isValidCardNumber(String carnum) {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.MASTERCARD + CreditCardValidator.VISA +
                CreditCardValidator.AMEX + CreditCardValidator.DINERS);
        if (TextUtils.isEmpty(carnum)) return false;
        return ccv.isValid(carnum);
    }

    public static boolean isValidExpirationDate(String year, String month) {
        return isValidMonth(month);

    }

    public static boolean isValidMonth(String s) {
        if (TextUtils.isEmpty(s)) return false;
        int month = Integer.parseInt(s);
        return ((month > 0 && month < 13) ? true : false);
    }


    public static boolean isNameValid(CharSequence name) {
        if (TextUtils.isEmpty(name)) return false;
        Pattern pattern = Pattern.compile("[^0-9()*^|\\.,:;\"&@$~+_-]+");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static void showToast(Context context, int i) {
        int duration = Toast.LENGTH_LONG;
        Toast.makeText(context, i, duration).show();
    }
}
