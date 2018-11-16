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
	
	public ProductionCSV ToCSV(FormatHTML htmlFormat) {
		// TODO implement me
		return null;
	}
	
	
	//remplace le return par un String tab[] on aura tout les tableaux de la page
	
	/*début : wikitable
	 * fin : à modifier
	 */
	
	public FormatHTML PremierSplit() {
		keep = this.html;
		FormatHTML clone = clone();
		String[] separateur = clone.html.split("table class=\"wikitable");
		/*ArrayList<String> pageWikitable = new ArrayList<String>();
		    for(String clear : pageWikitable) {
		        if (clear.startsWith("wikitable")) {
		            pageWikitable.add(clear);
		    }
		}*/
		nbtab = separateur.length -1;
		FormatHTML result = new FormatHTML(separateur[1]);
		//System.out.println(separateur[1]);
		return result;
	}
	
	public FormatHTML SecondSplit() {
		FormatHTML html = PremierSplit();
		String[] separateur = html.html.split("</tbody>");
		FormatHTML result = new FormatHTML(separateur[0]);
		//System.out.println(separateur[0]);
		return result;
	}
	
	
	public FormatHTML headSplit() {
		FormatHTML html = SecondSplit();
		String [] separateur = html.html.split("<tr>");
		FormatHTML result = new FormatHTML(separateur[1]);
		//System.out.println(separateur[1]);
		return result;		
	}
	
	public int NombreCol() {
		FormatHTML clone = headSplit();
		int result =0;
		String[] nbcol = clone.html.split("<th ");
		result = nbcol.length -1;
		return result;
	}
	
	
	public FormatHTML headParse() {
		FormatHTML html = headSplit();
		Document doc = Jsoup.parse(html.html);
		//System.out.println(doc.html());
		FormatHTML result = new FormatHTML();
		Elements rows = doc.getAllElements();
		
		/*for (Element row : rows ) {
			result.html += row.text();
			//System.out.println(row.text());

		}
		result.html = doc.html();*/
		Element row = rows.first();
		result.html = row.text();
		//System.out.println(result.html);
		return result;	
	}
	
	public ProductionCSV headToCSV() {
		int nbcol = NombreCol();
		FormatHTML result = headParse();
		String head = "";
		int i = 0;
		String[] test = result.html.split(" ");
	for(String st : test) {
			Pattern p = Pattern.compile("\\p{Upper}", Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
			Pattern p1 = Pattern.compile("\\p{Digit}", Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
			Pattern p2 = Pattern.compile("\\p{Punct}", Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
			if(i == 0) {
				head += st;
				i++;
			}
			else if(p2.matcher(st).find()){
				head += " " + st;
			}
				
			else if(p.matcher(st).find() || p1.matcher(st).find()) {
				head += ("," + st);
			}
			else {
				head += " " + st;
			}
		}
		ProductionCSV csvhead = new ProductionCSV(head);
		return csvhead;
	}
	
	public FormatHTML BodySplit() {
		FormatHTML html = SecondSplit();
		String[] separateur = html.html.split("<tr>");
		String st ="";
		FormatHTML result = new FormatHTML(st);
		FormatHTML result1 = new FormatHTML();
		for(int i = 2; i< separateur.length; i++) {
			st = separateur[i];
			result = new FormatHTML(st);
			Document doc = Jsoup.parse(result.html);
			Elements rows = doc.getAllElements();
			Element row = rows.first();
			result1.html +=  row.text() + "\n";
		}
		System.out.print(result1.html);
		return result1;
	}
	
	public FormatHTML BodyParse() {
		FormatHTML html = BodySplit();
		Document doc = Jsoup.parse(html.html);
		//System.out.println(doc.html());
		FormatHTML result = new FormatHTML();
		Elements rows = doc.getAllElements();
		Element row = rows.first();
		result.html = row.text();
		System.out.println(result.html);
		return result;	
	}
	

	
	public FormatHTML PremierParse() throws IOException {
		
	    File file = new File("test.txt");
	    file.createNewFile();
	    FileWriter writer = new FileWriter(file);

		
		Document doc = Jsoup.parse(this.html);
		//System.out.println(doc.html());
		FormatHTML result = new FormatHTML();
		Elements rows = doc.getElementsByTag("a");
		for(Element row : rows) {
			System.out.println(row.text());
			Elements cells = row.getElementsByTag("th");
			result.html += row.text();
			
			writer.write(row.text().concat(", "));
			writer.write("\n");
			for(Element cell : cells) {
				
				//System.out.println(cell.text());
				
			}
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

