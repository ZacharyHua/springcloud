package com.study.springcloudstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
//@EnableBinding(SpringCloudStreamApplication.TestOutput.class)
@EnableBinding({Sink.class, Source.class})
public class SpringCloudStreamApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context =  SpringApplication.run(SpringCloudStreamApplication.class, args);
//        TestOutput out = context.getBean(TestOutput.class);
//        out.output().send(new GenericMessage("11111111111111111111"));
        Source source = context.getBean(Source.class);
        Thread.sleep(5000L);

        source.output().send(new GenericMessage("测试Stream代码块"));
    }

//    public interface TestOutput{
//        @Output("testOutput")
//        MessageChannel output();
//    }

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT,poller = @Poller(fixedDelay = "5000",maxMessagesPerPoll = "1"))
    public MessageSource<String> sendMessage() {
        return new MessageSource<String>() {
            @Override
            public Message<String> receive() {
                return new GenericMessage("hello,spring cloud stream");
            }
        };
    }


}
