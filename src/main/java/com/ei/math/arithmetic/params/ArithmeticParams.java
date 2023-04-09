package com.ei.math.arithmetic.params;

import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ArithmeticParams {
     @Builder.Default
     private String lang = Locale.getDefault().getISO3Language();
     private String expression;
     @Builder.Default
     private String methodFractionSum = "random";
     @Builder.Default
     private String methodFractionSub = "random";
     @Builder.Default
     private String methodFractionMult = "random";
     @Builder.Default
     private String methodFractionDiv = "random";     
     @Builder.Default
     private String methodArithmeticSum = "random";
     @Builder.Default
     private String methodArithmeticSub = "random";
     @Builder.Default
     private String methodArithmeticDiv = "random";
     @Builder.Default
     private String methodArithmeticMult = "random";     
     @Builder.Default
     private String methodArithmeticSumOrSub = "random";
     @Builder.Default
     private String methodArithmeticMultOrDiv = "random";
}
