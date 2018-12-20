package src.main.java.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class ProductionCSV
{

	public String csv;
	
	public ProductionCSV(){
		this.csv = new String();
	}
	
	public ProductionCSV(String csv){
		this.csv = csv;
	}

	/**
	 * 
	 * Renvoie le CSV sous forme de string
	 * @return 1 si le fichier CSV est créer
	 * @return 0 sinon
	 * @generated fichier CSV
	 * @ordered
	 */
	public int generateCSVFromHtml(String title,int numTab) throws IOException {
		FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "\\output\\html\\" +title.trim() + "-" + numTab + ".csv");
		fileWriter.write(this.csv);
		fileWriter.flush();
		fileWriter.close();
		
		File f = new File(System.getProperty("user.dir") + "\\output\\" + title.trim() + "-" + numTab + ".csv");
		if(f.exists()){
			return 1;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * 
	 * Renvoie le CSV sous forme de string
	 * @return 1 si le fichier CSV est créer
	 * @return 0 sinon
	 * @generated fichier CSV
	 * @ordered
	 */
	public int generateCSVFromWikitext(String title,int numTab) throws IOException {
		FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "\\output\\wikitext\\" +title.trim() + "-" + numTab + ".csv");
		fileWriter.write(this.csv);
		fileWriter.flush();
		fileWriter.close();
		
		File f = new File(System.getProperty("user.dir") + "\\output\\" + title.trim() + "-" + numTab + ".csv");
		if(f.exists()){
			return 1;
		}
		else {
			return 0;
		}
	}
	
	
	public void Production (FormatWikitext wiki)
	{
		
		//Production du fichier CSV Ã  partir du format Wikitext
		
		
	}
	
	public String ShowCSV(ProductionCSV csvProduction) {
		// TODO implement me
		return null;
	}

	
	public String getCSV() {
		// TODO implement me
		return this.csv;
	}

}

