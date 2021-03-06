import java.util.*;
@SuppressWarnings("unchecked")
public class MyHeap<T extends Comparable<T>>{
    private int size;
    private T[] data;
    private boolean isMax;

    public MyHeap(){
	data = (T[]) new Comparable[10];
    }
   
    public MyHeap(T[] array){
	data = (T[]) new Comparable[array.length+1];
	for (int x = 0; x < array.length;x++){
	    data[x+1] = array[x];
	}
	size = array.length;
	heapify();
    }

    public MyHeap(boolean Max){
	this();
        isMax = Max;
	heapify();
    }
    
    public MyHeap(T[] array, boolean Max){
	this(array);
        isMax = Max;
	heapify();
    }

    /*
    public boolean getMax(){
	return isMax;
    }
    */
    
    private void swap(int a, int b){
	T temp = data[a];
	data[a] = data[b];
	data[b] = temp;
    }

    /*pushDown
      precondition: datas[k]'s children are valid heaps
      postconditions:-the element at index k has been 
                     shifted to the correct spot.
                     -data[k] and is a valid heap
   */    
    private void pushDown(int index){
	if (index*2+1 < size){
	    if (isMax){
		if (data[index*2].compareTo(data[index]) < 0){
		    swap(index,index*2);
		    pushDown(index*2);
		}
		if (data[index*2+1].compareTo(data[index]) > 0){
		    swap(index,index*2+1);
		    pushDown(index*2+1);
		}
	    }else{
		if (data[index*2].compareTo(data[index]) < 0){
		    swap(index,index*2);
		    pushDown(index*2);
		}
		if (data[index*2+1].compareTo(data[index]) < 0){
		    swap(index,index*2+1);
		    pushDown(index*2+1);
		}
	    }
	}
    }

    /*pushUp
      precondition: data is a heap with at most one item
      out of place (element at k)
      postconditions:-the element at index k has been 
                     shifted to the correct spot.
                     -data is a valid heap
   */
    private void pushUp(int index){
	if (index/2 > 0){
	    if (isMax){
		if (data[index/2].compareTo(data[index]) < 0){
		    swap(index,index/2);
		    pushUp(index/2);
		}
	    }else{
		if (data[index/2].compareTo(data[index]) > 0){
		    swap(index,index/2);
		    pushUp(index/2);
		}
	    }
	}
    }

    private void heapify(){
	int index = size/2;
	while (index > 0){
	    pushDown(index);
	    index--;
	}
	index = size/2;
	while (index < size){
	    pushUp(index);
	    index++;
	}
    }

    public T delete(){
	T temp;
	if (size == 0){
	    throw new NoSuchElementException();
	}else if (size == 1){
	    temp = data[1];
	    data[size] = null;
	    return null;
	}else{
	    temp = data[1];
	    data[1] = data[size];
	    data[size] = null;
	    pushDown(1);
	}
	size--;
	return temp;
    }
   
    public void add(T x){
	if (size == 0){
	    data[1] = x;
	}else{
	    if (size == data.length-1){
		doubleSize();
	    }
	    data[size+1] = x;
	    pushUp(size+1);
	}
	size++;
    }

    public int size(){
	return size;
    }

    public T peek(){
	if (size == 0){
	    throw new NoSuchElementException();
	}
	return data[1];
    }
   
    private void doubleSize(){
	T[] temp = (T[])new Comparable[size*2+1];
	for (int x = 0; x < data.length; x++){
	    temp[x] = data[x];
	}
	data = temp;
    }
   
    public String toString(){
	String ans = "[";
	for (int x = 0; x < data.length; x++){
	    if (x == data.length-1){
		ans+= data[x];
	    }else{
		ans += data[x] + ", ";
	    }
	}
	return ans + "]";
    }

    public static void main(String[]args){
	
        Integer[] array = {1,5,24,8,15,3,8,8,10,13,6,30,17,30,28};
	MyHeap<Integer> test = new MyHeap<Integer>(array);
	System.out.println(test);
	
	
	MyHeap<Integer> test2 = new MyHeap();
	//System.out.println(test2.peek());
	//test2.delete();
	test2.add(4);
	System.out.println(test2);
	test2.add(9);
	test2.add(-2);
	test2.add(14);
	test2.add(10);
	test2.add(20);
	test2.add(5);
	test2.add(12);
	test2.add(0);
	test2.add(7);
	test2.add(19);
	test2.add(24);
	test2.add(-10);
	test2.add(29);
	test2.add(13);
        System.out.println(test2);
	test2.delete();
	System.out.println(test2);
	System.out.println(test2.peek());
	
	
	MyHeap<Integer> test3 = new MyHeap<Integer>(array,true);
	//System.out.println(test3.getMax());
	System.out.println(test3);
	System.out.println(test3.delete());
	System.out.println(test3.delete());
	System.out.println(test3);
    }

}
