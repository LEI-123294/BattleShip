package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Ship")
class ShipTest {

    @Nested
    @DisplayName("Testes do método buildShip")
    class BuildShipTests {

        @Test
        @DisplayName("Deve criar uma Barca corretamente")
        void testBuildBarge() {
            Position pos = new Position(0, 0);
            Ship ship = Ship.buildShip("barca", Compass.NORTH, pos);

            assertNotNull(ship);
            assertInstanceOf(Barge.class, ship);
            assertEquals("Barca", ship.getCategory());
        }

        @Test
        @DisplayName("Deve criar uma Caravela corretamente")
        void testBuildCaravel() {
            Position pos = new Position(0, 0);
            Ship ship = Ship.buildShip("caravela", Compass.NORTH, pos);

            assertNotNull(ship);
            assertInstanceOf(Caravel.class, ship);
            assertEquals("Caravela", ship.getCategory());
        }

        @Test
        @DisplayName("Deve criar uma Nau corretamente")
        void testBuildCarrack() {
            Position pos = new Position(0, 0);
            Ship ship = Ship.buildShip("nau", Compass.NORTH, pos);

            assertNotNull(ship);
            assertInstanceOf(Carrack.class, ship);
            assertEquals("Nau", ship.getCategory());
        }

        @Test
        @DisplayName("Deve criar uma Fragata corretamente")
        void testBuildFrigate() {
            Position pos = new Position(0, 0);
            Ship ship = Ship.buildShip("fragata", Compass.NORTH, pos);

            assertNotNull(ship);
            assertInstanceOf(Frigate.class, ship);
            assertEquals("Fragata", ship.getCategory());
        }

        @Test
        @DisplayName("Deve criar um Galeão corretamente")
        void testBuildGalleon() {
            Position pos = new Position(0, 0);
            Ship ship = Ship.buildShip("galeao", Compass.NORTH, pos);

            assertNotNull(ship);
            assertInstanceOf(Galleon.class, ship);
            assertEquals("Galeao", ship.getCategory());
        }

        @Test
        @DisplayName("Deve retornar null para tipo de navio desconhecido")
        void testBuildUnknownShip() {
            Position pos = new Position(0, 0);
            Ship ship = Ship.buildShip("submarine", Compass.NORTH, pos);

            assertNull(ship);
        }
    }

    @Nested
    @DisplayName("Testes dos getters básicos")
    class BasicGettersTests {

        private Ship ship;
        private Position position;

        @BeforeEach
        void setUp() {
            position = new Position(2, 3);
            ship = new Barge(Compass.NORTH, position);
        }

        @Test
        @DisplayName("Deve retornar a categoria correta")
        void testGetCategory() {
            assertEquals("Barca", ship.getCategory());
        }

        @Test
        @DisplayName("Deve retornar a posição inicial correta")
        void testGetPosition() {
            assertEquals(position, ship.getPosition());
        }

        @Test
        @DisplayName("Deve retornar o rumo correto")
        void testGetBearing() {
            assertEquals(Compass.NORTH, ship.getBearing());
        }

        @Test
        @DisplayName("Deve retornar a lista de posições ocupadas")
        void testGetPositions() {
            assertNotNull(ship.getPositions());
            assertFalse(ship.getPositions().isEmpty());
        }
    }

    @Nested
    @DisplayName("Testes do método stillFloating")
    class StillFloatingTests {

        @Test
        @DisplayName("Navio recém-criado deve estar a flutuar")
        void testNewShipIsFloating() {
            Ship ship = new Caravel(Compass.NORTH, new Position(0, 0));
            assertTrue(ship.stillFloating());
        }

        @Test
        @DisplayName("Navio com um tiro deve continuar a flutuar")
        void testShipWithOneHitStillFloating() {
            Ship ship = new Caravel(Compass.NORTH, new Position(0, 0));
            ship.shoot(new Position(0, 0));

            assertTrue(ship.stillFloating());
        }

        @Test
        @DisplayName("Navio com todos os tiros deve afundar")
        void testShipWithAllHitsSinks() {
            Ship ship = new Caravel(Compass.NORTH, new Position(0, 0));

            // Atirar em todas as posições
            for (IPosition pos : ship.getPositions()) {
                ship.shoot(pos);
            }

            assertFalse(ship.stillFloating());
        }
    }

    @Nested
    @DisplayName("Testes dos métodos de posicionamento")
    class PositioningTests {

        @Test
        @DisplayName("Deve calcular corretamente a posição mais à esquerda (NORTH)")
        void testGetLeftMostPosNorth() {
            Ship ship = new Caravel(Compass.NORTH, new Position(0, 5));
            assertEquals(5, ship.getLeftMostPos());
        }

        @Test
        @DisplayName("Deve calcular corretamente a posição mais à direita (EAST)")
        void testGetRightMostPosEast() {
            Ship ship = new Caravel(Compass.EAST, new Position(0, 5));
            assertEquals(6, ship.getRightMostPos());
        }

