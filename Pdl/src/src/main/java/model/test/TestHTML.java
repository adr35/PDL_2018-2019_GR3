package src.main.java.model.test;

import static org.junit.Assert.*;

import java.io.*;
import org.junit.Test;

import src.main.java.model.FormatHTML;
import src.main.java.model.Url;

/**
 * Classe de tests de la classe FormatHTML
 * @author Romiche
 *
 */
public class TestHTML {

	final Url url = new Url("https://fr.wikipedia.org/wiki/Coupe du monde de football");
	
	/**
	 * 
	 * @throws IOException
	 */
	@Test
	public void TestUrl() throws IOException {
	 String HTML = url.HTML();
	 FormatHTML test = new FormatHTML(HTML);
	 test = test.PremierSplit();
	 System.out.println(test.PremierSplit().toString());
	 test = test.SecondSplit();
	 test = test.headSplit();
	 test = test.headParse();
	 //test = test.PremierParse();
	 assertEquals("", test.html, "");
	 
}
}
