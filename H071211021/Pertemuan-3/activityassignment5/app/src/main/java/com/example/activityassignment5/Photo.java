package com.example.activityassignment5;
import android.net.Uri;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class Photo implements Parcelable {
    private Uri photoUri;

    public Photo() {}

    public Uri getPhotoUri() {
        return photoUri;
    }
    public void setPhotoUri(Uri photoUri) {
        this.photoUri = photoUri;
    }

    protected Photo(android.os.Parcel in) {
        photoUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(android.os.Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull android.os.Parcel parcel, int i) {
        parcel.writeParcelable(photoUri, i);
    }
}

