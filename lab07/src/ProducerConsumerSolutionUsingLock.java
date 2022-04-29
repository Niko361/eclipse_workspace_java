import java.util.LinkedList; 
import java.util.Queue; 
import java.util.Random; 
import java.util.concurrent.locks.Condition; 
import java.util.concurrent.locks.Lock; 
import java.util.concurrent.locks.ReentrantLock; 
/** * Java Program to demonstrate how to use Lock and Condition variable in Java by * solving Producer consumer problem. Locks are more flexible way to provide * mutual exclusion and synchronization in Java, a powerful alternative of * synchronized keyword. * * @author Javin Paul */ 
public class ProducerConsumerSolutionUsingLock { 
	public static void main(String[] args) { 
		// Object on which producer and consumer thread will operate 
		ProducerConsumerImpl sharedObject = new ProducerConsumerImpl(); 
		// creating producer and consumer threads 
		Producer p = new Producer(sharedObject); 
		Consumer c = new Consumer(sharedObject); 
		// starting producer and consumer threads 
		p.start(); 
		c.start(); 
	} 
} 

class ProducerConsumerImpl { 
	// producer consumer problem data 
	private static final int CAPACITY = 2; 
	private final Queue<Integer> queue = new LinkedList<Integer>(); 
	private final Random theRandom = new Random(); 
	// lock and condition variables 
	private final Lock aLock = new ReentrantLock(); 
	private final Condition bufferNotFull = aLock.newCondition(); 
	private final Condition bufferNotEmpty = aLock.newCondition(); 
	
	public void put() throws InterruptedException { 
		aLock.lock(); 
		try { 
			while (queue.size() == CAPACITY) { 
				System.out.println(Thread.currentThread().getName() + " : Buffer is full, waiting"); 
				bufferNotFull.await(); 
			}
			int number = theRandom.nextInt(); 
			boolean isAdded = queue.offer(new Integer(number)); 
			if (isAdded) { 
				System.out.printf("%s added %d into queue %n", Thread .currentThread().getName(), number); 
				// signal consumer thread that, buffer has element now 
				System.out.println(Thread.currentThread().getName() + " : Signalling that buffer is no more empty now"); 
				bufferNotEmpty.signalAll(); 
			} 
		} finally { 
			aLock.unlock(); 
		} 
	} 
	
	public void get() throws InterruptedException { 
		aLock.lock(); 
		try { 
			while (queue.size() == 0) { 
				System.out.println(Thread.currentThread().getName() + " : Buffer is empty, waiting"); 
				bufferNotEmpty.await(); 
			} 
			Integer value = (Integer)queue.poll(); 
			if (value != null) { 
				System.out.printf("%s consumed %d from queue %n", Thread .currentThread().getName(), value); 
				// signal producer thread that, buffer may be empty now 
				System.out.println(Thread.currentThread().getName() + " : Signalling that buffer is not full now"); 
				bufferNotFull.signalAll(); 
			} 
		} finally { 
			aLock.unlock(); 
		} 
	} 
} 

class Producer extends Thread { 
	ProducerConsumerImpl pc; 
	
	public Producer(ProducerConsumerImpl sharedObject) { 
		super("PRODUCER"); 
		this.pc = sharedObject; 
	} 
	@Override public void run() { 
		try { 
			for(int i=0; i<4; i++)
				pc.put(); 
		} 
		catch (InterruptedException e) { 
			e.printStackTrace(); 
		} 
	} 
} 


class Consumer extends Thread { 
	ProducerConsumerImpl pc; 
	public Consumer(ProducerConsumerImpl sharedObject) { 
		super("CONSUMER"); 
		this.pc = sharedObject; 
	} 
	@Override public void run() { 
		try { 
			for(int i=0; i<4; i++)
				pc.get(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 
		
	}
}
