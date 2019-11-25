package agh.cs.po.lab1;

import static java.lang.System.out;

public class CarSystem {
    //psvm + enter
    public static void main(String[] args) {
        //sout + enter
        out.println("START");
        run(Direction.fromStringArray(args));
        out.println("STOP");
    }
    public static void run(Direction[] args){

        for (Direction arg: args){

            if(arg == null){
                continue;
            }

            switch (arg){
                case FORWARD:
                    out.println("AUTO JEDZIE DO PRZODU!");
                    break;
                case BACK:
                    out.println("AUTO JEDZIE DO TYŁU!");
                    break;
                case LEFT:
                    out.println("AUTO SKRĘCA W LEWO!");
                    break;
                case RIGHT:
                    out.println("AUTO SKRĘCA W PRAWO!");
                    break;
                 //no need for break
            }
        }

    }
}



//static - metoda statyczna nie jest pzypisana do zadnego obiektu