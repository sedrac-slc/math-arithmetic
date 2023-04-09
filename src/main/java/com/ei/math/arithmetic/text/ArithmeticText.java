package com.ei.math.arithmetic.text;

import com.ei.math.arithmetic.util.ArithmeticUtil;
import com.ei.math.fraction.Fraction;
import java.util.List;
import java.util.stream.Collectors;

public class ArithmeticText {

    
    public static String sumOrSubList(List<Fraction> fractions, String signal){
        int tam = fractions.size();
        String join = fractions.get(0).text();
        if(tam == 1) return join;  
        for(int i = 1; i < tam; i++)
            join += fractions.get(i).text(true,signal);
        return join;
    }
    
    public static String sumOrSubList(List<Fraction> fractions){
        int tam = fractions.size();
        String join = fractions.get(0).text();
        if(tam == 1) return join;  
        for(int i = 1; i < tam; i++)
            join += fractions.get(i).text(true);
        return join;
    }

    public static String sumOrSubEqual(long den,long result,List<Fraction> fractionsSub){
        String join = result+fractionsSub.stream()
                        .map(frac -> frac.isPositive() ? "+"+frac.getNumerator() : frac.getNumerator()+"")
                        .collect(Collectors.joining(""));
        if(den != 1) return  "("+join+")/"+den;
        return join+"";
    }      
    
    public static String sumOrSubList(Fraction... fractions){
        return sumOrSubList(List.of(fractions));
    }
    
    public static String sumOrSubListMultDenominator(List<Fraction> fractions,long mmc){
        int tam = fractions.size();
        String join = fractions.get(0).text(mmc);
        if(tam == 1) return join;  
        for(int i = 1; i < tam; i++)
            join += fractions.get(i).text(mmc,true);
        return join;
    }

    public static String sumOrSubListMMC(List<Fraction> fractions, long mmc) {
        int tam = fractions.size();
        String join = ArithmeticUtil.fracMMC(fractions.get(0), mmc).text();
        if(tam == 1) return join;  
        for(int i = 1; i < tam; i++)
            join += ArithmeticUtil.fracMMC(fractions.get(i), mmc).text(true);
        return join;        
    }

    public static String sumOrSubListNum(List<Fraction> fractions, long mmc,String signal) {
        int tam = fractions.size();
        String join = ArithmeticUtil.fracMMC(fractions.get(0), mmc).getNumerator()+"";
        if(tam == 1) return join;  
        Fraction fraction;
        for(int i = 1; i < tam; i++){
            fraction = ArithmeticUtil.fracMMC(fractions.get(i), mmc);
            join += fraction.isPositive() ? "+"+fraction.getNumerator() : fraction.getNumerator();
        }
        if(signal.equals("-"))join = "-"+join.replace("+", "-");
        return "("+join +")/"+ mmc;        
    }
    
     public static String sumOrSubListNum(List<Fraction> fractions, long mmc){
         return sumOrSubListNum(fractions, mmc, "");
     }

    public static String sumOrSubListSolveNumerators(List<Fraction> fractions, long mmc) {
        int tam = fractions.size();
        long join = ArithmeticUtil.fracMMC(fractions.get(0), mmc).getNumerator();
        if(tam == 1) return join+"";  
        for(int i = 1; i < tam; i++)
            join += ArithmeticUtil.fracMMC(fractions.get(i), mmc).getNumerator();
        return join +"/"+ mmc;     
    }

    static String fractionArithmeticGroup(String first, String second, String signal) {
      return "("+first+")"+signal+"("+second+")";
    }
    
}
