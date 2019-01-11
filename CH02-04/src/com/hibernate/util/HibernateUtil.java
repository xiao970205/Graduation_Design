package com.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    // SessionFactory实例（会话工厂）
    private static SessionFactory sessionFactory = null;
    
    /**
     * 静态初始化 Hibernate 
     */
    static {
        // 1 创建 StandardServiceRegistry 实例（标准注册服务）
        // 1.1 创建 StandardServiceRegistryBuilder 实例（标准注册服务生成器）
        // 1.2 从 hibernate.cfg.xml 配置文件中读取配置信息
        // 1.3 构建 StandardServiceRegistry实例（标准注册服务）
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        try {
            // 2 创建 SessionFactory 实例（会话工厂）
            // 2.1 根据标准注册服务创建一个  MetadataSources 实例（元数据资源集）
            // 2.2 构建元数据
            // 2.3 生成应用一般唯一的 SessionFactory 实例（会话工厂）
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // 2.4 创建失败手动释放 StandardServiceRegistry 实例
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }
    }

    /**
     * 打开一个 Session 会话
     * @return Session
     */
    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
