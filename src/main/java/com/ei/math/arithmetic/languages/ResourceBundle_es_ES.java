package com.ei.math.arithmetic.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_es_ES extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"lang.en", "inglés"},
            {"lnag.de", "Alemán"},
            {"lang.es", "español"},
            {"lang.fr", "Francés"},
            {"lang.pt", "portugués"},
            {"lang.ru", "ruso"},
            {"lang.it", "italiano"},
            {"lang.jp", "japonés"},
            {"lang.cn", "Chino"},
            {"step.arith.start", "expresión inicial"},
            {"mmc", "minimo común multiplo"},
            {"step.arith.seq.finish", "expresión final en la secuencia"},
            {"step.arith.seq", "operación realizada (%s), lista de fracciones %s"},
            {"step.arith.cross.one", "encontrar el mínimo común múltiplo de los denominadores %s"},
            {"step.arith.cross.two", "escribe las fracciones basadas en el mínimo común múltiplo (mmc=%s) encontrado"},
            {"step.arith.cross.three", "solve(%s) la operación entre los numeradores, el denominador es mmc(%s)"},
            {"step.arith.cross.four", "el resultado de la operación será el nuevo numerador y el denominador es el mínimo común múltiplo"},
            {"step.arith.irreducible", "fracción irreducible"},
            {"step.arith.simplify", "simplificación de fracciones por el máximo común divisor (%s)"},
            {"step.arith.group", "agrupación entre operaciones de suma(%s) y resta(%s)"},
            {"step.arith.eq.oper", ""}
        };
    }

}
