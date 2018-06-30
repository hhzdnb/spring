package com.vesus;

import com.vesus.audit.AuditOperationRecord;
import com.vesus.audit.AuditOperationRecordAware;
import com.vesus.audit.AuditOperationRecordAwareImpl;
import com.vesus.listener.CustomAuditor;
import com.vesus.listener.CustomInterceptor;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing
public class SpringBootHibernateAditInterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHibernateAditInterceptorApplication.class, args);
    }

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
        return hemf.getSessionFactory();
    }

    @Bean
    AuditOperationRecordAware auditOperationRecordAware() {
        return new AuditOperationRecordAwareImpl();
    }


    @Bean
    AuditorAware<String> auditorProvider() {
        return new CustomAuditor();
    }


    @Resource
    private SessionFactory sessionFactory;
    //@Autowired
    //AuditOperationRecord auditOperationRecord;

    @Autowired
    CustomInterceptor interceptor;

    @PostConstruct
    public void registerInterceptor() {
        //CustomInterceptor interceptor = new CustomInterceptor();
        //interceptor.setAuditOperationRecord(auditOperationRecord);
        sessionFactory.withOptions().interceptor(interceptor);
    }

}
