create or replace PROCEDURE sp_listar_hospital_por_id(
    p_id_hospital IN NUMBER,          -- Entrada: ID del hospital que queremos buscar
    p_result_cursor OUT SYS_REFCURSOR -- Salida: Cursor que devolverá los datos del hospital
)
IS
BEGIN
    -- Abrimos el cursor con la consulta que selecciona los datos requeridos
    OPEN p_result_cursor FOR
    SELECT 
        id_distrito AS idDistrito,
        nombre,
        antiguedad,
        area,
        id_sede AS idSede,
        id_gerente AS idGerente,
        id_condicion AS idCondicion
    FROM 
        hospital
    WHERE 
        id_hospital = p_id_hospital; -- Buscamos según el ID especificado
END;
