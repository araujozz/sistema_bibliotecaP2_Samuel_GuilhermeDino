package lib.model;
import java.util.ArrayList;
import java.util.List;

public class Obra {
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private int anoPublicacao;
    private String editora;
    private String categoria;
    private List<Copia> copias;

    public Obra(int id, String titulo, String autor, String isbn, int anoPublicacao, String editora, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.editora = editora;
        this.categoria = categoria;
        this.copias = new ArrayList<>();
    }

    public Obra(String titulo, String autor, String isbn, int anoPublicacao, String editora, String categoria) {
        this(0, titulo, autor, isbn, anoPublicacao, editora, categoria);
    }

    // retorna a primeira cópia disponível, ou null se não houve
    public Copia getCopiaDisponivel(){
        for(Copia c : copias){
            if(c.isDisponivel()){
                return c;
            }
        }
    return null;
    }

    // verifica se existe ao menos uma cópia disponível
    public boolean isDisponivel(){
        return getCopiaDisponivel() != null;
    }

    // conta o total de cópias
    public int getTotalCopias(){
        return copias.size();
    }

    // conta só as cópias disponíveis
    public int getTotalCopiasDisponiveis(){
        int soma = 0;
        for (Copia c : copias){
            if(c.isDisponivel())soma++;
        }
        return  soma;
    }
    // usado pelo DAO para popular a lista ao carregar do banco
    public void addCopia(Copia copia){
        this.copias.add(copia);
    }

    @Override
    public String toString() {
        return titulo + " — " + autor + " (" + anoPublicacao + ")";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}
