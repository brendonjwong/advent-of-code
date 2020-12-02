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

(defn parse-line-2
  [line]
  (-> (zipmap
        [:position-1 :position-2 :letter :password]
        (rest (re-matches password-pattern line)))
      ;; `dec` because the positions start at index 1
      (update :position-1 #(dec (Integer/parseInt %)))
      (update :position-2 #(dec (Integer/parseInt %)))))


(defn valid-password-2?
  [{:keys [position-1 position-2 letter password]}]
  (or (and (= letter (str (get password position-1)))
           (not= letter (str (get password position-2))))
      (and (not= letter (str (get password position-1)))
           (= letter (str (get password position-2))))))


(defn problem-2
  []
  (->> (get-data)
       (map parse-line-2)
       (filter valid-password-2?)
       (count)))
