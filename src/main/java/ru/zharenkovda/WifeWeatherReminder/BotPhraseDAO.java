package ru.zharenkovda.WifeWeatherReminder;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BotPhraseDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<BotPhraseEntity> getMornignPhrases(){
       return entityManager.createQuery("select p from BotPhraseEntity p").getResultList();
    }

}
