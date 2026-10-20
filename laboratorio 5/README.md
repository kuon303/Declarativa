# Calculadora Java + Prolog (adaptada de la Guía 5)

Este proyecto **reutiliza la organización de archivos, clases, estilo visual y mecanismo de conexión** de la sección «Java y Prolog» de la Guía 5. Solo se adaptó la funcionalidad de derivar/integrar a sumar/restar dos números.

## Estructura

```text
CalculadoraJavaProlog_Guia5/
├── pom.xml
└── src/main/
    ├── java/org/example/
    │   ├── Main.java
    │   ├── Connection.java
    │   └── screen/
    │       ├── Theme.java
    │       ├── Window.java
    │       └── components/
    │           ├── Buttons.java
    │           ├── TextField.java
    │           └── PanelResult.java
    └── resources/
        └── operaciones.pl
```

## Para ejecutarlo

1. Abre la carpeta del proyecto con **IntelliJ IDEA**.
2. Abre o importa `pom.xml` como proyecto **Maven** si IntelliJ lo solicita.
3. Espera a que Maven descargue la dependencia **tuProlog 3.3.0** (requiere Internet).
4. Ejecuta `org.example.Main`.
5. Introduce dos números y pulsa **Sumar** o **Restar**.

Admite enteros, negativos y decimales con punto (`3.5`).

## Dónde se hace cada cosa

- `Window.java`: recibe los números y decide si consultar `suma` o `resta` al pulsar cada botón.
- `Connection.java`: carga `operaciones.pl` y envía consultas a Prolog, por ejemplo `suma(10, 5, R).`.
- `operaciones.pl`: ejecuta la operación mediante `R is A + B` o `R is A - B`.
- `Main.java`: inicia el motor y muestra la ventana.
- `Theme.java`, `Buttons.java`, `TextField.java`, `PanelResult.java`: componentes de interfaz tomados como referencia de la guía, con ajustes mínimos.

**La aritmética de sumar/restar está solamente en `operaciones.pl`.** Java valida y envía los datos a Prolog, y muestra la respuesta recibida.
