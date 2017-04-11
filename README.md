# spring-dubbo-parent
dubbo-spring-boot-starter
using dubbo with spring boot style 。this is based on dubbo 2.5.3 and spring boot 1.4.1.RELEASE
document:[spring boot with dubbo](https://my.oschina.net/u/3039671/blog/848097)

# how to use
you can deploy dubbo properties in spring boot configuration file —— application.properties/ym
e.g
```
dubbo:
  application:
    name: lizo
  registry:
    address: multicast://224.5.6.7:1234
  protocol:
    name: dubbo
    port: 20887
 ```
# @EnableDubbo
when bootstrap application, using @EnableDubbo annotation with the @Configurantion bean, lick this:
```
@SpringBootApplication
@EnableDubbo(basePackages = "com.alibaba.dubbo")
public class Provider {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(Provider.class)
                .web(false) 
                .run(args);
        new CountDownLatch(1).await();
    }
}
```
then you can using com.alibaba.dubbo.config.annotation.Service and com.alibaba.dubbo.config.annotation.Reference on provider bean and consumer bean respectively.

### api interface :
```
public interface AddService {
    int add(int a, int b);
}
```
### provider bean:
```
@Service
public class AddServiceImpl implements AddService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
```
### consumer bean:
```
 @Component

public class ConsumerAction {

    @Reference
    private AddService addService;

    public void add(int a,int b){
        System.out.println("ret = " + addService.add(a,b));
    }
}
```
more details see demo project
dubbo document see：http://dubbo.io/

# extension
## filter
you can define a dubbo filter as a Spring bean which extend AbstractDubboProviderFilterSupport or AbstractDubboConsumerFilterSupport
```
    @Bean
    ProviderFilter consumerFilter(){
        return new ProviderFilter();
    }

    static class ProviderFilter extends AbstractDubboProviderFilterSupport {
        public Result invoke(Invoker<?> invoker, Invocation invocation) {
            System.out.println("ProviderFilter");
            return invoker.invoke(invocation);
        }
    }
```
if you want more custom , you can using origin dubbo filter annotaion @activate with a spring bean extend AbstractDubboFilterSupport 
```
    @Bean
    CustomFilter customFilter(){
        return new CustomFilter();
    }

    @Activate(group = Constants.PROVIDER)
    static class CustomFilter extends AbstractDubboFilterSupport {
        public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
            System.out.println("CustomFilter");
            return invoker.invoke(invocation);
        }

        public Filter getDefaultExtension() {
            return this;
        }
    }
  ```
