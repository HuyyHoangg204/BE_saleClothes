package com.sale_clothes.nhom11.logger.ultil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final boolean DEBUG = true;

    public static void DebugLogic(String str, int deep) {
        if (!DEBUG) {
            return;
        }
        try {
            str += "\n\tat " + Thread.currentThread().getStackTrace()[2 + deep];
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Logic>>>\t" + str);
    }

    public static void DebugLogic(String str) {
        if (!DEBUG) {
            return;
        }
        try {
            str += "\n\tat " + Thread.currentThread().getStackTrace()[2];
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Logic>>>\t" + str);
    }

    public static void DebugLogic(String message, Exception exception) {
        System.out.println("wtf ?");
        if (!DEBUG) {
            return;
        }
        String timestamp = dtFormatter.format(LocalDateTime.now());  // Lấy thời gian hiện tại
        // Nội dung log
        StringBuilder logContent = new StringBuilder();
        logContent.append("====================================\n");
        logContent.append("<>Time: ").append(timestamp).append("\n");
        logContent.append("<>Message: ").append(message).append("\n");
        logContent.append("<>CallFrom: at ").append(Thread.currentThread().getStackTrace()[2]).append("\n");
        if (exception != null) {
            // Lấy thông tin stack trace từ exception dưới dạng String
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);  // In stack trace vào StringWriter
            logContent.append("<>Exception: ").append(exception.toString()).append("\n");
            logContent.append("<>StackTrace: ").append(sw.toString()).append("\n");
        }
        logContent.append("------------------------------------\n");
        System.out.println(logContent.toString());
    }
}
