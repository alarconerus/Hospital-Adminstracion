create or replace PROCEDURE sp_BuscarHospitales (
    p_nombre IN VARCHAR2 DEFAULT NULL,
    p_idGerente IN NUMBER DEFAULT NULL,
    p_idDistrito IN NUMBER DEFAULT NULL,
    p_idProvincia IN NUMBER DEFAULT NULL,
    cur OUT SYS_REFCURSOR
) AS
BEGIN
    OPEN cur FOR
        SELECT h.id_hospital AS idHospital,
               h.nombre AS nombre,
               h.antiguedad AS antiguedad,
               h.area AS area,
               s.desc_sede AS descSede,
               g.desc_gerente AS descGerente,
               c.desc_condicion AS descCondicion,
               d.desc_distrito AS descDistrito,
               p.desc_provincia AS descProvincia
        FROM Hospital h
        JOIN Sede s ON h.id_sede = s.id_sede
        JOIN Gerente g ON h.id_gerente = g.id_gerente
        JOIN Condicion c ON h.id_condicion = c.id_condicion
        JOIN Distrito d ON h.id_distrito = d.id_distrito
        JOIN Provincia p ON d.id_provincia = p.id_provincia
        WHERE (p_nombre IS NULL OR LOWER(h.nombre) LIKE LOWER('%' || p_nombre || '%'))
          AND (p_idGerente IS NULL OR h.id_gerente = p_idGerente)
          AND (p_idDistrito IS NULL OR h.id_distrito = p_idDistrito)
          AND (p_idProvincia IS NULL OR p.id_provincia = p_idProvincia);
END;