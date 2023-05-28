package br.com.Jm.ListaTarefas.ConnectionFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection CriaConeccao(){
        try {
            ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/listaTarefas?");
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("root");
            return comboPooledDataSource.getConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
