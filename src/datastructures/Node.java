package datastructures;
public class Node<K,T> {

    private K key;
    private T value;
    //Datos

    //Enlaces
    private Node<K,T> right;
    private Node<K,T> left;

    public Node(K key,T value) {
        this.key = key;
        this.value =value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public Node<K,T> getRight() {
        return right;
    }

    public void setRight(Node<K,T> right) {
        this.right = right;
    }

    public Node<K,T> getLeft() {
        return left;
    }

    public void setLeft(Node<K,T> left) {
        this.left = left;
    }
}
