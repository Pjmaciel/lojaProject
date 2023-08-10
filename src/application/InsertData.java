package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;

public class InsertData {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();

            // st = conn.prepareStatement("INSERT INTO seller" +
            // "(Name, Email, BirthDate, BaseSalary,DepartmentId)" +
            // "VALUES" +
            // "(?, ?, ?, ?, ?)",
            // Statement.RETURN_GENERATED_KEYS);

            // st.setString(1, "Pedro PedeMoleque");
            // st.setString(2, "pedropedemoleque@gmail.com");
            // st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
            // st.setDouble(4, 3000);
            // st.setInt(5, 4);

            st = conn.prepareStatement("insert into department (Name) values ('D1'),('D2')",
                    Statement.RETURN_GENERATED_KEYS);
            int rowsAffected = st.executeUpdate();// Resulta um numero inteiro dizendo quantas linhas foram alteradas

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();// Retorna o Resultset
                while (rs.next()) { // pecorre o Resultset
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            } else {
                System.out.println("No rown affected!");
            }

        } catch (SQLException e) {
            e.printStackTrace();

            // } catch (ParseException e) {
            // e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
