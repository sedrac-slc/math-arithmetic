package com.ei.math.arithmetic.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_pt_PT extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"lang.en", "Inglês"},
            {"lnag.de", "Alemão"},
            {"lang.es", "Espanhol"},
            {"lang.fr", "Frânces"},
            {"lang.pt", "Português"},
            {"lang.ru", "Russo"},
            {"lang.it", "Italino"},
            {"lang.jp", "Janponês"},
            {"lang.cn", "Chinês"},
            {"mmc", "mínimo múltiplo comum"},
            {"step.arith.start", "expressão inicial"},
            {"step.arith.seq.finish", "expressão final na sequencia"},
            {"step.arith.seq", "opereção realiza (%s), lista de fração %s"},
            {"step.arith.cross.one", "achando o mínimo múltiplo comum entre os denominadores %s"},
            {"step.arith.cross.two", "escrever a frações com base no mínimo múltiplo comum (mmc=%s) encontrado"},
            {"step.arith.cross.three", "resolver(%s) a opereção entre os numeradores, o denominador é o mmc(%s)"},
            {"step.arith.cross.four", "resultado da operação  será o novo numerador e denominador é o mínimo múltiplo comum"},
            {"step.arith.irreducible", "fração irredutivel"},
            {"step.arith.simplify", "simplificação da fração pelo máximo divisor comum (%s)"},
            {"step.arith.group", "agrupamento entre operações de adição(%s) e subtração(%s)"},
            {"step.arith.eq.oper", "efectuar a operação %s"}
        };
    }

}
