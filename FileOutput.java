package threads;

import java.io.*;

/*
Последовательный вывод файлов
1. В статическом блоке считай 2 имени файла firstFileName и secondFileName.
2. Внутри класса Solution создай нить public static ReadFileThread, которая реализует
интерфейс ReadFileInterface (Подумай, что больше подходит - Thread или Runnable).
3.1. Метод setFileName должен устанавливать имя файла, из которого будет читаться содержимое.
3.2. Метод getFileContent должен возвращать содержимое файла.
3.3. В методе run считай содержимое файла, закрой поток. Раздели пробелом строки файла.
4. Подумай, в каком месте нужно подождать окончания работы нити, чтобы обеспечить последовательный вывод файлов.

Ожидаемый вывод:
[все тело первого файла]
[все тело второго файла]
*/

public class FileOutput {
    public static String firstFileName;
    public static String secondFileName;

    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
        } catch (IOException ignore) {
            /*NOP*/
        }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface {
        StringBuffer sb = new StringBuffer();

        @Override
        public void setFileName(String fullFileName) {
            super.setName(fullFileName);
        }

        @Override
        public void run() {
            try {
                String line;
                BufferedReader inner_reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.getName())));
                while ((line = inner_reader.readLine()) != null) {
                    sb.append(line + " ");
                }
                inner_reader.close();
            } catch (FileNotFoundException ignore) {
                /*NOP*/
            } catch (IOException ignore) {
                /*NOP*/
            }
        }

        @Override
        public String getFileContent() {
            return new String(sb);
        }
    }
}