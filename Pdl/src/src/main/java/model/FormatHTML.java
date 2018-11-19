package src.main.java.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
	private String keep;
	private int nbtab;
	
	public FormatHTML(){
		this.html = new String();
	}
	
	public FormatHTML(String html){
		this.html = html;
	}
	
	public String getkeep() {
		return keep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * Renvoie la prodction CSV correspondant au tableau de la page HTML
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ProductionCSV ToCSV() {
		ProductionCSV head = headToCSV();
		ProductionCSV body = BodyToCSV();
		ProductionCSV prod = new ProductionCSV(head.csv + "\n"+ body.csv);
		System.out.print(prod.csv);
		return prod;
	}
	
	
	//remplace le return par un String tab[] on aura tout les tableaux de la page
	
	/*dÃ©but : wikitable
	 * fin : Ã  modifier
	 */
	
	public FormatHTML PremierSplit() {
		keep = this.html;
		FormatHTML clone = clone();
		String[] separateur = clone.html.split("table class=\"wikitable");
		nbtab = separateur.length -1;
		FormatHTML result = new FormatHTML(separateur[1]);
		return result;
	}
	
	public FormatHTML SecondSplit() {
		FormatHTML html = PremierSplit();
		String[] separateur = html.html.split("</tbody>");
		FormatHTML result = new FormatHTML(separateur[0]);
		return result;
	}
	
	public int NombreCol() {
		FormatHTML clone = headSplit();
		int result =0;
		String[] nbcol = clone.html.split("<th ");
		result = nbcol.length -1;
		return result;
	}
	
	
	public FormatHTML headSplit() {
		FormatHTML html = SecondSplit();
		String [] separateur = html.html.split("<tr>");
		FormatHTML result = new FormatHTML(separateur[1]);
		return result;		
	}
	
	
	public FormatHTML headParse() {
		FormatHTML html = headSplit();
		//String replaceString=html.html.replaceAll("<th scope=\"col\">","<th scope=\"col\">DEBUTDECASE ");
		String replaceString=html.html.replaceAll("<th scope=\"col\"[^>]*>","<th scope=\"col\">DEBUTDECASE ");
		FormatHTML result = new FormatHTML(replaceString);
		Document doc = Jsoup.parse(result.html);
		Elements rows = doc.getAllElements();
		Element row = rows.first();
		String line = row.text();
		String replaceline = line.replaceFirst("DEBUTDECASE ", "");
		result.html =  replaceline;
		return result;	
	}
	
	public ProductionCSV headToCSV() {
		FormatHTML html = headParse();
		String result = html.html.replaceAll(" DEBUTDECASE", ", ");
		String verif = result.replaceAll("  ", " ");
		ProductionCSV prod  = new ProductionCSV(verif);
		return prod;
	}
	
	public FormatHTML BodySplit() {
		FormatHTML html = SecondSplit();
		String[] separateur = html.html.split("<tr>");
		String st ="";
		FormatHTML result = new FormatHTML(st);
		FormatHTML result1 = new FormatHTML();
		for(int i = 2; i< separateur.length; i++) {
			st = separateur[i];
	
			String replaceString=st.replaceAll("<td>","<td>DEBUTDECASE ");
		
			result = new FormatHTML(replaceString);
			Document doc = Jsoup.parse(result.html);
			Elements rows = doc.getAllElements();
			Element row = rows.first();
			String line = row.text();
			String replaceline = line.replaceFirst("DEBUTDECASE ", "");
			result1.html +=  replaceline + "\n";
		}
		return result1;
	}
	
	public ProductionCSV BodyToCSV() {
		FormatHTML html = BodySplit();
		String result = html.html.replaceAll(" DEBUTDECASE", ", ");
		String verif = result.replaceAll("  ", " ");
		ProductionCSV prod  = new ProductionCSV(verif);
		return prod;
	}
	

	
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
	
	public FormatHTML clone() {
		FormatHTML clone = new FormatHTML(this.html);
		return clone;
	}

}

