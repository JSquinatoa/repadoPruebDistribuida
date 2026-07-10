INSERT INTO public.clientes(
    clie_id, clie_cedula, clie_email)
VALUES (1, '1751872399', 'jsquinatoa@uce.edu.ec');

INSERT INTO public.prestamos(
    pres_id, pres_clienteid, pres_tasainteres, pres_montototal)
VALUES (1, 1, 0.10, 150.10);

INSERT INTO public.pagos(
    pago_id, pago_montopago, pago_fechapago, pago_prestamo_id)
VALUES (1, 100.10, TO_DATE('06/08/2026', 'DD/MM/YYYY'), 1);