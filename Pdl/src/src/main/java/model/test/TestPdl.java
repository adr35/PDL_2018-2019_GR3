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
			public void testwiki() throws IOException {
				Url url = new Url("https://fr.wikipedia.org/w/index.php?title=Internationaux de France de tennis&action=edit");
				Url url2 = new Url("https://en.wikipedia.org/wiki/Comparison_of_BitTorrent_tracker_software");
				//https://en.wikipedia.org/wiki/Comparison_of_CRT,_LCD,_and_plasma
				String HTML2 = url2.HTML();
				
				FormatWikitext test2 = new FormatWikitext(HTML2);
				FormatWikitext lignes = new FormatWikitext(HTML2);
				
				//test = test.wikisplit();
				//test = test.wikipremierSplit();
				//test = test.wikisecondSplit();
				//test = test.wikiheadSplit();
				//System.out.println("");
				//test2 = test2.wikisplit();
				lignes = lignes.wikisplit();
				//System.out.print(test2.wikitext.toString());
				Url url3 = new Url(lignes.wikitext);
				//System.out.println(url3.url.toString());
				//System.out.println(lignes.wikitext.toString());
				Url urlLignes = new Url(lignes.wikitext);
				//System.out.print(url3.url.toString());
				String HTML3 = url3.HTML();
				String HTML4 = urlLignes.HTML();
				//System.out.print(HTML3);
				FormatWikitext test3 = new FormatWikitext(HTML3);
				FormatWikitext lignes2 = new FormatWikitext(HTML4);
				//ProductionCSV p = new ProductionCSV("coucou");
				//p.generateCSV("cou", 1);
				//test3 = test3.wikiPremierSplit();
				//System.out.print(test3.wikitext.toString());
			    test3 = test3.wikiSecondSplit();
			    ProductionCSV prod = test3.headToCSV();
				//lignes2 = lignes2.wikiSecondSplit();
				//System.out.print(test3.wikitext.toString());
				System.out.println("Tableau entier : ");
				//System.out.println(test3.wikitext.toString());
				//test3 = test3.printsplitRowTab();
				//System.out.println(test3.wikitext.toString());
				//test3 = test3.wikiFirstTab();
				//System.out.println(test3.wikitext.toString());
				System.out.println("\n" + "Head : ");
				//test3 = test3.wikiHeadPremierSplit(); //mettre en commentaire si wikiHeadParse() activé
			//	System.out.println(test3.wikitext.toString());
				//test3 = test3.wikiHeadParse();
				//test3 = test3.headToCSV();
				//System.out.println(test3.wikitext.toString());
				//test3 = test3.wikiSplitHeadParse();
				//System.out.println(test3.wikitext.toString());
				//test3= test.wikiHeadReplace();
				 //mettre en commentaire si wikiRowParse() activé
				System.out.println("Lignes : ");
				//System.out.println(test3.wikitext.toString());
				//lignes2 =lignes2.wikiRowPremierSplit();
				//System.out.println(lignes2.wikitext.toString());
				//lignes2 = lignes2.wikiRowParse();
				//System.out.println(lignes2.wikitext.toString());
			//	assertEquals("", test2.wikitext, "");
}
	
	

}

