package co.edu.uniquindio;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/* Caso 7: registrar una misión con su personal y sus vehículos. */

class RegistroMisionesTest {

    private static final Logger LOG = Logger.getLogger(RegistroMisionesTest.class.getName());

    private Batallon batallon;
    private TransporteTropas camion;
    private Blindado blindado;

    @BeforeEach
    void prepararDatos() {
        batallon = FabricaDePrueba.nuevoBatallon();
        camion = FabricaDePrueba.nuevoTransporte("TT-100");
        blindado = FabricaDePrueba.nuevoBlindado("BL-200");
        batallon.registrarVehiculo(camion);
        batallon.registrarVehiculo(blindado);
    }

    @DisplayName("La misión queda guardada con fecha, lugar, personal y vehículos")
    @Test
    void guardaMisionCompleta() {
        LOG.info("Registrando la misión OP-10...");

        LocalDate fecha = LocalDate.of(2026, 12, 1);
        Mision mision = new Mision("OP-10", fecha, "Montenegro");
        mision.agregarPersonal("Capitán Torres");
        mision.agregarPersonal("Cabo Herrera");

        assertTrue(batallon.registrarMision(mision));
        assertTrue(batallon.asignarVehiculoMision("OP-10", "TT-100"));
        assertTrue(batallon.asignarVehiculoMision("OP-10", "BL-200"));

        Mision guardada = batallon.buscarMision("OP-10");
        assertNotNull(guardada);
        assertEquals(fecha, guardada.getFecha());
        assertEquals("Montenegro", guardada.getUbicacion());
        assertEquals(EstadoMision.PLANIFICADA, guardada.getEstado());

        ArrayList<String> personal = guardada.getPersonalAsignado();
        assertEquals(2, personal.size());
        assertTrue(personal.contains("Capitán Torres"));
        assertTrue(personal.contains("Cabo Herrera"));

        ArrayList<DetalleMision> detalles = guardada.getListaDetallesMision();
        assertEquals(2, detalles.size());
        assertEquals(camion, detalles.get(0).getVehiculo());
        assertEquals(blindado, detalles.get(1).getVehiculo());

        // el vehículo también queda enlazado a la misión
        assertEquals(1, camion.getListaDetallesMision().size());
    }
}