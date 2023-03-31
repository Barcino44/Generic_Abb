package datastructures;

public class BinarySearchTree<K extends Comparable<K>,T> implements IBinarySearchTree<K,T>{
    private Node<K, T> root;
    @Override
    public T getRoot() {
        if (root == null) {
            return null;
        } else {
            return root.getValue();
        }
    }
    @Override
    public void insert(K key, T value){
        if(root == null){
            root = new Node<>(key, value);
        }else{
            Node<K,T> node = new Node<>(key, value);
            insert(root, node);
        }
    }
    public void insert( Node<K,T> current, Node<K,T> newNode) {
        if(newNode.getKey().compareTo(current.getKey()) < 0){ //Si el nodo entrante comparado con el nodo actual, es menor, lo ponemos a la izq
            //Meter a la izquierda
            if(current.getLeft() == null){ //Aqui es si el nodo de la izquierda esta vacia, si es a si, colocamos el nodo nuevo.
                current.setLeft(newNode);
            }else{
                insert(current.getLeft(), newNode); //Si no, vamos a la izq y volvemos a hacer el llamado al metodo.
            }
        }else if(newNode.getKey().compareTo(current.getKey()) > 0){ //Si el nodo entrante comparado con el actual es mayor, lo ponemos a la der.
            //Meter a la derecha
            if(current.getRight() == null){ //Si la derecha del actual esta vacio, colocamos el nodo alli.
                current.setRight(newNode);
            }else{
                insert(current.getRight(), newNode);//Si no, vamos a la der y volvemos a hacer el llamado al metodo.
            }
        }else{
            //No hacer nada
        }
    }
    @Override
    public T search(K key){
        return search(root, key);
    }
    private T search(Node <K,T> current, K key){
        if(current == null){ //Si el nodo actual ==nulo, es porque se recorrió todo el arbol y no se encontro nada.
            System.out.println("Not found");
            return null;
        }
        if(current.getKey().equals(key)){//Si el identificador del nodo actual = al identificador que estamos buscando, que me retorne el nodo.
            return current.getValue();
        }

        if(key.compareTo(current.getKey()) < 0){//Si el objetivo es menor que el identificador del nodo actual, dirijamos a la izq (llamado recursivo)
            return search(current.getLeft(), key);
        }else{
            return search(current.getRight(), key); //Si no es por que el objetivo es mayor que el identificador actual, dirijamonos a la der (llamado recursivo)
        }
    }
    public Node<K,T> getMin(){ //Obtener el valor minimo de un nodo.
        return getMin(root);
    }

    private Node<K,T> getMin(Node<K,T> current){
        if(current.getLeft() == null){ //Si el nodo a la izquierda es nulo, es por que ese nodo es el valor minimo.
            return current; //Retorno el nodo
        }
        return getMin(current.getLeft()); //Si no ocurre el caso base, hago recursividad.
    }

    public Node<K,T> getMax(){
        return getMax(root);
    }

    private Node<K,T> getMax(Node<K,T> current){ //Si el nodo a la derecha es nulo, es porque ese nodo es el valor minimo.
        if(current.getRight() == null){
            return current;
        }
        return getMax(current.getRight()); //Soy recursivo si no ocurre el caso base.
    }

    @Override
    public T delete(K key){
        return delete(null,root,key);
    }
    private T delete(Node<K,T> parent,Node<K,T> current,K key) {
        if (current == null) {
            return null;
        }
        if (current.getKey().equals(key)) {//Si el identificador del nodo actual = al identificador que estamos buscando, que me retorne el nodo.
            //Es un nodo hoja
            if (current.getLeft() == null && current.getRight() == null) {
                //Solo esta la raiz.
                if (current == root) {
                    root = null;
                }
                //Si current es el hijo izquierdo del padre.
                else if (parent.getLeft() == current) {
                    parent.setLeft(null);
                }
                //Si current es el hijo derecho del padre.
                else {
                    parent.setRight(null);
                }
            }
            //Eliminar nodo con hijo derecho
            else if (current.getRight() != null && current.getLeft() == null) {
                if (current == root) {
                    root = root.getRight();
                } else if (parent.getLeft() == current) { //Si el nodo a borrar esta a la izquierda del padre
                    parent.setLeft(current.getRight()); //Seteamos la izquierda del padre con la derecha del nodo
                    return parent.getLeft().getValue();
                } else {
                    parent.setRight(current.getRight());
                    return parent.getRight().getValue();

                }
            }
            //Eliminar nodo con hijo izquierdo
            else if (current.getLeft() != null && current.getRight() == null) {
                if (parent.getLeft() == current) {
                    parent.setLeft(current.getLeft());
                    return parent.getLeft().getValue();
                } else {
                    parent.setRight(current.getLeft());
                    return parent.getRight().getValue();
                }
            }
            //Eliminar un nodo que tiene 2 hIjos
            else if (current.getLeft() != null && current.getRight() != null) {
                Node<K, T> sucesor = getMin(current.getRight());
                //Sobreescribir la key y los valores
                T valueCurrent=current.getValue();
                T valueSucesor=sucesor.getValue();
                current.setKey(sucesor.getKey());
                current.setValue(valueSucesor);
                sucesor.setValue(valueCurrent);
                //Si tienen valores colocar los valores tambien
                delete(current, current.getRight(), sucesor.getKey());
                return sucesor.getValue();
            }
            //Recursivo
            else if (key.compareTo(current.getKey()) < 0) {//Si el objetivo es menor que el identificador del nodo actual, dirijamos a la izq (llamado recursivo)
                delete(current, current.getLeft(), key);

            } else {
                delete(current, current.getRight(), key);
            }
        }
        return current.getValue();
        }
    @Override
    public String inOrder(){ //Activador 1
        return delete();
    }
     private String delete(){ //Activador 2
        String tree=inOrder(root);
         if (!tree.equals("")) {
             tree=tree.substring(0,tree.length()-1);
         }
        return tree;
    }
    private String inOrder(Node<K,T> current) { //imprimir el arbol de menor a mayor.
        String tree = "";
        if (current != null) {
            tree+= inOrder(current.getLeft());//Como el menor es el de la izq, nos dirijimos a el, cuando no haya más izquierda, imprime, regresa a la rama principal y se va a la der, asi hasta terminar con todos los datos.
            tree += current.getKey() + " ";
            tree+=inOrder(current.getRight());
        }
        return tree;
    }
}
