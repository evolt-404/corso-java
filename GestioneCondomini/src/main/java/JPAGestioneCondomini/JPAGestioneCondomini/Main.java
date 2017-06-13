package JPAGestioneCondomini.JPAGestioneCondomini;

import java.util.ArrayList;
import java.util.Scanner;
import javax.persistence.Query;

public class Main {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
//		 System.out.println("Creo Proprietario");
//		 createProprietario(input);
//		 System.out.println("Crea Condominio");
//		 createCondominio(input);
		// System.out.println("Cerco Proprietario");
		// readProprietario(input);
		// System.out.println("Modifica Proprietario");
		// updateProprietario(input);
		// System.out.println("Elimina Proprietario");
		// deleteProprietario(input);
		 System.out.println("Crea Appartamento");
		 createAppartamento(input);
	}

	public static void createProprietario(Scanner input) {

		Proprietario proprietario = new Proprietario();
		System.out.println("Aggiungi Proprietario.");

		System.out.println("Inserisci Codice Fiscale:");
		String cf = input.nextLine();
		proprietario.setCodiceFiscale(cf);

		ServicesCrud crud_services = new ServicesCrud("jpa-example");

		// Creazione record in tabella
		crud_services.jpaCreate(proprietario);
		System.out.println("Nuovo Proprietario Creato.");
		crud_services.closeLogicaJPA();
	}

	public static Proprietario readProprietario(Scanner input) {

		System.out.println("Cerca Proprietario. ");
		System.out.println("Seleziona ID: ");
		int id = input.nextInt();
		input.nextLine();

		ServicesCrud crud_services = new ServicesCrud("jpa-example");

		Query jpaRead = crud_services.jpaRead("SELECT p FROM Proprietario p WHERE id = " + id);

		Proprietario proprietario = (Proprietario) jpaRead.getSingleResult();
		System.out.println(proprietario);
		crud_services.closeLogicaJPA();
		return proprietario;

	}

	public static void updateProprietario(Scanner input) {

		System.out.println("Modifica Proprietario. ");
		System.out.println("Seleziona ID: ");
		int id = input.nextInt();
		input.nextLine();

		ServicesCrud crud_services = new ServicesCrud("jpa-example");
		Query jpaRead = crud_services.jpaRead("SELECT p FROM Proprietario p WHERE id = " + id);

		Proprietario proprietario = (Proprietario) jpaRead.getSingleResult();
		System.out.println(proprietario);

		System.out.println("Inserisci Nuovo Codice Fiscale:");
		String codiceFiscale = input.nextLine();
		proprietario.setCodiceFiscale(codiceFiscale);

		crud_services.jpaUpdate(proprietario);
		System.out.println("Dati Proprietario Aggiornati.");
		crud_services.closeLogicaJPA();
	}

	public static void deleteProprietario(Scanner input) {

		System.out.println("Elimina Proprietario. ");
		System.out.println("Seleziona ID: ");
		int id = input.nextInt();
		input.nextLine();

		ServicesCrud crud_services = new ServicesCrud("jpa-example");
		Query jpaRead = crud_services.jpaRead("SELECT p FROM Proprietario p WHERE id = " + id);

		Proprietario proprietario = (Proprietario) jpaRead.getSingleResult();

		crud_services.jpaDelete(proprietario);
		System.out.println("Proprietario Eliminato.");
		crud_services.closeLogicaJPA();
	}

	public static void createAppartamento(Scanner input) {

		Appartamento appartamento = new Appartamento();
		System.out.println("Aggiungi Appartamento.");
		
		System.out.println("Inserisci Dimensione:");
		double dimensione = input.nextDouble();
		appartamento.setDimensione(dimensione);
		appartamento.setCostoFinaleManutenzione(dimensione * appartamento.getCostoManutenzione());
		
		Proprietario proprietario = readProprietario(input);
//		proprietario.setListaApp(new ArrayList<>()); //inizializzo gli arraylist delle classi
		Condominio condominio = readCondominio(input);
//		condominio.setListaAppartamenti(new ArrayList<>());
		
		
		//GLI ARRAYLIST DI PROPRIETARIO E CONDOMINIO SONO PRESI TRAMITE QUERY DAL DATABASE
		//(questo puo' creare problemi con le relazioni oneToMany LAZY)

		appartamento.setProprietario(proprietario);
		proprietario.getListaApp().add(appartamento);
		
		appartamento.setCondominio(condominio);
		condominio.setNumeroAppartamenti(condominio.getNumeroAppartamenti()+1);
		condominio.getListaAppartamenti().add(appartamento);
		
		

		ServicesCrud crud_services = new ServicesCrud("jpa-example");

		// Creazione record in tabella
		crud_services.jpaCreate(appartamento);
		crud_services.jpaUpdate(proprietario);
		crud_services.jpaUpdate(condominio);
		System.out.println("Nuovo Appartamento Creato.");
		crud_services.closeLogicaJPA();
	}

	public static void readAppartamento(Scanner input) {

		System.out.println("Cerca Appartamento. ");
		System.out.println("Seleziona ID: ");
		int id = input.nextInt();
		input.nextLine();

		ServicesCrud crud_services = new ServicesCrud("jpa-example");

		Query jpaRead = crud_services.jpaRead("SELECT a FROM Appartamento a WHERE id = " + id);

		Appartamento appartamento = (Appartamento) jpaRead.getSingleResult();
		System.out.println(appartamento);
		crud_services.closeLogicaJPA();

	}

	public static void updateAppartamento(Scanner input) {

		System.out.println("Modifica Appartamento. ");
		System.out.println("Seleziona ID: ");
		int id = input.nextInt();
		input.nextLine();

		ServicesCrud crud_services = new ServicesCrud("jpa-example");
		Query jpaRead = crud_services.jpaRead("SELECT a FROM Appartamento a WHERE id = " + id);

		Appartamento appartamento = (Appartamento) jpaRead.getSingleResult();
		System.out.println(appartamento);

		System.out.println("Inserisci Nuove Dimensioni:");
		double dimensione = input.nextDouble();
		appartamento.setDimensione(dimensione);

		crud_services.jpaUpdate(appartamento);
		System.out.println("Dati Appartamento Aggiornati.");
		crud_services.closeLogicaJPA();
	}

	public static void deleteAppartamento(Scanner input) {

		System.out.println("Elimina Appartamento. ");
		System.out.println("Seleziona ID: ");
		int id = input.nextInt();
		input.nextLine();

		ServicesCrud crud_services = new ServicesCrud("jpa-example");
		Query jpaRead = crud_services.jpaRead("SELECT a FROM Appartamento a WHERE id = " + id);

		Appartamento appartamento = (Appartamento) jpaRead.getSingleResult();

		crud_services.jpaDelete(appartamento);
		System.out.println("Appartamento Eliminato.");
		crud_services.closeLogicaJPA();
	}
	
	public static void createCondominio(Scanner input){
		
		Condominio condominio = new Condominio();
		System.out.println("Aggiungi Condominio.");

		System.out.println("Inserisci Nome Condominio:");
		String nome = input.nextLine();
		condominio.setNome(nome);
		
		System.out.println("Inserisci Dimensione:");
		double dimensione = input.nextDouble();
		condominio.setDimensione(dimensione);
		

		ServicesCrud crud_services = new ServicesCrud("jpa-example");

		// Creazione record in tabella
		crud_services.jpaCreate(condominio);
		System.out.println("Nuovo Condominio Creato.");
		crud_services.closeLogicaJPA();
		
	}
	
	public static Condominio readCondominio(Scanner input){
		
		System.out.println("Cerca Condominio. ");
		System.out.println("Seleziona ID: ");
		int id = input.nextInt();
		input.nextLine();

		ServicesCrud crud_services = new ServicesCrud("jpa-example");

		Query jpaRead = crud_services.jpaRead("SELECT c FROM Condominio c WHERE id = " + id);

		Condominio condominio = (Condominio) jpaRead.getSingleResult();
		System.out.println(condominio);
		crud_services.closeLogicaJPA();
		return condominio;
		
	}
	
	public static void updateCondominio(Scanner input){
		
		System.out.println("Modifica Condominio. ");
		System.out.println("Seleziona ID: ");
		int id = input.nextInt();
		input.nextLine();

		ServicesCrud crud_services = new ServicesCrud("jpa-example");
		Query jpaRead = crud_services.jpaRead("SELECT c FROM Condominio c WHERE id = " + id);

		Condominio condominio = (Condominio) jpaRead.getSingleResult();
		System.out.println(condominio);

		System.out.println("Inserisci Nuovo Nome:");
		String nome = input.nextLine();
		condominio.setNome(nome);

		crud_services.jpaUpdate(condominio);
		System.out.println("Dati Proprietario Aggiornati.");
		crud_services.closeLogicaJPA();
		
	}
	
	public static void deleteCondominio(Scanner input){
		
		System.out.println("Elimina Condominio. ");
		System.out.println("Seleziona ID: ");
		int id = input.nextInt();
		input.nextLine();

		ServicesCrud crud_services = new ServicesCrud("jpa-example");
		Query jpaRead = crud_services.jpaRead("SELECT c FROM Condominio c WHERE id = " + id);

		Condominio condominio = (Condominio) jpaRead.getSingleResult();

		crud_services.jpaDelete(condominio);
		System.out.println("Condominio Eliminato.");
		crud_services.closeLogicaJPA();
		
	}
	

}
