package co.edu.uniquindio;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/* Caso 8: el conteo de misiones de un vehículo solo debe tomar las COMPLETADAS. */
class ConteoMisionesTest {

    private static final Logger LOG = Logger.getLogger(ConteoMisionesTest.class.getName());

    private Batallon batallon;
    private Blindado blindado;

    @BeforeEach
    void prepararDatos() {
        batallon = FabricaDePrueba.nuevoBatallon();
        blindado = FabricaDePrueba.nuevoBlindado("BL-200");
        batallon.registrarVehiculo(blindado);
    }

    @DisplayName("Solo se cuentan las completadas, no las pendientes ni las en curso")
    @Test
    void soloCuentaLasCompletadas() {
        LOG.info("Armando misiones en distintos estados...");

        // tres completadas
        FabricaDePrueba.completarMisiones(batallon, blindado, 3);

        // una pendiente: registrada y asignada, pero sin iniciar
        batallon.registrarMision(new Mision("PEND-1", LocalDate.of(2026, 11, 20), "Calarcá"));
        batallon.buscarMision("PEND-1").agregarPersonal("Soldado Díaz");
        batallon.asignarVehiculoMision("PEND-1", "BL-200");

        // una en curso: se inicia al final porque deja al vehículo EN_MISION
        batallon.registrarMision(FabricaDePrueba.nuevaMision("CURSO-1"));
        batallon.asignarVehiculoMision("CURSO-1", "BL-200");
        assertTrue(batallon.iniciarMision("CURSO-1"));

        assertEquals(EstadoMision.PLANIFICADA, batallon.buscarMision("PEND-1").getEstado());
        assertEquals(EstadoMision.EN_CURSO, batallon.buscarMision("CURSO-1").getEstado());
        assertEquals(5, blindado.getListaDetallesMision().size());
        assertEquals(3, blindado.contarMisionesCompletadas());
    }
}