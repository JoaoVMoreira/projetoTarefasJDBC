package br.com.jm.gerenciadorTarefas.Mods;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Tarefa> listaTarefas = new ArrayList<>();

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria() {
    }

    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Tarefa> getListaTarefas() {
        return listaTarefas;
    }

    public void setListaTarefas(List<Tarefa> listaTarefas) {
        this.listaTarefas = listaTarefas;
    }




    @Override
    public String toString() {
        return nome;
    }
}
