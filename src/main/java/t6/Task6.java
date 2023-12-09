package t6;

public class Task6 {
    public Task6() {
        try {
            Word word1 = new Word("Просто");
            Word word2 = new Word("проверка");
            Sentence sentence1 = new Sentence();
            sentence1.addWord(word1);
            sentence1.addWord(word2);

            Word word3 = new Word("Съешь");
            Word word4 = new Word("ещё");
            Word word5 = new Word("этих");
            Word word6 = new Word("мягких");
            Word word7 = new Word("французских");
            Word word8 = new Word("булочек");
            Sentence sentence2 = new Sentence();
            sentence2.addWord(word3);
            sentence2.addWord(word4);
            sentence2.addWord(word5);
            sentence2.addWord(word6);
            sentence2.addWord(word7);
            sentence2.addWord(word8);

            Text text = new Text("Заголовок текста");
            text.addSentence(sentence1);
            text.addSentence(sentence2);

            text.printText();


            System.out.println("\n\nПопытка вывести пустой текст...");
            // Создаем текст с пустым предложением
            Text emptyText = new Text("Пустой текст");
            emptyText.printText(); // Вызываем метод printText(), который может сгенерировать EmptyTextException
        } catch (EmptyTextException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Общая ошибка: " + e.getMessage());
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        new Task6();
    }
}
