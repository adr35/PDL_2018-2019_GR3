package src.main.java.model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class Fichier extends Url {

	public Set<Url> setUrl;

	public Fichier() {
		this.setUrl = new HashSet<Url>();
	}

	public Fichier(Set<Url> setUrl) {
		this.setUrl = setUrl;
	}

	public Fichier(Url urlUrl) {
		this.setUrl = new HashSet<Url>();
		this.setUrl.add(urlUrl);
	}

	public Set<Url> getSetUrl() {
		return setUrl;
	}

	public void setSetUrl(Set<Url> setUrl) {
		this.setUrl = setUrl;
	}

	public boolean addUrl(Url url) throws IOException {
		if (url.isValidUrl()) {
			if (setUrl.isEmpty()) {
				return setUrl.add(url);
			} else {
				for (Url urlfichier : this.setUrl) {
					if (urlfichier == url) {
						return false;
					}
				}
			}
		}
		return this.setUrl.add(url);
	}

	public boolean removeUrl(Url url) {
		for (Url urlfichier : this.setUrl) {
			if (urlfichier.equals(url)) {
				return this.setUrl.remove(url);
			}
		}
		return false;
	}

		public void productUrls() { // méthode produisant des Urls à partir du fichier "wikiurls.txt"
		try {
			File fichier = new File("wikiurls.txt");
			FileReader fileread = new FileReader(fichier);
			BufferedReader bufferread = new BufferedReader(fileread);
			Set<Url> Url = new HashSet<Url>();
			try {
				String line = bufferread.readLine();
				while (line != null) {
					// System.out.println(line);
					line = bufferread.readLine();
					Url.add(new Url("https://en.wikipedia.org/wiki/" + line + "\n"));
				}		
				for (Url u : Url) {
					System.out.println(u.url);
				}				
				bufferread.close();
				fileread.close();
				this.setUrl = Url;
			} catch (IOException exception) {
				System.out.println("Erreur de la lecture : " + exception.getMessage());
			}
		} catch (FileNotFoundException exception) {
			System.out.println("Le fichier n'existe pas");
		}
	}

	public Url productUrls() {
		return null;
	}
	
	@Override
	public String toString() {
		String result = "[~~~~~~~~~~~~~~~~~~~~~~~~~~~Fichier~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n";
		int count = 1;
		for (Url url : this.setUrl) {
			result += count + "--> " + url.url + "\n";
			count++;
		}
		result += "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~]";
		return result;
	}

}
