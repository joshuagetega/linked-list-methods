/*
* Author: Joshua Getega, jgetega@gmail.com
* Methods to add to the LinkedIntList Class at https://practiceit.cs.washington.edu/problems/bjp3/chapter16/LinkedIntList.java
* Add to LinkedIntList Class to compile
* These are solutions to coding exercises in Ch. 16 of "Building Java Programs" by Stuart Reges and Marty Stepp
*/


//Reverses the order of the elements in the list
public void reverse() {
    if (front != null) {
        ListNode back = front;
        int sizeVal = 1;
        while (back.next != null) {
            back = back.next;
            sizeVal++;
        }
        int originalSizeVal = sizeVal;
        ListNode nodeToMove = front;
        while (sizeVal > 1) {
            front = front.next;
            nodeToMove.next = null;
            back.next = nodeToMove;
            nodeToMove = front;
            back = back.next;
            sizeVal--;
        }
        
        ListNode inPlace = front;
        nodeToMove = inPlace.next;
        int cycles = 1;
        sizeVal = originalSizeVal - cycles;
        
        while (sizeVal > 1) {
            while (sizeVal > 1) {
                inPlace.next = nodeToMove.next;
                nodeToMove.next = null;
                back.next = nodeToMove;
                nodeToMove = inPlace.next;
                back = back.next;
                sizeVal--;
            }
            inPlace = inPlace.next;
            nodeToMove = nodeToMove.next;
            cycles++;
            sizeVal = originalSizeVal - cycles;
        }
            
    }
}

/*
* Rearranges the elements of a list of integers by moving to the end of the list all values 
* that are in odd-numbered positions and otherwise preserving list order.
*/
public void shift() {
    if (front != null) {
        ListNode back = front;
        int sizeVal = 1;
        while (back.next != null) {
            back = back.next;
            sizeVal++;
        }

        if (sizeVal > 2) {
            ListNode prev = front;
            ListNode current = front.next;
            int cycles = sizeVal / 2;

            while (cycles > 0) {
                if (current.next != null) {
                    prev.next = current.next;
                    current.next = null;
                    back.next = current;
                    prev = prev.next;
                    current = prev.next;
                    back = back.next;
                }
                cycles--;    
            }
        }
    }
    
}

/*
* Moves the value at the front of a list of integers to the end of the list
*/
public void rotate() {
    if (front != null && front.next != null) {
        ListNode nodeToMove = front;
        front = front.next;
        nodeToMove.next = null;
        
        ListNode walker = front;
        while (walker.next != null) {
            walker = walker.next;
        }
        
        walker.next = nodeToMove;
    }
}

/*
* Doubles the size of a list by appending a copy of the original sequence to the 
* end of the list
*/
public void doubleList() {
    if (front != null) {
        int sizeVal = 1;
        ListNode appender = front;
        while (appender.next != null) {
            appender = appender.next;
            sizeVal++;
        }
        
        ListNode reader = front;
        
        while (sizeVal > 0) {
            appender.next = new ListNode(reader.data, null);
            appender = appender.next;
            reader = reader.next;
            sizeVal--;
        } 
    }
}

/*
* Accepts a starting and ending index as parameters and removes the elements at those 
* indexes (inclusive) from the list. Throws an IllegalArgumentException if either of 
* the positions is negative. Otherwise assumes that the positions represent a legal 
* range of the list
*/
public void removeRange(int startIndex, int endIndex) {
    if (startIndex < 0 || endIndex < 0) {
        throw new IllegalArgumentException();
    }
    
    ListNode beforeStart = front;
    int counter = 0;
    
    while (counter < startIndex - 1) {
        beforeStart = beforeStart.next;
        counter++;
    }
    
    counter = 0;
    ListNode afterEnd = front;
    
    while (counter < endIndex + 1) {
        afterEnd = afterEnd.next;
        counter++;
    }
    
    if (startIndex == 0) {
        front = afterEnd;
    } else {
        beforeStart.next = afterEnd;
    }     
}

/*
* Removes the values in even-numbered indexes from a list, returning a new list containing 
* those values in their original order.
*/
public LinkedIntList removeEvens() {
    int thisSize = this.size();
    
    LinkedIntList list2 = new LinkedIntList();
    list2.front = front;
    
    if (thisSize > 0) {
        
        front = front.next;

        ListNode list2Current = list2.front;
        ListNode list1Current = front;

        while (thisSize > 0) {
            if (list1Current.next == null) {
                break;
            }
            list2Current.next = list1Current.next;
            list2Current = list2Current.next;
            list1Current.next = null;
            if (list2Current.next == null) {
                break;
            }
            list1Current.next = list2Current.next;
            list1Current = list1Current.next;
            list2Current.next = null;
            thisSize -= 2;
        }
        
    }
    
    return list2;
}


