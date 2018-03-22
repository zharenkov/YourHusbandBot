package ru.zharenkovda.WifeWeatherReminder.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PhraseRepository {

    static Map<String, List<String>> phrases = new HashMap<>();

    static {
        phrases.put("morning_greetings", Stream.of("morningphrase1","morningphrase2").collect(Collectors.toList()));
        phrases.put("common_phrases", Stream.of("phrase1","phrase2").collect(Collectors.toList()));
        phrases.put("love_phrases", Stream.of("phrase1","phrase2").collect(Collectors.toList()));

    }

}
