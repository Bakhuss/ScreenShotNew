package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.model.person.Person;
import ru.bakhuss.ScreenShotNew.view.mainViewController;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLitePerson implements PersonRepository {

    SQLHandler sqlHandler;

    public SQLitePerson() {
        sqlHandler = new SQLHandler(DBType.sqlite);
    }

    @Override
    public void set(Person person) {

        try {

            String query = "insert into Full_Name (surname, first_name, patronymic) values (?,?,?)";
            sqlHandler.connect();
            sqlHandler.setPstmt(sqlHandler.getConnection().prepareStatement(query));
            sqlHandler.getPstmt().setString(1, person.getSurname());
            sqlHandler.getPstmt().setString(2, person.getFirstName());
            sqlHandler.getPstmt().setString(3, person.getPatronymic());
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
    public ArrayList<Person> getAll() {
        ArrayList<Person> people = new ArrayList<>();
        Person p = null;
        try {
            String query = "select * from Full_Name";
            sqlHandler.connect();
            ResultSet rs = sqlHandler.getStmt().executeQuery(query);
            while (rs.next()) {
                p = new Person();
                p.setPersonIdInDB(rs.getInt(1));
                p.setSurname(rs.getString(2));
                p.setFirstName(rs.getString(3));
                p.setPatronymic(rs.getString(4));
                people.add(p);
                p = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error: SQLitePerson get()");
        } finally {
            sqlHandler.disconnect();
        }

        return people;
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

    public int getCountFromRep() {
        String query = "select count(*) from Full_Name";
        int count = 0;
        try {
            sqlHandler.connect();
            ResultSet rs = sqlHandler.getStmt().executeQuery(query);
            rs.next();
            count =  rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error: SQLitePerson getCountFromRep()");
        } finally {
            sqlHandler.disconnect();
        }
        return count;
    }
}
