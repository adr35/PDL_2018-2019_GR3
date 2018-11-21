package src.main.java.model;
import static org.junit.Assert.*;


import java.io.*;
import org.junit.Test;


public class TestPdl extends Thread{

	final Url url = new Url("https://fr.wikipedia.org/wiki/Coupe du monde de football");
	final Url url2 = new Url("https://fr.wikipedia.org/wiki/Internationaux de France de tennis");
	final Url url3 = new Url("https://fr.wikipedia.org/wiki/Liste_des_pr%C3%A9sidents_des_%C3%89tats-Unis");
	
	
	final static File coupeDuMondeHTML = new File("html.txt");
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
		 

	/*@Test
	public void testwiki() throws IOException {
		String HTML = url.HTML();
		String HTML2 = url2.HTML();
		
		FormatWikitext test = new FormatWikitext(HTML);
		FormatWikitext test2 = new FormatWikitext(HTML2);
		
		//test = test.wikisplit();
		//test = test.wikipremierSplit();
		//test = test.wikisecondSplit();
		//test = test.wikiheadSplit();
		//System.out.println("");
		test2 = test2.wikisplit();	
		//System.out.print(test2.wikitext.toString());
		Url url3 = new Url(test2.wikitext.toString());
		System.out.print(url3.url.toString());
		String HTML3 = url3.HTML();
		//System.out.print(HTML3);
		FormatWikitext test3 = new FormatWikitext(HTML3);
		int nbtab = 0;
		nbtab = test3.wikiCountTabs();
		System.out.println("");
		System.out.println("Nombre de tableaux sur cette page :" + nbtab);
		//test3 = test3.wikiPremierSplit();
		//System.out.print(test3.wikitext.toString());
		//test3 = test3.wikiSecondSplit();
		//System.out.print(test3.wikitext.toString());
		test3 = test3.wikiFirstTab();
		System.out.println("Tableau entier : ");
		//System.out.println(test3.wikitext.toString());
		//test3 = test3.printsplitTab();
		//System.out.println(test3.wikitext.toString());
		System.out.println("Head : ");
		test3 = test3.wikiHeadSecondSplit(); //mettre en commentaire si wikiHeadParse() activï¿½
		System.out.println(test3.wikitext.toString());
		//test3 = test3.wikiHeadParse();
		//System.out.println(test3.wikitext.toString());
		//test3 = test3.wikiSplitHeadParse();
		//System.out.println(test3.wikitext.toString());
		//test2= test.wikiheadParse();
		//test3 = test3.wikiRowSplit(); //mettre en commentaire si wikiRowParse() activï¿½
		System.out.println("Lignes : ");
		//System.out.println(test3.wikitext.toString());
		test3 = test3.splitrow();
		System.out.println(test3.wikitext.toString());
		//test3 = test3.wikiSplitRowParse();
		//System.out.println(test3.wikitext.toString());
		assertEquals("", test2.wikitext, "");
}*/
	
	@Test
	public void testFichier() throws Exception {
		Fichier fichier = new Fichier(); // Création d'un fichier
		boolean assert1 = fichier.addUrl(url); // Ajout d'un Url dans le fichier
		boolean assert2 = fichier.addUrl(url2);
		// boolean assert3 = fichier.addUrl(url3);
		System.out.println(fichier.toString());
		boolean assertR = fichier.removeUrl(url);
		System.out.println(fichier.toString());
		assertTrue("Url ajouté", assert1);
		assertTrue("Url2 ajouté", assert2);
		assertTrue("Url supprimé", assertR);
		// assertTrue("Url3 ajouté", assert3);

	}

	@Test
	public void testconnexionUrl() {
		Url url4 = new Url("http://fr.wikipedia.org/wiki/Coupe_du_monde_de_football"); // Lien redirigé
		Url url5 = new Url("http://fr.wikipedia.org/wiki/Coupe de football"); // Lien non valide
		assertTrue(url.isValidUrl());
		assertTrue("Lien redirigé", url4.isValidUrl());
		assertFalse("Lien non valide", url5.isValidUrl());
	}
}

