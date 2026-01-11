package com.badlogic.echoesofthecrypt.model.DTO;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Rectangle;

/**
 * Data Transfer Object pour un objet Tiled (rectangle, tile, etc.).
 *
 * <p>
 * Cette classe extrait toutes les propriétés pertinentes d'un MapObject provenant
 * d'un TiledMap afin que le moteur de jeu puisse les utiliser pour créer les modèles
 * de jeu (EntityModel, PlatformObjectModel, etc.).
 * Certains champs sont conservés même s'ils ne sont pas encore utilisés,
 * pour permettre au level designer d'ajouter des informations dans Tiled
 * et pour faciliter de futures extensions.
 * </p>
 */
public class TiledDTO {

    // Valeurs par défaut si les propriétés Tiled ne sont pas renseignées
    private static final String DEFAULT_NAME = "none";
    private static final String DEFAULT_FAMILY = "none";
    private static final String DEFAULT_TYPE = "none";
    private static final String DEFAULT_STATE = "none";
    private static final String DEFAULT_OWNER = "none";
    private static final boolean DEFAULT_ANIMATED = false;
    private static final int DEFAULT_HP = 100;
    private static final int DEFAULT_DAMAGE = 10;
    private static final float DEFAULT_SPEED = 3f;
    private static final float DEFAULT_JUMP_VELOCITY = 6f;

    /** Position X dans le monde */
    public final float x;

    /** Position Y dans le monde */
    public final float y;

    /** Largeur de l'objet */
    public final float width;

    /** Hauteur de l'objet */
    public final float height;

    /** Nom de l'objet (optionnel, utile pour debug ou identification) */
    public final String name;

    /** Famille de l'objet (ex: "platform", "entity") */
    public final String family;

    /** Type précis de l'objet (ex: "solid", "hazard", "player") */
    public final String type;

    /** État initial de l'objet (ex: "idle", "walk", "jump") */
    public final String state;

    /** Propriétaire de l'objet si pertinent */
    public final String owner;

    /** Indique si l'objet est animé */
    public final boolean animated;

    /** Points de vie de l'objet (si applicable) */
    public final int hp;

    /** Dégâts infligés par l'objet (si applicable) */
    public final int damage;

    /** Vitesse de déplacement */
    public final float speed;

    /** Vitesse de saut */
    public final float jumpVelocity;

    /** Texture associée à l'objet (si c'est un tile) */
    public final TextureRegion texture;

    /**
     * Constructeur à partir d'un MapObject.
     *
     * <p>Récupère la position, les dimensions et les propriétés personnalisées
     * définies dans Tiled.</p>
     *
     * @param object   l'objet Tiled à convertir en DTO
     * @param unitScale échelle de conversion des coordonnées Tiled en pixels/monde
     */
    public TiledDTO(MapObject object, float unitScale) {
        TextureRegion tempTexture = null;

        // Extraction des dimensions et positions selon le type d'objet
        if (object instanceof RectangleMapObject) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            this.x = rect.x * unitScale;
            this.y = rect.y * unitScale;
            this.width = rect.width * unitScale;
            this.height = rect.height * unitScale;
        } else if (object instanceof TiledMapTileMapObject) {
            TiledMapTileMapObject tileObject = (TiledMapTileMapObject) object;
            tempTexture = tileObject.getTile().getTextureRegion();
            this.width = tempTexture.getRegionWidth() * unitScale;
            this.height = tempTexture.getRegionHeight() * unitScale;
            this.x = tileObject.getX() * unitScale;
            this.y = tileObject.getY() * unitScale;
        } else {
            this.x = this.y = this.width = this.height = 0;
        }
        this.texture = tempTexture;

        // Récupération des propriétés personnalisées
        MapProperties props = object.getProperties();
        this.name = object.getName() != null ? object.getName() : DEFAULT_NAME;
        this.family = props.get("family", DEFAULT_FAMILY, String.class);
        this.type = props.get("type", DEFAULT_TYPE, String.class);
        this.state = props.get("state", DEFAULT_STATE, String.class);
        this.owner = props.get("owner", DEFAULT_OWNER, String.class);
        this.animated = props.get("animated", DEFAULT_ANIMATED, Boolean.class);
        this.hp = props.get("hp", DEFAULT_HP, Integer.class);
        this.damage = props.get("damage", DEFAULT_DAMAGE, Integer.class);
        this.speed = props.get("speed", DEFAULT_SPEED, Float.class);
        this.jumpVelocity = props.get("jumpVelocity", DEFAULT_JUMP_VELOCITY, Float.class);
    }

    // Getters
    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public String getFamily() { return family; }
    public String getType() { return type; }
    public float getSpeed() { return speed; }
    public TextureRegion getTexture() { return texture; }
    public float getJumpVelocity() { return jumpVelocity; }

}


