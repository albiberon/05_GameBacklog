package com.example.gamebacklog.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.gamebacklog.service.database.GameBacklogRepository;
import com.example.gamebacklog.service.model.GameBacklog;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private GameBacklogRepository mRepository;
    private LiveData<List<GameBacklog>> mGameBacklogs;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = new GameBacklogRepository(application.getApplicationContext());
        mGameBacklogs = mRepository.getAllGameBacklogs();
    }

    public LiveData<List<GameBacklog>> getmGameBacklogs() {
        return mGameBacklogs;
    }

    public void insert(GameBacklog gameBacklog) {
        mRepository.insert(gameBacklog);
    }

    public void update(GameBacklog gameBacklog) {
        mRepository.update(gameBacklog);
    }

    public void delete(GameBacklog gameBacklog) {
        mRepository.delete(gameBacklog);
    }
}
