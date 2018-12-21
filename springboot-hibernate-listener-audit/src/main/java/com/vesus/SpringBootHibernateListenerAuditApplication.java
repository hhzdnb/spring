package com.vesus;

import com.vesus.audit.AuditOperationRecordAware;
import com.vesus.listener.AuditOperationEventListener;
import com.vesus.listener.AuditOperationRecordAwareImpl;
import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
public class SpringBootHibernateListenerAuditApplication {
    public static void main(String[] args) {
         SpringApplication.run(SpringBootHibernateListenerAuditApplication.class, args);
    }


    @Bean
    AuditOperationRecordAware auditOperationRecordAware() {
        return new AuditOperationRecordAwareImpl();
    }


    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
        return hemf.getSessionFactory();
    }

    @Autowired
    private AuditOperationEventListener auditOperationEventListener;

    @Resource
    private SessionFactory sessionFactory;

    @PostConstruct
    public void registerListeners() {
        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(
                EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(auditOperationEventListener);
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(auditOperationEventListener);
        registry.getEventListenerGroup(EventType.POST_DELETE).appendListener(auditOperationEventListener);
    }


}
