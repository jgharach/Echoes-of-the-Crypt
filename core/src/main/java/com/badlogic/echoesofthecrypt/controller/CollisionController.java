package com.badlogic.echoesofthecrypt.controller;

import com.badlogic.echoesofthecrypt.model.entity.EntityModel;
import com.badlogic.echoesofthecrypt.model.entity.EntityState;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Contrôleur de collision simplifié.
 *
 * <p>
 * Gère les collisions élémentaires des entités avec le sol (axe Y).
 * Actuellement, il ne gère que la détection de l'entité "au sol".
 * </p>
 */
public class CollisionController {

    /**
     * Vérifie si l'entité est sur le sol.
     * <p>
     * Si la position Y de l'entité est inférieure ou égale à 0,
     * sa position est corrigée à 0, sa vitesse verticale est remise à 0,
     * et son état est mis à {@link EntityState#IDLE}. Sinon, on marque l'entité comme
     * non "au sol".
     *
     * @param entityModel l'entité à tester
     */
    public void checkOnGround(EntityModel entityModel) {
        Rectangle hitbox = entityModel.getHitbox();
        Vector2 velocity = entityModel.getVelocity();

        if (hitbox.y <= 0) {
            hitbox.y = 0;
            velocity.y = 0;
            entityModel.setOnGround(true);
            entityModel.setState(EntityState.IDLE);
        } else {
            entityModel.setOnGround(false);
        }
    }
}

