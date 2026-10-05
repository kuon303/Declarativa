% ?- almacenar(82671, L).
% L = [8, 2, 6, 7, 1].

almacenar(0, [0]).

almacenar(N, L) :-
    N > 0,
    separar(N, [], L).

% Caso base
separar(0, L, L).

% Recursion
separar(N, Acumulador, L) :-
    N > 0,
    Digito is N mod 10,
    Resto is N // 10,
    separar(Resto, [Digito|Acumulador], L).
