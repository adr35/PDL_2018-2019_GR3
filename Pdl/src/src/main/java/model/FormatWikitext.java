package src.main.java.model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
// ET SI ON METTAIT DES FORMATWIKITEXT EN PARAMETRE ?? PAS BESOIN DE CREER NOUVEAU FW DANS TEST ?
public class FormatWikitext
{

	public String wikitext;
	public ArrayList<Character> urlTitle = new ArrayList<Character>();
	public String urlfinal = new String("");
	public int nbcolonnes;
	public int nblignes;
	public int nbtab;
	public int tabCourant;
	public String titleCSV;



	public FormatWikitext(){
		this.wikitext = new String();
	}

	public FormatWikitext(String wikitext){
		this.wikitext = wikitext;
	}

	/******************** DEBUT DE LA RECUPERATION DE L URL ***************/

	/**
	 * <!-- begin-user-doc -->
	 * Split sur le title : renvoi ce qu'il y a après le title
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public FormatWikitext recupererURL() {
		String[] separateur = this.wikitext.split("title");
		FormatWikitext fw = new FormatWikitext(separateur[1]);
		char[] chars = separateur[1].toCharArray();
		for(int i =0; i<separateur[1].length(); i++) {
			urlTitle.add(i, chars[i]);
		}
		urlTitle.remove(0); //remove le ">"
		for(int j=separateur[1].length()-2; j>separateur[1].length()-16;j--) {
			urlTitle.remove(j);
		}
		for(int i =0;i<urlTitle.size();i++) {
			urlfinal += urlTitle.get(i); 
		}
		fw.wikitext = urlfinal;
		FormatWikitext result = new FormatWikitext("https://en.wikipedia.org/w/index.php?title=" + fw.wikitext + "&action=edit");
		return result;
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
	public FormatWikitext getTableau() {
		String[] wikiseparateur = this.wikitext.split("\\{\\|");
		FormatWikitext premiersplit = new FormatWikitext(wikiseparateur[1]);
		FormatWikitext clone = premiersplit;
		//FormatWikitext secondsplit.wikitext = new FormatWikitext(wikiseparateur2[0]);
		String[] wikiseparateur2 = clone.wikitext.split("\\|}");
		FormatWikitext secondsplit = new FormatWikitext(wikiseparateur2[0]);
		return secondsplit;
	}

	/*public FormatWikitext allTabs() {
		FormatWikitext fw = new FormatWikitext();
		String[] tab1 = new String[wikiCountTabs()];
		String[] tab2 = new String[wikiCountTabs()];
		//String[] separateur = this.wikitext.split("\\{\\|");
		for(int i=0; i<tab1.length;i++) {
			tab1[i] = this.wikitext.split("\\{\\|").toString();
			tab1[i] = this.wikitext.split("\\|}").toString();
			System.out.println(tab1[i].toString());
		}
		return fw;
	}*/

	@Override
	public String toString() {
		return this.wikitext;
	}


	/******************* FIN DE LA RECUPERATION DU TABLEAU ****************/


	/*public boolean vautLaPeine(ArrayList<String> list) {
		Integer[] nb_elems = new Integer[list.size()];
		for(int i=1;i<list.size();i++) {
			String[] separateur = list.get(i).split("[\n]");
			nb_elems[i] = separateur.length;
			System.out.println(separateur.length);
		}
		for(int j=1; j<nb_elems.length;j++) {
			if(nb_elems[j-1] != nb_elems[j]) {
				return false;
			}
		}
		return true;
	}*/

	/******************* DEBUT DU HEAD *****************************/
	public FormatWikitext getHead1() {
		FormatWikitext fw = getTableau();
		String[]separateur = fw.wikitext.split("\\|-");
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(separateur));
		if(!list.get(0).contains("!")) {
			list.remove(0);
		}
		String[]separateur2 = list.get(0).split("[\n]");
		if(separateur2[0].contains("wikitable")) {
			list.removeAll(list);
			for(int i=1;i<separateur2.length;i++) {
				separateur2[i] = separateur2[i].replaceAll("!!", "!");
				separateur2[i] = separateur2[i].replaceAll(",", "");
				list.add(separateur2[i]);
			}
		}
		String[] result = new String[list.size()];
		result = list.toArray(result);
		FormatWikitext resultat = new FormatWikitext();
		for(int i=0;i<result.length;i++) {
			resultat.wikitext += result[i];
		}

