(ns advent-of-code.2020.day-02
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str]))


(defn get-data
  []
  (->> (io/resource "2020/day-02.txt")
       (slurp)
       (str/split-lines)))


;;; Part 1

(def password-pattern
  "Expects to match a string like '1-3 m: masdfsk'."
  #"([0-9]+)-([0-9]+) ([a-z]): ([a-z]*)")


(defn parse-line
  [line]
  (-> (zipmap
        [:low :high :letter :password]
        (rest (re-matches password-pattern line)))
      (update :low #(Integer/parseInt %))
      (update :high #(Integer/parseInt %))))


(defn valid-password?
  [{:keys [low high letter password]}]
  (<= low (count (re-seq (re-pattern letter) password)) high))


(defn problem-1
  []
  (->> (get-data)
       (map parse-line)
       (filter valid-password?)
       (count)))


;;; Part 2

(defn problem-2
  [])
