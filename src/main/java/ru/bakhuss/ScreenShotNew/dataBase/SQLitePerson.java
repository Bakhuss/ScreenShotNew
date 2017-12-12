package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLitePerson implements PersonRepository {

    SQLHandler sqlHandler;

    public SQLitePerson() {
        sqlHandler = new SQLHandler(DBType.sqlite);
    }

    @Override
    public void set(Person person) {

        try {

            String query = "insert into Full_Name (first_name, patronymic, surname) values (?,?,?)";
            sqlHandler.connect();
            sqlHandler.setPstmt(sqlHandler.getConnection().prepareStatement(query));
            sqlHandler.getPstmt().setString(1, person.getFirstName());
            sqlHandler.getPstmt().setString(2, person.getPatronymic());
            sqlHandler.getPstmt().setString(3, person.getSurname());
            sqlHandler.getPstmt().execute();
            query = "select id from Full_Name where rowid = last_insert_rowid();";
            ResultSet rs = sqlHandler.getStmt().executeQuery(query);
            rs.next();
            int idFromDB = rs.getInt(1);
            System.out.println("idFromDB: " + idFromDB);
            person.setPersonIdInDB(idFromDB);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error: SQLitePerson setPerson");
        } finally {
            sqlHandler.disconnect();
        }
    }

    @Override
    public Person get() {
        return null;
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void remove(Person person) {
        try {
            String query = "delete from Full_Name where id = " + person.getPersonIdInDB() + ";";
            sqlHandler.connect();
            sqlHandler.getStmt().execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error: SQLitePerson remove");
        } finally {
            sqlHandler.disconnect();
        }
    }
}
