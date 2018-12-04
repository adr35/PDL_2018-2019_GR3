package src.main.java.model.test;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.main.java.model.FormatWikitext;
import src.main.java.model.ProductionCSV;
import src.main.java.model.Url;

/**
 * Classe de tests de la classe FormatWikitext
 * @author Romiche
 *
 */
public class TestWikitext  {
	
	final Url url = new Url("https://en.wikipedia.org/wiki/Comparison_of_BitTorrent_tracker_software");
	
	/**
	 * 
	 * @throws IOException
	 */
	
	@Test
	public void TestNewUrl() throws IOException {
		String HTML2 = url.HTML();
		FormatWikitext wiki = new FormatWikitext(HTML2);
		wiki = wiki.wikisplit();
		assertEquals(wiki.wikitext.toString(),
				"https://en.wikipedia.org/w/index.php?title=Comparison of BitTorrent tracker software&action=edit");
	}
	
	
	@Test
	public void testwiki() throws IOException {
		String HTML2 = url.HTML();	
		FormatWikitext head = new FormatWikitext(HTML2);
		FormatWikitext lignes = new FormatWikitext(HTML2);	
		head = head.wikisplit();
		lignes = lignes.wikisplit();
		Url urlHead = new Url(head.wikitext);
		Url urlLignes = new Url(lignes.wikitext);
		String HTML3 = urlHead.HTML();
		String HTML4 = urlLignes.HTML();
		FormatWikitext head2 = new FormatWikitext(HTML3);
		FormatWikitext lignes2 = new FormatWikitext(HTML4);
		FormatWikitext result = new FormatWikitext();
	    head2 = head2.wikiSecondSplit();
	    lignes2 = lignes2.wikiSecondSplit();
	    result.wikitext = head2.wikitext + "\n" + lignes2.wikitext;
	    result.ToCSV();
		System.out.println("Tableau entier : ");
		System.out.println("\n" + "Head : ");
		System.out.println("Lignes : ");
}
	
	
	
}
