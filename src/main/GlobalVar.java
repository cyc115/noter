package main;

import java.io.File;

/**
 * Created by yuechuan on 19/07/14.
 */
public class GlobalVar {
    private static File currentFile ;

    /**
     * set the working file that context sensative operation
     * will work on
     * @param f
     */
    public static void setCurrentWorkingFile(File f){
        if (f != null){
            currentFile = f;
        }
        else {
            throw new IllegalArgumentException("cannot set currentFile as null");
        }
    }

    /**
     * get the working file
     */
    public static File getCurrentWorkingFile(){
        return currentFile;
    }

}
