package com.ei.math.arithmetic.operator;

import com.ei.math.arithmetic.abs.ArithOperaction;
import com.ei.math.MathResult;
import com.ei.math.arithmetic.basic.ArithmeticSum;
import com.ei.math.arithmetic.basic.ArithmeticSub;
import com.ei.math.Step;
import com.ei.math.StepMap;
import com.ei.math.arithmetic.ArithmeticRegex;
import com.ei.math.arithmetic.params.ArithmeticParams;
import com.ei.math.arithmetic.text.ArithmeticFormatter;
import com.ei.math.arithmetic.util.ArithmeticPartStepMethos;
import com.ei.math.arithmetic.util.ArithmeticUtil;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.FractionConverter;
import com.ei.math.fraction.FractionResult;
import com.ei.math.fraction.util.FractionUtil;
import com.ei.math.util.MathResultUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import com.ei.math.arithmetic.abs.ArithListOper;

public class ArithmeticSumOrSub extends ArithOperaction implements ArithListOper{
    private final ArithmeticSum arithmeticSum;
    private final ArithmeticSub arithmeticSub;
    
    public final static String METHOD_GROUPING = "grouping";
    public final static String METHOD_MMC = "mmc";
    public final static String METHOD_SEQUENCIAL = "sequencial";
    public final static String METHOD_RANDOM = "random";
    
    @Getter @Setter
    private String method = METHOD_RANDOM;
   
    
    {
        arithmeticSum = new ArithmeticSum();
        arithmeticSub = new ArithmeticSub();
    }
    
    public MathResult grouping(List<Fraction> fractions){
        init();
        long start = System.currentTimeMillis();
        int tam = fractions.size();
        
        if(tam == 0) return MathResult.builder().build();
        if(tam == 1) return ArithmeticPartStepMethos.of(fractions.get(0));
        final long den = fractions.get(0).getDenominator();
        boolean denEquals = fractions.stream().allMatch(frac -> frac.getDenominator().equals(den));
        if(denEquals) return ArithmeticUtil.commun(fractions);
        
        arithmeticSum.setArithmeticParams(arithmeticParams);
        arithmeticSub.setArithmeticParams(arithmeticParams);
        
        
        if(fractions.stream().allMatch(Fraction::isPositive)) return arithmeticSum.solve(fractions);
        if(fractions.stream().allMatch(Fraction::isNegative)) return  arithmeticSub.solve(fractions);
        
        Map<Boolean, List<Fraction>> mapGroup = fractions.stream().collect(Collectors.groupingBy(Fraction::isPositive));
       
        int tamPositive = mapGroup.get(true).size();
        int tamNegative = mapGroup.get(false).size();
       
        if(tamPositive == tamNegative  && tamPositive == 1)
            return arithmeticSum.solve(mapGroup.get(true).get(0),mapGroup.get(false).get(0));
        
        mathResult =  arithmeticSum.solve(mapGroup.get(true));
        FractionResult solveArithmeticSum = mathResult.fractionResult();
        stepGroups.add(StepMap.of(solveArithmeticSum.getExpression(), solveArithmeticSum.getSteps()));
        
        mathResult = arithmeticSub.solve(mapGroup.get(false));
        FractionResult solveArithmeticSub  = mathResult.fractionResult();
        stepGroups.add(StepMap.of(solveArithmeticSub.getExpression(), solveArithmeticSub.getSteps()));
        
        int tamStepSum = solveArithmeticSum.getSteps().size(); 
        int tamStepSub = solveArithmeticSub.getSteps().size();
        int tamStepMax = tamStepSum > tamStepSub ? tamStepSum : tamStepSub;
        
        Step step;
        List<Step> listSteps = new ArrayList<>();
        
        int posSum = 0, posSub = 0;
        for (int i = 0; i < tamStepMax ; i++) {
            if(i < tamStepSum && i < tamStepSub){ posSum = i; posSub = i; }
            if(i < tamStepSum && i >= tamStepSub){ posSum = i; posSub = tamStepSub-1; }
            if(i >= tamStepSum && i < tamStepSub){ posSum = tamStepSum-1; posSub = i; }
             step = ArithmeticFormatter.grouping(
                     solveArithmeticSum.getSteps().get(posSum),
                     solveArithmeticSub.getSteps().get(posSub),"+", i
             );
            listSteps.add(step);
        }
        
        FractionResult solve = arithmeticSum.solve(
                solveArithmeticSum.getFraction(),
                solveArithmeticSub.getFraction()
        ).fractionResult();
        
        tamStepMax = solve.getSteps().size();
        
        listSteps.addAll(solve.getSteps().subList(1, tamStepMax));
        long end = System.currentTimeMillis();
        return MathResultUtil.finishOperation(stepGroups,listSteps, solve.getFraction(), this, start, end);
    }
    
    public MathResult grouping(Fraction... fractions){
        return grouping(List.of(fractions));
    }
  
    public MathResult sequencial(List<Fraction> fractions) {
        arithmeticSum.setArithmeticParams(arithmeticParams);
        arithmeticSub.setArithmeticParams(arithmeticParams);
        if(fractions.stream().allMatch(Fraction::isNegative)) return  arithmeticSub.sequencial(fractions); 
        return arithmeticSum.sequencial(fractions);
    }
    
    public MathResult minMultiploCommon(Fraction... fractions){
        return minMultiploCommon(List.of(fractions));
    }
  
    public MathResult minMultiploCommon(List<Fraction> fractions) {        
        arithmeticSum.setArithmeticParams(arithmeticParams);
        arithmeticSub.setArithmeticParams(arithmeticParams);
        if(fractions.stream().allMatch(Fraction::isNegative)) 
            return  arithmeticSub.minMultiploCommon(fractions); 
        return arithmeticSum.minMultiploCommon(fractions);
    }    
    
    public MathResult sequencial(Fraction... fractions) {
        return sequencial(List.of(fractions));
    }

    @Override
    public MathResult solve(String expression) {
        return solve(expression,method);
    }

    @Override
    public MathResult solve(String expression, String method) {
        arithmeticParams.setMethodArithmeticSumOrSub(method);
        expression = FractionUtil.gameSignal(expression);
        if(expression.matches(ArithmeticRegex.FRACTION))
            return ArithmeticPartStepMethos.of(FractionConverter.parse(expression));
        if(!expression.matches(ArithmeticRegex.SUM_SUB)) 
           return MathResult.builder().build();
        List<Fraction> parseListSignalGroup = ArithmeticUtil.parseListSignalGroup(expression,"[\\+|\\-]");
        return chooseMethod(parseListSignalGroup, method); 
    }

    @Override
    public MathResult solve(List<Fraction> fractions) {
        return chooseMethod(fractions, arithmeticParams.getMethodArithmeticSumOrSub()); 
    }

   
    @Override
    public MathResult solve(Fraction... fractions) {
        return solve(List.of(fractions)); 
    }
    
    private MathResult chooseMethod(List<Fraction> fractions, String method) {
       switch(method){
            case METHOD_SEQUENCIAL:
                return sequencial(fractions);
            case METHOD_MMC:
                return  minMultiploCommon(fractions);
            case METHOD_GROUPING:
                return  grouping(fractions);
            default:
                int num = ThreadLocalRandom.current().nextInt(1, 30);
                if(num < 10) return sequencial(fractions);
                if(num < 20) return minMultiploCommon(fractions);
                return grouping(fractions);
      }
    }

    @Override
    public MathResult solve(ArithmeticParams params) {
        setArithmeticParams(arithmeticParams);
        return solve(params.getExpression());
    }

    
}
