package com.ei.math.arithmetic.registory;

import com.ei.math.fraction.registory.FractionMessage;
import com.ei.math.fraction.text.FractionFormatter;
import java.util.Locale;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ArithmeticMessage extends FractionMessage{
    
    {
        super.baseName = "com.ei.math.arithmetic.languages.ResourceBundle";
    }

    public static String get(String key) {
        return (new ArithmeticMessage()).getString(key);
    }


    @Override
    public void setLocale(Locale locale) {
        FractionFormatter.setLocale(locale);
        this.locale  = locale;
    }
    

    
   
}
