/**
 * producer and consumer
 */
package com.ss.jb4.assignment3;

import java.util.LinkedList;

/**
 * @author alecs
 *
 */
public class ConsumerProducer {

	static int count = 0;
	static LinkedList<Integer> buffer = new LinkedList<Integer>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int stoppingPoint = 20;
		final int capacity = 4;
		Runnable consumer = new Runnable() {
			@Override
			public void run() {
				while (true) {
					synchronized (this) {
						try {
							while (buffer.isEmpty()) {
								Thread.sleep(1000);
								;
							}
							Integer value = buffer.getFirst();
							System.out.println("Consuming " + value);
							buffer.remove();
							if (value == stoppingPoint) {
								System.out.println("Finished consuming");
								System.exit(0);
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		Runnable producer = new Runnable() {
			@Override
			public void run() {
				while (count <= stoppingPoint) {
					synchronized (this) {
						try {
							while (buffer.size() >= capacity) {
								Thread.sleep(1000);
								;
							}
							System.out.println("Producing " + count);
							buffer.add(count++);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				System.out.println("Finished producing");
			}
		};
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t1.start();
		t2.start();
	}

}
