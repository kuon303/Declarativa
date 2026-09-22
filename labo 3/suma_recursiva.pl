% Suma todos los numeros enteros desde N hasta 1 mediante recursion.
% Ejemplo de consulta: ?- suma(4, S).
% Resultado: S = 10.

% Caso base.
suma(1, 1).

% Caso recursivo.
suma(N, S) :-
    N > 1,
    N1 is N - 1,
    suma(N1, S1),
    S is N + S1.
