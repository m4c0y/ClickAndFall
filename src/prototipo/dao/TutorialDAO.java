package prototipo.dao;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import prototipo.redremover.Contants;
import android.util.Log;

public class TutorialDAO {

	public static String parseFaseXML(InputStream input) {
		
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(input);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getDocumentElement().getChildNodes();

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) nNode;

					return  element.getAttribute("str");
				}
			}
			
			
		} catch (Exception e) {
			Log.e(Contants.TAG, e.getMessage());
		}
		return "";
	}
}
