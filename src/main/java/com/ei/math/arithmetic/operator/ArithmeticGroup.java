package com.ei.math.arithmetic.operator;

import com.ei.math.MathResult;
import com.ei.math.StepMap;
import com.ei.math.arithmetic.ArithmeticRegex;
import com.ei.math.arithmetic.abs.ArithOperaction;
import com.ei.math.arithmetic.params.ArithmeticParams;
import com.ei.math.arithmetic.util.ArithmeticUtil;
import com.ei.math.fraction.util.FractionUtil;
import com.ei.math.util.MathResultUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithmeticGroup extends ArithOperaction{
    private final ArithmeticOper arithmeticOper;
    protected List<StepMap> stepGroupsPrep;
    
    {
        
        stepGroupsPrep = new ArrayList<>();
        arithmeticOper = new ArithmeticOper();
    }
    
    private String prepare(String expression){
        expression = expression.replaceAll("\\)\\(",")*(");
        expression = expression.replaceAll("\\)/\\(","):(");
        expression = expression.replaceAll("\\)/","):");
        expression = expression.replaceAll("/\\(",":(");
        
        expression = expression.replaceAll("::+",":");
        
        String rgx1 = "\\d+\\(",rgx2 = "\\)\\d+";
        Pattern pattern = Pattern.compile("("+rgx1+"|"+rgx2+")");
        Matcher matcher = pattern.matcher(expression);
        String group, replace;
        while(matcher.find()){  
           group = matcher.group();
           if(group.matches(rgx1)) {
              replace = group.replace("(", "*(");
              expression = expression.replace(group, replace);
           }
            if(group.matches(rgx2)) {
              replace = group.replace(")", ")*");
              expression = expression.replace(group, replace);
           } 
        }
        
        return expression;
    }
    
    private String resolve(String expression){
        int tam;
        String group;
        expression = prepare(expression);        
        while(expression.matches(".*"+ArithmeticRegex.GROUP+".*")){
            Pattern pattern = Pattern.compile(ArithmeticRegex.GROUP);
            Matcher matcher = pattern.matcher(expression);
            while(matcher.find()){
                tam = matcher.group().length();
                group = matcher.group().substring(1, tam-1);
                mathResult = arithmeticOper.solve(group);
                fractionResult = mathResult.fractionResult();
                stepGroupsPrep.add(StepMap.of(group, fractionResult.getSteps()));
                expression = expression.replace(matcher.group(), fractionResult.getFraction().text());
                expression = FractionUtil.gameSignal(expression);
            }
        }
        return expression;
    }

    @Override
    public MathResult solve(String expression) {
       return solve(expression,""); 
    }

    @Override
    public MathResult solve(String expression, String method) {
        
        long start = System.currentTimeMillis();
        expression = FractionUtil.gameSignal(expression);
         
        arithmeticOper.setArithmeticParams(arithmeticParams);

        if(expression.matches(ArithmeticRegex.OPERATION)) return  arithmeticOper.solve(expression);
        if(expression.matches(".*"+ArithmeticRegex.MULT_DIV_SIG+".*"))
            expression = ArithmeticUtil.gameSignalMultOrDiv(expression);
       
        if(expression.matches(".*"+ArithmeticRegex.GROUP+".*")) 
            expression = resolve(expression);
        
        mathResult = arithmeticOper.solve(expression);
        fractionResult = mathResult.fractionResult();
        stepGroupsPrep.addAll(mathResult.getStepGroups());
        long end = System.currentTimeMillis();
        return MathResultUtil.finishOperation(stepGroupsPrep, fractionResult.getSteps(), fractionResult.getFraction(), this, start, end);
    }

    @Override
    public MathResult solve(ArithmeticParams params) {
        setArithmeticParams(params);
        return solve(params.getExpression());
    }
   
   
}
