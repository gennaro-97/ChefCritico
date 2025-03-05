import java.util.ArrayList;
import java.util.Scanner;

public class Ristorante {

    static ArrayList<Utente> utenti = new ArrayList<>();
    static ArrayList<Piatto> piatti = new ArrayList<>();

    public static void main(String[] args) {
        menu(); // Avvio del programma
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int menu = -1;

        do {
            System.out.println("1. Registra Utente");
            System.out.println("2. Accedi");
            System.out.println("0. Esci");
            System.out.print("Scegli un'opzione: ");
            menu = scanner.nextInt();
            scanner.nextLine();  // Consuma il carattere di fine riga rimasto nel buffer

            switch (menu) {
                case 1:
                    registraUtente(scanner);
                    break;
                case 2:
                    accedi(scanner);
                    break;
                case 0:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Errore: Scelta non valida.");
                    break;
            }
        } while (menu != 0);

        scanner.close();
    }

    private static void registraUtente(Scanner scanner) {
        System.out.print("Inserisci il tuo nome: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Errore: Il nome non può essere vuoto.");
            return;
        }

        System.out.print("Inserisci la tua email: ");
        String email = scanner.nextLine().trim();
        if (emailEsistente(email)) {
            System.out.println("Errore: L'email è già registrata.");
            return;
        }

        System.out.print("Inserisci la tua password (minimo 6 caratteri): ");
        String password = scanner.nextLine().trim();
        if (password.length() < 6) {
            System.out.println("Errore: La password deve avere almeno 6 caratteri.");
            return;
        }

        System.out.println("Vuoi essere un critico o uno chef? (1. Critico / 2. Chef / 0. Nessuno)");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consuma il carattere di fine riga rimasto nel buffer

