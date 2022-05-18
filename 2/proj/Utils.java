package proj;

public final class Utils {
    public static String IP2Hex(String ip) {
        String hex = "";
        String[] part = ip.split("[\\.,]");
        if (part.length < 4) {
            System.out.println("00000000");
        }

        for (int i = 0; i < 4; i++) {
            int decimal = Integer.parseInt(part[i]);
            if (decimal < 16) {
                hex += "0" + String.format("%01X", decimal);
            } else {
                hex += String.format("%01X", decimal);
            }
        }

        return hex;
    }
}
