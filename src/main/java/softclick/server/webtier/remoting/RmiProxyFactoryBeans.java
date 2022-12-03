package softclick.server.webtier.remoting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import softclick.server.data.repositories.*;

@Configuration
public class RmiProxyFactoryBeans {
    @Bean
    RmiProxyFactoryBean rmiProxyFactoryUserRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(UserRepository.class);
        bean.setServiceUrl("rmi://localhost:1099/UserRepository");
        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryTaskRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(TaskRepository.class);
        bean.setServiceUrl("rmi://localhost:1099/TaskRepository");
        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryRoleRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(RoleRepository.class);
        bean.setServiceUrl("rmi://localhost:1099/RoleRepository");
        return bean;
    }
    @Bean
    RmiProxyFactoryBean rmiProxyFactoryProjectRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(ProjectRepository.class);
        bean.setServiceUrl("rmi://localhost:1099/ProjectRepository");
        return bean;
    }
    @Bean
    RmiProxyFactoryBean rmiProxyFactoryDomainRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(DomainRepository.class);
        bean.setServiceUrl("rmi://localhost:1099/DomainRepository");
        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryClientRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(ClientRepository.class);
        bean.setServiceUrl("rmi://localhost:1099/ClientRepository");
        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryEmployeeRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(EmployeeRepository.class);
        bean.setServiceUrl("rmi://localhost:1099/EmployeeRepository");
        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactorySkillRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(SkillRepository.class);
        bean.setServiceUrl("rmi://localhost:1099/SkillRepository");
        return bean;
    }
    @Bean
    RmiProxyFactoryBean rmiProxyFactoryInvoiceRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(InvoiceRepository.class);
        bean.setServiceUrl("rmi://localhost:1099/InvoiceRepository");
        return bean;
    }
}
