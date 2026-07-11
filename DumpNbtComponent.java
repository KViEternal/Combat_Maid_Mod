
import java.lang.reflect.Method;
public class DumpNbtComponent {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("net.minecraft.component.type.NbtComponent");
        for (Method m : clazz.getMethods()) {
            System.out.println(m.getName() + " " + java.util.Arrays.toString(m.getParameterTypes()));
        }
    }
}
