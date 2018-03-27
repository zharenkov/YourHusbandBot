package ru.zharenkovda.YourHusbandBot.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "StickerEntity")
@Table(name="sticker", schema="PUBLIC")
public class StickerEntity {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "code")
    private String stickerCode;

    public StickerEntity() {
    }

    public StickerEntity(long id, String stickerCode) {
        this.id = id;
        this.stickerCode = stickerCode;
    }

    public String getStickerCode() {

        return stickerCode;
    }

    public void setStickerCode(String stickerCode) {
        this.stickerCode = stickerCode;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
