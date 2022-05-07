
public class temp {

	public static void main(String[] args) {
		for(int i = 1; i <= 10; i++) {
			String[] aux = new String[1];
			aux[0] = "ejemplo"+ Integer.toString(i) + ".txt";
			try {
				constructorast.Main.main(aux);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
