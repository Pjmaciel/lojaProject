package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class UpdateData {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();// Conectar no banco

            pst = conn.prepareStatement("UPDATE seller " // checa para ver se tem o espaço em branco antes de fechar as
                                                         // aspas
                    + "SET BaseSalary = BaseSalary + ? "
                    + "WHERE "
                    + "(departmentId = ? )");

            pst.setDouble(1, 200.0);
            pst.setInt(2, 2);

            int rowsAffected = pst.executeUpdate();

            System.out.println("Done! Rows affected " + rowsAffected);

        } catch (SQLException e) {
            e.fillInStackTrace();
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }
}
