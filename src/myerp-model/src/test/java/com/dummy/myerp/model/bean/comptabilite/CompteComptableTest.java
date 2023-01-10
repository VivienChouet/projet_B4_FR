package com.dummy.myerp.model.bean.comptabilite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CompteComptableTest {

    private CompteComptable compteComptableTest = new CompteComptable(215553,"TEST");

    @Test
    public void testToStringOk()
    {
        Assertions.assertEquals(compteComptableTest.toString(), "CompteComptable{numero=215553, libelle='TEST'}");
    }

    @Test
    public void testToStringNok()
    {

        Assertions.assertNotEquals(compteComptableTest.toString(), "Ceci est un test de ToString Nok !");
    }

    @Test
    public void getByNumeroOk(){
        List<CompteComptable> liste = new ArrayList<CompteComptable>();
        liste.add(compteComptableTest);
        CompteComptable c = CompteComptable.getByNumero(liste,215553);
        Assertions.assertEquals(c, compteComptableTest);
    }

    @Test
    public void getByNumeroNok(){
        List<CompteComptable> liste = new ArrayList<CompteComptable>();
        liste.add(compteComptableTest);
        CompteComptable c = CompteComptable.getByNumero(liste,45632);
        Assertions.assertNotEquals(c, compteComptableTest);
    }

}
