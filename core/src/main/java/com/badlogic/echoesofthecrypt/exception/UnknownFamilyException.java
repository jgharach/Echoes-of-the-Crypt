package com.badlogic.echoesofthecrypt.exception;

/**
 * Exception levée lorsqu'une "famille" d'objet n'est pas reconnue.
 * <p>
 * Exemple : si le moteur reçoit un type inconnu ou mal configuré dans Tiled.
 * </p>
 */
public class UnknownFamilyException extends RuntimeException {

    /**
     * Crée une exception pour la famille non reconnue.
     *
     * @param family le nom de la famille inconnu
     */
    public UnknownFamilyException(String family) {
        super("Unknown family : " + family);
    }
}
