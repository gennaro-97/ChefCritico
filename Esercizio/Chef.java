import java.util.ArrayList;

public class Chef extends Utente{

    private ArrayList<Piatto> piattiCreati;

    public Chef(String nome, String email, String password) {
        super(nome, email, password);
        this.piattiCreati = new ArrayList<Piatto>();
    }

    public void creaPiatto(Piatto piatto) {
        piattiCreati.add(piatto);
    }

    public ArrayList<Piatto> getPiattiCreati() {
        return piattiCreati;
    }
    
        // Visualizza le recensioni di tutti i piatti creati dallo chef
        public void visualizzaRecensioni() {
            if (piattiCreati.isEmpty()) {
                System.out.println("Non hai creato alcun piatto.");
                return;
            }
    
            for (Piatto piatto : piattiCreati) {
                System.out.println("Piatto: " + piatto.getNome());
                piatto.mostraRecensioni();  // Mostra le recensioni del piatto
            }
        }
}
