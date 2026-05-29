package lib;

import java.sql.Connection;

import lib.dao.ConnectionFactory;
import lib.dao.DatabaseInitializer;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initialize();

        try (Connection conn = ConnectionFactory.getConnection()) {

            System.out.println("Conectado ao banco!");

        } catch (Exception e) {
            e.printStackTrace();
        }
   }
}
