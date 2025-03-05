import java.util.ArrayList;
import java.util.Scanner;

public class Critico extends Utente{

    private Scanner scanner = new Scanner(System.in);

    public Critico(String nome, String email, String password) {
        super(nome, email, password);
    }
    
    public void ordinaPiatto(Piatto piatto){
        if(getSaldo() >= piatto.getPrezzo()){
            sottraiSaldo(piatto.getPrezzo());
            System.out.println("Piatto " + piatto.getNome() + " ordinato con successo.");
            lasciaRecensione(piatto);
        } else {
            System.out.println("Errore: Saldo insufficiente.");
        }
    }

    private void lasciaRecensione(Piatto piatto) {
        System.out.print("Lascia una recensione per " + piatto.getNome() + " (voto da 1 a 5): ");
        int voto;
        try {
            voto = Integer.parseInt(scanner.nextLine());
            if (voto < 1 || voto > 5) {
                System.out.println("Errore: Il voto deve essere tra 1 e 5.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Errore: Inserisci un numero valido.");
            return;
        }

        String recensione = Integer.toString(voto);
        piatto.aggiungiRecensione(recensione, getNome());
        System.out.println("Grazie per la tua recensione!");
    }

}
