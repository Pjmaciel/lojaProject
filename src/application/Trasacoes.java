package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Trasacoes {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;

        try {

            conn = DB.getConnection();

            // inicio da proteçao de transacoes
            conn.setAutoCommit(false);// diz para que nao é para confimar as operacoes automaticamente.

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090.00 Where DepartmentId = 1");

            // Lançando um erro falso
            // int x = 1;
            // if (x < 2) {
            // throw new SQLException("Fake Erro");
            // }

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090.00 Where DepartmentId = 2");

            conn.commit();// informa que a transaçao realmente terminou

            System.out.println("Rows1:  " + rows1);
            System.out.println("Rows2:  " + rows2);

        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException(" Transaction rolled back! Cause by: " + e.getMessage());
            } catch (SQLException e1) {
                throw new DbException(" \nErro trying to rollback! Caused by: " + e.getMessage());
            }

        }

    }
}
