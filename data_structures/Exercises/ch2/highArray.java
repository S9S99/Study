// highArray.java
// demonstrates array class with high-level interface
////////////////////////////////////////////////////////////////
class HighArray {
    private long[] a;
    private int nElems;

    public HighArray(int max){
        a = new long[max];
        nElems = 0;
    }

    public boolean find(long searchKey) {
        int j;
        for(j=0; j<nElems; j++)
            if(a[j] == searchKey)
                break;
            if(j == nElems)
                return false;
            else
                return true;
    }

    public void insert(long value) {
        a[nElems] = value;
        nElems++;
    }

    public boolean delete(long value) {
        int j;
        for(j=0; j<nElems; j++)
            if( value == a[j] )
                break;
        if(j==nElems)
            return false;
        else {
            for(int k=j; k<nElems; k++)
                a[k] = a[k+1];
            nElems--;
            return true;
        }
    }

    public void display() {
        for(int j=0; j<nElems; j++)
            System.out.print(a[j] + " ");
        System.out.println("");
    }

    // Programming Projects 2.1
    // get highest key in the array
    public int getMax(){
        if(a.length == 0)
            return -1;

        int maxIndex = 0;
        long maxVaule = a[maxIndex];
        for(int index = 0 ; index < a.length ; index++){
            if(a[index] >= maxVaule){
                maxIndex = index;
                maxVaule = a[index];
            }
        }

        return maxIndex;   
    }

    // Programming Projects 2.2
    // get highest value in the array and delete
    public long removeMax(){
        if(a.length == 0)
            return -1;

        int maxIndex = 0;
        long maxVaule = a[maxIndex];
        for(int index = 0 ; index < a.length ; index++){
            if(a[index] >= maxVaule){
                maxIndex = index;
                maxVaule = a[index];
            }
        }

        for(int k=maxIndex; k<nElems; k++){
            a[k] = a[k+1];
        }
        nElems--;

        return maxVaule;
    }

    // Programming Projects 2.6
    // remove all duplicates from the array
    public void noDups(){
        int length = nElems;
        int i;
        int j;
        for(i=0; i<length; i++){
            for(j=i+1; j<length; j++){
                if(a[i] == a[j]){
                    for(int k=j; k<nElems; k++)
                        a[k] = a[k+1];
                    nElems--;
                    length--;
                    j--;
                }
            }
        }
    }
}

class HighArrayApp {
    public static void main(String[] args){

        int maxSize = 100;
        HighArray arr;
        arr = new HighArray(maxSize);
        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);
        arr.display();
        int searchKey = 35;
        
        if( arr.find(searchKey) )
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);
        
        arr.delete(00);
        arr.delete(55);
        arr.delete(99);
        arr.display();

        System.out.println("Index in maxVaule is " + arr.getMax());

        System.out.println("Delete " + arr.removeMax());

        arr.display();
        
        // Programming Projects 2.3
        // Inversely sort with removeMax()
        HighArray sortedArr;
        sortedArr = new HighArray(maxSize);
        System.out.println("sortedArr");
        while(true){
            long maxVaule = arr.removeMax();
            if(maxVaule == 0)
                break;
            sortedArr.insert(maxVaule);
        }
        sortedArr.display();

        sortedArr.insert(17);
        sortedArr.insert(17);
        sortedArr.insert(17);
        sortedArr.insert(77);

        sortedArr.display();

        sortedArr.noDups();
        sortedArr.display();

    }
}