package br.com.Jm.ListaTarefas.Modulos;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private Integer id;
    private String nome;
    private List<Tarefa> tarefas= new ArrayList<>();

    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void adicionar(Tarefa tarefa){
        this.tarefas.add(tarefa);
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nome;
    }
}
