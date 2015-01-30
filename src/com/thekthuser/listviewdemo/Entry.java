package com.thekthuser.listviewdemo;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.ArrayList;

public class Entry implements Parcelable {
    public int id;
    public int entryId; //entry's place in json list
    public String name;
    public String summary;
    public String price_amount; //would be float, but not doing any math with it
    public String price_currency;
    public String content_type_term;
    public String content_type_label;
    public String rights;
    public String title;
    public String link_rel;
    public String link_type;
    public String link_href;
    public String id_label;
    public int id_id; //I'm assuming this is a unique id and will use it as such
    public String id_bundleId;
    public String artist_label;
    public String artist_href;
    public String category_id;
    public String category_term;
    public String category_scheme;
    public String category_label;
    public String release_date;
    public String release_date_human;
    public EntryImage[] images;


    public Entry(int id, int entryId, String name, String summary, String price_amount, String price_currency, String content_type_term, String content_type_label, String rights, String title, String link_rel, String link_type, String link_href, String id_label, int id_id, String id_bundleId, String artist_label, String artist_href, String category_id, String category_term, String category_scheme, String category_label, String release_date, String release_date_human, EntryImage[] images) {
        this.id = id;
        this.entryId = entryId;
        this.name = name;
        this.summary = summary;
        this.price_amount = price_amount;
        this.price_currency = price_currency;
        this.content_type_term = content_type_term;
        this.content_type_label = content_type_label;
        this.rights = rights;
        this.title = title;
        this.link_rel = link_rel;
        this.link_type = link_type;
        this.link_href = link_href;
        this.id_label = id_label;
        this.id_id = id_id;
        this.id_bundleId = id_bundleId;
        this.artist_label = artist_label;
        this.artist_href = artist_href;
        this.category_id = category_id;
        this.category_term = category_term;
        this.category_scheme = category_scheme;
        this.category_label = category_label;
        this.release_date = release_date;
        this.release_date_human = release_date_human;
        this.images = images;

    }


    public Entry(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeInt(entryId);
        parcel.writeString(name);
        parcel.writeString(summary);
        parcel.writeString(price_amount);
        parcel.writeString(price_currency);
        parcel.writeString(content_type_term);
        parcel.writeString(content_type_label);
        parcel.writeString(rights);
        parcel.writeString(title);
        parcel.writeString(link_rel);
        parcel.writeString(link_type);
        parcel.writeString(link_href);
        parcel.writeString(id_label);
        parcel.writeInt(id_id);
        parcel.writeString(id_bundleId);
        parcel.writeString(artist_label);
        parcel.writeString(artist_href);
        parcel.writeString(category_id);
        parcel.writeString(category_term);
        parcel.writeString(category_scheme);
        parcel.writeString(category_label);
        parcel.writeString(release_date);
        parcel.writeString(release_date_human);
        parcel.writeParcelableArray(images, flags);
    }
    private void readFromParcel(Parcel in) {
        id = in.readInt();
        entryId = in.readInt();
        name = in.readString();
        summary = in.readString();
        price_amount = in.readString();
        price_currency = in.readString();
        content_type_term = in.readString();
        content_type_label = in.readString();
        rights = in.readString();
        title = in.readString();
        link_rel = in.readString();
        link_type = in.readString();
        link_href = in.readString();
        id_label = in.readString();
        id_id = in.readInt();
        id_bundleId = in.readString();
        artist_label = in.readString();
        artist_href = in.readString();
        category_id = in.readString();
        category_term = in.readString();
        category_scheme = in.readString();
        category_label = in.readString();
        release_date = in.readString();
        release_date_human = in.readString();
        Parcelable[] parcelableArray = in.readParcelableArray(EntryImage.class.getClassLoader());
        images = null;
        if (parcelableArray != null) {
            images = Arrays.copyOf(parcelableArray, parcelableArray.length, EntryImage[].class);
        }
    }
    public static final Parcelable.Creator<Entry> CREATOR = new Parcelable.Creator<Entry>() {
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };
}
