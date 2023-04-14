package com.example.activityassignment6;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class User implements Parcelable {
    private String username;
    private String imageUri;
    private ArrayList<Chat> chats;
    private Chat lastChat;
    private String phoneNumber;
    private String status;
    private String statusDate;
    public User() {}

    public Chat getLastChat() {
        return lastChat;
    }

    public void setLastChat(Chat lastChat) {
        this.lastChat = lastChat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    protected User(Parcel in) {
        username = in.readString();
        imageUri = in.readString();
        phoneNumber = in.readString();
        status = in.readString();
        statusDate = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(imageUri);
        dest.writeString(phoneNumber);
        dest.writeString(status);
        dest.writeString(statusDate);
    }
}
