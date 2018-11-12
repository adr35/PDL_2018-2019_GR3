package src.main.java.model;

import java.util.ArrayList;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class FormatHTML
{
	
	
	public String html;

	
	public FormatHTML(){
		this.html = new String();
	}
	
	public FormatHTML(String html){
		this.html = html;
	}

	/**
	 * <!-- begin-user-doc -->
	 * Renvoie la prodction CSV correspondant au tableau de la page HTML
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ProductionCSV ToCSV(FormatHTML htmlFormat) {
		// TODO implement me
		return null;
	}
	
	//remplace le return par un String tab[] on aura tout les tableaux de la page
	
	/*début : wikitable
	 * fin : à modifier
	 */
	
	public FormatHTML PremierSplit() {
		String[] separateur = this.html.split("table class=\"");
		/*ArrayList<String> pageWikitable = new ArrayList<String>();
		    for(String clear : pageWikitable) {
		        if (clear.startsWith("wikitable")) {
		            pageWikitable.add(clear);
		    }
		}*/
		FormatHTML result = new FormatHTML(separateur[1]);
		System.out.println(separateur[1]);
		return result;
	}
	
	public FormatHTML SecondSplit() {
		String[] separateur = this.html.split("</tbody>");
		FormatHTML result = new FormatHTML(separateur[0]);
		System.out.println(separateur[0]);
		return result;
	}

}

