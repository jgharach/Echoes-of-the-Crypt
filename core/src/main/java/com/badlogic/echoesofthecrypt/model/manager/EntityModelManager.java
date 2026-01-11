package com.badlogic.echoesofthecrypt.model.manager;

import com.badlogic.echoesofthecrypt.model.entity.EntityModel;
import com.badlogic.echoesofthecrypt.model.entity.PlayerModel;
import com.badlogic.gdx.utils.Array;

/**
 * Gestionnaire des entités du jeu.
 *
 * <p>
 * Permet de stocker et d'accéder aux entités créées (joueurs, ennemis, etc.).
 * Fournit également une méthode utilitaire pour récupérer uniquement les
 * joueurs.
 * </p>
 */
public class EntityModelManager {

    /** Liste des entités dans le jeu */
    private final Array<EntityModel> entities = new Array<>();

    /**
     * Ajoute une entité à la liste.
     *
     * @param e l'entité à ajouter
     */
    public void add(EntityModel e) {
        entities.add(e);
    }

    /**
     * Retourne toutes les entités.
     *
     * @return liste de toutes les entités
     */
    public Array<EntityModel> getEntities() {
        return entities;
    }

    /**
     * Retourne uniquement les entités de type {@link PlayerModel}.
     *
     * @return liste des joueurs
     */
    public Array<PlayerModel> getPlayers() {
        Array<PlayerModel> players = new Array<>();
        for (EntityModel e : entities) {
            if (e instanceof PlayerModel) {
                players.add((PlayerModel) e);
            }
        }
        return players;
    }
}

