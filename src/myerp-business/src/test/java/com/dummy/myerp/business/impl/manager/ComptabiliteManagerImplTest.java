package com.dummy.myerp.business.impl.manager;

import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.business.impl.TransactionManager;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.TransactionStatus;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ComptabiliteManagerImplTest {

    private EcritureComptable vEcritureComptable;
    private ComptabiliteManagerImpl comptabiliteManager = new ComptabiliteManagerImpl();

    @Mock
    DaoProxy daoProxyMock;

    @Mock
    ComptabiliteDao comptabiliteDaoMock;

    @Mock
    BusinessProxy businessProxyMock;

    @Mock
    TransactionManager transactionManagerMock;

    @Mock
    ComptabiliteManagerImpl comptabiliteManagerMock;

    @Mock
    TransactionStatus transactionStatusMock;


    // ==================== Test unitaires ====================


    @Test
    public void checkEcritureComptableUnitViolationNok() {
        assertThrows(FunctionalException.class, () ->
        {
            EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            comptabiliteManager.checkEcritureComptableUnitViolation(vEcritureComptable);
        });
    }

    @Test
    public void checkEcritureComptableUnitViolationOk() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("TS", "Test"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Test");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null,
                new BigDecimal(123)));

        comptabiliteManager.checkEcritureComptableUnitViolation(vEcritureComptable);
    }

    @Test
    public void checkEcritureComptableUnitNok() {
        assertThrows(FunctionalException.class, () ->
        {
            EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            comptabiliteManager.checkEcritureComptableUnit(vEcritureComptable);
        });
    }

    @Test
    public void checkEcritureComptableUnitOk() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Test"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Test");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null,
                new BigDecimal(123)));
        comptabiliteManager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test
    public void checkEcritureComptableUnitRG2Nok() {
        assertThrows(FunctionalException.class, () ->
        {
            EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            vEcritureComptable.setJournal(new JournalComptable("TS", "Test"));
            vEcritureComptable.setDate(new Date());
            vEcritureComptable.setLibelle("Test");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                    null, new BigDecimal(1234), null));
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                    null, null,
                    new BigDecimal(4321)));
            comptabiliteManager.checkEcritureComptableUnitRG2(vEcritureComptable);
        });
    }

    @Test
    public void checkEcritureComptableUnitRG2Ok() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("TS", "Test"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Test");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null,
                new BigDecimal(123)));
        comptabiliteManager.checkEcritureComptableUnitRG2(vEcritureComptable);

    }

    @Test
    public void checkEcritureComptableUnitRG3Nok() {
        assertThrows(FunctionalException.class, () ->
        {
            EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            vEcritureComptable.setJournal(new JournalComptable("TS", "Test"));
            vEcritureComptable.setDate(new Date());
            vEcritureComptable.setLibelle("test");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                    null, new BigDecimal(123),
                    null));
            comptabiliteManager.checkEcritureComptableUnitRG3(vEcritureComptable);
        });
    }

    @Test
    public void checkEcritureComptableUnitRG3Ok() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("TS", "Test"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Test");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null,
                null, new BigDecimal(123)));
        comptabiliteManager.checkEcritureComptableUnitRG3(vEcritureComptable);
    }

    @Test
    public void checkEcritureComptableUnitRG5Nok() {
        assertThrows(FunctionalException.class, () -> {
            EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            vEcritureComptable.setJournal(new JournalComptable("TS", "Test"));
            vEcritureComptable.setDate(new Date());
            vEcritureComptable.setLibelle("Test");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                    null, new BigDecimal(123),
                    null));
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                    null, new BigDecimal(123),
                    null));
            vEcritureComptable.setReference("TS-2023/110001");
            comptabiliteManager.checkEcritureComptableUnitRG5(vEcritureComptable);
        });
    }

    @Test
    public void checkEcritureComptableUnitRG5Ok() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("TS", "Test"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Test");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.setReference("TS-2023/00001");
        comptabiliteManager.checkEcritureComptableUnitRG5(vEcritureComptable);
    }

