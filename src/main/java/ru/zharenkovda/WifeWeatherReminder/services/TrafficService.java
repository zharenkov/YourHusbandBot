package ru.zharenkovda.WifeWeatherReminder.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.zharenkovda.WifeWeatherReminder.utils.RestRequestHelper;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;

import static javax.xml.xpath.XPathConstants.STRING;

@Service
public class TrafficService {

    String trafficUrl = "https://export.yandex.ru/bar/reginfo.xml?region=51";

    public String getTrafficMessage()  {
        try {
            String trafficXML = RestRequestHelper.sendGetRequest(trafficUrl);
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(trafficXML));
            Document doc = builder.parse(is);
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression levelExpression = xPath.compile("info/traffic/region/level");
            XPathExpression hintExpression = xPath.compile("info/traffic/region/hint[@lang='ru']");
            Integer level = Integer.valueOf((String) levelExpression.evaluate(doc, STRING));
            String hint = (String) hintExpression.evaluate(doc, STRING);
            if (level == null || StringUtils.isEmpty(hint)) {
                return "";
            }
            return String.format("На дороге пробки %d %s. %s.", level, scoreAffirmation(level), hint);
        //TODO logger
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e){
            e.printStackTrace();
        } catch (XPathExpressionException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    private String scoreAffirmation(Integer trafficScore){
        if (trafficScore == 0 || trafficScore>=5) {
            return "баллов";
        } else if (trafficScore>1 && trafficScore<5){
            return "балла";
        }
        return "балл";
    }
}
