package view;

import model.Skill;
import service.SkillService;

import java.sql.Connection;
import java.util.Collection;
import java.util.Scanner;

public class SkillView {
    private Scanner scanner;
    private SkillService skillService;

    public SkillView(Connection conn, Scanner sc) {

        scanner = sc;
        skillService = new SkillService(conn);
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
        System.out.println("Введите название скила для добавления: ");
        scanner.nextLine();
        String skillName = scanner.nextLine();

        int amount = skillService.insert(new Skill(skillName));
        if (amount > 0)
            System.out.println("Скил добавлен!");
        else
            System.out.println("Возникла ошибка при добавлении скила...");
    }

    private void deleteAction() {
        System.out.println("Введите id скила для удаления: ");
        Long skillId = scanner.nextLong();

        int amount = skillService.delete(skillId);
        System.out.println(amount + " записей удалено!");
    }

    private void updateAction() {
        System.out.println("Введите id скила для обновления: ");
        Long skillId = scanner.nextLong();

        scanner.nextLine();

        System.out.println("Введите название скила: ");
        String skillName = scanner.nextLine();

        int amount = skillService.update(new Skill(skillId,skillName));
        if (amount == 0)
            System.out.println("Запись с таким id не найдена!");
        else
            System.out.println(amount + " записей обновлено!");
    }

    private void selectByIdAction() {
        System.out.println("Введите id скила для показа: ");
        Long skillId = scanner.nextLong();

        Skill sk = skillService.getById(skillId);
        if (sk != null && sk.getName() != null)
            System.out.println(sk.getName());
        else
            System.out.println("Елемент с данным id в таблице отсутствует.");
    }

    private void selectAllAction() {
        Collection<Skill> skillCollection = skillService.getAll();

        System.out.println(skillCollection.size());

        skillCollection.forEach(System.out::println);
    }
}