		System.out.println(resultat);
		return resultat;
	}


	/**
	 * <!-- begin-user-doc -->
	 * Renvoi le head
	 * 
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public FormatWikitext getHead() {
		FormatWikitext fw = getTableau();
		String[]separateur = fw.wikitext.split("\\|\\-");
		String[]tabfinal = new String[separateur.length];
		ArrayList<String> list = new ArrayList<String>();
		if(separateur[0].contains("!")) { /* cas spécial rencontré */
			for(int i=0; i<separateur.length;i++) {
				list.add(separateur[i]); //Si le premier split contiens des "!" qui représente une colonne, alors on l'ajoute dans la liste !
			}
		}
		else {
			for(int i=1; i<separateur.length;i++) {
				list.add(separateur[i]);
			}
		}
		tabfinal = list.toArray(tabfinal); //Converti la liste (qui contient le tableau entier, spliter sur les |--) en tableau
		String head = tabfinal[0];
		this.nbcolonnes = head.split("!").length-1;
		String[]separateur1 = head.split("!");
		String[]tabfinal1 = new String[nbcolonnes];
		for(int i=0; i<nbcolonnes;i++) {
			tabfinal1[i] = separateur1[i+1]; //on ne prend pas le split[0] car vide
			if(tabfinal1[i].contains("|")) { //Permet de supprimer les balises avant les noms des cols
				String[]separateur2 = tabfinal1[i].split("\\|");
				tabfinal1[i] = separateur2[1];
			}
			tabfinal1[i] = tabfinal1[i].replaceAll("[^\\wàâäÄÀÂéèêëÈÊËìîïÌÏÎòöôÒÖÔùüûÙÜÛç!#$€%&'`(),;:/@...]", " ");
		}
		FormatWikitext result = new FormatWikitext(Arrays.toString(tabfinal1));
		result.wikitext = result.wikitext.replaceAll("[^\\wàâäÄÀÂéèêëÈÊËìîïÌÏÎòöôÒÖÔùüûÙÜÛç!#$€%&'`(),;:/@...]", " "); //enlève les corchets en trop
		System.out.println(result);
		return result;
	}



	public ProductionCSV headToCSV() {
		FormatWikitext fw = getHead();
		ProductionCSV prod  = new ProductionCSV("");
		String[] res =fw.wikitext.split(",");
		String stringCSV = "";
		for(String str : res){
			if(str != null)
				stringCSV += str + "," ;
		}
		prod.csv = stringCSV;
		prod.csv = prod.csv.replaceAll("  ", " ");
		return prod;
	}
	/*************************** FIN TRAVAIL SUR HEAD ****************************/

	public static boolean isNullOrEmpty(String str) {
		if(str != null && !str.trim().isEmpty())
			return false;
		return true;
	}

	public ArrayList<String> addRetourLignetoRow(ArrayList<String> list){
		ArrayList<String> resultat = new ArrayList<String>();
		String[] result = new String[list.size()];
		for(int i=0; i<list.size();i++) {
			String[]separateur = list.get(i).split("\\|\\|");
			for(int j=0; j<separateur.length;j++) {
				separateur[j] = separateur[j] + "\n";
				result[i] += separateur[j];	
			}
			resultat.add(result[i]);
		}
		return resultat;
	}


	public FormatWikitext getRow() {
		FormatWikitext fw = getTableau();
		String[] separateur = fw.wikitext.split("\\|\\-");
		ArrayList <String> list = new ArrayList<String>(); //on ajoute les séparateurs dans la liste pour remove ce qu'il y a en trop
		for(int i=0 ; i<separateur.length;i++) {
			list.add(separateur[i]);
		}
		/** List = separateur, on travaille donc uniquement sur la liste à partir de maintenant**/

		if(list.get(0).contains("class=")) {
			list.remove(0);
		}
		if(list.get(0).contains("!")) {
			list.remove(0);
		}
		if(isNullOrEmpty(list.get(0))) {
			list.remove(0);
		}
		list = addRetourLignetoRow(list);

		int nb_elements = list.get(0).split("[\n]").length-1;
		ArrayList<String>ligne = new ArrayList<String>();

		for(int i=0; i<list.size();i++) {
			String[]sep = list.get(i).split("[\n]");

			for(int j=0;j<nb_elements;j++) { //on parcours chaque élément de la ligne

				/** ON TRAITE TOUT LES CARACTERE SPECIAUX **/
				sep[j+1] = sep[j+1].replaceAll(",", "");
				sep[j+1] = sep[j+1].replaceAll("ref&gt;[^>]*/ref&gt;", "");
				sep[j+1] = sep[j+1].replaceAll("&lt;ref[^>]*/ref&gt;", "");
				sep[j+1] = sep[j+1].replaceAll("&lt;", "");
				sep[j+1] = sep[j+1].replaceAll("&lt;", "");
				sep[j+1] = sep[j+1].replaceAll("&gt;", "");
				sep[j+1] = sep[j+1].replaceAll("align=\"left\"", "");
				sep[j+1] = sep[j+1].replaceAll("br/&gt;", "");
				sep[j+1] = sep[j+1].replaceAll("&lt;br/&gt;", "");
				sep[j+1] = sep[j+1].replaceAll("&lt;ref&gt;", "");
				sep[j+1] = sep[j+1].replaceAll("&lt;br /&gt;", "");
				sep[j+1] = sep[j+1].replaceAll("br /&gt;", "");
				sep[j+1] = sep[j+1].replaceAll("&amp;nbsp;", "");
				sep[j+1] = sep[j+1].replaceAll("&amp", "");
				sep[j+1] = sep[j+1].replaceAll("Color[^>]*darkgray", "");


				sep[j+1] = sep[j+1].replaceAll("[^\\wâäÀÂéèêëÈÊËìîïÌÏÎòöôÒÖÔùüûÙÜÛ!#$€%&'`();:/@...]", " ");





				ligne.add(sep[j+1]);
				ligne.add(",");
			}
			ligne.add("\n");
		}
		String[]tableau = new String[list.size()];
		tableau = ligne.toArray(tableau);



		for(int i=0; i<tableau.length;i++) {
			System.out.print(tableau[i]);
		}

		return fw;
	}

	public ArrayList<String> deletealtref(ArrayList<String> list){
		ArrayList<String> l = new ArrayList<String>();
		String[] copie = new String[list.size()];
		for(int i =0; i<list.size();i++) {
			if(list.get(i).contains("ref")) {
				String[]t1 = list.get(i).split("&lt;ref&gt;");
				String[]t2 = list.get(i).split("&lt;/ref&gt;");
				copie[i] = t1[0] + t2[1];
				l.add(copie[i]);
			}
		}
		return l;
	}




	//<th[^>]*>
	/**************************DEBUT TRAVAIL SUR LIGNES *********************/



	public ProductionCSV rowToCSV() {
		FormatWikitext wikitext = getRow();
		ProductionCSV prod  = new ProductionCSV("");
		String[] res = wikitext.wikitext.split(",");
		String stringCSV = "";
		for(String str : res){
			if(str != null)
				stringCSV += str + "," ;
		}
		prod.csv = stringCSV;
		prod.csv = prod.csv.replaceAll("  ", " ");
		return prod;
	}

	/************************* FIN TRAITEMENT LIGNES ********************/

	/*public void ToCSV(FormatWikitext head, FormatWikitext row) throws IOException {
		String result = "";
		ProductionCSV header = headToCSV(head);
		ProductionCSV body = rowToCSV(row);
		result = (header.csv + "\n" +body.csv);
		ProductionCSV prod = new ProductionCSV(result);
		prod.generateCSV("test", 0);
		System.out.println("Fichier créé avec succès.");
	}*/

	public void ToCSV() throws IOException {
		String result = "";
		FormatWikitext clone = clone();
		String[] separateur = clone.wikitext.split("wikitable");
		int nbtab = separateur.length -1;
		String title = getTitle();
		for(int i = 0; i< nbtab; i++){
			tabCourant = i +1;
			ProductionCSV head = headToCSV();
			ProductionCSV body = rowToCSV();
			result = (head.csv + "\n" +body.csv);
			ProductionCSV prod = new ProductionCSV(result);
			prod.generateCSV(title, tabCourant);
			System.out.println("Fichier créé avec succès.");
		}
	}

	public String getTitle() {
		FormatWikitext clone = clone();
		String[] first = clone.wikitext.split("<title>");
		first = first[1].split("</title>");
		return first[0];
	}


	public FormatWikitext clone() {
		FormatWikitext clone = new FormatWikitext(this.wikitext);
		return clone;
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
