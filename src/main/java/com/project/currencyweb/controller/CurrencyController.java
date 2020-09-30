package com.project.currencyweb.controller;

import com.project.currencyweb.models.CcyAmt;
import com.project.currencyweb.models.CurrencyConversion;
import com.project.currencyweb.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CurrencyController {

        private CurrencyService currencyService;

        public CurrencyController(CurrencyService currencyService){
                this.currencyService = currencyService;
        }


        @RequestMapping(value = "/conversion", produces = {"application/json"}, method = RequestMethod.POST)
        public ResponseEntity<Double> currencyConversion(@RequestBody CurrencyConversion currencyConversion) {
                Optional<Double> conversionResult = this.currencyService.conversion(currencyConversion);
                if(conversionResult.isPresent()){
                return new ResponseEntity<>(conversionResult.get(), HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        @RequestMapping(value = "/currencies", produces = {"application/json"}, method = RequestMethod.GET)
        public ResponseEntity<List<CcyAmt>> getAllCurrencies(){
            return new ResponseEntity<>(this.currencyService.getCurrencies(), HttpStatus.OK);
        }


}
