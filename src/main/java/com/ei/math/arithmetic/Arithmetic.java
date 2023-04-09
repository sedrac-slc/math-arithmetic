package com.ei.math.arithmetic;

import com.ei.math.MathResult;
import com.ei.math.arithmetic.abs.ArithOper;
import com.ei.math.arithmetic.operator.ArithmeticGroup;
import com.ei.math.arithmetic.params.ArithmeticParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Arithmetic implements ArithOper{
   private String expression;
   
   private ArithmeticGroup arithmeticGroup;
   
   {
       arithmeticGroup = new ArithmeticGroup();
   }

    @Override
    public MathResult solve(ArithmeticParams params) {
        return arithmeticGroup.solve(params); 
    }

    @Override
    public MathResult solve(String expression) {
        return arithmeticGroup.solve(expression); 
    }

    @Override
    public MathResult solve(String expression, String method) {
        return arithmeticGroup.solve(expression, method);
    }
   
   
   
}
