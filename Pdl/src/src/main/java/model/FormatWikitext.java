package src.main.java.model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


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
	//public ArrayList<String> wikiHeadSplit = new ArrayList<String>();
	public int nbcolonnes;
	public int nblignes;
	public int nbelements;
	//public int nbcol;


	
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
			result = newUrl(ArrayListToString(charArrayToArrayList(separateur[1])));
			System.out.println(result.wikitext.toString());
			//result = newUrl();
			return result;
		}
	
	public int wikiCountTabs() {
	    Matcher matcher = Pattern.compile("wikitable").matcher(wikitext);
	    int occur = 0;
	    while(matcher.find()) {
	        occur ++;
	    }
	    return occur;
}
	
		public ArrayList<Character> charArrayToArrayList(String url) {
		char[] chars = url.toCharArray();
		for(int i =0; i<url.length(); i++) {
			urlTitle.add(i, chars[i]);
		}
		urlTitle.remove(0);
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
	
	
	
	public FormatWikitext wikiPremierSplit() {
		String[] wikiseparateur = this.wikitext.split("\\{\\|");
		FormatWikitext result = new FormatWikitext(wikiseparateur[1]);
		return result;
	}
	
	public FormatWikitext wikiSecondSplit() {
		FormatWikitext premiersplit = wikiPremierSplit();
		FormatWikitext clone = premiersplit;
		FormatWikitext result = new FormatWikitext();
		String[] separateur = clone.wikitext.split("\\|}");
		result.wikitext = separateur[0];
		return result;
	}
	

	
	public String[] splitTab() {
		String[]separateur = this.wikitext.split("\\|\\-");
		String[]tabfinal = new String[separateur.length-1];
		ArrayList<String> list = new ArrayList<String>();
		if(separateur[0].contains("!")) {
			for(int i=0; i<separateur.length;i++) {
				list.add(separateur[i]);
			}
		}
		else {
			for(int i=1; i<separateur.length;i++) {
			list.add(separateur[i]);
			}
		}
		tabfinal = list.toArray(tabfinal);
		return tabfinal;
	}
	
	public FormatWikitext printsplitHeadTab() {
		FormatWikitext result = new FormatWikitext();
		String[] tab = splitTab();
		//System.out.println(wikiHeadSplit);
		result.wikitext = Arrays.toString(tab);
		return result;
	}
	
	public FormatWikitext wikiHeadPremierSplit() {
		String[] clone = splitTab();
		FormatWikitext result = new FormatWikitext();
		String head = clone[0];
	//	System.out.println(head);
		int nbcolonnes = head.split("!").length-1;
		System.out.println(nbcolonnes);
		String[]separateur = head.split("!");
		//System.out.println(separateur.length);
		String[]tabfinal = new String[nbcolonnes];
		for(int i=0; i<nbcolonnes;i++) {
			tabfinal[i] = separateur[i+1];
			if(tabfinal[i].contains("|")) { //Permet de supprimer les balises avant les noms des cols
				String[]separateur2 = tabfinal[i].split("\\|");
				tabfinal[i] = separateur2[1];
			}
			tabfinal[i] = tabfinal[i].replaceAll("[^\\wàâäÄÀÂéèêëÈÊËìîïÌÏÎòöôÒÖÔùüûÙÜÛç!#$€%&'`(),;:/@...]", "");
			//System.out.println(tabfinal[i]);
		}
		result.wikitext = Arrays.toString(tabfinal);
		return result;
	}
	
	public FormatWikitext wikiHeadParse() {
		FormatWikitext wikihead = wikiHeadPremierSplit();
		//System.out.println(wikihead.wikitext.toString());
		Document doc = Jsoup.parse(wikihead.wikitext);
		//System.out.println(doc.html());
		FormatWikitext result = new FormatWikitext();
		Elements rows = doc.getAllElements();
		
		for (Element row : rows ) {
			result.wikitext += row.text();
			//System.out.println(row.text());
		}
		result.wikitext = doc.html();
		Element row = rows.first();
		result.wikitext = row.text();
		//System.out.println(result.html);
		return result;	
	}

	
	
	public FormatWikitext wikiRowPremierSplit() { // on split sur les "|-"
		FormatWikitext result = new FormatWikitext();
		String[] clone = splitTab();
		String[] lignes = new String[clone.length];
		String elems = clone[0];
		nbelements = elems.split("[\n]").length-1;
		String[] elements = new String[nbelements];
		//System.out.println(nbelements);
		for(int i=0; i<clone.length-1;i++) {
			lignes[i] = clone[i+1];
		//	System.out.println(lignes[i] + "\n break");
			for(int j=0; j<nbelements;j++) {
				String[] split = lignes[i].split("\n");
				elements[j] = split[j+1];
				if(elements[j].contains(",")) {
					elements[j] = elements[j].replaceAll(",", " ");
				}
				elements[j] = elements[j].replaceAll("[^\\wàâäÄÀÂéèêëÈÊËìîïÌÏÎòöôÒÖÔùüûÙÜÛç!#$€%&'`(),;:/@...]", "");
					/*String[] remove = elements[j].split("\\[\\[");
					elements[j] = remove[1];
					if(elements[j].contains("]]")) {
						String[] remove2 = elements[j].split("\\]\\]");
						elements[j] = remove2[0];
					}*/
				//System.out.println(elements[j] + "\n break");
				result.wikitext += elements[j] + ",";
			}
			result.wikitext = result.wikitext + "\n";
		}
		//System.out.println(result.wikitext.toString());
		return result;
	}
	
	
	

	public int wikiNombreLigne() {
		int result=0;
		String[] nbligne = this.wikitext.split("! scope\\=row \\|");
		result = nbligne.length-1;
		//System.out.println(result);
		return result;
	}
	
	public ProductionCSV headToCSV() {
		//FormatWikitext title = wikisplit();
		FormatWikitext wikitext = wikiHeadParse();
		//String wikisplit = title.wikitext;
		ProductionCSV prod  = new ProductionCSV("");
		String[] res = wikitext.wikitext.split(",");
		String stringCSV = "";
			for(String str : res){
				if(str != null)
					stringCSV += str + "," ;
			}
			prod.csv = stringCSV;
		prod.csv = prod.csv.replaceAll("  ", " ");
		try {
			prod.generateCSV("coin", 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prod;
	}

	
	/** Nouvel URL pour la page Wikitext
	 * 
	 * @return 
	 */
	public FormatWikitext newUrl(String url) {
		url = urlfinal;
		FormatWikitext newUrl = new FormatWikitext("https://en.wikipedia.org/w/index.php?title=" + url + "&action=edit");
		//System.out.println(newUrl);
		//System.out.print(wikitext);
		return newUrl;

	}
	
}
