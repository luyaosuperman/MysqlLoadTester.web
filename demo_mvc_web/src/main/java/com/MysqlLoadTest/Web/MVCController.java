package com.MysqlLoadTest.Web;

import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.MysqlLoadTest.SocketController.SocketSender;
import com.MysqlLoadTest.Utilities.TestInfoClient;

@Controller
public class MVCController  extends WebMvcConfigurerAdapter {
	
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/get_progress").setViewName("get_progress");
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @RequestMapping(value="/landing", method=RequestMethod.GET)
    public String landing( TestInfoClient testInfoClient) {
        return "landing";
    }
    
    @RequestMapping(value="/landing",method=RequestMethod.POST)
    public String start_test(@Valid TestInfoClient testInfoClient, BindingResult bindingResult){
    	
    	if (bindingResult.hasErrors()){
    		return "landing";
    	}
    	
    	SocketSender.sendFromSocket(testInfoClient);
    	
    	return "forward:/get_progress";
    	
    }
    
}