package com.ei.math.arithmetic.util;

import com.ei.math.MathResult;
import com.ei.math.Step;
import com.ei.math.number.util.MMC;
import com.ei.math.arithmetic.ArithmeticRegex;
import com.ei.math.arithmetic.text.ArithmeticFormatter;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.FractionConverter;
import com.ei.math.fraction.util.FractionUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ArithmeticUtil {

    public static List<Fraction> join(Fraction fraction, List<Fraction> fractions) {
        List<Fraction> list = new ArrayList<>(Arrays.asList(fraction));
        list.addAll(fractions);
        return list;
    }
    
    public static List<Fraction> join(Fraction fraction, Fraction... fractions) {
        return ArithmeticUtil.join(fraction, Arrays.asList(fractions));
    }
    
    public static long mmc(List<Fraction> fractions) {
        List<Long> numbs = fractions.stream().map(Fraction::getDenominator).collect(Collectors.toList());
        return MMC.solve(numbs);
    }
    
    public static Fraction fracMMC(Fraction fraction, long mmc) {
        long num = fraction.getNumerator() * (mmc / fraction.getDenominator());
        return Fraction.of(num, mmc);
    }
    
    public static Fraction getFractionMMC(List<Fraction> fractions, long mmc) {
        int tam = fractions.size();
        long join = ArithmeticUtil.fracMMC(fractions.get(0), mmc).getNumerator();
        if(tam == 1) return Fraction.of(join, mmc);  
        for(int i = 1; i < tam; i++)
            join += ArithmeticUtil.fracMMC(fractions.get(i), mmc).getNumerator();
        return Fraction.of(join, mmc);        
    }
    
    public static List<Fraction> reduce(List<Fraction> fractions, Function<Fraction, Fraction> func) {
         return fractions.stream()
                .map(frac-> func.apply(frac))
                .collect(Collectors.toList());
    }
    
    public static List<Fraction> parseListFraction(String expression,String sig){
        String[] numbs = expression.split(sig);
        return Arrays.stream(numbs).map(FractionConverter::parse).collect(Collectors.toList());  
    }
    
    public static List<Fraction> parseListSignalGroup(String expression,String rgx, boolean reverteDiv){
        String[] numbs = expression.split(rgx);
        List<Fraction> list = new ArrayList<>();
        int tam = numbs.length;
        int pos , start = 0;
        String numb, signal;
        Fraction fraction;
           for(int i=0; i < tam; i++)
            if(numbs[i].matches(ArithmeticRegex.FRACTION)){
                pos = expression.indexOf(numbs[i],start);
                signal  = pos-1 >= 0 ? signal(expression.charAt(pos-1),true) : "";
                numb = pos - 1 >= 0  ? plusOrSub(signal)+numbs[i] : numbs[i];
                start = pos + numb.length();
                fraction = FractionConverter.parse(numb);
                if(reverteDiv && ":".equals(signal)) fraction = fraction.reverse();
                list.add(fraction);
            }
        return  list;    
    }
    
    public static List<Fraction> parseListSignalGroup(String expression,String signal){
        return parseListSignalGroup(expression, signal, false);
    }
   
    public static String signal(char charAt, boolean rever) {
        if(charAt == '+' || charAt == '-') return Character.toString(charAt);
        if(rever && (charAt == ':' || charAt == '*')) return Character.toString(charAt);
        return "";
    }

    public static String plusOrSub(String signal) {
       if("+".equals(signal) || "-".equals(signal)) return signal;
        return "";
    }
    
    public static List<String> multOrDivOperaction(String expression){
        String rgx = ArithmeticRegex.MULT_DIV.replace("[\\+|\\-]?","");
         return Pattern.compile(rgx)
                       .matcher(expression)
                       .results()
                       .map(MatchResult::group)
                       .collect(Collectors.toList());
    }
   
    
    public static String gameSignalMultOrDiv(String expression){
        Pattern pattern = Pattern.compile(ArithmeticRegex.MULT_DIV_SIG);
        Matcher matcher = pattern.matcher(expression);
        String replace;
        while(matcher.find()){
            long count = matcher.group().chars().filter( charac -> charac == '-').count();
            replace = matcher.group().replaceAll("[\\-|\\+]","");
            if(count % 2 != 0) replace = "-"+replace; 
            expression = expression.replace(matcher.group(),replace);
        }
        expression = FractionUtil.gameSignal(expression);
        if(expression.charAt(0) == '+') expression = expression.substring(1);
        return expression;
    }
    
    
    public static MathResult commun(List<Fraction> fractions){
        long ini = System.currentTimeMillis();
        final long den = fractions.get(0).getDenominator();
        long start = fractions.get(0).getNumerator();
        final int tam = fractions.size();
        List<Step> steps = new ArrayList<>();
        for(int i = 1; i < tam; i++){
            start += fractions.get(i).getNumerator();
            steps.add(ArithmeticFormatter.stepSumOrSubEqual(den, start, fractions.subList(i+1, tam), fractions.get(i).getNumerator(), i));
        }
        Fraction fraction = Fraction.of(start,den);
        ArithmeticPartStepMethos.simplify(steps, fraction, tam);
        long fin = System.currentTimeMillis();
        return MathResult.builder()
                .status(true)
                .steps(steps)
                .timeMilliseconds(String.format("%s ms",(fin-ini)))
                .className("generic")
                .pack("generic")
                .build();
    }
    
    
}
