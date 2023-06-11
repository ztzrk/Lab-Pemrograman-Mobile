package com.example.activityassigment10;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Note implements Parcelable {
    private int id;
    private String title, description, date;
    private Boolean isEdited;

    public Note() {
    }

    public Note(int id, String title, String description, String date, int isEdited) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.isEdited = isEdited == 1;
    }

    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        date = in.readString();
        byte tmpIsEdited = in.readByte();
        isEdited = tmpIsEdited == 0 ? null : tmpIsEdited == 1;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(date);
        parcel.writeByte((byte) (isEdited == null ? 0 : isEdited ? 1 : 2));
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getEdited() {
        return isEdited;
    }

    public void setEdited(Boolean edited) {
        isEdited = edited;
    }
}
