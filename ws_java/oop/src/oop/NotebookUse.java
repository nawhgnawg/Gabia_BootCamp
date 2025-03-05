package oop;

public class NotebookUse {

    public static void main(String[] args) {
        Notebook notebook = new Notebook();
        
        notebook.name = "LG i7";
        notebook.price = 1_670_000;
        notebook.screen = 17.3;
        notebook.cpu = "Core i7 12세대";
        
        System.out.println("제품명: " + notebook.name);
        System.out.println("가격: " + String.format("%,d", notebook.price));
        System.out.println("화면: " + notebook.screen);
        System.out.println("CPU: " + notebook.cpu);
        
        
    }

}
