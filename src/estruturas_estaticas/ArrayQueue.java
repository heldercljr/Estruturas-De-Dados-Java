package estruturas_estaticas;

import interfaces.Queue;

/**
 * Classe que implementa a interface Fila, porém com a utilização de arrays, de
 * modo que seu tamanho é fixo a partir da criação.
 * 
 * @param <T> o tipo que a fila terá.
 *
 * @author Helder Chaves Leite Junior
 * @see Queue
 */
public class ArrayQueue<T> implements Queue<T>
{
	/**
	 * O array que irá armazenar a fila de elementos.
	 */
	private T[] queue;
	
	/**
	 * O inteiro que representa o índice do fim da fila.
	 */
	private int tail;
	
	/**
	 * A constante que representa a capacidade padrão da lista.
	 */
	private static final int DEFAULT_CAPACITY = 20;
	
	/**
	 * Método que cria uma fila com base em uma capacidade especificada.
	 * 
	 * @param length a capacidade total que a fila terá.
	 * @throws IllegalArgumentException caso a capacidade especificada não seja
	 *                                  válida.
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue(int length) throws IllegalArgumentException
	{
		if (length <= 0)
			throw new IllegalArgumentException("Illegal Capacity: " + length);
		
		this.queue = (T[]) new Object[length];
		this.tail = -1;
	}
	
	/**
	 * Método que cria uma fila com a capacidade padrão.
	 */
	public ArrayQueue()
	{
		this(DEFAULT_CAPACITY);
	}
	
	@Override
	public void push(T element) throws RuntimeException
	{
		if (element != null)
		{
			if (this.isFull())
				throw new RuntimeException("FullQueueException");
			
			this.queue[++this.tail] = element;
		}
	}

	@Override
	public T pop() throws RuntimeException
	{
		if (this.isEmpty())
			throw new RuntimeException("EmptyQueueException");
		
		T removed = this.queue[0];
		
		for (int i = 0; i < this.tail; i++)
			this.queue[i] = this.queue[i + 1];
		
		this.tail--;
		
		return removed;
	}

	@Override
	public T head()
	{
		if (this.isEmpty())
			return null;
		else
			return this.queue[0];
	}

	@Override
	public int size()
	{
		return this.tail + 1;
	}

	@Override
	public boolean isEmpty()
	{
		return this.tail == -1;
	}

	@Override
	public boolean isFull()
	{
		return this.tail == (this.queue.length - 1);
	}
}
