package estruturas_dinamicas;

import java.util.ArrayList;

import interfaces.Tree;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T>
{
	private TreeNode<T> root;

	public BinarySearchTree()
	{
		this.root = null;
	}

	@Override
	public boolean isEmpty()
	{
		return this.root.isEmpty();
	}

	@Override
	public int size()
	{
		return this.size(this.root);
	}

	private int size(TreeNode<T> node)
	{
		if (node.isEmpty())
			return 0;
		
		return 1 + this.size(node.left) + this.size(node.right);
	}

	@Override
	public int height()
	{
		return this.height(this.root);
	}

	private int height(TreeNode<T> node)
	{
		if (node.isEmpty())
			return -1;
		
		return 1 + Math.max(this.height(node.left), this.height(node.right));
	}

	@Override
	public void insert(T element)
	{
		if (element != null)
			this.insert(element, this.root, null);
	}

	private void insert(T element, TreeNode<T> node, TreeNode<T> nodeParent)
	{
		if (node.isEmpty())
		{
			node = new TreeNode<T>(element);
			node.parent = nodeParent;
		}
		else if (element.compareTo(node.data) < 0)
			this.insert(element, node.left, node);
		else if (element.compareTo(node.data) > 0)
			this.insert(element, node.right, node);
	}

	@Override
	public void remove(T element)
	{
		
	}

	public TreeNode<T> search(T element)
	{
		return this.search(element, this.root);
	}

	private TreeNode<T> search(T element, TreeNode<T> node)
	{
		if (node.isEmpty() || element == null)
			return null;
		
		if (node.data.compareTo(element) == 0)
			return node;
		else if (node.data.compareTo(element) < 0)
			return this.search(element, node.left);
		else
			return this.search(element, node.right);
	}

	public TreeNode<T> minimum()
	{
		if (this.root.isEmpty())
			return null;
		
		return this.minimum(this.root);
	}

	private TreeNode<T> minimum(TreeNode<T> node)
	{
		if (node.left.isEmpty())
			return node;
		
		return this.minimum(node.left);
	}

	public TreeNode<T> maximum()
	{
		if (this.root.isEmpty())
			return null;
		
		return this.maximum(this.root);
	}

	private TreeNode<T> maximum(TreeNode<T> node)
	{
		if (node.right.isEmpty())
			return node;
		
		return this.maximum(node.right);
	}

	@Override
	public int countLeaves()
	{
		return this.countLeaves(this.root);
	}

	private int countLeaves(TreeNode<T> node)
	{
		if (node.isEmpty())
			return 0;
		
		if (node.hasDegreeZero())
			return 1;
		
		return this.countLeaves(node.left) + this.countLeaves(node.right);
	}

	@SuppressWarnings("unchecked")
	public T[] preOrder()
	{
		ArrayList<T> arrayList = new ArrayList<T>();
		
		this.preOrder(arrayList, this.root);
		
		return arrayList.toArray((T[]) new Comparable[arrayList.size()]);
	}

	private void preOrder(ArrayList<T> arrayList, TreeNode<T> node)
	{
		if (!node.isEmpty())
		{
			arrayList.add(node.data);
			this.preOrder(arrayList, node.left);
			this.preOrder(arrayList, node.right);
		}
	}

	@SuppressWarnings("unchecked")
	public T[] inOrder()
	{
		ArrayList<T> arrayList = new ArrayList<T>();
		
		this.inOrder(arrayList, this.root);
		
		return arrayList.toArray((T[]) new Comparable[arrayList.size()]);
	}

	private void inOrder(ArrayList<T> arrayList, TreeNode<T> node)
	{
		if (!node.isEmpty())
		{
			this.preOrder(arrayList, node.left);
			arrayList.add(node.data);
			this.preOrder(arrayList, node.right);
		}
	}

	@SuppressWarnings("unchecked")
	public T[] postOrder()
	{
		ArrayList<T> arrayList = new ArrayList<T>();
		
		this.postOrder(arrayList, this.root);
		
		return arrayList.toArray((T[]) new Comparable[arrayList.size()]);
	}

	private void postOrder(ArrayList<T> arrayList, TreeNode<T> node)
	{
		if (!node.isEmpty())
		{
			this.preOrder(arrayList, node.left);
			this.preOrder(arrayList, node.right);
			arrayList.add(node.data);
		}
	}
}

class TreeNode<T>
{
	TreeNode<T> parent;
	TreeNode<T> left;
	TreeNode<T> right;
	T data;

	TreeNode(T data)
	{
		this.data = data;
	}

	boolean isEmpty()
	{
		return this == null;
	}

	boolean hasParent()
	{
		return !this.parent.isEmpty();
	}

	boolean hasDegreeZero()
	{
		return this.left.isEmpty() && this.right.isEmpty();
	}

	boolean hasDegreeOne()
	{
		return this.left.isEmpty() ^ this.right.isEmpty();
	}

	boolean hasDegreeTwo()
	{
		return !this.left.isEmpty() && !this.right.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean result = false;
		
		if (obj instanceof TreeNode)
		{
			if (!this.isEmpty() && !((TreeNode<T>) obj).isEmpty())
				result = this.data.equals(((TreeNode<T>) obj).data);
			else
				result = this.isEmpty() && ((TreeNode<T>) obj).isEmpty();
		}
		
		return result;
	}
}
