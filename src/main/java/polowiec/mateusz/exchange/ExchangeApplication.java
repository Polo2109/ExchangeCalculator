package polowiec.mateusz.exchange;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import polowiec.mateusz.exchange.model.ExchangeRate;
import polowiec.mateusz.exchange.service.ExchangeService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ExchangeService exchangeService) {
        return args -> {
            List<ExchangeRate> exchangeRates = new ArrayList<>();
            try {
                File inputFile = new File("src/main/resources/static/exchange-rate.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document document = dBuilder.parse(inputFile);
                document.getDocumentElement().normalize();
                NodeList nList = document.getElementsByTagName("Cube");
                for (int i = 2; i < nList.getLength(); i++) {
                    Node node = nList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        ExchangeRate exchangeRate = new ExchangeRate();
                        exchangeRate.setCurrency(element.getAttribute("currency"));
                        exchangeRate.setRate(Double.parseDouble(element.getAttribute("rate")));
                        exchangeRates.add(exchangeRate);
                    }
                }
                exchangeService.save(exchangeRates);
                System.out.println("Rates saved!");
            } catch (ParserConfigurationException | IOException | SAXException e) {
                System.err.println("Unable to save rates" + e.getMessage());
            }
        };
    }
}
