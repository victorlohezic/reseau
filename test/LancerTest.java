public class LancerTest {
    public static void lance(Class<?> c) throws Exception {
        java.lang.reflect.Method[] mesMethodes = c.getMethods();

        int nbTest = 0;
        for (int i = 0; i < mesMethodes.length; i++) {
            java.lang.reflect.Method m = mesMethodes[i];

            if (m.getName().startsWith("test")) {
                System.out.print(".");
                try {
                    m.invoke(c.getDeclaredConstructor().newInstance());
                    nbTest++;
                } catch(java.lang.reflect.InvocationTargetException e) {
                    throw new Exception(e.getCause());
                } catch(ReflectiveOperationException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        System.out.println("\n OK " + nbTest + " test(s) valide(s) " + c.getName());
    }

    public static void main(String[] args) throws Exception {
        boolean estMisAssertion = false;
        assert estMisAssertion = true;
    
        if (!estMisAssertion) {
          System.out.println("Execution impossible sans l'option -ea");
          return;
        }

        for (int i = 0; i < args.length; ++i) {
            Class<?> c = Class.forName(args[i]);
            lance(c);
        }
    }
}
