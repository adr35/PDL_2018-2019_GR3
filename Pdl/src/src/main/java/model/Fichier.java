package src.main.java.model;
import java.util.HashSet;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Fichier extends Url {
	
	public Set<Url> setUrl;

	public Fichier(){
		this.setUrl = new HashSet<Url>();
	}
	
	public Fichier(Url urlUrl) {
		this.setUrl = new HashSet<Url>();
		this.setUrl.add(urlUrl);
	}
	
	public Fichier(String urlString) {
		Url url = new Url(urlString);
		this.setUrl = new HashSet<Url>();
		this.setUrl.add(url);
		}
	
	public Fichier(Set<Url> setUrl){
		this.setUrl = setUrl;
	}
	
	
	/*public Url receive() {
		Url url = new Url("");
		url = url.getUrltest();
		System.out.println(url);
		return url;
	}*/
	
	public Fichier addUrl(Url url) {
		Fichier fichier = new Fichier(url);
		return fichier;
	}
		
	public boolean addString(String url) {
		//creer un nouveau fichier a chaque fois... XXX
		this.setUrl.add(new Url(url));
		return true;
	}

	@Override
	public String toString() {
		String result= "Fichier [urlString= " + "\n";
		for(Url url : this.setUrl) {
			result += url.url + "\n";
		}
		result += "]";
		return result;
		// A tester avec les Url sous forme Url...
	}

	
	

}

