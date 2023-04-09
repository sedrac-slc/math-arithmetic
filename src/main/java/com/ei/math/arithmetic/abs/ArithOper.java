package com.ei.math.arithmetic.abs;

import com.ei.math.MathResult;
import com.ei.math.arithmetic.params.ArithmeticParams;
import com.ei.math.MathOper;

public interface ArithOper extends MathOper{
    MathResult solve(ArithmeticParams params);
}
