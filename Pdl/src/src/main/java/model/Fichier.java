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


	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean addUrl(Url url)  {
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


	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean removeUrl(Url url) {
		for (Url urlfichier : this.setUrl) {
			if (urlfichier.equals(url)) {
				return this.setUrl.remove(url);
			}
		}
		return false;
	}


	/**
	 * <!-- begin-user-doc -->
	 * MÃ©thode produisant des Urls Ã  partir du fichier "wikiurls.txt"
	 * En lisant ligne par ligne les String contenus dans le fichier 
	 * Concat
	 * Les ajoute dans un nouveau String pour former un Url 
	 * Et renvoi un ensemble d'Url
	 * Les exceptions servent si le fichier Ã  tester n'existe pas
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void productUrls() { 
		try {
			FileReader fileread = new FileReader(System.getProperty("user.dir") + "\\wikiurls.txt");
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
					//System.out.println(u.url);
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


	public void productUrlsWikitext() { 
		try {
			FileReader fileread = new FileReader(System.getProperty("user.dir") + "\\wikiurls.txt");;
			BufferedReader bufferread = new BufferedReader(fileread);
			Set<Url> Url = new HashSet<Url>();
			try {
				String line = bufferread.readLine();
				while (line != null) {
					// System.out.println(line);
					line = bufferread.readLine();
					Url.add(new Url("https://en.wikipedia.org/w/index.php?title=" + line + "&action=edit"));
				}		
				for (Url u : Url) {
					//System.out.println(u.url);
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


	public int[] FichierToHTML() throws IOException {
		int[] tabTraite = new int[2];
		for(Url u : setUrl) {
			int[] tabThisUrlTraite = new int[2];
			System.out.println("extraction de  : " + u.getUrl());
			tabThisUrlTraite = u.UrlToHTML();
			tabTraite[0] += tabThisUrlTraite[0]; 
			tabTraite[1] += tabThisUrlTraite[1]; 
		}

		File repertoire = new File(System.getProperty("user.dir") + "\\output\\html");
		int nbFile = repertoire.listFiles().length;
		System.out.println("Nombre de tableau trouves : " + tabTraite[0]);
		System.out.println("Nombre de tableau importe avec succes : " + tabTraite[1]);
		System.out.println("Nombre de tableau importe en double : " + (tabTraite[1] - nbFile -1 )); // -1 car il y a un fichier de base dans le répertoire
		System.out.println("Nombre de tableau non importe : " + (tabTraite[0] - tabTraite[1]));
		return tabTraite;

	}

	public void FichierToWikitext() throws IOException {
		int i= 1;
		for(Url u : this.setUrl) {
			String HTML = u.HTML(); 
			if(HTML != null){
				FormatWikitext fw = new FormatWikitext(HTML);
				System.out.println("page " + i + " en cours de traitement ...");
				fw.ToCSV();
				System.out.println("");
				i++;
			}
		}
	}


	public void FichierToHTML(int nbUrl) throws IOException {
		int i = 0;
		int[] tabTraite = new int[2];
		for(Url u : setUrl) {
			if(i < nbUrl) {
				int[] tabThisUrlTraite = new int[2];
				System.out.println("extraction de  : " + u.getUrl());
				tabThisUrlTraite = u.UrlToHTML();
				tabTraite[0] += tabThisUrlTraite[0]; 
				tabTraite[1] += tabThisUrlTraite[1]; 
				i++;
			}
			else
				break;
		}
		System.out.println("Nombre de tableau trouvÃ©s : " + tabTraite[0]);
		System.out.println("Nombre de tableau importÃ© avec succÃ¨s : " + tabTraite[1]);
		System.out.println("Nombre de tableau non importÃ© : " + (tabTraite[0] - tabTraite[1]));
	}

	public void ThisUrlToHTML(int numUrl) throws IOException {
		int i = 0;
		for(Url u : setUrl) {
			if(i == numUrl) {
				u.UrlToHTML();
			}
			i++;
		}
	}
	//en.wikipedia.org/wiki/Comparison_of_consumer_brain%C3%A2%E2%82%AC%E2%80%9Ccomputer_interfaces




	/**
	 * <!-- begin-user-doc -->
	 * Mise en place d'un toString() permettant de renvoyer le contenu d'un fichier 
	 * Le fichier en question contient des Urls prÃ©cÃ©demment proposÃ© par l'utilisateur
	 * Ou pour visualiser simplement le contenu du/des fichier(s) utilisÃ©(s) contenant des String ou direcetment des Urls 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
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
