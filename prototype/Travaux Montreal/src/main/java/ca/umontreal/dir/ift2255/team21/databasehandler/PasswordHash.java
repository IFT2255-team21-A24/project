package ca.umontreal.dir.ift2255.team21.databasehandler;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
public class PasswordHash {
    public static String hashPassword(String password) {
        Argon2 argon2 = Argon2Factory.create();
        char[] chars = password.toCharArray();
        try {
            // Argon2 avec paramètres standard : 2 passes, 65536 KB de mémoire, 1 thread
            return argon2.hash(2, 65536, 1, chars);
        } finally {
            argon2.wipeArray(password.toCharArray()); // Sécurité supplémentaire
        }
    }

    // Vérification du mot de passe avec Argon2
    public static boolean checkPassword(String password, String hashedPassword) {
        Argon2 argon2 = Argon2Factory.create();
        return argon2.verify(hashedPassword, password);
    }

}
