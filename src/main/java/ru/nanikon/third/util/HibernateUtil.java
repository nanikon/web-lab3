package ru.nanikon.third.util;

import ru.nanikon.third.model.ShotEntity;
import ru.nanikon.third.model.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Natalia Nikonova
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry registry;

    static {
        try (Scanner scr = new Scanner(new FileReader(System.getenv("DATABASE_CONFIG")))) {
            String url = scr.nextLine().trim();
            String login = scr.nextLine().trim();
            String password = scr.nextLine().trim();
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
            Map<String, String> settings = new HashMap<>();
            settings.put(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
            settings.put(Environment.URL, url);
            settings.put(Environment.USER, login);
            settings.put(Environment.PASS, password);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.Oracle10gDialect");

            registryBuilder.applySettings(settings);

            registry = registryBuilder.build();

            MetadataSources sources = new MetadataSources(registry);
            sources.addAnnotatedClass(UserEntity.class);
            sources.addAnnotatedClass(ShotEntity.class);
            Metadata metadata = sources.getMetadataBuilder().build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (FileNotFoundException e) {
            System.out.println("Не найден конфигуционный файл");
            System.exit(-1);
        } catch (NoSuchElementException e) {
            System.out.println("В конфигуционном файле не хватает строк.");
            System.exit(-1);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void close() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}