        @Test
        @DisplayName("Deve calcular corretamente a posição mais acima (NORTH)")
        void testGetTopMostPosNorth() {
            Ship ship = new Caravel(Compass.NORTH, new Position(3, 0));
            assertEquals(3, ship.getTopMostPos());
        }

        @Test
        @DisplayName("Deve calcular corretamente a posição mais abaixo (NORTH)")
        void testGetBottomMostPosNorth() {
            Ship ship = new Caravel(Compass.NORTH, new Position(3, 0));
            assertEquals(4, ship.getBottomMostPos());
        }

        @Test
        @DisplayName("Deve calcular limites corretamente para navio horizontal")
        void testHorizontalShipBounds() {
            Ship ship = new Carrack(Compass.EAST, new Position(5, 2));

            assertAll(
                    () -> assertEquals(5, ship.getTopMostPos()),
                    () -> assertEquals(5, ship.getBottomMostPos()),
                    () -> assertEquals(2, ship.getLeftMostPos()),
                    () -> assertEquals(4, ship.getRightMostPos())
            );
        }

        @Test
        @DisplayName("Deve calcular limites corretamente para navio vertical")
        void testVerticalShipBounds() {
            Ship ship = new Carrack(Compass.SOUTH, new Position(2, 5));

            assertAll(
                    () -> assertEquals(2, ship.getTopMostPos()),
                    () -> assertEquals(4, ship.getBottomMostPos()),
                    () -> assertEquals(5, ship.getLeftMostPos()),
                    () -> assertEquals(5, ship.getRightMostPos())
            );
        }
    }

    @Nested
    @DisplayName("Testes do método occupies")
    class OccupiesTests {

        private Ship ship;

        @BeforeEach
        void setUp() {
            ship = new Caravel(Compass.NORTH, new Position(2, 3));
        }

        @Test
        @DisplayName("Deve identificar posição ocupada pelo navio")
        void testOccupiesValidPosition() {
            assertTrue(ship.occupies(new Position(2, 3)));
            assertTrue(ship.occupies(new Position(3, 3)));
        }

        @Test
        @DisplayName("Não deve identificar posição não ocupada")
        void testDoesNotOccupyInvalidPosition() {
            assertFalse(ship.occupies(new Position(0, 0)));
            assertFalse(ship.occupies(new Position(5, 5)));
        }

        @Test
        @DisplayName("Não deve ocupar posições adjacentes")
        void testDoesNotOccupyAdjacentPositions() {
            assertFalse(ship.occupies(new Position(1, 3)));
            assertFalse(ship.occupies(new Position(4, 3)));
            assertFalse(ship.occupies(new Position(2, 2)));
            assertFalse(ship.occupies(new Position(2, 4)));
        }
    }

    @Nested
    @DisplayName("Testes do método tooCloseTo")
    class TooCloseToTests {

        @Test
        @DisplayName("Navios na mesma posição estão demasiado próximos")
        void testShipsAtSamePositionAreTooClose() {
            Ship ship1 = new Barge(Compass.NORTH, new Position(5, 5));
            Ship ship2 = new Barge(Compass.NORTH, new Position(5, 5));

            assertTrue(ship1.tooCloseTo(ship2));
        }

        @Test
        @DisplayName("Navios adjacentes estão demasiado próximos")
        void testAdjacentShipsAreTooClose() {
            Ship ship1 = new Barge(Compass.NORTH, new Position(5, 5));
            Ship ship2 = new Barge(Compass.NORTH, new Position(6, 5));

            assertTrue(ship1.tooCloseTo(ship2));
        }

        @Test
        @DisplayName("Navios afastados não estão demasiado próximos")
        void testDistantShipsAreNotTooClose() {
            Ship ship1 = new Barge(Compass.NORTH, new Position(0, 0));
            Ship ship2 = new Barge(Compass.NORTH, new Position(5, 5));

            assertFalse(ship1.tooCloseTo(ship2));
        }

        @Test
        @DisplayName("Navio demasiado próximo de uma posição adjacente")
        void testShipTooCloseToAdjacentPosition() {
            Ship ship = new Barge(Compass.NORTH, new Position(5, 5));
            Position adjacentPos = new Position(6, 6);

            assertTrue(ship.tooCloseTo(adjacentPos));
        }

        @Test
        @DisplayName("Navio não está próximo de posição distante")
        void testShipNotTooCloseToDistantPosition() {
            Ship ship = new Barge(Compass.NORTH, new Position(5, 5));
            Position distantPos = new Position(8, 8);

            assertFalse(ship.tooCloseTo(distantPos));
        }

        @Test
        @DisplayName("Navios horizontais adjacentes verticalmente estão próximos")
        void testHorizontalShipsAdjacentVertically() {
            Ship ship1 = new Caravel(Compass.EAST, new Position(3, 2));
            Ship ship2 = new Caravel(Compass.EAST, new Position(4, 2));

            assertTrue(ship1.tooCloseTo(ship2));
        }
    }

    @Nested
    @DisplayName("Testes do método shoot")
    class ShootTests {

        @Test
        @DisplayName("Deve marcar posição como atingida")
        void testShootMarksPositionAsHit() {
            Ship ship = new Caravel(Compass.NORTH, new Position(0, 0));
            Position target = new Position(0, 0);

            ship.shoot(target);

            assertTrue(ship.getPositions().get(0).isHit());
        }

