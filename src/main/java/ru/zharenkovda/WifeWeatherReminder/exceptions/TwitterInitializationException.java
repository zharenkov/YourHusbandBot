package ru.zharenkovda.WifeWeatherReminder.exceptions;

public class TwitterInitializationException extends Exception {

    public TwitterInitializationException() {
        super();
    }

    public TwitterInitializationException(String s) {
        super(s);
    }

    public TwitterInitializationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public TwitterInitializationException(Throwable throwable) {
        super(throwable);
    }
}
