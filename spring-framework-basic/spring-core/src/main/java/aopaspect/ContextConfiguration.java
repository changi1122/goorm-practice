package aopaspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "aopaspect")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ContextConfiguration {
}
