package src.main.java.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Url
{

	public String url;

	public Url(){
		this.url = new String();
	}
	
	public Url(String url){
		this.url = url;
	}

	/**
	 * <!-- begin-user-doc -->
	 * Renvoie le code HTML de l'url contenant le wikitext
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public FormatWikitext ToWikitext(Url parameter) {
		// TODO implement me
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * Renvoie le code HTML de l'url courant
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public FormatHTML ToHTML(Url parameter) {
		// TODO implement me
		return null;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String HTML() throws IOException {
		Document doc = Jsoup.connect(getUrl()).get();
		return doc.html();
	}

}

