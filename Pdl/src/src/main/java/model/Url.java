package src.main.java.model;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

/**
 * @author Romiche
 *
 */
public class Url {

	public String url;
	public Url urltest;
	//public URL Url;
	

	public Url() {
		this.url = new String();
	}
	

	public Url(String url) {
		this.url = url;
	}
	
	/*public Url(URL Url) {
		this.Url = Url;
	}*/
	
	public Url getUrltest() {
		return this.urltest;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

		
	/**
	 * <!-- begin-user-doc -->
	 * Methode booleen qui renvoie vrai si Url est valide
	 * Et renvoie false si Url n'est pas valide
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isValidUrl() { 
		// int ch;
		try {
			URL urlt = new URL(url);
			HttpURLConnection connexion = (HttpURLConnection) urlt.openConnection();
			InputStream flux = connexion.getInputStream();
			System.out.println("Status de la connexion de " + urlt + ": " + connexion.getResponseMessage());
			if (connexion.getResponseCode() == HttpURLConnection.HTTP_OK)
				// while ((ch = flux.read()) != -1)
				// System.out.print((char) ch); //Affiche le code HTML de la page
				System.out.println("--> Url valide");
			flux.close();
			connexion.disconnect();
			return true;
		} catch (Exception e) {
			System.out.println(e + "\n--> Url non valide");
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc --> Renvoie le code HTML de l'url contenant le wikitext
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	
	public FormatWikitext ToWikitext(Url parameter) {
		// TODO implement me
		return null;
	}

	/**
	 * <!-- begin-user-doc --> Renvoie le code HTML de l'url courant <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */

	public FormatHTML ToHTML(Url parameter) {
		// TODO implement me
		return null;
	}

	
	public void HTML2() throws IOException {
		if(isValidUrl()) {
		Document doc = Jsoup.connect(getUrl()).get();
		FormatHTML fhtml =new FormatHTML(doc.html());
		fhtml.ToCSV();
		}
	}
	

	public String HTML() throws IOException {
		Document doc = Jsoup.connect(getUrl()).get();
		return doc.html();
	}


	/*@Override
	public String toString() {
		return "Url [Url=" + Url + "]";
	}*/
	
	
	
	
	/**
	 * Utilisation de Servelet, un API, pour crï¿½er dynamiquement des donnï¿½es au
	 * sein d'un serveur HTTP. Ces donnï¿½es sont le plus gï¿½nï¿½ralement
	 * prï¿½sentï¿½es au format HTML
	 * 
	 * @param requete
	 * @param reponse
	 * @param handler
	 * @return
	 * @throws Exception
	 */

	/*
	 * URLs courants : public boolean vï¿½rifURL(HttpServletRequest requete,
	 * HttpServletResponse reponse, Object handler) throws Exception { String url =
	 * requete.getRequestURL().toString(); requete.setAttribute("urlBySpring", url);
	 * return true; } public String recup(HttpServletRequest request) { String url =
	 * request.getRequestURL(); System.out.println(url); }
	 */

}

