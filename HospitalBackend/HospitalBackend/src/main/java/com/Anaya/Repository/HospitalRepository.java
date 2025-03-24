package com.Anaya.Repository;
import com.Anaya.DTO.HospitalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HospitalRepository {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;
    private SimpleJdbcCall listarHospitalesCall;
    @Autowired
    public HospitalRepository(JdbcTemplate jdbcTemplate) { // Inicialización de llamadas a procedimientos almacenados
        this.jdbcTemplate = jdbcTemplate;

        // Llamada para SP_HOSPITAL_REGISTRAR
        this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_HOSPITAL_REGISTRAR")
                .declareParameters(
                        new SqlParameter("p_id_distrito", Types.NUMERIC),
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_antiguedad", Types.NUMERIC),
                        new SqlParameter("p_area", Types.NUMERIC),
                        new SqlParameter("p_id_sede", Types.NUMERIC),
                        new SqlParameter("p_id_gerente", Types.NUMERIC),
                        new SqlParameter("p_id_condicion", Types.NUMERIC)
                );

        // Call para SP_HOSPITAL_LISTAR
        this.listarHospitalesCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_HOSPITAL_LISTAR")
                .declareParameters(
                        new SqlParameter("p_id_gerente", Types.NUMERIC),
                        new SqlParameter("p_id_distrito", Types.NUMERIC),
                        new SqlParameter("p_id_provincia", Types.NUMERIC),
                        new SqlOutParameter("cur_hospitales", oracle.jdbc.OracleTypes.CURSOR) // Cursor de salida
                );

        // Configurar la llamada al procedimiento almacenado
        this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_HOSPITAL_REGISTRAR") // Nombre del procedimiento
                .declareParameters(
                        new SqlParameter("p_id_distrito", Types.NUMERIC),
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_antiguedad", Types.NUMERIC),
                        new SqlParameter("p_area", Types.NUMERIC),
                        new SqlParameter("p_id_sede", Types.NUMERIC),
                        new SqlParameter("p_id_gerente", Types.NUMERIC),
                        new SqlParameter("p_id_condicion", Types.NUMERIC)
                );
    }

    /**
     * Método para registrar un hospital llamando al procedimiento almacenado
     */
    public void registrarHospital(int idDistrito, String nombre, int antiguedad, BigDecimal area,
                                  int idSede, int idGerente, int idCondicion) {

        // Crear mapa de parámetros

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("p_id_distrito", idDistrito);
        parametros.put("p_nombre", nombre);
        parametros.put("p_antiguedad", antiguedad);
        parametros.put("p_area", area);
        parametros.put("p_id_sede", idSede);
        parametros.put("p_id_gerente", idGerente);
        parametros.put("p_id_condicion", idCondicion);

        // Ejecutar el procedimiento almacenado
        simpleJdbcCall.execute(parametros);
    }
     //metodo para listar hopspitales
    public List<HospitalDTO> listarHospitales(Integer idGerente, Integer idDistrito, Integer idProvincia) {
        // Crear el mapa de parámetros con los nombres EXACTOS de los parámetros del procedimiento almacenado
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("p_id_gerente", idGerente); // Si es null, SimpleJdbcCall enviará NULL automáticamente
        parametros.put("p_id_distrito", idDistrito);
        parametros.put("p_id_provincia", idProvincia);

        // Ejecuta el procedimiento almacenado
        Map<String, Object> resultado = listarHospitalesCall.execute(parametros);

        // Manejar los resultados
        List<HospitalDTO> hospitales = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultados = (List<Map<String, Object>>) resultado.get("cur_hospitales");

        for (Map<String, Object> fila : resultados) {
            hospitales.add(new HospitalDTO(
                    ((Number) fila.get("id_hospital")).intValue(),
                    (String) fila.get("nombre"),
                    ((Number) fila.get("antiguedad")).intValue(),
                    ((Number) fila.get("area")).doubleValue(),
                    (String) fila.get("desc_sede"),
                    (String) fila.get("desc_gerente"),
                    (String) fila.get("desc_condicion"),
                    (String) fila.get("desc_distrito"),
                    (String) fila.get("desc_provincia")
            ));
        }

        return hospitales;


    }
    public void eliminarHospital(int idHospital) {
        // Configurar el llamado al procedimiento almacenado
        SimpleJdbcCall eliminarHospitalCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_HOSPITAL_ELIMINAR");

        // Establecer los parámetros necesarios
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("p_id_hospital", idHospital);

        // Ejecutar el procedimiento
        eliminarHospitalCall.execute(parametros);
    }

    public void actualizarHospital(int idHospital, int idDistrito, String nombre,
                                   int antiguedad, BigDecimal area, int idSede,
                                   int idGerente, int idCondicion) {

        // Configuración para llamar al procedimiento almacenado
        SimpleJdbcCall actualizarHospital = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_HOSPITAL_ACTUALIZAR");

        // Configuración de parámetros
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("p_id_hospital", idHospital);
        parametros.put("p_id_distrito", idDistrito);
        parametros.put("p_nombre", nombre);
        parametros.put("p_antiguedad", antiguedad);
        parametros.put("p_area", area);
        parametros.put("p_id_sede", idSede);
        parametros.put("p_id_gerente", idGerente);
        parametros.put("p_id_condicion", idCondicion);

        // Llamamos al procedimiento almacenado
        actualizarHospital.execute(parametros);
    }


    //Metodo para listar un hospital por su ID.

    public Map<String, Object> listarHospitalPorId(int idHospital) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_listar_hospital_por_id") // Nombre del procedimiento almacenado
                .returningResultSet("p_result_cursor", (rs, rowNum) -> {
                    // Mapeo de la respuesta del cursor SQL al objeto JSON esperado
                    Map<String, Object> hospitalData = new HashMap<>();
                    hospitalData.put("idDistrito", rs.getInt("idDistrito"));
                    hospitalData.put("nombre", rs.getString("nombre"));
                    hospitalData.put("antiguedad", rs.getInt("antiguedad"));
                    hospitalData.put("area", rs.getDouble("area"));
                    hospitalData.put("idSede", rs.getInt("idSede"));
                    hospitalData.put("idGerente", rs.getInt("idGerente"));
                    hospitalData.put("idCondicion", rs.getInt("idCondicion"));
                    return hospitalData;
                });

        // Parámetros que enviamos al procedimiento
        Map<String, Object> params = Map.of("p_id_hospital", idHospital);

        // Llamamos al procedimiento y capturamos el resultado
        Map<String, Object> result = simpleJdbcCall.execute(params);

        // Obtenemos la lista de resultados del cursor
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> results = (List<Map<String, Object>>) result.get("p_result_cursor");

        // Si no hay datos, lanzamos una excepción o manejamos el caso
        if (results == null || results.isEmpty()) {
            throw new IllegalArgumentException("El hospital con ID " + idHospital + " no existe.");
        }

        return results.get(0); // Devolvemos el primer (y único) resultado del cursor
    }

    //metodo para llamar al procedimiento almacenado buscar_Hospitales
    public List<HospitalDTO> buscarHospitales(String nombre, Integer idGerente, Integer idDistrito, Integer idProvincia) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_BuscarHospitales")
                .returningResultSet("cur", (ResultSet rs, int rowNum) -> new HospitalDTO(
                        rs.getInt("idHospital"),
                        rs.getString("nombre"),
                        rs.getInt("antiguedad"),
                        rs.getDouble("area"),
                        rs.getString("descSede"),
                        rs.getString("descGerente"),
                        rs.getString("descCondicion"),
                        rs.getString("descDistrito"),
                        rs.getString("descProvincia")
                ));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("p_nombre", nombre);
        parameters.put("p_idGerente", idGerente);
        parameters.put("p_idDistrito", idDistrito);
        parameters.put("p_idProvincia", idProvincia);

        Map<String, Object> result = jdbcCall.execute(parameters);
        return (List<HospitalDTO>) result.get("cur");
    }


}

