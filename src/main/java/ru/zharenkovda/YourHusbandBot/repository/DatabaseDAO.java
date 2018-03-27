package ru.zharenkovda.YourHusbandBot.repository;

import org.springframework.stereotype.Repository;
import ru.zharenkovda.YourHusbandBot.dto.BotPhraseEntity;
import ru.zharenkovda.YourHusbandBot.dto.StickerEntity;
import ru.zharenkovda.YourHusbandBot.enumerations.BotPhraseType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        return (StickerEntity) entityManager.createQuery("select p from StickerEntity p order by rand()")
                .setMaxResults(1)
                .getSingleResult();
    }

}
