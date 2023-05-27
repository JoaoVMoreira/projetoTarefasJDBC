package br.com.Jm.ListaTarefas.DAO;

import br.com.Jm.ListaTarefas.Modulos.Tarefa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    private Connection connection;
    public List<Tarefa> Listar(Connection connection) throws SQLException {
        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM TAREFA";
        try (PreparedStatement str = connection.prepareStatement(sql);){
            str.execute();

            try(ResultSet rs = str.getResultSet()){
                while (rs.next()){
                    Tarefa tarefa =
                            new Tarefa(rs.getString(2), rs.getString(3));
                    tarefas.add(tarefa);
                }
            }
        }
        return tarefas;
    }
    public void Inserir(Connection connection, Tarefa tarefa) throws SQLException {
        String sql = "INSERT INTO TAREFA (NOME, STATUS, DESCRICAO) VALUE(?, ?, ?)";

        try(PreparedStatement prst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            prst.setString(1, tarefa.getNome());
            prst.setBoolean(2, tarefa.getStatus());
            prst.setString(3, tarefa.getDescricao());
            prst.execute();

            try(ResultSet rs = prst.getGeneratedKeys()){
                while(rs.next()){
                    tarefa.setId(rs.getInt(1));
                }
            }
        }
    }
    public void Exlcuir(Connection connection, Integer id) throws SQLException{
        String sql = "DELETE FROM TAREFA WHERE ID = ?";

        try(PreparedStatement pr = connection.prepareStatement(sql)){
            pr.setInt(1, id);
            try{
                pr.execute();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void Concluir(Connection connection, Tarefa tarefa) throws SQLException {
        String sql = "";
        if(tarefa.getStatus()){
            sql = "UPDATE TAREFA SET STATUS = TRUE WHERE ID = ?";
        }else{
            sql = "UPDATE TAREFA SET STATUS = FALSE WHERE ID = ?";
        }
        try(PreparedStatement pr = connection.prepareStatement(sql)){
            pr.execute();
        }
    }

}
