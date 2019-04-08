package com.example.gamebacklog.service.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "gamebacklog")
public class GameBacklog implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    int id;
    public String title;
    public String platform;
    public String status;
    public String date;

    public GameBacklog(String title, String platform, String status, String date) {
        this.title = title;
        this.platform = platform;
        this.status = status;
        this.date = date;
    }

    @Override
    public String toString() {
        return "GameBacklog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", platform='" + platform + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    protected GameBacklog(Parcel in) {
        id = in.readInt();
        title = in.readString();
        platform = in.readString();
        status = in.readString();
        date = in.readString();
    }

    public static final Creator<GameBacklog> CREATOR = new Creator<GameBacklog>() {
        @Override
        public GameBacklog createFromParcel(Parcel in) {
            return new GameBacklog(in);
        }

        @Override
        public GameBacklog[] newArray(int size) {
            return new GameBacklog[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(platform);
        dest.writeString(status);
        dest.writeString(date);
    }
}
