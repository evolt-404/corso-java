package JPAGestioneCondomini.JPAGestioneCondomini;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "CONDOMINIO", schema = "GESTIONE_CONDOMINI")
public class Condominio implements Comparable<Condominio> {

	
	@Id
	@GeneratedValue
	private int id;
	private String nome;
	private double dimensione;
	private static final int COSTO_GESTIONE = 50;
	private int numeroAppartamenti;
	
	private int costoGestioneTotale;
	private static final String regexCF = "[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]";
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "condominio")
	private List<Appartamento> listaAppartamenti;

	public void creaAppartamento(Scanner input) {
		String codiceFiscale = "";
		System.out.println("Inserisci codice Fiscale Proprietario");
		String cf = input.nextLine();
		if (Pattern.matches(regexCF, cf)) {
			codiceFiscale = cf;
		}

		System.out.println("Inserisci dimensione appartamento");
		double dimensione = input.nextDouble();
		input.nextLine();
		Appartamento app = new Appartamento(codiceFiscale, dimensione);
		setDimensione(getDimensione() + dimensione);

		getListaAppartamenti().add(app);
		calcoloCosto();
	}

	public void calcoloCosto() {
		setNumeroAppartamenti(listaAppartamenti.size());
		setCostoGestioneTotale(getNumeroAppartamenti() * COSTO_GESTIONE);

	}
	
	public void appartamentoCostoso(){
		Collections.sort(listaAppartamenti);
		System.out.println(listaAppartamenti.get(0));
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getDimensione() {
		return dimensione;
	}

	public void setDimensione(double dimensione) {
		this.dimensione = dimensione;
	}

	public int getNumeroAppartamenti() {
		return numeroAppartamenti;
	}

	public void setNumeroAppartamenti(int numeroAppartamenti) {
		this.numeroAppartamenti = numeroAppartamenti;
	}

	public List<Appartamento> getListaAppartamenti() {
		return listaAppartamenti;
	}

	public static int getCostoGestione() {
		return COSTO_GESTIONE;
	}

	public int getCostoGestioneTotale() {
		return costoGestioneTotale;
	}

	public void setCostoGestioneTotale(int costoGestioneTotale) {
		this.costoGestioneTotale = costoGestioneTotale;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setListaAppartamenti(List<Appartamento> listaAppartamenti) {
		this.listaAppartamenti = listaAppartamenti;
	}

	@Override
	public String toString() {
		return "Condominio [nome " + nome + ", costoGestioneTotale " + costoGestioneTotale + "]";
	}

	@Override
	public int compareTo(Condominio c) {
		if (this.getNumeroAppartamenti() < c.getNumeroAppartamenti())
			return -1;
		else if (this.getNumeroAppartamenti() > c.getNumeroAppartamenti())
			return 1;
		else
			return 0;
	}

}
