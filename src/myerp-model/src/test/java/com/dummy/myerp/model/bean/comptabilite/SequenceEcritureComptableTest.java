package com.dummy.myerp.model.bean.comptabilite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SequenceEcritureComptableTest {

    private SequenceEcritureComptable sequence = new SequenceEcritureComptable(2023, 11111, "CT");

    @Test
    public void testToStringOK()
    {
        Assertions.assertEquals(sequence.toString(), "SequenceEcritureComptable{annee=2023, derniereValeur=11111}");
    }

    @Test
    public void testToStringNoK()
    {

        Assertions.assertNotEquals(sequence.toString(), "Ceci est un test de ToString ! ");
    }

}
