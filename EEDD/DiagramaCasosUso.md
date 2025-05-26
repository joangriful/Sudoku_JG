## 🎯 Diagrama de Casos de Uso - Proyecto Sudoku Java

```plantuml
@startuml

actor Jugador

usecase "Seleccionar dificultad" as UC1
usecase "Visualizar tablero" as UC2
usecase "Introducir número" as UC3
usecase "Solicitar pista" as UC4
usecase "Reiniciar partida" as UC5
usecase "Cambiar dificultad" as UC6
usecase "Finalizar partida (ganar/perder)" as UC7
usecase "Recibir mensajes de estado" as UC8
usecase "Jugar contrarreloj" as UC9

Jugador --> UC1
Jugador --> UC2
Jugador --> UC3
Jugador --> UC4
Jugador --> UC5
Jugador --> UC6
Jugador --> UC7
Jugador --> UC8
Jugador --> UC9

UC3 --> UC8 : «include»
UC4 --> UC8 : «include»
UC9 --> UC7 : «include»

@enduml



