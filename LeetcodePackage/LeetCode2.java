

import java.util.*;
import java.util.stream.*;

public class LeetCode2 {


       public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        int size() {
            int count = 0;
            ListNode temp = next;
            while (temp != null) {
                count++;
                temp = temp.next;
            }
            return count;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode current = this;
            while (current != null) {
                sb.append(current.val).append("-");
                current = current.next;
            }
            sb.append("null");
            return sb.toString();
        }
    }

    /**
     * 树结点
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }


        /**
     * 重要 反轉 linkedList
     * https://leetcode.com/problems/reverse-linked-list/
     * 206. Reverse Linked List
     * https://www.youtube.com/watch?v=iT1YrvSNtlw&ab_channel=%E5%9B%BE%E7%81%B5%E6%98%9F%E7%90%83TuringPlanet
     */
    public ListNode reverseList(ListNode head) {

        ListNode previous = null;
        while (head != null) {
            ListNode next = head.next; //暂存下一个头部的信息，因为头部会一直变动
            head.next = previous; //把現在 head 的下一個指向之前存起來的
            previous = head; //把未來要接上的 head 先暫存起來
            head = next; //把暫存的 next 指向現在的頭部
        }
        return previous;
    }


        /**
     * https://leetcode.com/problems/factorial-trailing-zeroes/
     * 172. Factorial Trailing Zeroes
     * 數學題，用一般解法會液位
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {

        long sum = 1;
        //先算出接乘數
        for (int i = 2; i <= n; i++) {
            sum = sum * i;
        }

        int conutZero = 0;
        while (sum > 0) {
            if (sum % 10 == 0) {
                conutZero++;
            } else {
                break;
            }
            sum = sum / 10;
        }
        return conutZero;
    }

    /**
     * <a href="https://leetcode.com/problems/permutations/">...</a>
     * 46. Permutations
     * backtrack
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        permutePart(list, new ArrayList<>(), nums);
        return list;
    }

    // In this code we are using tempList only, and not making a new arraylist at every call,
    // so we are doing Backtracking to achive this
    private void permutePart(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            // This means we found one answer, add it to the list
            list.add(new ArrayList<>(tempList));
        } else {
            for (int num : nums) {
                if (!tempList.contains(num)) {
                    //If element does not exist in the tempList then only go forward
                    tempList.add(num);
                    permutePart(list, tempList, nums);
                    // Backtrack -> removing the last added element from the tempList
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }

    /**
     * https://leetcode.com/problems/count-sorted-vowel-strings/
     * 1641. Count Sorted Vowel Strings
     */
    public int countVowelStrings(int n) {
        int a = 1;
        int e = 1;
        int i = 1;
        int o = 1;
        int u = 1;
        while (n-- > 1) {
            a = a + e + i + o + u;
            e = e + i + o + u;
            i = i + o + u;
            o = o + u;
        }
        return a + e + i + o + u;
    }

    /**
     * https://leetcode.com/problems/merge-in-between-linked-lists/
     * 1669. Merge In Between Linked Lists
     * 把 list1 a->b 的節點移除，放入 list2
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode curr = list1;
        ListNode node_a = list1, node_b = list1;
        //先把指针移到对应的位置
        for (int i = 1; i < a; i++) {
            node_a = node_a.next;
        }
        for (int i = 0; i <= b; i++) {
            node_b = node_b.next;
        }
        ListNode list2_tail = list2;
        while (list2_tail.next != null) {
            list2_tail = list2_tail.next;
        }
        //串接起来
        node_a.next = list2;
        list2_tail.next = node_b;
        return list1;
    }

    /**
     * https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/
     * 1287. Element Appearing More Than 25% In Sorted Array
     * 題意：找出出現 25% 以上的元素在一個排好的陣列中
     * ps 其實就是找出出現最多的元素
     * 題目有點慢，最快要用二分法
     */
    public int findSpecialInteger(int[] arr) {

        if (arr.length == 1) {
            return arr[0];
        }

        int maxCount = 0;
        int count = 1;
        int ans = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] == arr[i]) {
                count++;
            } else {
                count = 0;
            }
            if (count > maxCount) {
                maxCount = count;
                ans = arr[i];
            }
        }
        return ans;
    }

    /**
     * https://leetcode.com/problems/delete-characters-to-make-fancy-string/
     * 1957. Delete Characters to Make Fancy String
     * agoda
     * 刪除三個連續字母的數字
     * @param s
     * @return
     */
    public String makeFancyString(String s) {

        StringBuilder sb = new StringBuilder();

        int tempLonest = 0;
        sb.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                tempLonest++;
            } else {
                tempLonest = 0;
            }
            if (tempLonest < 2) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * https://leetcode.com/problems/make-the-string-great/
     * 1544. Make The String Great
     * @param s
     */
    public String makeGood(String s) {
        StringBuilder strBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            //大小寫的 char 互減會相差 32
            if (strBuilder.length() > 0 && Math.abs(strBuilder.charAt(strBuilder.length() - 1) - c) == 32)
                strBuilder.delete(strBuilder.length() - 1, strBuilder.length());
            else
                strBuilder.append(c);
        }
        return strBuilder.toString();
    }

    /**
     * https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
     * 1614. Maximum Nesting Depth of the Parentheses
     * 投票法
     * @param s
     * @return
     */
    public int maxDepth(String s) {
        char[] strChr = s.toCharArray();

        int max = 0;
        int count = 0;
        for (int i = 0; i < strChr.length; i++) {
            if (strChr[i] == '(') {
                count++;
            } else if (strChr[i] == ')') {
                count--;
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    /**
     * https://leetcode.com/problems/find-all-duplicates-in-an-array/
     * 442. Find All Duplicates in an Array
     * @param nums
     * @return
     */
    public static List<Integer> findDuplicates(int[] nums) {
        //boolean array 的判斷速度 > int[] >>> map or set
        boolean[] dup = new boolean[nums.length + 1]; // Increased size to accommodate all possible values
        List<Integer> duplicates = new ArrayList<>();

        for (int num : nums) {
            if (dup[num]) {
                duplicates.add(num);
            }
            dup[num] = true;
        }

        return duplicates;
    }

    /**
     * https://leetcode.com/problems/diameter-of-binary-tree/
     * 543. Diameter of Binary Tree
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        int[] record = new int[2];
        record[0] = 0; //current length
        record[1] = 0; //max
        return record[1];
    }

    /**
     * 動態規劃
     * https://leetcode.com/problems/min-cost-climbing-stairs/
     * 746. Min Cost Climbing Stairs
     * @param cost
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        //存儲到達每一級樓梯的最小成本。
        int[] dp = new int[n];

        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }

        return Math.min(dp[n - 1], dp[n - 2]);
    }

    /**
     * https://leetcode.com/problems/n-th-tribonacci-number/
     * 1137. N-th Tribonacci Number
     * The Tribonacci sequence Tn is defined as follows:
     * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
     */
    public int tribonacci(int n) {

        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }

    /**
     * https://leetcode.com/problems/coin-change/
     * 322. Coin Change
     * 我們使用一個長度為 amount+1 的陣列 dp 來存儲組成每個金額所需的最小硬幣數量。
     * 初始時，將 dp 數組填充為 amount+1，這是一個不可能達到的值。
     * 將 dp[0] 設置為 0，因為組成金額為 0 不需要任何硬幣。
     * 從金額為 1 開始遍歷到 amount，對於每個金額 i，遍歷硬幣面值 coins 中的每個硬幣。
     * 如果硬幣面值 coin 小於等於當前金額 i，則更新 dp[i] 為 dp[i-coin]+1 和當前 dp[i] 中的較小值。
     * 最終返回 dp[amount]，如果 dp[amount] 大於 amount，則表示無法湊出金額 amount，返回 -1。
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
     * 1160. Find Words That Can Be Formed by Characters
     * @param words
     * @param chars
     * @return
     */
    public int countCharacters(String[] words, String chars) {

        int sum = 0;

        //先放入字典中
        int[] charDictory = new int[26];
        for (int i = 0; i < chars.length(); i++) {
            charDictory[chars.charAt(i) - 'a']++;
        }

        int count = 0;
        for (String word : words) {

            //每次先更新字典
            int[] clone = charDictory.clone();
            for (int i = 0; i < word.length(); i++) {
                if (clone[word.charAt(i) - 'a'] < 1) {
                    break;
                } else {
                    clone[word.charAt(i) - 'a']--;
                }

                if (i == word.length() - 1) {
                    count = count + word.length();
                }
            }
        }
        return count;
    }

    /**
     * https://leetcode.com/problems/valid-parenthesis-string
     * 678. Valid Parenthesis String
     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        int low = 0; // 最小左括號數量
        int high = 0; // 最大左括號數量
        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                if (low > 0) {
                    low--;
                }
                high--;
            } else { // '*' 處理成左括號或空
                if (low > 0) {
                    low--;
                }
                high++;
            }
            if (high < 0) { // 如果高於 0，表示右括號過多，返回 false
                return false;
            }
        }
        return low == 0; // 如果最小左括號數量為 0，則返回 true，否則返回 false
    }

    /**
     * https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/
     * 1700. Number of Students Unable to Eat Lunch
     * 非常酷的解法
     */
    public int countStudents(int[] students, int[] sandwiches) {
        int[] count = new int[2]; // 用來統計每種三明治的數量

        for (int student : students) {
            count[student]++;
        }

        for (int sandwich : sandwiches) {
            if (count[sandwich] == 0) {
                break; // 如果沒有剩餘的這種三明治，停止遍歷
            }
            count[sandwich]--;
        }

        return count[0] + count[1]; // 返回剩餘的學生數量
    }

    /**
     * @param tickets
     * @param k
     * @return
     */
    public int timeRequiredToBuy(int[] tickets, int k) {

        int count = 0;
        while (tickets[k] > 0) {
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i] > 0) {
                    tickets[i]--;
                    count++;
                }
                if (tickets[k] == 0) {
                    break;
                }
            }
        }
        return count;
    }

    /**
     * https://leetcode.com/problems/add-to-array-form-of-integer/
     * 989. Add to Array-Form of Integer
     * @param num
     * @param k
     * @return
     */
    public List<Integer> addToArrayForm(int[] num, int k) {
        LinkedList<Integer> result = new LinkedList<>();
        int carry = k;

        for (int i = num.length - 1; i >= 0 || carry > 0; i--) {
            if (i >= 0) {
                carry += num[i];
            }
            result.add(0, carry % 10);
            carry /= 10;
        }

        return result;
    }

    /**
     * https://leetcode.com/problems/teemo-attacking/
     * 495. Teemo Attacking
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {

        int count = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            if (timeSeries[i] + duration - 1 < timeSeries[i + 1]) {
                count = count + duration;
            } else {
                count = count + (timeSeries[i + 1] - timeSeries[i]);
            }
        }
        //加上最后一次的中毒时间
        return count + duration;
    }

    /**
     * graph
     * https://leetcode.com/problems/number-of-islands/
     * 200. Number of Islands
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs_numIslands(grid, i, j);
                }
            }
        }

        return count;
    }

    private void dfs_numIslands(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0'; // 標記當前位置為已訪問

        // 遞歸探索相鄰的陸地
        dfs_numIslands(grid, i + 1, j);
        dfs_numIslands(grid, i - 1, j);
        dfs_numIslands(grid, i, j + 1);
        dfs_numIslands(grid, i, j - 1);
    }

    /**
     * graph
     * https://leetcode.com/problems/surrounded-regions/
     * 130. Surrounded Regions
     * @param board
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        int rows = board.length;
        int cols = board[0].length;

        // 遍歷邊界行和列，對於邊界上的'O'，從該位置開始DFS標記相連的'O'
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                dfs_solve(board, i, 0);
            }
            if (board[i][cols - 1] == 'O') {
                dfs_solve(board, i, cols - 1);
            }
        }

        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') {
                dfs_solve(board, 0, j);
            }
            if (board[rows - 1][j] == 'O') {
                dfs_solve(board, rows - 1, j);
            }
        }

        // 遍歷整個矩陣，將未標記的'O'翻轉為'X'，已標記的'.'還原為'O'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '.') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs_solve(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != 'O') {
            return;
        }

        board[i][j] = '.'; // 標記當前位置為已訪問

        // 遞歸探索相鄰的'O'
        dfs_solve(board, i + 1, j);
        dfs_solve(board, i - 1, j);
        dfs_solve(board, i, j + 1);
        dfs_solve(board, i, j - 1);
    }

    /**
     * https://leetcode.com/problems/student-attendance-record-i/
     * 551. Student Attendance Record I
     * @param s
     * @return
     */
    public boolean checkRecord(String s) {

        char[] chars = s.toCharArray();

        int countForA = 0;
        int countForL = 0;

        for (int i = 0; i < chars.length; i++) {

            if (chars[i] == 'A') {
                countForA++;
            } else if (chars[i] == 'L') {
                countForL++;
            }
            if (countForA >= 2 || countForL >= 3) {
                return false;
            }
            if (chars[i] == 'A' || chars[i] == 'P') {
                countForL = 0;
            }
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/largest-positive-integer-that-exists-with-its-negative/
     * 2441. Largest Positive Integer That Exists With Its Negative
     * @param nums
     * @return
     */
    public int findMaxK(int[] nums) {
        if (nums.length < 2) {
            return -1;
        }
        Arrays.sort(nums);
        int lp = 0;
        int rp = nums.length - 1;
        while (lp < rp) {
            if (0 - nums[lp] == nums[rp]) {
                return nums[rp];
            } else if (0 - nums[lp] < nums[rp]) {
                rp--;
            } else {
                lp++;
            }
        }
        return -1;
    }

    /**
     * leetcode 75
     * 这题麻烦在需要对原阵列做处理
     * https://leetcode.com/problems/string-compression/
     * 443. String Compression
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        int index = 0; // 結果字符串的索引位置
        int count = 1; // 重複字符的次數

        for (int i = 0; i < chars.length; i++) {
            if (i + 1 < chars.length && chars[i] == chars[i + 1]) {
                count++;
                continue;
            }
            chars[index++] = chars[i]; // 寫入壓縮後的字符
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[index++] = c; // 寫入重複次數的字符
                }
            }
            count = 1;

        }

        return index;
    }

    /**
     * two pointer , leetcode75
     * https://leetcode.com/problems/max-number-of-k-sum-pairs/
     * 1679. Max Number of K-Sum Pairs
     * @param nums
     * @param k
     * @return
     */
    public int maxOperations(int[] nums, int k) {
        //做這種問題前，通常必須先排序
        Arrays.sort(nums); // 先將數組排序
        int count = 0;
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == k) {
                count++;
                left++;
                right--;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }

        return count;
    }

    /**
     * leetcode75
     * https://leetcode.com/problems/determine-if-two-strings-are-close/
     * 1657. Determine if Two Strings Are Close
     * @param word1
     * @param word2
     * @return
     */
    public boolean closeStrings(String word1, String word2) {
        //長度不一樣必定 false
        if (word1.length() != word2.length()) {
            return false;
        }
        int[] word1Frequency = new int[26];
        int[] word2Frequency = new int[26];
        for (char ch : word1.toCharArray()) {
            word1Frequency[ch - 'a']++;
        }
        for (char ch : word2.toCharArray()) {
            word2Frequency[ch - 'a']++;
        }

        //檢查字母是否在兩個 String中都出現過
        for (int i = 0; i < 26; i++) {
            if ((word1Frequency[i] == 0 && word2Frequency[i] != 0) || (word1Frequency[i] != 0 && word2Frequency[i] == 0)) {
                return false;
            }
        }

        //排序的目的是把所有數字不分字母由小到大排好
        Arrays.sort(word1Frequency);
        Arrays.sort(word2Frequency);
        for (int i = 0; i < 26; i++) {
            //數字排好以後，如果有數字不對齊，則必定為 false
            if (word1Frequency[i] != word2Frequency[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * leetcode75, fast slow pointer
     * 本來想用一個 arrayList or array 紀錄剩下一半的值
     * 但這樣需要額外的記憶體空間
     * @param head
     * @return
     */
    public int pairSum(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode temp = slow;
        //反轉這個 slow 剩下一半的 list
        temp = reverseList(temp);
        int max = 0;
        //跟head开始比较数字
        while (temp != null) {
            int sum = temp.val + head.val;
            if (max < sum) max = sum;
            temp = temp.next;
            head = head.next;
        }
        return max;
    }

    /**
     * https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/
     * 2816. Double a Number Represented as a Linked List
     * @param head
     * @return
     */
    public ListNode doubleIt(ListNode head) {
        ListNode head1 = new ListNode();
        head1.next = head;

        ListNode left = head1;
        ListNode right = head;
        while (right != null) {
            //如果右邊有進位，則左邊+1
            //不需考慮左邊為9的情況，因為為9*2=8，最多就是8
            if (right.val * 2 >= 10) {
                left.val += 1;
            }
            right.val = (right.val * 2) % 10;
            left = left.next;
            right = right.next;
        }
        return head1.val > 0 ? head1 : head;
    }

    /**
     * https://leetcode.com/problems/max-consecutive-ones/
     * 485. Max Consecutive Ones
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {

        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 1) {
                count++;
            } else {
                count = 0;
            }
            if (max < count) {
                max = count;
            }
        }
        return max;
    }

    /**
     * https://leetcode.com/problems/cousins-in-binary-tree/
     * 993. Cousins in Binary Tree
     * @return 是否為表兄弟節點
     * cousin node : 父节点不一样且在同一层
     */
    public boolean isCousins(TreeNode root, int x, int y) {

        //x:所在層數，y左在層數 ,x的父節點, y的父節點
        int[] record = new int[4];
        if (root.val == x || root.val == y) {
            return false;
        }
        isCousinsPart(root, x, y, record, 0, root.val);
        return record[0] == record[1] && record[2] != record[3];
    }

    public void isCousinsPart(TreeNode root, int x, int y, int[] record, int level, int fatherNodeVal) {

        if (root == null) {
            return;
        }
        if (root.val == x) {
            record[0] = level;
            record[2] = fatherNodeVal;
        } else if (root.val == y) {
            record[1] = level;
            record[3] = fatherNodeVal;
        }
        isCousinsPart(root.left, x, y, record, level + 1, root.val);
        isCousinsPart(root.right, x, y, record, level + 1, root.val);
        return;
    }

    /**
     * https://leetcode.com/problems/maximize-happiness-of-selected-children/
     * 3075. Maximize Happiness of Selected Children
     * @param happiness
     * @param k
     * @return
     */
    public long maximumHappinessSum(int[] h, int k) {
        long max = 0;
        int c = 0, value = 0;
        Arrays.sort(h);
        for (int i = h.length - 1; i >= h.length - k; i--) {
            value = h[i] - c++;
            if (value > 0) {
                max += value;
            } else {
                return max;
            }
        }
        return max;
    }

    /**
     * https://leetcode.com/problems/uncommon-words-from-two-sentences/
     * 884. Uncommon Words from Two Sentences
     * @param s1
     * @param s2
     * @return
     */
    public String[] uncommonFromSentences(String s1, String s2) {

        String[] s1Str = s1.split(" ");
        String[] s2Str = s2.split(" ");

        Map<String, Integer> record = new HashMap<>();
        for (int i = 0; i < s1Str.length; i++) {
            record.put(s1Str[i], record.getOrDefault(s1Str[i], 0) + 1);
        }
        for (int i = 0; i < s2Str.length; i++) {
            record.put(s2Str[i], record.getOrDefault(s2Str[i], 0) + 1);
        }
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : record.entrySet()) {
            if (entry.getValue() == 1) {
                list.add(entry.getKey());
            }
        }

        return list.toArray(new String[0]);
    }

    /**
     * https://leetcode.com/problems/sort-the-people/
     * 2418. Sort the People
     * @param names
     * @param heights
     * @return
     */
    public String[] sortPeople(String[] names, int[] heights) {
        // 使用 IntStream.range() 創建索引範圍，並根據身高降序排序名字
        String[] sortedNames = IntStream.range(0, names.length)
                .boxed()
                .sorted(Comparator.comparingInt(i -> heights[(int) i]).reversed())
                .map(i -> names[i])
                .toArray(String[]::new);

        return sortedNames;
    }

    public int maxLevelSum(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.add(root);

        int level = 1;
        int maxSumLevel = 1;
        int maxSum = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int sumOfLevel = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sumOfLevel += node.val;

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            if (sumOfLevel > maxSum) {
                maxSum = sumOfLevel;
                maxSumLevel = level;
            }
            level++;
        }

        return maxSumLevel;
    }

    /**
     * https://leetcode.com/problems/greatest-english-letter-in-upper-and-lower-case/
     * 2309. Greatest English Letter in Upper and Lower Case
     * @param s
     * @return
     */
    public String greatestLetter(String s) {

        //(int)'a'=97 (int)'z'=122
        //(int)'A'=65 (int)'Z'=90
        for (int i = 90; i >= 64; i--)
            //indexOf 如果 input 是 int，則代表 ACSII 的位置
            //如果要找字串，則輸入要使用String  ""
            if (s.indexOf(i) != -1 && s.indexOf(i + 32) != -1)
                return String.valueOf((char) i);
        return "";
    }

    /**
     * https://leetcode.com/problems/score-of-a-string/
     * 3110. Score of a String
     * @param s
     * @return
     */
    public int scoreOfString(String s) {

        char[] chars = s.toCharArray();

        int sum = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            sum += Math.abs((int) chars[i] - (int) chars[i + 1]);
        }
        return sum;
    }

    /**
     * https://leetcode.com/problems/count-good-nodes-in-binary-tree/
     * 1448. Count Good Nodes in Binary Tree
     * @param root
     * @return
     */
    public int goodNodes(TreeNode root) {

        // 加總
        int[] record = new int[1];
        goodNodesPart(root, record, Integer.MIN_VALUE);
        return record[0];
    }

    private void goodNodesPart(TreeNode root, int[] record, int maxInPath) {

        if (root == null) {
            return;
        }
        if (root.val >= maxInPath) {
            maxInPath = root.val;
            record[0]++;
        }
        goodNodesPart(root.left, record, maxInPath);
        goodNodesPart(root.right, record, maxInPath);
    }

    /**
     * https://leetcode.com/problems/delete-leaves-with-a-given-value/
     * @param root
     * @param target
     * @return
     */
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root.left != null) {
            root.left = removeLeafNodes(root.left, target);
        }
        if (root.right != null) {
            root.right = removeLeafNodes(root.right, target);
        }

        // We are doing this after above conditions because:
        // The previous NON LEAF nodes can become LEAF nodes now
        // Since the original leaf nodes skip the above 2 conditions and delete themselves here
        if (root.left == null && root.right == null && root.val == target) {
            root = null;
        }
        return root;
    }

    /**
     * https://leetcode.com/problems/destination-city/
     * 1436. Destination City
     * @param paths
     * @return
     */
    public String destCity(List<List<String>> paths) {

        HashMap<String, Integer> map = new HashMap();
        for (int i = 0; i < paths.size(); i++) {
            String src = paths.get(i).get(0);
            String dest = paths.get(i).get(1);
            map.put(src, map.getOrDefault(src, 0) + 1);
            if (!map.containsKey(dest)) {
                map.put(dest, 0);
            }
        }
        System.out.println(map);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0)
                return entry.getKey();
        }

        return null;
    }

    /**
     * https://leetcode.com/problems/rank-transform-of-an-array/
     * 1331. Rank Transform of an Array
     * @param arr
     * @return
     */
    public int[] arrayRankTransform(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();
        int[] temp = arr.clone();
        Arrays.sort(temp);

        int rank = 1;

        for (int num : temp) {
            if (!map.containsKey(num)) {
                map.put(num, rank);
                rank++;
            }
        }

        //now assign the ranks
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }

        return arr;
    }
}
