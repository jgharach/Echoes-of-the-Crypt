package com.badlogic.echoesofthecrypt.controller;

import com.badlogic.echoesofthecrypt.interfaces.Renderable;
import com.badlogic.echoesofthecrypt.model.manager.PlatformObjectModelManager;
import com.badlogic.echoesofthecrypt.model.platform.objects.PlatformObjectModel;
import com.badlogic.echoesofthecrypt.view.PlatformObjectView;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Contrôleur responsable du rendu des objets plateformes dans le jeu.
 *
 * <p>
 * Cette classe orchestre le rendu des {@link PlatformObjectModel} via
 * la {@link PlatformObjectView}. Elle récupère la liste des plateformes
 * depuis le {@link PlatformObjectModelManager} et les dessine à l'écran.
 * </p>
 */
public class PlatformObjectController implements Renderable {

    /** Liste des modèles de plateformes à rendre */
    private final Array<PlatformObjectModel> platformObjectModels;

    /** Vue utilisée pour le rendu des plateformes */
    private final PlatformObjectView platformObjectView;

    /**
     * Crée un contrôleur de plateformes.
     *
     * @param platformObjectModelManager le manager contenant les modèles de plateformes
     * @param platformObjectView la vue utilisée pour le rendu
     */
    public PlatformObjectController(PlatformObjectModelManager platformObjectModelManager,
                                    PlatformObjectView platformObjectView) {
        this.platformObjectModels = platformObjectModelManager.getPlatformObjectModels();
        this.platformObjectView = platformObjectView;
    }

    /**
     * Rend toutes les plateformes à l'écran.
     *
     * @param batch le SpriteBatch utilisé pour le rendu
     */
    @Override
    public void render(SpriteBatch batch) {
        if (platformObjectView == null || batch == null) return;

        for (PlatformObjectModel platformObjectModel : platformObjectModels) {
            if (platformObjectModel.getTextureRegion() == null) return;

            platformObjectView.render(platformObjectModel, batch);
        }
    }
}

