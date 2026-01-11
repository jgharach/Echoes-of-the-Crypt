package com.badlogic.echoesofthecrypt.model.factory;

import com.badlogic.echoesofthecrypt.exception.UnknownFamilyException;
import com.badlogic.echoesofthecrypt.exception.UnknownTypeException;
import com.badlogic.echoesofthecrypt.model.entity.EnemyModel;
import com.badlogic.echoesofthecrypt.model.entity.PlayerModel;
import com.badlogic.echoesofthecrypt.model.DTO.TiledDTO;
import com.badlogic.echoesofthecrypt.model.manager.EntityModelManager;

/**
 * Factory pour créer des entités du jeu à partir de données TiledDTO.
 */
public class EntityModelFactory {

    private final EntityModelManager entityModelManager;

    // 🔹 Constantes des types d'entité
    public static final String TYPE_PLAYER = "player";
    public static final String TYPE_ENEMY = "enemy";

    public EntityModelFactory(EntityModelManager entityModelManager) {
        this.entityModelManager = entityModelManager;
    }

    /**
     * Crée et enregistre une entité selon le type défini dans le DTO.
     *
     * @param data les données TiledDTO contenant le type et position
     */
    public void create(TiledDTO data) {
        String type = data.getType();

        try {
            switch(type) {
                case TYPE_PLAYER:
                    PlayerModel playerModel = new PlayerModel(data);
                    entityModelManager.add(playerModel);
                    break;
                case TYPE_ENEMY:
                    EnemyModel enemyModel = new EnemyModel(data);
                    entityModelManager.add(enemyModel);
                    break;
                default:
                    throw new UnknownTypeException(type);
            }
        } catch (UnknownFamilyException e){
            System.err.println(e.getMessage());
        }
    }
}


