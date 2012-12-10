package org.experimenter.repository.scheduler;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.orm.hibernate3.HibernateInterceptor;

/**
 * A helper class, that is used to wrap a bean's methpod calls with the Hibernate interceptor to enable them to access
 * the session.
 * 
 * @author jfaryad
 * 
 */
public class SessionAdvisor {

    @SuppressWarnings("unchecked")
    public static <T> T adviseWithHibernateInterceptor(T object, HibernateInterceptor hibernateInterceptor) {
        ProxyFactory factory = new ProxyFactory(object);
        Advised advised = (Advised) factory.getProxy();
        advised.addAdvice(hibernateInterceptor);
        return (T) advised;
    }
}
