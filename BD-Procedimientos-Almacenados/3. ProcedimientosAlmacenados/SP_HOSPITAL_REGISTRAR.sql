create or replace PROCEDURE SP_HOSPITAL_REGISTRAR (
    p_id_distrito  IN NUMBER,
    p_nombre       IN VARCHAR2,
    p_antiguedad   IN NUMBER,
    p_area         IN NUMBER,
    p_id_sede      IN NUMBER,
    p_id_gerente   IN NUMBER,
    p_id_condicion IN NUMBER
) AS
BEGIN
    INSERT INTO Hospital (id_distrito, nombre, antiguedad, area, id_sede, id_gerente, id_condicion)
    VALUES (p_id_distrito, p_nombre, p_antiguedad, p_area, p_id_sede, p_id_gerente, p_id_condicion);
    COMMIT;
END SP_HOSPITAL_REGISTRAR;
