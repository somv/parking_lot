package com.gojek.parkingSolution.models;

public class ParkingLot {

	private int[] slots; 
    private int size; 
    private int maxsize; 
  
    private static final int FRONT = 1; 
  
    public ParkingLot(int maxsize) 
    { 
        this.maxsize = maxsize; 
        this.size = 0; 
        slots = new int[this.maxsize + 1]; 
        slots[0] = Integer.MIN_VALUE; 
    } 
  
    // Function to return the position of  
    // the parent for the node currently  
    // at pos 
    private int parent(int pos) 
    { 
        return pos / 2; 
    } 
  
    // Function to return the position of the  
    // left child for the node currently at pos 
    private int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
  
    // Function to return the position of  
    // the right child for the node currently  
    // at pos 
    private int rightChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 
  
    // Function that returns true if the passed  
    // node is a leaf node 
    private boolean isLeaf(int pos) 
    { 
        if (pos >= (size / 2) && pos <= size) { 
            return true; 
        } 
        return false; 
    } 
  
    // Function to swap two nodes of the heap 
    private void swap(int fpos, int spos) 
    { 
        int tmp; 
        tmp = slots[fpos]; 
        slots[fpos] = slots[spos]; 
        slots[spos] = tmp; 
    } 
  
    // Function to heapify the node at pos 
    private void heapify(int pos) 
    { 
  
        // If the node is a non-leaf node and greater 
        // than any of its child 
        if (!isLeaf(pos)) { 
            if (slots[pos] > slots[leftChild(pos)] 
                || slots[pos] > slots[rightChild(pos)]) { 
  
                // Swap with the left child and heapify 
                // the left child 
                if (slots[leftChild(pos)] < slots[rightChild(pos)]) { 
                    swap(pos, leftChild(pos)); 
                    heapify(leftChild(pos)); 
                } 
  
                // Swap with the right child and heapify  
                // the right child 
                else { 
                    swap(pos, rightChild(pos)); 
                    heapify(rightChild(pos)); 
                } 
            } 
        } 
    } 
  
    // Function to insert a node into the heap 
    public void insert(int element) 
    { 
    	slots[++size] = element; 
        int current = size; 
  
        while (slots[current] < slots[parent(current)]) { 
            swap(current, parent(current)); 
            current = parent(current); 
        } 
    } 
  
    // Function to print the contents of the heap 
    public void print() 
    {   
        for(int i=0; i<= size; i++) {
        	System.out.print(slots[i]+" ");
        }
    } 
  
    // Function to build the min heap using  
    // the minHeapify 
    public void minHeap() 
    { 
        for (int pos = (size / 2); pos >= 1; pos--) {
            heapify(pos); 
        } 
    } 
  
    // Function to remove and return the minimum 
    // element from the heap 
    public int getMinSlot() 
    { 
        int popped = slots[FRONT]; 
        slots[FRONT] = slots[size--]; 
        heapify(FRONT); 
        return popped; 
    } 
  
    // Driver code 
    public static void main(String[] arg) 
    { 
        System.out.println("The Min Heap is "); 
        ParkingLot parkingLot = new ParkingLot(15); 
        for(int i=1; i< 10; i++) {
        	parkingLot.insert(i);
        }
        parkingLot.minHeap(); 
  
        parkingLot.print(); 
        System.out.println("The Min val is " + parkingLot.getMinSlot()); 
        System.out.println("The Min val is " + parkingLot.getMinSlot());
    } 
	
    public int getSize() {
    	return size;
    }
	
}
