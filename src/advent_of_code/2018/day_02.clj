(ns advent-of-code.2018.day-02
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str]))


(def data
  (->> (io/resource "advent-of-code/2018/day-02.txt")
       slurp
       str/split-lines))


;;; Part 1

(defn item-frequency-match
  [matches coll]
  (let [freqs (->> (frequencies coll)
                   (vals)
                   (into #{}))]
    (reduce (fn [m match] (assoc m match (if (freqs match) 1 0)))
            {}
            matches)))


(defn problem-1
  [data]
  (let [exact-matches #{2 3}]
    (->> data
         (map (partial item-frequency-match exact-matches))
         (reduce (partial merge-with +))
         (vals)
         (reduce *))))


;;; Part 2

(defn remove-nth-char
  [n s]
  (assert (<= 0 n (dec (count s))))
  (str (subs s 0 n) (subs s (inc n))))


;; IDEA: strip off a character at a time and check frequencies.
;; use `subs` to strip out characters
(defn problem-2
  [data]
  (let [max-len (count (first data))
        positions (range max-len)]
    (loop [n 0]
      (let [common-letters (->> (map (partial remove-nth-char n) data)
                                (frequencies)
                                (filter #(= 2 (second %)))
                                (into {})
                                (keys)
                                (first))]
      (if (or common-letters (= n max-len))
        common-letters
        (recur (inc n)))))))
