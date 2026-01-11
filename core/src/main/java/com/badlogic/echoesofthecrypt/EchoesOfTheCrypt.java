package com.badlogic.echoesofthecrypt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.echoesofthecrypt.screen.GameScreen;

/**
 * Classe principale du jeu.
 * Permet de lancer le jeu et de spécifier la map à charger via un argument.
 */
public class EchoesOfTheCrypt extends Game {

    /** Chemin de la map Tiled à charger */
    private final String mapPath;
    private final String MAP_PATH = "maps/level1.tmx";

    /** Constructeur par défaut, charge la map par défaut */
    public EchoesOfTheCrypt() {
        this.mapPath = MAP_PATH;
    }

    /** Constructeur avec argument pour charger une map spécifique */
    public EchoesOfTheCrypt(String mapPath) {
        if (mapPath == null || mapPath.isEmpty()) {
            this.mapPath = MAP_PATH; // fallback
        } else {
            this.mapPath = mapPath;
        }
    }

    @Override
    public void create() {
        Gdx.app.log("Jeu", "Chargement de la map : " + mapPath);
        setScreen(new GameScreen(mapPath));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        if (getScreen() != null) getScreen().dispose();
        super.dispose();
    }
}

