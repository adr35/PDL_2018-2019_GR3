package src.main.java.model;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class FormatWikitext
{

	
	public String wikitext;
	public ArrayList<Character> urlTitle = new ArrayList<Character>();
	public String urlfinal = new String("");


	
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
			//System.out.println("Texte à ajouter à l'URL:" + separateur[1]);
			//System.out.print(result);
			ArrayListToString(charArrayToArrayList(separateur[1]));
			newUrl();
			return result;
		}
	
		public ArrayList<Character> charArrayToArrayList(String url) {
		char[] chars = url.toCharArray();
		for(int i =0; i<url.length(); i++) {
			urlTitle.add(i, chars[i]);
		}
		urlTitle.remove(0);
		//urlTitle.remove(url.length()-2);
		//urlTitle.remove(url.length()-3);
		for(int j=url.length()-2; j>url.length()-16;j--) {
			urlTitle.remove(j);
		}
		System.out.print(urlTitle);
		System.out.println("");
		return urlTitle;
	}
		
	public String ArrayListToString(ArrayList<Character> urlTitle) {
		for(int i =0;i<urlTitle.size();i++) {
			urlfinal += urlTitle.get(i); 
		}
		//System.out.println(urlfinal);
		return urlfinal;
	}
	
		public FormatWikitext wikipremierSplit() {
		//System.out.print("glou");
		//System.out.print(wikitext);
		String[] wikiseparateur = this.wikitext.split("\\{\\|");
		FormatWikitext result = new FormatWikitext(wikiseparateur[1]);
		//System.out.print(wikiseparateur[1]);
		return result;
	}
	
	public FormatWikitext wikisecondSplit() {
		String[] wikiseparateur = this.wikitext.split("\\|}");
		FormatWikitext result = new FormatWikitext(wikiseparateur[0]);
		//System.out.println(wikiseparateur[0]);
		return result;
	}
	
	public FormatWikitext wikiheadSplit() {
		String [] separateur = this.wikitext.split("! scope=col \\|");
		FormatWikitext result = new FormatWikitext(separateur[1]);
		System.out.println(separateur[1]);
		return result;		
	}
	
	/*	public String ArrayListtoString (ArrayList <Character> al) {
		 StringBuilder builder = new StringBuilder(al.size());
		    for(Character ch: al)
		    {
		        builder.append(ch);
		    }
		    System.out.print(builder.toString());
		    return builder.toString();
	}*/
	
	/*public FormatWikitext wikiparse() {
		Document doc = Jsoup.parse(this.wikitext);
		System.out.println(doc.html());
		FormatWikitext result = new FormatWikitext();
		//result = result.replaceAll("<[^>]*>", "");
		Elements rows = doc.getElementsByTag("title");
		System.out.println("couille bleue");
		for (Element row : rows) {
			result.wikitext += row.text();
			System.out.println("couille bleue");
		}

		return result;
	}*/
	
	*/
	/** Nouvel URL pour la page Wikitext
	 * 
	 * @return 
	 */
	public String newUrl() {
		String newUrl = new String("https://fr.wikipedia.org/w/index.php?title=" + urlfinal + "&action=edit&section=1");
		System.out.println(newUrl);
		return newUrl;

	}
	
}

