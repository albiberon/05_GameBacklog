package com.example.gamebacklog.service.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.gamebacklog.service.model.GameBacklog;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GameBacklogRepository {

    private GameBacklogRoomDatabase mAppDatabase;
    private GameBacklogDao mGameBacklogDao;
    private LiveData<List<GameBacklog>> mGameBacklogs;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public GameBacklogRepository(Context context) {
        mAppDatabase = GameBacklogRoomDatabase.getDatabase(context);
        mGameBacklogDao = mAppDatabase.gameBacklogDao();
        mGameBacklogs = mGameBacklogDao.getAllGameBacklogs();
    }

    public LiveData<List<GameBacklog>> getAllGameBacklogs() {
        return mGameBacklogs;
    }

    public void insert(final GameBacklog gameBacklog) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mGameBacklogDao.insertGameBacklog(gameBacklog);
            }
        });
    }

    public void update(final GameBacklog gameBacklog) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mGameBacklogDao.updateGameBacklog(gameBacklog);
            }
        });
    }

    public void delete(final GameBacklog gameBacklog) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mGameBacklogDao.deleteGameBacklog(gameBacklog);
            }
        });
    }
}
