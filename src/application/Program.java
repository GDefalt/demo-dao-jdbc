package application;

import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Program {
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DB.getConnection();
            ps = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");

            ps.setInt(1, 9);

            int rowsAffected = ps.executeUpdate();

            System.out.println("Rows affected: " + rowsAffected);

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            DB.closeConnection();
            DB.closeStatement(ps);
        }
    }
}
