create or replace PROCEDURE sp_validar_usuario(
    p_username IN VARCHAR2,  
    p_password IN VARCHAR2,  
    p_is_valid OUT NUMBER    
) AS
    v_password_db VARCHAR2(100);  
    v_enabled NUMBER(1);          
BEGIN
    -- Debug: Imprime los parámetros que entran
    DBMS_OUTPUT.PUT_LINE('Parametros: username=' || p_username || ', password=' || p_password);

    -- Recuperar la contraseña y estado (enabled) del usuario
    SELECT password, enabled
    INTO v_password_db, v_enabled
    FROM Usuario
    WHERE username = p_username;

    -- Debug: Imprime valores recuperados de la consulta
    DBMS_OUTPUT.PUT_LINE('Valores consultados: password_db=' || v_password_db || ', enabled=' || v_enabled);

    -- Si el usuario está deshabilitado (enabled = 0), marcar como no válido
    IF v_enabled = 0 THEN
        p_is_valid := 0; -- Usuario deshabilitado
        RETURN;
    END IF;

    -- Comparar la contraseña ingresada con la almacenada
    IF v_password_db = p_password THEN
        p_is_valid := 1; -- Usuario válido
    ELSE
        p_is_valid := 0; -- Contraseña incorrecta
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_is_valid := 0; -- Usuario no encontrado
        DBMS_OUTPUT.PUT_LINE('Usuario no encontrado');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Ocurrió un error no identificado: ' || SQLERRM);
        RAISE;
END sp_validar_usuario;