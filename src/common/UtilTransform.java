package common;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilTransform extends UtilC {

    private static final List<Map<String, String>> l = new ArrayList<>();

    private static Map<String, String> m = null;

    public static void requestTransform() throws TransformerException, IOException {
        try {
            Source x = new StreamSource(new File("src/config/EmployeeRequest.xml"));
            Source s = new StreamSource(new File("src/config/Employee-modified.xsl"));
            Result o = new StreamResult(new File("src/config/EmployeeResponse.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer(s);
            transformer.transform(x, o);
        } catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
            throw new TransformerException("Error while transforming request", e);
        }
    }

    public static List<Map<String, String>> xmlXPaths() throws ParserConfigurationException, SAXException, IOException,
            XPathExpressionException {
        Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse("src/config/EmployeeResponse.xml");
        XPath xPath = XPathFactory.newInstance().newXPath();
        int n = Integer.parseInt((String) xPath.compile("count(//Employees/Employee)").evaluate(d, XPathConstants.STRING));

        for (int i = 1; i <= n; i++) {
            m = new HashMap<>();
            m.put("XpathEmployeeIDKey", (String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeID/text()")
                    .evaluate(d, XPathConstants.STRING));
            m.put("XpathEmployeeNameKey", (String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeFullName/text()")
                    .evaluate(d, XPathConstants.STRING));
            m.put("XpathEmployeeAddressKey",
                    (String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeFullAddress/text()").evaluate(d,
                            XPathConstants.STRING));
            m.put("XpathFacultyNameKey", (String) xPath.compile("//Employees/Employee[" + i + "]/FacultyName/text()")
                    .evaluate(d, XPathConstants.STRING));
            m.put("XpathDepartmentKey", (String) xPath.compile("//Employees/Employee[" + i + "]/Department/text()")
                    .evaluate(d, XPathConstants.STRING));
            m.put("XpathDesignationKey", (String) xPath.compile("//Employees/Employee[" + i + "]/Designation/text()")
                    .evaluate(d, XPathConstants.STRING));
            l.add(m);
        }
        return l;
    }
}
