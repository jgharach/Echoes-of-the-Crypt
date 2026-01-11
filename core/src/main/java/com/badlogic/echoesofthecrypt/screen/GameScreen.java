package com.badlogic.echoesofthecrypt.screen;

import com.badlogic.echoesofthecrypt.controller.*;
import com.badlogic.echoesofthecrypt.interfaces.Renderable;
import com.badlogic.echoesofthecrypt.interfaces.Updatable;
import com.badlogic.echoesofthecrypt.model.TiledMapModel;
import com.badlogic.echoesofthecrypt.model.entity.PlayerModel;
import com.badlogic.echoesofthecrypt.model.factory.ModelMasterFactory;
import com.badlogic.echoesofthecrypt.model.manager.EntityModelManager;
import com.badlogic.echoesofthecrypt.model.manager.PlatformObjectModelManager;
import com.badlogic.echoesofthecrypt.view.EntityView;
import com.badlogic.echoesofthecrypt.view.PlatformObjectView;
import com.badlogic.echoesofthecrypt.view.TiledMapView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Écran principal du jeu.
 *
 * <p>
 * {@code GameScreen} représente l'écran de jeu actif pendant une partie.
 * Il est responsable de :
 * </p>
 *
 * <ul>
 *     <li>l'initialisation de la caméra et du viewport</li>
 *     <li>la création et l'orchestration des managers, vues et contrôleurs</li>
 *     <li>le chargement et l'affichage de la carte Tiled</li>
 *     <li>la mise à jour des entités (logique de jeu)</li>
 *     <li>le rendu graphique des éléments du jeu</li>
 * </ul>
 *
 * <p>
 * Cette classe agit comme un point central de coordination (composition root)
 * en reliant les différentes couches de l'architecture MVC :
 * </p>
 *
 * <ul>
 *     <li><b>Modèles</b> : {@link TiledMapModel}, {@link PlayerModel}</li>
 *     <li><b>Vues</b> : {@link TiledMapView}, {@link EntityView}, {@link PlatformObjectView}</li>
 *     <li><b>Contrôleurs</b> : {@link CameraController}, {@link EntityController},
 *     {@link PlayerController}, {@link PlatformObjectController}</li>
 * </ul>
 *
 * <p>
 * Le chemin de la carte Tiled est fourni dynamiquement via le constructeur,
 * ce qui permet de charger différentes cartes sans modifier le code source
 * (ex. via un argument passé au JAR).
 * </p>
 */
public class GameScreen implements Screen {

    // --- Constantes ---

    /** Largeur du monde en unités de tuiles */
    private static final float WORLD_WIDTH = 16f;

    /** Hauteur du monde en unités de tuiles */
    private static final float WORLD_HEIGHT = 9f;


    // --- Objets cœur du jeu ---

    /** Modèle représentant la carte Tiled */
    private TiledMapModel tiledMapModel;

    /** Modèle du joueur principal */
    private PlayerModel playerModel;

    /** Caméra orthographique utilisée pour l'affichage */
    private OrthographicCamera camera;

    /** Viewport assurant la gestion du redimensionnement */
    private Viewport viewport;

    /** SpriteBatch utilisé pour le rendu des entités */
    private SpriteBatch spriteBatch;


    // --- Vues ---

    /** Vue chargée de l'affichage de la carte Tiled */
    private TiledMapView tiledMapView;

    /** Vue chargée de l'affichage des entités */
    private EntityView entityView;

    /** Vue chargée de l'affichage des plateformes */
    private PlatformObjectView platformObjectView;


    // --- Contrôleurs ---

    /** Contrôleur de la caméra (suivi du joueur, limites de la map) */
    private CameraController cameraController;

    /** Contrôleur de la carte Tiled */
    private TiledMapController tiledMapController;

    /** Contrôleur des entités */
    private EntityController entityController;

    /** Contrôleur des objets de plateforme */
    private PlatformObjectController platformObjectController;

    /** Contrôleur spécifique au joueur */
    private PlayerController playerController;


    // --- Boucle de jeu générique ---

    /** Liste des objets à mettre à jour à chaque frame */
    private final Array<Updatable> updatables = new Array<>();

    /** Liste des objets à rendre à l'écran */
    private final Array<Renderable> renderables = new Array<>();


    // --- Managers ---

