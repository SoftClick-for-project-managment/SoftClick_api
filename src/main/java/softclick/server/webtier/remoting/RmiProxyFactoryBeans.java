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
}
