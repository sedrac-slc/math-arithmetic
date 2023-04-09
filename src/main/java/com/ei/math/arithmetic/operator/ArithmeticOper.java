package com.ei.math.arithmetic.operator;

import com.ei.math.arithmetic.abs.ArithOperaction;
import com.ei.math.MathResult;
import com.ei.math.StepMap;
import com.ei.math.arithmetic.ArithmeticRegex;
import com.ei.math.arithmetic.params.ArithmeticParams;
import com.ei.math.arithmetic.text.ArithmeticFormatter;
import com.ei.math.arithmetic.util.ArithmeticUtil;
import com.ei.math.fraction.util.FractionUtil;
import com.ei.math.util.MathResultUtil;
import java.util.List;

public class ArithmeticOper extends ArithOperaction {    
    private final ArithmeticSumOrSub arithmeticSumOrSub;
    private final ArithmeticMultOrDiv arithmeticMultOrDiv;
    
    {
        arithmeticSumOrSub = new ArithmeticSumOrSub();
        arithmeticMultOrDiv = new ArithmeticMultOrDiv();
    }
    
    @Override
    public MathResult solve(String expression) {
        long start = System.currentTimeMillis();
        expression = FractionUtil.gameSignal(expression);
       
        if(expression.matches(".*"+ArithmeticRegex.MULT_DIV_SIG+".*"))
            expression = ArithmeticUtil.gameSignalMultOrDiv(expression);
        
        arithmeticSumOrSub.setArithmeticParams(arithmeticParams);
        arithmeticMultOrDiv.setArithmeticParams(arithmeticParams);
        
        if(expression.matches(ArithmeticRegex.SUM_SUB)) return arithmeticSumOrSub.solve(expression);
        if(expression.matches(ArithmeticRegex.MULT_DIV)) return arithmeticMultOrDiv.solve(expression);
        
        if(expression.matches(ArithmeticRegex.OPERATION)){
            if(expression.charAt(0) == '+') expression = expression.substring(1);
            List<String> list = ArithmeticUtil.multOrDivOperaction(expression);
            steps.add(ArithmeticFormatter.expressionOperation(expression,"[\\+|\\-|\\*|:]",0));
            int tam = list.size();
            for(int i = 0; i < tam; i++){
                String key = list.get(i);
                mathResult  = arithmeticMultOrDiv.solve(key);
                fractionResult = mathResult.fractionResult();
                stepGroups.add(StepMap.of(fractionResult.getExpression(), fractionResult.getSteps()));
                expression = expression.replace(key,fractionResult.getFraction().text());
                if(i < tam -1)
                    steps.add(ArithmeticFormatter.expressionOperation(expression,"[\\+|\\-|\\*|:]",i+1));
            }
        }   
        mathResult = arithmeticSumOrSub.solve(expression);
        fractionResult = mathResult.fractionResult();
        steps.addAll(fractionResult.getSteps());
        long end = System.currentTimeMillis();
        return MathResultUtil.finishOperation(stepGroups,steps,fractionResult.getFraction(), this, start, end);
    }
    
    @Override
    public MathResult solve(ArithmeticParams params){
        setArithmeticParams(arithmeticParams);
        return solve(params.getExpression());
    }
    
    @Override
    public MathResult solve(String expression, String method) {
        return solve(expression);
    }    
    
      

}
