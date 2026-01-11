package com.badlogic.echoesofthecrypt.model.factory;

import com.badlogic.echoesofthecrypt.exception.UnknownFamilyException;
import com.badlogic.echoesofthecrypt.model.DTO.TiledDTO;
import com.badlogic.echoesofthecrypt.model.manager.EntityModelManager;
import com.badlogic.echoesofthecrypt.model.manager.PlatformObjectModelManager;

/**
 * Factory principale qui orchestre la création de tous les modèles à partir
 * de TiledDTO. Elle délègue la création aux factories spécialisées
 * (EntityModelFactory ou PlatformObjectModelFactory) selon la "famille" de l'objet.
 *
 * Cette classe représente le point central pour enrichir le jeu depuis Tiled
 * sans modifier le code Java.
 */
public class ModelMasterFactory {

    // 🔹 Constantes pour les familles
    public static final String FAMILY_PLATFORM = "platform";
    public static final String FAMILY_ENTITY = "entity";

    private final PlatformObjectModelFactory platformObjectModelFactory;
    private final EntityModelFactory entityModelFactory;

    public ModelMasterFactory(
        PlatformObjectModelManager platformObjectModelManager,
        EntityModelManager entityModelManager) {

        this.platformObjectModelFactory = new PlatformObjectModelFactory(platformObjectModelManager);
        this.entityModelFactory = new EntityModelFactory(entityModelManager);
    }

    /**
     * Crée un modèle selon sa famille et son type définis dans le DTO.
     *
     * @param data le DTO provenant de Tiled contenant la famille et le type
     */
    public void create(TiledDTO data) {
        String family = data.getFamily();

        try {
            switch(family) {
                case FAMILY_PLATFORM:
                    platformObjectModelFactory.create(data);
                    break;
                case FAMILY_ENTITY:
                    entityModelFactory.create(data);
                    break;
                default:
                    throw new UnknownFamilyException(family);
            }
        } catch (UnknownFamilyException e){
            System.err.println(e.getMessage());
        }
    }
}

