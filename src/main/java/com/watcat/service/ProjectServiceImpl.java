package com.watcat.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;

import com.watcat.controller.KobisDatabase;

@Service
public class ProjectServiceImpl implements CommandLineRunner, ApplicationListener<ContextClosedEvent>, InitializingBean, DisposableBean {

	@Autowired
	KobisDatabase kobisDatabase;
	
	@PostConstruct
	private void init() {
		
	}
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(String... args) throws Exception {
		kobisDatabase.KobisAutoThread();
	}

}
