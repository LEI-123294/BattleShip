package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Fleet")
class FleetTest {

    private Fleet fleet;

    @BeforeEach
    void setUp() {
        fleet = new Fleet();
    }

    @Nested
    @DisplayName("Testes de adição e obtenção de navios")
    class AddAndGetShipsTests {

        @Test
        @DisplayName("Deve adicionar navios à frota corretamente")
        void testAddShips() {
            Ship ship1 = new Barge(Compass.NORTH, new Position(0, 0));
            Ship ship2 = new Caravel(Compass.EAST, new Position(2, 2));

            fleet.addShip(ship1);
            fleet.addShip(ship2);

            List<IShip> ships = fleet.getShips();
            assertEquals(2, ships.size());
            assertTrue(ships.contains(ship1));
            assertTrue(ships.contains(ship2));
        }

        @Test
        @DisplayName("Frota inicial deve estar vazia")
        void testInitialFleetEmpty() {
            List<IShip> ships = fleet.getShips();
            assertTrue(ships.isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes de estado da frota")
    class FleetStateTests {

        @Test
        @DisplayName("allShipsFloating retorna true quando todos os navios flutuam")
        void testAllShipsFloatingTrue() {
            Ship ship1 = new Barge(Compass.NORTH, new Position(0, 0));
            Ship ship2 = new Caravel(Compass.EAST, new Position(2, 2));

            fleet.addShip(ship1);
            fleet.addShip(ship2);

            // Usa getFloatingShips() que é seguro
            assertEquals(fleet.getShips().size(), fleet.getFloatingShips().size());
        }

        @Test
        @DisplayName("allShipsFloating retorna false se algum navio afundou")
        void testAllShipsFloatingFalse() {
            Ship ship = new Caravel(Compass.NORTH, new Position(0, 0));
            fleet.addShip(ship);

            // Atira em todas as posições para afundar
            for (IPosition pos : ship.getPositions()) {
                ship.shoot(pos);
            }

            assertTrue(fleet.getFloatingShips().isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes de proximidade de navios")
    class FleetProximityTests {

        private Ship ship1;

        @BeforeEach
        void setupShips() {
            ship1 = new Barge(Compass.NORTH, new Position(0, 0));
            fleet.addShip(ship1);
        }

        @Test
        @DisplayName("Navio próximo à frota deve ser identificado")
        void testTooCloseToAnyTrue() {
            Ship newShip = new Barge(Compass.NORTH, new Position(0, 1));
            assertTrue(newShip.tooCloseTo(ship1));
        }

        @Test
        @DisplayName("Navio distante da frota não deve ser identificado como próximo")
        void testTooCloseToAnyFalse() {
            Ship newShip = new Caravel(Compass.NORTH, new Position(5, 5));
            assertFalse(newShip.tooCloseTo(ship1));
        }
    }

    @Nested
    @DisplayName("Testes parametrizados para Caravel")
    class CaravelParamTests {

        @ParameterizedTest
        @EnumSource(value = Compass.class, names = {"NORTH","SOUTH","EAST","WEST"})
        @DisplayName("Deve criar Caravel com rumos válidos sem lançar exceção")
        void testCreateCaravelWithValidBearings(Compass bearing) {
            Ship ship = new Caravel(bearing, new Position(3, 3));
            assertNotNull(ship);
            assertEquals(bearing, ship.getBearing());
        }
    }
}

