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
	final FormatWikitext wikitext = new FormatWikitext();

	
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
		wikitext.initialize(url);
} 
	
	
	
}
