import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Print a m x m matrix, which direction LDRU ");
        do {
            System.out.print("Enter n ( n <= 1 : exit): ");
            final int n = sc.nextInt();
            if (n <= 1) {
                System.out.println("Bye!");
                break;
            }
            final int[][] result = new Solution().generate_ldru_matrix(n);
            final int numLength = String.valueOf( n * n ).length();
            for (int i = 0; i < n; i++) {
                System.out.println(
                        Arrays.stream(result[i])
                              .mapToObj(v -> toPadString(v, numLength))
                              .collect(Collectors.joining(" "))
                );
            }
        } while (true);

    }

    public static String toPadString(int n, int m) {
        final String format = "%1$" + m + "s";
        return String.format(format, n);
    }

    public int[][] generate_ldru_matrix(int n) {
        final int[][] result = new int[n][n];
        final DirectionQueue directions = new DirectionQueue(new Direction[]
                                                                     {
                                                                             Direction.LEFT, Direction.DOWN,
                                                                             Direction.RIGHT, Direction.UP
                                                                     });
        final Index index = new Index(0, n - 1);
        Cell cell = new Cell(index, directions.current());
        int val = n * n;
        do {
            result[cell.index.x][cell.index.y] = val;
            val--;
            if (val <= 0) {
                break;
            }
            cell = nextCell(result, n, cell, directions);
        } while (true);
        return result;
    }

    public Cell nextCell(int[][] matrix, int n, Cell cell, DirectionQueue directionQueue) {
        return nextCell(matrix, n, cell, directionQueue.current(), directionQueue);
    }

    public Cell nextCell(int[][] matrix, int n, Cell cell, Direction curDirection,
                         DirectionQueue directionQueue) {
        if (curDirection.equals(Direction.UP)) {
            final int nextX = cell.index.x - 1;
            final int y = cell.index.y;
            if (nextX >= 0 && matrix[nextX][y] == 0) {
                return new Cell(new Index(nextX, y), curDirection);
            }
            return nextCell(matrix, n, cell, directionQueue.next(), directionQueue);
        } else if (curDirection.equals(Direction.DOWN)) {
            final int nextX = cell.index.x + 1;
            final int y = cell.index.y;
            if (nextX < n && matrix[nextX][y] == 0) {
                return new Cell(new Index(nextX, y), curDirection);
            }
            return nextCell(matrix, n, cell, directionQueue.next(), directionQueue);
        } else if (curDirection.equals(Direction.LEFT)) {
            final int x = cell.index.x;
            final int nextY = cell.index.y - 1;
            if (nextY >= 0 && matrix[x][nextY] == 0) {
                return new Cell(new Index(x, nextY), curDirection);
            }
            return nextCell(matrix, n, cell, directionQueue.next(), directionQueue);
        } else {
            // SOUTH
            final int x = cell.index.x;
            final int nextY = cell.index.y + 1;
            if (nextY < n && matrix[x][nextY] == 0) {
                return new Cell(new Index(x, nextY), curDirection);
            }
            return nextCell(matrix, n, cell, directionQueue.next(), directionQueue);
        }

    }

    public enum Direction {
        UP, LEFT, DOWN, RIGHT
    }

    public class DirectionQueue {
        Direction[] directions;
        private int currentIdx;

        public DirectionQueue(Direction[] directions) {
            this.directions = clone(directions);
            currentIdx = 0;
        }

        Direction[] clone(Direction[] directions) {
            final Direction[] result = new Direction[directions.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = directions[i];
            }
            return result;
        }

        public Direction next() {
            currentIdx = (currentIdx + 1) % directions.length;
            return current();
        }

        public Direction current() {
            return directions[currentIdx];
        }
    }

    public class Index {
        public int x;
        public int y;

        public Index(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public class Cell {
        public Index index;
        public Direction direction;

        public Cell(Index index, Direction direction) {
            this.index = index;
            this.direction = direction;
        }
    }
}
