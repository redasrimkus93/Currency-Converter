package com.project.currencyweb;

import com.project.currencyweb.models.CcyAmt;
import com.project.currencyweb.models.CurrencyConversion;
import com.project.currencyweb.repository.CurrencyRepository;
import com.project.currencyweb.service.CurrencyService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyServiceTest {

    @Mock
    private CurrencyRepository repository;

    private CurrencyService subject;

    @Before
    public void setup(){
        subject = new CurrencyService(repository);
    }
    @Test
    public void getAllCurrenciesTestEmpty() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList());
        List<CcyAmt> ccyAmts = subject.getCurrencies();
        Assert.assertTrue(ccyAmts.isEmpty());
    }

    @Test
    public void getAllCurrenciesTestSort() {

        CcyAmt ccyAmtCAD = new CcyAmt("CAD", 1.56);
        CcyAmt ccyAmtINR = new CcyAmt("INR", 86.262);
        CcyAmt ccyAmtEUR = new CcyAmt("EUR", 1);
        CcyAmt ccyAmtPLN = new CcyAmt("PLN", 4.4934);

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(ccyAmtCAD, ccyAmtEUR, ccyAmtINR, ccyAmtPLN));
        List<CcyAmt> ccyAmts = subject.getCurrencies();
        Assert.assertEquals(ccyAmts.get(0), ccyAmtCAD);
        Assert.assertEquals(ccyAmts.get(1), ccyAmtEUR);
        Assert.assertEquals(ccyAmts.get(2), ccyAmtINR);
        Assert.assertEquals(ccyAmts.get(3), ccyAmtPLN);
    }

    @Test(expected = NullPointerException.class)
    public void getAllCurrenciesTestNullPointerException() {

        Mockito.when(repository.findAll()).thenReturn(null);
        List<CcyAmt> ccyAmts = subject.getCurrencies();
    }

    @Test
    public void convertShouldReturnEmptyWhenValueIsNegative() {

        CcyAmt ccyAmtEUR = new CcyAmt("EUR", 1);
        CcyAmt ccyAmtPLN = new CcyAmt("PLN", 4.4934);

        Mockito.when(repository.findById("EUR")).thenReturn(Optional.of(ccyAmtEUR));
        Mockito.when(repository.findById("PLN")).thenReturn(Optional.of(ccyAmtPLN));

        CurrencyConversion currencyConversion = new CurrencyConversion("EUR","PLN",-10);

        Optional<Double> result = this.subject.conversion(currencyConversion);

        Assert.assertNotNull(result);
        Assert.assertFalse(result.isPresent());

    }
    @Test
    public void convertShouldReturnValue() {

        CcyAmt ccyAmtEUR = new CcyAmt("EUR", 1);
        CcyAmt ccyAmtPLN = new CcyAmt("PLN", 4.4934);

        Mockito.when(repository.findById("EUR")).thenReturn(Optional.of(ccyAmtEUR));
        Mockito.when(repository.findById("PLN")).thenReturn(Optional.of(ccyAmtPLN));

        CurrencyConversion currencyConversion = new CurrencyConversion("EUR","PLN",10);

        Optional<Double> result = this.subject.conversion(currencyConversion);

        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(new Double(2.2255), result.get());

    }


}
