package co.edu.uniquindio;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

/* Clase de apoyo para las pruebas: aquí dejo armados los objetos que se repiten en casi todos los tests, así cada
 prueba queda corta y fácil de leer.
 ¡No tiene pruebas propias! */

final class FabricaDePrueba {

    private FabricaDePrueba() {
    }

    static Batallon nuevoBatallon() {
        return new Batallon("BT-07", "Boyacá", "05", "Armenia", 7);
    }

    static TransporteTropas nuevoTransporte(String id) {
        return new TransporteTropas(id, "Camión Kodiak", 2018, 20000,
                EstadoOperativo.DISPONIBLE, 30);
    }

    static Blindado nuevoBlindado(String id) {
        return new Blindado(id, "Blindado Cascabel", 2019, 15000,
                EstadoOperativo.DISPONIBLE, 5);
    }

    static Apoyo nuevoApoyo(String id) {
        return new Apoyo(id, "Camión cisterna", 2021, 7000,
                EstadoOperativo.DISPONIBLE, TipoFuncion.LOGISTICA);
    }

    /** Misión planificada que ya trae un soldado asignado (sin eso no se puede iniciar). */
    static Mision nuevaMision(String id) {
        Mision mision = new Mision(id, LocalDate.of(2026, 11, 3), "Pijao");
        mision.agregarPersonal("Sargento Cordoba");
        return mision;
    }

    /**
     * Hace pasar por todo el ciclo (registrar, asignar, iniciar, kilometraje y completar)
     * a 'cantidad' misiones distintas del vehículo. El vehículo debe estar ya registrado.
     */
    static void completarMisiones(Batallon batallon, Vehiculo vehiculo, int cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            String idMision = "OP-" + vehiculo.getId() + "-" + i;
            assertTrue(batallon.registrarMision(nuevaMision(idMision)));
            assertTrue(batallon.asignarVehiculoMision(idMision, vehiculo.getId()));
            assertTrue(batallon.iniciarMision(idMision));
            assertTrue(batallon.registrarKilometrajeMision(idMision, vehiculo.getId(), 5));
            assertTrue(batallon.completarMision(idMision));
        }
    }
}