% Ejemplo:
% ?- almacenar(82671, L).
% L = [8, 2, 6, 7, 1].

almacenar(N, [N]) :-
    N >= 0,
    N < 10.

almacenar(N, L) :-
    N >= 10,
    Resto is N // 10,
    Digito is N mod 10,
    almacenar(Resto, L1),
    agregar_final(L1, Digito, L).

% Agregar un elemento al final de una lista usando recursion porque el
% ejemplo se invierte el orden asi que no se
agregar_final([], X, [X]).
agregar_final([H|T], X, [H|R]) :-
    agregar_final(T, X, R).
