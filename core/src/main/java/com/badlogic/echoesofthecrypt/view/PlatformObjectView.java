package com.badlogic.echoesofthecrypt.view;

import com.badlogic.echoesofthecrypt.model.platform.objects.PlatformObjectModel;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Vue pour dessiner les objets plateformes dans le jeu.
 *
 * <p>
 * Cette classe est responsable du rendu graphique des plateformes définies
 * dans le modèle {@link PlatformObjectModel} à l'aide d'un {@link SpriteBatch}.
 * </p>
 */
public class PlatformObjectView {

    /**
     * Dessine la plateforme à l'écran en utilisant son modèle.
     *
     * @param platformObjectModel le modèle de la plateforme à rendre
     * @param batch le SpriteBatch utilisé pour le rendu
     */
    public void render(PlatformObjectModel platformObjectModel, SpriteBatch batch) {
        batch.draw(
            platformObjectModel.getTextureRegion(),
            platformObjectModel.getX(),
            platformObjectModel.getY(),
            platformObjectModel.getWidth(),
            platformObjectModel.getHeight()
        );
    }
}
