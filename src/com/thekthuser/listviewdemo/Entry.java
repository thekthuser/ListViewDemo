package com.thekthuser.listviewdemo;

public class Entry {
    public int entryId;
    public String name;
    //needs images
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


    public Entry(int entryId, String name, String summary, String price_amount, String price_currency, String content_type_term, String content_type_label, String rights, String title, String link_rel, String link_type, String link_href, String id_label, int id_id, String id_bundleId, String artist_label, String artist_href, String category_id, String category_term, String category_scheme, String category_label, String release_date, String release_date_human) {
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

    }

}
