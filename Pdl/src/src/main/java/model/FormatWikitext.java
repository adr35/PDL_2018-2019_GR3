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
			
			result = newUrl(ArrayListToString(charArrayToArrayList(separateur[1])));
			System.out.println(result.wikitext.toString());
			//result = newUrl();
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
		//System.out.print(urlTitle);
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
	
	public int wikicounttabs() {
	    Matcher matcher = Pattern.compile("wikitable").matcher(wikitext);
	    int occur = 0;
	    while(matcher.find()) {
	        occur ++;
	    }
	    return occur;
	}
	
	
	public FormatWikitext wikipremierSplit() {
		//System.out.print("glou");
		//System.out.print(wikitext);
		String[] wikiseparateur = this.wikitext.split("\\{\\|");
		FormatWikitext result = new FormatWikitext(wikiseparateur[1]);
		System.out.print(wikiseparateur[1]);
		return result;
	}
	
	public FormatWikitext wikisecondSplit() {
		String[] wikiseparateur = this.wikitext.split("\\|}");
		FormatWikitext result = new FormatWikitext(wikiseparateur[0]);
		//System.out.println(wikiseparateur[0]);
		return result;
	}
	
	public String[] wikiTabAllTabs() {
		FormatWikitext premiersplit = wikiPremierSplit();
		FormatWikitext secondsplit = wikiSecondSplit();
		String[] tab1 = premiersplit.wikitext.split("\\s+");
		String[] tab2 = secondsplit.wikitext.split("\\s+");
		ArrayList <String> tabfinal = new ArrayList<String>();
		String[] tableaufinal = new String[tabfinal.size()];
		int i=1; 
		int j=1;
		int k=0;
		//System.out.print(tab1[1] + " ++ " + tab2[9594]);
		while(!tab1[i].equals(tab2[j])) {
			//System.out.println(j);
			j++;
		}
		//System.out.print(tab1[i+1] + " "+i +" " + " ++ " + tab2[j+1] + "  " + j);
		while((tab1[i].equals(tab2[j])) && (j != tab2.length-1) ) {
			tabfinal.add(tab1[i]);
			//System.out.println(j);
			i++;j++;k++;
		}
		tableaufinal = tabfinal.toArray(tableaufinal);
		return tableaufinal;
	}
	
	public FormatWikitext wikiAllTabs() {
		FormatWikitext result = new FormatWikitext(Arrays.toString(wikiTabAllTabs()));
		return result;
	}
	
	public FormatWikitext wikiHeadSplit() {
		//System.out.println("headsplit");
		//String [] separateur = this.wikitext.split("! scope=col \\|");
		FormatWikitext result = new FormatWikitext(Arrays.toString(wikiHeadSplitTab()));
		//System.out.println(result.wikitext.toString());
		return result;		
	}
	
	public String[] wikiHeadSplitTab() {
		//System.out.println("headsplittab");
		int nbcol = wikiNombreColonne();
		String[] separateur = this.wikitext.split("! scope=col \\|");
		String[] result = new String[nbcol];
		for(int i=0;i<nbcol;i++) {
			result[i] = separateur[i];
			//System.out.println(result[i]);
		}
		//System.out.println(Arrays.toString(result));
		return result;
	}
	
	public int wikiNombreColonne() {
		//System.out.println("nbcolonne");
		//FormatWikitext clone = this.wikitext;
		int result = 0;
		String[] nbcol = this.wikitext.split("! scope=col \\|");
		result = nbcol.length ;
		//System.out.println(result);
		return result;
	}
	
	public FormatWikitext wikiHeadParse() {
		FormatWikitext wikitext = wikiHeadSplit();
		Document doc = Jsoup.parse(wikitext.wikitext);
		//System.out.println(doc.html());
		FormatWikitext result = new FormatWikitext();
		Elements rows = doc.getAllElements();
		
		/*for (Element row : rows ) {
			result.html += row.text();
			//System.out.println(row.text());
		}
		result.html = doc.html();*/
		Element row = rows.first();
		result.wikitext = row.text();
		//System.out.println(result.html);
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
	
	
	/** Nouvel URL pour la page Wikitext
	 * 
	 * @return 
	 */
	public FormatWikitext newUrl(String url) {
		url = urlfinal;
		FormatWikitext newUrl = new FormatWikitext("https://fr.wikipedia.org/w/index.php?title=" + url + "&action=edit");
		//System.out.println(newUrl);
		//System.out.print(wikitext);
		return newUrl;

	}
	
}


