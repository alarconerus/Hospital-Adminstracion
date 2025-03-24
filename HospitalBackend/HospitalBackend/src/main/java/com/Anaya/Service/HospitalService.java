package com.Anaya.Service;
import com.Anaya.DTO.HospitalDTO;
import com.Anaya.Repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }
      //Registrar un hospital
    public void registrarHospital(int idDistrito, String nombre, int antiguedad, BigDecimal area,
                                  int idSede, int idGerente, int idCondicion) {
        hospitalRepository.registrarHospital(idDistrito, nombre, antiguedad, area, idSede, idGerente, idCondicion);
    }
    public List<HospitalDTO> listarHospitales(Integer idGerente, Integer idDistrito, Integer idProvincia) {
        return hospitalRepository.listarHospitales(idGerente, idDistrito, idProvincia);
    }
    public void eliminarHospital(int idHospital) {
        // Aquí podrías realizar validaciones si fuera necesario,
        // como verificar previamente si el hospital existe.

        // Llamar al repositorio para eliminar el hospital
        hospitalRepository.eliminarHospital(idHospital);
    }

    public void actualizarHospital(int idHospital, int idDistrito, String nombre,
                                   int antiguedad, BigDecimal area, int idSede,
                                   int idGerente, int idCondicion) {
        // Agregar lógica de validación si es necesario
        if (idHospital <= 0) {
            throw new IllegalArgumentException("El ID del hospital no es válido.");
        }
        // Llamar al repositorio para ejecutar la actualización
        hospitalRepository.actualizarHospital(idHospital, idDistrito, nombre, antiguedad, area, idSede, idGerente, idCondicion);
    }
    /**
     * Método para obtener los datos de un hospital por su ID.
     * @param id ID del hospital que queremos obtener
     * @return Mapa con los datos del hospital
     */
    public Map<String, Object> obtenerHospitalPorId(int id) {
        // Llama al repositorio y obtiene el hospital
        return hospitalRepository.listarHospitalPorId(id);
    }

    public List<HospitalDTO> buscarHospitales(String nombre, Integer idGerente, Integer idDistrito, Integer idProvincia) {
        return hospitalRepository.buscarHospitales(nombre, idGerente, idDistrito, idProvincia);
    }





}