package lib.model;

public class Funcionario extends Pessoa {
    private String cargo;
    private String senha;
    private String email;
    private String telefone;
    private boolean ativo;


    
    public Funcionario(int id, String nome, String cpf, String cargo, String senha, String email,
            String telefone, boolean ativo) {
        super(id, nome, cpf);
        this.cargo = cargo;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.ativo = true;
    }
    // construtor sem id (antes de salvar no banco)
    public Funcionario(String nome, String cpf, String cargo,String senha, String email, String telefone ) {
        this(0, nome, cpf, cargo, senha, email, telefone, true);
    }

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean verificarSenha(String senhaDigitada){
        return this.senha.equals(senhaDigitada);
    }

    @Override
    public String getDados() {
        return "funcionario: " + getNome() + " CPF: " + getCpf() + " cargo: " + cargo + "Email: " + email + "Telefone: " + telefone + "Ativo" + (ativo ? "sim" : "não");
    }

    @Override
    public String toString(){
        return getNome() + " (" + getCpf() + ")";
    }
}

