package com.dummy.myerp.model.bean.comptabilite;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;



public class EcritureComptableTest {



   private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        LigneEcritureComptable vRetour = new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                vLibelle,
                vDebit, vCredit);
        return vRetour;
    }

    @Test
    public void isEquilibree() {
        EcritureComptable vEcriture = new EcritureComptable();
        vEcriture.setLibelle("Equilibree");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "323", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "56", "353"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "23"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "86", "89"));
        Assertions.assertTrue(vEcriture.isEquilibree());


    }


    @Test
    public void isNotEquilibree(){
        EcritureComptable vEcriture = new EcritureComptable();
        vEcriture.setLibelle("Non equilibree");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "56", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "35", "23"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "16"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "19", "19"));
        Assertions.assertFalse(vEcriture.isEquilibree());
    }

   @Test
    public void getTotalDebit(){
        EcritureComptable vEcriture = new EcritureComptable();
       vEcriture.getListLigneEcriture().add(this.createLigne(1, "323.50", null));
       vEcriture.getListLigneEcriture().add(this.createLigne(1, "56.50", "353"));
       vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "23"));
       vEcriture.getListLigneEcriture().add(this.createLigne(2, "86", "90"));

        BigDecimal debit = vEcriture.getTotalDebit();
        double l = 466;
        Assertions.assertTrue(debit.equals(new BigDecimal(l).setScale(2)));



    }

    @Test
    public void getTotalCredit(){
        EcritureComptable vEcriture = new EcritureComptable();
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "323.5", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "56.5", "353"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "23"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "86", "90"));

        BigDecimal credit = vEcriture.getTotalCredit();
        double l = 466;
        Assertions.assertTrue(credit.equals(new BigDecimal(l).setScale(0)));
    }



    @Test
    public void testToStringOK()
    {
        EcritureComptable vEcriture = new EcritureComptable();
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "323.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "56.50", "353"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "23"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "86", "90"));
        Assertions.assertEquals(vEcriture.toString(), "EcritureComptable{id=null, journal=null, reference='null', date=null, libelle='null', totalDebit=466.00, totalCredit=466, listLigneEcriture=[\n" +
                "LigneEcritureComptable{compteComptable=CompteComptable{numero=1, libelle='null'}, libelle='323.50', debit=323.50, credit=null}\n" +
                "LigneEcritureComptable{compteComptable=CompteComptable{numero=1, libelle='null'}, libelle='-296.50', debit=56.50, credit=353}\n" +
                "LigneEcritureComptable{compteComptable=CompteComptable{numero=2, libelle='null'}, libelle='-23', debit=null, credit=23}\n" +
                "LigneEcritureComptable{compteComptable=CompteComptable{numero=2, libelle='null'}, libelle='-4', debit=86, credit=90}\n" +
                "]}");
    }

    @Test
    public void testToStringNok()
    {
        EcritureComptable vEcriture = new EcritureComptable();
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "123.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "56.25", "333"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "23"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "86", "89.75"));
        Assertions.assertNotEquals(vEcriture.toString(), "Ceci est un test de String !");
    }

}