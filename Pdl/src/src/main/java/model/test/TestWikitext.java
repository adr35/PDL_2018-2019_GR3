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
 * 
 * @author Romiche
 *
 */
public class TestWikitext extends IOException {

	private final Url url = new Url("https://en.wikipedia.org/wiki/Comparison_of_BitTorrent_tracker_software");
	// final Url url = new
	// Url("https://fr.wikipedia.org/wiki/Internationaux_de_France_de_tennis");
	private FormatWikitext wikitext = new FormatWikitext();

	/**
	 * Methode permettant la simplification de la classe de Test
	 * 
	 * @throws IOException
	 */

	@Before
	public void need() throws IOException {
		String HTML2 = url.HTML();
		FormatWikitext wiki = new FormatWikitext(HTML2);
		wikitext = wiki;
	}

	/**
	 * Test de la methode newUrl()
	 * @return l'Url de la page wikitext après avoir récupérer celui de la page wikipédia correspondante
	 * @throws IOException
	 */
	@Test
	public void TestNewUrl() throws IOException {
		wikitext = wikitext.wikisplit();
		assertEquals(wikitext.wikitext.toString(),
				"https://en.wikipedia.org/w/index.php?title=Comparison of BitTorrent tracker software&action=edit");
	}

	/**
	 * Test général des méthodes wikitext........
	 * @throws IOException
	 */
	@Test
	public void testwiki() throws IOException {
		wikitext.initialize(url);
	}

	/**
	 * Test de la methode wikiCountTabs()
	 * @return le nombre de tableaux présents sur la page wikipédia
	 * @throws IOException
	 */
	@Test
	public void TestNbTableaux() throws IOException {
		assertEquals(wikitext.wikiCountTabs(), 1);
	}

	/**
	 * Test de la méthode wikiNombreLigne()
	 * @return le nombre de lignes de chaque tableau de la page
	 * @throws IOException
	 */
	@Test
	public void TestNbLignes() throws IOException {
		//Méthode à revoir.... car elle split sur les scope=row
		System.out.println(wikitext.wikiNombreLigne());
		assertEquals(wikitext.wikiNombreLigne(), 9);
	}

}
