package com.dummy.myerp.model.bean.comptabilite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JournalComptableTest {

    private JournalComptable journal = new JournalComptable("CT","CompteTest");

    @Test
    public void testToStringOK()
    {
        Assertions.assertEquals(journal.toString(), "JournalComptable{code='CT', libelle='CompteTest'}");
    }

    @Test
    public void testToStringNok()
    {

        Assertions.assertNotEquals(journal.toString(), "Ceci est un test de ToString !");
    }

    @Test
    public void getByCodeOK(){
        List<JournalComptable> liste = new ArrayList<>();
        liste.add(journal);
        JournalComptable j = JournalComptable.getByCode(liste,"CT");
        Assertions.assertEquals(j,journal);
    }

    @Test
    public void getByCodeNok(){
        List<JournalComptable> liste = new ArrayList<>();
        liste.add(journal);
        JournalComptable j = JournalComptable.getByCode(liste,"HTC");
        Assertions.assertNotEquals(j,journal);
    }

}
