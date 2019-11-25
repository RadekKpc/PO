package agh.cs.po.lab1;

public enum Direction {
    FORWARD,BACK,LEFT,RIGHT;

    public static Direction[] fromStringArray(String[] args){

        Direction[] result = new Direction[args.length];

        for (int i =0;i<args.length; i++){
            switch (args[i]){
                case "f":
                    result[i]=FORWARD;
                    break;
                case "b":
                    result[i]=BACK;
                    break;
                case "l":
                    result[i]=LEFT;
                    break;
                case "r":
                    result[i]=RIGHT;
                    break;
                //no need for default

            }
        }

        return result;
    }
}
