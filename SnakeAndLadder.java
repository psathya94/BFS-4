package week12.day3;
//TC - O(n^2)
//TC - O(n^2)
import java.util.LinkedList;
import java.util.Queue;

public class SnakeAndLadder {
	public int snakesAndLadders(int[][] board) {
        int row = board.length;
		int col = board[0].length;
		int[] arr = flatten(board);
		
		//System.out.println(Arrays.toString(arr));
		
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		arr[0] = -2;
		int jump =0;
		while(!q.isEmpty()) {
			int size = q.size();
            //System.out.println(q);
			while(size>0) {
				int currIdx = q.poll();
                
                if(currIdx==arr.length-1)
                    return jump;

						
				for(int i=1;i<=6 && currIdx+i<arr.length ;i++) {  //dice roll from 1 to 6
					int newIdx = currIdx+i;
					if(arr[newIdx]!= -2) {
						if(arr[newIdx]== -1) {
							q.add(newIdx);
							arr[newIdx] = -2;
						}
						else {  //ladder or snake
							q.add(arr[newIdx]);
							arr[newIdx] = -2;
						}
					}
				}
				size--;
			}
            jump++;
            
		}
		return -1;
    }
    private int[] flatten(int[][] board) {
		int row = board.length;
		int col = board[0].length;
		int[] arr = new int[row*col];
		int idx =0;
        boolean flag = false;
        int i= board.length-1;
        int j =0;
		while(idx<arr.length){
            if(board[i][j]==-1)
                arr[idx++] = board[i][j];
            else
                arr[idx++] = board[i][j]-1;
            if(!flag){
                j++;
                if(j==col){
                    j--;
                    i--;
                    flag = true;
                }
            }
            else{
                j--;
                if(j<0){
                    j++;
                    i--;
                    flag = false;
                }
            }
        }
		return arr;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix = { { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
				{ -1, 35, -1, -1, 13, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, 15, -1, -1, -1, -1 } };
		//int[][] matrix = {{-1,-1,-1},{-1,9,8},{-1,8,9}};
		int[][] matrix1 = {{-1,-1},{-1,3}};
		SnakeAndLadder p = new SnakeAndLadder();
		System.out.println(p.snakesAndLadders(matrix));
	}

}
