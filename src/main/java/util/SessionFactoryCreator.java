package util;

import model.Developer;
import model.Skill;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class SessionFactoryCreator {

    public static SessionFactory createFactory() {
        /*
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Developer.class);
        cfg.addAnnotatedClass(Skill.class);
        cfg.configure("hibernate.cfg.xml");


        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(cfg.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return cfg.buildSessionFactory(serviceRegistry);
        */
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Developer.class);
        cfg.addAnnotatedClass(Skill.class);
        cfg.configure("hibernate.cfg.xml");

        Map<String,String> jdbcUrlSettings = new HashMap<>();
        String jdbcDbUrl = System.getenv("JDBC_DATABASE_URL");
        if (null != jdbcDbUrl) {
            jdbcUrlSettings.put("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"));
        }

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(cfg.getProperties());
        builder.applySettings(jdbcUrlSettings);
        ServiceRegistry serviceRegistry = builder.build();
        return cfg.buildSessionFactory(serviceRegistry);

    }
}