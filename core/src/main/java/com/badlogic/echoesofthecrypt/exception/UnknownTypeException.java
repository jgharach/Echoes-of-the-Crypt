package com.badlogic.echoesofthecrypt.exception;

/**
 * Exception levée lorsqu'un type d'objet n'est pas reconnu.
 * <p>
 * Exemple : type "solid", "hazard", "player", "enemy" non présent.
 * </p>
 */
public class UnknownTypeException extends RuntimeException {

    /**
     * Crée une exception pour le type non reconnu.
     *
     * @param type le nom du type inconnu
     */
    public UnknownTypeException(String type) {
        super("Unknown type : " + type);
    }
}

