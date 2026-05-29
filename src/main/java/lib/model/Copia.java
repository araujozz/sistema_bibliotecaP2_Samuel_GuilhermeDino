package lib.model;

public class Copia {

    private int id;
    private int numeroCopia;
    private StatusCopia status;
    private Obra obra;

    
    public Copia(int id, int numeroCopia, StatusCopia status, Obra obra) {
        this.id = id;
        this.numeroCopia = numeroCopia;
        this.status = status;
        this.obra = obra;
    }

    public Copia(int numeroCopia, Obra obra){
        this(0,numeroCopia, StatusCopia.DISPONIVEL, obra);

    }
    

    public boolean isDisponivel(){
        return this.status == StatusCopia.DISPONIVEL;
    }

    @Override
    public String toString() {
        return obra.getTitulo() + " — Cópia " + numeroCopia + " (" + status + ")";
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getNumeroCopia() {
        return numeroCopia;
    }


    public void setNumeroCopia(int numeroCopia) {
        this.numeroCopia = numeroCopia;
    }


    public StatusCopia getStatus() {
        return status;
    }


    public void setStatus(StatusCopia status) {
        this.status = status;
    }


    public Obra getObra() {
        return obra;
    }


    public void setObra(Obra obra) {
        this.obra = obra;
    }

    
}
