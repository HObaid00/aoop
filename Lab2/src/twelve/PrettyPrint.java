package twelve;

public class PrettyPrint<T> implements TreeVisitor<T, String, String> {

    private int depth = 0;

    @Override
    public String visit(Leaf<T> leaf, String val){
        depth++;
        for (int i = 2; i <= depth; i++) {
            val += "|   ";   
        }
        val +=leaf.getValue().toString() + "\n";
        depth--;
        return val;
    }

    @Override
    public String visit(Node<T> node, String val) {
        depth++;
        for(int i = 2; i <= depth; i++){
            val += "|   ";
        }
        val += node.getValue().toString() + '\n';
        String s = "";
        for(Tree<T> tree : node.getChildren()){
            val += tree.accept(this, s);
        }
        val += s;
        depth--;
        return val;
    }

}
