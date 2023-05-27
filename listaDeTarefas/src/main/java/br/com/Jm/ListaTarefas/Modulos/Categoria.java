package br.com.Jm.ListaTarefas.Modulos;

import java.util.List;

public class Categoria {
    private Integer id;
    private String nome;
    private List<Tarefa> listaTarefas;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void adicionar(Tarefa tarefa){
        listaTarefas.add(tarefa);
    }

    public List<Tarefa> getListaTarefas() {
        return listaTarefas;
    }
}
