package org.example.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class UserTest {

    private User user;

    @Before
    public void setUp() {
        // ARRANGE
        user = new User("Nome", "email@example.com", "senhaAntiga", new Date());
    }

    @Test
    public void testTrocarSenhaSenhaValida() {
        //ARRANGE
        String senhaCorreta = "senhaAntiga";

        //ACT
        String resultado = user.trocarSenha(senhaCorreta, "novaSenha");

        //ASSERT
        assertEquals("Senha alterada para: novaSenha", resultado);
        assertEquals("novaSenha", user.getPassword());
    }

    @Test
    public void testTrocarSenhaSenhaInvalida() {
        //ARRANGE
        String senhaIncorreta = "senhaIncorreta";

        //ACT
        String resultado = user.trocarSenha(senhaIncorreta, "novaSenha");

        //ASSERT
        assertEquals("Senha antiga inválida", resultado);
        assertEquals("senhaAntiga", user.getPassword()); // A senha deve permanecer a mesma
    }

    @Test
    public void testTrocarEmail() {
        //ARRANGE
        String novoEmail = "novoemail@example.com";

        //ACT
        String resultado = user.trocarEmail(novoEmail);

        //ASSERT
        assertEquals("Email alterado para: " + novoEmail, resultado);
        assertEquals(novoEmail, user.getEmail());
    }
}