

import java.math.*;
import java.util.*;
import java.util.stream.*;

class LeetCode {

    Stack<TreeNode> publicStack = new Stack<>();
    List<List<Integer>> publicLists = new ArrayList<>();

    //(int)'a'=97 (int)'z'=122
    //(int)'A'=65 (int)'Z'=90

    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        List<Integer> layerOrder = Arrays.asList(3, 9, 20, null, null, 15, 7);
        int[] input = {1, 2, 3, 4};

        leetCode.greatestLetter("lEeTcOdE");
        //ListNode node = leetCode.createListByArray(input);
        //TreeNode treeNode = leetCode.createTree(layerOrder);
        //leetCode.levelOrder(treeNode);
        //leetCode.validPalindrome("abca");

    }

    //反转阵列
    public void reverseArray(int[] s) {
        for (int left = 0; left < s.length / 2; left++) {
            int right = s.length - left - 1;
            int temp = s[left];
            s[left] = s[right];
            s[right] = temp;
        }
    }

    //陣列指定index 交換
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * ListNode 是资料结构中的 节点
     * val = 这个节点当下的值
     * next = 指向下一个节点的指标
     */
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

        void 前序遍历(TreeNode treeNode) {
            System.out.println(preorderTraversal(treeNode));
            ;
        }

        void 中序遍历(TreeNode treeNode) {
            System.out.println(inorderTraversal(treeNode));
            ;
        }

        void 后序遍历(TreeNode treeNode) {
            System.out.println(postorderTraversal(treeNode));
            ;
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

    ;

    /**
     * list to Tree (层序插入)
     * @param layerOrder
     * @return
     */
    public TreeNode createTree(List<Integer> layerOrder) {
        int n = layerOrder.size();
        if (n == 0) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(layerOrder.get(0));
        queue.offer(root);

        for (int i = 0; i <= (n - 1) / 2; i++) {
            if (layerOrder.get(i) == null) {
                continue;
            }

            TreeNode now = queue.poll();
            int left = 2 * i + 1;
            int right = 2 * (i + 1);

            if (left < n && layerOrder.get(left) != null) {
                now.left = new TreeNode(layerOrder.get(left));
                queue.offer(now.left);
            }

            if (right < n && layerOrder.get(right) != null) {
                now.right = new TreeNode(layerOrder.get(right));
                queue.offer(now.right);
            }
        }
        return root;
    }

    /**
     * 层序遍历标准代码，基本上模板都一样
     * @param root
     * @return [[第一层] ,[第二层],[第三层]]
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.add(root);
        List<List<Integer>> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelVals = new ArrayList<>();

            //这个 size 是这一层有几个元素
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                levelVals.add(node.val);

                //因為 First in First out 的特性，所以左子葉先放進去，將會先被 poll
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            result.add(levelVals);
        }

        return result;
    }

    /**
     * https://leetcode.com/problems/binary-tree-inorder-traversal/
     * 树的中序遍历，意思是 左 - node - 右
     * @param root
     * @return
     */
    public static ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        List<Integer> listleft = inorderTraversal(root.left);
        list.addAll(listleft);
        list.add(root.val);
        List<Integer> listright = inorderTraversal(root.right);
        list.addAll(listright);
        return list;
    }

    /**
     * https://leetcode.com/problems/binary-tree-preorder-traversal/
     * 144. Binary Tree Preorder Traversal
     * 树的前序遍历 意思是 node - 左 - 右
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        list.add(root.val);
        List<Integer> listleft = preorderTraversal(root.left);
        list.addAll(listleft);
        List<Integer> listright = preorderTraversal(root.right);
        list.addAll(listright);
        return list;
    }

    /**
     * https://leetcode.com/problems/binary-tree-postorder-traversal/
     * 145. Binary Tree Postorder Traversal
     * 树的后序遍历，意思是 左-右-node
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        List<Integer> listleft = postorderTraversal(root.left);
        list.addAll(listleft);
        List<Integer> listright = postorderTraversal(root.right);
        list.addAll(listright);
        list.add(root.val);
        return list;
    }

    public ListNode mergeOddAndEvenListNode(ListNode oddListNode, ListNode evenListNode) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (oddListNode != null && evenListNode != null) {
            if (oddListNode.val < evenListNode.val) {
                current.next = oddListNode;
                oddListNode = oddListNode.next;
            } else {
                current.next = evenListNode;
                evenListNode = evenListNode.next;
            }
            current = current.next;
        }

        if (oddListNode != null) {
            current.next = oddListNode;
        } else {
            current.next = evenListNode;
        }
        return dummy.next;
    }

    /**
     * 尾插法构建带头节点的单链表
     * @param head
     * @param nums
     * @return
     */
    public static ListNode createListByArray(int[] nums) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        for (int i = 0; i < nums.length; i++) {
            p.next = new ListNode(nums[i]); //必须要新增物件，否则会 NPE
            p = p.next;
        }
        return head.next;
    }

    public static List<Integer> convertListNodeToList(ListNode head) {
        List<Integer> result = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        return result;
    }

    //判斷兩個陣列是否重疊 overLap
    private boolean isOverlapping(int[] array1, int[] array2) {
        return array1[1] > array2[0] && array2[1] > array1[0];
    }

    public static int[] int2ArrayInt(int n) {

        return null;
    }

    /**
     * https://leetcode.com/problems/binary-search/?envType=study-plan-v2&envId=binary-search
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        //如果没有在阵列中，会返回小于 0的数字
        int index = Arrays.binarySearch(nums, target);
        return index < 0 ? -1 : index;
    }

    /**
     * https://leetcode.com/problems/find-smallest-letter-greater-than-target/?envType=study-plan-v2&envId=binary-search
     * 744. Find Smallest Letter Greater Than Target
     * 二分查找范例
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {

        int s = 0;
        int e = letters.length - 1;
        while (s <= e) {
            int mid = (e + s) / 2;
            if (target < letters[mid]) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return letters[s % letters.length];
    }

    /**
     * https://leetcode.com/problems/longest-common-prefix/
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        //result arrray
        StringBuilder result = new StringBuilder();
        //sort array according to array
        Arrays.sort(strs);
        //get the first and last string
        String first = strs[0];
        String last = strs[strs.length - 1];

        //comparing
        for (int i = 0; i < first.length(); i++) {
            //排好的 array，首字母也会排，所以比较 first 跟 last 即可
            if (first.charAt(i) != last.charAt(i)) {
                break;
            }
            result.append(first.charAt(i));
        }
        return result.toString();

    }

    /**
     * https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
     * 1351. Count Negative Numbers in a Sorted Matrix
     * 簡單的數學，因為本來 array 是被排列過的，可以利用此特性提早返回
     * @param grid
     * @return
     */
    public int countNegatives(int[][] grid) {

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] < 0) {
                    //這一句的意思是，array 中的數字是由大到小
                    count = count + (grid[0].length - j);
                    break;
                }
            }
        }
        return count;
    }

    /**
     * 1480. Running Sum of 1d Array
     * <A HREF="https://leetcode.com/problems/running-sum-of-1d-array/">link</A>.
     * perfie sum
     */
    public int[] runningSum(int[] nums) {

        int tempSum = 0;
        int lens = nums.length;
        int[] resultArray = new int[lens];
        for (int i = 0; i < lens; i++) {
            tempSum = tempSum + nums[i];
            resultArray[i] = tempSum;
        }
        //System.out.println(Arrays.toString(resultArray));
        return resultArray;
    }

    /**
     * https://leetcode.com/problems/search-insert-position/
     * 35. Search Insert Position
     */
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     * https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/549/
     * 找出 single num
     * Single Number
     */
    public int singleNumber(int[] nums) {

        int tempSum = 0;
        int lens = nums.length;
        HashSet<Integer> set = new HashSet(lens / 2);
        for (int i = 0; i < lens; i++) {
            if (set.contains(nums[i])) {
                tempSum = tempSum - nums[i];
            } else {
                set.add(nums[i]);
                tempSum = tempSum + nums[i];
            }
        }
        return tempSum;
    }

    /**
     * https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/546/
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        //解题思路：原本使用两个回圈，先放到map中再遍历，但发现说无法解决 [3,3] 这个检验，查别的做法有更好的
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < n; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    /**
     * Remove Duplicates from Sorted Array
     * https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/727/
     */
    public int removeDuplicates(int[] nums) {
        int lens = nums.length;
        int pivot = 1;

        for (int i = 0; i < lens - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                nums[pivot] = nums[i + 1];
                pivot++;
            }
        }
        return pivot;
    }

    /**
     * https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/564/
     * Best Time to Buy and Sell Stock II
     * tip: 顺序不影响最大利润
     */
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                //找到最佳買入時機
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }

    /**
     * https://leetcode.com/problems/top-k-frequent-elements/
     * 347. Top K Frequent Elements
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(frequencyMap.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue())); // 根据 value 进行排序

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).getKey();
        }
        return result;
    }

    /**
     * 巴斯卡金字塔
     * https://leetcode.com/problems/pascals-triangle/
     * 118. Pascal's Triangle
     */
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> resultList = new ArrayList<>();
        for (int x = 0; x < numRows; x++) {
            List<Integer> innerList = new ArrayList<>();
            for (int y = 0; y <= x; y++) {
                if (x == 0) {
                    //处理首行
                    innerList.add(1);
                } else {
                    //处理第二行开始
                    if (y == 0) {
                        innerList.add(resultList.get(x - 1).get(0));
                    } else if (x == y) {
                        innerList.add(resultList.get(x - 1).get(y - 1));
                    } else {
                        innerList.add(resultList.get(x - 1).get(y - 1) + resultList.get(x - 1).get(y));
                    }
                }
            }
            resultList.add(innerList);
        }
        //System.out.println(resultList);
        return resultList;
    }

    /**
     * https://leetcode.com/problems/roman-to-integer/description/
     * 13. Roman to Integer
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && map.get(s.charAt(i)) > map.get(s.charAt(i - 1))) {
                result += map.get(s.charAt(i)) - 2 * map.get(s.charAt(i - 1));
            } else {
                result += map.get(s.charAt(i));
            }
        }
        return result;
    }

    /**
     * 巴斯卡金字塔2
     * https://leetcode.com/problems/pascals-triangle-ii/
     * 119. Pascal's Triangle II
     */
    public List<Integer> getRow(int numRows) {

        List<List<Integer>> resultList = new ArrayList<>();
        for (int x = 0; x <= numRows; x++) {
            List<Integer> innerList = new ArrayList<>();
            for (int y = 0; y <= x; y++) {
                if (x == 0) {
                    //处理首行
                    innerList.add(1);
                } else {
                    //处理第二行开始
                    if (y == 0) {
                        innerList.add(resultList.get(x - 1).get(0));
                    } else if (x == y) {
                        innerList.add(resultList.get(x - 1).get(y - 1));
                    } else {
                        innerList.add(resultList.get(x - 1).get(y - 1) + resultList.get(x - 1).get(y));
                    }
                }
            }
            resultList.add(innerList);
        }
        //System.out.println(resultList);
        System.out.println();
        if (resultList.isEmpty()) {
            return new ArrayList<>();
        } else {
            return resultList.get(resultList.size() - 1);
        }

    }

    /**
     * <a href="https://leetcode.com/problems/merge-strings-alternately/description/?envType=study-plan-v2&envId=leetcode-75">...</a>
     * 1768. Merge Strings Alternately
     * @param word1
     * @param word2
     * @return
     */
    public static String mergeAlternately(String word1, String word2) {
        StringBuilder mergedString = new StringBuilder();
        int length1 = word1.length();
        int length2 = word2.length();
        int maxLength = Math.max(length1, length2);

        for (int i = 0; i < maxLength; i++) {
            if (i < length1) {
                mergedString.append(word1.charAt(i));
            }
            if (i < length2) {
                mergedString.append(word2.charAt(i));
            }
        }

        return mergedString.toString();
    }

    /**
     * <a href="https://leetcode.com/problems/move-zeroes/description/?envType=study-plan-v2&envId=leetcode-75">...</a>
     * 283. Move Zeroes
     * @param nums
     */
    public void moveZeroes(int[] nums) {

        //初始化一个指标
        int insertPos = 0;

        //思路：把不是0的数字往前填
        for (int num : nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }

        //其余的补0
        while (insertPos < nums.length) {
            nums[insertPos] = 0;
            insertPos++;
        }

    }

    /**
     * https://leetcode.com/problems/majority-element/
     * 169. Majority Element
     * Boyer-Moore 投票算法
     * 如果投票 = 候选人，加票数，反之则减票数，如果当 count = 0 时，候选人换人
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count = 1;
            } else if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }

    /**
     * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
     * 83. Remove Duplicates from Sorted List
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            // If the value of curr is equal to the value of prev...
            // It means the value is present in the linked list...
            if (curr.val == curr.next.val) {
                // Hence we do not need to include curr again in the linked list...
                // So we increment the value of curr...
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    /**
     * https://leetcode.com/problems/power-of-two/
     * 231. Power of Two
     * 检测n为2的倍数
     * 100%
     */
    public boolean isPowerOfTwo(int n) {
        while (n > 1) {
            if (n % 2 == 1) {
                return false;
            }
            n = n / 2;
        }
        if (n == 1) {
            return true;
        }
        return false;
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
     * 本地正确 丢上去报错
     * Rotate Array
     * https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/646/
     * 莫名的难，各种例外搞你
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }

    /**
     * https://leetcode.com/problems/valid-parentheses/
     * <p>
     * push() 推到最上层
     * pop() 拿出最上层
     * peek() 看最上层元素是什么
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 125. Valid Palindrome
     * https://leetcode.com/problems/valid-palindrome/
     * 回文 -> 雙指針
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {

        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        char[] strChar = actual.toCharArray();
        int low = 0;
        int high = actual.length() - 1;

        while (low <= high) {
            if (strChar[low] != strChar[high]) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }

    /**
     * 121. Best Time to Buy and Sell Stock
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     * 思路：two pointer
     * @param prices
     * @return 想法：一开始想找最大最小值，但发现没办法，参考网友的做法直接找最大利润最快
     */
    public int maxProfit2(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;
        int lens = prices.length;

        for (int i = 0; i < lens; i++) {
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            }
            if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }

        return maxProfit;
    }

    /**
     * https://leetcode.com/problems/plus-one/
     * 66. Plus One
     * @param digits
     * @return beat 100%
     */
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int right = len - 1;
        if (digits[right] < 9) {
            digits[right]++;
            return digits;
        } else {
            for (int i = right; i >= 0; i--) {
                if (digits[i] < 9) {
                    digits[i]++;
                    return digits;
                } else {
                    if (i == 0) {
                        int[] newNumbers = new int[len + 1];
                        //java int default value is 0 !!! so you don't need to copy ;
                        //System.arraycopy(digits, 0, newNumbers, 1 , len);
                        //newNumbers[1] = 0 ;
                        newNumbers[0] = 1;
                        return newNumbers;
                    }
                    digits[i] = 0;
                }

            }
        }
        return digits;
    }

    /**
     * https://leetcode.com/problems/delete-node-in-a-linked-list/
     * 37. Delete Node in a Linked List
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
        //題目有說這個 node 不在最後一個，如果是在最後一個，範例代碼如下
        //    if (node.next == null) {
        //    node = null; // 將該節點設置為null
        //} else {
        //    node.val = node.next.val; // 將該節點的值設置為下一個節點的值
        //    node.next = node.next.next; // 將該節點指向下一個節點的下一個節點
        //}
    }

    /**
     * https://leetcode.com/problems/isomorphic-strings/
     * 205. Isomorphic Strings
     * 想法：两个 map 互相纪录值
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        int sLens = sChar.length;
        Map<Character, Character> sMap = new HashMap<>(sLens);
        Map<Character, Character> tMap = new HashMap<>(sLens);
        for (int i = 0; i <= sLens - 1; i++) {
            Character sCharacter = Character.valueOf(sChar[i]);
            Character tCharacter = Character.valueOf(tChar[i]);
            if (sMap.containsKey(sCharacter)) {
                if (!sMap.get(sCharacter).equals(tCharacter)) {
                    return false;
                }
            } else if (tMap.containsKey(tCharacter)) {
                if (!tMap.get(tCharacter).equals(sCharacter)) {
                    return false;
                }
            } else {
                sMap.put(sCharacter, tCharacter);
                tMap.put(tCharacter, sCharacter);
            }
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/valid-anagram/
     * 242. Valid Anagram
     * 想法：参考网路上，char 有一个目标值，数字按此分配
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        char[] sCharacter = s.toCharArray();
        char[] tCharacter = t.toCharArray();
        if (sCharacter.length != tCharacter.length) {
            return false;
        }

        //26 chars in english
        int[] n1 = new int[26];
        for (char c : sCharacter) {
            n1[c - 'a']++;
        }
        for (char c : tCharacter) {
            n1[c - 'a']--;
            if (n1[c - 'a'] < 0) {
                return false;
            }
        }
        for (int i : n1) {
            if (n1[i] > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/intersection-of-two-arrays/
     * 349. Intersection of Two Arrays
     * 活用 set的去重
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> num1Set = new HashSet<>();
        List<Integer> ansList = new ArrayList<>();
        for (int i : nums1) {
            num1Set.add(i);
        }
        for (int j : nums2) {
            if (num1Set.remove(j)) {
                ansList.add(j);//移除已加到答案中的数字，但速度没有增加，可能remove是个高消耗的操作
            }
        }
        int[] ans = new int[ansList.size()];
        int index = 0;
        for (Integer integer : ansList) {
            ans[index] = integer;
            index++;
        }
        return ans;
    }

    /**
     * 263. Ugly Number
     * https://leetcode.com/problems/ugly-number/description/
     * ugly number 意思是 2,3,5 的倍数
     * @param n
     * @return
     */
    public boolean isUgly(int n) {
        if (n == 0) {
            return false;
        }
        while (n % 30 == 0) {
            n = n / 30;
        }
        while (n % 5 == 0) {
            n = n / 5;
        }
        while (n % 3 == 0) {
            n = n / 3;
        }
        while (n % 2 == 0) {
            n = n / 2;
        }
        return (n == 1);
    }

    /**
     * https://leetcode.com/problems/summary-ranges/
     * 228. Summary Ranges
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        ArrayList<String> al = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            //在回圈中使用一个指标去改变 nums指到的值
            while (i + 1 < nums.length && nums[i] + 1 == nums[i + 1]) {
                i++;
            }

            if (start != nums[i]) {
                al.add("" + start + "->" + nums[i]);
            } else {
                al.add("" + start);
            }
        }
        return al;
    }

    /**
     * https://leetcode.com/problems/palindrome-number/description/
     * 9. Palindrome Number
     * hint : 题目不准转成字串 -> 好难
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int reversed = 0;
        while (x > reversed) {
            int digit = x % 10;
            x /= 10;
            reversed = reversed * 10 + digit;
        }
        return x == reversed || x == reversed / 10;
    }

    /**
     * https://leetcode.com/problems/is-subsequence/
     * 392. Is Subsequence
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {

        //t.length > s.length
        char[] sC = s.toCharArray();
        char[] tC = t.toCharArray();
        int tLens = tC.length;
        int sLens = sC.length;
        if (tLens == 0 && sLens == 0) {
            return true;
        } else if (tLens == 0) {
            return false;
        } else if (sLens == 0) {
            return true;
        }

        int startPoint = 0;
        for (int i = 0; i < tLens; i++) {
            if (startPoint == sLens) {
                return true;
            }
            if (sC[startPoint] == tC[i]) {
                startPoint++;
            }
        }
        if (startPoint == sLens) {
            return true;
        }

        return false;
    }

    /**
     * 动态规划经典
     * https://leetcode.com/problems/climbing-stairs/description/
     * 70. Climbing Stairs
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int[] ways = new int[n + 1];
        ways[0] = 1; //to the 0 stairs ways
        ways[1] = 1; //to the 1 stairs ways

        for (int i = 2; i <= n; i++) {
            ways[i] = ways[i - 1] + ways[i - 2];
        }

        return ways[n];
    }

    /**
     * {@link https://leetcode.com/problems/merge-two-sorted-lists/}
     * 21. Merge Two Sorted Lists
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //dummyHead 是一个头节点，方便串接接下来的列表
        //虚拟头节点是一个特殊的节点，它不存储任何数据，只是用来标记合并后的链表的头节点。
        // 因此，我们不能直接操作虚拟头节点，否则会破坏它的作用。
        ListNode dummyHead = new ListNode(0);
        //current 是一个指针，在接下来的回圈中，current 会不断地往下
        ListNode current = dummyHead;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        //处理最后
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        //返回头节点的下一个
        return dummyHead.next;
    }

    /**
     * https://leetcode.com/problems/linked-list-cycle/
     * 141. Linked List Cycle
     * 题目：判断这是不是一个 linkedList circle
     * 思路：利用快慢指针来做题，如果指针一样，表示是个 circle
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        //if null it will not be a circle
        //fast 会先达到练尾的末端，所以 null 时，就是 false
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            //此时 == 比较两者的 hashCode 而不是值，值得话要看 fast.val
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * todo
     * 这一题是上一题的延伸题
     * 要求找出给定链表中循环的起始位置。
     * https://leetcode.com/problems/linked-list-cycle-ii/
     * 142. Linked List Cycle II
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode last = head;
        //if null it will not be a circle
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            //当快慢指针相遇时，我们使用一个新的指针从头节点开始，与慢指针以相同速度前进，直到它们再次相遇。相遇的节点即为循环的起始位置。
            if (fast == slow) {
                ListNode start = head;
                while (start != slow) {
                    start = start.next;
                    slow = slow.next;
                }
                return start;
            }
        }
        return null;
    }

    /**
     * 动态规划
     * https://leetcode.com/problems/happy-number/solutions/?q=java&orderBy=most_relevant
     * 202. Happy Number
     * @param n
     * @return
     */
    public boolean isHappy(int n) {

        Set<Integer> tempSet = new HashSet<>();
        while (!tempSet.contains(n) && n > 1) {
            tempSet.add(n);
            int n1 = n;
            int sum1 = 0;
            while (n1 != 0) {
                int temp = n1 % 10;
                sum1 += temp * temp;
                n1 = n1 / 10;
            }
            n = sum1;
        }
        if (n == 1) {
            return true;
        }
        return false;
    }

    /**
     * Linked List
     * https://leetcode.com/problems/remove-linked-list-elements/
     * 203. Remove Linked List Elements
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode result = new ListNode(); //虚拟node
        result.next = head; //将 head 接上
        ListNode curr = head;
        ListNode prev = result; // we keep prev pointer so its easy to delete

        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = prev.next;
            }
            curr = curr.next;
        }

        return result.next; //第一个 node 不返回
    }

    /**
     * math
     * 204. Count Primes
     * https://leetcode.com/problems/count-primes/description/
     * boolean array : boolean 预设为 false，将true放入 index 中，
     * 這題用一般的找質數，就會變很慢
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int counter = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                counter++;
                for (int j = 2; i * j < n; j++) { //if n * j < n 才跑回圈
                    notPrime[i * j] = true;
                }
            }
        }
        return counter;
    }

    /**
     * LinkedList
     * 876. Middle of the Linked List
     * https://leetcode.com/problems/middle-of-the-linked-list/
     * 找出 list 的中間，快慢指針，重要
     */
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode curr = head;
        ListNode result = head;

        int count = 0;
        //cuz result step half of curr , so it will be half long about curr
        while (curr != null && curr.next != null) {
            curr = curr.next.next;
            result = result.next;
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/ransom-note/
     * 383. Ransom Note
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        //record char array 的標準寫法
        int[] arr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            arr[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (--arr[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/word-pattern/
     * 290. Word Pattern
     * @param pattern
     * @param s
     * @return
     */
    public static boolean wordPattern(String pattern, String s) {

        String[] patternArray = pattern.split("");
        String[] sArray = s.split(" ");

        Map<String, String> map = new HashMap<>(patternArray.length);
        Map<String, String> map2 = new HashMap<>(sArray.length);

        if (patternArray.length != sArray.length) {
            return false;
        }

        for (int i = 0; i < patternArray.length; i++) {
            System.out.println(patternArray[i]);
            System.out.println(sArray[i]);
            if (map.containsKey(patternArray[i])) {
                if (!map.get(patternArray[i]).equals(sArray[i])) {
                    return false;
                }
            } else if (map2.containsKey(sArray[i])) {
                if (!map2.get(sArray[i]).equals(patternArray[i])) {
                    return false;
                }
            } else {
                map.put(patternArray[i], sArray[i]);
                map2.put(sArray[i], patternArray[i]);
            }
        }
        return true;
    }

    /**
     * 112. Path Sum
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {

        if (root == null) {
            return false;
        }
        //到達子葉
        if (root.right == null && root.left == null) {
            //判斷是否跟目標一致
            if (root.val == targetSum) {
                return true;
            }
        }
        int diff = targetSum - root.val;
        return hasPathSum(root.left, diff) || hasPathSum(root.right, diff);
    }

    /**
     * https://leetcode.com/problems/swap-nodes-in-pairs/
     * 24. Swap Nodes in Pairs
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        System.out.println(current.size());

        //null 代表已经到了尾
        while (current.next != null && current.next.next != null) {
            int x = current.next.val;
            int y = current.next.next.val;
            current.next.val = y;
            current.next.next.val = x;
            //只有给自己值的时候才是改变位子
            current = current.next.next;
        }
        return dummy.next;
    }

    /**
     * 水坝那一题，双指针经典题
     * 11. Container With Most Water
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    /**
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;

        // Calculate the sum of the first k elements
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        // Update the maximum sum with the initial sum
        int maxSum = sum;

        // Slide the window and update the sum
        // 将右边加入，同时将左侧排除
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }

        // Calculate the maximum average
        double maxAverage = (double) maxSum / k;

        return maxAverage;
    }

    /**
     * 1456. Maximum Number of Vowels in a Substring of Given Length
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {
        char[] aeiou = {'a', 'e', 'i', 'o', 'u'};
        Set<Character> vowelSet = new HashSet<>();
        for (char vowel : aeiou) {
            vowelSet.add(vowel);
        }

        char[] chars = s.toCharArray();
        int max = 0;

        for (int i = 0; i < k; i++) {
            if (vowelSet.contains(chars[i])) {
                max++;
            }
        }
        if (max >= k) {
            return max;
        }
        int maxCount = max;

        // Slide the window and update the count of vowels
        for (int i = k; i < s.length(); i++) {
            if (vowelSet.contains(s.charAt(i))) {
                max++;
            }
            if (vowelSet.contains(s.charAt(i - k))) {
                max--;
            }

        }

        return maxCount;
    }

    /**
     * 找出最高的海拔
     * <a href="https://leetcode.com/problems/find-the-highest-altitude/description/?envType=study-plan-v2&envId=leetcode-75">...</a>
     * 1732. Find the Highest Altitude
     * @param gain
     * @return
     */
    public int largestAltitude(int[] gain) {
        int maxAltitude = 0; // 最高海拔
        int currentAltitude = 0; // 当前海拔

        for (int i = 0; i < gain.length; i++) {
            currentAltitude += gain[i]; // 计算当前海拔
            if (currentAltitude > maxAltitude) {
                maxAltitude = currentAltitude;// 更新最高海拔
            }
        }
        return maxAltitude;
    }

    /**
     * <a href="https://leetcode.com/problems/find-pivot-index/description/?envType=study-plan-v2&envId=leetcode-75">...</a>
     * 724. Find Pivot Index
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int totalSum = Arrays.stream(nums).sum();
        int leftSum = 0;

        for (int i = 0; i < nums.length; i++) {
            //左边总和 = 右边总和 - 左边总和 - index值
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    /**
     * <a href="https://leetcode.com/problems/merge-sorted-array/?envType=study-plan-v2&envId=top-interview-150">...</a>
     * 88. Merge Sorted Array
     * 寫法簡潔但很慢，快的寫法看起來很多
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        while (n > 0) {
            nums1[m + n - 1] = nums2[n - 1];
            n--;
        }
        Arrays.sort(nums1);
    }

    /**
     * https://leetcode.com/problems/remove-element/description/?envType=study-plan-v2&envId=top-interview-150
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /**
     * https://leetcode.com/problems/jump-game/?envType=study-plan-v2&envId=top-interview-150
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int maxToReach = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > maxToReach) return false;
            //nums[i] + i 表示目前已走了几步
            maxToReach = maxToReach > nums[i] + i ? maxToReach : nums[i] + i;
            if (maxToReach >= length - 1) return true;
        }
        return false;
    }

    /**
     * https://leetcode.com/problems/can-place-flowers/description/?envType=study-plan-v2&envId=leetcode-75
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                int prev = (i == 0) ? 0 : flowerbed[i - 1];
                int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
                if (prev == 0 && next == 0) {
                    flowerbed[i] = 1;
                    count++;
                }
            }
        }
        return count >= n;
    }

    /**
     * 345. Reverse Vowels of a String
     * https://leetcode.com/problems/reverse-vowels-of-a-string/?envType=study-plan-v2&envId=leetcode-75
     * tip : 使用 stack 来做反转
     * todo : 使用双指针改良
     */
    public String reverseVowels(String s) {

        List<Character> vowelList = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

        char[] strChar = s.toCharArray();
        Stack<Character> aeiouInStr = new Stack<>();

        for (int i = 0; i < strChar.length; i++) {
            if (vowelList.contains(strChar[i])) {
                aeiouInStr.push(strChar[i]);
            }
        }
        for (int i = 0; i < strChar.length; i++) {
            if (vowelList.contains(strChar[i])) {
                strChar[i] = aeiouInStr.pop();
            }
        }
        String result = new String(strChar);
        return result;
    }

    /**
     * 2215. Find the Difference of Two Arrays
     * https://leetcode.com/problems/find-the-difference-of-two-arrays
     * 94.97%
     * @param nums1
     * @param nums2
     * @return
     */
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            set2.add(num);
        }

        List<Integer> diff1 = new ArrayList<>(set1);
        diff1.removeAll(set2);

        List<Integer> diff2 = new ArrayList<>(set2);
        diff2.removeAll(set1);

        List<List<Integer>> answer = new ArrayList<>();
        answer.add(diff1);
        answer.add(diff2);

        return answer;
    }

    /**
     * 1207. Unique Number of Occurrences
     * https://leetcode.com/problems/unique-number-of-occurrences/description/?envType=study-plan-v2&envId=leetcode-75
     * tip :
     * Integer 直接把 int[] 转型
     * getOrDefault 减少代码
     * 先用 map 再用 set 解题
     * occurences.values() 预设的型态是 collection，也就是 List
     */
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> occurences = new HashMap<>();
        for (Integer num : arr) {
            occurences.put(num, occurences.getOrDefault(num, 0) + 1);
        }

        Set<Integer> instances = new HashSet<>();

        for (Integer value : occurences.values()) {
            if (instances.contains(value)) return false;
            instances.add(value);
        }

        return true;
    }

    /**
     * https://leetcode.com/problems/removing-stars-from-a-string/
     * 2390. Removing Stars From a String
     * hint 推荐使用 stack，但直接在字串处理反而比较快
     */
    public String removeStars(String s) {
        Stack<Character> resultStack = new Stack<>();
        char[] strChar = s.toCharArray();
        for (char c : strChar) {
            if (c != '*') {
                resultStack.push(c);
            } else if (!resultStack.isEmpty()) {
                resultStack.pop();
            }
        }
        StringBuilder reversedString = new StringBuilder();

        for (Character c : resultStack) {
            reversedString.append(c);
        }
        System.out.println(reversedString);
        return reversedString.toString();
    }

    /**
     * 字串反转2
     * https://leetcode.com/problems/reverse-string-ii/
     * 541. Reverse String II
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {

        String first = s.substring(0, k);
        String second = s.substring(k, s.length());
        StringBuilder sb = new StringBuilder(first);
        return sb.reverse() + second;
    }

    /**
     * 字串反转3
     * https://leetcode.com/problems/reverse-words-in-a-string-iii/
     * 557. Reverse Words in a String III
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(new StringBuilder(word).reverse()).append(" ");
        }
        return result.toString().trim();
    }

    public int nextGreaterElement(int n) {
        return -1;
    }

    /**
     * https://leetcode.com/problems/missing-number/
     * 268. Missing Number
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {

        int highestNum = nums.length;
        int total = 0;
        while (highestNum > 0) {
            total = total + highestNum;
            highestNum--;
        }
        int totalForArray = 0;
        for (int i = 0; i < nums.length; i++) {
            totalForArray = totalForArray + nums[i];

        }

        return total - totalForArray;
    }

    /**
     * https://leetcode.com/problems/find-the-duplicate-number/
     * 287. Find the Duplicate Number
     * 思路，使用另一个 array来记录有填过的数字
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {

        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (temp[value] > 0) {
                return value;
            } else {
                temp[value] = 1;
            }
        }
        return 0;
    }

    /**
     * https://leetcode.com/problems/first-unique-character-in-a-string/
     * 387. First Unique Character in a String
     * agoda
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {

        int[] dictory = new int[26];
        char[] strChar = s.toCharArray();
        int lastIndex = -1;
        for (int i = 0; i < strChar.length; i++) {
            dictory[strChar[i] - 'a']++;
        }

        for (int i = 0; i < strChar.length; i++) {
            if (dictory[strChar[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * https://leetcode.com/problems/find-the-difference/
     * 389. Find the Difference
     * @param s
     * @param t t.length == s.length + 1
     * tip : char has int value in java .
     * @return
     */
    public char findTheDifference(String s, String t) {
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();

        int totalS = tChar[tChar.length - 1];
        for (int i = 0; i < s.length(); i++) {
            totalS = totalS + tChar[i] - sChar[i];
        }
        return (char) (totalS);
    }

    /**
     * https://leetcode.com/problems/sort-array-by-parity/
     * 905. Sort Array By Parity
     * 双指针
     * 将所有偶数都移到前面，后面跟着奇数
     * @param nums
     * @return
     */
    public int[] sortArrayByParity(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        int indexForLeft = 0;
        int indexForRight = nums.length - 1;
        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (indexForLeft > indexForRight) {
                return result;
            }
            if (nums[i] % 2 == 0) {
                result[indexForLeft] = nums[i];
                indexForLeft++;
            } else {
                result[indexForRight] = nums[i];
                indexForRight--;
            }
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/generate-parentheses/
     * 22. Generate Parentheses
     * topic : 动态规划
     * @param n
     * @return
     */

    public List<String> generateParenthesis(int n) {
        char[] ch = new char[n + n];
        int open = 0, index = 0, close = 0;
        List<String> list = new ArrayList<>();
        generate(n, open, close, index, ch, list);
        return list;
    }

    // 22题的子方法
    private void generate(int n, int open, int close, int index, char[] ch, List<String> list) {
        if (index == ch.length) {
            list.add(new String(ch));
            return;
        }
        if (open < n) {
            ch[index] = '(';
            generate(n, open + 1, close, index + 1, ch, list);
        }
        if (close < open) {
            ch[index] = ')';
            generate(n, open, close + 1, index + 1, ch, list);
        }
    }

    /**
     * https://leetcode.com/problems/group-anagrams/?envType=study-plan-v2&envId=top-interview-150
     * 49. Group Anagrams
     * @param numbers
     * @param target
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groupedAnagrams = new HashMap<>();
        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            if (!groupedAnagrams.containsKey(key)) {
                groupedAnagrams.put(key, new ArrayList<>());
            }
            groupedAnagrams.get(key).add(s);
        }
        return new ArrayList<>(groupedAnagrams.values());
    }

    /**
     * https://leetcode.com/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-interview-150
     * 128. Longest Consecutive Sequence
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int temp = nums[0];
        int count = 1;
        int longest = 1;
        for (int i = 1; i < nums.length; i++) {
            if (temp + 1 == nums[i]) {
                longest++;
            } else {
                if (count > longest) {
                    longest = count;
                }
                count = 1;
            }
            temp = nums[i];
        }
        return longest;
    }

    /**
     * https://leetcode.com/problems/reverse-linked-list-ii/?envType=study-plan-v2&envId=top-interview-150
     * 92. Reverse Linked List II
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }
        ListNode temp = new ListNode(0);
        temp.next = head;

        ListNode prev = temp;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        ListNode present = prev.next;
        System.out.println(present);
        for (int i = 0; i < right - left; i++) {
            ListNode then = present.next;
            present.next = then.next;
            then.next = prev.next;
            prev.next = then;
        }
        return temp.next;
    }

    /**
     * https://leetcode.com/problems/count-pairs-whose-sum-is-less-than-target/
     * 2824. Count Pairs Whose Sum is Less than Target
     * @param nums
     * @param target
     * @return
     */
    public int countPairs(List<Integer> nums, int target) {

        int count = 0;

        //由后往前数
        for (int i = nums.size() - 1; i > 0; i--) {
            int different = target - nums.get(i);
            for (int j = 0; j < i; j++) {
                if (nums.get(j) < different) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * https://leetcode.com/problems/minimum-common-value/
     * 2540. Minimum Common Value
     * ps : 因為是排序過後的資料
     * 可以用 binary search
     * @param nums1
     * @param nums2
     * @return
     */
    public int getCommon(int[] nums1, int[] nums2) {
        int i, j;
        i = 0;
        j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        return -1;
    }

    /**
     * 延续上一题，使用快慢指针
     * @param nums1
     * @param nums2
     * @return
     */
    public int getCommonUseTeoPointer(int[] nums1, int[] nums2) {

        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            int num1 = nums1[i];
            int num2 = nums2[j];
            if (num1 == num2) {
                return num1;
            } else if (num1 < num2) {
                i++;
            } else {
                j++;
            }
        }
        return -1;
    }

    /**
     * https://leetcode.com/problems/distribute-money-to-maximum-children/
     * 2591. Distribute Money to Maximum Children
     * @param money
     * @param children rule1 : 每个人都要分到钱
     * rule2 : 至少一员
     * rule3: 不可以是四元
     * @return 收到8元的最大数量
     */
    public int distMoney(int money, int children) {

        return 0;
    }

    /**
     * https://leetcode.com/problems/3sum-closest/
     * 16. 3Sum Closest
     * topic : 双指针
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        //先对array 排序
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                    closestSum = sum;
                }
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum; // 如果找到等于 target 的情况，直接返回
                }
            }
        }

        return closestSum;
    }

    /**
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
     * 19. Remove Nth Node From End of List
     * 快慢指针
     * @param head
     * @param n "倒数n个"
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy, second = head;

        //n数完以后，表示second 走了n步
        //second 会先走到底
        while (n > 0 && second != null) {
            second = second.next;
            n--;
        }

        //first 跟 second 各走一样步数，但 second先走了
        while (second != null) {
            first = first.next;
            second = second.next;
        }

        //把 first.next 接上 first.next.next;
        first.next = first.next.next;
        return dummy.next;
    }

    /**
     * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
     * 34. Find First and Last Position of Element in Sorted Array
     * todo 可以用二分搜寻法改进
     * @param nums
     * @param target 找出这个号码在这个 array中的起始跟结束 index
     */
    public int[] searchRange(int[] nums, int target) {

        int[] resultArray = {-1, -1};
        if (nums.length < 1) {
            return resultArray;
        }
        boolean findStart = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target && !findStart) {
                resultArray[0] = i;
                resultArray[1] = i;
                findStart = true;
            }
            if (nums[i] > target && findStart) {
                resultArray[1] = i - 1;
                break;
            } else if (i == nums.length - 1 && nums[i] == target) {
                resultArray[1] = i;
                break;
            }
        }
        return resultArray;

    }

    /**
     * https://leetcode.com/problems/contains-duplicate-ii/?envType=study-plan-v2&envId=top-interview-150
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer recordNum = map.get(nums[i]);
            if (recordNum != null) {
                if (Math.abs(recordNum - i) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }

    /**
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/
     * 3. Longest Substring Without Repeating Characters
     * 滑动窗口
     * @param s
     * @return 最小重复单位
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            if (map.containsKey(currentChar)) {
                //+1 的目的是确保左边的指针移动到重复 String 的右边一格，因为当下的 Str 已经被判断过了
                left = Math.max(left, map.get(currentChar) + 1);
            }
            map.put(currentChar, right);
            //这里的+1 是因为右指针 减 左指针 后，需要 +1 才能得到真实的长度
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    /**
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list/submissions/1175023251/
     * 209. Minimum Size Subarray Sum
     * slide windows
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int minLength = nums.length + 1;

        for (int right = 0; right < nums.length; right++) {
            sum = sum + nums[right];
            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum = sum - nums[left];
                left++;
            }
        }
        return minLength > nums.length ? 0 : minLength;
    }

    /**
     * https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/?envType=study-plan-v2&envId=leetcode-75
     * 2095. Delete the Middle Node of a Linked List
     * 快慢指针
     * fast 走到底的时候， slow 刚好就是一半
     * @param head
     * @return
     */
    public ListNode deleteMiddle(ListNode head) {

        if (head == null) {
            return head;
        }
        if (head.next == null) {
            head = null;
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;

        while (fast != null && fast.next != null) {
            //pre纪录未移动前的 slow位子
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }

        //删除中间节点
        pre.next = slow.next;
        //System.out.println(fast);
        return head;
    }

    /**
     * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/?envType=study-plan-v2&envId=top-interview-150
     * 82. Remove Duplicates from Sorted List II
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesV2(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy; // prev 指针用于指向不重复的节点
        ListNode current = head; // current 指针用于遍历整个炼表

        while (current != null) {
            // 如果发现重复元素，则跳过所有重复的节点
            if (current.next != null && current.val == current.next.val) {
                current = current.next;
            }
            // 如果没有发现重复元素，则将 prev 指针向前移动一个位置
            if (prev.next != current) {
                prev.next = current.next;
            } else {
                prev = prev.next;
            }
            current = current.next; // 将 current 指针向前移动一个位置

        }
        return dummy.next; // 返回虚拟节点(dummy node)的下一个节点作为结果
    }

    /**
     * https://leetcode.com/problems/partition-list/?envType=study-plan-v2&envId=top-interview-150
     * 86. Partition List
     * @param head ≤
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        System.out.println(head);

        ListNode lessThanX = new ListNode(0);
        ListNode greatOrEqualX = new ListNode(0);
        ListNode lessThanXPointer = lessThanX;
        ListNode greatOrEqualXPointer = greatOrEqualX;

        while (head != null) {
            if (head.val < x) {
                lessThanXPointer.next = head;
                lessThanXPointer = lessThanXPointer.next;
            } else {
                greatOrEqualXPointer.next = head;
                greatOrEqualXPointer = greatOrEqualXPointer.next;
            }
            head = head.next;
        }

        // 将两个分区连接起来
        greatOrEqualXPointer.next = null;
        lessThanXPointer.next = greatOrEqualX.next;
        return lessThanX.next;
    }

    /**
     * https://leetcode.com/problems/palindrome-linked-list/
     * 234. Palindrome Linked List
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {

        ArrayList recodeList = new ArrayList<>();

        while (head != null) {
            recodeList.add(head.val);
            head = head.next;
        }
        System.out.println(recodeList);
        int left = 0;
        int right = recodeList.size() - 1;
        while (right > left) {
            if (recodeList.get(left) != recodeList.get(right)) {
                return false;
            }
            right--;
            left++;
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/linked-list-components/
     * 817. Linked List Components
     * @param head
     * @param nums
     * @return
     */
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int count = 0;
        boolean inComponent = false;

        while (head != null) {
            if (set.contains(head.val)) {
                if (!inComponent) {
                    inComponent = true;
                    count++;
                }
            } else {
                inComponent = false;
            }
            head = head.next;
        }
        return count;
    }

    /**
     * https://leetcode.com/problems/odd-even-linked-list/
     * 328. Odd Even Linked List
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        //1 -> 2 -> 3 -> 4 -> 5
        //1 -> 3 -> 5 -> 2 -> 4
        while (even != null && even.next != null) {
            //把2的下一个节点3 指向1的necy
            odd.next = even.next;
            //把目前要处理的 head指到3
            odd = odd.next;
            //2的 next本来是3，现在point to 3的next，也就是4
            even.next = odd.next;
            //现在处理4这个head
            even = even.next;
        }

        odd.next = evenHead;

        return head;
    }

    /**
     * https://leetcode.com/problems/product-of-array-except-self/?envType=study-plan-v2&envId=top-interview-150
     * preSum 经典题
     * 使用两个 array 来记录数字
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] leftProducts = new int[n];
        int[] rightProducts = new int[n];
        int[] result = new int[n];

        leftProducts[0] = 1;
        for (int i = 1; i < n; i++) {
            leftProducts[i] = leftProducts[i - 1] * nums[i - 1];
        }

        rightProducts[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            rightProducts[i] = rightProducts[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            result[i] = leftProducts[i] * rightProducts[i];
        }

        return result;
    }

    /**
     * https://leetcode.com/problems/balanced-binary-tree/
     * 110. Balanced Binary Tree
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        //因为这是一个递回，所有如果节点 = null，表示根本没有子节点，所有直接返回 true ;
        if (root == null) {
            return true;
        }
        //如果左子树高度与右子树的高度相差超过1，表示这不是一个平衡的二叉树
        if (Math.abs(height(root.left) - height(root.right)) > 1) {
            return false;
        }
        //如果左边跟右边都是平衡的，表示整颗就是平衡的
        return isBalanced(root.left) && isBalanced(root.right);
    }

    //取得树的子节点的高度
    public int height(TreeNode node) {
        if (node == null) return 0;
        int heightLeft = height(node.left);
        int heightRight = height(node.right);
        //如果不是 null ，则最少会是 1
        return Math.max(heightLeft, heightRight) + 1;
    }

    /**
     * https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
     * 111. Minimum Depth of Binary Tree
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> levelList = new ArrayList<>();

        //目前最小层数
        int[] record = {0};
        minDepthII(root, levelList, 1);
        Collections.sort(levelList);
        return levelList.get(0);
    }

    public void minDepthII(TreeNode root, List<Integer> levelList, int level) {
        if (root == null) {
            return;
        }

        if (!levelList.isEmpty()) {
            Integer min = levelList.get(0);
            if (level > min) {
                return;
            }
        }

        if (root.left == null && root.right == null) {
            levelList.add(level);
        }
        minDepthII(root.left, levelList, level + 1);
        minDepthII(root.right, levelList, level + 1);
    }

    /**
     * https://leetcode.com/problems/invert-binary-tree/description/
     * 226. Invert Binary Tree
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 交换左右子树
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }

    /**
     * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/solutions/
     * 108. Convert Sorted Array to Binary Search Tree
     * <p>
     * 这段程式码中的 sortedArrayToBST 方法用于将排序数组转换为平衡二叉搜索树。我们使用递回的方式
     * ，每次选择排序数组的中间元素作为根节点，并以此中间元素将数组分为左右两个部分，
     * 然后递回处理这两个部分。这样就可以构建一个平衡的二叉搜索树。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return constructBST(nums, 0, nums.length - 1);
    }

    private TreeNode constructBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        //注意这个 nums[mid] 并不是原始 nums array 的mid , mid 来自上一层传进的左右边界
        TreeNode root = new TreeNode(nums[mid]);
        root.left = constructBST(nums, start, mid - 1);
        root.right = constructBST(nums, mid + 1, end);
        //返回这个 node
        return root;
    }

    /**
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/solutions/4760076/java-solution-with-0ms-and-explanation/
     * 153. Find Minimum in Rotated Sorted Array
     * 这题困难点在于题目要求 O(logN) ，不然其实很简单
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        // Given an index i of a sorted array, we know that all the values after index i appear to be ascending
        // Now, rotate this sorted array. For any index i, if the right side ascending pattern is broken,
        // we can conclude that the min is at the right side.
        // Otherwise, the min is at the left side, since the pattern preserves
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] > nums[r]) {
                l = m + 1;
            } else {
                // r = m here to counter the case where nums[m] is the minimum value
                r = m;
            }
        }
        return nums[l];
    }

    /**
     * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/?envType=study-plan-v2&envId=top-interview-150
     * 80. Remove Duplicates from Sorted Array II
     * @param nums
     * @return
     */
    public int removeDuplicatesII(int[] nums) {

        int lastInt = Integer.MIN_VALUE;
        int singleCount = 0;
        int totalCount = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (num == lastInt) {
                singleCount++;
            } else {
                singleCount = 1;
            }
            if (singleCount < 3) {
                totalCount++;
                list.add(num);
            }
            lastInt = num;
        }
        for (int i = 0; i < list.size(); i++) {
            nums[i] = (int) list.get(i);
        }

        return totalCount;
    }

    /**
     * @param s
     * @return
     */
    public String reverseWordsII(String s) {

        s = s.trim().replaceAll("\\s+", " ");
        String[] arrayForS = s.split(" ");
        String result = "";
        for (int i = arrayForS.length - 1; i >= 0; i--) {
            result = result + arrayForS[i];
            if (i > 0) {
                result = result + " ";
            }

        }
        return result;
    }

    /**
     * https://leetcode.com/problems/count-complete-tree-nodes/?envType=study-plan-v2&envId=top-interview-150
     * 222. Count Complete Tree Nodes
     * // 完全二叉树是一种特殊的二叉树，其中除了最后一层外，每一层的节点都被完全填充，且所有节点都尽可能地向左对齐。
     * @param root
     * @return 这个完美二叉树，总共几个子节点
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //计算左边的所有节点，跟右边的所有节点，加上自己的节点
        int thisNode = 1;
        return countNodes(root.left) + countNodes(root.right) + thisNode;
    }

    /**
     * https://leetcode.com/problems/binary-tree-right-side-view/?envType=study-plan-v2&envId=top-interview-150
     * 199. Binary Tree Right Side View
     * 1.層續遍歷
     * 2.取每層最後一個元素
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);
        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelVals = new ArrayList<>();

            //这个 size 是这一层有几个元素
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                levelVals.add(node.val);
                //因為 First in First out 的特性，所以左子葉先放進去，將會先被 poll
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(levelVals.get(levelVals.size()));
        }
        return result;
    }

    /**
     * presum
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {

        int count = 0;
        return count;
    }

    /**
     * https://leetcode.com/problems/valid-palindrome/description/
     * 125. Valid Palindrome
     * input : "abaababaab" , "a"
     * 本来想用 replace 的方式，但有隐藏的 test case
     * 实际上挺难的，不像 easy 题目
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int i = n / 2; i >= 1; i--) {
            if (n % i == 0) {
                int repeats = n / i;
                String sub = s.substring(0, i);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < repeats; j++) {
                    sb.append(sub);
                }
                if (sb.toString().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * https://leetcode.com/problems/reverse-integer/
     * 7. Reverse Integer
     * topic : math
     * @param x
     * @return
     */
    public int reverse(int x) {
        int num = Math.abs(x);
        int revNum = 0;
        while (num != 0) {

            int digit = num % 10;
            //题目有叙述一个可能会溢出的情况，所以当溢出时 return 0 ;
            if (revNum > (Integer.MAX_VALUE - digit) / 10) return 0;
            revNum = revNum * 10 + digit;
            num = num / 10;
        }
        return (x < 0) ? -revNum : revNum;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        // 先对数组进行排序，这一步是关键
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        // 遍历数组，排序后的 array，右边会最大
        for (int i = 0; i < nums.length - 2; i++) {
            //nums[i] != nums[i - 1] 避免重复计算
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int left = i + 1, right = nums.length - 1, target = -nums[i];
                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        // 找到符合条件的三元组
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        // 处理重复情况
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/length-of-last-word/
     * 58. Length of Last Word
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {

        String[] strArray = s.split(" ");
        String lastWord = strArray[strArray.length - 1];
        return lastWord.trim().length();
    }

    /**
     * https://leetcode.com/problems/range-sum-of-bst/
     * Bineat search tree 表示这是一个排序好的 tree
     * 938. Range Sum of BST
     * ps tree 上的所有数字节点不重复
     * @param root
     * @param low
     * @param high
     * @return sum
     */
    public int rangeSumBST(TreeNode root, int low, int high) {

        //先界定好终止条件
        if (root == null) {
            return 0;
        }
        int sum = 0;

        //排序好了，所以当节点的大于最低点时，往左有
        if (root.val >= low) {
            sum += rangeSumBST(root.left, low, high);
        }
        //不可以用 else if ，会造成计算减少
        if (root.val <= high) {
            sum += rangeSumBST(root.right, low, high);
        }
        if (root.val >= low && root.val <= high) {
            sum = sum + root.val;
        }
        return sum;

    }

    /**
     * https://leetcode.com/problems/sum-of-left-leaves/
     * 404. Sum of Left Leaves
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeavesSum(root, 0, "root");
    }

    private int sumOfLeftLeavesSum(TreeNode node, int sum, String side) {
        if (node == null) {
            return sum;
        }

        //如果本身是葉子，且他是左葉，則加總
        if (node.left == null && node.right == null && side.equals("left")) {
            sum += node.val;
        }
        sum = sumOfLeftLeavesSum(node.left, sum, "left");
        sum = sumOfLeftLeavesSum(node.right, sum, "right");
        return sum;
    }

    /**
     * https://leetcode.com/problems/search-in-a-binary-search-tree/
     * 700. Search in a Binary Search Tree
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            //将整个 subTreeNode 都返回
            return root;
        }

        if (root.val < val) {
            TreeNode rootleft = searchBST(root.right, val);
            return rootleft;
        } else {
            TreeNode rootright = searchBST(root.left, val);
            return rootright;
        }
    }

    /**
     * https://leetcode.com/problems/increasing-order-search-tree/
     * 897. Increasing Order Search Tree
     * 先使用中序遍歷，在串接答案
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {

        ArrayList<Integer> list = (ArrayList) inorderTraversal(root);

        TreeNode th = new TreeNode(0);
        TreeNode curr = th;

        for (Integer val : list) {
            curr.right = new TreeNode(val);
            curr = curr.right;
        }
        return th.right;
    }

    /**
     * https://leetcode.com/problems/binary-tree-level-order-traversal/
     * 102. Binary Tree Level Order Traversal
     * todo 未解決
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);
        List<List<Integer>> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelVals = new ArrayList<>();

            //这个 size 是这一层有几个元素
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                levelVals.add(node.val);

                //因為 First in First out 的特性，所以左子葉先放進去，將會先被 poll
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(levelVals);
        }

        return result;
    }

    /**
     * https://leetcode.com/problems/find-mode-in-binary-search-tree/
     * 501. Find Mode in Binary Search Tree
     * @param root 1. 使用 map 紀錄
     * 2. 子方法遍歷
     * 3. 因為是 BST ，所以左邊一定比較小
     */
    public int[] findMode(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        inOrder(root, map);
        int max = 0;
        for (int val : map.values()) {
            if (val > max) {
                max = val;
            }
        }
        int n = 0;
        for (int val : map.values()) {
            if (val == max) {
                n++;
            }
        }

        int[] ans = new int[n];
        int i = 0;
        for (int key : map.keySet()) {
            if (map.get(key) == max) {
                ans[i] = key;
                i++;
            }
        }
        return ans;
    }

    public void inOrder(TreeNode root, HashMap<Integer, Integer> map) {
        if (root == null) return;
        //先往左走，左边空就把当下 node 的 val 存入
        inOrder(root.left, map);

        //中序遍歷
        if (map.containsKey(root.val)) {
            map.put(root.val, map.get(root.val) + 1);
        } else {
            map.put(root.val, 1);
        }

        //往右走
        inOrder(root.right, map);
    }

    /**
     * https://leetcode.com/problems/minimum-absolute-difference-in-bst/solutions/4642805/java-concise-solution-beats-100-users/
     * 530. Minimum Absolute Difference in BST
     * @param root 使用 array的原因：
     * 在 Java 中，基本数据类型（如 int）和对象引用（如 TreeNode）在方法中传递时，是按值传递的。
     * 对于基本数据类型，传递的是值的副本；对于对象引用，传递的是引用的副本，而不是对象本身。
     * 因此，在方法内部修改这些参数的值或引用，并不会影响原始变量的值或引用。
     * ps : 使用基本類型，即使使用包裝類，在拆箱過程操作以後，仍然不會紀錄值
     */
    public int getMinimumDifference(TreeNode root) {
        int[] min = {Integer.MAX_VALUE};
        TreeNode[] prev = {null};
        getMin(root, min, prev);
        return min[0];
    }

    public void getMin(TreeNode root, int[] min, TreeNode[] prev) {
        if (root == null) {
            return;
        }
        getMin(root.left, min, prev);
        if (prev[0] != null) {
            min[0] = Math.min(min[0], Math.abs(root.val - prev[0].val));
        }
        prev[0] = root;
        getMin(root.right, min, prev);
    }

    /**
     * https://leetcode.com/problems/merge-two-binary-trees/
     * 617. Merge Two Binary Trees
     * idea : 第一次遇到兩個 tree 的情況，想法，只要把 root2 的值，加到 root1 就可以了 ;
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        //這一句把右邊的 val 加入左邊
        root1.val = root1.val + root2.val;
        //在單層中，將 val 相加，同時將 root1 跟 root2 的左子葉繼續遞迴，他們將在下一層被相加
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        //return 處理好的 root1
        return root1;
    }

    /**
     * https://leetcode.com/problems/univalued-binary-tree/
     * @param root
     * @return
     */
    public boolean isUnivalTree(TreeNode root) {

        boolean[] isUnivalTree = {true};
        if (root == null) {
            return isUnivalTree[0];
        }
        isUnivalTreePart(root, isUnivalTree, root.val);

        return isUnivalTree[0];
    }

    private void isUnivalTreePart(TreeNode root, boolean[] isUnivalTree, int val) {
        if (root == null) {
            return;
        }
        if (root.val != val) {
            isUnivalTree[0] = false;
            return;
        }
        isUnivalTreePart(root.left, isUnivalTree, val);
        isUnivalTreePart(root.right, isUnivalTree, val);
    }

    /**
     * https://leetcode.com/problems/binary-tree-paths/
     * 257. Binary Tree Paths
     * @param root ≤
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {

        List<String> ans = new ArrayList<>();
        binaryTreePathsPart(root, ans, "");
        return ans;
    }

    private void binaryTreePathsPart(TreeNode root, List<String> ans, String s) {
        if (root == null) {
            return;
        }
        if (root.right == null && root.left == null) {
            s = s + root.val;
            ans.add(s);
            return;
        }
        binaryTreePathsPart(root.left, ans, s + root.val + "->");
        binaryTreePathsPart(root.right, ans, s + root.val + "->");
    }

    /**
     * https://leetcode.com/problems/binary-tree-tilt/
     * 563. Binary Tree Tilt
     * 不用糾結在一定要弄出每個節點的值，需要一些數學思維
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        int[] max = {0};
        findTiltPart(root, max);
        return max[0];
    }

    private int findTiltPart(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }

        int leftSum = findTiltPart(root.left, max);
        int rightSum = findTiltPart(root.right, max);
        max[0] = max[0] + Math.abs(leftSum - rightSum);
        return leftSum + rightSum + root.val;
    }

    /**
     * https://leetcode.com/problems/subtree-of-another-tree/
     * 572. Subtree of Another Tree
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        //這個遞迴是選定每個節點 2 <- 1 -> 3
        if (root == null) {
            return false;
        }
        //比較 1
        if (isNodeEqual(root, subRoot)) {
            return true;
        }
        //把 2帶入
        //把 3帶入
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    //比較兩個 tree 是否一樣
    private boolean isNodeEqual(TreeNode node1, TreeNode node2) {
        //假數這個節點為空，則為此節點一樣
        if (node1 == null && node2 == null) {
            return true;
        }
        //假設僅有一個null 或是 value 不相等
        if (node1 == null || node2 == null || node1.val != node2.val) {
            return false;
        }
        return isNodeEqual(node1.left, node2.left) && isNodeEqual(node1.right, node2.right);
    }

    /**
     * 比較兩個Tree是否是一樣的
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<Integer> root1List = new ArrayList<>();
        List<Integer> root2List = new ArrayList<>();
        leafSimilarPart(root1, root1List);
        leafSimilarPart(root2, root2List);
        return root1List.equals(root2List);
    }

    public void leafSimilarPart(TreeNode root, List<Integer> rootList) {
        if (root == null) {
            return;
        }
        if (root.right == null && root.left == null) {
            rootList.add(root.val);
            return;
        }
        leafSimilarPart(root.left, rootList);
        leafSimilarPart(root.right, rootList);

    }

    /**
     * https://leetcode.com/problems/reverse-string/
     * 344. Reverse String
     * @param s
     */
    public void reverseString(char[] s) {

        for (int left = 0; left < s.length / 2; left++) {
            int right = s.length - left - 1;
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
        }
    }

    /**
     * https://leetcode.com/problems/longest-palindrome/description/
     * 409. Longest Palindrome
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {

        int palindromeLength = 0;
        //因為 ASCII 字元集中有 128 個字符
        char arr[] = new char[128];

        for (char ch : s.toCharArray()) {
            arr[ch]++;
        }

        int hasOdd = 0;
        for (int count : arr) {
            if (count % 2 == 0) {
                palindromeLength += count;
                //奇数的情况
            } else {
                palindromeLength += count - 1;
                hasOdd = 1;
            }
        }
        //判斷是否為 odd
        //所有的奇數 只會統一加1
        return palindromeLength + hasOdd;
    }

    public int thirdMax(int[] nums) {
        List<Integer> list = Arrays.stream(nums)
                .distinct()
                .boxed()  // 轉換為 Integer 物件
                .sorted(Comparator.reverseOrder())  // 降序排序
                .collect(Collectors.toList());

        // 獲取指定索引位置的元素
        int index = 2;
        if (index < list.size()) {
            return list.get(index);
        } else {
            return list.get(list.size() - 1);
        }
    }

    /**
     * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
     * 448. Find All Numbers Disappeared in an Array
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[nums[i - 1]]++;
        }
        List<Integer> t = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            //如果原本的 location == 0 ，表示原array沒有這個數字
            if (arr[i] == 0) {
                t.add(i);
            }
        }
        return t;
    }

    /**
     * https://leetcode.com/problems/find-common-characters/description/
     * 1002. Find Common Characters
     * @param words
     * @return
     */
    public List<String> commonChars(String[] words) {

        return null;
    }

    /**
     * https://leetcode.com/problems/average-of-levels-in-binary-tree/
     * 637. Average of Levels in Binary Tree
     * 層序遍歷 改一點
     */
    public List<Double> averageOfLevels(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);
        List<Double> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelVals = new ArrayList<>();

            long sumOfThisLevel = 0l;
            //这个 size 是这一层有几个元素
            for (int i = 0; i < size; i++) {

                TreeNode node = queue.poll(); // list.remove(0);
                sumOfThisLevel += node.val;

                if (node.left != null) queue.offer(node.left); //list.add(node.left);
                if (node.right != null) queue.offer(node.right);//list.add(node.right);
            }
            result.add((double) sumOfThisLevel / (double) size);
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
     * 653. Two Sum IV - Input is a BST
     * 作法跟第一題的 two Sum 類似，使用一個 set 來記錄答案
     * @param root
     * @param k 目標值
     */
    public boolean findTarget(TreeNode root, int k) {
        return findTargetPart(root, k, new HashSet<>());
    }

    private boolean findTargetPart(TreeNode root, int k, Set<Integer> ansSet) {
        if (root == null) {
            return false;
        }
        int ans = k - root.val;
        if (ansSet.contains(ans)) {
            return true;
        } else {
            ansSet.add(root.val);
        }
        if (findTargetPart(root.left, k, ansSet) || findTargetPart(root.right, k, ansSet)) {
            return true;
        }
        return false;
    }

    /**
     * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
     * 671. Second Minimum Node In a Binary Tree
     * @param root ≤
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {

        ArrayList<Integer> list = new ArrayList<>();
        findSecondMinimumValuePart(root, list);
        list = (ArrayList<Integer>) list.stream().distinct().sorted().collect(Collectors.toList());
        return list.size() < 2 ? -1 : list.get(1);
    }

    private void findSecondMinimumValuePart(TreeNode root, ArrayList list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        findSecondMinimumValuePart(root.left, list);
        findSecondMinimumValuePart(root.right, list);
    }

    /**
     * https://leetcode.com/problems/sum-root-to-leaf-numbers/submissions/1188790371/
     * 129. Sum Root to Leaf Numbers
     * 解題思維類似箭頭那題 ( binaryTreePaths )，但多次轉型造成效能問題
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {

        List<String> ans = new ArrayList<>();
        sumNumbersPart(root, ans, "");
        int sum = 0;
        for (String num : ans) {
            sum += Integer.valueOf(num);
        }
        return sum;
    }

    private void sumNumbersPart(TreeNode root, List<String> ans, String s) {
        if (root == null) {
            return;
        }
        if (root.right == null && root.left == null) {
            s = s + root.val;
            ans.add(s);
            return;
        }
        sumNumbersPart(root.left, ans, s + root.val);
        sumNumbersPart(root.right, ans, s + root.val);
    }

    /**
     * https://leetcode.com/problems/relative-ranks/description/
     * 506. Relative Ranks
     * @param score
     * @return
     */
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        int[] copy = Arrays.copyOf(score, n);
        Arrays.sort(copy);

        //这一段是想把 array 由大排到小
        for (int i = 0; i < n / 2; i++) {
            int temp = copy[i];
            copy[i] = copy[n - i - 1];
            copy[n - i - 1] = temp;
        }

        Map<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < n; i++) {
            hm.put(copy[i], i + 1);
        }
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            if (hm.get(score[i]) == 1) {
                ans[i] = "Gold Medal";
            } else if (hm.get(score[i]) == 2) {
                ans[i] = "Silver Medal";
            } else if (hm.get(score[i]) == 3) {
                ans[i] = "Bronze Medal";
            } else {
                ans[i] = "" + hm.get(score[i]);
            }
        }
        return ans;

    }

    /**
     * 找不到漂亮的作法
     * https://leetcode.com/problems/intersection-of-two-arrays-ii/
     * 350. Intersection of Two Arrays II
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {

        List<Integer> list1 = Arrays.stream(nums1)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(nums2)
                .boxed()
                .collect(Collectors.toList());

        list1.retainAll(list2);
        list2.retainAll(list1);

        int[] ans = new int[list2.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list2.get(i);
        }
        return ans;
    }

    /**
     * https://leetcode.com/problems/arranging-coins/
     * 441. Arranging Coins
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {

        for (int i = 0; i < 1000; i++) {
            n = n - i;
            if (n < 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * https://leetcode.com/problems/reverse-string-ii/
     * 541. Reverse String II
     * @param s
     * @param k
     * @return
     */
    public String reverseStrII(String s, int k) {

        return "";
    }

    /**
     * https://leetcode.com/problems/array-partition/
     * 561. Array Partition
     * nums 的長度一定是 2的倍數
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {

        int sum = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) {
            sum = sum + nums[i];
        }
        return sum;

    }

    /**
     * https://leetcode.com/problems/find-bottom-left-tree-value/?envType=daily-question&envId=2024-02-28
     * 513. Find Bottom Left Tree Value
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {

        int maxD = 1;
        int valN = root.val;
        //深度，當下的值
        int[] record = {maxD, valN};
        dfs_numIslands(root.left, 1, record);
        dfs_numIslands(root.right, 1, record);
        return record[1];
    }

    public void dfs_numIslands(TreeNode root, int depth, int[] record) {
        if (root == null) return;
        depth++;
        //tips:因为是 > ，不是 >= ，所以同一层，会先走左边，就不会被右边盖掉
        if (depth > record[0] && root.left == null && root.right == null) {
            record[0] = depth;
            record[1] = root.val;
        }
        dfs_numIslands(root.left, depth, record);
        dfs_numIslands(root.right, depth, record);
    }

    /**
     * https://leetcode.com/problems/last-stone-weight/submissions/1188904551/
     * 1046. Last Stone Weight
     * <p>
     * PriorityQueue 跟 list 一样，差别在于插入资料时，会自动帮你排序 (可以给他排序规则)
     * offer() 跟 add() 作用一樣，都是放到隊列中，差別在於 offer() 返回一個布林值
     * 注意 因為會自動排序，並不是放到最後一個的位置．
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            maxHeap.add(stone);
        }

        int x = 0;
        int y = 0;
        while (maxHeap.size() > 1) {
            y = maxHeap.poll();
            x = maxHeap.poll();
            if (y > x) {
                //插入時自動排序
                boolean success = maxHeap.offer(y - x);
            }
        }

        //If the maxHeap is empty, the expression evaluates to 0
        //If the maxHeap is not empty, the expression evaluates to maxHeap.poll()
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }

    /**
     * https://leetcode.com/problems/split-a-string-in-balanced-strings/
     * 1221. Split a String in Balanced Strings
     * @param s
     * @return
     */
    public int balancedStringSplit(String s) {

        char[] strChar = s.toCharArray();

        int balance = 0;
        int count = 0;
        for (int i = 0; i < strChar.length; i++) {
            if (strChar[i] == 'R') {
                balance++;
            } else {
                balance--;
            }
            if (balance == 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * https://leetcode.com/problems/valid-palindrome-ii/
     * 680. Valid Palindrome II
     * 需使用双指针法
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {

        return false;
    }

    /**
     * https://leetcode.com/problems/split-with-minimum-sum/
     * 2578. Split With Minimum Sum
     * Greedy
     * @param num
     * @return
     */
    public int splitNum(int num) {
        ArrayList<Integer> ll = new ArrayList<>();
        while (num > 0) {
            int r = num % 10;
            ll.add(r);
            num = num / 10;
        }
        Collections.sort(ll);
        int n1 = ll.get(0);
        int n2 = ll.get(1);
        for (int i = 2; i < ll.size(); i++) {
            if (i % 2 == 0)
                //這邊透過數學方法，處理為0的情況
                //即使第一個是0 ，在加入 list[2] 的時候就 0 就會退到第二位
                n1 = n1 * 10 + ll.get(i);
            else
                n2 = n2 * 10 + ll.get(i);
        }
        return n1 + n2;

    }

    /**
     * https://leetcode.com/problems/find-lucky-integer-in-an-array/description/
     * 1394. Find Lucky Integer in an Array
     * @param arr
     * @return
     */
    public int findLucky(int[] arr) {
        int[] temp = new int[501];
        for (int i : arr) {
            //在指定数字的 index 中增加数字
            temp[i]++;
        }
        int ans = -1;
        for (int i = temp.length - 1; i >= 0; i--) {
            int current = temp[i];
            //0 表示原来的 arr 中不包含这个数字
            if (current == 0) {
                continue;
            }
            if (current == i) {
                //返回最大的答案
                ans = current;
                break;
            }
        }
        return ans;
    }

    /**
     * https://leetcode.com/problems/determine-if-string-halves-are-alike/
     * 1704. Determine if String Halves Are Alike
     * @param s ≤
     * @return
     */
    public boolean halvesAreAlike(String s) {

        char[] strChar = s.toCharArray();
        List<Character> characters = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

        int left = 0;
        int right = strChar.length - 1;

        int leftCount = 0;
        int rightCount = 0;
        while (right > left) {
            if (characters.contains(strChar[left])) {
                leftCount++;
            }
            if (characters.contains(strChar[right])) {
                rightCount++;
            }
            right--;
            left++;
        }
        return leftCount == rightCount;
    }

    /**
     * https://leetcode.com/problems/richest-customer-wealth/
     * 1672. Richest Customer Wealth
     * @param accounts
     * @return
     */
    public int maximumWealth(int[][] accounts) {

        int max = 0;
        for (int i = 0; i < accounts.length; i++) {
            int sumOfThisArray = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                sumOfThisArray += accounts[i][j];
            }
            max = Math.max(max, sumOfThisArray);
        }
        return max;
    }

    /**
     * https://leetcode.com/problems/count-of-matches-in-tournament/
     * 1688. Count of Matches in Tournament
     * @param n
     * @return
     */
    public int numberOfMatches(int n) {

        int sum = 0;

        while (n > 1) {
            int oldn = n;
            n = n / 2;
            sum += (oldn - n);
        }
        return sum;
    }

    /**
     * https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
     * 1721. Swapping Nodes in a Linked List
     * 本來使用一個 arrayList 做紀錄，但是太慢了，參考做法：快慢指標
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodes(ListNode head, int k) {
        // 初始化快慢指針，都指向 head
        ListNode fast = head;
        ListNode slow = head;

        // 移動快指針到第 k 個節點
        for (int i = 1; i < k; i++) {
            fast = fast.next;
        }

        // 同時移動快慢指針，直到快指針到達最後一個節點
        ListNode temp = fast;
        while (temp.next != null) {
            slow = slow.next;
            temp = temp.next;
        }

        // 交換慢指針和快指針所指節點的值
        int tempval = slow.val;
        slow.val = fast.val;
        fast.val = tempval;

        // 返回交換後的鏈表頭部
        return head;
    }

    /**
     * https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
     * 1752. Check if Array Is Sorted and Rotated
     * 題意不清
     * @param nums
     * @return
     */
    public boolean check(int[] nums) {

        int longDifferent = nums.length - 1;

        for (int i = longDifferent; i > 0; i--) {

            if (nums[i] - nums[i - 1] != 1 && nums[i] - nums[i - 1] != -longDifferent
                    && nums[i] != nums[i - 1]) {
                return false;
            }

        }
        return true;
    }

    /**
     * https://leetcode.com/problems/deepest-leaves-sum/
     * 1302. Deepest Leaves Sum
     * dfs 貌似比較快， levelorder 沒那麼快
     * @param root
     * @return
     */
    public int deepestLeavesSum(TreeNode root) {
        //加总，已知最大层数
        int[] record = {0, 1};
        deepestLeavesSumPart(root, record, 1);
        return record[0];
    }

    /**
     * @param root
     * @param record 紀錄值
     * @param level 目前層數
     */
    private void deepestLeavesSumPart(TreeNode root, int[] record, int level) {

        if (root == null) {
            return;
        }
        boolean hasNoLeaf = (root.right == null && root.left == null);
        if (hasNoLeaf && record[1] == level) {
            record[0] += root.val;
        } else if (hasNoLeaf && record[1] < level) {
            //重新紀錄這一層的加總
            record[0] = root.val;
            //更新最大紀錄到這一層
            record[1] = level;
        }
        deepestLeavesSumPart(root.left, record, level + 1);
        deepestLeavesSumPart(root.right, record, level + 1);
    }

    /**
     * https://leetcode.com/problems/all-elements-in-two-binary-search-trees/description/
     * 1305. All Elements in Two Binary Search Trees
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {

        List<Integer> result = new LinkedList<>();

        getAllElementsPart(root1, result);
        getAllElementsPart(root2, result);
        Collections.sort(result);
        return result;

    }

    public void getAllElementsPart(TreeNode root, List result) {
        if (root == null) {
            return;
        }
        getAllElementsPart(root.left, result);
        result.add(root.val);
        getAllElementsPart(root.right, result);
    }

    /**
     * https://leetcode.com/problems/flip-equivalent-binary-trees/description/
     * 951. Flip Equivalent Binary Trees
     * @param root1
     * @param root2
     * @return
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {

        return false;
    }

    /**
     * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
     * 230. Kth Smallest Element in a BST
     * 利用 BST 的特性，不需要全部放進去即可最小值，找到最小值
     * @param root
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        //目前第幾小的，目標值
        int[] record = {0, k};
        if (root == null) {
            return 0;
        }
        int ans = kthSmallestPart(root, record);
        if (ans > -1) {
            return ans;
        }
        return -1;
    }

    private int kthSmallestPart(TreeNode root, int[] record) {
        if (root == null) {
            return -1;
        }

        // 先遞歸處理左子樹
        int left = kthSmallestPart(root.left, record);
        if (left != -1) {
            return left;
        }

        // 檢查當前節點
        record[0]++;
        if (record[0] == record[1]) {
            return root.val;
        }

        // 遞歸處理右子樹
        return kthSmallestPart(root.right, record);
    }

    /**
     * https://leetcode.com/problems/minimum-distance-between-bst-nodes/
     * 783. Minimum Distance Between BST Nodes
     * 參考作法 - 因為是 BST，所以中序遍歷後的 list 將會按照順序排列
     * 此時抓出最小值即可
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        List<Integer> list = inorderTraversal(root);

        int min = Integer.MAX_VALUE; // 使用Integer.MAX_VALUE作為初始最小值
        for (int i = 1; i < list.size(); i++) {
            // 直接計算當前差值並與min比較
            int diff = list.get(i) - list.get(i - 1);
            if (diff < min) {
                min = diff;
            }
        }
        return min;
    }

    /**
     * https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
     * @param n
     * @return
     */
    public int[] sumZero(int n) {

        int[] result = new int[n];

        int num = 1;
        int sum = 0;
        for (int i = 0; i < result.length - 1; i++) {
            result[i] = num;
            sum += num;
            num++;
        }
        result[n - 1] = -sum;
        return result;

    }

    /**
     * https://leetcode.com/problems/maximum-number-of-coins-you-can-get/
     * 1561. Maximum Number of Coins You Can Get
     * 雙指針
     * 同個回圈內兩個條件
     * @param piles
     * @return
     */
    public int maxCoins(int[] piles) {

        int coins = 0;
        Arrays.sort(piles);
        for (int i = 0, j = piles.length - 2; i < j; i++, j = j - 2) {
            coins += piles[j];
        }
        return coins;
    }

    /**
     * ˜
     * https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
     * 1588. Sum of All Odd Length Subarrays
     * 本来想用滑动窗口，但有更精巧的数学解法
     * @param arr
     * @return
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // 左邊選擇元素的方式：從i+1個元素中選擇任意個（包括0）
            int leftCount = i + 1;
            // 右邊選擇元素的方式：從n-i個元素中選擇任意個（包括0）
            int rightCount = n - i;
            // 左邊和右邊選擇元素總共的方式
            int totalSubarrays = leftCount * rightCount;
            // 包含arr[i]的奇數長度子數組的數量
            int oddSubarrays = (totalSubarrays + 1) / 2;
            // 將arr[i]的貢獻加到總和中
            sum += oddSubarrays * arr[i];
        }
        return sum;
    }

    /**
     * https://leetcode.com/problems/rotate-list/?envType=study-plan-v2&envId=top-interview-150
     * 61. Rotate List
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        ListNode temp = head;
        ArrayList list = new ArrayList<>();
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        k = k % list.size();
        if (k == 0) {
            return head;
        }
        List<Integer> subList = list.subList(list.size() - k, list.size());
        List<Integer> tailList = list.subList(0, list.size() - k);
        subList.addAll(tailList);

        ListNode current = head;
        for (int num : subList) {
            current.val = num;
            current = current.next;
        }
        return head;
    }

    /**
     * https://leetcode.com/problems/sort-list/?envType=study-plan-v2&envId=top-interview-150
     * 148. Sort List
     * 真的要快要用快慢指針，很複雜
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        List<Integer> values = new ArrayList<>();

        ListNode curr = head;
        while (curr != null) {
            values.add(curr.val);
            curr = curr.next;
        }

        Collections.sort(values);

        ListNode result = head;
        for (int value : values) {
            result.val = value;
            result = result.next;
        }

        return head;
    }

    /**
     * https://leetcode.com/problems/validate-binary-search-tree/
     * 98. Validate Binary Search Tree
     * <p>
     * 需將 max 跟 min 往下傳
     * 而不是只跟自己的父節點比較
     * 因為定義是左邊的全部節點都須小於原節點
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTPart(root, null, null);
    }

    private boolean isValidBSTPart(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }

        if ((max != null && root.val >= max) || (min != null && root.val <= min)) {
            return false;
        }

        return isValidBSTPart(root.left, min, root.val) && isValidBSTPart(root.right, root.val, max);
    }

    /**
     * https://leetcode.com/problems/sliding-window-maximum/solutions/4820179/java-using-monotonic-queue-o-n/
     * 239. Sliding Window Maximum
     * hard ，使用雙端隊列
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); //double linked list
        int index = 0;
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peek() == i - k)
                deque.poll(); //remove out of window elements
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
                deque.pollLast(); //remove non maximun elements
            deque.offer(i);
            if (i >= k - 1) ans[index++] = nums[deque.peek()];
        }
        return ans;
    }

    /**
     * https://leetcode.com/problems/binary-search-tree-iterator/?envType=study-plan-v2&envId=top-interview-150
     * 173. Binary Search Tree Iterator
     * 利用 stack 的特性來做
     * @param root
     */
    public void BSTIterator(TreeNode root) {
        //構造器，先把這棵樹的左子葉放進去
        pushAll(root);

    }

    public int next() {
        TreeNode node = publicStack.pop();
        pushAll(node.right);
        return node.val;

    }

    public boolean hasNext() {
        return !publicStack.isEmpty();
    }

    private void pushAll(TreeNode node) {
        while (node != null) {
            publicStack.push(node);
            node = node.left;
        }
    }
    //================= 以上屬於同一題 =================

    /**
     * https://leetcode.com/problems/maximum-product-difference-between-two-pairs/
     * 1913. Maximum Product Difference Between Two Pairs
     * @param nums
     * @return
     */
    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);

        return (nums[nums.length - 1] * nums[nums.length - 2] - nums[0] * nums[1]);
    }

    /**
     * https://leetcode.com/problems/squares-of-a-sorted-array/
     * 977. Squares of a Sorted Array
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    /**
     * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/?envType=study-plan-v2&envId=top-interview-150
     * @param root
     */
    public void flatten(TreeNode root) {

    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (prev != null) {
                    //設定 prev 的 next 指向 curr
                    prev.next = curr;
                }
                //把指針指過去
                prev = curr;
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        return root;
    }

    /**
     * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/?envType=study-plan-v2&envId=top-interview-150
     * 103. Binary Tree Zigzag Level Order Traversal
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> result = new ArrayList<>();

        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> levelVal = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr == null) {
                    continue;
                }
                if (level % 2 == 0) {
                    levelVal.add(0, curr.val);
                } else {
                    levelVal.add(curr.val);
                }

                //把指針指過去
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            level++;
            if (!levelVal.isEmpty()) {
                result.add(levelVal);
            }
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/add-two-numbers/
     * 2. Add Two Numbers
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        boolean lastOver10 = false;
        while (l1 != null || l2 != null || carry > 0) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int sum = val1 + val2 + carry;
            carry = sum / 10;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummy.next;

    }

    /**
     * https://leetcode.com/problems/clone-graph/description/
     * 1232. Check If It Is a Straight Line
     * @param coordinates
     * @return
     */
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length == 2) {
            return true; // 只有兩個點一定共線
        }

        int x0 = coordinates[0][0];
        int y0 = coordinates[0][1];
        int x1 = coordinates[1][0];
        int y1 = coordinates[1][1];

        for (int i = 2; i < coordinates.length; i++) {
            int xi = coordinates[i][0];
            int yi = coordinates[i][1];

            // 判斷斜率是否相等
            if ((yi - y0) / (xi - x0) != (y1 - y0) / (x1 - x0)) {
                return false;
            }
        }
        return true;

    }

    /**
     * https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/?envType=study-plan-v2&envId=programming-skills
     * 1523. Count Odd Numbers in an Interval Range
     * @param low
     * @param high
     * @return
     */
    // 解法：數學規律
    public int countOdds(int low, int high) {
        int count = (high - low) / 2;
        if (low % 2 != 0 || high % 2 != 0) {
            count++;
        }
        return count;
    }

    /**
     * https://leetcode.com/problems/custom-sort-string/?envType=daily-question&envId=2024-03-11
     * 791. Custom Sort String
     * 試著用 int[] 的方式來紀錄
     */
    public String customSortString(String order, String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder result = new StringBuilder();
        for (char c : order.toCharArray()) {
            //有在字典上
            while (count[c - 'a'] > 0) {
                result.append(c);
                count[c - 'a']--;
            }
        }
        //從 a 數到 z
        for (char c = 'a'; c <= 'z'; c++) {
            while (count[c - 'a'] > 0) {
                result.append(c);
                count[c - 'a']--;
            }
        }
        return result.toString();
    }

    /**
     * https://leetcode.com/problems/di-string-match/
     * 942. DI String Match
     * 給定只包含 "I"（增加）或 "D"（減少）的字符串 S，表示一個整數排列。你需要根據每個 "I" 和 "D" 處理如下：每個 "I" 後跟的數字比前一個數字大，每個 "D" 後跟的數字比前一個數字小。
     * 1.找規律 I一定從0 開始 D一定從n+1 開始扣
     */
    public int[] diStringMatch(String s) {

        int countI = 0;
        int countD = s.length();
        int[] result = new int[s.length() + 1];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                result[i] = countI;
                countI++;
            } else {
                result[i] = countD;
                countD--;
            }
        }
        result[result.length - 1] = countI;
        return result;
    }

    /**
     * https://leetcode.com/problems/boats-to-save-people/
     * 881. Boats to Save People
     * Greedy two pointer
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0, right = people.length - 1;
        int boats = 0;

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            boats++;
        }
        return boats;
    }

    /**
     * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/?envType=study-plan-v2&envId=top-interview-150
     * 452. Minimum Number of Arrows to Burst Balloons
     * 考題：看兩個陣列是否 overLap
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        //按照每個子陣列的第二個元素（即索引為 1 的元素）的值進行升序排序。
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 1;
        int end = points[0][1];

        for (int i = 1; i < points.length; i++) {
            //進入這個 if 內，表示沒有重疊，不需要使用新的箭
            if (points[i][0] > end) {
                count++;
                end = points[i][1];
            }
        }

        return count;
    }

    /**
     * https://leetcode.com/problems/insert-interval/?envType=study-plan-v2&envId=top-interview-150
     * 57. Insert Interval
     * 解法：遍歷並合併區間
     * 註解：遍歷給定的區間列表，將新區間插入並合併重疊的區間，最後返回合併後的區間列表。
     */

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        //因為題目給的 intervals 原本就已經對左邊最小值排序，故不需要手動排序一次．
        // 將新區間之前的不重疊區間加入結果列表
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // 合併重疊區間
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // 將新區間之後的不重疊區間加入結果列表
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    /**
     * https://leetcode.com/problems/merge-intervals/?envType=study-plan-v2&envId=top-interview-150
     * 56. Merge Intervals
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        //對 index == 0 做排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int n = intervals.length;
        int i = 0;

        while (i < n) {
            int[] newInterval = intervals[i];

            // i < n - 1  是為了防止 out of index
            while (i < n - 1 && newInterval[1] >= intervals[i + 1][0]) {
                newInterval[1] = Math.max(newInterval[1], intervals[i + 1][1]);
                i++;
            }

            result.add(newInterval);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    /**
     * https://leetcode.com/problems/single-number-ii/?envType=study-plan-v2&envId=top-interview-150
     * 137. Single Number II
     * 只想得到 map 的解法，另一種想不到
     */
    public int singleNumberII(int[] nums) {

        return -1;
    }

    /**
     * https://leetcode.com/problems/word-break/?envType=study-plan-v2&envId=top-interview-150
     * 139. Word Break
     * 有陷阱："cars" , ["car","ca","rs"]
     * 未完成
     */
    public boolean wordBreak(String s, List<String> wordDict) {

        //這個可以過 test，過不了 submit
        for (String word : wordDict) {
            s = s.replace(word, "");
        }
        return s.isEmpty();
    }

    /**
     * https://leetcode.com/problems/largest-odd-number-in-string/
     * 1903. Largest Odd Number in String
     * 這方法不行，因為垃圾題目故意給到液位
     * @param num
     * @return
     */
    public String largestOddNumber(String num) {
        BigInteger numInt = new BigInteger(num);

        TreeSet<BigInteger> set = new TreeSet<>();

        while (numInt.compareTo(BigInteger.ZERO) > 0) {
            if (numInt.compareTo(BigInteger.TEN) < 0 && numInt.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) {
                set.add(numInt);
                break;
            }
            if (numInt.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) {
                return numInt.toString();
            }
            BigInteger remain = numInt.mod(BigInteger.TEN);
            if (remain.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) {
                set.add(remain);
            }
            numInt = numInt.divide(BigInteger.TEN);
        }
        if (!set.isEmpty()) {
            return set.pollLast().toString();
        }
        return "";
    }

    /**
     * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/submissions/1200805991/
     * @param s
     * @return
     */
    public int minAddToMakeValid(String s) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }
        return left + right;
    }

    /**
     * https://leetcode.com/problems/reverse-prefix-of-word/
     * 2000. Reverse Prefix of Word
     * @param word
     * @param ch
     * @return
     */
    public String reversePrefix(String word, char ch) {

        char[] strChar = word.toCharArray();
        int indexOfChar = word.indexOf(ch);
        if (indexOfChar == -1) {
            return word; // 如果找不到字元 ch，返回原始字串
        }
        String strLeft = word.substring(0, indexOfChar + 1);
        String strRight = word.substring(indexOfChar + 1);
        StringBuilder reversed = new StringBuilder(strLeft).reverse();
        strLeft = reversed.toString();
        return strLeft + strRight;

    }

    /**
     * https://leetcode.com/problems/greatest-common-divisor-of-strings/?envType=study-plan-v2&envId=leetcode-75
     * 1071. Greatest Common Divisor of Strings
     * 抄的
     * @param str1
     * @param str2
     * @return
     */
    public String gcdOfStrings(String str1, String str2) {
        if (str1.equals(str2))
            return str1;

        if (str2.length() > str1.length()) {
            return gcdOfStrings(str2, str1);
        }

        if (str1.startsWith(str2)) {
            //切开后比较剩下的部分
            return gcdOfStrings(str1.substring(str2.length()), str2);
        }

        return "";
    }

    /**
     * https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/
     * 1877. Minimize Maximum Pair Sum in Array
     * 找規律
     * @param nums
     * @return
     */
    public int minPairSum(int[] nums) {

        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;
        int max = 0;

        while (left < right) {
            max = Math.max(max, nums[left] + nums[right]);
            left++;
            right--;
        }
        return max;
    }

    /**
     * https://leetcode.com/problems/minimum-subsequence-in-non-increasing-order/description/
     * 1403. Minimum Subsequence in Non-Increasing Order
     * 給定數組 nums，取得數組的一個子序列，其元素總和嚴格大於該子序列中未包含元素的總和。
     * @param nums
     * @return
     */
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int remain = Arrays.stream(nums).sum();
        int sum = 0;

        List<Integer> result = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            sum += nums[i];
            remain -= nums[i];
            result.add(nums[i]);
            //假设已经超过，就不计需放入回圈了
            if (sum > remain) {
                break;
            }
        }

        return result;
    }

    /**
     * https://leetcode.com/problems/minimum-operations-to-make-the-array-increasing/
     * 1827. Minimum Operations to Make the Array Increasing
     * @param nums
     * @return
     */
    public int minOperations(int[] nums) {
        int howManyOperations = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] >= nums[i + 1]) {
                int num = nums[i] - nums[i + 1] + 1;
                howManyOperations += num;
                nums[i + 1] = nums[i + 1] + num;
            }
        }
        return howManyOperations;
    }

    /**
     * https://leetcode.com/problems/evaluate-boolean-binary-tree/
     * 1331. Evaluate Boolean Binary Tree
     * @param root
     * @return
     */
    public boolean evaluateTree(TreeNode root) {

        if (root.left == null && root.right == null) {
            // 1== true , 0==false
            return root.val == 1;
        }

        boolean leftBoolean = evaluateTree(root.left);
        boolean rightBoolean = evaluateTree(root.right);

        //2 == or , 3 == and
        if (root.val == 2) {
            return leftBoolean || rightBoolean;
        } else {
            return leftBoolean && rightBoolean;
        }
    }

    /**
     * https://leetcode.com/problems/backspace-string-compare/?envType=daily-question&envId=2024-03-02
     * 844. Backspace String Compare
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare(String s, String t) {

        int sIndex = s.length() - 1;
        StringBuilder sbOfS = new StringBuilder();
        int skipCount = 0;
        while (sIndex >= 0) {
            if (s.charAt(sIndex) == '#') {
                skipCount++;
            } else {
                if (skipCount == 0) {
                    sbOfS.append(s.charAt(sIndex));
                } else {
                    skipCount--;
                }
            }
            sIndex--;
        }

        int tIndex = t.length() - 1;
        StringBuilder sbOfT = new StringBuilder();
        skipCount = 0;
        while (tIndex >= 0) {
            if (t.charAt(tIndex) == '#') {
                skipCount++;
            } else {
                if (skipCount == 0) {
                    sbOfT.append(t.charAt(tIndex));
                } else {
                    skipCount--;
                }
            }
            tIndex--;
        }

        return sbOfS.toString().equals(sbOfT.toString());
    }

    /**
     * https://leetcode.com/problems/longest-repeating-character-replacement/description/?envType=list&envId=5vexd9b3
     * 424. Longest Repeating Character Replacement
     * 滑動窗口
     * 找出 所有子陣列相乘後比K小的子陣列
     */
    public int numSubrrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }

        int count = 0;
        int product = 1;
        int left = 0;

        //因为只能跟旁边的元素做乘积，所以用滑动窗口
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];

            while (product >= k) {
                product /= nums[left];
                left++;
            }
            //這句不好理解，想像怎麼動比較好理解
            count = count + right - left + 1;
            ;
        }

        return count;
    }

    /**
     * https://leetcode.com/problems/reorder-list/?envType=list&envId=pboxj983
     * 143. Reorder List
     * 快慢指針
     * @param head
     */
    public void reorderList(ListNode head) {

        ListNode curr = head;
        List<Integer> oddList = new LinkedList<>();

        int count = 0;
        while (curr != null) {
            oddList.add(curr.val);
            curr = curr.next;
            count++;
        }

        count = 0;
        curr = head;
        while (curr != null) {
            if (count % 2 == 0 && !oddList.isEmpty()) {
                curr.val = oddList.remove(0);
            } else if (count % 2 == 1 && !oddList.isEmpty()) {
                curr.val = oddList.remove(oddList.size() - 1);
            }
            curr = curr.next;
            count++;
        }
    }

    /**
     * https://leetcode.com/problems/path-sum-ii/?envType=list&envId=pbo3z5xi
     * 113. Path Sum II
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        pathSumPart(root, targetSum, 0, new ArrayList<>());
        return publicLists;
    }

    private void pathSumPart(TreeNode root, int targetSum, int pathSum, List<Integer> parentNode) {

        if (root == null) {
            return;
        }
        pathSum = pathSum + root.val;
        parentNode.add(root.val);
        if (root.left == null && root.right == null) {
            if (pathSum == targetSum) {

                //這裡是關鍵，因為 parentNode 實際上會影響到其他分支的計算，所以必須用 new 來做
                publicLists.add(new ArrayList<>(parentNode));
                return;
            }
        }
        pathSumPart(root.left, targetSum, pathSum, parentNode);
        pathSumPart(root.right, targetSum, pathSum, parentNode);
        //返回上一層時，把這一層新增的元素拿掉，以確保路徑是正確的
        parentNode.remove(parentNode.size() - 1);

    }

    /**
     * https://leetcode.com/problems/binary-subarrays-with-sum/?envType=daily-question&envId=2024-03-14
     * 930. Binary Subarrays With Sum
     * 想法：本來想用 silde windows，但是程式碼太長，故改用  map
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        int countAns = 0;
        int sum = 0;
        //使用了一個 Map 來記錄每個前綴和出現的次數，並根據目標和 goal 找到符合條件的子數組數量
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int num : nums) {
            sum += num;
            countAns += map.getOrDefault(sum - goal, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return countAns;
    }

    /**
     * https://leetcode.com/problems/find-the-pivot-integer
     * 2485. Find the Pivot Integer
     * @param n
     * @return
     */
    public int pivotInteger(int n) {

        //index = n , value = 從1數到這個數字的總和
        int[] record = new int[n + 1];

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum + i;
            record[i] = sum;
        }
        for (int i = 1; i <= n; i++) {
            if (record[i] == record[n] - record[i - 1]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * https://leetcode.com/problems/power-of-four/
     * 342. Power of Four
     * @param n
     * @return
     */
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }

    /**
     * https://leetcode.com/problems/combination-sum
     * 39. Combination Sum
     * bcakTrack
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        this.combinationSumPart(nums, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void combinationSumPart(int[] nums, int start, int target,
                                    List<Integer> path, List<List<Integer>> result) {
        //傳進來的值已經剪去上一層的值，所以當 == 0 時，即是答案
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        //當 target 小於0時，直接反回不處理
        if (target < 0) return;

        //跟一般的dfs，差别是递回在回圈内
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            //把目標減去現在回圈的值
            combinationSumPart(nums, i, target - nums[i], path, result);
            //不管有沒有超過值，都要把最後一個 path 拿掉
            path.remove(path.size() - 1);
        }
    }

    /**
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        //combinationSum2Part(candidates, target, new ArrayList<>(), result, 0);
        return result;
    }

    /**
     * https://leetcode.com/problems/subsets/solutions/4866645/recursion-java-approach
     * 78. Subsets
     * backTracㄜ
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        result.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(result, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * https://leetcode.com/problems/reorganize-string/
     * 767. Reorganize String
     * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
     * @param s
     * @return 未完成 todo
     * 要用到 heap
     */
    public String reorganizeString(String s) {

        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }

        int count = 0;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            //计算非偶数次数
            if (chars[i] % 2 != 0) {
                count++;
            }
            //非偶数大于1次以上，无法凑成回文
            if (count > 1) {
                return "";
            }
        }

        return sb.toString();
    }

    /**
     * https://leetcode.com/problems/sort-characters-by-frequency
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        StringBuilder res = new StringBuilder();

        //設定這個 heap 的規則
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        pq.addAll(map.keySet());
        while (!pq.isEmpty()) {
            char curr = pq.poll();
            for (int i = 0; i < map.get(curr); i++) {
                res.append(curr);
            }
        }
        return res.toString();
    }

    /**
     * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
     * 373. Find K Pairs with Smallest Sums
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairsII(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((int[] a, int[] b) -> a[0] - b[0]);
        for (int i = 0; i < nums1.length; i++) {
            queue.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }
        List<List<Integer>> res = new ArrayList<>();
        while (k > 0) {
            int[] poll = queue.poll();
            int index1 = poll[1];
            int index2 = poll[2];
            List<Integer> list = Arrays.asList(nums1[index1], nums2[index2]);
            res.add(list);

            k--;
            if (index2 + 1 < nums2.length) {
                //持续在队列中放入新的元素
                queue.offer(new int[]{nums1[index1] + nums2[index2 + 1], index1, index2 + 1});
            }
        }
        return res;
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

