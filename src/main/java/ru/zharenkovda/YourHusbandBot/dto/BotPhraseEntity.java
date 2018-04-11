package ru.zharenkovda.YourHusbandBot.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "BotPhraseEntity")
@Table(name="botPhrases", schema="PUBLIC")
@Data
public class BotPhraseEntity {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "occasion")
    private String occasion;

    @Column(name = "phrase")
    private String phrase;
}
