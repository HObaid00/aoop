import java.lang.reflect.InvocationTargetException;

@Element(name = "node")
public class Tree<T> {
    private Tree<T>[] children = null;
    private T value;

    public Tree(T v, Tree<T>[] trees){
        children = trees;
        value = v;
    }

    public Tree(T v){
        value = v;
    }

    @SubElements(name = "subnodes")
    public Tree<T>[] getChildren(){
        return children;
    }

    @ElementField(name = "value")
    public T getValue(){
        return value;
    }

    public static void main(String[] args) throws NoSuchMethodException, 
        SecurityException, IllegalAccessException, IllegalArgumentException, 
        InvocationTargetException{
        
        @SuppressWarnings("unchecked")
        Tree<String> t = new Tree<String>("top",
             new Tree[]{
                new Tree<String>("sub1", 
                    new Tree[]{
                        new Tree<String>("subsub1"), 
                        new Tree<String>("subsub2")
                    }),
                new Tree<String>("sub2")
             });
        Saver<Tree<String>> s = new Saver<Tree<String>>();
        String r = s.save(t);
        System.out.println(r);
    }
}