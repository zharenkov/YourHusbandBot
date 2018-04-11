package ru.zharenkovda.YourHusbandBot.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "StickerEntity")
@Table(name="sticker", schema="PUBLIC")
@Data
public class StickerEntity {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "code")
    private String stickerCode;
}
