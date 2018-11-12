package src.main.java.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;


public class TestPdl {

	final static File coupeDuMondeHTML = new File("html.txt");
	//final static File presidentUSA = new File("HTML.txt");
	
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/

	 
		
		 @Test
			public void testUrl() throws IOException {
			 Url url = new Url("https://fr.wikipedia.org/wiki/Coupe du monde de football");
			 String HTML = url.HTML();
			 FormatHTML test = new FormatHTML(HTML);
			 test = test.PremierSplit();
			 test = test.SecondSplit();
			 assertEquals("", test.html, "");
			 
		 }
		 
		 @Test
		 public void testwiki() throws IOException {
			 Url url = new Url("https://fr.wikipedia.org/wiki/Liste_des_pr%C3%A9sidents_des_%C3%89tats-Unis");
			 String HTML = url.HTML();
			 FormatWikitext test = new FormatWikitext(HTML);
			 test = test.wikisplit();
			 //test = test.wikisplit2();
			 assertEquals("", test.wikitext, "");
		 }
	}
