package src.main.java.model.test;

import static org.junit.Assert.*;

import java.io.*;
import org.junit.Test;

import src.main.java.model.FormatWikitext;
import src.main.java.model.Url;

/**
 * Classe de tests de la classe FormatWikitext
 * @author Romiche
 *
 */
public class TestWikitext {
	
	final Url url = new Url("https://fr.wikipedia.org/wiki/Coupe du monde de football");
	final Url url2 = new Url("https://fr.wikipedia.org/wiki/Internationaux de France de tennis");
	final Url url3 = new Url("https://fr.wikipedia.org/wiki/Liste_des_pr%C3%A9sidents_des_%C3%89tats-Unis");
	
	/**
	 * 
	 * @throws IOException
	 */
	@Test
	public void testwiki() throws IOException {
		String HTML = url.HTML();
		String HTML2 = url2.HTML();

		FormatWikitext test = new FormatWikitext(HTML);
		FormatWikitext test2 = new FormatWikitext(HTML2);

		// test = test.wikisplit();
		// test = test.wikipremierSplit();
		// test = test.wikisecondSplit();
		// test = test.wikiheadSplit();
		// System.out.println("");
		test2 = test2.wikisplit();
		// System.out.print(test2.wikitext.toString());
		Url url3 = new Url(test2.wikitext.toString());
		System.out.print(url3.url.toString());
		String HTML3 = url3.HTML();
		// System.out.print(HTML3);
		FormatWikitext test3 = new FormatWikitext(HTML3);
		int nbtab = 0;
		nbtab = test3.wikiCountTabs();
		System.out.println("");
		System.out.println("Nombre de tableaux sur cette page :" + nbtab);
		// test3 = test3.wikiPremierSplit();
		// System.out.print(test3.wikitext.toString());
		// test3 = test3.wikiSecondSplit();
		// System.out.print(test3.wikitext.toString());
		//test3 = test3.wikiFirstTab();
		System.out.println("Tableau entier : ");
		// System.out.println(test3.wikitext.toString());
		// test3 = test3.printsplitTab();
		// System.out.println(test3.wikitext.toString());
		System.out.println("Head : ");
		test3 = test3.wikiHeadSecondSplit(); // mettre en commentaire si wikiHeadParse() activï¿½
		System.out.println(test3.wikitext.toString());
		// test3 = test3.wikiHeadParse();
		// System.out.println(test3.wikitext.toString());
		// test3 = test3.wikiSplitHeadParse();
		// System.out.println(test3.wikitext.toString());
		// test2= test.wikiheadParse();
		// test3 = test3.wikiRowSplit(); //mettre en commentaire si wikiRowParse()
		// activï¿½
		System.out.println("Lignes : ");
		// System.out.println(test3.wikitext.toString());
		//test3 = test3.splitrow();
		System.out.println(test3.wikitext.toString());
		// test3 = test3.wikiSplitRowParse();
		// System.out.println(test3.wikitext.toString());
		assertEquals("", test2.wikitext, "");
	}
}
