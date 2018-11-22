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

		public void productUrls(File fichier) { // méthode produisant des Urls à partir du fichier "wikiurls.txt"
		try {
			// File fichier = new File("wikiurls.txt");
			FileReader fileread = new FileReader(fichier);
			BufferedReader bufferread = new BufferedReader(fileread);
			String[] Url = new String[336];
			int i = 0;

			try {
				String line = bufferread.readLine();

				while (line != null) {
					// System.out.println(line);
					line = bufferread.readLine();

					Url[i] = "https://wikipedia.org/wiki/" + line + "\n";
					i++;
				}
				Url[i - 1] = "";
				
				for (int j = 0; j <= i - 1; j++) {
					System.out.println(Url[j]);
				}
				
				bufferread.close();
				fileread.close();

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
