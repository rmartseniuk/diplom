package ua.nure.martseniuk.diplom.security;

import ua.nure.martseniuk.diplom.domain.User;

/**
 * @author Roman_Martseniuk
 */
public final class JwtUserFactory {

    private JwtUserFactory() {
        //NON
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUsername(),
                user.getPassword()
        );
    }


}
