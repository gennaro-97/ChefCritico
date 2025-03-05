import java.util.Random;

public class Utente {
    private String nome;
    private String email;
    private String password;
    private double saldo;

    public Utente(String nome, String email, String password) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.saldo = 10 + new Random().nextFloat() * 90; // Genera una volta sola
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public double getSaldo() {
        return saldo; 
    }

    public String getPassword() {
        return password;
    }

    public void sottraiSaldo(double importo) {
        if (saldo >= importo) {
            saldo -= importo;
            System.out.println("Saldo aggiornato: " + saldo + "€");
        } else {
            System.out.println("Errore: Saldo insufficiente.");
        }
    }
}
