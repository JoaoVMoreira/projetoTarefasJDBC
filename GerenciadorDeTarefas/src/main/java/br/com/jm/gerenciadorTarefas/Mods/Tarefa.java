package br.com.jm.gerenciadorTarefas.Mods;

import javax.persistence.*;
import javax.xml.namespace.QName;

@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Boolean status = false;
    private String descricao;
    @ManyToOne
    private Categoria categoria;

    public Tarefa() {
    }

    public Tarefa(String nome, String descricao, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }


    @Override
    public String toString() {
        return "Tarefa{" + id + nome + '\'' + status + descricao + '\'' + categoria.getNome() +
                '}';
    }
}
