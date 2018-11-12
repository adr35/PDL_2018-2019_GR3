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
	 
	/*@Test
	public void testUrl() throws IOException {
		Url url = new Url("https://fr.wikipedia.org/wiki/Coupe_du_monde_de_football");
		String HTML = url.HTML();
		/*List<String> txttoString = Files.readAllLines(coupeDuMondeHTML.toPath());
		txttoString.toString();
		final FileInputStream lFileInputStream = new FileInputStream(coupeDuMondeHTML);
		String end = "";
		String endHTML = "";
		try{
			InputStream flux=new FileInputStream("html.txt"); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne;
			while ((ligne=buff.readLine())!=null){		
				end += ligne;
			}		
			buff.close(); 
			}		
			catch (Exception e){
			System.out.println(e.toString());
			}
		assertEquals("TestUrl 1", endHTML,end);
	}*/
	
	
	 @Test
		public void testUrl() throws IOException {
		 Url url = new Url("https://fr.wikipedia.org/wiki/Coupe_du_monde_de_football");
			String HTML = url.HTML();
		 FormatHTML test = new FormatHTML(HTML);
		 test = test.PremierSplit();
		 test = test.SecondSplit();
		 assertEquals("", test.html, "");
		 
	 }
}
