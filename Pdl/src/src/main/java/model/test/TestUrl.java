package src.main.java.model.test;

import static org.junit.Assert.*;

import org.junit.Test;
import src.main.java.model.Url;

/**
 * Classe de tests de la classe Url
 * @author Romiche
 *
 */
public class TestUrl {

	final Url url = new Url("https://fr.wikipedia.org/wiki/Coupe du monde de football");

	/**
	 * Test de la méthode isValidUrl() 
	 * Sur un url valide
	 * @return true
	 */
	@Test
	public void testvalidUrl() {
		assertTrue(url.isValidUrl());
	}

	/**
	 * Test de la méthode isValidUrl() 
	 * Sur un url à redirigé
	 * @return true
	 */
	@Test
	public void testredirectUrl() {
		Url url = new Url("http://fr.wikipedia.org/wiki/Coupe_du_monde_de_football"); // Lien redirigÃ©
		assertTrue("Lien redirigé", url.isValidUrl());
	}

	/**
	 * Test de la méthode isValidUrl() 
	 * Sur un url non valide
	 * @return false
	 */
	@Test
	public void testnonvalideUrl() {
		Url url = new Url("http://fr.wikipedia.org/wiki/Coupe de football"); // Lien non valide
		assertFalse("Lien non valide", url.isValidUrl());
	}
}
