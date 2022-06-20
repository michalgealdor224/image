public class Stam {
    public static void main(String[] args) {
        Stam stam = new Stam();
        System.out.println(stam.validName("pnina kadosh"));



    }
    public String validName(String name) {
        String newName = "";
        capitalLetters(name);
        String[] arr = name.split(" ");
        for(int i = 0; i<arr.length;i++) {
            capitalLetters(arr[i]);
            newName = newName + " " + capitalLetters(arr[i]);

        }
        System.out.println(newName.charAt(0) + "0");
        System.out.println(newName.charAt(1) + "1");
        newName = newName.substring(1);
        return newName;
    }

    public String capitalLetters (String name) {
        char first = name.charAt(0);
        char x =Character.toUpperCase(first);
        name = x + name.substring(1);
        return name;
    }
}
