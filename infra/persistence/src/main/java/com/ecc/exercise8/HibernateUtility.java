package com.ecc.exercise8;

public class HibernateUtility {

	private static SessionFactory factory;

	private HibernateUtility() {}

	public static synchronized SessionFactory getSessionFactory() {
		if (factory == null) {
            factory = new Configuration().configure("hibernate.cfg.xml")
            							 .buildSessionFactory();
        }
        
        return factory;
	}
}