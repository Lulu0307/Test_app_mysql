package ru.netology;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.Data.UserData;
import ru.netology.Data.VerificationInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseHelper {

    static final String URL = "jdbc:mysql://localhost:3306/app";


    public static Connection getConnect() {
        try {
            return DriverManager.getConnection(URL, "app", "pass");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void cleanDatabase() throws SQLException {
        val cleanUsersTable = "DELETE FROM users;";
        val cleanAuthTable = "DELETE FROM auth_codes;";
        val cleanTransactionsTable = "DELETE FROM card_transactions;";
        val cleanCardsTable = "DELETE FROM cards;";
        val runner = new QueryRunner();
        try (val conn = getConnect()) {
            runner.execute(conn, cleanTransactionsTable);
            runner.execute(conn, cleanCardsTable);
            runner.execute(conn, cleanAuthTable);
            runner.execute(conn, cleanUsersTable);
        }
    }


    public static String findCode() throws SQLException {
        val request = "SELECT * FROM auth_codes WHERE created = (SELECT MAX(created) FROM auth_codes);";
        val runner = new QueryRunner();
        VerificationInfo data;
        ResultSetHandler<VerificationInfo> bh = new BeanHandler<>(VerificationInfo.class);
        try (val conn = getConnect()) {
            data = runner.query(conn, request, bh);
        }
        return data.getCode();
    }

    public static void updateUsersTable(){
        val query = "INSERT INTO users(id,login,password,status)  VALUES (?,?,?,?);";
        val pass = "$2a$10$CIasK0AbTy76H5f.hokUtO3KJWpSVmiRAuFQJS3DFfqqY4Q2/Y0py";
        val id = "123456789#";
        val runner = new QueryRunner();
        try (val conn = getConnect()) {
           runner.update(conn,query,id, UserData.validAuthInfo().getLogin(),pass,"active");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}