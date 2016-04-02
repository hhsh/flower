package thread;

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
为什么出现先买后生产？
我觉得它如果这样执行的话，就可以产生这样的问题：
1、buy()方法中println方法打印buys ticket 9之后，切换
2、put()方法中this.number++，然后切换 
3、buy()方法中 if(i==this.number)，此时if返回false，因为number已经多1，又执行buy()方法中println方法打印buys ticket 10
4、put()方法println方法打印put ticket 10

在buy()和put()前面加上synchronized即可解决
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