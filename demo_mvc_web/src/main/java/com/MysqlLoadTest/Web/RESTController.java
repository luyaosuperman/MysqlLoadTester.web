package com.MysqlLoadTest.Web;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {

    /*private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/validate")
    public Greeting greeting(@RequestParam(value="name", defaultValue="Hello, World!") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }*/
    
	@CrossOrigin(origins = "http://localhost:1841")
    @RequestMapping("/validate")
    public UserInfo greeting(@RequestParam(value="username", defaultValue="user1") String username,
    		@RequestParam(value="password", defaultValue="pass1") String password) {
    	
    	System.out.println("==== /validate ====");
        return new UserInfo(username,password);
        //http://localhost:8080/validate?username=luyao&password=123
    }

    @RequestMapping(value="/get_progress",method=RequestMethod.POST)
    public TestProgress get_progress(@RequestParam(value="runCount") int runCount,
    							   @RequestParam(value="totalThreads") int totalThreads
    		){
    	
    	System.out.println("runCount:" + runCount);
    	System.out.println("totalThreads:" + totalThreads);
    	
    	return new TestProgress(1,50);
    	
    }

}