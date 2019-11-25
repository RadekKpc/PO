package agh.cs.po.lab3;

import agh.cs.po.lab2.MapDirection;
import agh.cs.po.lab2.MoveDirection;

public class OptionsParser {

    public static MoveDirection[] parse(String[] args){
        MoveDirection[] result = new MoveDirection[args.length];

        int a =0;

        for (int i =0;i<args.length; i++){
            switch (args[i]){
                case "f" :
                case "forward":
                    result[i]=MoveDirection.FORWARD;
                    a++;
                    break;
                case "b":
                case "backward":
                    result[i]=MoveDirection.BACKWARD;
                    a++;
                    break;
                case "l":
                case "left":
                    result[i]=MoveDirection.LEFT;
                    a++;
                    break;
                case "r":
                case "right":
                    result[i]=MoveDirection.RIGHT;
                    a++;
                    break;
                default:
                    throw new IllegalArgumentException(args[i] + " is not legal move specification");

            }
        }
        MoveDirection[] result2 = new MoveDirection[a];
        a=0;
        for (int i =0;i<args.length; i++){
            if(result[i] != null){
                result2[a]=result[i];
                a++;
            }
        }
        return result2;
    }
}
