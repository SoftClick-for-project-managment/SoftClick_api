package softclick.server.webtier.remoting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.stereotype.Component;
import softclick.server.data.repositories.*;

@Configuration
public class RmiProxyFactoryBeans {
//    @Value("${com.softclick.data.server.host}")
//    private String dataServerHost;
    private final String dataServerSocket;

    @Autowired
    public RmiProxyFactoryBeans(Environment env) {
//        this.dataServerSocket = env.getProperty("SOFTCLICK_DATA_HOST", "http://localhost:1099");
        this.dataServerSocket = "https://softclick-qa-data-app.azurewebsites.net";
    }

    @Bean
    HttpInvokerProxyFactoryBean httpProxyFactoryUserRepoBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceUrl(dataServerSocket+"/UserRepository");
        bean.setServiceInterface(UserRepository.class);
        return bean;
    }

    @Bean
    HttpInvokerProxyFactoryBean httpProxyFactoryTaskRepoBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceUrl(dataServerSocket+"/TaskRepository");
        bean.setServiceInterface(TaskRepository.class);
        return bean;
    }

    @Bean
    HttpInvokerProxyFactoryBean httpProxyFactoryRoleRepoBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceInterface(RoleRepository.class);
        bean.setServiceUrl(dataServerSocket+"/RoleRepository");
        return bean;
    }
    @Bean
    HttpInvokerProxyFactoryBean httpProxyFactoryProjectRepoBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceInterface(ProjectRepository.class);
        bean.setServiceUrl(dataServerSocket+"/ProjectRepository");
        return bean;
    }
    @Bean
    HttpInvokerProxyFactoryBean httpProxyFactoryDomainRepoBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceInterface(DomainRepository.class);
        bean.setServiceUrl(dataServerSocket+"/DomainRepository");
        return bean;
    }

    @Bean
    HttpInvokerProxyFactoryBean httpProxyFactoryClientRepoBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceInterface(ClientRepository.class);
        bean.setServiceUrl(dataServerSocket+"/ClientRepository");
        return bean;
    }

    @Bean
    HttpInvokerProxyFactoryBean httpProxyFactoryEmployeeRepoBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceInterface(EmployeeRepository.class);
        bean.setServiceUrl(dataServerSocket+"/EmployeeRepository");
        return bean;
    }

    @Bean
    HttpInvokerProxyFactoryBean httpProxyFactorySkillRepoBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceInterface(SkillRepository.class);
        bean.setServiceUrl(dataServerSocket+"/SkillRepository");
        return bean;
    }
    @Bean
    HttpInvokerProxyFactoryBean httpProxyFactoryInvoiceRepoBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceInterface(InvoiceRepository.class);
        bean.setServiceUrl(dataServerSocket+"/InvoiceRepository");
        return bean;
    }
    @Bean
    HttpInvokerProxyFactoryBean httpProxyFactoryExpenseRepoBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceInterface(ExpenseRepository.class);
        bean.setServiceUrl(dataServerSocket+"/ExpenseRepository");
        return bean;
    }

    @Bean
    HttpInvokerProxyFactoryBean httpProxyFactoryExpenseCategoryRepoBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceInterface(ExpenseCategoryRepository.class);
        bean.setServiceUrl(dataServerSocket+"/ExpenseCategoryRepository");
        return bean;
    }

    @Bean
    HttpInvokerProxyFactoryBean httpProxyFactoryPriorityRepoBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceInterface(PriorityRepository.class);
        bean.setServiceUrl(dataServerSocket+"/PriorityRepository");
        return bean;
    }

    @Bean
    HttpInvokerProxyFactoryBean httpProxyFactoryStatusRepoBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceInterface(StatusRepository.class);
        bean.setServiceUrl(dataServerSocket+"/StatusRepository");

        return bean;
    }

    @Bean
    HttpInvokerProxyFactoryBean httpProxyFactoryTeamRepoBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceInterface(TeamRepository.class);
        bean.setServiceUrl(dataServerSocket+"/TeamRepository");

        return bean;
    }
}
