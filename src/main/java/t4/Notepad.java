package t4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notepad {
    private Map<String, DayEntry> entries;

    public Notepad() {
        entries = new HashMap<>();
    }

    public void addEntry(String date, String note) {
        DayEntry dayEntry = entries.getOrDefault(date, new DayEntry());
        dayEntry.addNote(note);
        entries.put(date, dayEntry);
    }

    public List<String> getEntries(String date) {
        DayEntry dayEntry = entries.get(date);
        if (dayEntry != null) {
            return dayEntry.getNotes();
        } else {
            return new ArrayList<>();
        }
    }

    public void printAllEntries() {
        for (Map.Entry<String, DayEntry> entry : entries.entrySet()) {
            String date = entry.getKey();
            DayEntry dayEntry = entry.getValue();
            List<String> notes = dayEntry.getNotes();

            for (String note : notes) {
                System.out.println("[" + date + "] - " + note);
            }
        }
    }


    // Внутренний класс для хранения нескольких записей на одну дату
    private static class DayEntry {
        private List<String> notes;

        public DayEntry() {
            notes = new ArrayList<>();
        }

        public void addNote(String note) {
            notes.add(note);
        }

        public List<String> getNotes() {
            return notes;
        }
    }
}
