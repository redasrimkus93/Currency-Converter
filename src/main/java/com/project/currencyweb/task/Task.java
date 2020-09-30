package com.project.currencyweb.task;

import com.project.currencyweb.models.CcyAmt;
import com.project.currencyweb.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Task {

    @Autowired
    private CurrencyRepository currencyRepository;


    @Scheduled(fixedRate = 24 * 1000 * 60 * 60)
    private void parseCurrencies() throws ParserConfigurationException, SAXException, IOException
    {

        List<CcyAmt> ccyAmts = new ArrayList<>();
        CcyAmt ccyAmt = null;
        System.setProperty("http.agent", "Chrome");
        String URL= "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(URL);
        document.getDocumentElement().normalize();

        NodeList nList = document.getElementsByTagName("CcyAmt");

        for (int temp=0;  temp < nList.getLength(); temp++)
        {
            if (temp % 2 == 0 && temp != 0) {}
            else {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    ccyAmt = new CcyAmt();
                    ccyAmt.setCcy(element.getElementsByTagName("Ccy").item(0).getTextContent());
                    ccyAmt.setAmt(Double.parseDouble(element.getElementsByTagName("Amt").item(0).getTextContent()));
                    this.currencyRepository.save(ccyAmt);
                }

            }
        }

    }
}
