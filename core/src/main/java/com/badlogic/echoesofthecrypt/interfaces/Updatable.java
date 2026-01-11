package com.badlogic.echoesofthecrypt.interfaces;

/**
 * Interface représentant un objet pouvant être mis à jour chaque frame.
 *
 * <p>
 * Toute classe implémentant cette interface doit fournir une implémentation
 * de {@link #update(float)} pour mettre à jour l'état de l'objet.
 * </p>
 */
public interface Updatable {
    /**
     * Met à jour l'état de l'objet.
     *
     * @param delta le temps écoulé depuis la dernière frame (en secondes)
     */
    void update(float delta);
}
