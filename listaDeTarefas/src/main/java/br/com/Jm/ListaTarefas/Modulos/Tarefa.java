package br.com.Jm.ListaTarefas.Modulos;

public class Tarefa {
    private Integer id;
    private String nome;
    private Boolean status = false;
    private String descricao;

    public Tarefa(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
