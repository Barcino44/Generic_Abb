package datastructures;
public interface IBinarySearchTree<K, T> {
	// For testing purposes only in this exercise.
	public T getRoot();
	public T search(K key);
	public void insert(K key, T value);
	public T delete(K key);
	public String inOrder();
}
