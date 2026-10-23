package co.edu.uniquindio;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/* Caso 4: buscar un vehículo por ID, tanto si existe como si no. */

class ConsultaVehiculosTest {

    private static final Logger LOG = Logger.getLogger(ConsultaVehiculosTest.class.getName());

    private Batallon batallon;
    private Blindado blindado;

    @BeforeEach
    void prepararBatallon() {
        batallon = FabricaDePrueba.nuevoBatallon();
        blindado = FabricaDePrueba.nuevoBlindado("BL-200");
        batallon.registrarVehiculo(FabricaDePrueba.nuevoTransporte("TT-100"));
        batallon.registrarVehiculo(blindado);
    }

    @DisplayName("Encuentra el vehículo registrado y devuelve null si no existe")
    @Test
    void buscarVehiculoExistenteEInexistente() {
        LOG.info("Buscando BL-200 y un ID que no existe...");

        Vehiculo encontrado = batallon.buscarVehiculo("BL-200");
        assertNotNull(encontrado);
        assertEquals(blindado, encontrado);
        assertEquals("Blindado Cascabel", encontrado.getModelo());

        assertNull(batallon.buscarVehiculo("ZZ-999"));
    }
}