package src.main.java.model.test;

import static org.junit.Assert.*;

import org.junit.Test;
import src.main.java.model.Fichier;
import src.main.java.model.Url;

/**
 * Classe de tests de la classe Fichier
 * @author Romiche
 *
 */
public class TestFichier {

	final Url url = new Url("https://fr.wikipedia.org/wiki/Coupe du monde de football");
	final Url url2 = new Url("https://fr.wikipedia.org/wiki/Internationaux de France de tennis");
	
	/**
	 * Test de la méthode addUrl()
	 * Ajoute un Url dans un Fichier
	 * @return true si ajout dans fichier OK
	 * @throws Exception
	 */
	@Test
	public void testadd() throws Exception {
		Fichier fichier = new Fichier();
		boolean assert1 = fichier.addUrl(url);
		//System.out.println(fichier.toString());
		assertTrue("Url ajouté", assert1);
	}
	
	/**
	 * Test de la méthode removeUrl()
	 * Supprime un Url dans un Fichier
	 * @return true si suppression dans fichier OK
	 * @throws Exception
	 */
	@Test
	public void testremove() throws Exception {
		Fichier fichier = new Fichier();
		fichier.addUrl(url);
		fichier.addUrl(url2);
		boolean assertR = fichier.removeUrl(url);
		//System.out.println(fichier.toString());
		assertTrue("Url supprimé", assertR);
	}
	
	/**
	 * Test de la méthode addUrl() sur wikiurls.txt
	 * Ajoute tous les urls de wikiurls.txt dans un nouveau fichier pour save 
	 * @return true si ajout dans fichier OK
	 * @throws Exception
	 */
	@Test
	public void testaddwikiurls() {
		Fichier fichier = new Fichier();
		//.....
	}
	
	/**
	 * Production des Urls dans un fichier 
	 * (utilisation de la méthode productUrl() de la classe Fichier)
	 * Ensuite on parcourt tous les Urls du fichier
	 * Et on test si ils existent ou non
	 * Enfin, les assert testent si il y a bien 314 Urls valident 
	 * et 22 non valident grâce à des variables comptants ces validités.
	 */
	@Test
	public void testreadFile() {
		Fichier fichier = new Fichier();
		int articleOK = 0, articleKO = 0;
		fichier.productUrls(); 
		for (Url url : fichier.setUrl) { 
			if (url.isValidUrl()) { 
				articleOK++;
			} else {
				articleKO++;
			}
		}
		assertEquals(articleOK, 314); 
		assertEquals(articleKO, 22);
	}
	
	/**
	 * 
	 */
	@Test
	public void testUrlFromFile() {
		
	}
}
