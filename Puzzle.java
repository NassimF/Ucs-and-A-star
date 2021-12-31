//The Main Class. 


class Puzzle{
	public static void main(String args[]) {
		int[][] initial_state={{1,-1,3},{8,6,4},{7,5,2}};
                
                
                //check if it is solvable
                if (isSolvable(initial_state) == false)
                    System.out.println("not solvable!");
                
                else{


		Problem problem=new Problem(initial_state); 
                
                // for choosing between default strategies or A* 
                    //or A* with manhattan+ linear conflict or UCS
                    //{Astar, default, UCS, Astar2}
                    
                String version = "Astar2";
                long startTime = System.nanoTime();
		PSearcher searcher=new PSearcher(problem , version); 

		ActionSequence AS=searcher.Search("Astar"); 
		AS.printList(); 
                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println(totalTime);
                }
	}
        
        
        //find inversions in given array 
        static int getInvCount(int[]arr){
    int inv_count = 0;
    for (int i = 0; i < arr.length ; i++){
        for (int j = i + 1; j < arr.length; j++){
            
            if(i==arr.length-1){
                if (arr[i]!=5)
                    inv_count=inv_count+1;
            }
            
            else{
            switch(arr[i]){
                case 1: if ((arr[j] !=2)&&(arr[j] !=3)&&(arr[j] !=8)&&(arr[j] !=4)&&(arr[j] !=7)
                    &&(arr[j] !=6)&&(arr[j] !=5))
                inv_count++;
                break;
                
                case 2: if ((arr[j] !=3)&&(arr[j] !=8)&&(arr[j] !=4)&&(arr[j] !=7)
                    &&(arr[j] !=6)&&(arr[j] !=5))
                inv_count++;
                break;
                
                case 3: if ((arr[j] !=8)&&(arr[j] !=4)&&(arr[j] !=7)
                    &&(arr[j] !=6)&&(arr[j] !=5))
                inv_count++;
                break;
                
                case 8: if ((arr[j] !=4)&&(arr[j] !=7)
                    &&(arr[j] !=6)&&(arr[j] !=5))
                inv_count++;
                break;
                
                case 4: if ((arr[j] !=7)
                    &&(arr[j] !=6)&&(arr[j] !=5))
                inv_count++;
                break;
                
                case 7: if ((arr[j] !=6)&&(arr[j] !=5))
                inv_count++;
                break;
                
                case 6: if ((arr[j] !=5))
                inv_count++;
                break;
                
                case 5: if (i!=(arr.length-1))
                inv_count++;
                break;
            }
            }
        }
    }
    return inv_count;
}
 
        
// This function returns true
// if given 8 puzzle is solvable.
static boolean isSolvable(int[][] puzzle)
{
    // remove one tile for the blank=8
    int [] flattened_arr = new int [(puzzle.length*puzzle.length)-1];
    
    //flatten the aray
    int k=0;
    for(int i=0;i<3;i++){     
        for(int j=0;j<3;j++){
           
            if(puzzle[i][j]!=-1){
            flattened_arr[k]=puzzle[i][j];
            k++;
            }
        }
    }
    // Count inversions in given 8 puzzle
    int invCount = getInvCount(flattened_arr);
 
    // return true if inversion count is even.
    return (invCount % 2 == 0);
}
}
