package DAO;

import Util.ConnectionUtil;
import Model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class AccountDAO {

    public Account createAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO account (username, password) VALUES (?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            while(rs.next()) {
                account.setAccount_id(rs.getInt("account_id"));
            }

            return account;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Account login(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        Account accountCheck = null;

        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                accountCheck = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                return accountCheck;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return accountCheck;
    }

    public Account getAccountBy_id(int id) {
        Connection connection = ConnectionUtil.getConnection();
        Account account = null;

        try {
            String sql = "SELECT * FROM account WHERE account_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            
            if(rs.next()){
                account = new Account(rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"));
                return account;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return account;
    }
}