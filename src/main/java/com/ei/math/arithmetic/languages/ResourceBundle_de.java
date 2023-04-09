package com.ei.math.arithmetic.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_de extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"lang.en", "Englisch"},
            {"lnag.de", "Deutsch"},
            {"lang.es", "Spanisch"},
            {"lang.fr", "Französisch"},
            {"lang.pt", "Portugiesisch"},
            {"lang.ru", "Russisch"},
            {"lang.it", "Italienisch"},
            {"lang.jp", "japanisch"},
            {"lang.cn", "Chinesisch"},
            {"step.arith.start", "Anfangsausdruck"},
            {"mmc", "kleinstes gemeinsames Vielfaches"},
            {"step.arith.seq.finish", "letzter Ausdruck in der Sequenz"},
            {"step.arith.seq", "Operation führt aus (%s), Fraktionsliste %s"},
            {"step.arith.cross.one", "Finden des kleinsten gemeinsamen Vielfachen der Nenner %s"},
            {"step.arith.cross.two", "schreibe die Brüche basierend auf dem gefundenen kleinsten gemeinsamen Vielfachen (mmc=%s)"},
            {"step.arith.cross.three", "solve(%s) die Operation zwischen den Zählern, der Nenner ist mmc(%s)"},
            {"step.arith.cross.four", "Das Ergebnis der Operation ist der neue Zähler und der Nenner ist das kleinste gemeinsame Vielfache"},
            {"step.arith.irreducible", "irreduzibler Bruch"},
            {"step.arith.simplify", "Vereinfachung von Brüchen durch den größten gemeinsamen Teiler (%s)"},
            {"step.arith.group", "Gruppierung zwischen Additions(%s)- und Subtraktions(%s)-Operationen"},
            {"step.arith.eq.oper", ""}
        };
    }

}
