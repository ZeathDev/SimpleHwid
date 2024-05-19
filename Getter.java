import java.io.IOException;

public class Getter {
    // 自定义加密密钥
    private static final String key = "ZeathDev";

    public static void main(String[] args) {
        try {
            System.out.println("加密前:" + getUUID());
            String str = MathUtils.AES(getUUID(), key.toString());
            System.out.println("加密后:" + str);
            System.out.println("解密后:" + MathUtils.AntiAES(str, key.toString()));
        } catch (Exception e) {
            System.out.println("Can't get uuid");
        }
    }

    private static String getUUID() throws IOException {
        Process process = Runtime.getRuntime().exec("wmic csproduct get uuid");
        String output = parseOutput(process);
        return output.substring(4).trim();
    }

    private static String parseOutput(Process process) throws IOException {
        byte[] buffer = new byte[1024];
        StringBuilder output = new StringBuilder();
        process.getInputStream().read(buffer);
        output.append(new String(buffer));
        return output.toString();
    }
}
