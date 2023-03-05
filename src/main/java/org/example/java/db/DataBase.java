package org.example.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    public static void init() {
        initProfile();
        initCard();
        terminalInit();
        initTransaction();
    }

    public static void initProfile() {
        String sql = "create table if not exists  profile(id serial primary key," +
                "name varchar ," +
                "surname varchar," +
                "password varchar ," +
                "phone  varchar  ," +
                "created_date timestamp default now()," +
                "status varchar default 'REGISTRATION'," +
                "role varchar default 'USER');";
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initCard() {
        String sql = "create table if not exists  card(id serial primary key," +
                "number varchar ," +
                "exp_date varchar," +
                "amount  decimal(10,4) default 0 ," +
                "profile_id  int default null ," +
                "created_date timestamp default now()," +
                "added_date timestamp ," +
                "status varchar default 'NOACTIVE')";
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void terminalInit() {
        String sql = "create table if not exists  terminal(id serial primary key," +
                "number varchar ," +
                "address varchar," +
                "created_date timestamp default now()," +
                "status varchar default 'ACTIVE')";
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initTransaction() {
        String sql = "create table if not exists  transaction(id serial primary key," +
                "profile_id int " +
                "card_id int  ," +
                "terminal_id int " +
                "amount decimal(10,2) default 1400.00," +
                "created_date timestamp default now()," +
                "type varchar default 'PAYMENT'," +
                "foreign key (card_id) references card(id)," +
                "foreign key (profile_id) references profile(id)," +
                "foreign key (termianl_id) references terminal(id))";
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5433/atto_db", "postgres", "123");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }


}
