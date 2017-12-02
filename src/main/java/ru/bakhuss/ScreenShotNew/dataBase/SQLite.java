package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.model.media.Media;
import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.sql.SQLException;

public class SQLite implements PersonRepository {


    private SQLHandler sqlHandler = new SQLHandler("jdbc:sqlite:");


    // person
    public void setPerson(Person person) {
        try {
            sqlHandler.connect();



        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к БД SQLite");
        } finally {
            sqlHandler.disconnect();
        }
    }

    public Person getPerson() {
        try {
            sqlHandler.connect();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к БД SQLite");
        } finally {
            sqlHandler.disconnect();
        }

        return null;
    }

    public void updatePerson(Person person) {
        try {
            sqlHandler.connect();
            System.out.println("update");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к БД SQLite");
        } finally {
            sqlHandler.disconnect();
        }
    }

    public void removePerson(Person person) {
        try {
            sqlHandler.connect();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к БД SQLite");
        } finally {
            sqlHandler.disconnect();
        }
    }


    // media
    public void setMedia() {
        try {
            sqlHandler.connect();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к БД SQLite");
        } finally {
            sqlHandler.disconnect();
        }
    }

    public Media getMedia() {
        try {
            sqlHandler.connect();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к БД SQLite");
        } finally {
            sqlHandler.disconnect();
        }

        return null;
    }

    public void updateMedia() {
        try {
            sqlHandler.connect();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к БД SQLite");
        } finally {
            sqlHandler.disconnect();
        }
    }

    public void removeMedia() {
        try {
            sqlHandler.connect();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к БД SQLite");
        } finally {
            sqlHandler.disconnect();
        }
    }
}
