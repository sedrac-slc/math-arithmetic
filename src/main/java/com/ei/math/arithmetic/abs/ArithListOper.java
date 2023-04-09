package com.ei.math.arithmetic.abs;

import com.ei.math.MathResult;
import com.ei.math.fraction.Fraction;
import java.util.List;

public interface ArithListOper extends ArithOper{
    public MathResult solve(Fraction... fractions);
    public MathResult solve(List<Fraction> fractions);
}
