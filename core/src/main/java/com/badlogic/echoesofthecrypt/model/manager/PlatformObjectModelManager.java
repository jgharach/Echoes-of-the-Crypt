package com.badlogic.echoesofthecrypt.model.manager;

import com.badlogic.echoesofthecrypt.model.platform.objects.PlatformObjectModel;
import com.badlogic.gdx.utils.Array;

/**
 * Gestionnaire des objets de plateforme dans le jeu.
 *
 * <p>
 * Permet de stocker et d'accéder aux objets de type {@link PlatformObjectModel},
 * utilisés pour le rendu et la configuration des plateformes.
 * </p>
 */
public class PlatformObjectModelManager {

    /** Liste des objets de plateforme */
    private final Array<PlatformObjectModel> platformObjectModels = new Array<>();

    /**
     * Ajoute un objet de plateforme à la liste.
     *
     * @param p l'objet de plateforme à ajouter
     */
    public void add(PlatformObjectModel p) {
        platformObjectModels.add(p);
    }

    /**
     * Retourne tous les objets de plateforme.
     *
     * @return liste des objets de plateforme
     */
    public Array<PlatformObjectModel> getPlatformObjectModels() {
        return platformObjectModels;
    }
}
