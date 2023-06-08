package br.com.jm.gerenciadorTarefas.VO;

public class CategoriaTarefaVO {
    private Integer id;
    private String nome;
    private Boolean status;
    private String descricao;
    private String categoria;

    public CategoriaTarefaVO(Integer id, String nome, Boolean status, String descricao, String categoria) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "CategoriaTarefaVO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", status=" + status +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
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

    public String getCategoria() {
        return categoria;
    }
}
