package src.main.java.model.test;
import static org.junit.Assert.*;

import java.io.*;
import org.junit.Test;

import src.main.java.model.Fichier;
import src.main.java.model.FormatWikitext;
import src.main.java.model.ProductionCSV;
import src.main.java.model.Url;

/**
 * Classe de tests prévisionnels
 * @author Romiche
 *
 */

@Test
	public void testwiki() throws IOException {
		
		Url url = new Url("https://en.wikipedia.org/wiki/Comparison_of_alphabetic_country_codes" );

		String HTML = url.HTML(); //Récupère le code HTML de la page
		FormatWikitext newUrl = new FormatWikitext(HTML);
		newUrl = newUrl.recupererURL();

		Url urlFormatWikitext = new Url(newUrl.wikitext); //creer un formatwikitext avec le code source contenant le wikitext
		String WIKITEXT = urlFormatWikitext.HTML();
		FormatWikitext newFormatWikitextHead = new FormatWikitext(WIKITEXT);
		//newFormatWikitextHead.allTabs();
		//newFormatWikitextHead = newFormatWikitextHead.getTableau();
		newFormatWikitextHead.getHead1();
			//newFormatWikitextHead=newFormatWikitextHead.getHead();
			/* -> Ici on recupère le head */

			FormatWikitext newFormatWikitextRow = new FormatWikitext(WIKITEXT);
			newFormatWikitextRow = newFormatWikitextRow.getRow();
			//newFormatWikitextRow = newFormatWikitextRow.wikiRowPremierSplit();
			/* -> Ici on récupère les rows */

			//newFormatWikitextHead.ToCSV(); // on créé un CSV head + rows
		//System.out.println(newFormatWikitextRow.wikitext);
		Fichier f = new Fichier();

	}
public class TestPdl extends Thread{

	final Url url = new Url("https://fr.wikipedia.org/wiki/Coupe du monde de football");
	final Url url2 = new Url("https://fr.wikipedia.org/wiki/Internationaux de France de tennis");
	final Url url3 = new Url("https://fr.wikipedia.org/wiki/Liste_des_pr%C3%A9sidents_des_%C3%89tats-Unis");
	
	final static File Wikiurls = new File("wikiurls.txt");
	//final static File coupeDuMondeHTML = new File("html.txt");
	//final static File presidentUSA = new File("HTML.txt");
	
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/

	 
		
	 /*@Test
		public void testUrl() throws IOException {
		 String HTML = url.HTML();
		 FormatHTML test = new FormatHTML(HTML);
		 test = test.PremierSplit();
		 test = test.SecondSplit();
		 test = test.headSplit();
		 test = test.headParse();
		 //test = test.PremierParse();
		 assertEquals("", test.html, "");
		 
}*/
	
			 @Test
		 public void testHtml1() throws IOException, InterruptedException {
			 Url url = new Url("https://fr.wikipedia.org/wiki/Coupe_du_monde_de_football");
			 String HTML = url.HTML();
			 FormatHTML ht = new FormatHTML(HTML);
			 assertEquals(ht.getNbTab(),7);
			 ht.ToCSV();
			
			// System.out.println(ht.html);
			 
			 
		 }
		 
			 
			 @Test
			 public void testHtml2() throws IOException, InterruptedException {
				 Url url = new Url("https://fr.wikipedia.org/wiki/Liste_des_présidents_des_États-Unis");
				 String HTML = url.HTML();
				 FormatHTML ht = new FormatHTML(HTML);
				 assertEquals(ht.getNbTab(),1);
				 ht.ToCSV();
				
				 
				 
			 }
		 
				@Test
				public void testwiki() throws IOException {
				Url url = new Url("https://en.wikipedia.org/wiki/Comparison_of_Linux_distributions" );

				String HTML = url.HTML(); //Récupère le code HTML de la page
				FormatWikitext newUrl = new FormatWikitext(HTML);
				newUrl = newUrl.recupererURL();
				
				Url urlFormatWikitext = new Url(newUrl.wikitext); //creer un formatwikitext avec le code source contenant le wikitext
				String WIKITEXT = urlFormatWikitext.HTML();
				FormatWikitext newFormatWikitextHead = new FormatWikitext(WIKITEXT);
				newFormatWikitextHead = newFormatWikitextHead.getTableau();
				newFormatWikitextHead=newFormatWikitextHead.getHead();
				/* -> Ici on recupère le head */
				
				FormatWikitext newFormatWikitextRow = new FormatWikitext(WIKITEXT);
				newFormatWikitextRow = newFormatWikitextRow.getTableau();
				newFormatWikitextRow = newFormatWikitextRow.wikiRowPremierSplit();
				/* -> Ici on récupère les rows */
				
				FormatWikitext t = new FormatWikitext();
				t.ToCSV(newFormatWikitextHead, newFormatWikitextRow); // on créé un CSV head + rows
				//System.out.println(newFormatWikitextRow.wikitext);
				Fichier f = new Fichier();

}
		
	
	

}

