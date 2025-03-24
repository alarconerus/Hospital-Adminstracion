create or replace PROCEDURE SP_HOSPITAL_ACTUALIZAR (
    p_id_hospital  IN NUMBER,
    p_id_distrito  IN NUMBER,
    p_nombre       IN VARCHAR2,
    p_antiguedad   IN NUMBER,
    p_area         IN DECIMAL,
    p_id_sede      IN NUMBER,
    p_id_gerente   IN NUMBER,
    p_id_condicion IN NUMBER
) AS
BEGIN
    UPDATE Hospital
    SET id_distrito  = p_id_distrito,
        nombre       = p_nombre,
        antiguedad   = p_antiguedad,
        area         = p_area,
        id_sede      = p_id_sede,
        id_gerente   = p_id_gerente,
        id_condicion = p_id_condicion
    WHERE id_hospital = p_id_hospital;
    COMMIT;
END SP_HOSPITAL_ACTUALIZAR;
