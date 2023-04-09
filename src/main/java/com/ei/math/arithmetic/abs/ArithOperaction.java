package com.ei.math.arithmetic.abs;

import com.ei.math.StepMap;
import com.ei.math.Step;
import com.ei.math.arithmetic.text.ArithmeticFormatter;
import com.ei.math.fraction.Fraction;
import com.ei.math.MathResult;
import com.ei.math.arithmetic.params.ArithmeticParams;
import com.ei.math.fraction.FractionResult;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public abstract class ArithOperaction implements ArithOper{
    protected List<Step> steps;

    protected ArithmeticFormatter formatter;
    
    protected List<Fraction> fractionsOper;
    
    protected FractionResult fractionResult;
    
    protected List<StepMap> stepGroups;
    
    protected MathResult mathResult;
    
    @Getter @Setter
    protected ArithmeticParams arithmeticParams;    
    
    {
        init();
        arithmeticParams = new ArithmeticParams();
    }
    
    protected void init(){
        steps = new ArrayList<>();
        stepGroups = new ArrayList<>();
        fractionsOper = new ArrayList<>();
        fractionResult = new FractionResult();
        formatter = new ArithmeticFormatter();
    }
   
    
}
