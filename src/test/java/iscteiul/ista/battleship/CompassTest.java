package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Compass")
class CompassTest {

    @Test
    @DisplayName("Deve retornar a direção correta para NORTH")
    void testGetDirectionNorth() {
        assertEquals('n', Compass.NORTH.getDirection());
    }

    @Test
    @DisplayName("Deve retornar a direção correta para SOUTH")
    void testGetDirectionSouth() {
        assertEquals('s', Compass.SOUTH.getDirection());
    }

    @Test
    @DisplayName("Deve retornar a direção correta para EAST")
    void testGetDirectionEast() {
        assertEquals('e', Compass.EAST.getDirection());
    }

    @Test
    @DisplayName("Deve retornar a direção correta para WEST")
    void testGetDirectionWest() {
        assertEquals('o', Compass.WEST.getDirection());
    }

    @Test
    @DisplayName("Deve retornar a direção correta para UNKNOWN")
    void testGetDirectionUnknown() {
        assertEquals('u', Compass.UNKNOWN.getDirection());
    }

    @ParameterizedTest
    @CsvSource({
            "n, NORTH",
            "s, SOUTH",
            "e, EAST",
            "o, WEST"
    })
    @DisplayName("Deve converter char para Compass corretamente")
    void testCharToCompass(char c, Compass expected) {
        assertEquals(expected, Compass.charToCompass(c));
    }

    @Test
    @DisplayName("Deve retornar UNKNOWN para char inválido")
    void testCharToCompassInvalid() {
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('x'));
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('z'));
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('1'));
    }

    @Test
    @DisplayName("Método toString deve retornar string com a direção")
    void testToString() {
        assertEquals("n", Compass.NORTH.toString());
        assertEquals("s", Compass.SOUTH.toString());
        assertEquals("e", Compass.EAST.toString());
        assertEquals("o", Compass.WEST.toString());
        assertEquals("u", Compass.UNKNOWN.toString());
    }

    @Test
    @DisplayName("Deve existir exatamente 5 valores no enum")
    void testEnumValues() {
        Compass[] values = Compass.values();
        assertEquals(5, values.length);
    }

    @Test
    @DisplayName("Conversão char para Compass e volta deve ser consistente")
    void testRoundTripConversion() {
        for (Compass compass : new Compass[]{Compass.NORTH, Compass.SOUTH, Compass.EAST, Compass.WEST, Compass.UNKNOWN}) {
            char direction = compass.getDirection();
            Compass result = Compass.charToCompass(direction);
            assertEquals(compass, result);
        }
    }
}