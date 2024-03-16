/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicios.matrices.y.arreglos;

import static ejercicios.matrices.y.arreglos.Battleship.columna;
import static ejercicios.matrices.y.arreglos.Battleship.fila;
import java.util.Random;
import java.util.Scanner;

public class BuscaMinas {

    static Random rndm = new Random();
    static Scanner duki = new Scanner(System.in);

    static boolean GameOver = false;

    public void BuscaMinasGame() {
        char[][] updateTablero = new char[fila][columna];
        char[][] updateTableroEspejo = new char[fila][columna];

        boolean win = false;
        System.out.println("***MODOS DE JUEGO***\n[1]Modo dinamico\n[2]Modo estatico\n[3]Volver al menu");
        int MododeJuegoMinas = duki.nextInt();
        while (MododeJuegoMinas != 3) {
            System.out.println("\n***TIPO DE JUEGO***\n[1]Modo Silencioso\n[2]Modo Simpke\n[3]Back");
            int tipodeJuego = duki.nextInt();
            if (tipodeJuego == 3) {
                break;
            }
            switch (MododeJuegoMinas) {
                case 1:
                    if (tipodeJuego == 1) {
                        char[][] TableroS = TableroenBlanco();
                        char[][] TableroEspejo = TableroDinamico(fila, columna);
                        ImprimirTablero(TableroS);
                        while (!win) {
                            System.out.print("\nIngrese coordenada a atacar [fila,columna]: ");
                            String coordenadas = duki.next();
                            String[] partes = coordenadas.split(",");
                            int coordenadaF = Integer.parseInt(partes[0]) - 1;
                            int coordenadaC = Integer.parseInt(partes[1]) - 1;

                            if (coordenadaC < 0 || coordenadaC > 6 || coordenadaF < 0 || coordenadaF > 5) {
                                System.out.println("Coordenadas no validas!!");
                                continue;
                            }
                            if (updateTablero[coordenadaF][coordenadaC] == 'X') {
                                System.out.println("Posicion ya ingresada, ingrese otra!!");
                                continue;
                            }

                            updateTablero = TableroMinasEspejo(coordenadaF, coordenadaC, TableroS);
                            updateTableroEspejo = TableroMinas(coordenadaF, coordenadaC, TableroEspejo);

                            ImprimirTablero(updateTablero);

                            TableroEspejo = updateTableroEspejo;
                            TableroS = updateTablero;

                            if (Win(updateTableroEspejo)) {
                                win = true;
                                System.out.println("¡Has ganado!");
                                break;
                            } else if (GameOver) {
                                win = true;
                                System.out.println("¡Has perdido!");
                                break;
                            }
                        }

                    } else if (tipodeJuego == 2) {
                        char[][] TableroS = TableroDinamico(fila, columna);
                        ImprimirTablero(TableroS);
                        while (!win) {
                            System.out.print("\nIngrese coordenada a atacar [fila,columna]: ");
                            String coordenadas = duki.next();
                            String[] partes = coordenadas.split(",");
                            int coordenadaF = Integer.parseInt(partes[0]) - 1;
                            int coordenadaC = Integer.parseInt(partes[1]) - 1;

                            if (coordenadaC < 0 || coordenadaC > 6 || coordenadaF < 0 || coordenadaF > 5) {
                                System.out.println("Coordenadas no validas!!");
                                continue;
                            }
                            if (updateTablero[coordenadaF][coordenadaC] == 'X') {
                                System.out.println("Posicion ya ingresada, ingrese otra!!");
                                continue;
                            }

                            updateTablero = TableroMinas(coordenadaF, coordenadaC, TableroS);
                            ImprimirTablero(updateTablero);
                            TableroS = updateTablero;

                            if (Win(updateTablero)) {
                                win = true;
                                System.out.println("¡Has ganado!");
                                break;
                            } else if (GameOver) {
                                win = true;
                                System.out.println("¡Has perdido!");
                                break;
                            }
                        }

                    } else {
                        System.out.println("Opcion no disponible");

                    }
                    break;

                case 2:
                    if (tipodeJuego == 1) {
                        ImprimirTablero(TableroSimpleSilencioso(fila, columna, TableroenBlanco()));
                        char[][] TableroS = TableroSimpleSilencioso(fila, columna, TableroenBlanco());
                        while (!win) {
                            System.out.print("\nIngrese coordenada a atacar [fila,columna]: ");
                            String coordenadas = duki.next();
                            String[] partes = coordenadas.split(",");
                            int coordenadaF = Integer.parseInt(partes[0]) - 1;
                            int coordenadaC = Integer.parseInt(partes[1]) - 1;

                            if (coordenadaC < 0 || coordenadaC > 6 || coordenadaF < 0 || coordenadaF > 5) {
                                System.out.println("Coordenadas no validas!!");
                                continue;
                            }

                            if (updateTablero[coordenadaF][coordenadaC] == 'X') {
                                System.out.println("Posicion ya ingresada, ingrese otra!!");
                                continue;
                            }

                            updateTablero = TableroSimpleSilencioso(coordenadaF, coordenadaC, TableroS);
                            ImprimirTablero(updateTablero);
                            TableroS = updateTablero;

                            if (Win(updateTablero)) {
                                win = true;
                                System.out.println("¡Has ganado!");
                                break;
                            } else if (GameOver) {
                                win = true;
                                System.out.println("¡Has perdido!");
                                break;
                            }
                        }

                    } else if (tipodeJuego == 2) {
                        ImprimirTablero(TableroSimple(fila, columna));
                        char[][] TableroS = TableroSimple(fila, columna);
                        while (!win) {
                            System.out.print("\nIngrese coordenada a atacar [fila,columna]: ");
                            String coordenadas = duki.next();
                            String[] partes = coordenadas.split(",");
                            int coordenadaF = Integer.parseInt(partes[0]) - 1;
                            int coordenadaC = Integer.parseInt(partes[1]) - 1;

                            if (coordenadaC < 0 || coordenadaC > 6 || coordenadaF < 0 || coordenadaF > 5) {
                                System.out.println("Coordenadas no validas!!");
                                continue;
                            }

                            if (updateTablero[coordenadaF][coordenadaC] == 'X') {
                                System.out.println("Posicion ya ingresada, ingrese otra!!");
                                continue;
                            }

                            updateTablero = TableroMinas(coordenadaF, coordenadaC, TableroS);
                            ImprimirTablero(updateTablero);
                            TableroS = updateTablero;

                            if (Win(updateTablero)) {
                                win = true;
                                System.out.println("¡Has ganado!");
                                break;
                            } else if (GameOver) {
                                win = true;
                                System.out.println("¡Has perdido!");
                                break;
                            }
                        }
                        System.out.println("\nSaliendo...");

                    } else {
                        System.out.println("Opcion no disponible");
                    }
                    break;

                default:
                    System.out.println("Opcion no disponible");
                    break;
            }
            System.out.println("\n***MODOS DE JUEGO***\n[1]Modo dinamico\n[2]Modo estatico\n[3]Back");
            MododeJuegoMinas = duki.nextInt();
        }
        System.out.println("Saliendo...");
    }


public static void ImprimirTablero(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println();
        }
    }

    public static char[][] TableroenBlanco() {
        char temp[][] = new char[fila][columna];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                temp[i][j] = ' ';
            }
        }
        return temp;
    }

    public static boolean Win(char[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static char[][] TableroMinas(int fila, int columna, char[][] tablero) {
        char[][] temp = new char[tablero.length][tablero[0].length];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (fila == i && columna == j) {
                    temp[i][j] = 'X';
                    if (tablero[i][j] == '*') {
                        System.out.println("BOOMMM!!!");
                        GameOver = true;
                    } else {
                        System.out.println("“Uff, no erá una bomba”");
                        GameOver = false;
                    }
                } else {
                    temp[i][j] = tablero[i][j];
                }
            }
        }
        return temp;
    }

    public static char[][] TableroMinasEspejo(int fila, int columna, char[][] tablero) {
        char[][] temp = new char[tablero.length][tablero[0].length];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (fila == i && columna == j) {
                    temp[i][j] = 'X';
                    if (tablero[i][j] == '*') {
                        GameOver = true;
                    } else {
                        GameOver = false;
                    }
                } else {
                    temp[i][j] = tablero[i][j];
                }
            }
        }
        return temp;
    }

    public static char[][] TableroSimpleSilencioso(int fila, int columna, char[][] tablero) {
        char[][] temp = new char[tablero.length][tablero[0].length];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (fila == i && columna == j) {
                    temp[i][j] = 'X';
                    if ((i == 0 && j == 0) || (i == 1 && j == 3) || (i == 3 && j == 1) || (i == 5 && j == 0) || (i == 5 && j == 4)) {
                        temp[i][j] = 'X';
                        System.out.println("BOOMMM!!!");
                        GameOver = true;
                    } else {
                        temp[i][j] = 'X';
                        System.out.println("“Uff, no erá una bomba”");
                        GameOver = false;
                    }
                } else {
                    temp[i][j] = tablero[i][j];
                }
            }
        }
        return temp;
    }

    public static char[][] TableroDinamico(int fila, int columna) {
        int columnaRandom = 0;
        int filaRandom = 0;
        char[][] temp = new char[fila][columna];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                temp[i][j] = ' ';
            }
        }

        for (int k = 0; k < 5; k++) {
            filaRandom = rndm.nextInt(fila);
            columnaRandom = rndm.nextInt(columna);
            temp[filaRandom][columnaRandom] = '*';
        }
        return temp;
    }

    public static char[][] TableroSimple(int fila, int columna) {
        char[][] temp = new char[fila][columna];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                if ((i == 0 && j == 0) || (i == 1 && j == 3) || (i == 3 && j == 1) || (i == 5 && j == 0) || (i == 5 && j == 4)) {
                    temp[i][j] = '*';
                } else {
                    temp[i][j] = ' ';
                }
            }
        }
        return temp;
    }

}
