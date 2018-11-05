package src.main.java.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

public class TestPdl {
	
	 final static File coupeDuMondeHTML = new File("html.txt");
	 
	@Test
	public void testUrl() throws IOException {
		Url url = new Url("https://fr.wikipedia.org/wiki/Coupe_du_monde_de_football");
		String HTML = url.HTML();
		List<String> txttoString = Files.readAllLines(coupeDuMondeHTML.toPath());
		txttoString.toString();
		final FileInputStream lFileInputStream = new FileInputStream(coupeDuMondeHTML);
		assertEquals("TestUrl 1", HTML.getBytes(), txttoString.toString());
	}
	
	

}
