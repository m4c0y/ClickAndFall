package prototipo.dao;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.anddev.andengine.entity.shape.IShape;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import prototipo.redremover.Contants;
import android.util.Log;

public class FaseDAO {

	public static List<IShape> parseFaseXML(InputStream input) {
		
		List<IShape> shapes = new ArrayList<IShape>();
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

					 Log.i(Contants.TAG, element.getTagName());
					
					 Log.i(Contants.TAG, "X : " + element.getAttribute("x"));
					 Log.i(Contants.TAG, "Y : " + element.getAttribute("y"));
					
					 Log.i(Contants.TAG, "Width : " + element.getAttribute("width"));
					 Log.i(Contants.TAG, "Height : " + element.getAttribute("height"));

					List<Float> params = new ArrayList<Float>();
					Class[] classParam;

					params.add(Float.parseFloat(element.getAttribute("x")));
					params.add(Float.parseFloat(element.getAttribute("y")));

					if (element.getAttribute("width").trim().length() > 0) {
						params.add(Float.parseFloat(element.getAttribute("width")));
						params.add(Float.parseFloat(element.getAttribute("height")));

						classParam = new Class[] { Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE };
					} else {
						classParam = new Class[] { Float.TYPE, Float.TYPE };
					}


		            Class classXML = Class.forName("prototipo.bean.shapes." + element.getTagName());
					Constructor constructor = classXML.getConstructor(classParam);

					IShape shape = (IShape) constructor.newInstance(params.toArray());
					shapes.add(shape);
				}
			}
			
//			Element e = doc.createElement("CuboVerde");
//			e.setAttribute("x", "0");
//			e.setAttribute("y", "0");
//			
//			doc.getDocumentElement().appendChild(e);
			
		} catch (Exception e) {
			Log.e(Contants.TAG, e.getMessage());
		}
		
//		Log.i(Contants.TAG, shapes.toString());
		return shapes;
	}
}
