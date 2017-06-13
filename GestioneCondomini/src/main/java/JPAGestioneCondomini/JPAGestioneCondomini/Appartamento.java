package JPAGestioneCondomini.JPAGestioneCondomini;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table (name = "APPARTAMENTO", schema = "GESTIONE_CONDOMINI")
public class Appartamento implements Comparable<Appartamento>{

	@Id
	@GeneratedValue
	private int id;
	
	private double dimensione;
	private static final int COSTO_MANUTENZIONE = 150;
	private double costoFinaleManutenzione;
	private double speseTotali;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROPRIETARIO", nullable = false)
	private Proprietario proprietario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CONDOMINIO", nullable = false)
	private Condominio condominio;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCostoFinaleManutenzione(double costoFinaleManutenzione) {
		this.costoFinaleManutenzione = costoFinaleManutenzione;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	public double getSpeseTotali() {
		return speseTotali;
	}

	public void setSpeseTotali(double speseTotali) {
		this.speseTotali = speseTotali;
	}

	public Appartamento(String codiceFiscale, double dimensione) {
		super();
		this.dimensione = dimensione;
		this.costoFinaleManutenzione = dimensione * COSTO_MANUTENZIONE;
	}

	public Appartamento() {
		// TODO Auto-generated constructor stub
	}


	public double getDimensione() {
		return dimensione;
	}

	public void setDimensione(double dimensione) {
		this.dimensione = dimensione;
	}

	public static int getCostoManutenzione() {
		return COSTO_MANUTENZIONE;
	}

	public double getCostoFinaleManutenzione() {
		return costoFinaleManutenzione;
	}
	
	
	
	

	@Override
	public String toString() {
		return "Appartamento [codiceFiscale=" + proprietario.getCodiceFiscale() + ", dimensione=" + dimensione
				+ ", costoFinaleManutenzione=" + costoFinaleManutenzione + "]";
	}

	@Override
	public int compareTo(Appartamento a) {
		if (this.getDimensione() < a.getDimensione())
			return -1;
		else if (this.getDimensione() > a.getDimensione())
			return 1;
		else
			return 0;
	}
	


	
	
}
