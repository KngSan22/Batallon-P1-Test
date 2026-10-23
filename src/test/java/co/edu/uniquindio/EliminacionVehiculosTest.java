package co.edu.uniquindio;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/* Caso 5: eliminar un vehículo que no tiene misiones. */

class EliminacionVehiculosTest {

    private static final Logger LOG = Logger.getLogger(EliminacionVehiculosTest.class.getName());

    private Batallon batallon;

    @BeforeEach
    void prepararBatallon() {
        batallon = FabricaDePrueba.nuevoBatallon();
        batallon.registrarVehiculo(FabricaDePrueba.nuevoTransporte("TT-100"));
        batallon.registrarVehiculo(FabricaDePrueba.nuevoBlindado("BL-200"));
    }

    @DisplayName("Se elimina un vehículo que no tiene misiones")
    @Test
    void eliminaVehiculoSinMisiones() {
        LOG.info("Eliminando TT-100...");

        assertTrue(batallon.eliminarVehiculo("TT-100"));

        assertNull(batallon.buscarVehiculo("TT-100"));
        assertEquals(1, batallon.getListaVehiculos().size());
        assertNotNull(batallon.buscarVehiculo("BL-200"));
    }
}