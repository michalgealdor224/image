public class Stam {
    public static void main(String[] args) {
        Stam stam = new Stam();
        stam.getProfile();

    }
    public void getProfile() {
        String name = "pnina kadosh";
        String masho="";
        for (int i = 0; i < name.length(); i++) {
            if ((!Character.isLetter(name.charAt(i)))) {
               masho  = name.substring(0,i) + name.substring(i+1);
            }
        }
        System.out.println(masho);
    }
}
