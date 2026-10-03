package IntroductiontoDataStructures.class_problems;
class SummaryResult {
    int total;
    int row;
    int col;

    SummaryResult(int total, int row, int col) {
        this.total = total;
        this.row = row;
        this.col = col;
    }
}

public class WarehouseSummary {
    public static SummaryResult warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new SummaryResult(0, -1, -1);
        }

        int totalSum = 0;
        int maxVal = -1;
        int maxRow = 0;
        int maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                int count = grid[r][c];
                totalSum += count;

                if (count > maxVal) {
                    maxVal = count;
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        return new SummaryResult(totalSum, maxRow, maxCol);
    }

    public static void main(String[] args) {
        int[][] grid = {
                {4, 9, 2},
                {7, 1, 6},
                {3, 12, 5}
        };
        warehouseSummary(grid);
    }
}