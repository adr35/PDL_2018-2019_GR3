package src.main.java.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
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

public class FormatHTML
{
	
	
	public String html;
	private int nbtab;
	private int tabCourant;
	
	public FormatHTML(){
		this.html = new String();
	}
	
	public FormatHTML(String html){
		this.html = html;
	}
	

	/**
	 * <!-- begin-user-doc -->
	 * Renvoie la production sous format CSV de tous les tableaux de la page html courante.
	 * Fait un premier tour dans la page html courante afin de dÃ©terminer le nombre de tableaux Ã  convertir.
	 * Ensuite boucle afin de traiter les tableaux 1 par 1
	 * Puis les ajoute Ã  la production CSVfinal,
	 * On distingue deux traitements differents,celle de la tete du tableau, et celle du corps.
	 * <!--  end-user-doc  -->
	 * @throws IOException 
	 * @generated
	 * @ordered
	 */
	
	public void ToCSV() throws IOException {
		String result = "";
		FormatHTML clone = clone();
		String[] separateur = clone.html.split("table class=\"wikitable");
		nbtab = separateur.length -1;
		String title = getTitle();
		for(int i = 0; i< nbtab; i++){
			tabCourant = i +1;
			ProductionCSV head = headToCSV();
			ProductionCSV body = BodyToCSV();
			result = (head.csv + "\n" +body.csv);
			ProductionCSV prod = new ProductionCSV(result);
			prod.generateCSV(title, tabCourant);
		}
	}
	
	
	public String getTitle() {
		FormatHTML clone = clone();
		String[] first = clone.html.split("<title>");
		first = first[1].split("</title>");
		return first[0];
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * Renvoie un FormatHTML commencant par "table classe="wikitable"" qui correspond au debut d'un tableau wikipedia.
	 * On clone un format HTML afin de ne pas modifier la page initiale.
	 * La fin du FormatHTML se caracterise par le debut d'un nouveau tableau, ou la fin de la page dans le cas 
	 * ou le tableau courant est le dernier tableau de la page.
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	public FormatHTML PremierSplit() {
		FormatHTML clone = clone();
		String[] separateur = clone.html.split("table class=\"wikitable");
		FormatHTML result = new FormatHTML(separateur[tabCourant]);
		return result;
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * Renvoie l'intÃ©rieur du tableau obtenu par PremierSplit()
	 * Pour cela on recupere tout ce qu'il y a entre le debut du tableau et la fin caracterise par </tbody>
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	public FormatHTML SecondSplit() {
		FormatHTML html = PremierSplit();
		String[] separateur = html.html.split("</tbody>");
		FormatHTML result = new FormatHTML(separateur[0]);
		return result;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * Renvoie le nombre de colonne du tableau courant
	 * les colonnes sont caracterisees par : <th>
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	public int NombreCol() {
		FormatHTML clone = headSplit();
		int result =0;
		String[] nbcol = clone.html.split("<th ");
		result = nbcol.length -1;
		return result;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * Ici commence le traitement de la tete.
	 * Les differentes lignes du tableau sont caracterisees par <tr>.
	 * Et les noms des colonnes sont notÃ©s dans la premiere ligne du tableau.
	 * C est pourquoi nous recuperons seulement la premiere ligne
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	
	public FormatHTML headSplit() {
		FormatHTML html = SecondSplit();
		html.html = html.html.replaceAll("<tr [^>]*>", "<tr>");
		String [] separateur = html.html.split("<tr>");
		String res = separateur[1]; 
		for(int i = 2; i < separateur.length ;i++){
			if(!separateur[i].contains("<td")){
				res += "NOUVLIGNE\n" + separateur[i];
			}
		}
		FormatHTML result = new FormatHTML(res);
		return result;		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	public FormatHTML getSpan() {
		FormatHTML html = headSplit().clone();
		String[] numCol = html.html.split("<th [^>]*>");
		String[] resultConcat = html.html.split("<th [^>]*>");
		String result = numCol[0];
		for (int i = 1; i < numCol.length; i++){	
			String row = numCol[i];
			String col = numCol[i];
			String numberRow = "";
			String numberCol = "";
			if(row.contains("rowspan=")){
				String[] replacerow = html.html.split("rowspan=\"");
				String[] tabnumberRow = replacerow[1].split("\"");
				numberRow = "rowDBTR" + tabnumberRow[0]; 
			}
			if(row.contains("colspan=")){
				String[] replaceCol = html.html.split("colspan=\"");
				String[] tabnumberCol = replaceCol[1].split("\"");
				numberCol = "rowDBTC" +tabnumberCol[0]; 
			}
			resultConcat[i] = ("<th>" + numberRow + " " + numberCol + " " + resultConcat[i]);
		}
		FormatHTML res = new FormatHTML();
		for(String st : resultConcat){
			res.html += st;
		}
		return res;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * Renvoie l'interieur de chaque balise, chaque colonne commencant par DEBUTDECASE
	 * La premiere ligne recuperee par : headsplit va etre traitee.
	 * pour cela on va remplacer l'interieur d'une balise en ajoutant au debut : DEBUTDECASE
	 * afin de s'assurer que l'on prenne bien l'integralite de chaque colonne.
	 * Ensuite l'html ainsi modifie est parser a l'aide de JSOUP pour enfin effectuer la recuperation des colonnes.
	 * On remplace ensuite le premier DEBUTDECASE pour s'ajuster au format CSV
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	public FormatHTML headParse() {
		FormatHTML html = getSpan();
		String replaceString=html.html.replaceAll("<th>","<th>DEBUTDECASE ");
		//System.out.println(replaceString);
		FormatHTML result = new FormatHTML(replaceString);
		Document doc = Jsoup.parse(result.html);
		Elements rows = doc.getAllElements();
		Element row = rows.first();
		String line = row.text();
		String replaceline = line.replaceFirst("DEBUTDECASE ", "");
		result.html =  replaceline;
		return result;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * Renvoie au format CSV la tete du tableau courant
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	public ProductionCSV headToCSV() {
		FormatHTML html = headParse();
		String result = html.html.replaceAll(" DEBUTDECASE", ", ");
		result = result.replaceAll("DEBUTDECASE ", ", ");
		String verif = result.replaceAll("  ", " ");
		ProductionCSV prod  = new ProductionCSV("");
		if(verif.contains("rowDBTR") || verif.contains("rowDBTC")){
			String[] span = verif.split(" NOUVLIGNE, ");	
			String[] splitline = span[1].split(", ");
			String test = "";
			int cpt = 0;
			String[] res = new String[span.length];
			for(int i = 0; i< span.length -1; i++){	
				Boolean first = true;
				String[] modif = span[i].split("row");
				for(int j = 0; j < modif.length; j++){
					if(modif[j].length() > 7){ //taille pour svaoir si la ligne est en fait une colonne vide ou non (> 7 == non vide
					if(modif[j].startsWith("DBTR")){
						int number =  Integer.parseInt("" + modif[j].charAt(4));
						if(first){
							res[i] = modif[j].substring(6);
							first = false;
							test += " ";
							for(int k = 2; k<number; k++){
								test += ", ";
							}
						}
						else {
							res[i] += modif[j].substring(5);
							for(int k = 1; k<number; k++){
								test += ", ";
							}
						}	
					}
					if(modif[j].startsWith("DBTC")){
						int number =  Integer.parseInt("" + modif[j].charAt(4));
						boolean devant = true;
						int derriereToAdd = 0;
						for(int k = 0; k<number; k++){
							test +=  ", " + splitline[cpt];
							cpt ++;
							if(k >= 1){
								if(devant){
									res[i]+= ", ";
									devant = false;
								}
								else{
									derriereToAdd ++;
									devant = true;
								}
							}
						}
						if(first){
							res[i] = modif[j].substring(6);
							first = false;
						}
						else
						res[i] += modif[j].substring(5);
						for(int k =0; k<derriereToAdd;k ++){
							res[i] += ", ";
						}
					}
				}
				}
			}
			String stringCSV = "";
			for(String str : res){
				if(str != null)
					stringCSV += str;
			}
			stringCSV += "\n" + test;
			prod.csv = stringCSV;
		}
		else{
			prod.csv = verif;
		}
		prod.csv = prod.csv.replaceAll("NOUVLIGNE", "\n");
		prod.csv = prod.csv.replaceAll("  ", " ");
		return prod;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * Renvoie l'interieur de chaque balise pour chaque ligne du tableau, chaque colonne commencant par DEBUTDECASE et de DEBUTIMAGE
	 * Nous allons traiter ligne par ligne le body du tableau courant.
	 * pour cela on va remplacer l'interieur d'une balise en ajoutant au debut : DEBUTDECASE et DEBUTIMAGE si une image est presente
	 * afin de s'assurer que l'on prenne bien l'integralite de chaque colonne.
	 * Ensuite l'html ainsi modifie est parser a l'aide de JSOUP pour enfin effectuer la recuperation des colonnes.
	 * On remplace ensuite le premier DEBUTDECASE et DEBUTIMAGE pour s'ajuster au format CSV
	 * Enfin on ajoute chaques lignes ainsi obtenues a la suite pour former le corps du tableau
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	public FormatHTML BodySplit() {
		FormatHTML html = SecondSplit();
		String[] separateur = html.html.split("<tr>");
		String st ="";
		FormatHTML result = new FormatHTML(st);
		FormatHTML result1 = new FormatHTML();
		for(int i = 2; i< separateur.length; i++) {
			separateur[i] = separateur[i].replaceAll("<th [^>]* >", "<td>");
			separateur[i] = separateur[i].replaceAll("\th>", "\td>");
			if(separateur[i].contains("<td>")){
				st = separateur[i];
				String replaceString=st.replaceAll("<td[^>]*>","<td>DEBUTDECASE ");
				
				String regex = "<img ";
				String replaceStringImg = replaceString;
				if(replaceString.contains(regex)) {
					replaceStringImg = replaceString.replace("<img", "DEBUTIMAGE <img ");
				}
				replaceStringImg = replaceStringImg.replaceAll(",","");
				result = new FormatHTML(replaceStringImg);
				Document doc = Jsoup.parse(result.html);
				Elements rows = doc.getAllElements();
				Element row = rows.first();
				Elements im = doc.select("img");
				List<String> listIm= im.eachAttr("src");
				String line = row.text();
				String replaceline = line.replaceFirst("DEBUTDECASE ", "");
				String replaceIMG = replaceline;
				for(String str : listIm) {
					replaceIMG = replaceIMG.replaceFirst("DEBUTIMAGE ", "IMG" + " "); //remplacer "IMG" par str pour url image
				}
				result1.html +=  replaceIMG + "\n";
			}
		}
		return result1;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * Ajuste au format CSV le corps du tableau obtenu
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	public ProductionCSV BodyToCSV() {
		FormatHTML html = BodySplit();
		String result = html.html.replaceAll(" DEBUTDECASE", ", ");
		result =result.replaceAll("DEBUTDECASE", ", ");
		String verif = result.replaceAll("  ", " ");
		ProductionCSV prod  = new ProductionCSV(verif);
		return prod;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	

	
	public FormatHTML PremierParse() throws IOException {
		
	    File file = new File("test.txt");
	    file.createNewFile();
	    FileWriter writer = new FileWriter(file);

		
		Document doc = Jsoup.parse(this.html);
		FormatHTML result = new FormatHTML();
		Elements rows = doc.getElementsByTag("a");
		for(Element row : rows) {
			System.out.println(row.text());
			Elements cells = row.getElementsByTag("th");
			result.html += row.text();
			
			writer.write(row.text().concat(", "));
			writer.write("\n");
		}
		result.html = doc.html();
		writer.close();
		return result;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * Permet de cloner le FORMATHTML.
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	public FormatHTML clone() {
		FormatHTML clone = new FormatHTML(this.html);
		return clone;
	}

	@Override
	public String toString() {
		return "FormatHTML [html=" + html + ", nbtab=" + nbtab + ", tabCourant=" + tabCourant + "]";
	}
	
	

}
