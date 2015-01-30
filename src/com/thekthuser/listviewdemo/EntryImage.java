package com.thekthuser.listviewdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class EntryImage implements Parcelable {
    public int id;
    public int imageId; // image's place in json list
    public int entryid_id;
    public String url;
    public int height;


    public EntryImage(int id, int imageId, int entryid_id, String url, int height) {
        this.id = id;
        this.imageId = imageId;
        this.entryid_id = entryid_id;
        this.url = url;
        this.height = height;
    }


    public EntryImage(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeInt(imageId);
        parcel.writeInt(entryid_id);
        parcel.writeString(url);
        parcel.writeInt(height);
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        imageId = in.readInt();
        entryid_id = in.readInt();
        url = in.readString();
        height = in.readInt();
    }

    public static final Parcelable.Creator<EntryImage> CREATOR = new Parcelable.Creator<EntryImage>() {
        public EntryImage createFromParcel(Parcel in) {
            return new EntryImage(in);
        }
        public EntryImage[] newArray(int size) {
            return new EntryImage[size];
        }
    };

}
