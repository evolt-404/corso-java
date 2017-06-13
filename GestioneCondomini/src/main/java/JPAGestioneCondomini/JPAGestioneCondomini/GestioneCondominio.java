package JPAGestioneCondomini.JPAGestioneCondomini;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GestioneCondominio {

	ArrayList<Condominio> listaCondomini = new ArrayList<>();

	public void calcoloSpeseCondominiali() {
		for (Condominio condominio : listaCondomini) {
			for (Appartamento appartamento : condominio.getListaAppartamenti()) {
				double indice = condominio.getDimensione() / appartamento.getDimensione();
				double spesa = indice * condominio.getCostoGestioneTotale();
				appartamento.setSpeseTotali(spesa + appartamento.getCostoFinaleManutenzione());
			}
		}

	}


	public ArrayList<Condominio> getListaCondomini() {
		return listaCondomini;
	}


	public void setListaCondomini(ArrayList<Condominio> listaCondomini) {
		this.listaCondomini = listaCondomini;
	}


	public void speseProprietario(Scanner input) { //spese proprietario y
		double spese = 0;
		System.out.println("Inserisci il codice Fiscale");
		String cf = input.nextLine();
		for (Condominio condominio : listaCondomini) {
			for (Appartamento appartamento : condominio.getListaAppartamenti()) {
				if (appartamento.getProprietario().getCodiceFiscale().equals(cf)) {
					spese += appartamento.getSpeseTotali();
				}
			}
		}
		System.out.println("Le spese totali del Proprietario " + cf + " " + spese);
	}

	public void condominioCostoso() { //condominio più costoso
		Collections.sort(listaCondomini);
		System.out.println(listaCondomini.get(0));
	}

	public void appartamentoCostoso() { //appartamento più costoso
		ArrayList<Appartamento> listaCostosissimi = new ArrayList<>();
		for (Condominio condominio : listaCondomini) {
			if (!(condominio.getListaAppartamenti().isEmpty())) {
				Collections.sort(condominio.getListaAppartamenti());
				listaCostosissimi.add(condominio.getListaAppartamenti().get(0));
			}
		}

		Collections.sort(listaCostosissimi);
		System.out.println(listaCostosissimi.get(0));
	}

	public void mediaCosto() { //costo medio per condominio
		double spese = 0;
		double media = 0;
		for (Condominio condominio : listaCondomini) {
			for (Appartamento appartamento : condominio.getListaAppartamenti()) {
				spese += appartamento.getSpeseTotali();
			}
			media = spese / condominio.getNumeroAppartamenti();
			System.out.println(condominio.getNome() + " costo medio " + media);
		}
	}

}
