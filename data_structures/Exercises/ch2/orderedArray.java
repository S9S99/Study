// orderedArray.java
// demonstrates ordered array class
////////////////////////////////////////////////////////////////
class OrdArray {
    private long[] a;
    private int nElems;

    public OrdArray(int max){
        a = new long[max];
        nElems = 0;
    }

    public int size() {
        return nElems;
    }

    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems-1;
        int curIn;
        while(true) {
            curIn = (lowerBound + upperBound ) / 2;
            if(a[curIn]==searchKey)
                return curIn;
            else if(lowerBound > upperBound)
                return nElems;
            else {
                if(a[curIn] < searchKey)
                    lowerBound = curIn + 1;
                else
                    upperBound = curIn - 1;
            }
        }
    }

    public void insert(long value) {
        // Programming Projects 2.4
        // insert() use binary search
        int j = 0;
        int lowerBound = 0;
        int upperBound = nElems-1;
        int curIn;
        while(nElems > 2) {
            curIn = (lowerBound + upperBound ) / 2;
            if(a[curIn]==value){
                j = curIn + 1;
                break;
            }
            else if(lowerBound > upperBound){
                j = lowerBound;
                break;
            }
            else {
                if(a[curIn] < value)
                    lowerBound = curIn + 1;
                else
                    upperBound = curIn - 1;
            }
        }
        if(nElems == 1 && value > a[0]){
            j = 1;
        } else if(nElems == 2){
            if(value < a[0]){
                j = 0;
            } else if(value < a[1]){
                j = 1;
            } else {
                j = 2;
            }
        }
        //for(j=0; j<nElems; j++)
        //    if(a[j] > value)
        //        break;
        for(int k=nElems; k>j; k--)
            a[k] = a[k-1];
        a[j] = value;
        nElems++;
    }

    public boolean delete(long value) {
        int j = find(value);
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

    // Programming Projects 2.5
    // two ordered source arrays into an ordered destination array
    public long getValue(int index) {
        return a[index];
    }
    public void setValue(int index, long value) {
        a[index] = value;
    }
    public static OrdArray merge(OrdArray a, OrdArray b) {
        int arrLength = a.size() + b.size();
        OrdArray result = new OrdArray(arrLength*2);
        
        int i = 0;
        int j = 0;
        int k = 0;
        
        while(j < a.size() && k < b.size()) {
            if(a.getValue(j) <= b.getValue(k)) {
                result.setValue(i, a.getValue(j));
                j++;
            } else {
                result.setValue(i, b.getValue(k));
                k++;
            }
            result.nElems++;
            i++;
        }

        while(j < a.size()) {
            result.setValue(i, a.getValue(j));
            result.nElems++;
            i++;
            j++;
        }
        
        while(k < b.size()) {
            result.setValue(i, b.getValue(k));
            result.nElems++;
            i++;
            k++;
        }

        return result;
    }
}


class OrderedApp {
    public static void main(String[] args) {
        int maxSize = 100;
        OrdArray arr;
        arr = new OrdArray(maxSize);
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
        int searchKey = 55;
        if( arr.find(searchKey) != arr.size() )
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);
        arr.display();
        arr.delete(00);
        arr.delete(55);
        arr.delete(99);
        arr.display();

        OrdArray arr2 = new OrdArray(maxSize);
        long random = 0;
        for(int i = 0; i < 10; i++) {
            random = (long) (Math.random()*100);
            arr2.insert(random);
        }
        arr2.display();

        OrdArray mergeArr = OrdArray.merge(arr, arr2);
        System.out.print("merge result: ");
        mergeArr.display();
    }
}