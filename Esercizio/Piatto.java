import java.util.ArrayList;

public class Piatto {

    private String nome;
    private double prezzo;
    private Chef chef;
    private ArrayList<String> recensioni;
    private ArrayList<String> nomiCritici;

    // Costruttore
    public Piatto(String nome, double prezzo, Chef chef) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.chef = chef;
        this.recensioni = new ArrayList<String>();
        this.nomiCritici = new ArrayList<String>(); 
    }

    // Getter per il nome del piatto
    public String getNome() {
        return nome;
    }

    // Getter per il prezzo del piatto
    public double getPrezzo() {
        return prezzo;
    }

    // Getter per l'chef che ha creato il piatto
    public Chef getChef() {
        return chef;
    }

    // Metodo toString per visualizzare informazioni sul piatto
    @Override
    public String toString() {
        return "Piatto: " + nome + " - Prezzo: " + prezzo + "€" + " (Creato da: " + chef.getNome() + ")";
    }

    public void aggiungiRecensione(String recensione, String nomeCritico) {
        this.recensioni.add(recensione);
        this.nomiCritici.add(nomeCritico);
    }

    public void mostraRecensioni() {
        System.out.println("Recensioni per " + nome + ":");
        if (recensioni.isEmpty()) {
            System.out.println("Nessuna recensione disponibile.");
        } else {
            for (int i = 0; i < recensioni.size(); i++) {
                System.out.println("Critico: " + nomiCritici.get(i));
                System.out.println("Recensione: " + recensioni.get(i));
                System.out.println("-----------------------");
            }
        }
    }
}
