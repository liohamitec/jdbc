package view;

import util.ConnectionUtil;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserMenu {
    Connection conn;

    public UserMenu() {
        ConnectionUtil connUtil = new ConnectionUtil();
        conn = connUtil.getConnection();

        execute();

        connUtil.closeConnection();
    }

    private void execute() {
        Scanner sc = new Scanner(System.in);

        int table = 0;
        int action = 0;

        printGeneralMenu();
        table = sc.nextInt();
        if (table < 1 || table > 2)
            throw new InputMismatchException("Введеное число не входит в диапазон корректных значений!");

        printSubmenu();
        action = sc.nextInt();
        if (action < 1 || action > 5)
            throw new InputMismatchException("Введеное число не входит в диапазон корректных значений!");

        switch (table) {
            case 1: new SkillView(action,conn,sc); break;
            case 2: //new DeveloperView(action,conn,sc);
                    break;
        }

        sc.close();
    }


    private void printGeneralMenu() {
        System.out.println("Выберите таблицу: ");
        System.out.println("1. developers");
        System.out.println("2. skills");
    }

    private void printSubmenu() {
        System.out.println("Выберите действие: ");
        System.out.println("1. Вставка элемента");
        System.out.println("2. Удаление элемента");
        System.out.println("3. Обновление элемента");
        System.out.println("4. Получить строку по id");
        System.out.println("5. Получить все строки из таблицы");

    }

    public static void main(String[] args) {
        new UserMenu();
    }

}
