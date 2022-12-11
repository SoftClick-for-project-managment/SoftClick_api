package softclick.server.webtier.remoting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Component;
import softclick.server.data.repositories.*;

@Configuration
public class RmiProxyFactoryBeans {
//    @Value("${com.softclick.data.server.host}")
//    private String dataServerHost;
//    @Value("${com.softclick.data.server.port}")
//    private String dataServerPort;
    private String dataServerSocket;

    @Autowired
    public RmiProxyFactoryBeans(Environment env) {
        this.dataServerSocket = env.getProperty("SOFTCLICK_DATA_HOST", "localhost");
//        this.dataServerSocket = dataServerHost+":"+dataServerPort;
//        this.dataServerSocket = "softclick-data-app-qa.azurewebsites.net:1099";
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryUserRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(UserRepository.class);
        bean.setServiceUrl("rmi://"+dataServerSocket+"/UserRepository");
        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryTaskRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(TaskRepository.class);
        bean.setServiceUrl("rmi://"+dataServerSocket+"/TaskRepository");
        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryRoleRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(RoleRepository.class);
        bean.setServiceUrl("rmi://"+dataServerSocket+"/RoleRepository");
        return bean;
    }
    @Bean
    RmiProxyFactoryBean rmiProxyFactoryProjectRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(ProjectRepository.class);
        bean.setServiceUrl("rmi://"+dataServerSocket+"/ProjectRepository");
        return bean;
    }
    @Bean
    RmiProxyFactoryBean rmiProxyFactoryDomainRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(DomainRepository.class);
        bean.setServiceUrl("rmi://"+dataServerSocket+"/DomainRepository");
        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryClientRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(ClientRepository.class);
        bean.setServiceUrl("rmi://"+dataServerSocket+"/ClientRepository");
        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryEmployeeRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(EmployeeRepository.class);
        bean.setServiceUrl("rmi://"+dataServerSocket+"/EmployeeRepository");
        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactorySkillRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(SkillRepository.class);
        bean.setServiceUrl("rmi://"+dataServerSocket+"/SkillRepository");
        return bean;
    }
    @Bean
    RmiProxyFactoryBean rmiProxyFactoryInvoiceRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(InvoiceRepository.class);
        bean.setServiceUrl("rmi://"+dataServerSocket+"/InvoiceRepository");
        return bean;
    }
    @Bean
    RmiProxyFactoryBean rmiProxyFactoryExpenseRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(ExpenseRepository.class);
        bean.setServiceUrl("rmi://"+dataServerSocket+"/ExpenseRepository");
        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryExpenseCategoryRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(ExpenseCategoryRepository.class);
        bean.setServiceUrl("rmi://"+dataServerSocket+"/ExpenseCategoryRepository");
        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryPriorityRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(PriorityRepository.class);
        bean.setServiceUrl("rmi://"+dataServerSocket+"/PriorityRepository");
        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryStatusRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(StatusRepository.class);
        bean.setServiceUrl("rmi://"+dataServerSocket+"/StatusRepository");

        return bean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryTeamRepoBean(){
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(TeamRepository.class);
        bean.setServiceUrl("rmi://"+dataServerSocket+"/TeamRepository");

        return bean;
    }
}
