package sg.asia21at.webdev.footprintimporter.fileHandler;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

import sg.asia21at.webdev.footprintimporter.util.Convertor;
import sg.asia21at.webdev.footprintimporter.util.XmlReader;

/**
 * @author Ye Chuang
 * Handler BJ2 metafile
 *	result will accumulated. To reuse, create a new instance
 */
public class MetaFileHandler implements FileHandler {
	private XmlReader xmlReader = new XmlReader();
	
	private List<Object[]> results = new ArrayList<Object[]>();

	@Override
	public void process(Path file) {
		try {			
			Object[] row = new Object[19];
			xmlReader.openDoc(file);
			row[0] = getSatFromStr(xmlReader.getString("Satellite_Name").trim());	//int
			row[1] = xmlReader.getString("Acquisition_ID").trim();	//String
			row[2] = getSceneNumFromStr(xmlReader.getString("Scene_ID").trim());	//int
			row[3] = Convertor.getDateFromStr(xmlReader.getString("Begin_Time").trim());	//Date
			row[4] = Double.parseDouble(xmlReader.getString("Central_Lat").trim());	//double below
			row[5] = Double.parseDouble(xmlReader.getString("Central_Lon").trim());
			row[6] = Double.parseDouble(xmlReader.getString("Cloud_Cover").trim());
			row[7] = Double.parseDouble(xmlReader.getString("Roll_Angle").trim());
			row[8] = Double.parseDouble(xmlReader.getString("Pitch_Angle").trim());
			// mind the order: ul, ur, lr, ll, ul. Otherwise, create invalid polygon
			// Long = x, Lat = y
			row[9] = Double.parseDouble(xmlReader.getString("UL_Lon").trim());	//double below
			row[10] = Double.parseDouble(xmlReader.getString("UL_Lat").trim());
			row[11] = Double.parseDouble(xmlReader.getString("UR_Lon").trim());	//double below
			row[12] = Double.parseDouble(xmlReader.getString("UR_Lat").trim());
			row[13] = Double.parseDouble(xmlReader.getString("LR_Lon").trim());	//double below
			row[14] = Double.parseDouble(xmlReader.getString("LR_Lat").trim());
			row[15] = Double.parseDouble(xmlReader.getString("LL_Lon").trim());	//double below
			row[16] = Double.parseDouble(xmlReader.getString("LL_Lat").trim());
			row[17] = Convertor.getDateFromStr(xmlReader.getString("End_Time").trim());
			row[18] = xmlReader.getString("Scene_ID").trim();
			
			results.add(row);

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public List<Object[]> getResults() {
		return results;
	}
	public void clearResults() {
		results = new ArrayList<Object[]>();
	}

	public void printResults() {
		for (Object[] row : results) {
			for (Object s : row) {
				System.out.print(s + " ");
			}
			System.out.println("");

		}
	}
	public int getSatFromStr(String s){
		return Integer.parseInt(s.substring(24, 25));
	}
	public int getSceneNumFromStr(String s){
		return Integer.parseInt(s.substring(43, 46));
	}
	
	
	

}
