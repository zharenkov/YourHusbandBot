package ru.zharenkovda.WifeWeatherReminder.dao;

import org.springframework.stereotype.Repository;
import ru.zharenkovda.WifeWeatherReminder.dto.BotPhraseEntity;
import ru.zharenkovda.WifeWeatherReminder.dto.StickerEntity;
import ru.zharenkovda.WifeWeatherReminder.enumerations.BotPhraseType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DatabaseDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public BotPhraseEntity getRandomPhrasesByType(BotPhraseType type){
       return (BotPhraseEntity) entityManager
               .createQuery("select p from BotPhraseEntity p where p.occasion = :type order by rand()")
               .setMaxResults(1)
               .setParameter("type",type.getType())
               .getSingleResult();
    }


    public StickerEntity getRandomSticker(){
        return (StickerEntity) entityManager.createQuery("select p from StickerEntity p order by rand()").setMaxResults(1)
                .getSingleResult();
    }

}
