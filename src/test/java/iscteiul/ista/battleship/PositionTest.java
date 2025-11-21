package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Position")
class PositionTest {

    private Position position;

    @BeforeEach
    @DisplayName("Configurar posição para testes")
    void setUp() {
        position = new Position(5, 5);
    }

    @Test
    @DisplayName("Deve criar posição com coordenadas corretas")
    void testPositionCreation() {
        assertEquals(5, position.getRow());
        assertEquals(5, position.getColumn());
    }

    @Test
    @DisplayName("Posição nova não deve estar ocupada")
    void testNewPositionNotOccupied() {
        assertFalse(position.isOccupied());
    }

    @Test
    @DisplayName("Posição nova não deve estar atingida")
    void testNewPositionNotHit() {
        assertFalse(position.isHit());
    }

    @Test
    @DisplayName("Deve marcar posição como ocupada")
    void testOccupy() {
        position.occupy();
        assertTrue(position.isOccupied());
    }

    @Test
    @DisplayName("Deve marcar posição como atingida")
    void testShoot() {
        position.shoot();
        assertTrue(position.isHit());
    }

    @Test
    @DisplayName("Posições com mesmas coordenadas devem ser iguais")
    void testEquals() {
        Position samePosition = new Position(5, 5);
        assertEquals(position, samePosition);
    }

    @Test
    @DisplayName("Posições com coordenadas diferentes não devem ser iguais")
    void testNotEquals() {
        Position differentPosition = new Position(3, 3);
        assertNotEquals(position, differentPosition);
    }

    @Test
    @DisplayName("Posição deve ser igual a si mesma")
    void testEqualsSameObject() {
        assertEquals(position, position);
    }

    @Test
    @DisplayName("Posição não deve ser igual a null")
    void testNotEqualsNull() {
        assertNotEquals(position, null);
    }

    @Test
    @DisplayName("Posição não deve ser igual a objeto de outra classe")
    void testNotEqualsOtherClass() {
        assertNotEquals(position, "String");
    }

    @ParameterizedTest
    @CsvSource({
            "5, 5, true",   // mesma posição
            "4, 4, true",   // diagonal superior esquerda
            "4, 5, true",   // acima
            "4, 6, true",   // diagonal superior direita
            "5, 4, true",   // esquerda
            "5, 6, true",   // direita
            "6, 4, true",   // diagonal inferior esquerda
            "6, 5, true",   // abaixo
            "6, 6, true",   // diagonal inferior direita
            "3, 3, false",  // longe
            "7, 7, false",  // longe
            "5, 8, false"   // longe na horizontal
    })
    @DisplayName("Deve identificar corretamente posições adjacentes")
    void testIsAdjacentTo(int row, int col, boolean expected) {
        Position other = new Position(row, col);
        assertEquals(expected, position.isAdjacentTo(other));
    }

    @Test
    @DisplayName("Posições iguais devem ter mesmo hashCode")
    void testHashCodeEquals() {
        Position samePosition = new Position(5, 5);
        assertEquals(position.hashCode(), samePosition.hashCode());
    }

    @Test
    @DisplayName("Método toString deve conter linha e coluna")
    void testToString() {
        String result = position.toString();
        assertTrue(result.contains("Linha"));
        assertTrue(result.contains("Coluna"));
        assertTrue(result.contains("5"));
    }

    @Test
    @DisplayName("Ocupar e atingir posição ao mesmo tempo")
    void testOccupyAndShoot() {
        position.occupy();
        position.shoot();

        assertTrue(position.isOccupied());
        assertTrue(position.isHit());
    }

    @Test
    @DisplayName("Posições com coordenadas negativas")
    void testNegativeCoordinates() {
        Position negativePos = new Position(-1, -1);
        assertEquals(-1, negativePos.getRow());
        assertEquals(-1, negativePos.getColumn());
    }
}