        if (tipo == 1) {
            Critico critico = new Critico(nome, email, password);
            utenti.add(critico);
            System.out.println("Critico registrato con successo!");
        } else if (tipo == 2) {
            Chef chef = new Chef(nome, email, password);
            utenti.add(chef);
            System.out.println("Chef registrato con successo!");
        } else {
            Utente utente = new Utente(nome, email, password);
            utenti.add(utente);
            System.out.println("Utente registrato con successo!");
        }
    }

    private static boolean emailEsistente(String email) {
        for (Utente u : utenti) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    private static void accedi(Scanner scanner) {
        System.out.print("Inserisci la tua email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Inserisci la tua password: ");
        String password = scanner.nextLine().trim();

        for (Utente u : utenti) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
                System.out.println("Benvenuto " + u.getNome());
                if (u instanceof Critico) {
                    menuCritico(scanner, (Critico) u);
                } else if (u instanceof Chef) {
                    menuChef(scanner, (Chef) u);
                } else {
                    menuUtente(scanner, u);
                }
                return;
            }
        }
        System.out.println("Errore: Email o password errate.");
    }

    // Menu per l'utente normale
    private static void menuUtente(Scanner scanner, Utente utente) {
        int menu = -1;
        do {
            System.out.println("1. Visualizza il saldo");
            System.out.println("2. Ordina un piatto");
            System.out.println("0. Esci");
            System.out.print("Scegli un'opzione: ");
            menu = scanner.nextInt();
            scanner.nextLine();  // Consuma il carattere di fine riga rimasto nel buffer

            switch (menu) {
                case 1:
                    System.out.println("Il tuo saldo è: " + utente.getSaldo());
                    break;
                case 2:
                    ordinaPiatto(scanner, utente);
                    break;
                case 0:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Errore: Scelta non valida.");
                    break;
            }
        } while (menu != 0);
    }

    // Menu per il critico
    private static void menuCritico(Scanner scanner, Critico critico) {
        int menu = -1;
        do {
            // Visualizza il menu per il critico
            System.out.println("\n===== MENU CRITICO =====");
            System.out.println("1. Visualizza il saldo");
            System.out.println("2. Ordina e recensisci un piatto");
            System.out.println("3. Visualizza recensioni dei piatti");
            System.out.println("0. Esci");
            System.out.print("Scegli un'opzione: ");
            menu = scanner.nextInt();
            scanner.nextLine();  // Consuma il carattere di fine riga rimasto nel buffer
    
            switch (menu) {
                case 1:
                    System.out.println("Il tuo saldo è: " + critico.getSaldo());
                    break;
    
                case 2:
                    ordinaPiattoCritico(scanner, critico);  // I critici possono ordinare e recensire un piatto
                    break;
    
                case 3:
                    visualizzaRecensioni(scanner);  // Visualizza tutte le recensioni dei piatti
                    break;
    
                case 0:
                    System.out.println("Arrivederci!");
                    break;
    
                default:
                    System.out.println("Errore: Scelta non valida.");
                    break;
            }
        } while (menu != 0);
    }

    // Menu per lo chef
    private static void menuChef(Scanner scanner, Chef chef) {
        int menu = -1;
        do {
            System.out.println("\n===== MENU CHEF =====");
            System.out.println("1. Aggiungi un nuovo piatto");
            System.out.println("2. Visualizza le recensioni dei tuoi piatti");
            System.out.println("0. Esci");
            System.out.print("Scegli un'opzione: ");
            menu = scanner.nextInt();
            scanner.nextLine();  // Consuma il carattere di fine riga rimasto nel buffer
    
            switch (menu) {
                case 1:
                    creaPiatto(scanner, chef);  // Opzione per aggiungere un piatto
                    break;
    
                case 2:
                    chef.visualizzaRecensioni();  // Mostra le recensioni dei piatti creati dallo chef
                    break;
    
                case 0:
                    System.out.println("Arrivederci!");
                    break;
    
                default:
                    System.out.println("Errore: Scelta non valida.");
                    break;
            }
        } while (menu != 0);
    }
    

    private static void ordinaPiatto(Scanner scanner, Utente utente) {
        if (piatti.isEmpty()) {
            System.out.println("Non ci sono piatti disponibili.");
            return;
        }

        System.out.println("\n===== MENU PIATTI =====");
        for (int i = 0; i < piatti.size(); i++) {
            System.out.println((i + 1) + ". " + piatti.get(i).getNome() + " - " + piatti.get(i).getPrezzo() + "€");
        }

        System.out.print("Scegli un piatto da ordinare: ");
        int scelta = scanner.nextInt() - 1;
        scanner.nextLine();  // Consuma il carattere di fine riga rimasto nel buffer

        if (scelta < 0 || scelta >= piatti.size()) {
            System.out.println("Errore: Piatto non valido.");
            return;
        }

        Piatto piattoScelto = piatti.get(scelta);
        if (utente.getSaldo() >= piattoScelto.getPrezzo()) {
            utente.sottraiSaldo(piattoScelto.getPrezzo());
            System.out.println("Piatto ordinato con successo!");
        } else {
            System.out.println("Errore: Saldo insufficiente.");
        }
    }

    // Funzione per creare un piatto
    private static void creaPiatto(Scanner scanner, Chef chef) {
        System.out.print("Inserisci il nome del piatto: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Inserisci il prezzo del piatto: ");
        double prezzo = scanner.nextDouble();
        scanner.nextLine();  // Consuma il carattere di fine riga rimasto nel buffer

        Piatto piatto = new Piatto(nome, prezzo, chef);
        piatti.add(piatto);
        chef.creaPiatto(piatto);  // Aggiungi il piatto alla lista dei piatti creati dallo chef
        System.out.println("Piatto creato con successo!");
    }

    private static void ordinaPiattoCritico(Scanner scanner, Critico critico) {
        if (piatti.isEmpty()) {
            System.out.println("Non ci sono piatti disponibili da ordinare.");
            return;
        }
    
        // Visualizza il menu dei piatti disponibili
        System.out.println("\n===== MENU PIATTI =====");
        for (int i = 0; i < piatti.size(); i++) {
            System.out.println((i + 1) + ". " + piatti.get(i).getNome() + " - Prezzo: " + piatti.get(i).getPrezzo() + "€");
        }
    
        System.out.print("Scegli il piatto da ordinare (inserisci il numero): ");
        int scelta;
        try {
            scelta = Integer.parseInt(scanner.nextLine()) - 1;
            if (scelta < 0 || scelta >= piatti.size()) {
                System.out.println("Errore: Scelta non valida.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Errore: Inserisci un numero valido.");
            return;
        }
    
        // Recupera il piatto scelto
        Piatto piattoScelto = piatti.get(scelta);
    
        // Ordina il piatto e lascia la recensione
        critico.ordinaPiatto(piattoScelto);  // Ordina e lascia la recensione
    }
   
    private static void visualizzaRecensioni(Scanner scanner) {
        if (piatti.isEmpty()) {
            System.out.println("Non ci sono piatti da visualizzare.");
            return;
        }
    
        System.out.println("\n===== RECENSIONI PIATTI =====");
        for (Piatto piatto : piatti) {
            System.out.println("Piatto: " + piatto.getNome());
            System.out.println("Creato da: " + piatto.getChef().getNome());
            piatto.mostraRecensioni();  // Mostra le recensioni per ciascun piatto
        }
    }
}