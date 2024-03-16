package ejercicios.matrices.y.arreglos;

import java.util.Random;
import java.util.Scanner;

public class Battleship {

    Random rndm = new Random();
    Scanner duki = new Scanner(System.in);

    public void ImprimirTablero(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println();
        }
    }

    static int cont = 0;
    static int contMaquina = 0;
    static char[][] tableroA;
    static char[][] tableroB;
    static char[][] tableroAsin;
    static char[][] tableroBsin;
    static char[][] Maintablero;
    static char[][] SegundoTablero;
    static int fila = 6;
    static int columna = 5;

    public void BattleshipGame() {

        boolean win = false;
        tableroA = TableroA(fila, columna);
        tableroB = TableroB(fila, columna);
        tableroAsin = TableroAsinBarcos(fila, columna);
        tableroBsin = TableroBsinBarcos(fila, columna);
        System.out.print("\n***JUGADORES***\n[1]1 jugador\n[2]2 jugadores\n[3]Salir al menu\nIngrese modo: ");
        int modojugadores = duki.nextInt();
        while (modojugadores != 3) {
            System.out.print("\n***MODO DE JUEGO***\n[1]Modo silencioso\n[2]Modo simple\n[3]Back\nIngrese modo: ");
            int modo = duki.nextInt();
            while (modo != 3) {
                switch (modojugadores) {
                    case 1:
                        System.out.println("Tablero A");
                        ImprimirTablero(tableroA);
                        System.out.println("Tablero B");
                        ImprimirTablero(tableroB);
                        System.out.print("\nElija que tablero quiere utilizar [A/B]: ");
                        char eleccionTablero = duki.next().toUpperCase().charAt(0);
                        System.out.println("Ha elegido el Tablero " + eleccionTablero);

                        if (modo == 1) {
                            if (eleccionTablero == 'A' || eleccionTablero == 'a') {
                                Maintablero = tableroAsin;
                                SegundoTablero = tableroBsin;
                            } else if (eleccionTablero == 'B' || eleccionTablero == 'b') {
                                Maintablero = tableroBsin;
                                SegundoTablero = tableroAsin;
                            }
                            char[][] updateSegundoTablero = new char[fila][columna];
                            char[][] updateTablero = new char[fila][columna];
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

                                if (updateSegundoTablero[coordenadaF][coordenadaC] == 'X' || updateSegundoTablero[coordenadaF][coordenadaC] == 'O') {
                                    System.out.println("Posición ya ingresada, ingrese otra!!");
                                    continue;
                                }

                                int coordenadaFMaquina = rndm.nextInt(fila);
                                int coordenadaCMaquina = rndm.nextInt(columna);

                                while (updateSegundoTablero[coordenadaFMaquina][coordenadaCMaquina] == 'X' && updateSegundoTablero[coordenadaFMaquina][coordenadaCMaquina] == 'O') {
                                    coordenadaFMaquina = rndm.nextInt(fila);
                                    coordenadaCMaquina = rndm.nextInt(columna);
                                }
                                int tipo1 = 1;//es igual a Usario
                                int tipo2 = 2;//es igual a Maquina

                                updateSegundoTablero = TableroenguerraModoSilencioso(coordenadaC, coordenadaF, SegundoTablero, tipo1);
                                ImprimirTablero(updateSegundoTablero);

                                updateTablero = TableroenguerraModoSilencioso(coordenadaCMaquina, coordenadaFMaquina, Maintablero, tipo2);
                                ImprimirTablero(updateTablero);

                                if (Win(tipo1)) {
                                    System.out.println("Has ganado!!!");
                                    break;
                                } else if (Win(tipo2)) {
                                    System.out.println("Has perdido contra la Maquina!!!");
                                    break;
                                }
                            }
                            reiniciarJuego();
                        } else if (modo == 2) {
                            if (eleccionTablero == 'A' || eleccionTablero == 'a') {
                                Maintablero = tableroA;
                                SegundoTablero = tableroB;
                            } else if (eleccionTablero == 'B' || eleccionTablero == 'b') {
                                Maintablero = tableroB;
                                SegundoTablero = tableroA;
                            }

                            Maintablero = Tablerosinbarcos(Maintablero);
                            SegundoTablero = Tablerosinbarcos(SegundoTablero);
                            char[][] updateSegundoTablero = new char[fila][columna];
                            char[][] updateTablero = new char[fila][columna];
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
                                if (updateSegundoTablero[coordenadaF][coordenadaC] == 'O' || updateSegundoTablero[coordenadaF][coordenadaC] == 'X') {
                                    System.out.println("Posicion ya ingresada, ingrese otra!!");
                                    continue;
                                }

                                int coordenadaFMaquina = rndm.nextInt(fila);
                                int coordenadaCMaquina = rndm.nextInt(columna);

                                while (updateSegundoTablero[coordenadaFMaquina][coordenadaCMaquina] == 'O' || updateSegundoTablero[coordenadaFMaquina][coordenadaCMaquina] == 'X') {
                                    coordenadaFMaquina = rndm.nextInt(fila);
                                    coordenadaCMaquina = rndm.nextInt(columna);
                                }
                                int tipo1 = 1;//es igual a Usario
                                int tipo2 = 2;//es igual a Maquina

                                updateSegundoTablero = Tableroenguerra(coordenadaC, coordenadaF, SegundoTablero, tipo1);
                                ImprimirTablero(updateSegundoTablero);

                                updateTablero = Tableroenguerra(coordenadaCMaquina, coordenadaFMaquina, Maintablero, tipo2);
                                ImprimirTablero(updateTablero);

                                if (Win(tipo1)) {
                                    System.out.println("Has ganado!!!");
                                    break;
                                } else if (Win(tipo2)) {
                                    System.out.println("Has perdido contra la Maquina!!!");
                                    break;
                                }
                            }// fin del boolean win
                            reiniciarJuego();
                        } else {
                            System.out.println("Opcion no disponible");
                            break;
                        }
                        break;

                    case 2:

                        if (modo == 1) {
                            int A = rndm.nextInt(2);
                            if (A == 0) {
                                Maintablero = tableroAsin;
                                SegundoTablero = tableroBsin;
                            } else {
                                Maintablero = tableroBsin;
                                SegundoTablero = tableroAsin;
                            }

                            char[][] updateSegundoTablero = new char[fila][columna];
                            char[][] updateTablero = new char[fila][columna];
                            while (!win) {
                                System.out.print("\nIngrese coordenada a atacar Jugador 1[fila,columna]: ");
                                String coordenadas = duki.next();
                                String[] partes = coordenadas.split(",");
                                int coordenadaF = Integer.parseInt(partes[0]) - 1;
                                int coordenadaC = Integer.parseInt(partes[1]) - 1;

                                if (coordenadaC < 0 || coordenadaC > 6 || coordenadaF < 0 || coordenadaF > 5) {
                                    System.out.println("Coordenadas no validas!!");
                                    continue;
                                }
                                if (updateSegundoTablero[coordenadaF][coordenadaC] == 'X' || updateSegundoTablero[coordenadaF][coordenadaC] == 'O') {
                                    System.out.println("Posicion ya ingresada, ingrese otra!!");
                                    continue;
                                }

                                System.out.print("\nIngrese coordenada a atacar Jugador 2[fila,columna]: ");
                                String coordenadas2 = duki.next();
                                String[] partes2 = coordenadas2.split(",");
                                int coordenadaF2 = Integer.parseInt(partes2[0]) - 1;
                                int coordenadaC2 = Integer.parseInt(partes2[1]) - 1;

                                if (coordenadaC2 < 0 || coordenadaC2 > 6 || coordenadaF2 < 0 || coordenadaF2 > 5) {
                                    System.out.println("Coordenadas no validas!!");
                                    continue;
                                }

                                if (updateTablero[coordenadaF2][coordenadaC2] == 'X' || updateTablero[coordenadaF2][coordenadaC2] == 'O') {
                                    System.out.println("Posicion ya ingresada, ingrese otra!!");
                                    continue;
                                }

                                int tipo1 = 1;//es igual a Usario
                                int tipo2 = 2;//es igual a Maquina

                                updateSegundoTablero = TableroenguerraModoSilencioso2jugadores(coordenadaC, coordenadaF, SegundoTablero, tipo1);
                                ImprimirTablero(updateSegundoTablero);

                                updateTablero = TableroenguerraModoSilencioso2jugadores(coordenadaC2, coordenadaF2, Maintablero, tipo2);
                                ImprimirTablero(updateTablero);

                                if (Win(tipo1)) {
                                    System.out.println("Jugador 1 Ha ganado!!!");
                                    break;
                                } else if (Win(tipo2)) {
                                    System.out.println("Jugador 2 Ha ganado!!!");
                                    break;
                                }
                            }
                            reiniciarJuego();
                        } else if (modo == 2) {
                            int A = rndm.nextInt(2);
                            if (A == 0) {
                                Maintablero = tableroA;
                                SegundoTablero = tableroB;
                            } else {
                                Maintablero = tableroB;
                                SegundoTablero = tableroA;
                            }
                            Maintablero = Tablerosinbarcos(Maintablero);
                            SegundoTablero = Tablerosinbarcos(SegundoTablero);
                            char[][] updateSegundoTablero = new char[fila][columna];
                            char[][] updateTablero = new char[fila][columna];
                            while (!win) {
                                System.out.print("\nIngrese coordenada a atacar Jugador 1[fila,columna]: ");
                                String coordenadas = duki.next();
                                String[] partes = coordenadas.split(",");
                                int coordenadaF = Integer.parseInt(partes[0]) - 1;
                                int coordenadaC = Integer.parseInt(partes[1]) - 1;

                                if (coordenadaC < 0 || coordenadaC > 6 || coordenadaF < 0 || coordenadaF > 5) {
                                    System.out.println("Coordenadas no validas!!");
                                    continue;
                                }
                                if (updateSegundoTablero[coordenadaF][coordenadaC] == 'X' || updateSegundoTablero[coordenadaF][coordenadaC] == 'O') {
                                    System.out.println("Posicion ya ingresada, ingrese otra!!");
                                    continue;
                                }

                                System.out.print("\nIngrese coordenada a atacar Jugador 2[fila,columna]: ");
                                String coordenadas2 = duki.next();
                                String[] partes2 = coordenadas2.split(",");
                                int coordenadaF2 = Integer.parseInt(partes2[0]) - 1;
                                int coordenadaC2 = Integer.parseInt(partes2[1]) - 1;

                                if (coordenadaC2 < 0 || coordenadaC2 > 6 || coordenadaF2 < 0 || coordenadaF2 > 5) {
                                    System.out.println("Coordenadas no validas!!");
                                    continue;
                                }

                                if (updateTablero[coordenadaF2][coordenadaC2] == 'X' || updateTablero[coordenadaF2][coordenadaC2] == 'O') {
                                    System.out.println("Posicion ya ingresada, ingrese otra!!");
                                    continue;
                                }

                                int tipo1 = 1;//es igual a Usario
                                int tipo2 = 2;//es igual a Maquina

                                updateSegundoTablero = Tableroenguerra2jugadores(coordenadaC, coordenadaF, SegundoTablero, tipo1);
                                ImprimirTablero(updateSegundoTablero);

                                updateTablero = Tableroenguerra2jugadores(coordenadaC2, coordenadaF2, Maintablero, tipo2);
                                ImprimirTablero(updateTablero);

                                if (Win(tipo1)) {
                                    System.out.println("Jugador 1 Ha ganado!!!");
                                    break;
                                } else if (Win(tipo2)) {
                                    System.out.println("Jugador 2 Ha ganado!!!");
                                    break;
                                }
                            }
                            reiniciarJuego();
                        }
                        break;
                    default:
                        System.out.println("Opcion no dispoble");
                        break;
                }
                System.out.print("\n***MODO DE JUEGO***\n[1]Modo silencioso\n[2]Modo simple\n[3]Back\nIngrese modo: ");
                modo = duki.nextInt();

            }
            System.out.println("\nSaliendo...");
            System.out.print("\n***JUGADORES***\n[1]1 jugador\n[2]2 jugadores\n[3]Salir al menu\nIngrese modo: ");
            modojugadores = duki.nextInt();
        }
        System.out.println("\nSaliendo al menu...");
    }

    public char[][] TableroA(int fila, int columna) {
        char temp[][] = new char[fila][columna];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                if ((i == 0 && j == 0) || (i == 1 && j == 0) || (i == 2 && j == 0)) {
                    temp[i][j] = '*';
                } else if ((i == 1 && j == 2) || (i == 1 && j == 3)) {
                    temp[i][j] = '*';

                } else if ((i == 3 && j == 4) || (i == 4 && j == 4) || (i == 5 && j == 4)) {
                    temp[i][j] = '*';

                } else {
                    temp[i][j] = ' ';
                }
            }
        }
        return temp;
    }

    public char[][] TableroB(int fila, int columna) {
        char temp[][] = new char[fila][columna];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                if ((i == 1 && j == 3) || (i == 2 && j == 3) || (i == 3 && j == 3)) {
                    temp[i][j] = '*';
                } else if ((i == 5 && j == 3) || (i == 5 && j == 4)) {
                    temp[i][j] = '*';

                } else if ((i == 3 && j == 1) || (i == 4 && j == 1) || (i == 5 && j == 1)) {
                    temp[i][j] = '*';

                } else {
                    temp[i][j] = ' ';
                }
            }
        }
        return temp;
    }

    public boolean Win(int tipo) {
        boolean win = false;
        if (cont >= 3 && tipo == 1) {
            win = true;
        } else if (contMaquina >= 3 && tipo == 2) {
            win = true;
        }
        return win;
    }

    public char[][] TableroAsinBarcos(int fila, int columna) {
        char temp[][] = new char[fila][columna];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                temp[i][j] = ' ';
            }
        }
        return temp;
    }

    public char[][] TableroBsinBarcos(int fila, int columna) {
        char temp[][] = new char[fila][columna];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                temp[i][j] = ' ';
            }
        }
        return temp;
    }

    public char[][] Tablerosinbarcos(char[][] tablero) {
        char[][] temp = new char[tablero.length][tablero[0].length];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (temp[i][j] == '*') {
                    temp[i][j] = ' ';
                }
            }
        }
        return temp;
    }

    public char[][] Tableroenguerra(int columna, int fila, char[][] tablero, int tipo) {
        if (tipo == 1) {
            System.out.println("****Tablero de Rival****");
            if (tablero[fila][columna] == '*') {
                tablero[fila][columna] = 'X';
                cont++;
                System.out.println("Uy, un barco ha sido dañado!!");
            } else {
                tablero[fila][columna] = 'O';
                System.out.println("Bomba al agua!!");
            }
        } else if (tipo == 2) {
            System.out.println("*****Tablero de Usuario****");
            if (tablero[fila][columna] == '*') {
                tablero[fila][columna] = 'X';
                contMaquina++;
                System.out.println("Nos han danado un barco!!");
            } else {
                tablero[fila][columna] = 'O';
            }
        }
        return tablero;
    }

    public char[][] Tableroenguerra2jugadores(int columna, int fila, char[][] tablero, int tipo) {
        if (tipo == 1) {
            System.out.println("****Tablero de Jugador 2****");
            if (tablero[fila][columna] == '*') {
                tablero[fila][columna] = 'X';
                cont++;
                System.out.println("Uy, un barco ha sido dañado Jugador 2!!");
            } else {
                tablero[fila][columna] = 'O';
                System.out.println("Bomba al agua Jugador 2!!");
            }
        } else if (tipo == 2) {
            System.out.println("****Tablero de Jugador 1****");
            if (tablero[fila][columna] == '*') {
                tablero[fila][columna] = 'X';
                contMaquina++;
                System.out.println("Uy, un barco ha sido dañado Jugador 1!!");
            } else {
                tablero[fila][columna] = 'O';
                System.out.println("Bomba al agua Jugador 1!!");
            }
        }
        return tablero;
    }

    public char[][] TableroenguerraModoSilencioso(int columna, int fila, char[][] tablero, int tipo) {
        if (tipo == 1) {
            System.out.println("****Tablero de Rival****");
            if ((fila == 0 && columna == 0) || (fila == 1 && columna == 0) || (fila == 2 && columna == 0)) {
                tablero[fila][columna] = 'X';
                cont++;
                System.out.println("Uy, un barco ha sido dañado!!!");
            } else if ((fila == 1 && columna == 2) || (fila == 1 && columna == 3)) {
                tablero[fila][columna] = 'X';
                cont++;
                System.out.println("Uy, un barco ha sido dañado!!!");
            } else if ((fila == 3 && columna == 4) || (fila == 4 && columna == 4) || (fila == 5 && columna == 4)) {
                tablero[fila][columna] = 'X';
                cont++;
                System.out.println("Uy, un barco ha sido dañado!!!");
            } else {
                tablero[fila][columna] = 'O';
                System.out.println("Bomba al agua!!");
            }
        } else if (tipo == 2) {
            System.out.println("*****Tablero de Usuario****");
            if ((fila == 0 && columna == 0) || (fila == 1 && columna == 0) || (fila == 2 && columna == 0)) {
                tablero[fila][columna] = 'X';
                contMaquina++;
                System.out.println("Nos han danado un barco!!");
            } else if ((fila == 1 && columna == 2) || (fila == 1 && columna == 3)) {
                tablero[fila][columna] = 'X';
                contMaquina++;
                System.out.println("Nos han danado un barco!!");
            } else if ((fila == 3 && columna == 4) || (fila == 4 && columna == 4) || (fila == 5 && columna == 4)) {
                tablero[fila][columna] = 'X';
                contMaquina++;
                System.out.println("Nos han danado un barco!!");
            } else {
                tablero[fila][columna] = 'O';
            }
        }
        return tablero;
    }

    public char[][] TableroenguerraModoSilencioso2jugadores(int columna, int fila, char[][] tablero, int tipo) {
        if (tipo == 1) {
            System.out.println("****Tablero de Jugador 2****");

            if ((fila == 0 && columna == 0) || (fila == 1 && columna == 0) || (fila == 2 && columna == 0)) {
                tablero[fila][columna] = 'X';
                cont++;
                System.out.println("Uy, un barco ha sido dañado! Jugador 2!!");
            } else if ((fila == 1 && columna == 2) || (fila == 1 && columna == 3)) {
                tablero[fila][columna] = 'X';
                cont++;
                System.out.println("Uy, un barco ha sido dañado! Jugador 2!!");
            } else if ((fila == 3 && columna == 4) || (fila == 4 && columna == 4) || (fila == 5 && columna == 4)) {
                tablero[fila][columna] = 'X';
                cont++;
                System.out.println("Uy, un barco ha sido dañado! Jugador 2!!");
            } else {
                tablero[fila][columna] = 'O';
                System.out.println("Bomba al agua Jugador 2!!");
            }

        } else if (tipo == 2) {
            System.out.println("****Tablero de Jugador 1****");
            if ((fila == 0 && columna == 0) || (fila == 1 && columna == 0) || (fila == 2 && columna == 0)) {
                tablero[fila][columna] = 'X';
                contMaquina++;
                System.out.println("Uy, un barco ha sido dañado Jugador 1!!");
            } else if ((fila == 1 && columna == 2) || (fila == 1 && columna == 3)) {
                tablero[fila][columna] = 'X';
                contMaquina++;
                System.out.println("Uy, un barco ha sido dañado Jugador 1!!");
            } else if ((fila == 3 && columna == 4) || (fila == 4 && columna == 4) || (fila == 5 && columna == 4)) {
                tablero[fila][columna] = 'X';
                contMaquina++;
                System.out.println("Uy, un barco ha sido dañado Jugador 1!!");
            } else {
                tablero[fila][columna] = 'O';
                System.out.println("Bomba al agua Jugador 1!!");
            }
        }
        return tablero;
    }

    public void reiniciarJuego() {
        cont = 0;
        contMaquina = 0;
        tableroA = TableroA(fila, columna);
        tableroB = TableroB(fila, columna);
        tableroAsin = TableroAsinBarcos(fila, columna);
        tableroBsin = TableroBsinBarcos(fila, columna);
        Maintablero = null;
        SegundoTablero = null;
    }
}
