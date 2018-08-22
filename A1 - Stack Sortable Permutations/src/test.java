public class test {
	
	public boolean isStackSortablePermutation(myqueue input) {
		int elem;
		mystack S = new mystack();
		myqueue Q = new myqueue();
		while (input.isEmpty()==false){
			elem = input.dequeue();
			while ((S.isEmpty()!=true)&&(elem>S.getElementAtTopOfStack())){
				int a=S.pop();
				Q.enqueue(a);
			} 
			S.push(elem);
		}
		while (S.isEmpty()==false){
			elem = S.pop();
			Q.enqueue(elem);
		}
		return (Q.isSorted());  
	}

}
