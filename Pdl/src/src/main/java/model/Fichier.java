package src.main.java.model;
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
