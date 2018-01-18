package ru.bakhuss.ScreenShotNew.save.db;

import ru.bakhuss.ScreenShotNew.model.media.Media;
import ru.bakhuss.ScreenShotNew.model.person.Person;
import ru.bakhuss.ScreenShotNew.save.db.SQLite.SQLiteMedia;
import ru.bakhuss.ScreenShotNew.save.db.SQLite.SQLitePerson;
import ru.bakhuss.ScreenShotNew.save.db.SQLite.SQLitePersonalData;
import ru.bakhuss.ScreenShotNew.save.impl.SavedObjects;
import ru.bakhuss.ScreenShotNew.save.impl.repository.Repository;

import java.sql.SQLException;
import java.util.Collection;

public class SQLiteDB implements Repository {

    SQLitePerson sqLitePerson;
    SQLitePersonalData sqLitePersonalData;
    SQLiteMedia sqLiteMedia;

    public SQLiteDB() {
        sqLitePerson = new SQLitePerson();
        sqLitePersonalData = new SQLitePersonalData();
        sqLiteMedia = new SQLiteMedia();
    }

    public <T extends SavedObjects> SQLiteDB(T obj) {
        String cls = obj.getClass().getSimpleName();
        switch (cls) {
            case "Person":

        }
    }

    public <T extends SavedObjects> void method(T media) {

    }


    @Override
    public void set(SavedObjects... savedObjects) throws SQLException {
        String cls = savedObjects.getClass().getSimpleName();
        switch (cls) {
            case "Person":
                sqLitePerson.set((Person[]) savedObjects);
                break;
            default:
                System.out.println("Non");
        }
    }

    @Override
    public Collection get() throws SQLException {
        return null;
    }

    @Override
    public void update(SavedObjects... savedObjects) throws SQLException {

    }

    @Override
    public void delete(SavedObjects... savedObjects) throws SQLException {

    }
}
