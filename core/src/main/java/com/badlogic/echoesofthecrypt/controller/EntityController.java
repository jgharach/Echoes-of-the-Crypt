package com.badlogic.echoesofthecrypt.controller;

import com.badlogic.echoesofthecrypt.interfaces.Renderable;
import com.badlogic.echoesofthecrypt.interfaces.Updatable;
import com.badlogic.echoesofthecrypt.model.TiledMapModel;
import com.badlogic.echoesofthecrypt.model.entity.EntityModel;
import com.badlogic.echoesofthecrypt.model.manager.EntityModelManager;
import com.badlogic.echoesofthecrypt.view.EntityView;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

/**
 * Contrôleur des entités du jeu.
 *
 * <p>
 * Cette classe est responsable de :
 * <ul>
 *     <li>Mettre à jour la logique des entités (déplacement, gravité, collisions avec le sol)</li>
 *     <li>Maintenir les entités à l'intérieur des limites de la carte</li>
 *     <li>Rendre les entités à l'écran via {@link EntityView}</li>
 * </ul>
 * </p>
 *
 * <p>
 * Fait partie du pattern MVC : elle orchestre le modèle ({@link EntityModel}),
 * la vue ({@link EntityView}) et la logique collision ({@link CollisionController}).
 * </p>
 */
public class EntityController implements Updatable, Renderable {

    /** Liste des entités gérées par le contrôleur */
    private final Array<EntityModel> entities;

    /** Vue pour le rendu des entités */
    private final EntityView entityView;

    /** Modèle de la carte pour connaître les limites */
    private final TiledMapModel tiledMapModel;

    /** Contrôleur de collisions */
    private final CollisionController collisionController;

    /**
     * Constructeur du contrôleur.
     *
     * @param entityModelManager manager contenant les entités
     * @param entityView vue pour le rendu des entités
     * @param tiledMapModel modèle de la carte
     * @param collisionController gestion des collisions
     */
    public EntityController(EntityModelManager entityModelManager, EntityView entityView, TiledMapModel tiledMapModel, CollisionController collisionController){
        this.entities = entityModelManager.getEntities();
        this.entityView = entityView;
        this.tiledMapModel = tiledMapModel;
        this.collisionController = collisionController;
    }

    /**
     * Met à jour toutes les entités : mouvement, collision et clamp sur la carte.
     *
     * @param delta temps écoulé depuis la dernière frame
     */
    @Override
    public void update(float delta) {
        for (EntityModel entity : entities) {
            entity.computeMovement(delta);
            entity.update();
            collisionController.checkOnGround(entity);
            clampToMap(entity, tiledMapModel.getWidth(), tiledMapModel.getHeight());
        }
    }

    /**
     * Rendu de toutes les entités.
     *
     * @param batch SpriteBatch utilisé pour dessiner les entités
     */
    @Override
    public void render(SpriteBatch batch){
        if (entityView == null || batch == null) return;

        for (EntityModel entity : entities) {
            if (entity.getTextureRegion() == null) return;

            entityView.render(entity, batch);
        }
    }

    /**
     * Limite la position d'une entité aux bords de la carte.
     *
     * @param entity entité à clamer
     * @param mapWidth largeur de la carte en pixels
     * @param mapHeight hauteur de la carte en pixels
     */
    private void clampToMap(EntityModel entity, float mapWidth, float mapHeight) {
        entity.getHitbox().x = MathUtils.clamp(
            entity.getHitbox().x,
            0,
            mapWidth - entity.getHitbox().width
        );

        if (entity.getHitbox().y < 0) {
            entity.getHitbox().y = 0;
            entity.getVelocity().y = 0;
        }

        entity.getHitbox().y = MathUtils.clamp(
            entity.getHitbox().y,
            0,
            mapHeight - entity.getHitbox().height
        );

        entity.getPosition().set(entity.getHitbox().x, entity.getHitbox().y);
    }
}

