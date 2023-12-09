package t6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Word {
    private String word;

    public Word(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public String toString() {
        return word;
    }
}

class Sentence {
    private List<Word> words;

    public Sentence() {
        this.words = new ArrayList<>();
    }

    public void addWord(Word word) {
        words.add(word);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(words, sentence.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(words);
    }

    @Override
    public String toString() {
        StringBuilder sentenceString = new StringBuilder();
        for (Word word : words) {
            sentenceString.append(word).append(" ");
        }
        return sentenceString.toString().trim();
    }
}

class Text {
    private List<Sentence> sentences;
    private String title;

    public Text(String title) {
        this.title = title;
        this.sentences = new ArrayList<>();
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public void printText() throws EmptyTextException {
        try {
            if (sentences.isEmpty()) {
                throw new EmptyTextException("Текст не содержит предложений.");
            }

            System.out.println(title);
            for (Sentence sentence : sentences) {
                System.out.print(sentence + ". ");
            }
            System.out.println();
        } catch (EmptyTextException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return Objects.equals(sentences, text.sentences) &&
                Objects.equals(title, text.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentences, title);
    }

    @Override
    public String toString() {
        StringBuilder textString = new StringBuilder();
        textString.append(title).append("\n");
        for (Sentence sentence : sentences) {
            textString.append(sentence).append(". ");
        }
        return textString.toString();
    }
}

class EmptyTextException extends Exception {
    public EmptyTextException(String message) {
        super(message);
    }
}

