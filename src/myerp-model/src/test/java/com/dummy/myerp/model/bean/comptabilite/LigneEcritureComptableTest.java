package com.dummy.myerp.model.bean.comptabilite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LigneEcritureComptableTest {

    private CompteComptable compte = new CompteComptable(215,"TEST");
    private LigneEcritureComptable ligne = new LigneEcritureComptable( compte, "Test",
            null,null);

    @Test
    public void testToStringOK()
    {
        Assertions.assertEquals(ligne.toString(), "LigneEcritureComptable{compteComptable=CompteComptable{numero=215, libelle='TEST'}, libelle='Test', debit=null, credit=null}");}

    @Test
    public void testToStringNok()
    {

        Assertions.assertNotEquals(ligne.toString(), "Ceci est un test de ToString ! ");
    }
}
