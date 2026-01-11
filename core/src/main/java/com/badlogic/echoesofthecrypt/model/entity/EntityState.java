package com.badlogic.echoesofthecrypt.model.entity;

/**
 * Enum représentant l'état d'une entité.
 *
 * <p>
 * Les états possibles sont :
 * <ul>
 *     <li>IDLE : l'entité est immobile</li>
 *     <li>WALK : l'entité se déplace horizontalement</li>
 *     <li>JUMP : l'entité est en train de sauter</li>
 * </ul>
 * </p>
 */
public enum EntityState {
    IDLE("idle"),
    WALK("walk"),
    JUMP("jump");

    /** Nom de l'état correspondant à la configuration Tiled */
    private final String stateName;

    EntityState(String stateName) {
        this.stateName = stateName;
    }

    /**
     * Convertit une chaîne provenant de Tiled en EntityState.
     *
     * @param state nom de l'état depuis Tiled
     * @return l'état correspondant, ou IDLE si inconnu
     */
    public static EntityState fromTiled(String state) {
        for (EntityState entityState : values()) {
            if (entityState.stateName.equalsIgnoreCase(state)) {
                return entityState;
            }
        }
        return IDLE;
    }
}

