package com.ei.math.arithmetic.basic;

import com.ei.math.MathResult;
import com.ei.math.StepMap;
import com.ei.math.arithmetic.ArithmeticRegex;
import com.ei.math.arithmetic.abs.ArithOperaction;
import com.ei.math.arithmetic.params.ArithmeticParams;
import com.ei.math.arithmetic.registory.ArithmeticMessage;
import com.ei.math.arithmetic.text.ArithmeticFormatter;
import com.ei.math.arithmetic.util.ArithmeticPartStepMethos;
import com.ei.math.arithmetic.util.ArithmeticUtil;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.FractionConverter;
import com.ei.math.fraction.operation.FractionMult;
import com.ei.math.fraction.util.FractionUtil;
import com.ei.math.util.MathResultUtil;
import java.util.List;
import java.util.stream.Collectors;
import com.ei.math.arithmetic.abs.ArithListOper;

public class ArithmeticMult extends ArithOperaction implements ArithListOper{
    public final static String METHOD_SEQUENCIAL = "sequencial";
    
    private final FractionMult fractionMult;
    
    {
        fractionMult = new FractionMult();
    }

    public MathResult sequencial(List<Fraction> fractions) {
        init();
        long start = System.currentTimeMillis();
        int tam = fractions.size(); 
        if(tam == 0) return MathResult.builder().build();
        if(tam == 1) return ArithmeticPartStepMethos.of(fractions.get(0));
        if(tam == 2) 
            return fractionMult.solve(fractions.get(0), fractions.get(1),arithmeticParams.getMethodFractionMult());
        
        long count = fractions.stream().filter(Fraction::isNegative).count();
        fractions = fractions.stream().map(Fraction::positive).collect(Collectors.toList());
        if(count % 2 != 0)
            fractions.set(0, fractions.get(0).negative() );
        
        steps.add(ArithmeticFormatter.startMultOrDiv(fractions,"*"));
        mathResult = fractionMult.solve(fractions.get(0), fractions.get(1),arithmeticParams.getMethodFractionMult());
        fractionResult = mathResult.fractionResult();
        
        fractions = fractions.subList(2, tam);
        tam = fractions.size();
        
        fractionsOper = ArithmeticUtil.join(fractionResult.getFraction(), fractions);
        stepGroups.add(StepMap.of(fractionResult.getExpression(), fractionResult.getSteps()));
        Fraction fraction = fractionResult.getFraction().simplify();
        String operacao;
        for (int i = 0; i < tam; i++) {
            fractionsOper = ArithmeticUtil.join(fraction, fractions.subList(i, tam));
            mathResult = fractionMult.solve(fraction, fractions.get(i), arithmeticParams.getMethodFractionMult());
            fractionResult = mathResult.fractionResult();
            operacao = String.format("%s*%s=", fraction,fractions.get(i));
            fraction = fractionResult.getFraction().simplify();
            operacao += fraction.text();
            if(i+1 < tam){
               steps.add(ArithmeticFormatter.stepSequencial(fractionsOper,operacao,i+2,"*"));
               stepGroups.add(StepMap.of(fractionResult.getExpression(), fractionResult.getSteps()));
            }
        }
        fractionResult.getSteps().get(0).setMessage(ArithmeticMessage.get("step.arith.seq.finish"));
        steps.addAll(fractionResult.getSteps());
        long end = System.currentTimeMillis();
        return MathResultUtil.finishOperation(stepGroups,steps, fraction, this, start, end);
    }

    public MathResult sequencial(Fraction... fractions) {
        return sequencial(List.of(fractions));
    }

    @Override
    public MathResult solve(String expression, String method) {
        init();
        expression = FractionUtil.gameSignal(expression);
        if(expression.matches(ArithmeticRegex.FRACTION))
            return ArithmeticPartStepMethos.of(FractionConverter.parse(expression));   
        if(!expression.matches(ArithmeticRegex.MULT)) 
           return MathResult.builder().build();
        if(expression.charAt(0) == '+') expression = expression.substring(1);
        
        fractionsOper = ArithmeticUtil.parseListFraction(expression, "\\*");
        return sequencial(fractionsOper);
    }
    
    @Override
    public MathResult solve(String expression) {
        return solve(expression,arithmeticParams.getMethodArithmeticMult());
    }    

    public MathResult solve(List<Fraction> fractions) {
        return sequencial(fractions);
    }

    public MathResult solve(Fraction... fractions) {
        return solve(List.of(fractions));
    }

    @Override
    public MathResult solve(ArithmeticParams params) {
        setArithmeticParams(params);
        return solve(params.getExpression()); 
    }
    
    
}
