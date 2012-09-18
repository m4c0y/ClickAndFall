package prototipo.dao;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import prototipo.bean.records.Record;
import prototipo.redremover.Contants;
import android.util.Log;

public class RecordesDAO {

	public static void saveRecord(InputStream input, FileOutputStream output, Record record) {

		DataOutputStream out = new DataOutputStream(output);
		
		List<Record> records = new ArrayList<Record>();
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

					String jogador = element.getAttribute("jogador");
					int clicks = Integer.parseInt(element.getAttribute("clicks"));
					long tempo = Long.parseLong(element.getAttribute("tempo"));

					records.add(new Record(jogador, clicks, tempo));
				}
			}
			records.add(record);
			Collections.sort(records);
			
			Log.i(Contants.TAG, records.toString());

			
			out.writeChars("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Game><Record jogador=\"A\" clicks=\"1\" tempo=\"1000\" /></Game>");
			out.flush();
			
			
		} catch (Exception e) {
			Log.e(Contants.TAG, e.getMessage());
		}
	}
}
