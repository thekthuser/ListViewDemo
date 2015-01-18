package com.thekthuser.listviewdemo;

public class Entry {
    public int entryId;
    public String name;
    //needs images
    public String summary;
    public String price_amount;
    public String price_currency;
    public String content_type_term;
    public String content_type_label;
    public String rights;
    public String title;
    public String link_rel;
    public String link_type;
    public String link_href;
    public String id_label;
    public String id_id;
    public String id_bundleId;
    public String artist_label;
    public String artist_href;
    public String category_id;
    public String category_term;
    public String category_scheme;
    public String category_label;
    public String release_date;
    public String release_date_human;


    public Entry(int entryId, String name) {
        this.entryId = entryId;
        this.name = name;
    }
}
