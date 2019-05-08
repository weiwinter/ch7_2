package com.wisely.ch7_2;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisely.ch7_2.bean.Person;

@Controller
@SpringBootApplication
public class Ch72Application {

	// 监听的http请求的端口,需要在application配置中添加http.port=端口号  如80
    @Value("${http.port}")
    Integer httpPort;
 
    //正常启用的https端口 如443
    @Value("${server.port}")
    Integer httpsPort;
    
	/*
	 * @RequestMapping("/") public String index(Model model) { Person single = new
	 * Person("aa",11); List<Person> people = new ArrayList<Person>(); Person
	 * single1 = new Person("xx",11); Person single2 = new Person("yy",22); Person
	 * single3 = new Person("zz",33); people.add(single1); people.add(single2);
	 * people.add(single3); model.addAttribute("singlePerson",single);
	 * model.addAttribute("people", people); return "index"; }
	 */
	public static void main(String[] args) {
		SpringApplication.run(Ch72Application.class, args);
	}
	
	@Bean
	public TomcatServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
		};
		
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
	}
	
	@Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(httpPort);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(httpsPort);
        return connector;
    }

}
