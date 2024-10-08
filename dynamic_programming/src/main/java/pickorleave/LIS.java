package pickorleave;

public class LIS {
    public void print(int[] array){
        for (int i : array)
            System.out.print(i+ " ");
        System.out.println();
    }
    int[] result = new int[6];
    public int lis(int [] sequence,int previousNum , int start){ // 2^n without memoization
        if ( start==result.length){
            int longest=0;
            for (int i : result){
                if(i !=-1)
                    longest++;
            }
            return longest;
        }
        result[start]=-1;
        int choice1=lis(sequence,previousNum,start+1); //leave

        int choice2=0;
        if (sequence[start] >= previousNum ){
            result[start]=sequence[start];  // pick
            choice2=lis(sequence,sequence[start], start+1);
        }
        return Math.max(choice1,choice2);
    }

    int [][] memory = new int[50][50];
    public int lis2(int [] sequence,int previous, int start){
        if ( start==sequence.length)
            return 0;
        if(memory[start][previous]!=0)
            return memory[start][previous];

        int choice1= lis2(sequence,previous,start+1); //leave
        int choice2=0;
        if (previous>=sequence.length || sequence[start] >= sequence[previous] ){
            choice2 = 1 + lis2(sequence, start,start+1);
        }

        memory[start][previous]=Math.max(choice1,choice2);
        return memory[start][previous];
    }
    public void printLis2(int [] sequence,int previous, int start){
        if ( start==sequence.length)
            return ;
        int optimal=lis2(sequence,previous,start);

        int choice1= lis2(sequence,previous,start+1); //leave
        int choice2=0;
        if (previous>=sequence.length || sequence[start] >= sequence[previous] )
            choice2 = 1 + lis2(sequence, start,start+1);
        if (optimal==choice1){
            printLis2(sequence, previous, start+1);
        }
        else {
            System.out.print(sequence[start]+" ");   //  print the token number how can i print the numnber i leave 
            printLis2(sequence, start, start+1);
        }
    }

    int [] memory1 = new int[50];
    public int lis3(int [] sequence, int start){
        if ( start==sequence.length)
            return 0;
        if(memory1[start]!=0)
            return memory1[start];

        for (int i =start+1 ; i<sequence.length ; i++){
            if(sequence[i]>sequence[start])
                memory1[start] = Math.max(memory1[start],lis3(sequence,i));
        }
        return ++memory1[start];
    }
    public int longestIncreasingSubsequence(int [] sequence){
        int longestSubsequence=0;
        for (int i =0 ; i<sequence.length ; i++){
            longestSubsequence=Math.max(longestSubsequence,lis3(sequence,i));
        }
        return longestSubsequence;
    }
    public int longestIncreasingSubsequence_improved(int [] sequence){
        int[] updatedArray=new int[sequence.length+1];
        updatedArray[0]=Integer.MIN_VALUE;
        for (int i =0 ; i<sequence.length ; i++){
            updatedArray[i+1]=sequence[i];
        }
        return lis3(updatedArray,0) -1;
    }
    public static void main(String[] args) {
        int [] array = {15,2,7,4,6,9};
        System.out.println(new LIS().lis(array,-1,0));
        System.out.println(new LIS().lis2(array,array.length,0)); // -1 as the initial value for prev makes a problem . solve it flagging
        System.out.println(new LIS().longestIncreasingSubsequence(array));
        System.out.println(new LIS().longestIncreasingSubsequence_improved(array));

        //build output
        new LIS().printLis2(array,array.length,0);

    }

}
