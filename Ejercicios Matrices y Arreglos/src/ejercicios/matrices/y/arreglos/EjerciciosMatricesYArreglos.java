/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicios.matrices.y.arreglos;

import java.util.Scanner;

public class EjerciciosMatricesYArreglos {

    static Scanner duki = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("***MENU***\n[1]Battleship\n[2]Busca Minas\n[3]Salir del prgrama");
        int opcionMenu = duki.nextInt();
        while (opcionMenu != 3) {
            switch (opcionMenu) {
                case 1:
                    Battleship Game = new Battleship();
                    Game.BattleshipGame();
                    break;

                case 2:
                    BuscaMinas Start = new BuscaMinas();
                    Start.BuscaMinasGame();
                    break;
                default:
                    System.out.println("Opcion no disponible");
                    break;
            }
            System.out.println("\n***MENU***\n[1]Battleship\n[2]Busca Minas\n[3]Salir del prgrama");
            opcionMenu = duki.nextInt();
        }
    }

}
