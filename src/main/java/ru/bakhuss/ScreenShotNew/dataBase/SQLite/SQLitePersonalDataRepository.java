package ru.bakhuss.ScreenShotNew.dataBase.SQLite;

import ru.bakhuss.ScreenShotNew.dataBase.DBType;
import ru.bakhuss.ScreenShotNew.dataBase.PersonalDataRepository;
import ru.bakhuss.ScreenShotNew.dataBase.SQLHandler;
import ru.bakhuss.ScreenShotNew.model.person.PersonalData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLitePersonalDataRepository implements PersonalDataRepository {

    SQLHandler sqlHandler;
//    Statement stmt;
//    PreparedStatement pstmt;

    public SQLitePersonalDataRepository() {
        sqlHandler = new SQLHandler(DBType.sqlite);
    }

    public SQLitePersonalDataRepository(SQLHandler sqlHandler) {
        this.sqlHandler = sqlHandler;
    }

    @Override
    public void set(PersonalData personalData) throws SQLException {
//        try {
//            sqlHandler.connect();
            String query = "insert into Personal_Data (birthDay, birthPlace, deathDay, deathPlace, height, eyeColor, hairColor)" +
                    " values (?,?,?,?,?,?,?)";
            sqlHandler.setPstmt(sqlHandler.getConnection().prepareStatement(query));;
            sqlHandler.getPstmt().setString(1, personalData.getBirthDay());
            sqlHandler.getPstmt().setString(2, personalData.getBirthPlace());
            sqlHandler.getPstmt().setString(3, personalData.getDeathDay());
            sqlHandler.getPstmt().setString(4, personalData.getDeathPlace());
            sqlHandler.getPstmt().setString(5, personalData.getHeight());
            sqlHandler.getPstmt().setString(6, personalData.getEyeColor());
            sqlHandler.getPstmt().setString(7, personalData.getHairColor());
            sqlHandler.getPstmt().execute();
//            sqlHandler.getStmt().execute("select id from Personal_Data where rowid = last_insert_rowid();");
            sqlHandler.getPstmt().clearParameters();

//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("error: SQLitePersonalDataRepository set");
//        } finally {
////            sqlHandler.disconnect();
//        }

    }


    @Override
    public PersonalData get() {
        return null;
    }

    @Override
    public ArrayList<PersonalData> getAll() {
        return null;
    }

    @Override
    public void update(PersonalData personalData) {

    }

    @Override
    public void remove(PersonalData personalData) {

    }
}
