package com.ei.math.arithmetic.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_it extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"lang.en", "inglese"},
            {"lnag.de", "Tedesco"},
            {"lang.es", "spagnolo"},
            {"lang.fr", "francese"},
            {"lang.pt", "portoghese"},
            {"lang.ru", "russo"},
            {"lang.it", "italiano"},
            {"lang.jp", "giapponese"},
            {"lang.cn", "Cinese"},
            {"step.arith.start", "espressione iniziale"},
            {"mmc", "minimo comune multiplo"},
            {"step.arith.seq.finish", "espressione finale nella sequenza"},
            {"step.arith.seq", "operazione eseguita (%s), elenco di frazioni %s"},
            {"step.arith.cross.one", "trovare il minimo comune multiplo del denominatore %s"},
            {"step.arith.cross.three", "solve l'operazione tra i numeratori, il denominatore è mmc(%s)"},
            {"step.arith.cross.four", "risultato(%s) dell'operazione (%s) sarà il nuovo numeratore e denominatore è il minimo comune multiplo"},
            {"step.arith.cross.two", "scrivere le frazioni in base al minimo comune multiplo (mmc=%s) trovato"},
            {"step.arith.irreducible", "frazione irriducibile"},
            {"step.arith.simplify", "semplificazione delle frazioni per il massimo comun divisore (%s)"},
            {"step.arith.group", "raggruppamento tra operazioni di addizione(%s) e sottrazione(%s)"},
            {"step.arith.eq.oper", ""}
        };
    }

}
