import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Command {
    public enum Type{
        READ, WRITE
    }
    public Type type;
    public ArrayList<String> CI;

    public Command(String string, ArrayList<String> CI){
        if(string.contentEquals("Read")){
            this.type = Type.READ;
        }
        else if(string.contentEquals("Write")){
            this.type = Type.WRITE;
        }
        this.CI = CI;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Command c)) return false;
        if(this.type!=c.type) return false;
        for(int i=0; i<8; i++){
            if(!this.CI.get(i).contentEquals(c.CI.get(i))) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type.toString()).append("\n");
        for(int i = 0; i<8; i++) {
            sb.append(CI.get(i)).append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
