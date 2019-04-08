package com.example.gamebacklog.service.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.gamebacklog.service.model.GameBacklog;

@Database(entities = {GameBacklog.class}, version = 3)
public abstract class GameBacklogRoomDatabase extends RoomDatabase {


    private static GameBacklogRoomDatabase instance;

    public abstract GameBacklogDao gameBacklogDao();



    //Singleton to create database here
    public static GameBacklogRoomDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (GameBacklogRoomDatabase.class) {
                if (instance == null) {

                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            GameBacklogRoomDatabase.class, "gbl_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallBack)
                            .build();
                }
            }
        }
        return instance;
    }


    //for pre-populating the database
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };



    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private GameBacklogDao gameBacklogDao;

        private PopulateDbAsyncTask(GameBacklogRoomDatabase db) {
            gameBacklogDao = db.gameBacklogDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {

            //Database populated with pre-existing GameBackLogs
            gameBacklogDao.insertGameBacklog(new GameBacklog("The Witcher 3", "PC", "Playing", "15/02/2019"));
            gameBacklogDao.insertGameBacklog(new GameBacklog("Overwatch", "PC", "Dropped", "15/02/2019"));
            gameBacklogDao.insertGameBacklog(new GameBacklog("Destiny 2", "PC", "Stalled", "15/02/2019"));

            return null;
        }
    }






}
