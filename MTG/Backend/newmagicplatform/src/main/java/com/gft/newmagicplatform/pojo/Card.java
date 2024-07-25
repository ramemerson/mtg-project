package com.gft.newmagicplatform.pojo;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {

    @SerializedName("id")
    private String id;

    @SerializedName("oracle_id")
    private String oracle_id;

    @SerializedName("multiverse_ids")
    private Long[] multiverse_ids;

    @SerializedName("mtgo_id")
    private Long mtgo_id;

    @SerializedName("set_id")
    private String set_id;

    @SerializedName("name")
    private String name;

    @SerializedName("released_at")
    private String released_at;

    @SerializedName("uri")
    private String uri;

    @SerializedName("scryfall_uri")
    private String scryfall_uri;

    @SerializedName("image_uris")
    private ImageUris image_uris;

    @SerializedName("mana_cost")
    private String mana_cost;

    @SerializedName("cmc")
    private double cmc;

    @SerializedName("type_line")
    private String type_line;

    @SerializedName("oracle_text")
    private String oracle_text;

    @SerializedName("power")
    private String power;

    @SerializedName("toughness")
    private String toughness;

    @SerializedName("colors")
    private String[] colors;

    @SerializedName("color_identity")
    private String[] color_identity;

    @SerializedName("keywords")
    private String[] keywords;

    @SerializedName("set")
    private String set;

    @SerializedName("set_name")
    private String set_name;

    @SerializedName("set_type")
    private String set_type;

    @SerializedName("rarity")
    private String rarity;

    @SerializedName("prices")
    private Prices prices;

}
