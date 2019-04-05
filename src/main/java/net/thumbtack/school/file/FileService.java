package net.thumbtack.school.file;

import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.windows.v4.Point;
import net.thumbtack.school.windows.v4.RectButton;
import net.thumbtack.school.windows.v4.base.WindowState;

import java.io.*;
import java.util.ArrayList;


public class FileService {

    //Записывает массив байтов в двоичный файл, имя файла задается текстовой строкой.
    public static void writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFile(new File(fileName), array);
    }

    //Записывает массив байтов в двоичный файл, имя файла задается  экземпляром класса File.
    public static void writeByteArrayToBinaryFile(File file, byte[] array) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(array);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Читает массив байтов из двоичного файла, имя файла задается текстовой строкой.
    public static byte[] readByteArrayFromBinaryFile(String fileName) throws IOException {
        return readByteArrayFromBinaryFile(new File(fileName));
    }

    //Читает массив байтов из двоичного файла, имя файла задается экземпляром класса File.
    public static byte[] readByteArrayFromBinaryFile(File file) throws IOException {
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
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toByteArray();
    }

    //Записывает массив байтов в ByteArrayOutputStream, создает на основе данных в полученном  ByteArrayOutputStream
    // экземпляр ByteArrayInputStream и читает из ByteArrayInputStream байты с четными номерами. Возвращает массив
    // прочитанных байтов.
    public static byte[] writeAndReadByteArrayUsingByteStream(byte[] array) throws IOException {
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

        ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
        try {
            ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(byteArray);
            while (byteArrayInput.available() > 0) {
                byteArrayOutput.write(byteArrayInput.read());
            }
            return byteArrayOutput.toByteArray();
        } catch (Exception e) {
            throw e;
        } finally {
            if (byteArray != null) {
                try {
                    byteArrayOutput.close();
                }
                catch (IOException e) {
                    throw e;
                }
            }
        }
    }

    //6.  Записывает массив байтов в двоичный файл, используя буферизованный вывод, имя файла задается текстовой строкой.
    public static void writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFileBuffered(new File(fileName), array);
    }

    //7. Записывает массив байтов в двоичный файл, используя буферизованный вывод, имя файла задается экземпляром класса File.
    public static void writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            bufferedOutputStream.write(array);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //8. Читает массив байтов из двоичного файла, используя буферизованный ввод, имя файла задается текстовой строкой.
    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        return readByteArrayFromBinaryFileBuffered(new File(fileName));
    }

    //9. Читает массив байтов из двоичного файла, используя буферизованный ввод, имя файла задается экземпляром класса File.
    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
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
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    //10.Записывает RectButton в двоичный файл, имя файла задается экземпляром класса File. Состояние окна
    // записывается в виде текстовой строки в формате UTF. Используйте DataOutputStream.
    public static void writeRectButtonToBinaryFile(File file, RectButton rectButton) throws IOException {
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
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //11.    Читает данные для RectButton из двоичного файла и создает на их основе экземпляр RectButton, имя файла
    // задается экземпляром класса File. Предполагается, что данные в файл записаны в формате предыдущего упражнения.
    // Используйте DataInputStream.
    public static RectButton readRectButtonFromBinaryFile(File file) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            int X_TopLeft = Integer.parseInt(dataInputStream.readUTF());
            int Y_TopLeft = Integer.parseInt(dataInputStream.readUTF());
            int X_BottomRight = Integer.parseInt(dataInputStream.readUTF());
            int Y_BottomRight = Integer.parseInt(dataInputStream.readUTF());
            String state = dataInputStream.readUTF();
            String text = dataInputStream.readUTF();
            return new RectButton(new Point(X_TopLeft, Y_TopLeft), new Point(X_BottomRight, Y_BottomRight), state, text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //12.Записывает массив из RectButton в двоичный файл, имя файла задается экземпляром класса File.
    // Поле состояния и текст не записываются.
    public static void writeRectButtonArrayToBinaryFile(File file, RectButton[] rects) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            for (RectButton rectButton : rects) {
                dataOutputStream.writeInt(rectButton.getTopLeft().getX());
                dataOutputStream.writeInt(rectButton.getTopLeft().getY());
                dataOutputStream.writeInt(rectButton.getBottomRight().getX());
                dataOutputStream.writeInt(rectButton.getBottomRight().getY());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //13.В файле массива данных RectButton из предыдущего упражнения увеличивает на 1 значение x каждой точки каждого
    // RectButton. Имя файла задается экземпляром класса File.
    public static void modifyRectButtonArrayInBinaryFile(File file) throws IOException {
        RectButton[] buttons = readRectButtonArrayFromBinaryFile(file);
        for (RectButton button : buttons) {
            button.moveRel(1, 0);
        }
        writeRectButtonArrayToBinaryFile(file, buttons);
    }

    //14.Читает данные, записанные в формате предыдущего упражнения и создает на их основе массив RectButton c с
    // состоянием “ACTIVE” и текстом “OK”.
    public static RectButton[] readRectButtonArrayFromBinaryFile(File file) throws IOException {
        ArrayList<RectButton> rectButtonArrayList = new ArrayList<>();
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            while (dataInputStream.available() > 0) {
                int X_TopLeft = dataInputStream.readInt();
                int Y_TopLeft = dataInputStream.readInt();
                int X_BottomRight = dataInputStream.readInt();
                int Y_BottomRight = dataInputStream.readInt();

                rectButtonArrayList.add(new RectButton(new Point(X_TopLeft, Y_TopLeft),
                        new Point(X_BottomRight, Y_BottomRight), WindowState.ACTIVE, "OK"));
            }
            return rectButtonArrayList.toArray(new RectButton[rectButtonArrayList.size()]);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //15.Записывает RectButton в текстовый файл в одну строку, имя файла задается экземпляром класса File.
    // Поля в файле разделяются пробелами.
    public static void writeRectButtonToTextFileOneLine(File file, RectButton rectButton) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeUTF(rectButton.getTopLeft().getX() + " " + rectButton.getTopLeft().getY() + " " +
                    rectButton.getBottomRight().getX() + " " + rectButton.getBottomRight().getY() + " " +
                    rectButton.getState() + " " + rectButton.getText());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //16.Читает данные для RectButton из текстового файла и создает на их основе экземпляр RectButton, имя файла
    // задается экземпляром класса File. Предполагается, что данные в файл записаны в формате предыдущего упражнения.
    public static RectButton readRectButtonFromTextFileOneLine(File file) throws IOException {

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            String[] splitArray = dataInputStream.readUTF().split(" ");
            int X_TopLeft = Integer.parseInt(splitArray[0]);
            int Y_TopLeft = Integer.parseInt(splitArray[1]);
            int X_BottomRight = Integer.parseInt(splitArray[2]);
            int Y_BottomRight = Integer.parseInt(splitArray[3]);

            return new RectButton(new Point(X_TopLeft, Y_TopLeft),
                    new Point(X_BottomRight, Y_BottomRight), splitArray[4], splitArray[5]);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //17.Записывает RectButton в текстовый файл В первые 4 строки записываются координаты
    // (каждое число в отдельной строке) , в следующие 2 - состояние и текст. Имя файла задается экземпляром класса File.
    public static void writeRectButtonToTextFileSixLines(File file, RectButton rectButton) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            String lineSeparator = System.getProperty("line.separator");
            dataOutputStream.writeUTF(String.valueOf(rectButton.getTopLeft().getX()) + lineSeparator +
                    String.valueOf(rectButton.getTopLeft().getY()) + lineSeparator + String.valueOf(rectButton.getBottomRight().getX())
                    + lineSeparator + String.valueOf(rectButton.getBottomRight().getY()) + lineSeparator +
                    rectButton.getState() + lineSeparator + rectButton.getText());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //18.Читает данные для RectButton из текстового файла и создает на их основе экземпляр RectButton, имя файла
    // задается экземпляром класса File. Предполагается, что данные в файл записаны в формате предыдущего упражнения.
    public static RectButton readRectButtonFromTextFileSixLines(File file) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            String lineSeparator = System.getProperty("line.separator");
            String[] splitArray = dataInputStream.readUTF().split(lineSeparator);
            return new RectButton(new Point(Integer.parseInt(splitArray[0]), Integer.parseInt(splitArray[1])),
                    new Point(Integer.parseInt(splitArray[2]), Integer.parseInt(splitArray[3])), splitArray[4], splitArray[5]);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //19.Записывает Trainee в текстовый файл в одну строку в кодировке UTF-8, имя файла задается экземпляром класса
    // File. Имя, фамилия и оценка в файле разделяются пробелами.
    public static void writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeUTF(trainee.getFirstName() + " " + trainee.getLastName() + " " + trainee.getRating());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //20.Читает данные для Trainee из текстового файла и создает на их основе экземпляр Trainee, имя файла задается
    // экземпляром класса File. Предполагается, что данные в файл записаны в формате предыдущего упражнения.
    public static Trainee readTraineeFromTextFileOneLine(File file) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            String[] traineeTextArray = dataInputStream.readUTF().split(" ");
            return new Trainee(traineeTextArray[0], traineeTextArray[1], Integer.parseInt(traineeTextArray[2]));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //21.Записывает Trainee в текстовый файл в кодировке UTF-8, каждое поле в отдельной строке, имя файла задается
    // экземпляром класса File.
    public static void writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            String lineSeparator = System.getProperty("line.separator");
            dataOutputStream.writeUTF(trainee.getFirstName() + lineSeparator + trainee.getLastName() + lineSeparator +
                    trainee.getRating());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //22.Читает данные для Trainee из текстового файла и создает на их основе экземпляр Trainee, имя файла задается
    // экземпляром класса File. Предполагается, что данные в файл записаны в формате предыдущего упражнения.
    public static Trainee readTraineeFromTextFileThreeLines(File file) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            String lineSeparator = System.getProperty("line.separator");
            String[] traineeArray = dataInputStream.readUTF().split(lineSeparator);
            return new Trainee(traineeArray[0], traineeArray[1], Integer.parseInt(traineeArray[2]));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //23.Сериализует Trainee в двоичный файл, имя файла задается экземпляром класса File.
    public static void serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(trainee);
            objectOutputStream.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //24.    Десериализует Trainee из двоичного файла, имя файла задается экземпляром класса File. Предполагается, что
    // данные в файл записаны в формате предыдущего упражнения.
    public static Trainee deserializeTraineeFromBinaryFile(File file) throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (Trainee) objectInputStream.readObject();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //25.Сериализует Trainee в формате Json в текстовую строку.
    public static String serializeTraineeToJsonString(Trainee trainee) {
        String serializableString = new String(String.valueOf(trainee));
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(serializableString))) {
            objectOutputStream.writeObject(trainee);
            return serializableString;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //26. Десериализует Trainee из текстовой строки с Json-представлением Trainee.
    public static Trainee deserializeTraineeFromJsonString(String json) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(json))) {
            return (Trainee) objectInputStream.readObject();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //27.Сериализует Trainee в формате Json в файл, имя файла задается экземпляром класса File.
    public static void serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(trainee);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //28.Десериализует Trainee из файла с Json-представлением Trainee, имя файла задается экземпляром класса File.
    public static Trainee deserializeTraineeFromJsonFile(File file) throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (Trainee) objectInputStream.readObject();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
