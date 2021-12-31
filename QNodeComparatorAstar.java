/*this class compares QNode costs for A*
the heuristic function in use is manhattan 
*/ 



import static java.lang.Math.abs;
import java.util.Comparator;



class QNodeComparatorAstar implements Comparator<QNode>{
              
            // Overriding compare()method of Comparator 
                        // for ascending order of path cost
                        //this is callded each time a node is being added to queue
            public int compare(QNode n1, QNode n2) {
                
                //find distance from target for the nodes
                int h1 = h(n1);
                int h2 = h(n2);
                
                //find distance from root for the nodes
                int g1 = n1.element.path_cost;
                int g2 = n2.element.path_cost;
                
                //find f 
                int f1 = h1+ g1;
                int f2 = h2+ g2;
                
                // compare f of two nodes
                if (f1 < f2)
                    return -1;
                else if (f1 > f2)
                    return 1;
                                return 0;
            }
            
            
            // find manhattan distance
            public int h(QNode n){
                int manhattan = 0;
                
                for(int i = 0; i<=2; i++){
                    for(int j = 0; j<=2; j++){
                        // value of the selected tile
                        int value = n.element.state[i][j];
                        //find the target indices
                        //then add the distance to the manhattan distance value
                        switch(value){
                            case 1: manhattan = manhattan + (abs(i-0)+abs(j-0));
                            break;
                            
                            case 2: manhattan = manhattan + (abs(i-0)+abs(j-1));
                            break;
                            
                            case 3: manhattan = manhattan + (abs(i-0)+abs(j-2));
                            break;
                            
                            case 4: manhattan = manhattan + (abs(i-1)+abs(j-2));
                            break;
                            
                            case 5: manhattan = manhattan + (abs(i-2)+abs(j-2));
                            break;
                            
                            case 6: manhattan = manhattan + (abs(i-2)+abs(j-1));
                            break;
                            
                            case 7: manhattan = manhattan + (abs(i-2)+abs(j-0));
                            break;
                            
                            case 8: manhattan = manhattan + (abs(i-1)+abs(j-0));
                            break;
                            //don't calculate for the blank tile
                            case -1: break;
                            
                        }
                    }
                    
                }
                return manhattan;
                
            }
}
