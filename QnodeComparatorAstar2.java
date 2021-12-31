/*this class compares QNode costs for A*
the heuristic function in use is manhattan + linear conflict
*/ 



import static java.lang.Math.abs;
import java.util.Comparator;



class QNodeComparatorAstar2 implements Comparator<QNode>{
              
            // Overriding compare()method of Comparator 
                        // for ascending order of path cost
                        //this is callded each time a node is being added to queue
            public int compare(QNode n1, QNode n2) {
                
                //find distance from target for the nodes
                int h1 = manhattan_distance(n1) + linear_conflict(n1);
                int h2 = manhattan_distance(n2) + linear_conflict(n2);
                
                  

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
            public int manhattan_distance(QNode n){
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
            
            
            
            // find the correct location of value and store it in correct_location
            public void findCorrectLocation(int value, int[][] correct_location){
                switch(value){
                            case 1: correct_location[0][0]=0;
                                    correct_location[0][1]=0;
                            break;
                            
                            case 2:correct_location[0][0]=0;
                                    correct_location[0][1]=1;
                            break;
                            
                            case 3: correct_location[0][0]=0;
                                    correct_location[0][1]=2;
                            break;
                            
                            case 4:correct_location[0][0]=1;
                                    correct_location[0][1]=2;
                            break;
                            
                            case 5: correct_location[0][0]=2;
                                    correct_location[0][1]=2;
                            break;
                            
                            case 6: correct_location[0][0]=2;
                                    correct_location[0][1]=1;
                            break;
                            
                            case 7:correct_location[0][0]=2;
                                    correct_location[0][1]=0;
                            break;
                            
                            case 8: correct_location[0][0]=1;
                                    correct_location[0][1]=0;
                            break;
                            //don't calculate for the blank tile
                            case -1: break;
                            
                        }
                
            }
            
            
            
            //find the mumber of linear conflicts in a certain state
            public int linear_conflict(QNode n){
                int num_conflict = 0;
                int [][] state = n.element.state.clone();
                
                for(int i = 0; i< n.element.state.length; i++){
                    for(int j = 0; j<n.element.state[0].length; j++){
                      
                        //ignore the blank tile
                        if( state[i][j] == -1)
                            continue;
                        
                        int value = state[i][j];
                        
                        //find the correct location
                        int [][] correct_location_current = new int [1][2];
                        findCorrectLocation(value, correct_location_current);
                        
                        //if correct location
                        if((correct_location_current[0][0]==i) && (correct_location_current[0][1]==j))
                            continue;
                        
                        // if correct row
                        if(i==correct_location_current[0][0]){
                            //find the correct location of the neighbor tile
                            // and see if there is conflict
                            int [][] location_neighbor = new int[1][2];
                            for(int fromCol=j+1; fromCol< state.length; fromCol++){
                                //ignore blank
                                if(state[i][fromCol]!=-1){
                                findCorrectLocation(state[i][fromCol], location_neighbor);}

                                if ((value> state[i][fromCol]) && (location_neighbor[0][0]==i)){
                                    num_conflict = num_conflict+1;}
                                
                            }
                         
                        }
                        
                        // if correct column
                        if(j==correct_location_current[0][1]){
                            //find the correct location of the neighbor tile
                            // and see if there is conflict
                            int [][] location_neighbor = new int[1][2];
                            for(int fromRow=i+1; fromRow< state.length; fromRow++){
                                //ignore blank
                                if(state[fromRow][j]!=-1){
                                findCorrectLocation(state[fromRow][j], location_neighbor);}
                                    
                                
                                if ((value> state[fromRow][j]) && (location_neighbor[0][1]==j))
                                    num_conflict = num_conflict+1;}
                            
                        }
                          
                    }
                }
                return 2*num_conflict;
            }
}
