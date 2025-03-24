create or replace PROCEDURE SP_HOSPITAL_ELIMINAR (
    p_id_hospital IN NUMBER
) AS
BEGIN
    DELETE FROM Hospital WHERE id_hospital = p_id_hospital;
    COMMIT;
END SP_HOSPITAL_ELIMINAR;
