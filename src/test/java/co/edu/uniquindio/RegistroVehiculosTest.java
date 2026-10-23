package co.edu.uniquindio;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/* Casos 2 y 3: agregar vehículos al batallón y no permitir IDs repetidos. */

class RegistroVehiculosTest {

    private static final Logger LOG = Logger.getLogger(RegistroVehiculosTest.class.getName());

    private Batallon batallon;

    @BeforeEach
    void prepararBatallon() {
        batallon = FabricaDePrueba.nuevoBatallon();
    }

    @DisplayName("Se registra un vehículo con ID nuevo")
    @Test
    void registraVehiculoConIdNuevo() {
        LOG.info("Registrando un vehículo nuevo...");

        TransporteTropas camion = FabricaDePrueba.nuevoTransporte("TT-100");

        assertTrue(batallon.registrarVehiculo(camion));

        assertEquals(1, batallon.getListaVehiculos().size());
        assertTrue(batallon.getListaVehiculos().contains(camion));
    }

    @DisplayName("No se registra un vehículo con un ID que ya existe")
    @Test
    void rechazaIdRepetido() {
        LOG.info("Intentando registrar un ID repetido...");

        TransporteTropas original = FabricaDePrueba.nuevoTransporte("TT-100");
        Blindado conMismoId = FabricaDePrueba.nuevoBlindado("TT-100");
        batallon.registrarVehiculo(original);

        assertFalse(batallon.registrarVehiculo(conMismoId));

        // sigue habiendo uno solo y es el primero que se registró
        assertEquals(1, batallon.getListaVehiculos().size());
        assertEquals(original, batallon.buscarVehiculo("TT-100"));
    }
}