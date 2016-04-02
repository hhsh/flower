package com.hajea.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Test {
    public static void main(String []arg){ 
        Tickets ticket = new Tickets(10);
        Producer producer = new Producer(ticket);
        Consumer consumer = new Consumer(ticket);
        producer.start();
        consumer.start();
    }
}
 
/*
 
Consumer buys ticket 9
Consumer buys ticket 10
Producer puts ticket 10
Ϊʲô���������������
�Ҿ������������ִ�еĻ����Ϳ��Բ������������⣺
1��buy()������println������ӡbuys ticket 9֮���л�
2��put()������this.number++��Ȼ���л� 
3��buy()������ if(i==this.number)����ʱif����false����Ϊnumber�Ѿ���1����ִ��buy()������println������ӡbuys ticket 10
4��put()����println������ӡput ticket 10

��buy()��put()ǰ�����synchronized���ɽ��
*/
 
class Tickets {
    int number = 0 ;
    int size;
    int i =0;
    boolean available = false;
    Lock lock = new ReentrantLock();
    public Tickets(int size){
        this.size = size;
    }
    public   void put(){
    	lock.lock();
    	try{
    		this.number++;
            System.out.println("Producer puts ticket "+this.number);
            this.available = true;
    	}finally{
    		lock.unlock();
    	}
    	
    	  
    }
    public   void buy(){
    	lock.lock();
    	try{
    		if(this.available==true && i<this.number){
                i++;
                System.out.println("Consumer buys ticket "+i);
            }
             
            if(i==this.number){
                this.available = false;
            }
    	}finally{
    		lock.unlock();
    	}
    	 
    }
}
class Producer extends Thread{
    Tickets t = null;
    public Producer(Tickets t){
        this.t = t;
    }
    public void run(){
        while(t.number<t.size){
        	t.put();
        }
        System.out.println("Producer ends!");
    } 
}
class Consumer extends Thread{
    Tickets t = null;
    int i=0;
    public Consumer(Tickets t){
        this.t = t;
    }
    public void run(){
        while(t.i<t.size){
           t.buy();
        }
        System.out.println("Consumer ends!");
    } 
}
