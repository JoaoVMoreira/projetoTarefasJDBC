package br.com.Jm.ListaTarefas.Teste;

import br.com.Jm.ListaTarefas.ConnectionFactory.ConnectionFactory;
import br.com.Jm.ListaTarefas.DAO.CategoriaDAO;
import br.com.Jm.ListaTarefas.Modulos.Categoria;
import br.com.Jm.ListaTarefas.Modulos.Tarefa;
import br.com.Jm.ListaTarefas.DAO.TarefaDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class Teste {
    public static void main(String[] args) throws SQLException {
        try(Connection con = new ConnectionFactory().CriaConeccao()){
            TarefaDAO tarefaDAO = new TarefaDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            Tarefa tarefa2 = new Tarefa("Teste", "Testando inserção");

            tarefaDAO.Inserir(con, new Tarefa("Limpar caixa de areia", "Limpar caixa de areia do gato"));

            for (Tarefa tarefa : tarefaDAO.Listar(con)) {
                System.out.println("Nome:" + tarefa.getNome());
                System.out.println("Status: " + tarefa.getStatus());
                System.out.println("Descricao: " + tarefa.getDescricao());
            }

            System.out.println("*****************************");

            for (Categoria categoria: categoriaDAO.listar(con)) {
                System.out.println(categoria.getNome());
            }

            System.out.println("*****************************");


            for (Categoria categoria : categoriaDAO.listarCategoria(con)) {
                for (Tarefa tarefa:categoria.getListaTarefas()) {
                    System.out.println(tarefa.getNome() + "-" + categoria.getNome());
                }
            }

        }
    }
}
