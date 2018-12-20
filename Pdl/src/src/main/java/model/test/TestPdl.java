package src.main.java.model;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;



public class TestPdl {

	final static File coupeDuMondeHTML = new File("html.txt");
	public List <FormatWikitext> list_head = new ArrayList<FormatWikitext>();
	//final static File presidentUSA = new File("HTML.txt");

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/



	/*@Test
	public void testUrl() throws IOException {
	 Url url = new Url("https://fr.wikipedia.org/wiki/Coupe du monde de football");
	 String HTML = url.HTML();
	 FormatHTML test = new FormatHTML(HTML);
	 test = test.PremierSplit();
	 test = test.SecondSplit();
	 test = test.headSplit();
	 test = test.headParse();
	 //test = test.PremierParse();
	 assertEquals("", test.html, "");

}
	 */
	@Test
	public void testwiki() throws IOException {
		
		Url url = new Url("https://en.wikipedia.org/wiki/Comparison_between_Ido_and_Interlingua" );

		/*String HTML = url.HTML(); //Récupère le code HTML de la page
		FormatWikitext newUrl = new FormatWikitext(HTML);
		newUrl = newUrl.recupererURL();

		Url urlFormatWikitext = new Url(newUrl.wikitext); //creer un formatwikitext avec le code source contenant le wikitext
		String WIKITEXT = urlFormatWikitext.HTML();
		FormatWikitext newFormatWikitextHead = new FormatWikitext(WIKITEXT);
		//newFormatWikitextHead.allTabs();
		//newFormatWikitextHead = newFormatWikitextHead.getTableau();
		newFormatWikitextHead.ToCSV();;*/
			//newFormatWikitextHead=newFormatWikitextHead.getHead();
			/* -> Ici on recupère le head */

			//FormatWikitext newFormatWikitextRow = new FormatWikitext(WIKITEXT);
			//newFormatWikitextRow = newFormatWikitextRow.wikiRowPremierSplit();
			/* -> Ici on récupère les rows */

			//newFormatWikitextHead.ToCSV(); // on créé un CSV head + rows
		//System.out.println(newFormatWikitextRow.wikitext);
		Fichier f = new Fichier();
		f.productUrlsWikitext();
		int i=0;
		for(Url u : f.setUrl) {
			String HTML = u.HTML(); //Récupère le code HTML de la page
			FormatWikitext fw = new FormatWikitext(HTML);
			/*newUrl = newUrl.recupererURL();

			Url urlFormatWikitext = new Url(newUrl.wikitext); //creer un formatwikitext avec le code source contenant le wikitext
			String WIKITEXT = urlFormatWikitext.HTML();
			FormatWikitext newFormatWikitextHead = new FormatWikitext(WIKITEXT);*/
			//newFormatWikitextHead.allTabs();
			//newFormatWikitextHead = newFormatWikitextHead.getTableau();
			fw.ToCSV();
			System.out.println("page " + i + " en cours de traitement");
			i++;
		}

	}

	/*@Test 
	 	public void testFichier() {
		 	Url url = new Url("https://fr.wikipedia.org/wiki/Liste_des_pr%C3%A9sidents_des_%C3%89tats-Unis");
		 	Url url2 = new Url("https://fr.wikipedia.org/wiki/Coupe du monde de football");
		 	String newUrl = new String("https://fr.wikipedia.org/wiki/Liste_des_pr%C3%A9sidents_des_%C3%89tats-Unis");
		 	String newUrl2 = new String("https://fr.wikipedia.org/wiki/Coupe du monde de football");
		 	Fichier fichier = new Fichier();
		 	fichier.addString(newUrl);
			 fichier.addString(newUrl2);			 
		 	//fichier = fichier.addString(newUrl2);
		 	//fichier = fichier.addUrl(url2);
			 System.out.println(fichier.toString());
		 	//System.out.println(url2);

}
	 */	
}
