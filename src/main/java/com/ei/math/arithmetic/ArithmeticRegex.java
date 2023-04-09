package com.ei.math.arithmetic;
/**
 * {@code FractionRegex} is a class that contains regular expressions to 
 * validate whether a fraction or operation between fraction.
 * 
 * @author  Sedrac Lucas Calupeteca
 * @since   1.0
 */
public final class ArithmeticRegex {
    public static final String FRACTION = "\\d+(\\.\\d+)?(/\\d+(\\.\\d+)?)?";

    public static final String SUM = FRACTION+"(\\+"+FRACTION+")+";
    public static final String SUB = FRACTION+"(\\-"+FRACTION+")+";
    public static final String SUM_SUB = "[\\+|\\-]?"+FRACTION+"([\\+|\\-]"+FRACTION+")+";
    
    
    public static final String MULT = "[\\+|\\-]?"+FRACTION+"(\\*[\\+|\\-]?"+FRACTION+")+";
    public static final String DIV = "[\\+|\\-]?"+FRACTION+"(:[\\+|\\-]?"+FRACTION+")+";
    public static final String MULT_DIV = "[\\+|\\-]?"+FRACTION+"([\\*|:][\\+|\\-]?"+FRACTION+")+";
    
    public static final String OPERATION = "[\\+|\\-]?"+FRACTION+"([\\+|\\-|\\*|:]"+FRACTION+")+";
    
    public static final String GROUP = "\\("+OPERATION+"\\)";
    
    public static final String MULT_DIV_SIG = FRACTION+"([\\*|:][\\+|\\-]"+FRACTION+")+";
    
}
