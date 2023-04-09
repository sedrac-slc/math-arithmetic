package com.ei.math.arithmetic.util;

import com.ei.math.MathResult;
import com.ei.math.Step;
import com.ei.math.arithmetic.ArithmeticResult;
import com.ei.math.arithmetic.text.ArithmeticFormatter;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.FractionResult;
import com.ei.math.fraction.registory.FractionMessage;
import com.ei.math.fraction.util.FractionUtil;
import java.util.List;

public class ArithmeticPartStepMethos {

  public static ArithmeticResult baseCase(FractionResult fractionResult){
        return new ArithmeticResult(fractionResult.getExpression(), fractionResult);
  }
  
   public static String getNumeratorOperation(List<Fraction> fractions, long mmc, String sig) {
        int tam = fractions.size();
        String join = ArithmeticUtil.fracMMC(fractions.get(0), mmc).getNumerator()+"";
        if(tam == 1) return join;  
        Fraction fraction;
        for(int i = 1; i < tam; i++){
            fraction = ArithmeticUtil.fracMMC(fractions.get(i), mmc);
            join += "+"+fraction.getNumerator();
        }
        if(join.charAt(0) == '+') join = join.substring(1);
        if(sig.equals("-")) join = join.replace("+", "-");
        return FractionUtil.gameSignal(join);        
    }
   
   public static void simplify(List<Step> list, Fraction fraction, int posSimply, int posFinish) {
        if(!fraction.isIrreducible()){
            list.add(ArithmeticFormatter.stepSimplify(fraction, posSimply));
            fraction = fraction.simplify();
            list.add(ArithmeticFormatter.finish(fraction,posFinish));
        }
    } 
    
   public static void simplify(List<Step> list, Fraction fraction, int pos) {
        simplify(list, fraction, pos, pos+1);
    } 
    
    public static MathResult of(Fraction fraction) {
        FractionMessage message = new FractionMessage();
        return MathResult.builder().steps(
          List.of(new Step(1,fraction.text(),fraction.html(),message.getString("step.start")))
        ).result(fraction.text())
         .expression(fraction.text())
         .pack(fraction.getClass().getPackageName())
         .className(fraction.getClass().getName())
         .status(true)
         .object(fraction)
         .build();
    }   
   
}
