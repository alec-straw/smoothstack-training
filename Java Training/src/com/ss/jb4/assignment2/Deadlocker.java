/**
 * demonstrates a deadlock
 */
package com.ss.jb4.assignment2;

/**
 * @author alecs
 *
 */
public class Deadlocker implements Runnable {

	public static String lock1 = "Lock 1";
	public static String lock2 = "Lock 2";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Deadlocker t1 = new Deadlocker();
		Runnable t2 = new Runnable() {

			@Override
			public void run() {
				synchronized (lock2) {
					System.out.println("Thread 2 holding lock 2");
					synchronized (lock1) {
						System.out.println("Thread 2 holding lock 1 and 2");
					}
				}
			}
		};

		new Thread(t1).start();
		new Thread(t2).start();
	}

	@Override
	public void run() {
		synchronized (lock1) {
			System.out.println("Deadlocker holding lock 1");
			synchronized (lock2) {
				System.out.println("Deadlocker holding lock 1 and 2");
			}
		}

	}
}
