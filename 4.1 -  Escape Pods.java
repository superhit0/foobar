import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class HelloWorld{
    public static int solution(int[] entrances, int[] exits, int[][] paths) {
        int ans = 0;
        int prev = -1;
        
        Set<Integer> exitSet = new HashSet<>();
        
        for(int exit:exits) {
            exitSet.add(exit);
        }
        
        while(prev!=ans) {
            
            prev = ans;
            
            for(int entrance:entrances) {
                boolean visited[]=new boolean[paths.length];
                List<Integer> currPath = new ArrayList<>();
                int currNode = entrance;
                int idx = 0;
                
                for(;;) {
                    boolean hasUnvisited=false;
                    visited[currNode] = true;
                    
                    int max = 0;
                    for(int i=0;i<paths[currNode].length;i++) {
                        if(paths[currNode][i]>max && visited[i] == false) {
                            max = paths[currNode][i];
                            hasUnvisited = true;
                            idx = i;
                        }
                    }
                    
                    if(hasUnvisited) {
                        currPath.add(currNode);
                        currNode = idx;
                    } else if (currPath.size() == 0) {
                        break;
                    } else {
                        currNode = currPath.get(currPath.size()-1);
                        currPath.remove(currPath.size()-1);
                    }
                    
                    if(exitSet.contains(currNode)) {
                        currPath.add(currNode);
                        int min = 2000000;
                        for(int i=0;i<currPath.size()-1;i++) {
                            if(paths[currPath.get(i)][currPath.get(i+1)] < min) {
                                min = paths[currPath.get(i)][currPath.get(i+1)];
                            }
                        }
                        
                        ans+=min;
                        
                        for(int i=0;i<currPath.size()-2;i++) {
                            paths[currPath.get(i)][currPath.get(i+1)]-=min;
                            paths[currPath.get(i+1)][currPath.get(i)]+=min;
                        }
                        
                        paths[currPath.get(currPath.size()-2)][currPath.get(currPath.size()-1)]-=min;
                        break;
                    }
                }
            }
        }
        
        return ans;
    }
     public static void main(String []args){
         int ans = solution(new int[]{0, 1}, new int[]{4, 5}, new int[][]{{0, 0, 4, 6, 0, 0}, {0, 0, 5, 2, 0, 0}, {0, 0, 0, 0, 4, 4}, {0, 0, 0, 0, 6, 6}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}});
        System.out.println("Result= " + ans);
     }
}
