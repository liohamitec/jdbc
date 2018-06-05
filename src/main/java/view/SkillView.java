package view;

import model.Skill;
import service.SkillService;

import java.sql.Connection;
import java.util.Scanner;

public class SkillView {
    Scanner scanner;
    SkillService skillService;

    public SkillView(int action, Connection conn, Scanner sc) {
        scanner = sc;
        skillService = new SkillService(conn);

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
        String skillName = scanner.nextLine();

        skillService.insert(new Skill(skillName));
    }

    private void deleteAction() {
        System.out.println("Введите id скила для удаления: ");
        Long skillId = scanner.nextLong();

        skillService.delete(skillId);
    }

    private void updateAction() {
        System.out.println("Введите id скила для обновления: ");
        Long skillId = scanner.nextLong();
        System.out.println("Введите название скила: ");
        String skillName = scanner.nextLine();

        skillService.update(new Skill(skillId,skillName));
    }

    private void selectByIdAction() {
        System.out.println("Введите id скила для показа: ");
        Long skillId = scanner.nextLong();

        skillService.getById(skillId);
    }

    private void selectAllAction() {
        skillService.getAll();
    }
}
