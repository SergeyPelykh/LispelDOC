package com.lispel.lispeldoc.newVersion.models;



import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Junction;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.lispel.lispeldoc.model.utility.Convert;
import com.lispel.lispeldoc.newVersion.interfaces.Client;
import com.lispel.lispeldoc.newVersion.interfaces.PrintUnit;
import com.lispel.lispeldoc.newVersion.interfaces.Sticker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity(tableName = "cartridge_table", foreignKeys = {@ForeignKey(entity = ClientLispelPerson.class, parentColumns = "id", childColumns = "owner_id")})
@TypeConverters({Convert.class})
public class Cartridge implements PrintUnit {
    @PrimaryKey
    private long id;
    private String name;
    private String vendor;
    private boolean originality;
    @Ignore
    private ClientLispelPerson owner;
    @ColumnInfo(name = "owner_id")
    private long ownerId;
    @Ignore
    private List<StickerLispel> stickers;
    @TypeConverters({Convert.class})
    private List<String>stickerNumbersIds;

    public List<String> getStickerNumbersIds() {
        return stickerNumbersIds;
    }

    public void setStickerNumbersIds(List<String> stickerNumbersIds) {
        this.stickerNumbersIds = stickerNumbersIds;
    }


    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public void setOwner(ClientLispelPerson owner) {
        ownerId = owner.getId();
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public boolean isOriginality() {
        return originality;
    }

    public void setStickers(List<StickerLispel> stickers) {
        this.stickers = stickers;
    }

    public Cartridge() {
        id = new Date().getTime();
        stickers = new ArrayList<>();
    }

    public void addSticker(StickerLispel stickerLispel){
        stickers.add(stickerLispel);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setOriginality(boolean originality) {
        this.originality = originality;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Sticker> getStickers() {
        return new ArrayList<>(stickers);
    }

    @Override
    public Sticker getCurrentSticker() {
        if (stickers.isEmpty()){
            return null;
        } else {
            return stickers.get(stickers.size() - 1);
        }
    }

    @Override
    public ClientLispelPerson getOwner() {
        return  owner;
    }
}
