package com.badlogic.echoesofthecrypt.view;

import com.badlogic.echoesofthecrypt.model.entity.EntityModel;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Vue responsable du rendu graphique d'une entité.
 *
 * <p>
 * Cette classe prend un {@link EntityModel} et un {@link SpriteBatch} et
 * dessine l'entité à l'écran avec sa texture, sa position et sa taille.
 * </p>
 *
 * <p>
 * Fait partie du pattern MVC : aucune logique de gameplay ici,
 * uniquement l'affichage.
 * </p>
 */
public class EntityView {

    /**
     * Rend l'entité sur l'écran à l'aide du SpriteBatch fourni.
     *
     * @param entityModel le modèle de l'entité à dessiner
     * @param batch le SpriteBatch utilisé pour le rendu
     */
    public void render(EntityModel entityModel, SpriteBatch batch) {
        batch.draw(
            entityModel.getTextureRegion(),
            entityModel.getPosition().x,
            entityModel.getPosition().y,
            entityModel.getWidth(),
            entityModel.getHeight()
        );
    }
}
