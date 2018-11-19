package src.main.java.model;


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
		FileWriter writer = new FileWriter("File_Name.csv");
		writer.write("TEST,TEST,TEST");
		writer.close();
		 }
		 catch (IOException e) {
		        e.getStackTrace();
		 
		 }
		//Production du fichier CSV à partir du format HTML
		
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

