package lib.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {

    private int id;
    private LocalDate dataReserva;
    private LocalDate dataExpiracao;
    private Leitor leitor;
    private Obra obra;
    private String statusReserva;
    
    public Reserva(int id, LocalDate dataReserva, LocalDate dataExpiracao, Leitor leitor, Obra obra, String statusReserva) {
        this.id = id;
        this.dataReserva = dataReserva;
        this.dataExpiracao = dataExpiracao;
        this.leitor = leitor;
        this.obra = obra;
        this.statusReserva = statusReserva;
    }

    // construtor sem id (antes de salvar no banco)
    // dataExpiracao = 7 dias a partir de hoje por padrão
    public Reserva(Leitor leitor, Obra obra) {
        this.id = 0;
        this.leitor = leitor;
        this.obra = obra;
        this.dataReserva = LocalDate.now();
        this.dataExpiracao = LocalDate.now().plusDays(7);
        this.statusReserva = "ATIVA";
    }

    public boolean isAtiva(){
        return  statusReserva.equals("ATIVA");
    }

    public boolean isExpirada(){
        return LocalDate.now().isAfter(dataExpiracao);
    }

    // calcula quantos dias restam até a expiração
    public long getDiasRestantes(){
        return ChronoUnit.DAYS.between(LocalDate.now(), dataExpiracao);
    }
    public void cancelar() {
        this.statusReserva = "CANCELADA";
    }

    // confirma a reserva transformando em empréstimo
    public Emprestimo confirmar (Funcionario funcionario){
        Copia copia = obra.getCopiaDisponivel();
        if(copia == null) return null;

        copia.setStatus(StatusCopia.EMPRESTADO);
        this.statusReserva = "CONCLUIDA";

        return new Emprestimo(leitor, copia, funcionario);
    }

    @Override
    public String toString() {
        return "Reserva: " + obra.getTitulo() +
               " | Leitor: " + leitor.getNome() +
               " | Expira: " + dataExpiracao +
               " | Status: " + statusReserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public String getStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(String statusReserva) {
        this.statusReserva = statusReserva;
    }
}
