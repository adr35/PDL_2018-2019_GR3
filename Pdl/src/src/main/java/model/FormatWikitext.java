package src.main.java.model;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class FormatWikitext
{

	
	public String wikitext;


	
	public FormatWikitext(){
		this.wikitext = new String();
	}

	public FormatWikitext(String wikitext){
		this.wikitext = wikitext;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * Renvoie la prodction CSV correspondant au tableau de la page HTML sous forme CSV
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ProductionCSV ToCSV(FormatWikitext WikitextFormat) {
		// TODO implement me
		return null;
	}
	
	public FormatWikitext wikisplit() {
			String[] separateur = this.wikitext.split("title");
			FormatWikitext result = new FormatWikitext(separateur[1]);
			//separateur[1].replaceAll("<","");
			System.out.println("Texte à ajouter à l'URL:" + separateur[1]);
			//System.out.print(result);
			charArrayToArrayList(separateur[1]);
			
			return result;
		}
	
	public void charArrayToArrayList(String url) {
		char[] chars = url.toCharArray();
		for(int i =0; i<url.length(); i++) {
			urlTitle.add(i, chars[i]);
			//System.out.print(chars[i]);
		}
		//for(int j=0; j<12;j++)
		urlTitle.remove(0);
		urlTitle.remove(url.length()-2);
		urlTitle.remove(url.length()-3);
		System.out.print(urlTitle);
	}
	
	/*public FormatWikitext wikisplit2() {
		String[] separateur = this.wikitext.split("title");
		FormatWikitext result = new FormatWikitext(separateur[0]);
		System.out.println(separateur[0]);
		return result;
	}
	
	*/
	/** Nouvel URL pour la page Wikitext
	 * 
	 * @return 
	 */
	public Url newUrl() {
		FormatWikitext result = wikisplit();
		Url newUrl = new Url("https://fr.wikipedia.org/w/index.php?title=" + result +"&action=edit&section=1");
		return newUrl;
			
	}
	
	public void main (String[] args) {
		//A tester...
		Url jeanlouis = newUrl();
		System.out.println(jeanlouis);
	}

}

