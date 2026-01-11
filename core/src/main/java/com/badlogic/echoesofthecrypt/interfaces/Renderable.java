package com.badlogic.echoesofthecrypt.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Interface représentant un objet pouvant être rendu à l'écran.
 *
 * <p>
 * Toute classe implémentant cette interface doit fournir une implémentation
 * de {@link #render(SpriteBatch)} pour dessiner l'objet à l'écran.
 * </p>
 */
public interface Renderable {
    /**
     * Rend l'objet à l'écran à l'aide du SpriteBatch fourni.
     *
     * @param batch le SpriteBatch utilisé pour le rendu
     */
    void render(SpriteBatch batch);
}