    private EntityModelManager entityModelManager;
    private PlatformObjectModelManager platformObjectModelManager;

    /**
     * Construit l'écran de jeu principal.
     *
     * <p>
     * L'ensemble des composants du jeu est initialisé à l'aide de méthodes privées
     * spécialisées afin d'améliorer la lisibilité, la maintenabilité et la testabilité
     * du code.
     * </p>
     *
     * @param tiledMapPath chemin vers le fichier de carte Tiled à charger
     */
    public GameScreen(String tiledMapPath) {
        initManagers();
        initCameraAndViewport();
        initSpriteBatch();
        initModels(tiledMapPath);
        initViews();
        initControllers();
        initGameLoopLists();
    }

    // -------------------
    // Méthodes d'initialisation
    // -------------------

    /** Initialise les managers pour entités et plateformes */
    private void initManagers() {
        platformObjectModelManager = new PlatformObjectModelManager();
        entityModelManager = new EntityModelManager();
    }

    /** Initialise la caméra et le viewport */
    private void initCameraAndViewport() {
        camera = new OrthographicCamera();
        viewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        cameraController = new CameraController(camera);
    }


    /** Initialise le SpriteBatch pour le rendu */
    private void initSpriteBatch() {
        spriteBatch = new SpriteBatch();
    }

    /** Initialise le modèle de map et la factory */
    private void initModels(String tiledMapPath) {
        ModelMasterFactory modelFactory = new ModelMasterFactory(platformObjectModelManager, entityModelManager);
        tiledMapModel = new TiledMapModel(tiledMapPath, modelFactory);
    }


    /** Initialise les vues */
    private void initViews() {
        tiledMapView = new TiledMapView();
        entityView = new EntityView();
        platformObjectView = new PlatformObjectView();

    }

    /** Initialise les contrôleurs */
    private void initControllers() {
        CollisionController collisionController = new CollisionController();
        InputController inputController = new InputController();

        tiledMapController = new TiledMapController(tiledMapModel, tiledMapView);
        platformObjectController = new PlatformObjectController(platformObjectModelManager, platformObjectView);
        entityController = new EntityController(entityModelManager, entityView, tiledMapModel, collisionController);
        playerController = new PlayerController(entityModelManager, inputController);

    }

    /** Initialise les listes pour une boucle de jeu générique */
    private void initGameLoopLists() {
        updatables.add(entityController, playerController);
        renderables.add(entityController, platformObjectController);
    }


    // -------------------
    // Cycle de vie
    // -------------------

    /**
     * Appelé lorsque l'écran devient actif.
     *
     * <p>
     * Initialise la carte, instancie les entités et sélectionne
     * le joueur principal.
     * </p>
     */
    @Override
    public void show() {
        viewport.apply(true);

        // Initialise la map et les models
        tiledMapController.initialize();

        // Sélection du joueur principal
        playerController.setPlayers();
        playerModel = playerController.selectMainPlayer();

    }

    /**
     * Boucle principale de rendu et de mise à jour.
     *
     * @param delta temps écoulé depuis la dernière frame (en secondes)
     */
    @Override
    public void render(float delta) {
        // Nettoie l'écran
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Met à jour tous objets
        for (Updatable u : updatables) u.update(delta);

        // La caméra suit le joueur et limite ce qui peut être vu aux limites de la map
        cameraController.followPlayer(playerModel);
        cameraController.clampToMapBounds(tiledMapModel);
        cameraController.update();

        // Affichage de la map
        tiledMapController.render(camera);

        // Affichage des entités et des platformes
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        for (Renderable r : renderables) r.render(spriteBatch);
        spriteBatch.end();
    }

    /**
     * Appelé lors du redimensionnement de la fenêtre.
     *
     * @param width nouvelle largeur
     * @param height nouvelle hauteur
     */
    @Override
    public void resize(int width, int height) {
        if (width <= 0 || height <= 0) return;
        viewport.update(width, height, true);
    }

    @Override
    public void pause() { /* Rien de spécifique */ }

    @Override
    public void resume() { /* Rien de spécifique */ }

    @Override
    public void hide() { /* Rien à faire */ }

    /**
     * Libère les ressources utilisées par l'écran.
     */
    @Override
    public void dispose() {
        tiledMapController.dispose();
        spriteBatch.dispose();
    }
}