/*
* Accepts a second list as a parameter.
* Returns true if the two lists are equal and false otherwise.
* Two lists are considered equal if they store exactly the same values in exactly the same 
* order and have exactly the same length.
*/
public Boolean equals2(LinkedIntList list2) {
    int thisSize = this.size();
    if (thisSize != list2.size()) {
        return false;
    }
    ListNode thisCurrent = this.front;
    ListNode list2Current = list2.front;
    while(thisSize > 0) {
        if (thisCurrent.data != list2Current.data) {
            return false;
        }
        thisSize--;
    }
    
    return true;
}

// Removes all occurrences of a particular value from the list
public void removeAll(int val) {
    if (front != null) {
        ListNode checker = front;
        while (front != null && front.data == val) {
            if (front.next != null) {
                checker = front.next;
                front = checker;
            } else {
                front = null;
            }          
        }
        if (front != null && front.next != null) {
            ListNode prev = checker;
            checker = checker.next;
            while (checker.next != null) {
                if (checker.data != val) {
                    checker = checker.next;
                    prev = prev.next;
                } else {
                    checker = checker.next;
                    prev.next = checker;
                }
            }
            if (checker.data == val) {
                prev.next = null;
            }
        }
    }  
}

/*
* Accepts a second LinkedIntList as a parameter.
* Moves values from the second list to this list.
* Empties the second list.
*/
public void transferFrom(LinkedIntList list2) {
    if (front == null) {
        front = list2.front;
        list2.front = null;
    } else {
        ListNode back = front;
        while (back.next != null) {
            back = back.next;
        }
        back.next = list2.front;
        list2.front = null;
    }    
}

/*
* Rearranges the elements of a list so that all of the negative values appear before all 
* of the non-negatives.
*/
public void split() {
    if (front != null && front.next != null) {
        int numLoops = this.size();
        ListNode checker = front;
        ListNode back = front;
        while (back.next != null) {
            back = back.next;
        }
        while (front.data >= 0 && numLoops > 0) {
            front = checker.next;
            checker.next = null;
            back.next = checker;
            back = checker;
            checker = front;
            numLoops--;
        }
        ListNode prev = checker;
        checker = prev.next;
        for (int i = 1; i < numLoops; i++) {
            if (checker.data < 0) {
                checker = checker.next;
                prev = prev.next;
            } else {
                prev.next = checker.next;
                checker.next = null;
                back.next = checker;
                back = checker;
                checker = prev.next;
            }
        }
    }
}

/*
* Accepts an integer n representing a "compression factor" and replaces every n elements 
* with a single element whose data value is the sum of those n nodes. If the list's size 
* is not an even multiple of n, whatever elements are left over at the end are compressed 
* into one node. If n is greater than or equal to the list size, the entire list compresses 
* into a single element. If the list is empty, the result after the call is empty regardless 
* of what factor n is passed.
*/
public void compress(int n) {
    int size = this.size();
    
    if (size > 1 && n > 1) {
        ListNode base = front;
        ListNode current = front.next;
        int completeCycles = size / n;
        int nodesLeft = size - (completeCycles * n);
        while (completeCycles > 0) {
            for (int i = 0; i < n - 1; i++) {
                base.data = base.data + current.data;
                if (current.next == null) {
                    base.next = null;
                    break;
                }
                current = current.next;
            }
            
            if (current.next == null) {
                break;
            }
            base.next = current;
            base = current;
            current = current.next;
            completeCycles--;
        }
        
        if (nodesLeft > 1) {
            while (current.next != null) {
                base.data = base.data + current.data;
                current = current.next;
            }
            base.data = base.data + current.data;
            base.next = null;
        }
        
    }    
}

/*
* Takes an integer n as a parameter and increases a list of integers by a factor of n by 
* replacing each integer in the original list with n copies of that integer.
*/
public void stretch(int n) {
    if (n <= 0) {
        front = null;
    } else if (front != null) {
        ListNode prev = front;
        if (front.next != null) {
            ListNode current = front.next;
            while (current.next != null) {
                for (int i = 0; i < n - 1; i++) {
                    prev.next = new ListNode(prev.data, null);
                    prev = prev.next;
                }
                prev.next = current;
                current = current.next;
                prev = prev.next;
            }
            for (int i = 0; i < n - 1; i++) {
                prev.next = new ListNode(prev.data, null);
                prev = prev.next;
            }
            prev.next = current;
            prev = prev.next;
        }
        
        for (int i = 0; i < n - 1; i++) {
            prev.next = new ListNode(prev.data, null);
            prev = prev.next;
        }
    }
}

