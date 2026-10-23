package co.edu.uniquindio;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/* Caso 1: que cada tipo de vehículo guarde bien lo que se le pasa al crearlo. */

class CreacionVehiculosTest {

    private static final Logger LOG = Logger.getLogger(CreacionVehiculosTest.class.getName());

    private TransporteTropas camion;
    private Blindado tanque;
    private Apoyo cisterna;

    @BeforeEach
    void crearVehiculos() {
        camion = new TransporteTropas("TT-100", "Camión Kodiak", 2018, 20000,
                EstadoOperativo.DISPONIBLE, 30);
        tanque = new Blindado("BL-200", "Blindado Cascabel", 2019, 15000,
                EstadoOperativo.EN_MANTENIMIENTO, 5);
        cisterna = new Apoyo("AP-300", "Camión cisterna", 2021, 7000,
                EstadoOperativo.DISPONIBLE, TipoFuncion.LOGISTICA);
    }

    @DisplayName("Los tres tipos de vehículo guardan sus datos")
    @Test
    void crearLosTresTiposDeVehiculo() {
        LOG.info("Revisando los tres tipos de vehículo...");

        assertNotNull(camion);
        assertEquals("TT-100", camion.getId());
        assertEquals("Camión Kodiak", camion.getModelo());
        assertEquals(2018, camion.getAnioFabricacion());
        assertEquals(20000, camion.getKilometraje());
        assertEquals(EstadoOperativo.DISPONIBLE, camion.getEstadoOperativo());
        assertEquals(30, camion.getCapacidadSoldados());

        assertNotNull(tanque);
        assertEquals("BL-200", tanque.getId());
        assertEquals("Blindado Cascabel", tanque.getModelo());
        assertEquals(2019, tanque.getAnioFabricacion());
        assertEquals(15000, tanque.getKilometraje());
        assertEquals(EstadoOperativo.EN_MANTENIMIENTO, tanque.getEstadoOperativo());
        assertEquals(5, tanque.getNivelBlindaje());

        assertNotNull(cisterna);
        assertEquals("AP-300", cisterna.getId());
        assertEquals("Camión cisterna", cisterna.getModelo());
        assertEquals(2021, cisterna.getAnioFabricacion());
        assertEquals(7000, cisterna.getKilometraje());
        assertEquals(EstadoOperativo.DISPONIBLE, cisterna.getEstadoOperativo());
        assertEquals(TipoFuncion.LOGISTICA, cisterna.getTipoFuncion());
    }
}
