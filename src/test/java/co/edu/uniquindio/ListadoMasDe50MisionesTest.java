package co.edu.uniquindio;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/* Casos 9 y 10: el listado de vehículos con más de 50 misiones completadas. */

class ListadoMasDe50MisionesTest {

    private static final Logger LOG = Logger.getLogger(ListadoMasDe50MisionesTest.class.getName());

    private Batallon batallon;
    private TransporteTropas camion;

    @BeforeEach
    void prepararDatos() {
        batallon = FabricaDePrueba.nuevoBatallon();
        camion = FabricaDePrueba.nuevoTransporte("TT-100");
        batallon.registrarVehiculo(camion);
    }

    @DisplayName("Con exactamente 50 misiones el vehículo NO aparece")
    @Test
    void con50MisionesNoAparece() {
        LOG.info("Completando 50 misiones...");

        FabricaDePrueba.completarMisiones(batallon, camion, 50);

        assertEquals(50, camion.contarMisionesCompletadas());
        ArrayList<Vehiculo> listado = batallon.obtenerVehiculosConMasDe50Misiones();
        assertFalse(listado.contains(camion));
        assertEquals(0, listado.size());
    }

    @DisplayName("Con 51 misiones el vehículo SÍ aparece")
    @Test
    void con51MisionesSiAparece() {
        LOG.info("Completando 51 misiones...");

        FabricaDePrueba.completarMisiones(batallon, camion, 51);

        assertEquals(51, camion.contarMisionesCompletadas());
        ArrayList<Vehiculo> listado = batallon.obtenerVehiculosConMasDe50Misiones();
        assertTrue(listado.contains(camion));
        assertEquals(1, listado.size());
        assertEquals(51, listado.get(0).contarMisionesCompletadas());
    }
}