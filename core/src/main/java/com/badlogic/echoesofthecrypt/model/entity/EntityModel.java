package com.badlogic.echoesofthecrypt.model.entity;

import com.badlogic.echoesofthecrypt.model.DTO.TiledDTO;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Modèle représentant une entité mobile dans le jeu.
 *
 * <p>
 * Cette classe contient les informations de position, taille, texture, vitesse,
 * état et hitbox de l'entité. Elle gère également la logique de mouvement
 * de base (gauche, droite, saut, arrêt) et l'application de la gravité.
 * </p>
 *
 * <p>
 * Elle fait partie du pattern MVC comme modèle et ne contient aucune logique
 * de rendu graphique.
 * </p>
 */
public abstract class EntityModel {

    /** Position de l'entité dans le monde */
    protected Vector2 position;

    /** Largeur de l'entité */
    private final float width;

    /** Hauteur de l'entité */
    private final float height;

    /** Rectangle de collision */
    protected Rectangle hitbox;

    /** Texture utilisée pour le rendu */
    private final TextureRegion textureRegion;

    /** Vitesse de déplacement horizontal */
    private final float speed;

    /** Vitesse actuelle de l'entité */
    private final Vector2 velocity;

    /** Déplacement calculé par frame */
    private float dx;
    private float dy;

    /** État actuel de l'entité */
    private EntityState state;

    /** Indique si l'entité est au sol */
    private boolean onGround;

    /** Vitesse initiale de saut */
    private final float jumpVelocity;

    /**
     * Crée une nouvelle entité à partir des données fournies par TiledDTO.
     *
     * @param data les données issues du fichier Tiled (position, taille, texture, vitesse, jump)
     */
    public EntityModel(TiledDTO data) {
        float x = data.getX();
        float y = data.getY();
        this.position = new Vector2(x, y);
        this.width = data.getWidth();
        this.height = data.getHeight();
        this.hitbox = new Rectangle(position.x, position.y, width, height);
        this.textureRegion = data.getTexture();
        this.speed = data.getSpeed();
        this.velocity = new Vector2();
        this.jumpVelocity = data.getJumpVelocity();
    }

    /**
     * Déplace l'entité vers la gauche.
     */
    public void moveLeft() {
        velocity.x = -speed;
        state = EntityState.WALK;
    }

    /**
     * Déplace l'entité vers la droite.
     */
    public void moveRight() {
        velocity.x = speed;
        state = EntityState.WALK;
    }

    /**
     * Fait sauter l'entité si elle est au sol.
     */
    public void jump() {
        if (onGround) {
            velocity.y = jumpVelocity;
            onGround = false;
            state = EntityState.JUMP;
        }
    }

    /**
     * Arrête le déplacement horizontal de l'entité.
     */
    public void stop() {
        velocity.x = 0;
        state = EntityState.IDLE;
    }

    /**
     * Calcule le déplacement de l'entité pour la frame en cours
     * en appliquant la gravité.
     *
     * @param delta temps écoulé depuis la dernière frame (en secondes)
     */
    public void computeMovement(float delta){
        float gravity = 6.125f;
        velocity.y -= gravity * delta;

        this.dx = velocity.x * delta;
        this.dy = velocity.y * delta;
    }

    /**
     * Met à jour la position et la hitbox de l'entité après calcul du mouvement.
     */
    public void update() {
        hitbox.x += dx;
        hitbox.y += dy;
        position.set(hitbox.x, hitbox.y);
    }

    // --- Getters et setters ---

    public Vector2 getPosition() { return position; }

    public float getWidth() { return width; }

    public float getHeight() { return height; }

    public Rectangle getHitbox() { return hitbox; }

    public TextureRegion getTextureRegion() { return textureRegion; }

    public Vector2 getVelocity() { return velocity; }

    public void setOnGround(boolean onGround) { this.onGround = onGround; }

    public void setState(EntityState state) { this.state = state; }
}


