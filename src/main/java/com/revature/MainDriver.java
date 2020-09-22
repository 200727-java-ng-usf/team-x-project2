package com.revature;

import com.revature.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

public class MainDriver {

    public static void main(String[] args) {
        System.out.println(HibernateUtil.getSessionFactory());
    }
}