        @Test
        @DisplayName("Atirar em posição não ocupada não afeta o navio")
        void testShootMissDoesNotAffectShip() {
            Ship ship = new Caravel(Compass.NORTH, new Position(0, 0));
            Position miss = new Position(5, 5);

            ship.shoot(miss);

            for (IPosition pos : ship.getPositions()) {
                assertFalse(pos.isHit());
            }
            assertTrue(ship.stillFloating());
        }

        @Test
        @DisplayName("Múltiplos tiros em diferentes posições")
        void testMultipleShotsOnDifferentPositions() {
            Ship ship = new Carrack(Compass.NORTH, new Position(0, 0));

            ship.shoot(new Position(0, 0));
            ship.shoot(new Position(1, 0));

            assertTrue(ship.getPositions().get(0).isHit());
            assertTrue(ship.getPositions().get(1).isHit());
            assertFalse(ship.getPositions().get(2).isHit());
            assertTrue(ship.stillFloating());
        }
    }

    @Nested
    @DisplayName("Testes do método toString")
    class ToStringTests {

        @Test
        @DisplayName("Deve retornar string formatada corretamente")
        void testToString() {
            Position pos = new Position(2, 3);
            Ship ship = new Barge(Compass.NORTH, pos);

            String result = ship.toString();

            assertTrue(result.contains("Barca"));
            assertTrue(result.contains("n"));
        }

        @Test
        @DisplayName("ToString deve incluir todas as informações do navio")
        void testToStringContainsAllInfo() {
            Ship ship = new Frigate(Compass.EAST, new Position(5, 5));
            String result = ship.toString();

            assertAll(
                    () -> assertTrue(result.contains("Fragata")),
                    () -> assertTrue(result.contains("e")),
                    () -> assertTrue(result.contains("5"))
            );
        }
    }

    @Nested
    @DisplayName("Testes de integração - Cenários completos")
    class IntegrationTests {

        @Test
        @DisplayName("Cenário completo: criar, posicionar e afundar navio")
        void testCompleteShipLifecycle() {
            // Criar navio
            Ship ship = Ship.buildShip("caravela", Compass.NORTH, new Position(3, 4));

            // Verificar criação
            assertNotNull(ship);
            assertTrue(ship.stillFloating());
            assertEquals(2, ship.getPositions().size());

            // Atirar em todas as posições
            ship.shoot(new Position(3, 4));
            assertTrue(ship.stillFloating()); // Ainda flutua

            ship.shoot(new Position(4, 4));
            assertFalse(ship.stillFloating()); // Agora afundou
        }

        @Test
        @DisplayName("Múltiplos navios não devem colidir quando bem posicionados")
        void testMultipleShipsWellPositioned() {
            Ship ship1 = new Barge(Compass.NORTH, new Position(0, 0));
            Ship ship2 = new Barge(Compass.NORTH, new Position(5, 5));
            Ship ship3 = new Barge(Compass.NORTH, new Position(9, 9));

            assertAll(
                    () -> assertFalse(ship1.tooCloseTo(ship2)),
                    () -> assertFalse(ship2.tooCloseTo(ship3)),
                    () -> assertFalse(ship1.tooCloseTo(ship3))
            );
        }

        @Test
        @DisplayName("Verificar limites de diferentes tipos de navios")
        void testDifferentShipSizesBounds() {
            Ship barge = new Barge(Compass.NORTH, new Position(0, 0));
            Ship caravel = new Caravel(Compass.EAST, new Position(3, 3));
            Ship carrack = new Carrack(Compass.SOUTH, new Position(6, 6));

            assertAll(
                    () -> assertEquals(1, barge.getSize()),
                    () -> assertEquals(2, caravel.getSize()),
                    () -> assertEquals(3, carrack.getSize())
            );
        }
    }

    @Nested
    @DisplayName("Testes parametrizados")
    class ParameterizedTests {

        @ParameterizedTest
        @EnumSource(Compass.class)
        @DisplayName("Deve criar navios em todos os rumos válidos")
        void testCreateShipWithAllBearings(Compass bearing) {
            if (bearing != Compass.UNKNOWN) {
                Ship ship = new Caravel(bearing, new Position(3, 3));
                assertNotNull(ship);
                assertEquals(bearing, ship.getBearing());
            }
        }

        @ParameterizedTest
        @CsvSource({
                "barca, Barca, 1",
                "caravela, Caravela, 2",
                "nau, Nau, 3",
                "fragata, Fragata, 4",
                "galeao, Galeao, 5"
        })
        @DisplayName("Deve criar navios de diferentes tipos com tamanhos corretos")
        void testBuildShipsWithCorrectSizes(String type, String category, int size) {
            Ship ship = Ship.buildShip(type, Compass.NORTH, new Position(0, 0));

            assertAll(
                    () -> assertNotNull(ship),
                    () -> assertEquals(category, ship.getCategory()),
                    () -> assertEquals(size, ship.getSize())
            );
        }
    }
}