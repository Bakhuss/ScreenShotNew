package ru.bakhuss.ScreenShotNew.save;

import ru.bakhuss.ScreenShotNew.save.impl.SavedObjects;
import ru.bakhuss.ScreenShotNew.save.impl.repository.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Save implements Repository<SavedObjects> {

    private HashMap<String, Integer> temp;

    public Save(){}


    @Override
    public void set(SavedObjects... savedObjects) throws SQLException, IOException {
        getTemp();
        ArrayList<SavedObjects> list = new ArrayList<>(Arrays.asList(savedObjects));
        long t = System.nanoTime();
        for (SavedObjects so : list) {
            String key = so.getClass().getSimpleName();
            int value = temp.get(so.getClass().getSimpleName());
            temp.replace(key,(value + 1));
        }
        System.out.println("t: " + (System.nanoTime() - t));
        System.out.println("temp");
        for (Map.Entry entr : temp.entrySet()) {
            System.out.println(entr.getKey() + " " + entr.getValue());
        }
        temp = null;
    }

    private HashMap<String, Integer> getTemp() {
        temp = new HashMap<>(7);
        temp.put("Media", 0);
        temp.put("Image", 0);
        temp.put("Audio", 0);
        temp.put("Video", 0);
        temp.put("MediaGroup", 0);
        temp.put("Person", 0);
        temp.put("PersonalData", 0);
        return temp;
    }

    @Override
    public Collection<SavedObjects> get() throws SQLException, IOException {
        return null;
    }

    @Override
    public void update(SavedObjects... savedObjects) throws SQLException, IOException {

    }

    @Override
    public void delete(SavedObjects... savedObjects) throws SQLException, IOException {

    }
}
