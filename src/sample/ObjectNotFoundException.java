package sample;

/**
 * TODO package level to allow further refactoring
 * this will be thrown when a lookup on the id has been
 * performed but cannot find the corresponding element
 * within the parent node.
 * Created by yuechuan on 08/07/14.
 */
class ObjectNotFoundException extends Exception {
    private String msg ;

    ObjectNotFoundException(){   }
    ObjectNotFoundException(String s){
        msg = s;
    }

    @Override public String getMessage(){
        return "current exception msg: " + msg +
                super.getMessage();
    }
}
