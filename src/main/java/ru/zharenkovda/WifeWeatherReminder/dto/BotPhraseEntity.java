package ru.zharenkovda.WifeWeatherReminder.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "BotPhraseEntity")
@Table(name="botPhrases", schema="PUBLIC")
public class BotPhraseEntity {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "occasion")
    private String occasion;

    @Column(name = "phrase")
    private String phrase;

    public BotPhraseEntity(int id, String occasion, String phrase) {
        this.id = id;
        this.occasion = occasion;
        this.phrase = phrase;
    }

    public BotPhraseEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public String toString() {
        return "BotPhraseEntity{" +
                "id=" + id +
                ", occasion='" + occasion + '\'' +
                ", phrase='" + phrase + '\'' +
                '}';
    }
}
