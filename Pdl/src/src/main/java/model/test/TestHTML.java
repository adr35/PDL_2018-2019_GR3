package src.main.java.model.test;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Before;
import org.junit.Test;

import src.main.java.model.FormatHTML;
import src.main.java.model.Url;

/**
 * Classe de tests de la classe FormatHTML
 * 
 * @author Romiche
 *
 */
public class TestHTML {

	final Url url = new Url("https://fr.wikipedia.org/wiki/Coupe du monde de football");
	private FormatHTML html = new FormatHTML();

	/**
	 * Methode permettant la simplification de la classe de Test
	 * @throws IOException
	 */
	@Before
	public void need() throws IOException {
		String HTML = url.HTML();
		FormatHTML test = new FormatHTML(HTML);
		html = test;
	}

	@Test
	public void Testhtml() throws IOException {
		html = html.PremierSplit();
		System.out.println(html.PremierSplit().toString());
		html = html.SecondSplit();
		html = html.headSplit();
		html = html.headParse();
		// test = test.PremierParse();
		assertEquals("", html.html, "");
	}
	
	@Test
	public void TestTitle() {
		assertEquals(html.getTitle(),"Coupe du monde de football — Wikipédia");
	}
	
	@Test
	public void TestNbColonnes() {
		System.out.println(html.NombreCol());
		assertEquals(html.NombreCol(), 10);
	}
}
