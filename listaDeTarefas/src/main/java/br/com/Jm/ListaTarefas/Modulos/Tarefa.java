package br.com.Jm.ListaTarefas.Modulos;

public class Tarefa {
    private Integer id;
    private String nome;
    private Boolean status;
    private String descricao;
    private Integer categoria_id = 1;

    public Tarefa(String nome, String descricao, Integer categoria_id, Boolean status) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria_id = categoria_id;
        this.status = status;
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

    public Integer getCategoria_id() {
        return this.categoria_id;
    }
}
