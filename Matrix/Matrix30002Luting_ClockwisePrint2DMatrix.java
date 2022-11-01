package Matrix;

import java.util.ArrayList;

public class Matrix30002Luting_ClockwisePrint2DMatrix {
    static class UseTemplate {
        private final static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        private enum Status {
            Left, Top, Right, Bottom, None;
        }
        private static int M;
        private static int N;
        private static boolean[][] visited;

        private class Tile {
            int row;
            int col;
            Status status;
            Tile(int x, int y, Status s) {
                this.row = x;
                this.col = y;
                this.status = s;
            }
        }

        public void printMatrixClockwise(int[][] matrix) {
            if (matrix == null || matrix[0].length == 0) {
                System.out.println();
                return;
            }
            this.M = matrix.length;
            this.N = matrix[0].length;
            this.visited = new boolean[M][N];

            // Start from top left, go clockwise. identify the change point of directions, continue
            Tile currTile = new Tile(0, 0, Status.Left);
            do {
                System.out.print(matrix[currTile.row][currTile.col] + " ");
                currTile = getNextStatusOfTile(currTile);
            } while (currTile.status != Status.None);
            System.out.println();
        }
        private Tile getNextStatusOfTile(Tile curr) {
            ArrayList<Tile> nextStatus = new ArrayList<>();
            visited[curr.row][curr.col] = true;
            for (int i = 0; i < directions.length; ++i) {
                int nextRow = curr.row + directions[i][0];
                int nextCol = curr.col + directions[i][1];
                if (nextRow < 0 || nextRow >= M || nextCol < 0 || nextCol >= N || visited[nextRow][nextCol]) {
                    continue;
                }
//                System.out.println("DEBUG getNExtStatusOfTile " + nextRow + " " + nextCol);
                if (i == 0) {
                    nextStatus.add(new Tile(nextRow, nextCol, Status.Left));
                } else if (i == 1) {
                    nextStatus.add(new Tile(nextRow, nextCol, Status.Top));
                } else if (i == 2) {
                    nextStatus.add(new Tile(nextRow, nextCol, Status.Right));
                } else {
                    nextStatus.add(new Tile(nextRow, nextCol, Status.Bottom));
                }
            }

            if (nextStatus.size() == 0) {
                return new Tile(0, 0, Status.None);
            }
            if (nextStatus.size() == 1) {
                return nextStatus.get(0);
            }
            if (curr.status == nextStatus.get(0).status ) {
                return nextStatus.get(0);
            } else if (curr.status == nextStatus.get(1).status){
                return nextStatus.get(1);
            }
            System.out.println("Abnormal");
            return null;
        }

        private boolean validTile(int x, int y) {
            if (x < 0 || x >= M || y < 0 || y >= N || visited[x][y]) {
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        UseTemplate solution = new UseTemplate();
        int[][] a1 =  {
                {1,2,3,4},
                {10,11,12,5},
                {9,8,7,6}
        };
        solution.printMatrixClockwise(a1);
        int[][] a2 =  {{1,2,3,4}};
        solution.printMatrixClockwise(a2);
        int[][] a3 = {
            {1, 2, 3, 10},
            {4, 5, 6, 9},
            {7,7,7,7},
        };
        solution.printMatrixClockwise(a3);
        int[][] a5 = {
                {},
        };
        solution.printMatrixClockwise(a5);
        int[][] a4 = {
                {1},
        };
        solution.printMatrixClockwise(a4);
    }

}
