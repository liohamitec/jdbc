package view;

import model.Developer;
import service.DeveloperService;

import java.sql.Connection;
import java.util.Collection;
import java.util.Scanner;

public class DeveloperView {
    private Scanner scanner;
    private DeveloperService devService;

    public DeveloperView(Connection conn, Scanner sc) {
        scanner = sc;
        devService = new DeveloperService(conn);
    }

    public void executeAction(int action) {
        switch (action) {
            case 1: insertAction(); break;
            case 2: deleteAction(); break;
            case 3: updateAction(); break;
            case 4: selectByIdAction(); break;
            case 5: selectAllAction(); break;
        }
    }

    private void insertAction() {
        String devName = null;
        int age = 0;
        int salary = 0;

        System.out.println("Введите имя разработчика для добавления: ");
        scanner.nextLine();
        devName = scanner.nextLine();

        System.out.println("возраст (полных лет): ");
        age = scanner.nextInt();

        System.out.println("зарплата: ");
        salary = scanner.nextInt();

        int amount = devService.insert(new Developer(devName,age,salary));
        if (amount > 0)
            System.out.println("Разработчик добавлен!");
        else
            System.out.println("Возникла ошибка при добавлении разработчика...");
    }

    private void deleteAction() {
        System.out.println("Введите id разработчика для удаления: ");
        Long devId = scanner.nextLong();

        int amount = devService.delete(devId);
        System.out.println(amount + " записей удалено!");
    }

    private void updateAction() {
        System.out.println("Введите id разработчика для обновления: ");
        Long devId = scanner.nextLong();

        scanner.nextLine();

        System.out.println("Введите новое имя разработчика: ");
        String devName = scanner.nextLine();

        System.out.println("возраст (полных лет): ");
        int age = scanner.nextInt();

        scanner.nextLine();

        System.out.println("зарплата: ");
        int salary = scanner.nextInt();

        scanner.nextLine();

        int amount = devService.update(new Developer(devId,devName,age,salary));
        if (amount == 0)
            System.out.println("Запись с таким id не найдена!");
        else
            System.out.println(amount + " записей обновлено!");
    }

    private void selectByIdAction() {
        System.out.println("Введите id разработчика для показа: ");
        Long devId = scanner.nextLong();

        Developer dev = devService.getById(devId);
        if (dev != null) {
            System.out.println(dev.toString());
        } else {
            System.out.println("Елемент с данным id в таблице отсутствует.");
        }

        /*
        if (dev != null && dev.getName() != null) {
            System.out.print(dev.getName() + " ");
            Integer age = dev.getAge();
            Integer sal = dev.getSalary();
            if (age != null)
                System.out.print(dev.getAge() + " ");
            if (sal != null)
                System.out.print(dev.getSalary());
            System.out.println();
        */
    }

    private void selectAllAction() {
        Collection<Developer> devCollection = devService.getAll();
        devCollection.forEach(System.out::println);
    }
}
