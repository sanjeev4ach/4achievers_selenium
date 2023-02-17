package threadlocal;

public class ThreadLocalDemo {
	
	private static ThreadLocal<String> atm = new ThreadLocal<>();

	public static String getMoney() {
		return atm.get();
	}

	public static void depositeMoney(String money) {
		atm.set(money);
	}
	
	public static void closeAccount() {
		atm.remove();
	}
	

}
