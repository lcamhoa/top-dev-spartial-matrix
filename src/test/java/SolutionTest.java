import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class SolutionTest {

    @Test
    public void generate_3_matrix() {
        final Solution solution = new Solution();
        final int[][] actual = solution.generate_ldru_matrix(3);
        assertArrayEquals(new int[] { 7, 8, 9 }, actual[0]);
        assertArrayEquals(new int[] { 6, 1, 2 }, actual[1]);
        assertArrayEquals(new int[] { 5, 4, 3 }, actual[2]);
    }

    @Test
    public void generate_4_matrix() {
        final Solution solution = new Solution();
        final int[][] actual = solution.generate_ldru_matrix(4);
        assertArrayEquals(new int[] { 13, 14, 15, 16 }, actual[0]);
        assertArrayEquals(new int[] { 12, 3, 4, 5 }, actual[1]);
        assertArrayEquals(new int[] { 11, 2, 1, 6 }, actual[2]);
        assertArrayEquals(new int[] { 10, 9, 8, 7 }, actual[3]);
    }

    @Test
    public void generate_5_matrix() {
        final Solution solution = new Solution();
        final int[][] actual = solution.generate_ldru_matrix(5);
        assertArrayEquals(new int[] { 21, 22, 23, 24, 25 }, actual[0]);
        assertArrayEquals(new int[] { 20, 7, 8, 9, 10 }, actual[1]);
        assertArrayEquals(new int[] { 19, 6, 1, 2, 11 }, actual[2]);
        assertArrayEquals(new int[] { 18, 5, 4, 3, 12 }, actual[3]);
        assertArrayEquals(new int[] { 17, 16, 15, 14, 13 }, actual[4]);
    }
}