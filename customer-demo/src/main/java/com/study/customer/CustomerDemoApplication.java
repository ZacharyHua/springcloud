package com.study.customer;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.study.customer.event.MyEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RibbonClients(
        @RibbonClient(value = "hello-server")
)
public class CustomerDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CustomerDemoApplication.class);
        BusProperties busProperties = context.getBean(BusProperties.class);
        context.publishEvent(new MyEvent("这是SpringCloud-Bus 事件发布机制",busProperties.getId()));

//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        // 注册到spring上线文中
//        applicationContext.register(EventConfiguration.class);
//        // 启动上下文
//        applicationContext.refresh();
//        ApplicationEventPublisher publisher = applicationContext;
//        publisher.publishEvent(new MyApplicationEvent("开始学习springcloud-bus事件"));
    }

//    @Bean
//    public RestTemplate template(){
//        return new RestTemplate();
//    }

    @Bean
    @LoadBalanced
    public RestTemplate template(){
        return new RestTemplate();
    }

//    @Bean
//    public IRule ribbonRule(){
//        //自定义规则
//        return new RandomRule();
//    }


    // spring 中的事件机制   继承 ApplicationEvent 有了事件发布的基础
    // 自定义发布事件
    public static class MyApplicationEvent extends ApplicationEvent {

        /**
         * Create a new ApplicationEvent.
         *     创建我们的构造器  在构造器中定义我们的一些东西
         * @param source the object on which the event initially occurred (never {@code null})
         */
        public MyApplicationEvent(Object source) {
            super(source);
        }
    }

    @Configuration
    public static class EventConfiguration{

        @EventListener
        private void onEvent(MyApplicationEvent event){
            System.out.println("监听到事件:"+event);
        }
    }
}
