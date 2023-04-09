package com.ei.math.arithmetic.text;

import com.ei.math.arithmetic.util.ArithmeticUtil;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.text.FractionHtml;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArithmeticHtml {

    public static String sumOrSubList(List<Fraction> fractions,String signal){
        int tam = fractions.size();
        String join = fractions.get(0).html();
        if(tam == 1) return join;  
        for(int i = 1; i < tam; i++)
            join += fractions.get(i).html(true,signal);
        return "<div class='fraction-arithmetic'>"+join+"</div>";
    }
    
    public static String sumOrSubList(List<Fraction> fractions){
        int tam = fractions.size();
        String join = fractions.get(0).html();
        if(tam == 1) return join;  
        for(int i = 1; i < tam; i++)
            join += fractions.get(i).html(true);
        return "<div class='fraction-arithmetic'>"+join+"</div>";
    }
    
    public static String sumOrSubEqual(long den,long result,List<Fraction> fractionsSub){
        String join = result+fractionsSub.stream()
                        .map(frac -> frac.isPositive() ? "+"+frac.getNumerator() : ""+frac.getNumerator())
                        .collect(Collectors.joining());
        if(den != 1) return  FractionHtml.template(join, den+"", "numflex");
        return "<div class='fraction-flex-simple'>"+join+"</div>";
    }    
    
    public static String sumOrSubList(Fraction... fractions){
        return sumOrSubList(Arrays.asList(fractions));
    }
    
    public static String sumOrSubListMultDenominator(List<Fraction> fractions,long mmc){
        int tam = fractions.size();
        String join = fractions.get(0).html(mmc);
        if(tam == 1) return join;  
        for(int i = 1; i < tam; i++)
            join += fractions.get(i).html(mmc,true);
        return "<div class='fraction-arithmetic'>"+join+"</div>";
    }

    public static String sumOrSubListMMC(List<Fraction> fractions, long mmc) {
        int tam = fractions.size();
        String join = ArithmeticUtil.fracMMC(fractions.get(0), mmc).html();
        if(tam == 1) return join;  
        for(int i = 1; i < tam; i++)
            join += ArithmeticUtil.fracMMC(fractions.get(i), mmc).html(true);
        return "<div class='fraction-arithmetic'>"+join+"</div>";        
    }

    public static String sumOrSubListNum(List<Fraction> fractions, long mmc) {
        int tam = fractions.size();
        String join = ArithmeticUtil.fracMMC(fractions.get(0), mmc).getNumerator()+"";
        if(tam == 1) return join;  
        Fraction fraction;
        for(int i = 1; i < tam; i++){
            fraction = ArithmeticUtil.fracMMC(fractions.get(i), mmc);
            join += fraction.isPositive() ? "+"+fraction.getNumerator() : fraction.getNumerator();
        }
        return FractionHtml.template(join, mmc+"");        
    }

    public static String sumOrSubListSolveNumerators(List<Fraction> fractions, long mmc) {
        int tam = fractions.size();
        long join = ArithmeticUtil.fracMMC(fractions.get(0), mmc).getNumerator();
        if(tam == 1) return join+"";  
        for(int i = 1; i < tam; i++)
            join += ArithmeticUtil.fracMMC(fractions.get(i), mmc).getNumerator();
        return FractionHtml.template(join+"", mmc+"");        
    }
    
    public static String fractionArithmeticGroup(String htmlOne,String htmlTwo,String signal){
        return "<div class='fraction-arithmetic-group'>\n" 
                +"<div class='fraction-arithmetic-group-item'>"+htmlOne+"</div>"
                +"<div class='fraction-arithmetic-group-signal'>"+signal+"</div>"
                +"<div class='fraction-arithmetic-group-item'>"+htmlTwo+"</div>"
               +"</div>";
    }
    
    public static String fractionArithmeticGroup(String htmlOne,String htmlTwo){
         return fractionArithmeticGroup(htmlOne, htmlTwo, "+");
     }

    
}
