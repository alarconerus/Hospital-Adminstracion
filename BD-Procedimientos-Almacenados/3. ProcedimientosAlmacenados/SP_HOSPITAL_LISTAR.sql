create or replace PROCEDURE SP_HOSPITAL_LISTAR (
    p_id_gerente   IN NUMBER DEFAULT NULL,
    p_id_distrito  IN NUMBER DEFAULT NULL,
    p_id_provincia IN NUMBER DEFAULT NULL,
    cur_hospitales OUT SYS_REFCURSOR
) AS
BEGIN
    OPEN cur_hospitales FOR
        SELECT h.id_hospital, h.nombre, h.antiguedad, h.area, 
               s.desc_sede, g.desc_gerente, c.desc_condicion, 
               d.desc_distrito, p.desc_provincia
        FROM Hospital h
        JOIN Sede s ON h.id_sede = s.id_sede
        JOIN Gerente g ON h.id_gerente = g.id_gerente
        JOIN Condicion c ON h.id_condicion = c.id_condicion
        JOIN Distrito d ON h.id_distrito = d.id_distrito
        JOIN Provincia p ON d.id_provincia = p.id_provincia
        WHERE (p_id_gerente IS NULL OR h.id_gerente = p_id_gerente)
          AND (p_id_distrito IS NULL OR h.id_distrito = p_id_distrito)
          AND (p_id_provincia IS NULL OR p.id_provincia = p_id_provincia);
END SP_HOSPITAL_LISTAR;


