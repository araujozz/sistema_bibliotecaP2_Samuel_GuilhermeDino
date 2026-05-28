package lib.model;

public class Leitor extends Pessoa {
    private String telefone;
    private String email;
    private boolean ativo;


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public Leitor(int id, String nome, String cpf, String telefone, String email) {
        super(id, nome, cpf);
        this.telefone = telefone;
        this.email = email;
        this.ativo = true;
    }

     // construtor sem id (antes de salvar no banco)
    public Leitor(String nome, String cpf, String telefone, String email) {
        this(0, nome, cpf, telefone, email);
    }
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String getDados(){
        return "leitor: " + getNome() + "CPF: " + getCpf() + "Email:" + email + "Ativo" + (ativo ? "sim" : "não");
    }

    @Override
    public String toString() {
        return getNome() + "(" + getCpf() + ")";
    }

    

}
