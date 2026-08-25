:- consult('hechos').

% regla 1
esta_en_peligro(Personaje) :-
    encontrar(Lugar, Personaje),
    zona(Lugar, Enemigo),
    enemigo(Enemigo).

% regla 2
esta_armado(Personaje) :-
    porta(pistola, Personaje);
    porta(cuchillo, Personaje).

% regla 3
mayor_de_25(Personaje) :-
    edad(Edad, Personaje),
    Edad > 25.
