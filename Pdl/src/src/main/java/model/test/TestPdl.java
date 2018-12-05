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
				
				FormatWikitext head = new FormatWikitext(HTML2);
				FormatWikitext lignes = new FormatWikitext(HTML2);
				//wikisecondsplit -> wikipremiersplit
				// HeadtoCSV -> wikiHeadParse -> wikiheadpremiersplit -> splittab 
				head = head.wikisplit();
				lignes = lignes.wikisplit();
				Url urlHead = new Url(head.wikitext);
				Url urlLignes = new Url(lignes.wikitext);
				String HTML3 = urlHead.HTML();
				String HTML4 = urlLignes.HTML();
				FormatWikitext head2 = new FormatWikitext(HTML3);
				FormatWikitext lignes2 = new FormatWikitext(HTML4);
			    head2 = head2.wikiSecondSplit();
			    lignes2 = lignes2.wikiSecondSplit();
			    lignes2.ToCSV();
			    //ProductionCSV prodHead = head2.headToCSV();
				System.out.println("Tableau entier : ");
				System.out.println("\n" + "Head : ");
				System.out.println("Lignes : ");
				//lignes2 = lignes2.wikiRowPremierSplit();
				//ProductionCSV prodLignes = lignes2.rowToCSV();
				//System.out.println(lignes2.wikitext.toString());
}
		
	
	

}

