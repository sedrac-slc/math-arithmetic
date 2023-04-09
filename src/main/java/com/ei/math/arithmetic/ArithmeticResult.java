package com.ei.math.arithmetic;

import com.ei.math.fraction.FractionResult;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArithmeticResult {
    private String expression;
    private FractionResult fractionResult = new FractionResult();
    private Map<String, FractionResult> map = new HashMap<>();

    public ArithmeticResult(String expression, FractionResult fractionResult) {
        this.expression = expression;
        this.fractionResult = fractionResult;
    }
     
    
    
}
