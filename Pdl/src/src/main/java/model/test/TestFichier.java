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
	final Url urlfalse = new Url("http://wikipedia.fr/InternationauxFrancetennis");

	
	Fichier fichier = new Fichier();
	Fichier Wikiurls = new Fichier();
	//final static File Wikiurls = new File("wikiurls.txt");
	
	/**
	 * Test de la méthode addUrl()
	 * Ajoute un Url dans un Fichier
	 * @return true si ajout dans fichier OK
	 * @throws Exception
	 */
	@Test
	public void TestAdd() throws Exception {
		assertTrue("Url ajouté", fichier.addUrl(url));
	}
	
	/**
	 * Test de la méthode addUrl()
	 * @return false si le fichier existe déjà
	 * @throws Exception
	 */
	@Test
	public void TestAdd2() throws Exception {
		assertTrue(fichier.addUrl(url));
		assertFalse("Url ne peux pas s'ajouter", fichier.addUrl(url));
	}
	
	
	
	/**
	 * Test de la méthode removeUrl()
	 * Supprime un Url dans un Fichier
	 * @return true si suppression dans fichier OK
	 * @throws Exception
	 */
	@Test
	public void TestRemove() throws Exception {
		assertTrue(fichier.addUrl(url));
		assertTrue("Url supprimer", fichier.removeUrl(url));
	}
	
	/**
	 * Test de la méthode removeUrl()
	 * Supprime un Url dans un Fichier
	 * @return false si l'Url n'est pas présent dans le fichier
	 * @throws Exception
	 */
	@Test
	public void TestRemove2() throws Exception {
		assertFalse("Url n'est pas présent dans le fichier", fichier.removeUrl(url));
	}
	
	/**
	 * Test de la méthode toString()
	 * Affiche le fichier contenant les Urls ajouté, sous forme de String
	 * 
	 */
	@Test
	public void TestToString() {
		assertTrue(fichier.addUrl(url));
		System.out.println(fichier.toString());
		assertEquals(fichier.toString(),
				"[~~~~~~~~~~~~~~~~~~~~~~~~~~~Fichier~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n"
				+ "1--> https://fr.wikipedia.org/wiki/Coupe du monde de football" + "\n"
						+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~]");
	}
	
	/**
	 * Production des Urls dans un fichier 
	 * (utilisation de la méthode productUrl() de la classe Fichier)
	 * Ensuite on parcourt tous les Urls du fichier
	 * Et on test si ils existent ou non
	 * Enfin, les assert testent si il y a bien 314 Urls valident 
	 * et 22 non valident grâce à des variables comptants ces validités.
	 * 
	 */
	@Test
	public void TestReadFile() {
		int articleOK = 0;
		int articleKO = 0;
		Wikiurls.productUrls();
		for (Url url : Wikiurls.setUrl) {
			if (url.isValidUrl()) {
				articleOK++;
			} else {
				articleKO++;
			}
		}
		System.out.println(Wikiurls.toString());
		assertEquals(articleOK, 314);
		assertEquals(articleKO, 22);
	}
	

	/**
	 * 
	 */
	@Test
	public void TestUrlFromFile() {
		
	}
}
