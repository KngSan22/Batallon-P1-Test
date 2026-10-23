package co.edu.uniquindio;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/* Caso 6: cambiar el kilometraje y el estado operativo de un vehículo ya registrado. */

class ModificacionVehiculosTest {

    private static final Logger LOG = Logger.getLogger(ModificacionVehiculosTest.class.getName());

    private Batallon batallon;

    @BeforeEach
    void prepararDatos() {
        batallon = FabricaDePrueba.nuevoBatallon();
        // arranca con 7000 km y DISPONIBLE
        batallon.registrarVehiculo(FabricaDePrueba.nuevoApoyo("AP-300"));
    }

    @DisplayName("Se actualizan el kilometraje y el estado")
    @Test
    void actualizaKilometrajeYEstado() {
        LOG.info("Actualizando AP-300...");

        Vehiculo registrado = batallon.buscarVehiculo("AP-300");

        assertTrue(registrado.setKilometraje(8500));
        assertTrue(registrado.setEstadoOperativo(EstadoOperativo.EN_MANTENIMIENTO));

        // el cambio queda guardado en el vehículo del batallón
        assertEquals(8500, batallon.buscarVehiculo("AP-300").getKilometraje());
        assertEquals(EstadoOperativo.EN_MANTENIMIENTO,
                batallon.buscarVehiculo("AP-300").getEstadoOperativo());
    }
}