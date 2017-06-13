package JPAGestioneCondomini.JPAGestioneCondomini;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "PROPRIETARIO", schema = "GESTIONE_CONDOMINI")
public class Proprietario {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String codiceFiscale;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "proprietario")
	private List<Appartamento> listaApp ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public List<Appartamento> getListaApp() {
		return listaApp;
	}
	public void setListaApp(List<Appartamento> listaAppartamenti) {
		this.listaApp = listaAppartamenti;
	}
	@Override
	public String toString() {
		return "Proprietario [id=" + id + ", codiceFiscale=" + codiceFiscale + ", listaAppartamenti="
				+ listaApp + "]";
	}
	
	
	

}
