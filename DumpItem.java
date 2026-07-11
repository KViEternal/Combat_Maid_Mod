
public class DumpItem {
    public static void main(String[] args) throws Exception {
        Object item = net.minecraft.item.Items.DIAMOND_CHESTPLATE;
        System.out.println("Class: " + item.getClass().getName());
        System.out.println("Superclass: " + item.getClass().getSuperclass().getName());
    }
}
