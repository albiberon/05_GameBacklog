package com.example.gamebacklog.service.database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.gamebacklog.service.model.GameBacklog;


import java.util.List;

@Dao
public interface GameBacklogDao {

    @Query("SELECT * FROM gamebacklog")
    public LiveData<List<GameBacklog>> getAllGameBacklogs();

    @Insert
    void insertGameBacklog(GameBacklog gameBacklog);

    @Delete
    void deleteGameBacklog(GameBacklog gameBacklog);

    @Update
    void updateGameBacklog(GameBacklog gameBacklog);
}

