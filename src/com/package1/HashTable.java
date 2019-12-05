package com.package1;

import java.util.LinkedList;

public class HashTable {

    private static class Entry{
        private int key;
        private String value;

        public Entry(int key, String value){
            this.key = key;
            this.value = value;
        }

    }

    private LinkedList<Entry>[] entries = new LinkedList[5];

    public void put(int key, String value){

        var entry = getEntry(key);
        if (entry != null){
            entry.value = value;
        }
       getOrCreateBucket(key).addLast(new Entry(key,value));
    }

    public String get(int key){

        var entry = getEntry(key);
        return (entry != null) ? entry.value : null;
    }

    public void remove(int key){

        var entry = getEntry(key);
        if (entry != null){
            getBucket(key).remove(entry);
            return;
        }
        System.out.print("there is no target");
    }

    private int hash(int key){
        return key % entries.length;
    }

    private LinkedList<Entry> getBucket(int key){
        return entries[hash(key)];
    }

    private LinkedList<Entry> getOrCreateBucket(int key){
        var bucket = getBucket(key);
        if (bucket == null){
            entries[hash(key)] = new LinkedList<>();
        }
        return bucket;
    }

    private Entry getEntry(int key){
        var bucket = getBucket(key);
        if (bucket != null){
            for (var entry:bucket){
                if (entry.key == key){
                    return entry;
                }
            }
        }
        return null;
    }

}


