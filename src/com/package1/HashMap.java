package com.package1;


public class HashMap {

    private static class Entry{
        private int key;
        private String value;

        public Entry(int key, String value){
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] entries = new Entry[5];

    public void put(int key, String value){
        var index = hash(key);
        if (entries[index] == null){
            entries[index] = new Entry(key,value);
            return;
        }
        while (entries[index] != null){
            index++;
            if (index == entries.length)
                index = 0;
        }
        entries[index] = new Entry(key,value);
    }

    public String get(int key){
        var index = hash(key);
        int step = 0;
        while (entries[index].key != key && step < entries.length){
            index++;
            if (index == entries.length)
                index = 0;
            step++;
        }
        if (step == entries.length){
            return null;
        }
        return entries[index].value;
    }

    private int hash(int key){
        return key % entries.length;
    }



}
