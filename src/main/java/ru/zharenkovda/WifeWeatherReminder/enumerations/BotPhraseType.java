package ru.zharenkovda.WifeWeatherReminder.enumerations;

public enum BotPhraseType {
    MORNING("morning"),
    COMMON("common"),
    LOVE("love");


    String type;


    public String getType() {
        return type;
    }

    BotPhraseType(String type) {

        this.type = type;
    }
}
