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
	public int nbtab;
	public int tabCourant;
	public String titleCSV;
	//public int nbcol;


	
	public FormatWikitext(){
		this.wikitext = new String();
	}

	public FormatWikitext(String wikitext){
		this.wikitext = wikitext;
	}
	
	
	
	public void initialize(Url url) throws IOException{
		String HTML2 = url.HTML();	
		FormatWikitext head = new FormatWikitext(HTML2);
		FormatWikitext lignes = new FormatWikitext(HTML2);	
		head = head.wikisplit();
		lignes = lignes.wikisplit();
		Url urlHead = new Url(head.wikitext);
		Url urlLignes = new Url(lignes.wikitext);
		String HTML3 = urlHead.HTML();
		String HTML4 = urlLignes.HTML();
		FormatWikitext head2 = new FormatWikitext(HTML3);
		FormatWikitext lignes2 = new FormatWikitext(HTML4);
		FormatWikitext result = new FormatWikitext();
	    head2 = head2.wikiSecondSplit();
	    lignes2 = lignes2.wikiSecondSplit();
	    result.wikitext = head2.wikitext + "\n" + lignes2.wikitext;
	    result.ToCSV();
	    System.out.println(result.wikitext.toString());
	}
	
	/******************** DEBUT DE LA RECUPERATION DE L URL ***************/

	
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
	
	/**
	 * <!-- begin-user-doc -->
	 * Split sur le title : renvoi ce qu'il y a après le title
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public FormatWikitext wikisplit() {
			String[] separateur = this.wikitext.split("title");
			FormatWikitext result = new FormatWikitext(separateur[1]);
			result = newUrl(ArrayListToString(charArrayToArrayList(separateur[1])));
			System.out.println(result.wikitext.toString());
			//result = newUrl();
			return result;
		}

	/**
	 * <!-- begin-user-doc -->
	 * Permet de supprimer la partie inutile dans les balises title
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
		public ArrayList<Character> charArrayToArrayList(String url) {
		char[] chars = url.toCharArray();
		for(int i =0; i<url.length(); i++) {
			urlTitle.add(i, chars[i]);
		}
		urlTitle.remove(0);
		for(int j=url.length()-2; j>url.length()-16;j--) {
			urlTitle.remove(j);
		}
		return urlTitle;
	}
		
		/**
		 * <!-- begin-user-doc -->
		 * Converti l'arrayList de la fonction precedente en String
		 * <!--  end-user-doc  -->
		 * @generated
		 * @ordered
		 */
	public String ArrayListToString(ArrayList<Character> urlTitle) {
		for(int i =0;i<urlTitle.size();i++) {
			urlfinal += urlTitle.get(i); 
		}
		//System.out.println(urlfinal);
		return urlfinal;
	}
	
	
	
	/******************** FIN DE LA RECUPERATION DE L URL ***************/
	
	
	/******************* DEBUT DE LA RECUPERATION DU TABLEAU ****************/
	
	/**
	 * <!-- begin-user-doc -->
	 * Split sur le debut du tableau, tab[1] correspond au debut du tableau + fin de la page
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public FormatWikitext wikiPremierSplit() {
		String[] wikiseparateur = this.wikitext.split("\\{\\|");
		FormatWikitext result = new FormatWikitext(wikiseparateur[1]);
		return result;
	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 *  Split sur la fin du tableau, tab[0] correspond au debut du tableau + debut de la page
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public FormatWikitext wikiSecondSplit() {
		FormatWikitext premiersplit = wikiPremierSplit();
		FormatWikitext clone = premiersplit;
		FormatWikitext result = new FormatWikitext();
		String[] separateur = clone.wikitext.split("\\|}");
		result.wikitext = separateur[0];
		return result;
	}
	

	/**
	 * <!-- begin-user-doc -->
	 * Récupère uniquement le tableau
	 * 
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
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
	
	/*
	public FormatWikitext printsplitHeadTab() {
		FormatWikitext result = new FormatWikitext();
		String[] tab = splitTab();
		//System.out.println(wikiHeadSplit);
		result.wikitext = Arrays.toString(tab);
		return result;
	}*/
	
	/******************* FIN DE LA RECUPERATION DU TABLEAU ****************/
	
	
	/******************* DEBUT DU HEAD *****************************/
	
	/**
	 * <!-- begin-user-doc -->
	 * Renvoi le head
	 * 
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
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
			tabfinal[i] = tabfinal[i].replaceAll("[^\\wàâäÄÀÂéèêëÈÊËìîïÌÏÎòöôÒÖÔùüûÙÜÛç!#$€%&'`(),;:/@...]", " ");
			//System.out.println(tabfinal[i]);
		}
		result.wikitext = Arrays.toString(tabfinal);
		result.wikitext = result.wikitext.replaceAll("[^\\wàâäÄÀÂéèêëÈÊËìîïÌÏÎòöôÒÖÔùüûÙÜÛç!#$€%&'`(),;:/@...]", " ");
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
	
	public ProductionCSV headToCSV() {
		FormatWikitext wikitext = wikiHeadParse();
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
	/*************************** FIN TRAVAIL SUR HEAD ****************************/
	
	
	//<th[^>]*>
	/**************************DEBUT TRAVAIL SUR LIGNES *********************/
	public FormatWikitext wikiRowPremierSplit() { // on split sur les "|-"
		FormatWikitext result = new FormatWikitext();
		String[] clone = splitTab();
		String[] lignes = new String[clone.length];
		String elems = clone[0];
		nbelements = elems.split("[\n]").length-1;
		String[] elements = new String[nbelements];
		for(int i=0; i<clone.length-1;i++) {
			lignes[i] = clone[i+1];
			for(int j=0; j<nbelements;j++) {
				String[] split = lignes[i].split("\n");
				elements[j] = split[j+1];
				if(elements[j].contains(",")) { //si il y a des virgules dans le txt on les supprime
					elements[j] = elements[j].replaceAll(",", " ");
				}
				if(elements[j].contains("&lt;")) {
					elements[j] = elements[j].replaceAll("&lt[^>]*ref", "");
					elements[j] = elements[j].replaceAll("&gt;", "");
				}
				elements[j] = elements[j].replaceAll("[^\\wàâäÄÀÂéèêëÈÊËìîïÌÏÎòöôÒÖÔùüûÙÜÛç!#$€%&'`(),;:/@...]", " ");
				result.wikitext += elements[j] + ",";
			}
			result.wikitext = result.wikitext + "\n";
		}
		//System.out.println(result.wikitext.toString());
		return result;
	}
	
	public ProductionCSV rowToCSV() {
		FormatWikitext wikitext = wikiRowPremierSplit();
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
			prod.generateCSV("lignes", 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prod;
	}
	
	/************************* FIN TRAITEMENT LIGNES ********************/
	
	public void ToCSV() throws IOException {
		String result = "";
		/*FormatWikitext clone = clone();
		String[] separateur = clone.html.split("table class=\"wikitable");
		nbtab = separateur.length -1;*/
		//String title = ArrayListToString(charArrayToArrayList(wikisplit().wikitext));
		/*for(int i = 0; i< nbtab; i++){
			tabCourant = i +1;*/
			ProductionCSV head = headToCSV();
			ProductionCSV body = rowToCSV();
			result = (head.csv + "\n" +body.csv);
			ProductionCSV prod = new ProductionCSV(result);
			prod.generateCSV("", 0);
			System.out.println("Fichier créé avec succès.");
}
	

	
	
	
	public int wikiNombreLigne() {
		int result=0;
		String[] nbligne = this.wikitext.split("! scope\\=row \\|");
		result = nbligne.length-1;
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

	
}
