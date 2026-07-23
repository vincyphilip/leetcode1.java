class Solution {
    public String[] findWords(String[] words) {
        String row1 = "qwertyuiop";
        String row2 = "asdfghjkl";
        String row3 = "zxcvbnm";

        List<String> result = new ArrayList<>();

        for (String word : words) {
            String w = word.toLowerCase();

            String row;
            if (row1.contains("" + w.charAt(0)))
                row = row1;
            else if (row2.contains("" + w.charAt(0)))
                row = row2;
            else
                row = row3;

            boolean valid = true;
            for (char c : w.toCharArray()) {
                if (!row.contains("" + c)) {
                    valid = false;
                    break;
                }
            }

            if (valid)
                result.add(word);
        }

        return result.toArray(new String[0]);
    }
}
