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
            TarefaDAO tarefaDAO = new TarefaDAO(con);
            CategoriaDAO categoriaDAO = new CategoriaDAO(con);
            //Tarefa tarefa2 = new Tarefa("Teste", "Testando inserção");

            //tarefaDAO.Inserir(new Tarefa("Limpar caixa de areia", "Limpar caixa de areia do gato"));
            //Tarefa tarefa = new Tarefa("Testando", "mais um teste", 1);

//            for (Tarefa tarefa : tarefaDAO.Listar()) {
//                System.out.println("Nome:" + tarefa.getNome());
//                System.out.println("Status: " + tarefa.getStatus());
//                System.out.println("Descricao: " + tarefa.getDescricao());
//                System.out.println("Categoria_id: "  + tarefa.getCategoria_id());
//            }
//
//            System.out.println("*****************************");

//            for (Categoria categoria: categoriaDAO.listar()) {
//                System.out.println(categoria.getNome());
//            }
//
//            System.out.println("*****************************");


            for (Categoria categoria : categoriaDAO.listarCategoria()) {
                for (Tarefa tarefa:categoria.getTarefas()) {
                    System.out.println(tarefa.getNome() + "-" + categoria.getNome());
                }
            }

            System.out.println("*****************************");


        }
    }
}