/*
* Doubles the size of a list by replacing every integer in the list with two of that integer.
*/
public void stutter() {
    if (front != null) {
        ListNode current = front;
        while (current.next != null) {
            current.next = new ListNode(current.data, current.next);
            current = current.next.next;
        }
        current.next = new ListNode(current.data, null);
    }  
}

/*
* Switches the order of elements in a linked list of integers in a pairwise fashion, i.e.
* switch first 2, then next 2, then next 2 etc. If there are an odd number of values, the 
* final element is not moved.
*/
public void switchPairs() {
    if (front != null && front.next != null) {
        ListNode current = front.next;
        ListNode prev = front;
        prev.next = current.next;
        current.next = prev;
        front = current;
        if (prev.next.next != null) {
            current = prev.next.next;
            prev = prev.next;

            ListNode prevPrev = front.next;
            while (current.next != null) {
                prev.next = current.next;
                current.next = prev;
                prevPrev.next = current;
                if (prev.next.next == null) {
                    break;
                }
                current = prev.next.next;
                prev = prev.next;
                prevPrev = prevPrev.next.next;
            }
            if (current.next == null) {
                prev.next = null;
                current.next = prev;
                prevPrev.next = current;
            }
            
        }   
    }     
}

/*
* Deletes the last value (the value at the back of the list) and returns the deleted value. 
* If the list is empty, throws a NoSuchElementException.
*/
public int deleteBack() {
    if (front == null) {
        throw new NoSuchElementException();
    } else if (front.next == null) {
        int lastVal = front.data;
        front = null;
        return lastVal;
    } else {
        ListNode current = front.next;
        ListNode prev = front;
        while (current.next != null) {
            current = current.next;
            prev = prev.next;
        }
        int lastVal = current.data;
        prev.next = null;
        return lastVal;
    }
}

/*
* Returns whether or not a list of integers has two adjacent numbers that are consecutive 
* integers (true if such a pair exists and false otherwise)
*/
public Boolean hasTwoConsecutive() {
    if (front == null || front.next == null) {
        return false;
    }
    ListNode current = front.next;
    ListNode prev = front;
    while (current != null) {
        if (current.data - prev.data == 1) {
            return true;
        }
        current = current.next;
        prev = prev.next;
    }
    return false;
}

/*
* Returns the number of duplicates in a sorted list
*/
public int countDuplicates() {
    if (front == null || front.next == null) {
        return 0;
    }
    ListNode current = front.next;
    ListNode prev = front;
    int numDuplicates = 0;
    while (current != null) {
        if (current.data == prev.data) {
            numDuplicates++;
        }
        current = current.next;
        prev = prev.next;
    }
    return numDuplicates;
}

/*
* Accepts an integer value as a parameter and returns the index in the list of the last 
* occurrence of that value, or -1 if the value is not found in the list.
*/
public int lastIndexOf(int val) {
    if (front == null) {
        return -1;
    }
    ListNode current = front;
    int lastIndex = -1;
    int position = 0;
    while (current != null) {
        if (current.data == val) {
            lastIndex = position;
        }
        current = current.next;
        position++;
    }
    return lastIndex;
}

/* 
* Returns true if the list is in sorted (nondecreasing) order and returns false otherwise.
* An empty list is considered to be sorted.
*/
public Boolean isSorted() {
    if (front == null || front.next == null) {
        return true;
    }
    ListNode current = front.next;
    ListNode prev = front;
    while (current != null) {
        if (current.data < prev.data) {
            return false;
        }
        current = current.next;
        prev = prev.next;
    }
    return true;
}

/*
* Returns the minimum value in a list of integers
* If the list is empty, throws a NoSuchElementException.
*/
public int min() {
    if (front == null) {
        throw new NoSuchElementException();
    }
    ListNode current = front;
    int minVal = current.data;
    while (current.next != null) {
        if (current.next.data < minVal) {
            minVal = current.next.data;
            current = current.next;
        } else {
            current = current.next;
        }
    }
    return minVal;
}

/*
* Accepts an index and a value and sets the list's element at that index to have the given 
* value. Assumes that the index is between 0 (inclusive) and the size of the list (exclusive).
*
*/
public void set(int index, int val) {
    ListNode current = front;
    while (index > 0) {
        current = current.next;
        index--;
    }
    current.data = val;
}


//Moves the first element of the list to the back end of the list. 
public void firstLast() {
    if (front != null && front.next != null) {
        ListNode current = front.next;
        ListNode temp = front.next;
        front.next = null;
        while (current.next != null) {
            current = current.next;
        }
        current.next = front;
        front = temp;
    }
}