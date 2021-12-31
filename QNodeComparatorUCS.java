/*this class compares QNode costs for UCS*/ 



import java.util.Comparator;



class QNodeComparatorUCS implements Comparator<QNode>{
              
            // Overriding compare()method of Comparator 
                        // for ascending order of path cost from root
                        //this is callded each time a node is being added to queue
            public int compare(QNode n1, QNode n2) {
                if (n1.element.path_cost < n2.element.path_cost)
                    return -1;
                else if (n1.element.path_cost > n2.element.path_cost)
                    return 1;
                                return 0;
            }
}