//   ==================== Test Integrations ====================

    @BeforeEach
    public void setup() {
        //Arrange
        comptabiliteManager = new ComptabiliteManagerImpl();
        vEcritureComptable = new EcritureComptable();
        JournalComptable pJournal = new JournalComptable("TS", "test");
        vEcritureComptable.setJournal(pJournal);
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("TEST");
        vEcritureComptable.setReference("TS-2023/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123), null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null, new BigDecimal(123)));
        //Mock
        ComptabiliteManagerImpl.configure(businessProxyMock, daoProxyMock, transactionManagerMock);
        lenient().when(daoProxyMock.getComptabiliteDao()).thenReturn(comptabiliteDaoMock);
    }

    @Test
    public void testAddNewReference() throws Exception {
        //Arrange
        String code = vEcritureComptable.getJournal().getCode();
        int annee = vEcritureComptable.getDate().toInstant().atZone(ZoneId.of("Europe/Paris")).getYear();
        //Mock
        when(comptabiliteDaoMock.getSequenceEcritureComptable(code, annee)).thenReturn(null);
        when(daoProxyMock.getComptabiliteDao()).thenReturn(comptabiliteDaoMock);
        //Act
        comptabiliteManager.addReference(vEcritureComptable);
        //Assert
        verify(daoProxyMock, times(4)).getComptabiliteDao();
        verify(comptabiliteDaoMock, times(1)).insertSequenceEcritureComptable(any());
    }

    @Test
    public void testUpdateReference() throws Exception {
        //Arrange
        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable();
        sequenceEcritureComptable.setAnnee(2023);
        sequenceEcritureComptable.setDerniereValeur(00001);
        String code = vEcritureComptable.getJournal().getCode();
        int annee = vEcritureComptable.getDate().toInstant().atZone(ZoneId.of("Europe/Paris")).getYear();
        //Mock
        when(daoProxyMock.getComptabiliteDao()).thenReturn(comptabiliteDaoMock);
        when(comptabiliteDaoMock.getSequenceEcritureComptable(code, annee)).thenReturn(sequenceEcritureComptable);
        //Act
        comptabiliteManager.addReference(vEcritureComptable);
        //Assert
        verify(daoProxyMock, times(4)).getComptabiliteDao();
        verify(comptabiliteDaoMock, times(1)).updateEcritureComptable(any());
    }


    @Test
        public void testCheckEcritureComptable() throws FunctionalException, NotFoundException {
            //Arrange
            vEcritureComptable.setId(1);
            SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable();
            sequenceEcritureComptable.setAnnee(2023);
            sequenceEcritureComptable.setDerniereValeur(00001);
            //Mock
            System.out.println(vEcritureComptable);
            lenient().when(comptabiliteDaoMock.getEcritureComptableByRef("TS-2023/00001")).thenReturn(vEcritureComptable);
            //Assert
            comptabiliteManager.checkEcritureComptable(vEcritureComptable);
    }
    @Test
    public void testCheckEcritureComptableContextWithNewRef() throws NotFoundException {
        given(comptabiliteDaoMock.getEcritureComptableByRef(anyString())).willReturn(vEcritureComptable);
        FunctionalException functionalException = assertThrows(FunctionalException .class, () -> {
           System.out.println(vEcritureComptable);
           comptabiliteManager.checkEcritureComptableContext(vEcritureComptable);
       });
        Assertions.assertEquals(functionalException.getMessage(),"Une autre écriture comptable existe déjà avec la même référence.");

    }

    @Test
    public void testCheckEcritureComptableContextWithSameRef() throws NotFoundException {
        //Arrange
        vEcritureComptable.setId(1);
        EcritureComptable testEcritureComptable = new EcritureComptable();
        testEcritureComptable.setId(2);
        //Mock
        when(comptabiliteDaoMock.getEcritureComptableByRef(anyString())).thenReturn(testEcritureComptable);
        //Act
        FunctionalException functionalException = assertThrows(FunctionalException.class,
                () -> comptabiliteManager.checkEcritureComptableContext(vEcritureComptable));
        //Assert
        Assertions.assertEquals(functionalException.getMessage(),"Une autre écriture comptable existe déjà avec la même référence.");
    }

    @Test
    public void testNewEcritureComptable() throws FunctionalException, NotFoundException {
        //Arrange
        transactionStatusMock = transactionManagerMock.beginTransactionMyERP();
        vEcritureComptable.setId(1);
        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(2022,1);
        //Mock
        when(comptabiliteDaoMock.getEcritureComptableByRef("TS-2023/00001")).thenReturn(vEcritureComptable);
        lenient().when(comptabiliteDaoMock.getSequenceEcritureComptable(anyString(),anyInt())).thenReturn(sequenceEcritureComptable);
        //Act
        comptabiliteManager.insertEcritureComptable(vEcritureComptable);
        //Assert
        verify(daoProxyMock, times(2)).getComptabiliteDao();
        verify(transactionManagerMock, times(1)).commitMyERP(transactionStatusMock);
        verify(transactionManagerMock, times(1)).rollbackMyERP(transactionStatusMock);
    }

    @Test
    public void testUpdateEcritureComptable() throws NotFoundException, FunctionalException {
        //Arrange
        transactionStatusMock = transactionManagerMock.beginTransactionMyERP();
        vEcritureComptable.setId(1);
        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(2023,1);
        //Mock
        when(daoProxyMock.getComptabiliteDao()).thenReturn(comptabiliteDaoMock);
        lenient().when(comptabiliteDaoMock.getSequenceEcritureComptable(anyString(),anyInt())).thenReturn(sequenceEcritureComptable);
        //Act
        comptabiliteManager.updateEcritureComptable(vEcritureComptable);
        //Assert
        verify(transactionManagerMock, times(1)).commitMyERP(transactionStatusMock);
        verify(transactionManagerMock, times(1)).rollbackMyERP(transactionStatusMock);
    }

    @Test
    public void testDeleteEcritureComptableShouldCallTransactionManager() {
        //Arrange
        transactionStatusMock = transactionManagerMock.beginTransactionMyERP();
        vEcritureComptable.setId(1);
        //Act
        comptabiliteManager.deleteEcritureComptable(vEcritureComptable.getId());
        //Assert
        verify(daoProxyMock, times(1)).getComptabiliteDao();
        verify(transactionManagerMock, times(1)).commitMyERP(transactionStatusMock);
        verify(transactionManagerMock, times(1)).rollbackMyERP(transactionStatusMock);
    }

}


