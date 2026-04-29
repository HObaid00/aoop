
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.*;


public class Saver<T> {

    private int depth = 0;

    /**
     * Creates the correct depth.
     * @return String s that has the correct depth.
     */
    private String addDepth(){
        String s = "";
        for(int i = 0; i < depth; i++)
            s += "\t";
        return s;
    }

    /**
     * Function save requires that the given object has implemented the following 
     * interfaces: Element, SubElement and ElementField.
     * Function sets the given objects properties to the corresponding XML attributes
     * in String format. Ready to be used.
     * @param o is the object we use to extract attributes from.
     * @return XML string representation of the object that was given.
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public String save(Object o) throws NoSuchMethodException, SecurityException,
        IllegalAccessException, IllegalArgumentException, InvocationTargetException{

        // Methods from the given Class
        Method[] methods = o.getClass().getDeclaredMethods(); 
        Element el = o.getClass().getAnnotation(Element.class);
        SubElements sub = null;
        ElementField field = null;
        Object[] children = null;
        Object value = null;

        // Extract the given elements needed from the method
        for(Method m : methods){
            if(m.isAnnotationPresent(SubElements.class)){
                children = (Object[])m.invoke(o); // get the objects sub-elements
                sub = m.getAnnotation(SubElements.class);
            }
            else if(m.isAnnotationPresent(ElementField.class)){
                value = m.invoke(o);  // get the value of the element
                field = m.getAnnotation(ElementField.class);
            }
        }

        // Making the String to be used
        String xml = addDepth() + "<" + el.name() + " " + field.name() +  "=\"" + value + "\">\n";
        if(children != null && children.length != 0) {
            depth++;
            xml += addDepth() + "<" + sub.name() + ">\n";
            depth++;
            // Recursion for the given sub-elements
            for(Object child : children){
                xml += save(child);
            }
            depth--;
            xml += addDepth() + "</" + sub.name() +">\n";
            depth--;
            xml += addDepth() + "</" + el.name() + ">\n";
        }
        return xml;
    }
}