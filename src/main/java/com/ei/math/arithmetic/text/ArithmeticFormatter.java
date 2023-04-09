package com.ei.math.arithmetic.text;

import com.ei.math.Step;
import com.ei.math.arithmetic.ArithmeticRegex;
import com.ei.math.arithmetic.registory.ArithmeticMessage;
import com.ei.math.arithmetic.util.ArithmeticPartStepMethos;
import com.ei.math.arithmetic.util.ArithmeticUtil;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.FractionConverter;
import com.ei.math.fraction.text.FractionHtml;
import com.ei.math.fraction.text.FractionText;
import java.util.List;
import java.util.Locale;

final public class ArithmeticFormatter {
    public static final  ArithmeticMessage message;
    
    static{
        message = new ArithmeticMessage();
    }
    
    public static void setLocale(Locale locale){
        message.setLocale(locale);
    }
    
    public static Step startMultOrDiv(List<Fraction> fractions, String signal){
        return startMultOrDiv(fractions, signal, 0);
    }
    
    public static Step start(List<Fraction> fraction){
        return start(fraction, 0);
    }

    public static Step startMultOrDiv(List<Fraction> fractions, String signal, int pos){
       String msg = message.getString("step.arith.start");
       String text = ArithmeticText.sumOrSubList(fractions,signal);
       String html = ArithmeticHtml.sumOrSubList(fractions,signal);
       return Step.builder().text(text).html(html).message(msg).codigo(pos).build();
    }
    
    public static Step start(List<Fraction> fractions, int pos){
       String msg = message.getString("step.arith.start");
       String text = ArithmeticText.sumOrSubList(fractions);
       String html = ArithmeticHtml.sumOrSubList(fractions);
       return Step.builder().text(text).html(html).message(msg).codigo(pos).build();
    }  
    
     public static Step stepSumOrSubEqual(long den,long result,List<Fraction> fractionsSub, long num, int pos){
       String msg = String.format(message.getString("step.arith.eq.oper"));
       String text = ArithmeticText.sumOrSubEqual(den,result,fractionsSub);
       String html = ArithmeticHtml.sumOrSubEqual(den,result,fractionsSub);
       return Step.builder().text(text).html(html).message(msg).codigo(pos).build();
    }     
    
    public static Step stepSequencial(List<Fraction> fractions,String operacao, int pos){
       String msg = String.format(message.getString("step.arith.seq"),operacao, fractions);
       String text = ArithmeticText.sumOrSubList(fractions);
       String html = ArithmeticHtml.sumOrSubList(fractions);
       return Step.builder().text(text).html(html).message(msg).codigo(pos).build();
    }  
    
    public static Step stepSequencial(List<Fraction> fractions,String operacao, int pos,String signal){
       String msg = String.format(message.getString("step.arith.seq"),operacao, fractions);
       String text = ArithmeticText.sumOrSubList(fractions,signal);
       String html = ArithmeticHtml.sumOrSubList(fractions,signal);
       return Step.builder().text(text).html(html).message(msg).codigo(pos).build();
    }      
    
    public static Step stepOneCrossSystem(List<Fraction> fractions, long mmc){
       String msg = String.format(message.getString("step.arith.cross.one"),fractions);
       String text = ArithmeticText.sumOrSubListMultDenominator(fractions, mmc);
       String html = ArithmeticHtml.sumOrSubListMultDenominator(fractions, mmc);
       return Step.builder().text(text).html(html).codigo(1).message(msg).build();
    }  
    
    public static Step stepTwoCrossSystem(List<Fraction> fractions, long mmc){
       String msg = String.format(message.getString("step.arith.cross.two"), mmc);
       String text = ArithmeticText.sumOrSubListMMC(fractions, mmc);
       String html = ArithmeticHtml.sumOrSubListMMC(fractions, mmc);
       return Step.builder().text(text).html(html).codigo(2).message(msg).build();
    }  
    
    public static Step stepThreeCrossSystem(List<Fraction> fractions, long mmc,String sig){
       String msg1 = "";
       if(sig.equals("-")){
           msg1 = "-";
           fractions = ArithmeticUtil.reduce(fractions, frac -> frac.isNegative() ? frac.positive() : frac);
       }
       msg1 += ArithmeticPartStepMethos.getNumeratorOperation(fractions, mmc, sig); 
       String msg = String.format(message.getString("step.arith.cross.three"),msg1,mmc);
       String text = ArithmeticText.sumOrSubListNum(fractions, mmc,sig);
       String html = ArithmeticHtml.sumOrSubListNum(fractions, mmc);
       return Step.builder().text(text).html(html).codigo(3).message(msg).build();
    }  

    public static Step stepFourCrossSystem(List<Fraction> fractions, long mmc) {
       String msg = String.format(message.getString("step.arith.cross.four"),mmc);
       String text = ArithmeticText.sumOrSubListSolveNumerators(fractions, mmc);
       String html = ArithmeticHtml.sumOrSubListSolveNumerators(fractions, mmc);
       return Step.builder().text(text).html(html).codigo(4).message(msg).build();
    }
    
    public static Step stepSimplify(Fraction fraction,int pos) {
        long mdc = fraction.mdc();
        String msg = String.format(message.getString("step.arith.simplify"),mdc);        
        return Step.builder()
                   .text(FractionText.simply(fraction, mdc))
                   .html(FractionHtml.simply(fraction, mdc))
                   .message(msg)
                   .codigo(pos)
                   .build();         
    }
    
    public static Step finish(Fraction fraction, int pos){
       String msg = String.format(message.getString("step.arith.irreducible"));
       return Step.builder().text(fraction.text()).html(fraction.html()).message(msg).codigo(pos).build();
    }  
    
    public static Step grouping(Step first, Step second,String signal,int pos) {
       String msg = String.format(message.getString("step.arith.group"),first.getText(), second.getText()); 
       String text = ArithmeticText.fractionArithmeticGroup(first.getText(), second.getText(),signal);
       String html = ArithmeticHtml.fractionArithmeticGroup(first.getHtml(), second.getHtml(),signal);
       return Step.builder().text(text).html(html).message(msg).codigo(pos).build();
   }

    public static Step expressionOperation(String expression,String rgx, int pos) {
        
         boolean startNeg = false;
         if(expression.charAt(0) == '-'){
            expression = expression.substring(1);
            startNeg = true;
        }
         
        String[] numbs = expression.split(rgx);
        if(startNeg) numbs[0] = "-"+numbs[0];
        
        int tam = numbs.length, index, start = 0;
        String signal , numb , text = "", html = "";
        
        Fraction fraction;
        for(int i=0; i < tam; i++)
            if(numbs[i].matches("\\-?"+ArithmeticRegex.FRACTION)){
                index = expression.indexOf(numbs[i],start);
                signal  = index-1 >= 0 ? ArithmeticUtil.signal(expression.charAt(index-1),true) : "";
                numb = index - 1 >= 0  ? ArithmeticUtil.plusOrSub(signal)+numbs[i] : numbs[i];
                start = index + numb.length();
                fraction = FractionConverter.parse(numb);
                text += fraction.text(true, signal);
                html += fraction.html(true, signal);   
            }
        String msg = String.format(message.getString("step.arith.seq"),expression,"");
        return Step.builder()
                .text(text)
                .html("<div class=\"fraction-arithmetic\">"+html+"</div>")
                .message(msg).codigo(pos).build();
    }
   
     
}
