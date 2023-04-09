package com.ei.math.arithmetic.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_fr_FR extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"lang.en", "Anglais"},
            {"lnag.de", "Allemand"},
            {"lang.es", "Espagnol"},
            {"lang.fr", "Français"},
            {"lang.pt", "Portugais"},
            {"lang.ru", "russe"},
            {"lang.it", "italien"},
            {"lang.jp", "Japonais"},
            {"lang.cn", "Chinois"},
            {"step.arith.start", "expression initiale"},
            {"mmc", "multiple moins commun"},
            {"step.arith.seq.finish", "expression finale dans la séquence"},
            {"step.arith.seq", "opération effectuée (%s), liste de fractions %s"},
            {"step.arith.cross.one", "trouver le plus petit commun multiple de dénominateurs %s"},
            {"step.arith.cross.two", "écrire les fractions en fonction du plus petit commun multiple (mmc=%s) trouvé"},
            {"step.arith.cross.three", "résoudre(%s) l'opération entre les numérateurs, le dénominateur est mmc(%s)"},
            {"step.arith.cross.four", "le résultat de l'opération (%s) sera le nouveau numérateur et le dénominateur est le plus petit commun multiple"},
            {"step.arith.irreducible", "fraction irréductible"},
            {"step.arith.simplify", "simplification de fractions par le plus grand diviseur commun (%s)"},
            {"step.arith.group", "regroupement entre les opérations d'addition(%s) et de soustraction(%s)"},
            {"step.arith.eq.oper", ""}
        };
    }

}
