package com.Anaya.Controller;
import com.Anaya.DTO.HospitalDTO;
import com.Anaya.DTO.HospitalRequest;
import com.Anaya.Service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/hospitales")
@CrossOrigin(origins = "http://localhost:4200") // Permitir Angular
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    /**
     * Endpoint para registrar un hospital
     */
    @PostMapping("/registrar")
    public ResponseEntity<Map<String, String>> registrarHospital(@RequestBody HospitalRequest request) {
        hospitalService.registrarHospital(
                request.getIdDistrito(),
                request.getNombre(),
                request.getAntiguedad(),
                request.getArea(),
                request.getIdSede(),
                request.getIdGerente(),
                request.getIdCondicion()
        );

        // Crear un objeto JSON de respuesta
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hospital registrado correctamente");

        return ResponseEntity.ok(response); // ✅ Devuelve un JSON válido
    }



    @GetMapping("/listar")
    public ResponseEntity<Object> listarHospitales(
            @RequestParam(required = false) Integer idGerente,
            @RequestParam(required = false) Integer idDistrito,
            @RequestParam(required = false) Integer idProvincia) {
        try {
            // Validaciones simples con mensajes descriptivos
            if (idGerente != null && idGerente < 0) {
                return ResponseEntity.badRequest().body("El ID del gerente no puede ser negativo");
            }
            if (idDistrito != null && idDistrito < 0) {
                return ResponseEntity.badRequest().body("El ID del distrito no puede ser negativo");
            }
            if (idProvincia != null && idProvincia < 0) {
                return ResponseEntity.badRequest().body("El ID de la provincia no puede ser negativo");
            }

            // Si los parámetros son null, la lógica en el repository devolverá todos los hospitales
            List<HospitalDTO> hospitales = hospitalService.listarHospitales(idGerente, idDistrito, idProvincia);
            return ResponseEntity.ok(hospitales);

        } catch (Exception e) {
            e.printStackTrace();
            // Maneja cualquier otro error y lo registra
            return ResponseEntity.internalServerError().body("Error interno al procesar la solicitud");
        }

    }
    @DeleteMapping("/eliminar/{idHospital}")
    public ResponseEntity<Map<String, String>> eliminarHospital(@PathVariable int idHospital) {
        Map<String, String> response = new HashMap<>();
        try {
            hospitalService.eliminarHospital(idHospital);
            response.put("message", "Hospital eliminado correctamente.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "Error al eliminar el hospital.");
            return ResponseEntity.internalServerError().body(response);
        }
    }
    @PutMapping("/actualizar/{idHospital}")
    public ResponseEntity<Map<String, String>> actualizarHospital(
            @PathVariable int idHospital,
            @RequestBody HospitalRequest hospitalRequest) {
        try {
            // Llamar al servicio para actualizar el hospital
            hospitalService.actualizarHospital(
                    idHospital,
                    hospitalRequest.getIdDistrito(),
                    hospitalRequest.getNombre(),
                    hospitalRequest.getAntiguedad(),
                    hospitalRequest.getArea(),
                    hospitalRequest.getIdSede(),
                    hospitalRequest.getIdGerente(),
                    hospitalRequest.getIdCondicion()
            );

            // Crear respuesta en formato JSON
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hospital actualizado correctamente.");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Ocurrió un error al actualizar el hospital.");
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    /**
     * Endpoint para obtener un hospital por su ID.
     */
    /**
     * Endpoint para listar un hospital por ID en formato JSON.
     * @param id ID del hospital que buscamos
     * @return JSON con los datos del hospital
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerHospitalPorId(@PathVariable int id) {
        try {
            Map<String, Object> hospital = hospitalService.obtenerHospitalPorId(id);
            return ResponseEntity.ok(hospital);
        } catch (IllegalArgumentException e) {
            // Si no se encuentra el hospital, devolvemos un error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<HospitalDTO>> buscarHospitales(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer idGerente,
            @RequestParam(required = false) Integer idDistrito,
            @RequestParam(required = false) Integer idProvincia
    ) {
        List<HospitalDTO> hospitales = hospitalService.buscarHospitales(nombre, idGerente, idDistrito, idProvincia);
        return new ResponseEntity<>(hospitales, HttpStatus.OK);
    }







}