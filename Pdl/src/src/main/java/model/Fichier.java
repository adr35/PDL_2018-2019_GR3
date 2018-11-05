package src.main.java.model;
import java.util.HashSet;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Fichier
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<Url> setUrl;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Fichier(){
		this.setUrl = new HashSet<Url>();
	}
	
	public Fichier(Set<Url> setUrl){
		this.setUrl = setUrl;
	}

}

