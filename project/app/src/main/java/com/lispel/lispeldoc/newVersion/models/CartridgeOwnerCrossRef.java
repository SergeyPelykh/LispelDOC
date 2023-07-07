package com.lispel.lispeldoc.newVersion.models;

import androidx.room.Entity;

@Entity(primaryKeys = {"cartridgeId", "ownerId"})
public class CartridgeOwnerCrossRef {
    public long cartridgeId;
    public long ownerId;
}
