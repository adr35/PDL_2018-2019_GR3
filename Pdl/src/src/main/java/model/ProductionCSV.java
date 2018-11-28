package src.main.java.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


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
	 * <!-- begin-user-doc -->
	 * Renvoie le CSV sous forme de string
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
		public void Production (FormatHTML html) throws IOException
	{
		 try {
		Scanner sc = new Scanner(System.in);	 
	        String FILE_NAME = sc.nextLine();
		sc.close();
		
	    FileWriter writer = new FileWriter(FILE_NAME+".csv");
		  
		  writer.write("TEST,TEST,TEST");
		  writer.close();
		
		  System.out.println("Fichier CSV bien enregistré !");
		 }
		 catch (IOException e) {
		        e.getStackTrace();
		 
		 }
		//Production du fichier CSV à partir du format HTML
		
	}
	
	public void generateCSV(String title,int numTab) throws IOException {
		FileWriter fileWriter = new FileWriter(title.trim() + "-" + numTab + ".csv");
		fileWriter.write(this.csv);
		fileWriter.flush();
		fileWriter.close();
	}
	
	
	public void Production (FormatWikitext wiki)
	{
		
		//Production du fichier CSV à partir du format Wikitext
		
		
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

