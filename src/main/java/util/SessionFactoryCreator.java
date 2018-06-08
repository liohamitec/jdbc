package util;

import model.Developer;
import model.Skill;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryCreator {

    public static SessionFactory createFactory() {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Developer.class);
        cfg.addAnnotatedClass(Skill.class);
        cfg.configure("hibernate.cfg.xml");


        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(cfg.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return cfg.buildSessionFactory(serviceRegistry);
    }
}