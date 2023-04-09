package com.ei.math.arithmetic.operator;

import com.ei.math.arithmetic.abs.ArithOperaction;
import com.ei.math.MathResult;
import com.ei.math.arithmetic.basic.ArithmeticMult;
import com.ei.math.arithmetic.basic.ArithmeticDiv;
import com.ei.math.Step;
import com.ei.math.StepMap;
import com.ei.math.arithmetic.ArithmeticRegex;
import com.ei.math.arithmetic.params.ArithmeticParams;
import com.ei.math.arithmetic.text.ArithmeticFormatter;
import com.ei.math.arithmetic.util.ArithmeticUtil;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.util.FractionUtil;
import com.ei.math.util.MathResultUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.Setter;

public class ArithmeticMultOrDiv extends ArithOperaction{
    public final static String METHOD_RANDOM = "random";
    public final static String METHOD_SEQUENCIAL = "sequencial";
    public final static String METHOD_PRIORITY_MULT = "priorityMult";
    public final static String METHOD_PRIORITY_DIV = "priorityDiv";
    
    private final ArithmeticDiv arithmeticDiv;
    private final ArithmeticMult arithmeticMult;
    
    @Getter @Setter
    private String methodFractionMult = "";
    @Getter @Setter
    private String method = METHOD_SEQUENCIAL;

    {
        arithmeticDiv = new ArithmeticDiv();
        arithmeticMult = new ArithmeticMult();
    }

    public MathResult sequencial(List<Fraction> fractions) {
        init();
        return arithmeticMult.solve(fractions);
    }

    public MathResult sequencial(Fraction... fractions) {
        return sequencial(List.of(fractions));
    }

    public MathResult sequencial(String expression){
        init();
        expression = FractionUtil.gameSignal(expression);
        if(!expression.matches(ArithmeticRegex.MULT_DIV)) 
           return MathResult.builder().build();
        if(expression.matches(".*\\+.*")) expression = expression.replace("+", "");
        long numSignlSub = 0;
        if(expression.matches(".*\\-.*")){
            numSignlSub = expression.chars().filter(e-> e == '-').count();
            expression = expression.replace("-", "");
        }
        List<Fraction> fractions = ArithmeticUtil.parseListSignalGroup(expression,"[\\*|:]", true);
        if(numSignlSub % 2 != 0) fractions.set(0, fractions.get(0).negative() );
        return arithmeticMult.solve(fractions);
    }
    
    public MathResult priorityMult(String expression){
        return priority(expression, "mult");
    }
    
    public MathResult priorityDiv(String expression){
        return priority(expression, "div");
    }    
        
    private MathResult priority(String expression, String type){
        init();
        expression = FractionUtil.gameSignal(expression);
        long start = System.currentTimeMillis();
        
        if(expression.matches(ArithmeticRegex.MULT)) return arithmeticMult.solve(expression);
        if(expression.matches(ArithmeticRegex.DIV)) return arithmeticDiv.solve(expression);
        
        if(!expression.matches(ArithmeticRegex.MULT_DIV)) 
            return MathResult.builder().build();
        if(expression.matches(".*\\+.*")) expression = expression.replace("+", "");
        
        long numSignlSub = 0;
 
        if(expression.matches(".*\\-.*")){
            numSignlSub = expression.chars().filter(e-> e == '-').count();
            expression = expression.replace("-", "");
        }
        
       if( numSignlSub % 2 !=  0) expression = "-"+expression;
       
       List<Step> list = new ArrayList<>();
       String[] multOpers = expression.split(type.equals("mult")? ":" : "\\*");
       int tam = multOpers.length;
       String text;
       String rgx = type.equals("mult") ? ArithmeticRegex.MULT : ArithmeticRegex.DIV;
        for (int i = 0; i < tam; i++) {
           mathResult=type.equals("mult")?arithmeticMult.solve(multOpers[i]):arithmeticDiv.solve(multOpers[i]);
           fractionResult = mathResult.fractionResult();
           if(multOpers[i].matches(rgx)){
            text = fractionResult.getFraction().text();
            expression = expression.replace(multOpers[i],text );
            list.add(ArithmeticFormatter.expressionOperation(expression,"[\\*|:]",i+1));
            stepGroups.add(StepMap.of(fractionResult.getExpression(), fractionResult.getSteps()));
           }
        }         
       mathResult=type.equals("mult")?arithmeticDiv.solve(expression):arithmeticMult.solve(expression);
       fractionResult = mathResult.fractionResult();
       tam = fractionResult.getSteps().size();
       list.addAll(fractionResult.getSteps().subList(1, tam));
       long end = System.currentTimeMillis();
       return MathResultUtil.finishOperation(stepGroups,list,fractionResult.getFraction(), this, start, end);
    }
    
    
    @Override
    public MathResult solve(String expression, String method) {
        init();
        expression = FractionUtil.gameSignal(expression);
        if(!expression.matches(ArithmeticRegex.MULT_DIV)) 
          return MathResult.builder().build();
        if(expression.charAt(0) == '+') expression = expression.substring(1);
        
        if(method.equals(METHOD_SEQUENCIAL)) return sequencial(expression);
        if(method.equals(METHOD_PRIORITY_MULT)) return priorityMult(expression);
        if(method.equals(METHOD_PRIORITY_DIV)) return priorityDiv(expression);
        
        boolean par = ThreadLocalRandom.current().nextInt(1, 10) % 2 == 0;
        return par ? sequencial(expression) : priorityMult(expression);
    }
    
    @Override
    public MathResult solve(String expression) {
        return solve(expression,method);
    }    

    
    public MathResult solve(List<Fraction> fractions) {
        return sequencial(fractions);
    }

    
    public MathResult solve(Fraction... fractions) {
        return solve(List.of(fractions));
    }

    @Override
    public MathResult solve(ArithmeticParams params) {
        setArithmeticParams(arithmeticParams);
        return solve(params.getExpression());
    }

}
