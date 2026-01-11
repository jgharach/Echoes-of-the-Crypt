package com.badlogic.echoesofthecrypt.model.factory;

import com.badlogic.echoesofthecrypt.exception.UnknownTypeException;
import com.badlogic.echoesofthecrypt.model.manager.PlatformObjectModelManager;
import com.badlogic.echoesofthecrypt.model.DTO.TiledDTO;
import com.badlogic.echoesofthecrypt.model.platform.objects.SolidPlatformObjectModel;
import com.badlogic.echoesofthecrypt.model.platform.objects.HazardPlatformObjectModel;

/**
 * Factory pour créer les objets "plateformes" du jeu à partir de TiledDTO.
 */
public class PlatformObjectModelFactory {

    private final PlatformObjectModelManager platformObjectModelManager;

    // 🔹 Constantes des types de plateforme
    public static final String TYPE_SOLID = "solid";
    public static final String TYPE_HAZARD = "hazard";

    public PlatformObjectModelFactory(PlatformObjectModelManager platformObjectModelManager) {
        this.platformObjectModelManager = platformObjectModelManager;
    }

    /**
     * Crée un objet plateforme selon le type défini dans le DTO.
     *
     * @param data les données TiledDTO contenant le type et position
     */
    public void create(TiledDTO data) {
        String type = data.getType();

        try {
            switch(type) {
                case TYPE_SOLID:
                    SolidPlatformObjectModel solidPlatformObjectModel = new SolidPlatformObjectModel(data);
                    platformObjectModelManager.add(solidPlatformObjectModel);
                    break;
                case TYPE_HAZARD:
                    HazardPlatformObjectModel hazardPlatformObjectModel = new HazardPlatformObjectModel(data);
                    platformObjectModelManager.add(hazardPlatformObjectModel);
                    break;
                default:
                    throw new UnknownTypeException(type);
            }
        } catch (UnknownTypeException e){
            System.err.println(e.getMessage());
        }
    }
}
