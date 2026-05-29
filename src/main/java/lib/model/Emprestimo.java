package lib.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private int id;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevDevolucao;
    private LocalDate dataDevReal;
    private Leitor leitor;
    private Copia copia;
    private Funcionario funcionario;


    public Emprestimo(int id, LocalDate dataEmprestimo, LocalDate dataPrevDevolucao, LocalDate dataDevReal,
            Leitor leitor, Copia copia, Funcionario funcionario) {
        this.id = id;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevDevolucao = dataPrevDevolucao;
        this.dataDevReal = dataDevReal;
        this.leitor = leitor;
        this.copia = copia;
        this.funcionario = funcionario;
    }




    // construtor sem id (antes de salvar no banco)
    // prazo padrão de 7 dias para devolução
    public Emprestimo(Leitor leitor, Copia copia, Funcionario funcionario) {
        this.id = 0;
        this.leitor = leitor;
        this.copia = copia;
        this.funcionario = funcionario;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevDevolucao = LocalDate.now().plusDays(7);
        this.dataDevReal = null; // null = ainda não devolvido
    }

    public boolean isDevolucaoFeita(){
        return dataDevReal != null;
    }

    // verifica se está atrasado
    public boolean isAtrasado(){
        if(isDevolucaoFeita())return dataDevReal.isAfter(dataPrevDevolucao);
        return LocalDate.now().isAfter(dataPrevDevolucao);
    }


    //cobra multa de 1 real por dia
    public double calcularMulta(){
        if (!isAtrasado()) return 0.0;

        LocalDate dataFim = isDevolucaoFeita() ? dataDevReal : LocalDate.now();
        long diasAtraso = ChronoUnit.DAYS.between(dataPrevDevolucao, dataFim);
        return diasAtraso * 1.0;
    }

     // registra a devolução — muda o status da cópia e define a data real
    public void registrarDevolucao(){
        this.dataDevReal = LocalDate.now();
        this.copia.setStatus(StatusCopia.DISPONIVEL);
    }

    // calcula quantos dias faltam para a devolução (negativo = atrasado)
    public long getDiasParaDevolucao(){
        if (isDevolucaoFeita())return 0;
        return ChronoUnit.DAYS.between(LocalDate.now(), dataPrevDevolucao);
    }

    @Override
    public String toString() {
        return "Empréstimo: " + copia.getObra().getTitulo() +
               " | Leitor: " + leitor.getNome() +
               " | Prev. devolução: " + dataPrevDevolucao +
               (isDevolucaoFeita() ? " | Devolvido: " + dataDevReal : " | Em aberto");
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevDevolucao() {
        return dataPrevDevolucao;
    }

    public void setDataPrevDevolucao(LocalDate dataPrevDevolucao) {
        this.dataPrevDevolucao = dataPrevDevolucao;
    }

    public LocalDate getDataDevReal() {
        return dataDevReal;
    }

    public void setDataDevReal(LocalDate dataDevReal) {
        this.dataDevReal = dataDevReal;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public Copia getCopia() {
        return copia;
    }

    public void setCopia(Copia copia) {
        this.copia = copia;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    

}
