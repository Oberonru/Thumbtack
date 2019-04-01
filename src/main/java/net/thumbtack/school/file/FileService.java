package net.thumbtack.school.file;

import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.windows.v4.RectButton;

import java.io.*;
import java.util.ArrayList;


public class FileService {

    //Записывает массив байтов в двоичный файл, имя файла задается текстовой строкой.
    public static void writeByteArrayToBinaryFile(String fileName, byte[] array) throws FileNotFoundException {
        writeByteArrayToBinaryFile(new File(fileName), array);
    }

    //Записывает массив байтов в двоичный файл, имя файла задается  экземпляром класса File.
    public static void writeByteArrayToBinaryFile(File file, byte[] array) throws FileNotFoundException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(array);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Читает массив байтов из двоичного файла, имя файла задается текстовой строкой.
    public static byte[] readByteArrayFromBinaryFile(String fileName) {
        return readByteArrayFromBinaryFile(new File(fileName));
    }

    //Читает массив байтов из двоичного файла, имя файла задается экземпляром класса File.
    public static byte[] readByteArrayFromBinaryFile(File file) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            while (fileInputStream.available() > 0) {
                result.write(fileInputStream.read());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toByteArray();
    }

    //Записывает массив байтов в ByteArrayOutputStream, создает на основе данных в полученном  ByteArrayOutputStream
    // экземпляр ByteArrayInputStream и читает из ByteArrayInputStream байты с четными номерами. Возвращает массив
    // прочитанных байтов.
    public static byte[] writeAndReadByteArrayUsingByteStream(byte[] array) {
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                list.add(array[i]);
            }
        }
        byte[] byteArray = new byte[list.size()];
        for (int j = 0; j < byteArray.length; j++) {
            byteArray[j] = list.get(j).byteValue();
        }
        ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(byteArray);
        ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
        while (byteArrayInput.available() > 0) {
            byteArrayOutput.write(byteArrayInput.read());
        }
        return byteArrayOutput.toByteArray();
    }

    //6.  Записывает массив байтов в двоичный файл, используя буферизованный вывод, имя файла задается текстовой строкой.
    public static void writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) {
        writeByteArrayToBinaryFileBuffered(new File(fileName), array);
    }

    //7. Записывает массив байтов в двоичный файл, используя буферизованный вывод, имя файла задается экземпляром класса File.
    public static void writeByteArrayToBinaryFileBuffered(File file, byte[] array) {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            bufferedOutputStream.write(array);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //8. Читает массив байтов из двоичного файла, используя буферизованный ввод, имя файла задается текстовой строкой.
    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) {
        return readByteArrayFromBinaryFileBuffered(new File(fileName));
    }

    //9. Читает массив байтов из двоичного файла, используя буферизованный ввод, имя файла задается экземпляром класса File.
    public static byte[] readByteArrayFromBinaryFileBuffered(File file) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            while (bufferedInputStream.available() > 0) {
                byteArrayOutputStream.write(bufferedInputStream.read());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    //10.Записывает RectButton в двоичный файл, имя файла задается экземпляром класса File. Состояние окна
    // записывается в виде текстовой строки в формате UTF. Используйте DataOutputStream.
    public static void writeRectButtonToBinaryFile(File file, RectButton rectButton) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {

            dataOutputStream.writeUTF(String.valueOf(rectButton.getTopLeft().getX()));
            dataOutputStream.writeUTF(String.valueOf(rectButton.getTopLeft().getY()));
            dataOutputStream.writeUTF(String.valueOf(rectButton.getBottomRight().getX()));
            dataOutputStream.writeUTF(String.valueOf(rectButton.getBottomRight().getY()));
            dataOutputStream.writeUTF(rectButton.getState().toString());
            dataOutputStream.writeUTF(rectButton.getText());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //11.    Читает данные для RectButton из двоичного файла и создает на их основе экземпляр RectButton, имя файла
    // задается экземпляром класса File. Предполагается, что данные в файл записаны в формате предыдущего упражнения.
    // Используйте DataInputStream.
    public static RectButton readRectButtonFromBinaryFile(File file) {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {

            int X_TopLeft = dataInputStream.readInt();
            int Y_TopLeft = dataInputStream.readInt();
            int X_BottomRight = dataInputStream.readInt();
            int Y_BottomRight = dataInputStream.readInt();
            String state = dataInputStream.readUTF();
            String text = dataInputStream.readUTF();
            return new RectButton(X_TopLeft, Y_TopLeft, X_BottomRight, Y_BottomRight, state, text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //12.Записывает массив из RectButton в двоичный файл, имя файла задается экземпляром класса File.
    // Поле состояния и текст не записываются.
    public static void writeRectButtonArrayToBinaryFile(File file, RectButton[] rects) {

    }

    //13.В файле массива данных RectButton из предыдущего упражнения увеличивает на 1 значение x каждой точки каждого
    // RectButton. Имя файла задается экземпляром класса File.
    public static void modifyRectButtonArrayInBinaryFile(File file) {

    }

    //14.Читает данные, записанные в формате предыдущего упражнения и создает на их основе массив RectButton c с
    // состоянием “ACTIVE” и текстом “OK”.
    public static RectButton[] readRectButtonArrayFromBinaryFile(File file) {
        return null;
    }

    //15.Записывает RectButton в текстовый файл в одну строку, имя файла задается экземпляром класса File.
    // Поля в файле разделяются пробелами.
    public static void writeRectButtonToTextFileOneLine(File file, RectButton rectButton) {

    }

    //16.Читает данные для RectButton из текстового файла и создает на их основе экземпляр RectButton, имя файла
    // задается экземпляром класса File. Предполагается, что данные в файл записаны в формате предыдущего упражнения.
    public static RectButton readRectButtonFromTextFileOneLine(File file) {
        return null;
    }

    //17.Записывает RectButton в текстовый файл В первые 4 строки записываются координаты
    // (каждое число в отдельной строке) , в следующие 2 - состояние и текст. Имя файла задается экземпляром класса File.
    public static void writeRectButtonToTextFileSixLines(File file, RectButton rectButton) {

    }

    //18.Читает данные для RectButton из текстового файла и создает на их основе экземпляр RectButton, имя файла
    // задается экземпляром класса File. Предполагается, что данные в файл записаны в формате предыдущего упражнения.
    public static RectButton readRectButtonFromTextFileSixLines(File file) {
        return null;
    }

    //19.Записывает Trainee в текстовый файл в одну строку в кодировке UTF-8, имя файла задается экземпляром класса
    // File. Имя, фамилия и оценка в файле разделяются пробелами.
    public static void writeTraineeToTextFileOneLine(File file, Trainee trainee) {

    }

    //20.Читает данные для Trainee из текстового файла и создает на их основе экземпляр Trainee, имя файла задается
    // экземпляром класса File. Предполагается, что данные в файл записаны в формате предыдущего упражнения.
    public static Trainee readTraineeFromTextFileOneLine(File file) {
        return null;
    }

    //21.Записывает Trainee в текстовый файл в кодировке UTF-8, каждое поле в отдельной строке, имя файла задается
    // экземпляром класса File.
    public static void writeTraineeToTextFileThreeLines(File file, Trainee trainee) {

    }

    //22.Читает данные для Trainee из текстового файла и создает на их основе экземпляр Trainee, имя файла задается
    // экземпляром класса File. Предполагается, что данные в файл записаны в формате предыдущего упражнения.
    public static Trainee readTraineeFromTextFileThreeLines(File file) {
        return null;
    }

    //23.Сериализует Trainee в двоичный файл, имя файла задается экземпляром класса File.
    public static void serializeTraineeToBinaryFile(File file, Trainee trainee) {

    }

    //24.    Десериализует Trainee из двоичного файла, имя файла задается экземпляром класса File. Предполагается, что
    // данные в файл записаны в формате предыдущего упражнения.
    public static Trainee deserializeTraineeFromBinaryFile(File file) {
        return null;
    }

    //25.Сериализует Trainee в формате Json в текстовую строку.
    public static String serializeTraineeToJsonString(Trainee trainee) {
        return "";
    }

    //26. Десериализует Trainee из текстовой строки с Json-представлением Trainee.
    public static Trainee deserializeTraineeFromJsonString(String json) {
        return null;
    }

    //27.Сериализует Trainee в формате Json в файл, имя файла задается экземпляром класса File.
    public static void serializeTraineeToJsonFile(File file, Trainee trainee) {

    }

    //28.Десериализует Trainee из файла с Json-представлением Trainee, имя файла задается экземпляром класса File.
    public static Trainee deserializeTraineeFromJsonFile(File file) {
        return null;
    }


}
