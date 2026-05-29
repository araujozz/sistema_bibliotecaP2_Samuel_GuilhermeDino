package lib.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize(){
        try (Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement()){
            
            //ativa suporte a chaves estrangeiras no SQLite
            stmt.execute("PRAGMA foreign_keys = ON");

            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS leitor(
                    id          INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome        TEXT NOT NULL,
                    cpf         TEXT NOT NULL UNIQUE,
                    telefone    TEXT,
                    email       TEXT,
                    ativo       INTEGER NOT NULL DEFAULT 1
                    )
                    """);
            
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS funcionario(
                    id          INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome        TEXT NOT NULL,
                    cpf         TEXT NOT NULL UNIQUE,
                    cargo       TEXT NOT NULL,
                    email       TEXT,
                    telefone    TEXT,
                    senha       TEXT NOT NULL,
                    ativo       INTEGER NOT NULL DEFAULT 1
                    )
                    """);
            
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS obra(
                    id          INTEGER PRIMARY KEY AUTOINCREMENT,
                    titulo      TEXT NOT NULL,
                    autor       TEXT NOT NULL,
                    isbn        TEXT,
                    ano_publicacao INTEGER,
                    editora     TEXT,
                    categoria   TEXT
                    )
                    """);
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS copia(
                    id          INTEGER PRIMARY KEY AUTOINCREMENT,
                    numero_copia INTEGER NOT NULL,
                    status      TEXT NOT NULL DEFAULT 'DISPONIVEL',
                    id_obra     INTEGER NOT NULL,
                    FOREIGN KEY (id_obra) REFERENCES obra(id)
                    )
                    """);
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS emprestimo(
                    id                  INTEGER PRIMARY KEY AUTOINCREMENT,
                    data_emprestimo     TEXT NOT NULL,
                    data_prev_devolucao TEXT NOT NULL,
                    data_dev_real       TEXT,
                    id_leitor           INTEGER NOT NULL,
                    id_copia            INTEGER NOT NULL,
                    id_funcionario      INTEGER NOT NULL,
                    FOREIGN KEY (id_leitor)      REFERENCES leitor(id),
                    FOREIGN KEY (id_copia)       REFERENCES copia(id),
                    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
                    )
                    """);
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS reserva(
                    id             INTEGER PRIMARY KEY AUTOINCREMENT,
                    data_reserva   TEXT NOT NULL,
                    data_expiracao TEXT NOT NULL,
                    status_reserva TEXT NOT NULL DEFAULT 'ATIVA',
                    id_leitor      INTEGER NOT NULL,
                    id_obra        INTEGER NOT NULL,
                    FOREIGN KEY (id_leitor) REFERENCES leitor(id),
                    FOREIGN KEY (id_obra)   REFERENCES obra(id)
                    )
                    """);
            System.out.println("Banco inicializado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inicializar banco: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
