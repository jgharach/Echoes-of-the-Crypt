package com.badlogic.echoesofthecrypt.controller;

import com.badlogic.echoesofthecrypt.interfaces.Updatable;
import com.badlogic.echoesofthecrypt.model.entity.PlayerModel;
import com.badlogic.echoesofthecrypt.model.manager.EntityModelManager;
import com.badlogic.gdx.utils.Array;

/**
 * Contrôleur du joueur principal.
 *
 * <p>
 * Récupère la liste des joueurs depuis {@link EntityModelManager}, sélectionne
 * le joueur principal et délègue la mise à jour des entrées à {@link InputController}.
 * </p>
 */
public class PlayerController implements Updatable {
    private PlayerModel playerModel;
    private final InputController inputController;
    private Array<PlayerModel> players;
    private final EntityModelManager entityModelManager;

    /**
     * Constructeur du PlayerController.
     *
     * @param entityModelManager manager contenant tous les joueurs
     * @param inputController contrôleur d'entrées clavier
     */
    public PlayerController(EntityModelManager entityModelManager, InputController inputController){
        this.entityModelManager = entityModelManager;
        this.inputController = inputController;
    }

    /**
     * Sélectionne le joueur principal (le premier de la liste).
     *
     * @return le joueur principal, ou null si aucun joueur
     */
    public PlayerModel selectMainPlayer() {
        players = entityModelManager.getPlayers();
        if (!players.isEmpty()) {
            playerModel = players.get(0);
            return playerModel;
        }
        return null;
    }

    /**
     * Met à jour le joueur principal en fonction des entrées clavier.
     *
     * @param delta temps écoulé depuis la dernière frame
     */
    @Override
    public void update(float delta){
        if (playerModel == null) return;

        inputController.update(playerModel);
    }
}

