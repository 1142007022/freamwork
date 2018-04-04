import java.util.Scanner;

public class Game {
    int num = (int)(Math.random()*10);
    int errorTime = 0;
     public void startUp(){
         System.out.println("请输入一个数字：");
         Scanner input = new Scanner(System.in);
         int index = input.nextInt();
         if(index == num) {
             System.out.println("猜对了！这个数就是："+num);
             if(errorTime > 3) {
                 System.out.println("您一共尝试了"+errorTime+"次，罚三杯");
             }
         }else{
             System.out.println("猜错了，再试试！");
             errorTime++;
             startUp();
         }
     }

     public static void main(String[] agrs){
         Game game = new Game();
         game.startUp();
     }

}
