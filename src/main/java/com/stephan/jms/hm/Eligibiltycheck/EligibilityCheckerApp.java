package com.stephan.jms.hm.Eligibiltycheck;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.stephan.jms.hm.Eligibiltycheck.listeners.EligibilityCheckListener;

public class EligibilityCheckerApp {

	public static void main(String[] args) throws NamingException, InterruptedException {

		InitialContext initialContext = new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext=cf.createContext("eligibilityuser","eligibilitypass")){
			
			JMSConsumer consumer1 = jmsContext.createConsumer(requestQueue);
			JMSConsumer consumer2 = jmsContext.createConsumer(requestQueue);
			
			for(int i=1;i<=10;i+=2) {
				System.out.println("consumer1: "+consumer1.receive());
				System.out.println("consumer2: "+consumer2.receive());
			}
			//asyyn using alister
	//First		//consumer.setMessageListener(new EligibilityCheckListener());
			
			//Thread.sleep(10000);
			
		}
	}

}
